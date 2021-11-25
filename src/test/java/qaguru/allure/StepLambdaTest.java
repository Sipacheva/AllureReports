package qaguru.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class StepLambdaTest {

  AllureLifecycle lifecycle = Allure.getLifecycle();

  private static final String REPOSITORY = "eroshenkoam/allure-example";
  private static final Integer ISSUE_NUMBER = 68;

  @Test
  @Owner("SipachevaOP")
  public void testGithub() {
    step("Открываем главную страницу", () -> {
              open("https://github.com/");
            });
    step("Ищем репозиторий " + REPOSITORY, () -> {
      $(".header-search-input").click();
      $(".header-search-input").sendKeys(REPOSITORY);
      $(".header-search-input").submit();
      lifecycle.addAttachment("Screenshot", "image/png", "png", takeScreenshot());
    });
    step("Переходим в репозиторий " + REPOSITORY, () -> {
      $(linkText(REPOSITORY)).click();
    });
    step("Открываем таб Issues", () -> {
      $(partialLinkText("Issues")).click();
    });
    step("Проверяем, что существует Issue c номером " + ISSUE_NUMBER, () -> {
      $(withText("#" + ISSUE_NUMBER)).should(Condition.visible);
    });
    }

  private byte[] takeScreenshot() {
    final WebDriver driver = WebDriverRunner.getWebDriver();
    return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
  }
}
