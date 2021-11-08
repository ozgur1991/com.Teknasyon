package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import stepDefinitions.TeknasyonStepDefinitions;
import utilities.Driver;

import java.util.List;

public class teknasyonPage {




    WebDriver driver;

    @FindBy (css = "[class='menu-item menu-item-type-post_type menu-item-object-page menu-item-128'] [href]")
    public WebElement element;

    @FindBy(className = "jlb-link")
    public List<WebElement> isIlanlari;



    @FindBy(id="jobNameSurname")
    public WebElement cvIsimInput;

    @FindBy(id="jobEmail")
    public WebElement cvEmail;

    @FindBy(id= "jobFile")
    public WebElement cVYukleButonu;

    public teknasyonPage() {

        PageFactory.initElements(Driver.getDriver(),this);

    }

    public WebElement istenenLinkiBul(String linkAdi, String indexNo) {


        return Driver.getDriver().findElement(By.xpath("(//*[text()='"+linkAdi+"'])["+indexNo+"]"));
    }
    public void istenenLinkiBul(String linkAdi) {
        WebElement element=Driver.getDriver().findElement(By.xpath("//a[@title='"+linkAdi+"']"));
        JavascriptExecutor js=(JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].click();", element);

    }







}
