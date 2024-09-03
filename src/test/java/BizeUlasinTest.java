import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class BizeUlasinTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        try {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
            driver = new ChromeDriver(options);
            wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Bekleme süresini artırdık
            driver.get("https://devweb.doggoapp.com/");
            System.out.println("Driver başarıyla başlatıldı ve sayfa yüklendi.");


            wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            System.out.println("Sayfa tam olarak yüklendi.");
        } catch (Exception e) {
            System.out.println("Driver başlatılırken hata oluştu: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @Test
    public void testBizeUlasin() {
        try {
            System.out.println("Test başlıyor...");

            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
            System.out.println("Sayfa aşağı kaydırıldı.");

            try {
                WebElement blocker = driver.findElement(By.cssSelector(".fixed.bottom-0.left-0.right-0"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].remove();", blocker);
                System.out.println("Engelleyen element kaldırıldı.");
            } catch (NoSuchElementException e) {
                System.out.println("Engelleyen element bulunamadı.");
            }

            WebElement input_element = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("fullName")));
            input_element.sendKeys("John Doe");
            System.out.println("İsim girildi.");

            WebElement dropdownGonderen = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-slot='innerWrapper']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownGonderen);
            System.out.println("Dropdown tıklandı.");

            dropdownGonderen = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Müşteri']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownGonderen);
            System.out.println("'Müşteri' seçeneği seçildi.");

            WebElement dropdownKonu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/section[8]/div/div[2]/form/div/div[3]/div[2]/button/div")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownKonu);
            System.out.println("Konu dropdown'ı tıklandı.");

            dropdownKonu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Gezdirme Hizmeti Hakkında']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownKonu);
            System.out.println("'Gezdirme Hizmeti Hakkında' seçeneği seçildi.");

            WebElement email = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/main/div/div/section[8]/div/div[2]/form/div/div[4]/div[1]/div/div/div/input")));
            email.sendKeys("asd@gmail.com");
            System.out.println("E-posta adresi girildi.");

            WebElement input_phone = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("phone")));
            input_phone.sendKeys("5397240935");
            System.out.println("Telefon numarası girildi.");

            WebElement input_message = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("message")));
            input_message.sendKeys("deneme mesajı");
            System.out.println("Mesaj girildi.");

            WebElement dropdownProvince = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/main/div/div/section[8]/div/div[2]/form/div/div[5]/div[2]/button/div")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownProvince);
            System.out.println("İl dropdown'ı tıklandı.");

            dropdownProvince = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='İstanbul']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownProvince);
            System.out.println("'İstanbul' seçeneği seçildi.");


            System.out.println("Son assertion öncesi kontroller başlıyor...");

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-slot='innerWrapper']//span[@data-slot='value']")));
            System.out.println("Element bulundu, değeri kontrol ediliyor...");

            WebElement selectedValue = driver.findElement(By.xpath("//div[@data-slot='innerWrapper']//span[@data-slot='value']"));

            if (selectedValue != null) {
                String actualText = selectedValue.getText();
                System.out.println("Seçilen gerçek değer: " + actualText);


                try {
                    Assertions.assertEquals("Müşteri", actualText, "Beklenen 'Müşterii' değeri, ancak '" + actualText + "' bulundu.");
                    System.out.println("Assertion başarılı!");
                } catch (AssertionError e) {
                    System.out.println("Assertion başarısız: " + e.getMessage());
                    throw e;
                }
            } else {
                System.out.println("selectedValue null!");
                throw new RuntimeException("selectedValue is null");
            }

            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit'], input[type='submit']")));
            submitButton.click();
            System.out.println("Gönder butonu tıklandı.");

        } catch (TimeoutException e) {
            System.out.println("Zaman aşımı hatası: " + e.getMessage());
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            System.out.println("Beklenmeyen hata oluştu: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Driver kapatıldı.");
        }
    }
}