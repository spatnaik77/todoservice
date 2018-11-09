package com.sidd.todoservice;

import com.sidd.todo.dao.TodoItemInMemoryRepository;
import com.sidd.todo.entity.TodoItem;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;

public class TodoItemInMemoryserviceTest {

    static TodoItemInMemoryRepository itemInMemoryRepository;

    String id1 = "item1";
    String id2 = "item2";
    String id3 = "item3";

    @BeforeClass
    public static void init()
    {
        itemInMemoryRepository = new TodoItemInMemoryRepository();
    }
    @Test
    public void testCreateItem()
    {
        TodoItem item1 = new TodoItem(id1, "Send mail", "siddharth");
        TodoItem item2 = new TodoItem(id2, "Send mail", "siddharth");
        TodoItem item3 = new TodoItem(id3, "Send mail", "siddharth");

        item1 = itemInMemoryRepository.save(item1);
        item2 = itemInMemoryRepository.save(item2);
        item3 = itemInMemoryRepository.save(item3);

        Assert.assertNotNull(item1);
        Assert.assertNotNull(item1);
        Assert.assertNotNull(item1);

        itemInMemoryRepository.deleteAll();
    }
    @Test
    public void testGetItems()
    {
        TodoItem item1 = new TodoItem(id1, "Send mail", "siddharth");
        TodoItem item2 = new TodoItem(id2, "Send mail", "siddharth");
        TodoItem item3 = new TodoItem(id3, "Send mail", "siddharth");

        item1 = itemInMemoryRepository.save(item1);
        item2 = itemInMemoryRepository.save(item2);
        item3 = itemInMemoryRepository.save(item3);

        Collection<TodoItem> items  = (Collection<TodoItem>)itemInMemoryRepository.findAll();
        Assert.assertEquals(3, items.size());

        itemInMemoryRepository.deleteAll();
    }
    @Test
    public void testGetItem()
    {
        TodoItem item1 = new TodoItem(id1, "Send mail", "siddharth");
        item1 = itemInMemoryRepository.save(item1);
        Assert.assertEquals(id1, item1.getID());

        itemInMemoryRepository.deleteAll();
    }

    @Test
    public void testDeleteItem()
    {
        TodoItem item1 = new TodoItem(id1, "Send mail", "siddharth");
        item1 = itemInMemoryRepository.save(item1);
        Assert.assertEquals(id1, item1.getID());

        itemInMemoryRepository.deleteById(id1);
        Assert.assertNull(itemInMemoryRepository.findById(id1));

        itemInMemoryRepository.deleteAll();
    }
}
