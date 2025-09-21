package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBaseClass.BaseClass;

public class TC_LoginTest extends BaseClass {

	@Test(groups= {"sanity","master"})
	public void test_login() {
		// Test steps would go here
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		MyAccountPage mat = new MyAccountPage(driver);
		boolean targetPage=mat.isMyAccountPageExists();
		Assert.assertEquals(targetPage, true,"Login Unsuccessful");
		
		
		
		
	}

}
