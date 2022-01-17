package mavenProject.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OrangeHR {
	WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {

		WebDriverManager.edgedriver().setup();

		driver = new EdgeDriver();

		driver.get("https://opensource-demo.orangehrmlive.com/");

		driver.manage().window().maximize();

	}

	// Forgot password functionality
	@Test
	public void forgotPasswordLink() {

		WebElement forgotLink = driver.findElement(By.linkText("Forgot your password?"));
		Assert.assertTrue(forgotLink.isEnabled());
		forgotLink.click();
	}

	// Valid username and invalid password
	@Test
	public void validUserInvalidpasswd() {

		WebElement userName = driver.findElement(By.id("txtUsername"));
		WebElement passwrd = driver.findElement(By.id("txtPassword"));
		WebElement loginButton = driver.findElement(By.id("btnLogin"));

		userName.sendKeys("Admin");
		passwrd.sendKeys("12345");
		loginButton.click();

		WebElement errorMsg = driver.findElement(By.id("spanMessage"));
		Assert.assertEquals(errorMsg.getText(), "Invalid credentials");
	}

	// Empty user name and password submission
	@Test
	public void emptyCredentials() {

		WebElement userName = driver.findElement(By.id("txtUsername"));
		WebElement passwrd = driver.findElement(By.id("txtPassword"));
		WebElement loginButton = driver.findElement(By.id("btnLogin"));

		userName.clear();
		passwrd.sendKeys("12345");
		loginButton.click();

		WebElement errorMsg = driver.findElement(By.id("spanMessage"));
		Assert.assertEquals(errorMsg.getText(), "Username cannot be empty");

		userName.sendKeys("any");
		passwrd.clear();
		loginButton.click();

		Assert.assertEquals(errorMsg.getText(), "Password cannot be empty");

		userName.clear();
		passwrd.clear();
		;
		loginButton.click();

		Assert.assertEquals(errorMsg.getText(), "Username cannot be empty");

	}

	// Invalid username and password Submission
	@Test
	public void invalidCredentials() {

		WebElement userName = driver.findElement(By.id("txtUsername"));
		WebElement passwrd = driver.findElement(By.id("txtPassword"));
		WebElement loginButton = driver.findElement(By.id("btnLogin"));

		userName.sendKeys("admin007");
		passwrd.sendKeys("12345");
		loginButton.click();

		WebElement errorMsg = driver.findElement(By.id("spanMessage"));
		Assert.assertEquals(errorMsg.getText(), "Invalid credentials");

	}

	// Valid username and valid password
	@Test
	public void validUserPasswd() {

		WebElement userName = driver.findElement(By.id("txtUsername"));
		WebElement passwrd = driver.findElement(By.id("txtPassword"));
		WebElement loginButton = driver.findElement(By.id("btnLogin"));

		userName.sendKeys("Admin");
		passwrd.sendKeys("admin123");
		loginButton.click();

		Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/index.php/dashboard");

		WebElement employeeName = driver.findElement(By.id("welcome"));
		Assert.assertEquals(employeeName.getText(), "Welcome Paul");

	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();

	}


}
