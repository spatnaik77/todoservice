package com.sidd.todo.dao;

import com.microsoft.azure.spring.data.cosmosdb.repository.DocumentDbRepository;
import com.sidd.todo.entity.TodoItem;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoItemCosmosRepository extends DocumentDbRepository<TodoItem, String> {
}
