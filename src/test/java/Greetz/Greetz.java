package Greetz;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class Greetz {
    private WebDriver driver;
    @BeforeClass
    public void Login() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver",
                "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.manage().window().maximize();
        driver.get("https://www.greetz.nl/auth/login");

        By loginEmail = By.cssSelector("[data-qa-ref=login-email]");
        WebElement loginEmailElement = wait.until(ExpectedConditions.visibilityOfElementLocated(loginEmail));
        loginEmailElement.sendKeys("musculvacho@gmail.com");

        By loginPassword = By.cssSelector("[data-qa-ref=login-password]");
        WebElement loginPasswordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(loginPassword));
        loginPasswordElement.sendKeys("123456");

        By loginClick = By.cssSelector(".b-button--label");
        WebElement loginClickElement = wait.until(ExpectedConditions.elementToBeClickable(loginClick));
        loginClickElement.click();


    }
@Test
public void Testing()
{

    driver.get("https://www.greetz.nl/bloemen/gemengde-boeketten");
    WebDriverWait wait = new WebDriverWait(driver, 20);
    By productsElems = By.xpath("//*[@class='b-products-grid__item']");
    wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(productsElems, 20));
    List<WebElement> productsElements = driver.findElements(productsElems);
    WebElement element = getRandomElement(productsElements);
    element.click();

    By starIcon = By.cssSelector(".b-favourite");
    WebElement favoriteStar = element.findElement(starIcon);
    WebElement favoriteStarPage = wait.until(ExpectedConditions.elementToBeClickable(favoriteStar));
    favoriteStarPage.click();

    By productName = By.cssSelector(".b-products-grid__item-title");
    WebElement productNameFront = element.findElement(productName);
    WebElement prodNameFront = wait.until(ExpectedConditions.visibilityOf(productNameFront));
    String prodName = prodNameFront.getText();
    System.out.println(prodName);

}
    public WebElement getRandomElement(List<WebElement> list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }
  @AfterClass
   public void logOut() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        By login = By.cssSelector("[data-qa-ref=profile-icon]");
        WebElement loginElement = wait.until(ExpectedConditions.elementToBeClickable(login));
        loginElement.click();
        By loginPage = By.xpath("//*[@data-qa-ref='hamburger-items']//span[text()='Uitloggen']");
        WebElement loginPageElement = wait.until(ExpectedConditions.elementToBeClickable(loginPage));
        loginPageElement.click();
        driver.quit();
    }



  }
