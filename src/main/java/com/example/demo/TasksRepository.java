package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepository extends CrudRepository<Task, Integer> {
    List<Task> findByAccount(Account account);
}
