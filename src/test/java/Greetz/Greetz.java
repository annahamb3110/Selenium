package Greetz;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.thread.IThreadWorkerFactory;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public class Greetz {
    private WebDriver driver;
   private static WebDriverWait wait;

 @BeforeClass
    public void Login() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver",
                "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 30);
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
    WebDriverWait wait = new WebDriverWait(driver, 30);

    By productsElems = By.xpath("//*[@class='b-products-grid__item']");
    List<WebElement> productsElements = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(productsElems, 15));
    WebElement element = getRandomElement(productsElements);
     element.click();
    By favoriteStar = By.cssSelector(".page-detail__favorite");
    WebElement favoriteStarElement = wait.until(ExpectedConditions.elementToBeClickable(favoriteStar));
    favoriteStarElement.click();




    By prodName1=By.xpath("//div[@class='b-products-grid__item-title']");
    WebElement prodNameElement1=wait.until(ExpectedConditions.visibilityOfElementLocated(prodName1));
    System.out.println(prodNameElement1.getText());

    By price1=By.xpath("//*[@data-qa-ref='current-price']");
    WebElement priceElement1=wait.until(ExpectedConditions.visibilityOfElementLocated(price1));
    System.out.println(priceElement1.getText());

    WebElement productPrice1;
    try {
        productPrice1 = element.findElement(By.xpath("//*[@data-qa-ref='current-price']"));
    } catch (NoSuchElementException e) {
        productPrice1 = element.findElement(By.xpath("//*[data-qa-ref='normal-price']"));
    }
    System.out.println(productPrice1);


   By prodNameFav= By.xpath("//*[@name='productAmountForm']//h1");
     WebElement prodNameElement=wait.until(ExpectedConditions.visibilityOfElementLocated(prodNameFav));
     System.out.println(prodNameElement.getText());

    WebElement favDetailsPrice;
    try {
        favDetailsPrice = driver.findElement(By.cssSelector("[data-qa-ref=current-price]"));
    } catch (NoSuchElementException e) {
        favDetailsPrice = driver.findElement(By.cssSelector("[data-qa-ref=normal-price]"));
    }

    System.out.println(favDetailsPrice.getText());
    By price=By.xpath("//*[@data-qa-ref='normal-price']");
     WebElement priceElement=wait.until(ExpectedConditions.visibilityOfElementLocated(price));
     System.out.println(priceElement.getText());


    Assert.assertEquals(prodNameElement1, prodNameElement, "The product name does not match:") ;
    Assert.assertEquals(productPrice1,  favDetailsPrice, "The price does not match:") ;
    System.out.println("Test Succeed");


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

