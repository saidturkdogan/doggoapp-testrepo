import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

public class BizeUlasın {
    public static void main(String[] args) {

        WebDriver driver = new EdgeDriver();

        driver.get("https://devweb.doggoapp.com/");

        WebElement fullName = driver.findElement(By.xpath("/html/body/div/main/div/div/section[8]/div/div[2]/form/div/div[1]/div/div/div/input"));
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

        //WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit'], input[type='submit']"));
        //submitButton.click();

        //driver.quit();
    }

}
