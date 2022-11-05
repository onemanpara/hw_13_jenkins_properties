package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Locale;
import java.util.Properties;

public class TestBase {
    Faker faker = new Faker(new Locale("ru"));
    Faker fakerEn = new Faker(new Locale("en"));

    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = fakerEn.internet().emailAddress();
    String number = faker.phoneNumber().subscriberNumber(10);
    String address = faker.address().fullAddress();
    String day = "26";
    String month = "August";
    String year = "1998";
    String gender = "Male";
    String subject = "Maths";
    String hobbie = "Sports";
    String state = "Haryana";
    String city = "Karnal";
    String imgPath = "img/test.jpg";


    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.browserVersion = System.getProperty("browserVersion");
        String remote = System.getProperty("remote");
        if (remote != null) {
            Configuration.remote = remote;
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;
        }
    }

    @AfterEach
    void addAttach() {
        Attach.addVideo();
        Attach.browserConsoleLogs();
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
    }
}
