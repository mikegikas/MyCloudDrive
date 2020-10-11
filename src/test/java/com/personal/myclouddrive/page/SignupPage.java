package com.personal.myclouddrive.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    private WebElement inputLastName;

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "signupButton")
    private WebElement signupButton;

    @FindBy(id = "success-msg")
    private WebElement successMsg;

    @FindBy(id = "error-msg")
    private WebElement errorMsg;

    public WebElement getInputFirstName() {
        return inputFirstName;
    }

    public WebElement getInputLastName() {
        return inputLastName;
    }

    public WebElement getInputUsername() {
        return inputUsername;
    }

    public WebElement getInputPassword() {
        return inputPassword;
    }

    public WebElement getSignupButton() {
        return signupButton;
    }

    public void signupButtonclick() {
        signupButton.click();
    }

    public WebElement getSuccessMsg() {
        return successMsg;
    }

    public WebElement getErrorMsg() {
        return errorMsg;
    }

    public SignupPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void register(String firstname, String lastname, String username, String password) {
        getInputFirstName().sendKeys(firstname);
        getInputLastName().sendKeys(lastname);
        getInputUsername().sendKeys(username);
        getInputPassword().sendKeys(password);
        signupButtonclick();
    }
}
