package com.personal.myclouddrive;

import com.personal.myclouddrive.page.HomePage;
import com.personal.myclouddrive.page.LoginPage;
import com.personal.myclouddrive.page.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
//import org.junit.jupiter.api.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginSignupTest {

    @LocalServerPort
    private int port;
    private String localhost;

    private static WebDriver webDriver;
    private LoginPage loginPage;
    private SignupPage signupPage;
    private HomePage homePage;

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
    }

    @AfterAll
    public static void afterAll() {
        webDriver.close();
    }

    public void timeout() throws InterruptedException {
        Thread.sleep(1000);
    }

    @Test
    @Order(1)
    public void signupLoginLogoutTest() throws InterruptedException {
        // Check unauthorized access to homepage
        webDriver.get(localhost + "/home");

        assertEquals(localhost + "/login", webDriver.getCurrentUrl());

        // Check unauthorized access to signup
        webDriver.get(localhost + "/signup");

        assertEquals(localhost + "/signup", webDriver.getCurrentUrl());

        // User register
        signupPage.register("Mike", "G", "mikeg", "pass");

        timeout();

        signupPage.signupButtonclick();

        timeout();

        assertEquals(localhost + "/login", webDriver.getCurrentUrl());

        timeout();

        // Login page check wrong password error
        loginPage.login("Mi", "pass");

        timeout();

        assertEquals("Invalid username or password", loginPage.invalidLogin());

        timeout();

        // Login success and go to homepage
        loginPage.login("mikeg", "pass");

        timeout();

        assertEquals(localhost + "/home", webDriver.getCurrentUrl());

        timeout();

        // Check if logout works
        homePage.logOut();

        timeout();

        assertEquals("Login", webDriver.getTitle());
    }
}
