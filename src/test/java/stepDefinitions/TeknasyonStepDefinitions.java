package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import pages.GooglePage;
import pages.Log4j;
import pages.teknasyonPage;
import utilities.Driver;
import utilities.ReusableMethods;
import java.util.List;
import java.util.Locale;
import org.apache.log4j.Logger;

public class TeknasyonStepDefinitions {
    final static Logger logger = Logger.getLogger(TeknasyonStepDefinitions.class);
    GooglePage googlePage = new GooglePage();
    teknasyonPage teknasyonPage = new teknasyonPage();


    @Given("kulanici {string} adresini ziyaret eder")
    public void kulaniciAdresiniZiyaretEder(String url) {
        Log4j.startLog("Teknasyon");
        Driver.getDriver().get(url);
        logger.info(url+" adresine gidiliyor");
    }

    @And("{string} adresini dogrular")
    public void adresiniDogrular(String expectedUrl) {
        String actualUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(expectedUrl));
        logger.info(expectedUrl+" adresine basariyla gidildi");


    }

    @Then("istenen {string} kelimeyi arar")
    public void istenenKelimeyiArar(String searchWord) {
        googlePage.googleSearchInputBar.sendKeys(searchWord + Keys.ENTER);
    }

    @And("istenen {string} adresinin listelendigini dogrular ve adrese tiklar")
    public void istenenAdresininListelendiginiDogrularVeAdreseTiklar(String expectedUrl) {
        googlePage.isUrlListed(expectedUrl);

    }

    @And("sitenin basliginin {string} icerdigini kontrol eder")
    public void siteninBasligininIcerdiginiKontrolEder(String expectedBaslik) {
        String actualBaslik = Driver.getDriver().getTitle();
        Assert.assertTrue(actualBaslik.contains(expectedBaslik));
       Log4j.info("Baslikta "+expectedBaslik+" oldugu dogrulandi");
    }

    @Given("istenen {string} numarali {string} linkine scroll yapar")
    public void istenenNumaraliLinkineScrollYapar(String indexNo, String link) {
        ReusableMethods.scDownToTheElement(teknasyonPage.istenenLinkiBul(link, indexNo));
    }

    @Then("istenen {string} numarali {string} butonun gorunurlugunu test eder")
    public void istenenNumaraliButonunGorunurlugunuTestEder(String indexNo, String link) {
        Assert.assertTrue(teknasyonPage.istenenLinkiBul(link, indexNo).isDisplayed());
        Log4j.info(link+" butonunun gorunurlugu dogrulandi");

    }

    @And("istenen {string} numarali {string} butonuna tiklar")
    public void istenenNumaraliButonunaTiklar(String indexNo, String butonIsmi) {
        teknasyonPage.istenenLinkiBul(butonIsmi, indexNo).click();
        Log4j.info(butonIsmi+" butonuna tiklandi");
    }

    @Then("{string} pozisyonunun bulundugunu dogrular")
    public void pozisyonununBulundugunuDogrular(String pozisyon) {
        Assert.assertTrue(teknasyonPage.isIlanlari.stream().anyMatch(x -> x.getAttribute("title").equals(pozisyon)));
       Log4j.info("ilanlar arasinda "+pozisyon+" oldugu dogrulandi");

    }

    @And("istenen {string} linkine tiklar")
    public void istenenLinkineTiklar(String link) {
        teknasyonPage.istenenLinkiBul(link);
        Log4j.info(link+" tiklandi");
    }

    @And("{string} ismiyle {string} adresyile basvuru formunu doldurur")
    public void ismiyleAdresyileBasvuruFormunuDoldurur(String isim, String email) {
          teknasyonPage.cvIsimInput.sendKeys(isim);
          teknasyonPage.cvEmail.sendKeys(email);
    }


    @And("{string} dosyasini {string} pathinden yukler")
    public void dosyasiniPathindenYukler(String dosya, String path) {
        teknasyonPage.cVYukleButonu.sendKeys(path + dosya);
    }

    @And("onay kutularini doldurur")
    public void onayKutulariniDoldurur() {

        List<WebElement> kutular = Driver.getDriver().findElements(By.xpath("//input[@type='checkbox']"));
        ReusableMethods.scDownToTheElement(kutular.get(0));
        JavascriptExecutor executor = (JavascriptExecutor) Driver.getDriver();
        for (WebElement each : kutular) {
            executor.executeScript("arguments[0].click();", each);
        }

    }


    @Then("{string} sayfasinda oldugunu dogrular")
    public void sayfasindaOldugunuDogrular(String aciklama) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        Object text = js.executeScript("return document.querySelector(\"#content > section.banner.banner-5 > div > div > div > div.b-cell.b-cell-left > div.b-title > h1\").textContent");
        Assert.assertEquals(aciklama.toLowerCase(Locale.ROOT), text.toString().replaceAll("[^\\p{L}]", "").toLowerCase(Locale.forLanguageTag("tr")));
    }

    @Then("sayfada reCAPTCHA oldugunu dogrular")
    public void sayfadaReCAPTCHAOldugunuDogrular() {
        JavascriptExecutor jsExecutor;
        jsExecutor = (JavascriptExecutor) Driver.getDriver();
        String url= jsExecutor.executeScript("return document.querySelector(\"#recaptcha > div > div > iframe\").src").toString();
        Driver.getDriver().get(url);
        String actualTitle=Driver.getDriver().getTitle();
        String expectedTitle="reCAPTCHA";
        Assert.assertEquals(expectedTitle,actualTitle);
        Log4j.info("Sayfada "+expectedTitle+"oldugu dogrulandi");
        Driver.getDriver().navigate().back();
        Driver.getDriver().navigate().refresh();
    }

    @Then("hata mesajlarinin geldigini dogrular")
    public void hataMesajlarininGeldiginiDogrular() {
        teknasyonPage.cvIsimInput.sendKeys(Keys.ENTER);
        JavascriptExecutor jsExecutor;
        jsExecutor = (JavascriptExecutor) Driver.getDriver();
        String errorMessage=jsExecutor.executeScript("return document.querySelector(\"#jobEmail-error\").textContent").toString();
        Assert.assertFalse(errorMessage.isBlank());
        Log4j.info(errorMessage+" hata mesajinin geldigi dogrulandi");
        ReusableMethods.sleep(2000);




    }

    @Then("hata mesajlarinin gittigini dogrular")
    public void hataMesajlarininGittiginiDogrular() {

        JavascriptExecutor jsExecutor;
        jsExecutor = (JavascriptExecutor) Driver.getDriver();
        String errorMessage=jsExecutor.executeScript("return document.querySelector(\"#jobLet-error\").textContent").toString();
        Assert.assertTrue(errorMessage.isBlank());
        Log4j.info(errorMessage+" hata mesajinin gittigi dogrulandi");
    }

    @And("Basvur butonuna tiklar")
    public void basvurButonunaTiklar() {
        WebElement basvurButonu=Driver.getDriver().findElement(By.xpath("//button[@class='button button-blue']"));
        JavascriptExecutor jsExecutor;
        jsExecutor = (JavascriptExecutor) Driver.getDriver();
        jsExecutor.executeScript("arguments[0].click();", basvurButonu);
    }


}