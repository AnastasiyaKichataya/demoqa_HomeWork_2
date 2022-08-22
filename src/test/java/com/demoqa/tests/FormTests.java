package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.demoqa.pages.RegistrationFormPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FormTests {
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = true;
        //Configuration.browserSize = "1920x1080";
    }

    @Test
    void assertTest() {
        registrationFormPage
        .openPage()
        .setFirstName("Ivan")
        .setLastName("Petrov")
        .setEmail("test@test.com")
        .setGender("Male")
        .setNumber("9998887744")
        .setBirthDate("01","January", "2000")
        .chooseSubjects("Maths")
        .chooseSubjects("Economics")
        .chooseSubjects("Arts")
        .chooseHobbies("Sports")
        .chooseHobbies("Music")
        .downloadFile("images/test.png")
        .inputAddress("Some address: city, street, house 0")
        .chooseCountry("Haryana")
        .chooseCity("Karnal")
        .clickButton();

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
    }
}
