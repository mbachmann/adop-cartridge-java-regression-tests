package springpetclinic.selenium.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import springpetclinic.selenium.utils.Configure;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


import java.util.List;


/**
 * Unit test for Spring Petclinic.
 */
public class OwnerTest extends TestCase {
    //private HtmlUnitDriver driver = new HtmlUnitDriver();

    private WebDriver driver ;
    private Configure config = new Configure();
    private static final Logger logger = LoggerFactory.getLogger(OwnerTest.class);
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public OwnerTest( String testName )  {
        super(testName);
        WebDriverManager.chromedriver().setup();
        if(config.getZapEnabled()){
        	// driver.setProxy(config.getZapIp(), config.getZapPort());
        }
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( OwnerTest.class );
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        driver = new ChromeDriver();
    }

    /**
     * Test searching for an owner that does not exist.
     */
    public void testNotFindOwner() {
        logger.info("testNotFindOwner");
        driver.get(config.getPetClinicUrl()+"/owners/find");
        wait2Seconds();
        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys(config.getOwnerLastNameNotExist());
        wait2Seconds();
        pressButton("Find Owner");
        // driver.findElement(By.xpath("//*[@id='search-owner-form']/fieldset/div[2]/button")).submit();
        assertTrue(driver.findElement(By.xpath("//*[@id='lastNameGroup']/div/span")).isDisplayed());
        // assertTrue(driver.findElement(By.xpath("//*[@id='owner.errors']")).getText().equals("has not been found"));
        wait2Seconds();
    }
    
    /**
     * Test adding owner.
     */
    public void testAddOwner() {
        logger.info("testAddOwner");
        driver.get(config.getPetClinicUrl()+"/owners/find");
        wait2Seconds();
        driver.findElement(By.xpath("/html/body/div/div/a")).click();
        driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys(config.getOwner().getFirstName());
        driver.findElement(By.xpath("//*[@id='lastName']")).sendKeys(config.getOwner().getLastName());
        driver.findElement(By.xpath("//input[@id='address']")).sendKeys(config.getOwner().getAddress());
        driver.findElement(By.xpath("//input[@id='city']")).sendKeys(config.getOwner().getAddress());
        driver.findElement(By.xpath("//input[@id='telephone']")).sendKeys(config.getOwner().getTelephone());
        wait2Seconds();
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        wait2Seconds();
        assertTrue(driver.findElement(By.xpath("/html/body/div/div/table[1]/tbody/tr[1]/td/b")).getText().equals("John Smith"));
        wait2Seconds();

    }

    private void pressButton(String buttonText)  {
        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        for (WebElement webElement : buttons) {
            if (webElement.getText().equals(buttonText)){
                webElement.click();
                assertTrue(true);
            }

        }
    }

    private void wait2Seconds () {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        driver.close();
    }
}
