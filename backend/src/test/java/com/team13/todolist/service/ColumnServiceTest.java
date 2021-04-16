package com.team13.todolist.service;

import com.team13.todolist.model.*;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class ColumnServiceTest {

    @Autowired
    private ColumnService columnService;

    private ColumnInfo column;

    private SoftAssertions softly;

    @BeforeEach
    void setUp() {
        softly = new SoftAssertions();
        column = columnService.addColumn(new ColumnParameter("할 일"));
    }

    @AfterEach
    void afterEach() {
        softly.assertAll();
    }

    @Test
    @DisplayName("칼럼을 추가한다.")
    public void addColumn() {
        softly.assertThat(column).hasNoNullFieldsOrProperties();
        softly.assertThat(column.getName()).isEqualTo("할 일");
    }

    @Test
    @DisplayName("새로운 카드를 원하는 칼럼에 추가한다.")
    public void addNewCard() {
        CardInfo cardInfo = columnService.addCard(column.getColumnId(), Card.of("Spring 공부", "토비의 스프링"));
        softly.assertThat(cardInfo)
                .hasFieldOrPropertyWithValue("columnId", column.getColumnId())
                .hasFieldOrProperty("cardId")
                .hasFieldOrPropertyWithValue("previousCardId", 0L)
                .hasFieldOrPropertyWithValue("title", "Spring 공부")
                .hasFieldOrPropertyWithValue("body", "토비의 스프링");
    }

    @Test
    @DisplayName("빈 칼럼 하나를 가져온다.")
    void getEmptyColumn() {
        ColumnInfo columnInfo = columnService.getColumn(column.getColumnId());
        softly.assertThat(columnInfo.getColumnId()).isEqualTo(column.getColumnId());
        softly.assertThat(columnInfo.getName()).isEqualTo("할 일");
        softly.assertThat(columnInfo.getCards()).hasSize(0);
    }

    @Test
    @DisplayName("칼럼 하나를 가져온다.")
    void getColumnWithCard() {
        columnService.addCard(column.getColumnId(), Card.of("Spring 공부", "토비의 스프링"));
        columnService.addCard(column.getColumnId(), Card.of("DB 공부", "데이터베이스 첫걸음"));
        columnService.addCard(column.getColumnId(), Card.of("AWS 공부", "호눅스 강의"));

        ColumnInfo columnInfo = columnService.getColumn(column.getColumnId());
        softly.assertThat(columnInfo.getColumnId()).isEqualTo(column.getColumnId());
        softly.assertThat(columnInfo.getName()).isEqualTo("할 일");
        softly.assertThat(columnInfo.getCards()).hasSize(3);
    }

    @Test
    @DisplayName("모든 칼럼을 가져온다.")
    public void getColumns() {
        columnService.addCard(column.getColumnId(), Card.of("Spring 공부", "토비의 스프링"));
        columnService.addCard(column.getColumnId(), Card.of("DB 공부", "데이터베이스 첫걸음"));
        columnService.addCard(column.getColumnId(), Card.of("AWS 공부", "호눅스 강의"));

        ColumnInfo doneColumn = columnService.addColumn(new ColumnParameter("Done"));
        columnService.addCard(doneColumn.getColumnId(), Card.of("잠자기", "침대에서 꿀잠"));
        columnService.addCard(doneColumn.getColumnId(), Card.of("노트북 설치", "리눅스 민트"));

        List<ColumnInfo> columns = columnService.getColumns();
        softly.assertThat(columns).hasSize(5);
    }

    @Test
    @DisplayName("칼럼에서 카드를 삭제한다.")
    public void removeCard() {
        columnService.addCard(column.getColumnId(), Card.of("Spring 공부", "토비의 스프링"));
        CardInfo cardInfo = columnService.addCard(column.getColumnId(), Card.of("DB 공부", "데이터베이스 첫걸음"));
        columnService.addCard(column.getColumnId(), Card.of("AWS 공부", "호눅스 강의"));

        columnService.removeCard(column.getColumnId(), cardInfo.getCardId());

        ColumnInfo columnInfo = columnService.getColumn(column.getColumnId());
        softly.assertThat(columnInfo.getCards()).hasSize(2);
    }

}
