package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.components.CalendarComponent;
import com.demoqa.pages.components.ResultsTableComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RegistrationFormPage {

    private CalendarComponent calendarComponent = new CalendarComponent();
    private ResultsTableComponent resultsTableComponent = new ResultsTableComponent();

    private SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmail = $("#userEmail");

    private final static String TITLE_TEXT = "Student Registration Form";


    public RegistrationFormPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text(TITLE_TEXT));
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        return this;
    }

    public RegistrationFormPage setFirstName(String name) {
        firstNameInput.setValue(name);

        return this;
    }

    public RegistrationFormPage setLastName(String name) {
        lastNameInput.setValue(name);

        return this;
    }

    public RegistrationFormPage setEmail(String email) {
        userEmail.setValue(email);

        return this;
    }

    public RegistrationFormPage setGender(String gender) {
        $("#genterWrapper").$(byText(gender)).click();

        return this;
    }

    public RegistrationFormPage setNumber(String phoneNumber){
        $("#userNumber").setValue(phoneNumber);

        return this;
    }

    public RegistrationFormPage setBirthDate(String day, String month, String year){
        $("#dateOfBirthInput").click();
        //$(".react-datepicker__year-select").click();
        calendarComponent.setDate(day,month, year);

        return this;
    }

    public RegistrationFormPage checkResultsTableVisible(){
        resultsTableComponent.checkVisible();

        return this;
    }
}
