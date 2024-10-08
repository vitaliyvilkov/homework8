package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class BankLoginTest {
    LoginPage loginPage;

    @BeforeEach
    void setUp() {
        loginPage = open("http://localhost:9999", LoginPage.class);
    }

    @AfterEach
    void tearDown() {
        SQLHelper.cleanAuthCode();
    }

    @AfterAll
    static void tearDownAll() {
        SQLHelper.cleanDatabase();
    }

    @Test
    void shouldSuccessFullLogin() {
        var authInfo = DataHelper.getUser();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validCode(verificationCode);
    }

    @Test
    void invalidUser() {
        var authInfo = DataHelper.getRandomUser();
        loginPage.invalidUser(authInfo);
        loginPage.errorNotification("Ошибка! Неверно указан логин или пароль");
    }

    @Test
    void invalidAuthCode() {
        var authInfo = DataHelper.getUser();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.randomVerify();
        verificationPage.verify(verificationCode.getCode());
        verificationPage.errorMessage("Ошибка! Неверно указан код! Попробуйте ещё раз.");
    }
}