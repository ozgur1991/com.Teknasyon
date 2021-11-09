package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import stepDefinitions.TeknasyonStepDefinitions;
import utilities.Driver;

import org.apache.log4j.Logger;

public class GooglePage {
    final static Logger logger = Logger.getLogger(GooglePage.class);
    WebDriver driver;

    public GooglePage() {

        PageFactory.initElements(Driver.getDriver(), this);

    }

    @FindBy(name = "q")
    public WebElement googleSearchInputBar;

    

public void isUrlListed(String url) {
    WebElement actualUrl= Driver.getDriver().findElement(By.xpath("//*[text()='"+ url+"']"));
   Assert.assertTrue(actualUrl.isDisplayed());
   if (actualUrl.isDisplayed()) {
      Log4j.info(url+" listelendi");
   } else
      Log4j.error(url+" listelenmedi");

   actualUrl.click();
   Log4j.info(url+" sitesine tiklandi");

}
}
