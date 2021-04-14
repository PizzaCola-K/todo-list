package com.team13.todolist.model;

import org.springframework.data.annotation.Id;

import java.util.Map;
import java.util.Objects;

public class Column {
    @Id
    private final Long id;

    private String name;
    private Map<Long, CardRef> cards;

    Column(Long id, String name, Map<Long, CardRef> cards) {
        this.id = id;
        this.name = name;
        this.cards = cards;
    }

    public static Column of(String name, Map<Long, CardRef> cards) {
        return new Column(null, name, cards);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<Long, CardRef> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        addCard(0L, card.getId());
    }

    public void addCard(Long prevCardId, Long cardId) {
        if (!(cards.containsKey(prevCardId) || prevCardId.equals(0L))) {
            containsCardOrThrowException(prevCardId);
        }
        CardRef nextCard = cards.put(prevCardId, new CardRef(cardId));
        if (nextCard != null) {
            cards.putIfAbsent(cardId, nextCard);
        }
    }

    public void removeCard(Long cardId) {
        Long prevCardId = findPrevCardId(cardId);
        removeCardByPreviousCardId(prevCardId, cardId);
    }

    public void removeCardByPreviousCardId(Long prevCardId, Long cardId) {
        cards.remove(prevCardId);
        CardRef nextCard = cards.remove(cardId);
        if (nextCard != null) {
            cards.put(prevCardId, nextCard);
        }
    }

    public Long findPrevCardId(Long cardId) {
        CardRef cardRef = CardRef.of(cardId);
        for (Map.Entry<Long, CardRef> entry : cards.entrySet()) {
            if (entry.getValue().equals(cardRef)) {
                return entry.getKey();
            }
        }
        throw new RuntimeException("Not Found");
    }

    public void containsCardOrThrowException(Long cardId) {
        if (!cards.containsValue(CardRef.of(cardId))) {
            // TODO: Throw prefer exception
            throw new RuntimeException("Not Found");
        }
    }

    public boolean isEditRequest(Long columnId, Long cardId, UpdateCardParameter updateCardInfo) {
        CardRef checkingCard = cards.get(updateCardInfo.getPreviousCardId());
        return updateCardInfo.equalsColumnId(columnId) && updateCardInfo.equalsCardId(cardId)
                && (checkingCard != null && checkingCard.equalsCardId(cardId));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Column column = (Column) o;
        return Objects.equals(id, column.id) && Objects.equals(name, column.name) && Objects.equals(cards, column.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cards);
    }
}
