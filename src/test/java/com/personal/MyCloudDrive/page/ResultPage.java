package com.personal.MyCloudDrive.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {

    @FindBy(className = "result-msg")
    private WebElement resultMsg;

    @FindBy(className = "next-button")
    private WebElement nextButton;

    public ResultPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public WebElement getResultMsg() {
        return resultMsg;
    }

    public WebElement getNextButton() {
        return nextButton;
    }

    public String getResultMessage() {
        return getResultMsg().getText();
    }

    public void nextButtonClick() {
        getNextButton().click();
    }
}
