package qaguru.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.LogEventListener;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.*;

public class SelenideTest {

  @Test
  public void testGithub() {
    SelenideLogger.addListener("allure", new AllureSelenide());

    open("https://github.com/");

    $(".header-search-input").click();
    $(".header-search-input").sendKeys("eroshenkoam/allure-example");
    $(".header-search-input").submit();

    $(linkText("eroshenkoam/allure-example")).click();
    $(partialLinkText("Issues")).click();
    $(withText("#68")).should(Condition.visible);

  }
}
