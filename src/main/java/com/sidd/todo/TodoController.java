package com.sidd.todo;

import com.sidd.todo.dao.TodoItemCosmosRepository;
import com.sidd.todo.dao.TodoItemInMemoryRepository;
import com.sidd.todo.entity.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class TodoController {

    //@Autowired
    //private TodoItemCosmosRepository todoItemRepository;

    @Autowired
    private TodoItemInMemoryRepository todoItemRepository;


    public TodoController()
    {

    }
    @GetMapping("/")
    public String hello(){

        TodoItem item1 = new TodoItem("item1", "item1-desc", "sidd");
        todoItemRepository.save(item1);
        return "Todo V1 service is up and running \n";
    }

    @RequestMapping(value = "/api/todolist/{index}",
            method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getTodoItem(@PathVariable("index") String index) {
        try {
            return new ResponseEntity<TodoItem>(todoItemRepository.findById(index).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(index + " not found", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * HTTP GET ALL
     */
    @RequestMapping(value = "/api/todolist", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getAllTodoItems() {
        try {
            return new ResponseEntity<>(todoItemRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Nothing found", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * HTTP POST NEW ONE
     */
    @RequestMapping(value = "/api/todolist", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TodoItem> addNewTodoItem(@RequestBody TodoItem item)
    {
        try
        {
            if(item.getID() == null)
            {
                item.setID(UUID.randomUUID().toString());
            }
            todoItemRepository.save(item);
            return new ResponseEntity<TodoItem>(item, HttpStatus.CREATED);
        } catch (Exception e)
        {
            throw e;
        }
    }

    /**
     * HTTP PUT UPDATE
     */
    @RequestMapping(value = "/api/todolist", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateTodoItem(@RequestBody TodoItem item) {
        try {
            todoItemRepository.deleteById(item.getID());
            todoItemRepository.save(item);
            return new ResponseEntity<String>("Entity updated", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Entity updating failed", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * HTTP DELETE
     */
    @RequestMapping(value = "/api/todolist/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteTodoItem(@PathVariable("id") String id) {
        try {
            todoItemRepository.deleteById(id);
            return new ResponseEntity<String>("Entity deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Entity deletion failed", HttpStatus.NOT_FOUND);
        }

    }



}
