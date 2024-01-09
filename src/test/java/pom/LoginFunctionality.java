package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;

import base.BaseClass;

public class LoginFunctionality extends BaseClass {
	
	@FindBy(id="si-email")
    public WebElement email;

	@FindBy(id="si-password")
	public WebElement password;

	@FindBy(xpath="//button[text()='Sign in']")
	public WebElement signin;

	@FindBy(xpath="//a[text()[normalize-space()='Forgot password?']]")
	public WebElement forgotPassword;

	@FindBy(id="remember")
	public WebElement rememberMe;

	@FindBy(xpath="//a[@class=\"btn btn-outline-primary\"]")
	public WebElement signupbutton;

	@FindBy(xpath="//i[@class='czi-eye password-toggle-indicator']")
	public WebElement showPassword;


	//pagefactory initialization 
	public LoginFunctionality(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	/*
	//properties file
	public void doLogin() {
		email.sendKeys(prop.getProperty("email"));
		password.sendKeys(prop.getProperty("password"));
		signin.click();
	}
	*/

	//dataprovider & excel
	public void doLogin(String username,String pass) {
		email.sendKeys(username);
		password.sendKeys(pass);
		signin.click();
	}

	public void forgotPass() {
			forgotPassword.click();
	}

	public void remember_me() {
		rememberMe.click();

	}

	public void signup_button() {
		signupbutton.click();
	}

	public void show_password() {
		showPassword.click();
	}

}

