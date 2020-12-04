package com.personal.myclouddrive.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(id = "logoutButton")
    private WebElement logOutButton;

    /* Notes */

    @FindBy(id = "nav-notes-tab")
    private WebElement noteTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTab;

    @FindBy(id = "add-note")
    private WebElement addNoteButton;

    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "save-note")
    private WebElement saveNote;

    @FindBy(id = "edit-note")
    private WebElement editNoteButton;

    @FindBy(id = "delete-note")
    private WebElement deleteNoteButton;

    @FindBy(id = "note-title-text")
    private WebElement noteTitleText;

    @FindBy(id = "note-description-text")
    private WebElement noteDescriptionText;

    /* Credential */

    @FindBy(id = "add-credential")
    private WebElement addCredentialButton;

    @FindBy(id = "credential-url")
    private WebElement credentialUrl;

    @FindBy(id = "credential-username")
    private WebElement credentialUsername;

    @FindBy(id = "credential-password")
    private WebElement credentialPassword;

    @FindBy(id = "save-credential")
    private WebElement saveCredential;

    @FindBy(id = "edit-credential")
    private WebElement editCredential;

    @FindBy(id = "delete-credential")
    private WebElement deleteCredential;

    @FindBy(id = "credential-url-text")
    private WebElement credentialUrlText;

    @FindBy(id = "credential-username-text")
    private WebElement credentialUsernameText;

    @FindBy(id = "credential-password-text")
    private WebElement credentialPasswordText;


    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void logOut() {
        logOutButton.click();
    }

    /* Note */

    public WebElement getNoteTab() {
        return noteTab;
    }

    public WebElement getCredentialsTab() {
        return credentialsTab;
    }

    public void openNoteTab() {
        noteTab.click();
    }

    public void openCredentialsTab() {
        credentialsTab.click();
    }

    public WebElement getAddNoteButton() {
        return addNoteButton;
    }

    public void addNoteButtonClick() {
        addNoteButton.click();
    }

    public WebElement getNoteTitle() {
        return noteTitle;
    }

    public WebElement getNoteDescription() {
        return noteDescription;
    }

    public WebElement getSaveNote() {
        return saveNote;
    }

    public WebElement getEditButtonElement() {
        return editNoteButton;
    }

    public void getEditNoteButton() {
        editNoteButton.click();
    }

    public WebElement getDeleteNoteButtonElement() {
        return deleteNoteButton;
    }

    public void getDeleteNoteButton() {
        deleteNoteButton.click();
    }

    public WebElement getNoteTitleText() {
        return noteTitleText;
    }

    public WebElement getNoteDescriptionText() {
        return noteDescriptionText;
    }

    public String getNoteTitleTextValue() {
        return noteTitleText.getText();
    }

    public String getNoteDescriptionTextValue() {
        return noteDescriptionText.getText();
    }

    public void createNote(String title, String description) {
        getNoteTitle().sendKeys(title);
        getNoteDescription().sendKeys(description);
        getSaveNote().click();
    }

    public void editNote(String title, String description) {
        getNoteTitle().clear();
        getNoteDescription().clear();
        createNote(title, description);
    }
    
    /* Credential */

    public void getAddCredentialButton() {
        addCredentialButton.click();
    }

    public WebElement getAddCredential() {
        return addCredentialButton;
    }

    public WebElement getCredentialUrl() {
        return credentialUrl;
    }

    public WebElement getCredentialUsername() {
        return credentialUsername;
    }

    public WebElement getCredentialPassword() {
        return credentialPassword;
    }

    public WebElement getSaveCredential() {
        return saveCredential;
    }

    public WebElement getEditCredentialElement() {
        return editCredential;
    }

    public WebElement getDeleteCredentialElement() {
        return deleteCredential;
    }

    public void getEditCredential() {
        editCredential.click();
    }

    public void getDeleteCredential() {
        deleteCredential.click();
    }

    public WebElement getCredentialUrlText() {
        return credentialUrlText;
    }

    public WebElement getCredentialUsernameText() {
        return credentialUsernameText;
    }

    public WebElement getCredentialPasswordText() {
        return credentialPasswordText;
    }

    public String getCredentialUrlTextValue() {
        return credentialUrlText.getText();
    }

    public String getCredentialUsernameTextValue() {
        return credentialUsernameText.getText();
    }

    public String getCredentialPasswordTextValue() {
        return credentialPasswordText.getText();
    }

    public void createCredential(String url, String username, String password) {
        getCredentialUrl().sendKeys(url);
        getCredentialUsername().sendKeys(username);
        getCredentialPassword().sendKeys(password);
        getSaveCredential().click();
    }

    public void editCredential(String url, String username, String password) {
        getCredentialUrl().clear();
        getCredentialUsername().clear();
        getCredentialPassword().clear();
        createCredential(url, username, password);
    }
}
