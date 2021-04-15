package com.team13.todolist.repository;

import com.team13.todolist.model.Activity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ActivityRepositoryTest {

    @Autowired
    private ActivityRepository activityRepository;

    @Test
    @DisplayName("활동 기록을 추가한다.")
    public void addActivity() {
        Activity activity = Activity.of(1L, "Removed", "JS 공부", "해야할 일", "하는 일");
        Activity addedActivity = activityRepository.save(activity);
        assertThat(activityRepository.findAll()).hasSize(1);
        assertThat(addedActivity).hasFieldOrPropertyWithValue("action", "Removed");
    }

}