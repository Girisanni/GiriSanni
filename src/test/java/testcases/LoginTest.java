package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseClass;
import pom.LoginFunctionality;
import utils.Utils;

public class LoginTest  extends BaseClass {

	LoginFunctionality login;

	@BeforeMethod
	public void setUp() {
		browserInitialization();
		login=new LoginFunctionality(driver);
	}

	String sheetName="loginFunctionality";
	@DataProvider
	public String[][] testData() throws Throwable{
		return Utils.setData(sheetName);
	}



	@Test(dataProvider="testData")
	public void verifyLoginValidCredentials(String uname,String pwd) {	
		login.doLogin(uname, pwd);
		String url=driver.getCurrentUrl();
		Assert.assertEquals("https://e-quarz.com/", url);

	}

	@Test(dataProvider="testData")
	public void verifyLoginInvalid(String uname,String pwd) {
		login.doLogin(uname, pwd);
		String errMsg=driver.findElement(By.xpath("//div[@class='toast-message']")).getText();
		Assert.assertEquals("Credentials do not match or account has been suspended.", errMsg,"Invalid UserName");
	}


	@Test(dataProvider="testData")
	public void verifyLoginInvalidPassword(String uname,String pwd) {
		login.doLogin(uname, pwd);
		String errMsg=driver.findElement(By.xpath("//div[@class='toast-message']")).getText();
		Assert.assertEquals("Credentials do not match or account has been suspended.", errMsg,"Invalid Password");
	}

	@Test(dataProvider="testData")
	public void verifyLoginEmptyUserName(String uname,String pwd) {
		login.doLogin(uname, pwd);
		String isUserNameRequired=login.email.getAttribute("required");
		if(uname.equals("")) {
			Assert.assertEquals(false,isUserNameRequired, "Empty UserName");
		}
	}

	@Test(dataProvider="testData")
	public void verifyLoginEmptyPassword(String uname,String pwd) {
		login.doLogin(uname, pwd);
		String isPasswordRequired=login.password.getAttribute("required");
		if(pwd.equals("")){
			Assert.assertEquals(false,isPasswordRequired, "Empty Password");
		}

	}

	@Test
	public void verifyLoginEmptyCredentials() {
		login.signin.click();
		Assert.fail("Both credentials are empty");
	}

//	@Test(dataProvider="testData")
//	public void verifyTabButton(String uname,String pwd) throws Throwable {
//		login.email.sendKeys(uname);
//		Utils.keysRobot();
//		login.password.sendKeys(pwd);
//		login.signin.click();
//		
//	}
//	
//	@Test(dataProvider="testData")
//	public void verifyEnterButton(String uname,String pwd) throws Throwable {
//		login.email.sendKeys(uname);
//		login.password.sendKeys(pwd);
//		Utils.enterRobot();
//		
//	}
//	
	@Test
	public void forgotPasswordEnable() {
		boolean forgotPassPresence=login.forgotPassword.isEnabled();
		Assert.assertEquals(true, forgotPassPresence);

	}


	@Test
    public void verifyForgotPassword() {
		login.forgotPass();
		String url=driver.getCurrentUrl();
		Assert.assertEquals("https://e-quarz.com/customer/auth/recover-password", url);
	}


//	@Test
//	public void rememberMeEnable() {
//		Assert.assertEquals(true, Utils.isElementEnable(login.rememberMe) );
//	}


	@Test
	public void verifyRememberme() {
		login.remember_me();	
		Assert.assertEquals(true, Utils.isElementSelected(login.rememberMe));

	}

	@Test
	public void verifySignUp() {
		login.signup_button();
		String url=driver.getCurrentUrl();
		Assert.assertEquals("https://e-quarz.com/customer/auth/sign-up", url);
	}

//	@Test
//	public void verifyShowPassword() {
//		login.show_password();
//		String attribute=login.password.getAttribute("type");
//		Assert.assertEquals("text", attribute);
//	}


	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}

