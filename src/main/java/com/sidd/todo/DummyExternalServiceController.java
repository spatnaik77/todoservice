package com.sidd.todo;

import com.microsoft.applicationinsights.TelemetryClient;
import com.microsoft.applicationinsights.telemetry.Duration;
import com.sidd.todo.entity.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class DummyExternalServiceController
{

    private TelemetryClient telemetryClient;

    RestTemplate restTemplate;

    public DummyExternalServiceController()
    {
        restTemplate = new RestTemplate();
        telemetryClient = new TelemetryClient();
    }
    @RequestMapping(value = "/api/yahoo", method = RequestMethod.GET)
    public ResponseEntity<String> yahoo()
    {
        long startTime = -1;
        long endTime = -1;
        try
        {
            startTime = System.currentTimeMillis();
            ResponseEntity<String> response = restTemplate.getForEntity("http://www.yahoo.com", String.class);
            endTime = System.currentTimeMillis();
            telemetryClient.trackDependency("Yahoo", "http://www.yahoo.com", new Duration(endTime-startTime), true);

            return response;

        } catch (Exception e)
        {
            telemetryClient.trackException(e);
            telemetryClient.trackDependency("Yahoo", "http://www.yahoo.com", new Duration(endTime-startTime), false);
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = "/api/google", method = RequestMethod.GET)
    public ResponseEntity<String> google()
    {
        long startTime = -1;
        long endTime = -1;
        try
        {
            startTime = System.currentTimeMillis();
            ResponseEntity<String> response = restTemplate.getForEntity("http://www.google.com", String.class);
            endTime = System.currentTimeMillis();
            telemetryClient.trackDependency("Google", "http://www.google.com", new Duration(endTime-startTime), true);
            return response;

        } catch (Exception e)
        {
            telemetryClient.trackException(e);
            telemetryClient.trackDependency("Google", "http://www.google.com", new Duration(endTime-startTime), false);
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/api/exception")
    public ResponseEntity exception() throws Exception
    {
        Exception e = new RuntimeException("Some Exception");
        telemetryClient.trackException(e);
        throw e;
    }
    @GetMapping("/api/sleep/{time}")
    public String sleep(@PathVariable("time") long time) throws Exception
    {
        Thread.sleep(time);
        return "Slept for " + time + " Ms";
    }
}
