package utilities;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class ReusableMethods {

    public static void scDownWithPixel(int pixel) {

        JavascriptExecutor scDown = (JavascriptExecutor) Driver.getDriver();

        scDown.executeScript("window.scrollBy(0," + pixel + ")", "");
    }

    public static void scDownToTheElement(WebElement element) {

        JavascriptExecutor scDown = (JavascriptExecutor) Driver.getDriver();

        scDown.executeScript("arguments[0].scrollIntoView();",element);
    }

    public static void scDownToTheBottomOfThePage() {

        JavascriptExecutor scDown = (JavascriptExecutor) Driver.getDriver();

        scDown.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    public static void sleep(int milliseconds) {

        try {
            Thread.sleep(milliseconds);

        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }
 }