import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Login {
    ChromeDriver driver;

    @BeforeMethod
    void Setup(){
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();

    }
   // ------------------------------------------------------------------------------------------------

    @Test(dataProvider = "Data")
    void Registration_TC_1(String x , String y)
    {
        driver.findElement(By.id("user-name")).sendKeys(x);
        driver.findElement(By.id("password")).sendKeys(y);
        driver.findElement(By.id("login-button")).click();
        boolean codition = ((x.equals("standard_user")) ||
                (x.equals("locked_out_user")) ||
                (x.equals("problem_user")) ||
                (x.equals("performance_glitch_user"))
                || (x.equals("visual_user")) ||
                (x.equals("error_user")))&&(y.equals("secret_sauce"));
        if (codition)
        {
            Assert.assertTrue(driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).isDisplayed());

        } else {
            Assert.assertTrue(driver.findElement(By.className("error-button")).isDisplayed());
        }
    }

    @DataProvider(name = "Data")
    String[][] giveData()
    {
        String [][] Data=
        {
        {"standard_user","secret_sauce"}, // positive scenarios
        {"locked_out_user","secret_sauce"},
        {"problem_user","secret_sauce"},
        {"performance_glitch_user","secret_sauce"},
        {"error_user","secret_sauce"},
        {"visual_user","secret_sauce"},
        {"Ahmed","12345"},  // negative scenario
                {"",""}

        };
return Data;
    }



    //  ------------------------------------------------------------------------------

    @AfterMethod
    void Finish() throws InterruptedException {
Thread.sleep(3000);
driver.quit();
    }
}
