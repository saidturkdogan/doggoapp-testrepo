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

public class WalkerBasvuruTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Bekleme süresini artırdık
        driver.get("https://devweb.doggoapp.com/walker-basvuru");
        System.out.println("Driver başarıyla başlatıldı ve sayfa yüklendi.");
    }

    private WebDriverWait getWait() {
        if (this.wait == null) {
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        }
        return this.wait;
    }

    @Test
    public void testWalkerBasvuru(){
        WebElement input_firstName = getWait().until(ExpectedConditions.presenceOfElementLocated(By.name("firstName")));
        input_firstName.sendKeys("John");
        System.out.println("İsim girildi.");

        WebElement input_lastName = getWait().until(ExpectedConditions.presenceOfElementLocated(By.name("lastName")));
        input_lastName.sendKeys("Doe");
        System.out.println("Soyad girildi.");

        WebElement input_phone = getWait().until(ExpectedConditions.presenceOfElementLocated(By.name("telephone")));
        input_phone.sendKeys("5397240935");
        System.out.println("Telefon numarası girildi.");

        WebElement email = getWait().until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
        email.sendKeys("asd@gmail.com");
        System.out.println("E-posta adresi girildi.");

        WebElement password = getWait().until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        password.sendKeys("turkdogan2525.");
        System.out.println("Şifre girildi.");

        WebElement repeatPassword = getWait().until(ExpectedConditions.presenceOfElementLocated(By.name("repeatPassword")));
        repeatPassword.sendKeys("turkdogan2525.");
        System.out.println("Şifre tekrar girildi.");

        WebElement dropdownProvince = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/main/div/div/section/form/div[1]/div[10]/div[2]/button/div")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownProvince);
        System.out.println("İl dropdown'ı tıklandı.");

        dropdownProvince = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='İstanbul']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownProvince);
        System.out.println("İstanbul' seçeneği seçildi.");

        WebElement dropdownProvinceILCE = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/main/div/div/section/form/div[1]/div[11]/div[2]/button/div")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownProvinceILCE);
        System.out.println("İlçe dropdown'ı tıklandı.");

        dropdownProvinceILCE = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Kadıköy']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownProvinceILCE);
        System.out.println("Kadıköy' seçeneği seçildi.");
    }
}
