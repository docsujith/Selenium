package mavenProject.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutomationPractice {
	
	WebDriver driver;

	// launching Chrome browser and loading the website
	@BeforeMethod
	public void beforeMethod() {

		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();

		driver.get("http://automationpractice.com/index.php");

		driver.manage().window().maximize();

	}

	// Clicking on Sign in link
	@Test
	public void clickLink() {

		// navigating to Elements Page
		WebElement linkelement = driver.findElement(By.className("login"));
		linkelement.click();
		WebElement pageHeading = driver.findElement(By.className("navigation_page"));
		String headingText = pageHeading.getText();
		System.out.println("Page heading: " + headingText);
		Assert.assertEquals(headingText, "Authentication");

	}

	// Testing email input box using valid email
	@Test
	public void emailBoxTest() {

		// navigating to Elements Page
		WebElement linkelement = driver.findElement(By.className("login"));
		linkelement.click();
		// Validating email input box
		WebElement emailBox = driver.findElement(By.id("email_create"));
		emailBox.sendKeys("double64bit@gmail.com");
		WebElement createButton = driver.findElement(By.name("SubmitCreate"));
		createButton.click();
		WebElement pageHeading = driver.findElement(By.cssSelector("h1[class='page-heading']"));
		String headingText = pageHeading.getText();
		System.out.println("Page heading: " + headingText);
		Assert.assertEquals(headingText, "CREATE AN ACCOUNT");
	}

	// Testing email input box using invalid email-blank text
	@Test
	public void emailBoxInvalidTest() {

		// navigating to Elements Page
		WebElement linkelement = driver.findElement(By.className("login"));
		linkelement.click();
		// Validating email input box-no text
		WebElement emailBox = driver.findElement(By.id("email_create"));
		emailBox.sendKeys("");
		// Clicking "Create an account" button
		WebElement createButton = driver.findElement(By.name("SubmitCreate"));
		createButton.click();
		// Validating the error message
		WebElement errorMessage1 = driver.findElement(By.id("create_account_error"));
		String messageText1 = errorMessage1.getText();
		Assert.assertEquals(messageText1, "Invalid email address.");
	}

	// Testing email input box using already registered email
	@Test
	public void emailBoxExistingTest() {

		// navigating to Elements Page
		WebElement linkelement = driver.findElement(By.className("login"));
		linkelement.click();
		// Validating email input box-input existing email
		WebElement emailBox = driver.findElement(By.id("email_create"));
		emailBox.sendKeys("123456@gmail.com");
		// Clicking "Create an account" button
		WebElement createButton = driver.findElement(By.name("SubmitCreate"));
		createButton.click();
		WebDriverWait explicitWait= new WebDriverWait(driver, 5);
		// Validating the error message
		WebElement errorMessage2 = driver.findElement(By.id("create_account_error"));
		String messageText2 = errorMessage2.getText();
		Assert.assertEquals(messageText2,
				"An account using this email address has already been registered. Please enter a valid password or request a new one.");
	}

	@Test
	public void fillForm() {
		
		// navigating to Elements Page
		WebElement linkelement = driver.findElement(By.className("login"));
		linkelement.click();
		// Validating email input box
		WebElement emailBox = driver.findElement(By.cssSelector("[id*='email_create']"));
		emailBox.sendKeys("commondramahesh@yahoo.co.in");
		WebElement createButton = driver.findElement(By.name("SubmitCreate"));
		createButton.click();
		// Filling input fields and register an account
		WebElement gender = driver.findElement(By.cssSelector("[id*='gender1']"));
		WebElement firstName = driver.findElement(By.id("customer_firstname"));
		WebElement lastName = driver.findElement(By.id("customer_lastname"));
		WebElement email = driver.findElement(By.id("email"));
		WebElement password = driver.findElement(By.id("passwd"));

		WebElement dobDate = driver.findElement(By.id("uniform-days"));
		Select dobDateSelect = new Select(driver.findElement(By.id("days")));
		WebElement dobMonth = driver.findElement(By.id("uniform-months"));
		Select dobMonthSelect = new Select(driver.findElement(By.id("months")));
		WebElement dobYear = driver.findElement(By.id("uniform-years"));
		Select dobYearSelect = new Select(driver.findElement(By.id("years")));

		WebElement addressFirstName = driver.findElement(By.id("firstname"));
		WebElement addressLastName = driver.findElement(By.id("lastname"));
		WebElement companyName = driver.findElement(By.id("company"));
		WebElement addressFirstLine = driver.findElement(By.id("address1"));
		WebElement addressSecondLine = driver.findElement(By.id("address2"));
		WebElement addresscity = driver.findElement(By.id("city"));

		WebElement clickState = driver.findElement(By.id("uniform-id_state"));
		Select selectState = new Select(driver.findElement(By.id("id_state")));

		WebElement zipCode = driver.findElement(By.id("postcode"));

		WebElement clickCountry = driver.findElement(By.id("uniform-id_country"));
		Select selectCountry = new Select(driver.findElement(By.id("id_country")));

		WebElement phone = driver.findElement(By.id("phone_mobile"));
		WebElement alias = driver.findElement(By.id("alias"));
		WebElement registerButton = driver.findElement(By.id("submitAccount"));

		gender.click();
		firstName.sendKeys("John");
		lastName.sendKeys("Smith");
		email.getText().equalsIgnoreCase("double64bit@gmail.com");
		password.sendKeys("admin");
		dobDate.click();
		dobDateSelect.selectByValue("15");
		dobMonth.click();
		dobMonthSelect.selectByValue("8");
		dobYear.click();
		dobYearSelect.selectByValue("1947");

		addressFirstName.getText().equalsIgnoreCase("John");
		addressLastName.getText().equalsIgnoreCase("Smith");
		companyName.sendKeys("Pivot");
		addressFirstLine.sendKeys("1234");
		addressSecondLine.sendKeys("Beach drive");
		addresscity.sendKeys("Annapolis");
        clickState.click();
		selectState.selectByVisibleText("Maryland");

		zipCode.sendKeys("45454");
		clickCountry.click();
		selectCountry.selectByVisibleText("United States");
        phone.sendKeys("1234567890");
		alias.clear();
		alias.sendKeys("SquareOne, Mississauga, Ontario");
        registerButton.click();

		// Validating whether my account page is loaded after registering
		WebElement accountPage = driver.findElement(By.className("navigation_page"));
		String navigationText = accountPage.getText();
		System.out.println("Page heading: " + navigationText);
		Assert.assertEquals(navigationText, "My account");
	}
    // Validating the functionality of contact us link and function
	@Test
	public void contactLinkTest() {

		// navigating to Elements Page
		WebElement contactlinkElement = driver.findElement(By.id("contact-link"));
		contactlinkElement.click();
		WebElement contactpageHeading = driver.findElement(By.cssSelector("h1[class='page-heading bottom-indent']"));
		String headingText = contactpageHeading.getText();
		System.out.println("Page heading: " + headingText);
		Assert.assertEquals(headingText, "CUSTOMER SERVICE - CONTACT US");

		WebElement subject = driver.findElement(By.id("uniform-id_contact"));
		Select selectSubject = new Select(driver.findElement(By.id("id_contact")));

		WebElement email = driver.findElement(By.id("email"));
		WebElement oredrRef = driver.findElement(By.id("id_order"));
		WebElement attachFile = driver.findElement(By.id("fileUpload"));
		WebElement message = driver.findElement(By.id("message"));
		WebElement sendButton = driver.findElement(By.id("submitMessage"));

		subject.click();
		selectSubject.selectByVisibleText("Customer service");
		email.sendKeys("double64bit@gmail.com");
		oredrRef.sendKeys("001");
		attachFile.sendKeys("C:\\Users\\dr_su\\Downloads\\temp.docx");
		message.sendKeys("Thank you");
		sendButton.click();
		WebElement findSuccessMessage = driver.findElement(By.cssSelector("p[class='alert alert-success']"));
		String sucessMessage = findSuccessMessage.getText();
		Assert.assertEquals(sucessMessage, "Your message has been successfully sent to our team.");
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();

	}

}



