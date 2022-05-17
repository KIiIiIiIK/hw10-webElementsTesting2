import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.locators.RelativeLocator;

public class TestWebElements2 {
    private static WebDriver driver;

    public TestWebElements2() {
        System.setProperty("webdriver.chrome.driver", "D:\\QA\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        //options
        options.addArguments("start-maximized");
        options.setHeadless(false);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000L));
    }

    @Test //1
    public void incorrect_input_notification() throws InterruptedException {

        //GIVEN
        driver.get("http://online-sh.herokuapp.com/login");
        Thread.sleep(600L);

        //WHEN
        By emailLocator = RelativeLocator.with(By.id("exampleInputEmail1")).above(By.id("exampleInputPassword1"));
        WebElement input1 = driver.findElement(emailLocator);
        input1.sendKeys("abcabc.ua");
        Thread.sleep(600L);
        By passwordLocator = RelativeLocator.with(By.id("exampleInputPassword1")).below(By.id("exampleInputEmail1"));
        WebElement input2 = driver.findElement(passwordLocator);
        input2.sendKeys("12345");
        Thread.sleep(600L);
        driver.findElement(By.className("btn-primary")).click();
        String message = driver.findElement(By.cssSelector("#exampleInputEmail1.form-control")).getAttribute("validationMessage");
        System.out.println(message);

        //THEN
        boolean messageContent = message.contains("Адрес электронной почты должен содержать символ \"@\". В адресе \"abcabc.ua\" отсутствует символ \"@\".");
        Assertions.assertTrue(messageContent);
    }

    @Test //2
    public void checkbox_test() throws InterruptedException {

        //GIVEN
        driver.get("https://mdbootstrap.com/docs/standard/forms/checkbox/");
        Thread.sleep(600L);

        //WHEN
        WebElement checkbox = driver.findElement(By.id("flexCheckDefault"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", checkbox);
        Thread.sleep(1000L);

        //THEN
        Assertions.assertTrue(checkbox.isSelected());
    }

    @Test //3
    public void radio_button_test() throws InterruptedException {

        //GIVEN
        driver.get("https://www.javascripttutorial.net/javascript-dom/javascript-radio-button/");
        WebElement frame1 = driver.findElement(By.xpath("//*[@id=\"post-85771\"]/div/div[2]/div/iframe"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", frame1);
        driver.switchTo().frame(frame1);
        Thread.sleep(600L);

        //WHEN
        WebElement radioButton = driver.findElement(By.xpath("//*[@id=\"l\"]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", radioButton);
        Thread.sleep(1000L);

        //THEN
        Assertions.assertTrue(radioButton.isSelected());
    }

    @AfterEach
    public void cleanUp() {
        driver.close();
        driver.quit();
    }
}
