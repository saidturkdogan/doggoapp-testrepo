import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;



public class BizeUlasin {
    private WebDriver driver = new ChromeDriver();


    @BeforeEach
    public void setUp() {
        driver.get("https://www.doggoapp.com/");
    }

    @Test
    public void testBizeUlasin() {
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
        message.sendKeys("Deneme bu bir deneme mesajıdır dikkat almayınız");

        // Assertions
        Assertions.assertEquals("Said Türkdoğan1", fullName.getAttribute("value"));
        Assertions.assertEquals("Müşteri", dropdown_author.getFirstSelectedOption().getText());
        Assertions.assertEquals("Gezdirme Hizmeti Hakkında", dropdown_category.getFirstSelectedOption().getText());
        Assertions.assertEquals("saidcemal@turkdogan.com", email.getAttribute("value"));
        Assertions.assertEquals("90 538 314 37 45", phone.getAttribute("value"));
        Assertions.assertEquals("İstanbul", dropdown_province.getFirstSelectedOption().getText());
        Assertions.assertEquals("Deneme bu bir deneme mesajıdır dikkat almayınız", message.getAttribute("value"));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}