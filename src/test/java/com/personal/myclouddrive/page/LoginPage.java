package com.personal.myclouddrive.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "loginButton")
    private WebElement loginButton;

    @FindBy(id = "logout-msg")
    private WebElement logoutMsg;

    @FindBy(id = "error-msg")
    private WebElement errorMsg;

    public WebElement getInputUsername() {
        return inputUsername;
    }

    public WebElement getInputPassword() {
        return inputPassword;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public WebElement getLogoutMsg() {
        return logoutMsg;
    }

    public WebElement getErrorMsg() {
        return errorMsg;
    }

    public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void login(String username, String password) {
        getInputUsername().sendKeys(username);
        getInputPassword().sendKeys(password);
        getLoginButton().click();
    }

    public String invalidLogin() {
        return getErrorMsg().getText();
    }
}
