package net.catosplace.bdd.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {
	
	private final WebDriver driver;

	@FindBy(how = How.NAME, using = "j_username")
	private WebElement j_username;
	@FindBy(name = "j_password")
	private WebElement j_password;
	
	@FindBy(name = "f")
	private WebElement loginForm;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		
		if (!"Login Page".equals(driver.getTitle())) {
			throw new IllegalStateException("This is not the login page");
		}
	}
	
	public LoginPage loginAsExpectingError(String username, String password) {
		j_username.clear();
		j_username.sendKeys(username);
		j_password.clear();
		j_password.sendKeys(password);
		loginForm.submit();
		
		return new LoginPage(driver);
	}
	
	public HomePage loginSuccessfully(String username, String password) {
		j_username.clear();
		j_username.sendKeys(username);
		j_password.clear();
		j_password.sendKeys(password);
		loginForm.submit();
		
		return new HomePage(driver);
	}
	
	public HomePage loginRoleSuccessfully(String role) {
		j_username.clear();
		j_username.sendKeys("admin");
		j_password.clear();
		j_password.sendKeys("password");
		loginForm.submit();
		
		return new HomePage(driver);
	}
	
	public String getErrorMessage() {
		return driver.findElement(By.xpath("//html/body/p/font")).getText();
	}
}
