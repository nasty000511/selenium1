import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;



public class TestAutoRia {

    private WebDriver driver;

    @Before
    public void initialize() {
         System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        Map prefs = new HashMap();
        prefs.put("profile.default_content_settings.cookies", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.get("https://auto.ria.com/");

    }

    @After
    public void close() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException interruptedException) {

        }
          driver.quit();
    }

    @Test
    public void searchTest() {

    By carBrand = By.xpath("//*[@id=\"brandTooltipBrandAutocomplete-brand\"]/label");
    By BMWBrand = By.xpath("//*[text()='BMW']");
    By carModel = By.xpath("//input[@id=\"brandTooltipBrandAutocompleteInput-model\"]");
    By X5Model = By.xpath("//*[text()='X5']");
    By region = By.xpath("//*[@id=\"brandTooltipBrandAutocomplete-region\"]/label");
    By OdessaRegion = By.xpath("//*[text()='Одесса']");
    By yearFrom = By.id("yearFrom");
    By yearTo = By.id("yearTo");
    By search = By.xpath("//*[text()=' Поиск ']");
    By linklist = By.xpath("//a[@class='address']/parent::div");



        driver.findElement(carBrand).click();
        driver.findElement(BMWBrand).click();
        WebElement carModelElement = driver.findElement(carModel);
        carModelElement.sendKeys("X5");
        try {
            Thread.sleep(1500);
        }
        catch (InterruptedException interruptedException){
        }
        driver.findElement(X5Model).click();
        driver.findElement(region).click();
        driver.findElement(OdessaRegion).click();

        Select yearFromElement = new Select (driver.findElement(yearFrom));
        yearFromElement.selectByValue("2010");
        Select yearToElement = new Select (driver.findElement(yearTo));
        yearToElement.selectByValue("2020");
        driver.findElement(search).click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 35);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(linklist));

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0, 400)", "linkList");

        driver.findElements(linklist).get(2).click();

       // driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }
}




