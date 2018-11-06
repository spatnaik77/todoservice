package com.sidd.todo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

    public TodoController()
    {

    }

    @GetMapping("/")
    public String hello(){
        return "Todo service is up and running\n";
    }

}
