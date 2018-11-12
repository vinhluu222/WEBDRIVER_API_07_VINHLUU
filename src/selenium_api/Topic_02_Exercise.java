package selenium_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Exercise {
	WebDriver driver;
  @Test
  public void TC_01_Check_Url_And_Title() {
	  driver.get("http://live.guru99.com");
	  String homepageTitle =  driver.getTitle();
	  Assert.assertEquals(homepageTitle, "Home page");
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	  driver.navigate().back();
	  String loginpageUrl = driver.getCurrentUrl();
	  Assert.assertTrue(loginpageUrl.equals("http://live.guru99.com/index.php/customer/account/login/"));
	  driver.navigate().forward();
	  String createAnAccountUrl = driver.getCurrentUrl();
	  Assert.assertTrue(createAnAccountUrl.equals("http://live.guru99.com/index.php/customer/account/create/"));
  }
  
  
  @Test
  public void TC_02_Login_Empty() {
	  driver.get("http://live.guru99.com");
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  driver.findElement(By.name("login[username]")).clear();
	  driver.findElement(By.name("login[password]")).clear();
	  driver.findElement(By.id("send2")).click();
	  String errMsgEmailEmpty = driver.findElement(By.id("advice-required-entry-email")).getText();
	  Assert.assertEquals(errMsgEmailEmpty,"This is a required field.");
	  String errMsgPassEmpty = driver.findElement(By.id("advice-required-entry-pass")).getText();
	  Assert.assertEquals(errMsgPassEmpty,"This is a required field.");
  }
	
  @Test
  public void TC_03_Login_With_Email_Invalid() {
	  driver.get("http://live.guru99.com");
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  driver.findElement(By.id("email")).clear();
	  driver.findElement(By.id("email")).sendKeys("123434234@12312.123123");
	  driver.findElement(By.id("send2")).click();
	  String errMsgEmailInvalid = driver.findElement(By.id("advice-validate-email-email")).getText();
	  Assert.assertEquals(errMsgEmailInvalid,"Please enter a valid email address. For example johndoe@domain.com.");
  }
	  
  @Test
  public void TC_04_Login_WithPPassword_Less_Than_6_chars() {
	  driver.get("http://live.guru99.com");
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  driver.findElement(By.id("email")).clear();
	  driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
	  driver.findElement(By.id("pass")).sendKeys("123");
	  driver.findElement(By.id("send2")).click();
	  String errMsgPassInvalid = driver.findElement(By.id("advice-validate-password-pass")).getText();
	  Assert.assertEquals(errMsgPassInvalid,"Please enter 6 or more characters without leading or trailing spaces.");
  }
  
  @Test
  public void TC_05_Login_With_Password_Incorrect() {
	  driver.get("http://live.guru99.com");
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  driver.findElement(By.id("email")).clear();
	  driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
	  driver.findElement(By.id("pass")).clear();
	  driver.findElement(By.id("pass")).sendKeys("123123123");
	  driver.findElement(By.id("send2")).click();
	  String errMsgPassIncorrect = driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).getText();
	  Assert.assertEquals(errMsgPassIncorrect,"Invalid login or password.");
	  
  }
  
  @Test
  public void TC_06_Create_An_Account() {
	  String firstname = "Selenium", lastname = "07", email = "seleniumonline" + randomEmail() + "@gmail.com", password = "123456";
	  driver.get("http://live.guru99.com");
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	  
	  driver.findElement(By.id("firstname")).sendKeys(firstname);
	  driver.findElement(By.id("lastname")).sendKeys(lastname);
	  driver.findElement(By.id("email_address")).sendKeys(email);
	  driver.findElement(By.id("password")).sendKeys(password);
	  driver.findElement(By.id("confirmation")).sendKeys(password);
	  
	  driver.findElement(By.xpath("//button[@title='Register']")).click();
	  Assert.assertTrue(driver.findElement(By.xpath("//*[(text()='Thank you for registering with Main Website Store.')]")).isDisplayed());
	  
	  driver.findElement(By.xpath("//header//span[text()='Account']")).click();
	  driver.findElement(By.xpath("//a[@title='Log Out']")).click();
	  Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'This is demo site for')]")).isDisplayed());
	
  }
  
  
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

  public int randomEmail() {
	  Random random = new Random();
	  int number = random.nextInt(999999);
	  System.out.println("Random number " + number);
	  return number;
  }
}
