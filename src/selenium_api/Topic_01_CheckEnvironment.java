package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_01_CheckEnvironment {
	WebDriver driver;
		
	@BeforeClass
	public void beforeClass() {
		//Chrome
		//System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		//driver = new ChromeDriver();
		
		//Firefox
		//Khởi tạo trình duyệt
		driver = new FirefoxDriver();		
				
		driver.manage().window().maximize();		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void TC_01_CheckUrlAndTitle() {
		//Steo 1 - Vao trang homepage
		driver.get("http://live.guru99.com/");
		
		//Step 2 - Kiem tra Title
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "Home page");		
		//Assert.assertTrue(homePageTitle.equals("Home Page"));
		
		//Step 3 - Click vao link My account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		//Step 4 - Click vao Create an account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		//Step 5 - Back lai trang dang nhap
		driver.navigate().back();
		
		//Step 6 - Kiểm tra url của page đăng nhập là: http://live.guru99.com/index.php/customer/account/login/
		String loginUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginUrl, "http://live.guru99.com/index.php/customer/account/login/");
		
		//Step 7 - Forward toi trang tao tai khoan
		driver.navigate().forward();
		
		//Step 8 - Kiểm tra url của page tạo tài khoản là: http://live.guru99.com/index.php/customer/account/create/
		String createAnAccountUrl = driver.getCurrentUrl();
		Assert.assertEquals(createAnAccountUrl, "http://live.guru99.com/index.php/customer/account/create/");
		
	}
	
	@Test
	public void TC_02_EmailAndPasswordEmpty() {
		
				
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
