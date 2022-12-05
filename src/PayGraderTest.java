import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PayGraderTest {
    private final Duration timeoutLoadPageInSeconds = Duration.ofSeconds(5);

    private final String loginUsername = "Admin";
    private final String loginPassword = "admin123";

    private final String payGradeName = "Random Name For Pay Grade";
    private final String minimumSalary = "1";
    private final String maximumSalary = "1000";

    private final WebDriver driver = new ChromeDriver();
    private final WebDriverWait wait = new WebDriverWait(driver, timeoutLoadPageInSeconds);

    public PayGraderTest() {
        driver.manage().window().maximize();
    }

    public void LoginPage() {
        driver.get("https://opensource-demo.orangehrmlive.com/");
        String stringXpath = "//input[@name=\"username\"]";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(stringXpath))).sendKeys(loginUsername);
        driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys(loginPassword);
        driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
    }

    @SuppressWarnings("ReassignedVariable")
    public void MainPage() {
        String stringXpath = "//span[text()=\"Admin\"]";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(stringXpath))).click();

        stringXpath = "//span[contains(text(),\"Job\")]";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(stringXpath))).click();
        driver.findElement(By.xpath("//a[contains(text(),\"Pay Grades\")]")).click();

        stringXpath = "//button[contains(.,\"Add\")]";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(stringXpath))).click();
    }

    @SuppressWarnings("ReassignedVariable")
    public void PayGradePage() throws InterruptedException {
        String stringXpath = "//input[@class=\"oxd-input oxd-input--active\" and not(@placeholder)]";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(stringXpath))).sendKeys(payGradeName);
        driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();

        stringXpath = "//button[contains(., \"Add\")]";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(stringXpath))).click();
        WebElement search_element = driver.findElement(By.xpath("//div[@class=\"oxd-select-text-input\"]"));
        search_element.click();
        stringXpath = "//span[contains(text(),\"USD - United States Dollar\")]";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(stringXpath))).click();

        stringXpath = "//input[@class=\"oxd-input oxd-input--active\"]";
        List<WebElement> salariesInputs = driver.findElements(By.xpath(stringXpath));
        salariesInputs.get(2).sendKeys(minimumSalary);
        salariesInputs.get(3).sendKeys(maximumSalary);

        driver.findElements(By.xpath("//button[contains(.,\"Save\")]")).get(1).click();
        driver.findElement(By.xpath("//button[contains(.,\"Cancel\")]")).click();
    }

    public Boolean checkRow() {
        try {
            String stringXpath = String.format("//div[contains(.,\"%s\")]", payGradeName);
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(stringXpath))).click();
        } catch (TimeoutException e) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @SuppressWarnings("ReassignedVariable")
    public void removeRow() {
        String stringXpath = "//div[@class=\"oxd-table-row oxd-table-row--with-border\" and " +
                String.format("./div/div[contains(text(), \"%s\")]]//i[@class=\"oxd-icon bi-trash\"]", payGradeName);

        driver.findElement(By.xpath(stringXpath)).click();
        stringXpath = "//button[contains(., \"Yes, Delete\")]";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(stringXpath))).click();
        driver.close();
    }
}
