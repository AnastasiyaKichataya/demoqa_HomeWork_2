package com.demoqa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FormTests {
    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://demoqa.com";
        //  Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "400x1028";
    }

    @Test
    void assertTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Ivan");
        $("#lastName").setValue("Petrov");
        $("#userEmail").setValue("test@test.com");
        $("[for='gender-radio-1']").click();
        $("#userNumber").setValue("9998887744");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").click();
        $(".react-datepicker__year-select").selectOption("2000");
        $(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").selectOption("January");
        $(".react-datepicker__day--001").click();
        $("#subjectsInput").click();
        $("#subjectsInput").setValue("Math").pressEnter();
        $("#subjectsInput").setValue("Economics").pressEnter();
        $("#subjectsInput").setValue("Arts").pressEnter();
        $("[for='hobbies-checkbox-1']").click();
        $("[for='hobbies-checkbox-3']").click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/images/test.png"));
        $("#currentAddress").setValue("Some address: city, street, house 0");
        $("#state").click();
        $(byText("Haryana")).click();
        $("#city").click();
        $(byText("Panipat")).click();
        $("#submit").click();

        $(".modal-header").$("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".modal-body").shouldHave(text("Ivan Petrov"));
        $(".modal-body").shouldHave(text("test@test.com"));
        $(".modal-body").shouldHave(text("Male"));
        $(".modal-body").shouldHave(text("9998887744"));
        $(".modal-body").shouldHave(text("01 January,2000"));
        $(".modal-body").shouldHave(text("Maths, Economics, Arts"));
        $(".modal-body").shouldHave(text("Sports, Music"));
        $(".modal-body").shouldHave(text("test.png"));
        $(".modal-body").shouldHave(text("Some address: city, street, house 0"));
        $(".modal-body").shouldHave(text("Haryana Panipat"));
    }
}
