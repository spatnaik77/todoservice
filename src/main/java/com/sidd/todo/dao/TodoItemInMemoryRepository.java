package com.sidd.todo.dao;

import com.sidd.todo.entity.TodoItem;
import org.springframework.data.repository.CrudRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TodoItemInMemoryRepository implements CrudRepository<TodoItem, String> {

    private Map<String, TodoItem> itemsMap = new HashMap<String, TodoItem>();


    @Override
    public <S extends TodoItem> S save(S s) {

        itemsMap.put(s.getID(), s);
        return s;
    }

    @Override
    public <S extends TodoItem> Iterable<S> saveAll(Iterable<S> iterable) {

        for(TodoItem item : iterable) {

            itemsMap.put(item.getID(), item);
        }
        return iterable;
    }

    @Override
    public Optional<TodoItem> findById(String s) {
        TodoItem item =  itemsMap.get(s);
        if(item != null)
            return Optional.of(item);
        else
            return null;
    }

    @Override
    public boolean existsById(String s) {
        return itemsMap.containsKey(s);
    }

    @Override
    public Iterable<TodoItem> findAll() {
        return itemsMap.values();
    }

    @Override
    public Iterable<TodoItem> findAllById(Iterable<String> iterable) {
        return null;
    }

    @Override
    public long count() {
        return itemsMap.size();
    }

    @Override
    public void deleteById(String s) {
        itemsMap.remove(s);
    }

    @Override
    public void delete(TodoItem todoItem) {

        itemsMap.remove(todoItem.getID());
    }

    @Override
    public void deleteAll(Iterable<? extends TodoItem> iterable) {
        for(TodoItem item : iterable) {

            itemsMap.remove(item.getID());
        }

    }

    @Override
    public void deleteAll() {
        itemsMap.clear();
    }
}
