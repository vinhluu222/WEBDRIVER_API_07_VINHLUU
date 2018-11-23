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

public class Topic_04_Exercise {
	WebDriver driver;
	private String newName, newDob, newAddress, newCity, newState, newPin, newPhone, newEmail, password;
	private String editAddress, editCity, editState, editPin, editPhone, editEmail;
	private String customerID;
	By nameByTextBox = By.xpath("//input[@name='name']");
	By dobByTextBox = By.xpath("//input[@name='dob']");
	By addressByTextBox = By.xpath("//textarea[@name='addr']");
	By cityByTextBox = By.xpath("//input[@name='city']");
	By stateByTextBox = By.xpath("//input[@name='state']");
	By pinnoByTextBox = By.xpath("//input[@name='pinno']");
	By telephonenoByTextBox = By.xpath("//input[@name='telephoneno']");
	By emailByTextBox = By.xpath("//input[@name='emailid']");
	By passwordByTextBox = By.xpath("//input[@name='password']");
	By submitButton = By.xpath("//input[@name='sub']");

	@Test
	public void TC_01_Create_New_Customer() {
		driver.get("http://demo.guru99.com/v4");
		driver.findElement(By.xpath("//input[@name='uid']")).clear();
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr161493");
		driver.findElement(By.xpath("//input[@name='password']")).clear();
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("harErAh");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		String currentUrl = driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, "http://demo.guru99.com/v4/manager/Managerhomepage.php");

		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		// New Customer
		driver.findElement(nameByTextBox).sendKeys(newName);
		driver.findElement(dobByTextBox).sendKeys(newDob);
		driver.findElement(addressByTextBox).sendKeys(newAddress);
		driver.findElement(cityByTextBox).sendKeys(newCity);
		driver.findElement(stateByTextBox).sendKeys(newState);
		driver.findElement(pinnoByTextBox).sendKeys(newPin);
		driver.findElement(telephonenoByTextBox).sendKeys(newPhone);
		driver.findElement(emailByTextBox).sendKeys(newEmail);
		driver.findElement(passwordByTextBox).sendKeys(password);
		driver.findElement(submitButton).click();

		// Get CustomerID
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		System.out.println(customerID);

		// Verify information
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), newName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), newDob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), newAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), newCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), newState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), newPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), newPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), newEmail);

	}

	@Test
	public void TC_02_Edit_Customer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();

		/* Verify Name / Address */
		Assert.assertEquals(driver.findElement(nameByTextBox).getAttribute("value"), newName);
		Assert.assertEquals(driver.findElement(addressByTextBox).getText(), newAddress);

		/* Edit Customer */
		driver.findElement(addressByTextBox).clear();
		driver.findElement(addressByTextBox).sendKeys(editAddress);
		driver.findElement(cityByTextBox).clear();
		driver.findElement(cityByTextBox).sendKeys(editCity);
		driver.findElement(stateByTextBox).clear();
		driver.findElement(stateByTextBox).sendKeys(editState);
		driver.findElement(pinnoByTextBox).clear();
		driver.findElement(pinnoByTextBox).sendKeys(editPin);
		driver.findElement(telephonenoByTextBox).clear();
		driver.findElement(telephonenoByTextBox).sendKeys(editPhone);
		driver.findElement(emailByTextBox).clear();
		driver.findElement(emailByTextBox).sendKeys(editEmail);
		driver.findElement(submitButton).click();

		/*Verify data*/
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), editAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), editPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), editEmail);
		
		
	}

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		newName = "Automation Test";
		newDob = "2000-10-01";
		newAddress = "123 Address";
		newCity = "HCM";
		newState = "Tan Phu";
		newPin = "700000";
		newPhone = "123456789";
		newEmail = "newtest" + Commons.randomEmail() + "@gmail.com";
		password = "123123";

		editAddress = "234 Address";
		editCity = "Da Lat";
		editState = "Tan Binh";
		editPin = "20000";
		editPhone = "987654321";
		editEmail = "editTest" + Commons.randomEmail() + "@gmail.com";
	}

	@AfterClass
	public void afterClass() throws InterruptedException {
		Thread.sleep(1000);
		// driver.quit();
	}

}
