package com.sidd.todo.dao;

//import com.microsoft.azure.spring.data.documentdb.repository.DocumentDbRepository;
import com.sidd.todo.entity.TodoItem;
import org.springframework.stereotype.Repository;
import com.microsoft.azure.spring.data.cosmosdb.repository.DocumentDbRepository;

@Repository
public interface TodoItemCosmosRepository extends DocumentDbRepository<TodoItem, String> {




}
