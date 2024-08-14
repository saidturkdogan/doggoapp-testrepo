import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;



public class BizeUlasin {
    private WebDriver driver;
    private WebDriverWait wait;


    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://devweb.doggoapp.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testBizeUlasin() {
        WebElement fullName = driver.findElement(By.xpath("/html/body/div/main/div/div/section[8]/div/div[2]/form/div/div[1]/div/div/div/input"));
        fullName.sendKeys("Said Türkdoğan1");

        WebElement dropdownAuthor = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/main/div/div/section[8]/div/div[2]/form/div/div[2]/div[2]/button/div/span")));
        dropdownAuthor.click();
        WebElement musteriOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Müşteri']")));
        musteriOption.click();

        WebElement dropdownCategory = driver.findElement(By.id("react-aria5603053694-:rk:"));
        Select dropdown_category = new Select(dropdownCategory);
        dropdown_category.selectByVisibleText("Gezdirme Hizmeti Hakkında");

        WebElement email = driver.findElement(By.cssSelector("select[type='email']"));
        email.sendKeys("saidcemal@turkdogan.com");

        WebElement phone = driver.findElement(By.name("phone"));
        phone.sendKeys("5383143745");

        WebElement dropdownProvince = driver.findElement(By.id("react-aria5603053694-:r1a:"));
        Select dropdown_province = new Select(dropdownProvince);
        dropdown_province.selectByVisibleText("İstanbul");

        WebElement message = driver.findElement(By.name("message"));
        message.sendKeys("Deneme bu bir deneme mesajıdır dikkat almayınız");

        // Assertions

        //Assertions.assertEquals("Said Türkdoğan1", fullName.getAttribute("value"));
        //Assertions.assertEquals("Müşteri", dropdown_author.getFirstSelectedOption().getText());
        //Assertions.assertEquals("Gezdirme Hizmeti Hakkında", dropdown_category.getFirstSelectedOption().getText());
        //Assertions.assertEquals("saidcemal@turkdogan.com", email.getAttribute("value"));
        //Assertions.assertEquals("90 538 314 37 45", phone.getAttribute("value"));
        //Assertions.assertEquals("İstanbul", dropdown_province.getFirstSelectedOption().getText());
        //Assertions.assertEquals("Deneme bu bir deneme mesajıdır dikkat almayınız", message.getAttribute("value"));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}