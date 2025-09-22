package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBaseClass.BaseClass;

public class TC_AccountRegistrationTest extends BaseClass {

	@Test(groups= {"regression","master"})
	public void test_account_Registration() {
   
		// Test steps would go here
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();
		AccountRegistrationPage accReg = new AccountRegistrationPage(driver);
		accReg.setFirstName(randomeString().toUpperCase());
		accReg.setLastName(randomeString().toUpperCase());
		accReg.setEmail(randomeString() + "@gmail.com");
		accReg.setTelephone(randomNumber());
		String password = randomAlphaNumeric();
		accReg.setPassword(password);
		accReg.setConfirmPassword(password);
		accReg.setPrivacyPolicy();
		accReg.clickContinue();
		String confirmation = accReg.getConfirmationMsg();
		Assert.assertEquals(confirmation, "Your Account Has Been Created!");
	}

}
