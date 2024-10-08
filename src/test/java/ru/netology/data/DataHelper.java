package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;


public class DataHelper {
    private static final Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static AuthInfo getUser() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static String getRandomLogin() {
        return new Faker().name().username();
    }

    public static String getRandomPassword() {
        return new Faker().internet().password();
    }

    public static AuthInfo getRandomUser() {
        return new AuthInfo(getRandomLogin(), getRandomPassword());
    }

    public static VerificationCode randomVerify() {
        return new VerificationCode(Faker.instance().numerify("#####"));
    }
}