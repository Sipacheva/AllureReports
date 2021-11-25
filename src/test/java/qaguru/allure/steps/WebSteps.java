package qaguru.allure.steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class WebSteps {

  @Step("Открываем главную страницу")
  public void openMainPage() {
    open("https://github.com/");
  }

  @Step("Ищем репозиторий {repository}")
  public void searchForRepository(String repository) {
    $(".header-search-input").click();
    $(".header-search-input").sendKeys(repository);
    $(".header-search-input").submit();
  }

  @Step("Переходим в репозиторий {repository}")
  public void goToRepository(String repository) {
    $(linkText(repository)).click();
  }

  @Step("Открываем таб Issues")
  public void openIssueTab() {
    $(partialLinkText("Issues")).click();
  }

  @Step("Проверяем, что существует Issue c номером  {number}")
  public void shouldSeeIssueWithNumber(int number) {
    $(withText("#" + number)).should(Condition.visible);
  }
}
