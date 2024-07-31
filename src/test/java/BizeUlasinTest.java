import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class BizeUlasinTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\PC\\Desktop\\chromedriver-win64\\chromedriver.exe");


        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");


        driver = new ChromeDriver(options);


        String browserVersion = (String) ((JavascriptExecutor) driver).executeScript("return navigator.userAgent;");
        System.out.println("Tarayıcı sürümü: " + browserVersion);


        driver.get("https://devweb.doggoapp.com/");
    }

    @Test
    public void testBizeUlasinFormu() {
        WebElement fullName = driver.findElement(By.name("fullName"));
        fullName.sendKeys("Said Türkdoğan1");

        WebElement dropdownAuthor = driver.findElement(By.cssSelector("select[name='author']"));
        Select dropdown_author = new Select(dropdownAuthor);
        dropdown_author.selectByVisibleText("Müşteri");

        WebElement dropdownCategory = driver.findElement(By.cssSelector("select[name='category']"));
        Select dropdown_category = new Select(dropdownCategory);
        dropdown_category.selectByVisibleText("Gezdirme Hizmeti Hakkında");

        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("saidcemal@turkdogan.com");

        WebElement phone = driver.findElement(By.name("phone"));
        phone.sendKeys("5383143745");

        WebElement dropdownProvince = driver.findElement(By.cssSelector("select[name='province']"));
        Select dropdown_province = new Select(dropdownProvince);
        dropdown_province.selectByVisibleText("İstanbul");

        WebElement message = driver.findElement(By.name("message"));
        message.sendKeys("Deneme mesajı1");

        assertEquals("Said Türkdoğan1", fullName.getAttribute("value"));
        assertEquals("Müşteri", new Select(dropdownAuthor).getFirstSelectedOption().getText());
        assertEquals("Gezdirme Hizmeti Hakkında", new Select(dropdownCategory).getFirstSelectedOption().getText());
        assertEquals("saidcemal@turkdogan.com", email.getAttribute("value"));
        assertEquals("90 538 314 37 45", phone.getAttribute("value"));
        assertEquals("İstanbul", new Select(dropdownProvince).getFirstSelectedOption().getText());
        assertEquals("Deneme mesajı1", message.getAttribute("value"));

        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit'], input[type='submit']"));
        submitButton.click();
        /*
        WebElement successMessage = driver.findElement(By.id("successMessageId"));
        assertNotNull(successMessage);
        assertEquals("Mesajınız başarıyla gönderildi", successMessage.getText());*/
    }
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
