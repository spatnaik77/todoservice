package com.sidd.todoservice;

import com.sidd.todo.dao.TodoItemInMemoryRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class BrowserStackTest {

    public static final String USERNAME = "siddharthpatnaik1";
    public static final String AUTOMATE_KEY = "gPSwmUrHKZof8bxiSuDr";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    WebDriver driver = null;

    @BeforeClass
    public static void init()
    {

    }
    @Test
    public void test() throws Exception
    {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "android");
        caps.setCapability("device", "Samsung Galaxy S8");
        caps.setCapability("realMobile", "true");
        caps.setCapability("os_version", "7.0");
        caps.setCapability("name", "Bstack-[Java] Sample Test");

        WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
    }
}
