package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_03_Exercise {
	WebDriver driver;
	By emailTextbox = By.xpath("//input[@id='mail']");
	By ageUnder18 = By.xpath("//input[@id='under_18']");
	By eduTextbox = By.xpath("//textarea[@id='edu']");
	By jobRole01 = By.xpath("//select[@id='job1']");
	By interest_development = By.xpath("//input[@id='development']");
	By slider01 = By.xpath("//input[@id='slider-1']");
	By buttonEnabled = By.xpath("//button[@id='button-enabled']");
	By passwordTextbox = By.xpath("//input[@id='password']");
	By ageDisabled = By.xpath("//input[@id='radio-disabled']");
	By biography = By.xpath("//textarea[@id='bio']");
	By jobRole02 = By.xpath("//select[@id='job2']");
	By interest_disabled = By.xpath("//input[@id='check-disbaled']");
	By slider02 = By.xpath("//input[@id='slider-2']");
	By buttonDisabled = By.xpath("//button[@id='button-disabled']");
	
  @Test
  public void TC_01_Check_Element_IsDisplayed() {
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  if(isElementDisplayed(emailTextbox)) {
		  driver.findElement(emailTextbox).sendKeys("Automation Testing");
	  }
	  if(isElementDisplayed(ageUnder18)) {
		  driver.findElement(ageUnder18).click();
	  }
	  if(isElementDisplayed(eduTextbox)) {
		  driver.findElement(eduTextbox).sendKeys("Automation Testing");
	  }
	  
	  
  }
  
  @Test
  public void TC_02_Check_Element_IsEnabled() {
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  Assert.assertTrue(isElementEnabled(emailTextbox));
	  Assert.assertTrue(isElementEnabled(ageUnder18));
	  Assert.assertTrue(isElementEnabled(eduTextbox));
	  Assert.assertTrue(isElementEnabled(jobRole01));
	  Assert.assertTrue(isElementEnabled(interest_development));
	  Assert.assertTrue(isElementEnabled(slider01));
	  Assert.assertTrue(isElementEnabled(buttonEnabled));
	  
	  Assert.assertFalse(isElementEnabled(passwordTextbox));
	  Assert.assertFalse(isElementEnabled(ageDisabled));
	  Assert.assertFalse(isElementEnabled(biography));
	  Assert.assertFalse(isElementEnabled(jobRole02));
	  Assert.assertFalse(isElementEnabled(interest_disabled));
	  Assert.assertFalse(isElementEnabled(slider02));
	  Assert.assertFalse(isElementEnabled(buttonDisabled));
	  
  }
  
  @Test
  public void TC_03_Check_Element_IsSelected() {
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  driver.findElement(ageUnder18).click();
	  driver.findElement(interest_development).click();
	  
	  if(isElementSelected(ageUnder18)) {
		  
	  }else {
		  driver.findElement(ageUnder18).click();
	  }
	  
	  if(isElementSelected(interest_development)) {
		  
	  }else {
		  driver.findElement(interest_development).click();
	  }
  }
  
  
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30,  TimeUnit.SECONDS);
	  }

  @AfterClass
  public void afterClass() {
	  //driver.quit();
  }
  
  public boolean isElementDisplayed (By by) {
	  WebElement element = driver.findElement(by);
	  if(element.isDisplayed()) {
		  System.out.println("Element " + by + "is displayed");
		  return true;
	  }else {
		  System.out.println("Element " + by + "is not displayed");
		  return false;
	  }
  }
  
  public boolean isElementEnabled(By by) {
	  WebElement element = driver.findElement(by);
	  if (element.isEnabled()) {
		  System.out.println("Element " + by + "is enabled");
		  return true;		  
	  }else {
		  System.out.println("Element " + by + "is not enabled");
		  return false;
	  }
	  
  }    
  
  public boolean isElementSelected (By by) {
	  WebElement element = driver.findElement(by);
	  if(element.isSelected()) {
		  System.out.println("Element " + by + "is selected");
		  return true;
	  }else {
		  System.out.println("Element " + by + "is not selected");
		  return false;
	  }
  }
}
