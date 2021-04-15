package com.team13.todolist.repository;

import com.team13.todolist.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
