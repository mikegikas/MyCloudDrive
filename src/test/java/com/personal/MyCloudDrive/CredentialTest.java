package com.personal.MyCloudDrive;

import com.personal.MyCloudDrive.page.HomePage;
import com.personal.MyCloudDrive.page.LoginPage;
import com.personal.MyCloudDrive.page.ResultPage;
import com.personal.MyCloudDrive.page.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CredentialTest {

    @LocalServerPort
    private int port;
    private String localhost;

    private static WebDriver webDriver;
    private HomePage homePage;
    private LoginPage loginPage;
    private SignupPage signupPage;
    private ResultPage resultPage;
    WebDriverWait wait = new WebDriverWait(webDriver, 10);

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
    }

    @BeforeEach
    public void beforeEach() {
        localhost = "http://localhost:" + port;
        signupPage = new SignupPage(webDriver);
        loginPage = new LoginPage(webDriver);
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
    public void addCredentialTest() throws InterruptedException {
        registerLogin();

        timeout();

        wait.until(ExpectedConditions.elementToBeClickable(homePage.getCredentialsTab()));

        homePage.openCredentialsTab();

        timeout();

        wait.until(ExpectedConditions.elementToBeClickable(homePage.getAddCredential()));

        homePage.getAddCredentialButton();

        timeout();

        wait.until(ExpectedConditions.elementToBeClickable(homePage.getSaveCredential()));

        homePage.createCredential("website.org", "admin", "admin123");

        timeout();

        wait.until(ExpectedConditions.elementToBeClickable(resultPage.getNextButton()));

        resultPage.nextButtonClick();

        timeout();

        wait.until(ExpectedConditions.elementToBeClickable(homePage.getCredentialsTab()));

        homePage.openCredentialsTab();

        timeout();

        wait.until(ExpectedConditions.visibilityOf(homePage.getCredentialUrlText()));

        assertEquals("website.org", homePage.getCredentialUrlTextValue());
        assertEquals("admin", homePage.getCredentialUsernameTextValue());
    }

    @Test
    @Order(2)
    public void editCredentialTest() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getEditCredentialElement()));

        homePage.getEditCredential();

        timeout();

        wait.until(ExpectedConditions.elementToBeClickable(homePage.getSaveCredential()));

        homePage.editCredential("website.com", "mike", "admin321");

        timeout();

        wait.until(ExpectedConditions.elementToBeClickable(resultPage.getNextButton()));

        resultPage.nextButtonClick();

        timeout();

        wait.until(ExpectedConditions.elementToBeClickable(homePage.getCredentialsTab()));

        homePage.openCredentialsTab();

        timeout();

        wait.until(ExpectedConditions.visibilityOf(homePage.getCredentialUrlText()));

        assertEquals("website.com", homePage.getCredentialUrlTextValue());
        assertEquals("mike", homePage.getCredentialUsernameTextValue());
    }

    @Test
    @Order(3)
    public void deleteCredentialTest() throws InterruptedException {
        timeout();

        wait.until(ExpectedConditions.elementToBeClickable(homePage.getDeleteCredentialElement()));

        homePage.getDeleteCredential();

        assertEquals("were successfully deleted.", resultPage.getResultMessage());
    }
}
