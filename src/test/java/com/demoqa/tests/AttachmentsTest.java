package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.demoqa.pages.RegistrationFormPage;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.*;

import java.util.Date;
import java.util.logging.Level;

import static com.codeborne.selenide.Selenide.webdriver;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;

public class AttachmentsTest {
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://demoqa.com";
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        Configuration.browserCapabilities.setCapability("goog:loggingPrefs", logPrefs);
        //Configuration.holdBrowserOpen = true;
        //Configuration.browserSize = "1920x1080";
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot)WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    String buildLogs(LogEntries entries) {
       String str = "";
        for (LogEntry entry : entries) {
            str += "" +
                    new Date(entry.getTimestamp()) + " "
                    + entry.getLevel() + " "
                    + entry.getMessage() + "\n"
            ;
        }

        return str;
    }

    @Attachment(value = "Console log")
    public String takeConsoleLog() {
        LogEntries logs = WebDriverRunner
                .getWebDriver()
                .manage()
                .logs()
                .get(LogType.BROWSER);

        return buildLogs(logs);
    }

    @Test
    void assertTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную старницу", () ->{
            registrationFormPage.openPage();
            attachment("Source", webdriver().driver().source());
        });
        step("Вводим имя", () ->{
            registrationFormPage.setFirstName("Ivan");
        });
        step("Вводим фамилию", () ->{
            registrationFormPage.setLastName("Petrov");
        });
        step("Вводим email", () ->{
            registrationFormPage.setEmail("test@test.com");
        });
        step("Выбираем пол", () ->{
            registrationFormPage.setGender("Male");
        });
        step("Вводим номер телефона", () ->{
            registrationFormPage.setNumber("9998887744");
        });
        step("Вводим дату рождения", () ->{
            registrationFormPage.setBirthDate("01", "January", "2000");
        });
        step("Выбираем предметы", () ->{
            registrationFormPage.chooseSubjects("Maths");
            registrationFormPage.chooseSubjects("Economics");
            registrationFormPage.chooseSubjects("Arts");
        });
        step("Выбираем хобби", () ->{
            registrationFormPage.chooseHobbies("Sports");
            registrationFormPage.chooseHobbies("Music");
        });
        step("Загружаем картинку", () ->{
            registrationFormPage.uploadImage("images/test.png");
        });
        step("Вводим адрес", () ->{
            registrationFormPage.inputAddress("Some address: city, street, house 0");
        });
        step("Выбираем страну", () ->{
            registrationFormPage.chooseCountry("Haryana");
        });
        step("Выбираем город", () ->{
            registrationFormPage.chooseCity("Karnal");
        });
        step("Нажимаем кнопку подтверждения", () ->{
            registrationFormPage .clickButton();
        });

        registrationFormPage.checkResultsTableVisible()
                .checkResult("Student Name", "Ivan Petrov")
                .checkResult("Student Email", "test@test.com")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "9998887744")
                .checkResult("Date of Birth", "01 January,2000")
                .checkResult("Subjects", "Maths, Economics, Arts")
                .checkResult("Hobbies", "Sports, Music")
                .checkResult("Picture", "test.png")
                .checkResult("Address", "Some address: city, street, house 0")
                .checkResult("State and City", "Haryana Karnal");

        takeScreenshot();
        takeConsoleLog();
    }
}
