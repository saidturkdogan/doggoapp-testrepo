import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class BizeUlasin {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://devweb.doggoapp.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        System.out.println("Sayfa yüklendi: " + driver.getTitle());
    }

    @Test
    public void testBizeUlasin() {
        try {

            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");


            try {
                WebElement blocker = driver.findElement(By.cssSelector(".fixed.bottom-0.left-0.right-0"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].remove();", blocker);
                System.out.println("Engelleyen element kaldırıldı.");
            } catch (NoSuchElementException e) {
                System.out.println("Engelleyen element bulunamadı.");
            }


            WebElement input_element = driver.findElement(By.name("fullName"));
            input_element.sendKeys("John Doe");


            WebElement dropdownGonderen = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-slot='innerWrapper']")));


            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownGonderen);
            System.out.println("Dropdown tıklandı.");


            dropdownGonderen = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Müşteri']")));


            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownGonderen);
            System.out.println("'Müşteri' seçeneği seçildi.");



            WebElement dropdownKonu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/section[8]/div/div[2]/form/div/div[3]/div[2]/button/div")));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownKonu);
            System.out.println("Dropdown tıklandı.");

            dropdownKonu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Gezdirme Hizmeti Hakkında']")));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownKonu);
            System.out.println("'Gezdirme Hizmeti Hakkında' seçeneği seçildi.");


            WebElement email = driver.findElement(By.xpath("/html/body/div/main/div/div/section[8]/div/div[2]/form/div/div[4]/div[1]/div/div/div/input"));
            email.sendKeys("asd@gmail.com");

            WebElement input_phone = driver.findElement(By.name("phone"));
            input_phone.sendKeys("5397240935");

            WebElement input_message = driver.findElement(By.name("message"));
            input_message.sendKeys("deneme mesajı");


            WebElement dropdownProvince = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/main/div/div/section[8]/div/div[2]/form/div/div[5]/div[2]/button/div")));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownProvince);
            System.out.println("Dropdown tıklandı.");

            dropdownProvince = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='İstanbul']")));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownProvince);
            System.out.println("'İstanbul' seçeneği seçildi.");



            WebElement selectedValue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-slot='innerWrapper']//span[@data-slot='value']")));
            Assertions.assertEquals("Müşteri", selectedValue.getText());
            System.out.println("Seçim doğrulandı: " + selectedValue.getText());


            WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit'], input[type='submit']"));
            submitButton.click();

        } catch (TimeoutException e) {
            System.out.println("Zaman aşımı hatası: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Hata oluştu: " + e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            //driver.quit();
        }
    }
}
