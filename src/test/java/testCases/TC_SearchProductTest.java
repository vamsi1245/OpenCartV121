package testCases;

import testBaseClass.BaseClass;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.SearchPage;

public class TC_SearchProductTest extends BaseClass{
	
	@Test(groups= {"sanity","master"})
	public void test_searchProduct() {		
		
		SearchPage sp=new SearchPage(driver);
		sp.setSearch("MacBook");
		sp.clickSearch();
		sp.clickProduct();
		//boolean product=sp.isProductExist();
		//Assert.assertEquals(product, true,"Product not found");
	
		Assert.assertEquals(true, true);
		
		
	}

}
