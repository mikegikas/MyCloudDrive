package com.personal.myclouddrive;

import com.personal.myclouddrive.page.HomePage;
import com.personal.myclouddrive.page.LoginPage;
import com.personal.myclouddrive.page.ResultPage;
import com.personal.myclouddrive.page.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NoteTest {

    @LocalServerPort
    private int port;
    private String localhost;

    private static WebDriver webDriver;
    private HomePage homePage;
    private LoginPage loginPage;
    private SignupPage signupPage;
    private ResultPage resultPage;
    private WebDriverWait wait = new WebDriverWait(webDriver, 10);

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
    }

    @BeforeEach
    public void beforeEach() {
        localhost = "http://localhost:" + port;
        loginPage = new LoginPage(webDriver);
        signupPage = new SignupPage(webDriver);
        homePage = new HomePage(webDriver);
        resultPage = new ResultPage(webDriver);
    }

    @AfterAll
    public static void afterAll() {
        webDriver.close();
    }

    public void registerLogin() {
        webDriver.get(localhost + "/signup");

        wait.until(ExpectedConditions.elementToBeClickable(signupPage.getSignupButton()));

        signupPage.register("m", "m", "m", "m");

        wait.until(ExpectedConditions.elementToBeClickable(loginPage.getLoginButton()));

        loginPage.login("m", "m");
    }

    public void timeout() throws InterruptedException {
        Thread.sleep(1000);
    }

    @Test
    @Order(1)
    public void addNoteTest() throws InterruptedException {
        registerLogin();

        timeout();

        wait.until(ExpectedConditions.elementToBeClickable(homePage.getNoteTab()));

        homePage.openNoteTab();

        timeout();

        wait.until(ExpectedConditions.elementToBeClickable(homePage.getAddNoteButton()));

        homePage.addNoteButtonClick();

        timeout();

        wait.until(ExpectedConditions.elementToBeClickable(homePage.getSaveNote()));

        homePage.createNote("Hello World", "Trying to complete the testing");

        timeout();

        wait.until(ExpectedConditions.elementToBeClickable(resultPage.getNextButton()));

        resultPage.nextButtonClick();

        timeout();

        wait.until(ExpectedConditions.elementToBeClickable(homePage.getNoteTab()));

        homePage.openNoteTab();

        timeout();

        wait.until(ExpectedConditions.visibilityOf(homePage.getNoteTitleText()));

        assertEquals("Hello World", homePage.getNoteTitleTextValue());
        assertEquals("Trying to complete the testing", homePage.getNoteDescriptionTextValue());
    }

    @Test
    @Order(2)
    public void editNoteTest() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getEditButtonElement()));

        homePage.getEditNoteButton();

        timeout();

        wait.until(ExpectedConditions.elementToBeClickable(homePage.getSaveNote()));

        homePage.editNote("Thank you", "I hope i finished my project");

        timeout();

        wait.until(ExpectedConditions.elementToBeClickable(resultPage.getNextButton()));

        resultPage.nextButtonClick();

        timeout();

        wait.until(ExpectedConditions.elementToBeClickable(homePage.getNoteTab()));

        homePage.openNoteTab();

        timeout();

        wait.until(ExpectedConditions.visibilityOf(homePage.getNoteTitleText()));

        assertEquals("Thank you", homePage.getNoteTitleTextValue());
        assertEquals("I hope i finished my project", homePage.getNoteDescriptionTextValue());
    }

    @Test
    @Order(3)
    public void deleteNoteTest() throws InterruptedException {
        timeout();

        wait.until(ExpectedConditions.elementToBeClickable(homePage.getDeleteNoteButtonElement()));

        homePage.getDeleteNoteButton();

        timeout();

        wait.until(ExpectedConditions.visibilityOf(resultPage.getResultMsg()));

        assertEquals("were successfully deleted.", resultPage.getResultMessage());
    }
}
