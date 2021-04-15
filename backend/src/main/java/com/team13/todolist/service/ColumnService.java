package com.team13.todolist.service;

import com.team13.todolist.model.*;
import com.team13.todolist.repository.ActivityRepository;
import com.team13.todolist.repository.CardRepository;
import com.team13.todolist.repository.ColumnRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ColumnService {

    private final ColumnRepository columnRepository;
    private final CardRepository cardRepository;
    private final ActivityRepository activityRepository;

    public ColumnService(ColumnRepository columnRepository, CardRepository cardRepository, ActivityRepository activityRepository) {
        this.columnRepository = columnRepository;
        this.cardRepository = cardRepository;
        this.activityRepository = activityRepository;
    }

    public Column addColumn(String name) {
        Column column = Column.of(name, new HashMap<>());
        return columnRepository.save(column);
    }

    @Transactional
    public CardInfo addCard(Long columnId, Card card) {
        Column column = columnRepository.findById(columnId).orElseThrow(() -> new RuntimeException("Not Found"));
        Card newCard = cardRepository.save(card);
        column.addCard(newCard);
        column = columnRepository.save(column);

        Activity activity = Activity.of(1L, "Added", card.getTitle(), null, column.getName());
        activityRepository.save(activity);

        return new CardInfo(column.getId(), newCard.getId(), 0L, newCard.getTitle(), newCard.getBody());
    }

    public ColumnInfo getColumn(Long columnId) {
        Column column = columnRepository.findById(columnId).orElseThrow(() -> new RuntimeException("Not Found"));
        Map<Long, CardRef> cardMap = column.getCards();
        List<CardInfo> cards = makeCardList(columnId, cardMap);
        return new ColumnInfo(columnId, column.getName(), cards);
    }

    private List<CardInfo> makeCardList(Long columnId, Map<Long, CardRef> cardMap) {
        List<CardInfo> cards = new ArrayList<>();
        Long previousCardId = 0L;
        CardRef cardRef;
        while ((cardRef = cardMap.get(previousCardId)) != null) {
            Card card = cardRepository.findById(cardRef.getCardId()).orElseThrow(() -> new RuntimeException("Not Found"));
            Long cardId = card.getId();
            CardInfo cardInfo = new CardInfo(columnId, cardId, previousCardId, card.getTitle(), card.getBody());
            cards.add(cardInfo);
            previousCardId = cardId;
        }
        return cards;
    }

    public List<ColumnInfo> getColumns() {
        List<Column> columns = columnRepository.findAll();
        List<ColumnInfo> ColumnInfoList = new ArrayList<>();
        for (Column column : columns) {
            ColumnInfoList.add(getColumn(column.getId()));
        }
        return ColumnInfoList;
    }

    @Transactional
    public void removeCard(Long columnId, Long cardId) {
        Column column = columnRepository.findById(columnId).orElseThrow(() -> new RuntimeException("Not Found"));
        Card card = cardRepository.findById(cardId).orElseThrow(() -> new RuntimeException("Not Found"));
        column.removeCard(cardId);
        columnRepository.save(column);

        Activity activity = Activity.of(1L, "Deleted", card.getTitle(), null, null);
        activityRepository.save(activity);
    }

    @Transactional
    public CardInfo updateCard(Long columnId, Long cardId, UpdateCardParameter updateCardInfo) {
        Column column = columnRepository.findById(columnId).orElseThrow(() -> new RuntimeException("Not Found"));
        Long newPreviousCardId = updateCardInfo.getPreviousCardId();
        Card oldCard = cardRepository.findById(cardId).orElseThrow(() -> new RuntimeException("Not Found"));
        // edit card
        if (column.isEditRequest(columnId, cardId, updateCardInfo)) {
            oldCard.update(updateCardInfo);
            Card updatedCard = cardRepository.save(oldCard);

            Activity activity = Activity.of(1L, "Edited", updateCardInfo.getTitle(), null, null);
            activityRepository.save(activity);

            return new CardInfo(
                    columnId,
                    cardId,
                    newPreviousCardId,
                    updatedCard.getTitle(),
                    updatedCard.getBody());
        }
        //move card
        column.containsCardOrThrowException(cardId);
        Column targetColumn = column;
        if (!updateCardInfo.equalsColumnId(columnId)) {
            targetColumn = columnRepository.findById(updateCardInfo.getColumnId())
                    .orElseThrow(() -> new RuntimeException("Not Found"));
        }
        column.removeCard(cardId);
        targetColumn.addCard(updateCardInfo.getPreviousCardId(), cardId);
        if (!column.equals(targetColumn)) {
            columnRepository.save(column);
        }
        columnRepository.save(targetColumn);

        Activity activity = Activity.of(1L, "Moved", oldCard.getTitle(),
                column.getName(), targetColumn.getName());
        activityRepository.save(activity);

        return new CardInfo(
                targetColumn.getId(),
                cardId,
                newPreviousCardId,
                oldCard.getTitle(),
                oldCard.getBody());
    }
}
