package com.team13.todolist.repository;

import com.team13.todolist.model.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ActivityRepository extends CrudRepository<Activity, Long> {
    Page<Activity> findAll(Pageable pageable);
}
