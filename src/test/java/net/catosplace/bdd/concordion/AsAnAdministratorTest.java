package net.catosplace.bdd.concordion;

import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import net.catosplace.bdd.page.LoginPage;
import net.catosplace.bdd.page.HomePage;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.PageFactory;

@RunWith(ConcordionRunner.class)
public class AsAnAdministratorTest {
	
	private WebDriver driver;
	private static final String URL_BDD_APPLICATION = "http://localhost:8080/bdd";
	private static final String URL_BDD_APP_LOGOUT = 
		"http://localhost:8080/bdd/j_spring_security_logout";
	
	private LoginPage loginPage;
	private HomePage homePage;
	
	private String errorMessage;
	
	private static final String ADMIN_USER = "admin";
	private static final String ADMIN_PASSWORD = "password";
	
	@Before
	public void initWebDriverAndPageFactoryElements() {
		driver = new HtmlUnitDriver();
		driver.get(URL_BDD_APPLICATION);
	}
	
	@After
	public void logoutOfApplication() {
		driver.get(URL_BDD_APP_LOGOUT);
	}

	/**
	 * When trying to login with bad credentials an error message
	 * should appear on the login page.
	 * 
	 * @return the login page error message
	 */
	public String shouldSeeBadCredentialsErrorMessage() {
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage = loginPage.loginAsExpectingError(ADMIN_USER, null);
		assertNotNull("An error message should occur on the current page",
				loginPage.getErrorMessage());
		return loginPage.getErrorMessage();
	}
	
	/**
	 * When using concordion:assertEquals you can use bean property
	 * names to return the value of the getXXX() method for that
	 * property
	 * 
	 * @return the errorMessage property
	 */
	public String getErrorMessage() {
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage = loginPage.loginAsExpectingError(ADMIN_USER, null);
		setErrorMessage(loginPage.getErrorMessage());
		return this.errorMessage;
	}
	
	private void setErrorMessage(String message) {
		this.errorMessage = message;
	}
	
	/**
	 * Gets the message when the passed in user successfully logs in to
	 * the application
	 * @param user
	 * 	- the user to successfully log in with
	 * @return
	 *  - the welcome message for the user successfully logged in
	 */
	public String loggedInMessageFor(String user) {
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		homePage = loginPage.loginSuccessfully(user, ADMIN_PASSWORD);
		PageFactory.initElements(driver, homePage);
		return homePage.getWelcomeMessage();
	}
	
	private String role;
	private Set<String> users = new HashSet<String>();
	
	class Message {
		
		//public String welcome;
		//public String role;
		
		private final String welcome;
		private final String role;
		
		public Message(String welcome, String role) {
			this.welcome = welcome;
			this.role = role;
		}

		public String getWelcome() {
			return this.welcome;
		}
		
		public String getRole() {
			return this.role;
		}
	}
	
	public String loggedInMessageForRole() {
		return "Welcome " + getRole();
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	private String getRole() {
		return this.role;
	}
	
	public Message split(String welcomeMessage) {
        //Message message = new Message();
        String[] words = welcomeMessage.split(" ");
        //message.welcome = words[0];
        //message.role = words[1];
        return new Message(words[0], words[1]);
        //return message;

	}
	
	public void setUpUser(String username) {
		users.add(username);
	}
	
    public Iterable<String> getSearchResultsFor(String searchString) {
        SortedSet<String> matches = new TreeSet<String>();
        for (String username : users) {
            if (username.contains(searchString)) {
                matches.add(username);
            }
        }
        return matches;
    }

}
