package TEST;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class Selenium {

    private WebDriver driver;
    private String searchTerm = "SelenIum";


    @Test
    public void Testing()throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/");
        driver.manage().window().maximize();
        Thread.sleep(3000);

        WebElement downloads = driver.findElement(By.xpath("//a[contains(text(),'Downloads')]"));
        downloads.click();
        Thread.sleep(3000);

        WebElement version = driver.findElement(By.xpath("//p[contains(text(),'Latest stable version')]"));
        WebElement versionNumber = driver.findElement(By.xpath("//a[contains(text(),'3.141.59')]"));

        String Version = versionNumber.getText();
        String expectedVersion = "3.141.59";


        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(Version, expectedVersion, "This is  not right version");
        System.out.println("Soft assert Succeed");

        WebElement inputField = driver.findElement(By.cssSelector("input[name=search]"));
        inputField.sendKeys(searchTerm + Keys.ENTER);
        Thread.sleep(5000);

        String newResult = (searchTerm.toLowerCase());
        System.out.println(newResult);

        List<WebElement> searchResult = driver.findElements(By.xpath("//div[@class='gsc-resultsbox-visible']//div[@class='gs-title']"));
        for (int i = 0; i < searchResult.size(); i++) {

            softAssert.assertTrue(ignoreCase(searchResult.get(i).getText(),searchTerm),"Search result validation failed ");


            System.out.println();

        }
        System.out.println("softAssert's searchResults succeed");
        Thread.sleep(5000);
        softAssert.assertAll();
        driver.quit();
    }
    // search resultFunction

    public static boolean ignoreCase(String result1, String result2)
    {
        return result1.toLowerCase().contains(result2.toLowerCase());
    }



}
