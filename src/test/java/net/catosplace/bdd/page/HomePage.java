package net.catosplace.bdd.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
	
	private final WebDriver driver;

	@FindBy(id = "welcome")
	private WebElement welcome;
	@FindBy(id = "footer")
	private WebElement footer;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		
		if (!"Home Page".equals(driver.getTitle())) {
			throw new IllegalStateException("This is not the home page");
		}
	}
	
	public String getWelcomeMessage() {
		return welcome.getText();
	}
	
	public String getFooter() {
		return footer.getText();
	}
}
