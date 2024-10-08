package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private final SelenideElement codeField = $("[data-test-id='code'] input");
    private final SelenideElement button = $("[data-test-id='action-verify']");
    private final SelenideElement errorNotification = $("[data-test-id='error-notification'] .notification__content");

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    public DashBoardPage validCode(String verificationCode) {
        verify(verificationCode);
        return new DashBoardPage();
    }

    public void verify(String verificationCode) {
        codeField.setValue(verificationCode);
        button.click();
    }

    public void errorMessage(String expectedText) {
        errorNotification.shouldHave(exactText(expectedText))
                .shouldBe(visible, Duration.ofSeconds(15));
    }

}