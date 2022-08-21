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
        .setBirthDate("01","January", "2000");

        $("#subjectsInput").click();
        $("#subjectsInput").setValue("Math").pressEnter();
        $("#subjectsInput").setValue("Economics").pressEnter();
        $("#subjectsInput").setValue("Arts").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#hobbiesWrapper").$(byText("Music")).click();
        $("#uploadPicture").uploadFromClasspath("images/test.png");
        $("#currentAddress").setValue("Some address: city, street, house 0");
        $("#state").scrollTo().click();
        //$("#state").click();
        $(byText("Haryana")).click();
        $("#city").click();
        $(byText("Karnal")).click();
        $("#submit").click();

        registrationFormPage.checkResultsTableVisible();
        $(".modal-body").shouldHave(text("Ivan Petrov"), text("test@test.com"), text("Male"), text("9998887744"), text("01 January,2000"), text("Maths, Economics, Arts"), text("Sports, Music"), text("test.png"), text("Some address: city, street, house 0"), text("Haryana Karnal"));


    }
}
