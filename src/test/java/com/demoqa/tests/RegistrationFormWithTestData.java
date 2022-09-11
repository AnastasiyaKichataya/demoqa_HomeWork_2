package com.demoqa.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.demoqa.pages.RegistrationFormPage;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static java.lang.String.format;
import static utils.RandomUtils.getRandomEmail;
import static utils.RandomUtils.getRandomPhone;

public class RegistrationFormWithTestData extends TestBase {

    Faker faker = new Faker();
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            gender = "Male",
            phoneNumber = faker.phoneNumber().subscriberNumber(10),
            day = "01",
            month = "January",
            year = "2000",
            fullExpectedDate = format("%s %s,%s", day, month, year),
            subject = "Maths",
            hobby = "Sports",
            someAddress = faker.address().fullAddress(),
            testCountry = "Haryana",
            testCity = "Karnal";

    @Test
    void assertTest() {

        registrationFormPage
                .openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setNumber(phoneNumber)
                .setBirthDate(day, month, year)
                .chooseSubjects(subject)
                .chooseHobbies(hobby)
                .uploadImage("images/test.png")
                .inputAddress(someAddress)
                .chooseCountry(testCountry)
                .chooseCity(testCity)
                .clickButton();


        registrationFormPage.checkResultsTableVisible()
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", email)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phoneNumber)
                .checkResult("Date of Birth", fullExpectedDate)
                .checkResult("Subjects", subject)
                .checkResult("Hobbies", hobby)
                .checkResult("Picture", "test.png")
                .checkResult("Address", someAddress)
                .checkResult("State and City", testCountry + " " + testCity);
    }
}
