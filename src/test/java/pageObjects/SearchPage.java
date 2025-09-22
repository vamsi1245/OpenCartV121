package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage{

	public SearchPage(WebDriver driver) {
		super(driver);
		
	}
	@FindBy(xpath="//input[@name='search']")
	WebElement txtSearch;
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']")
     WebElement btnSearch;
	@FindBy(linkText="MacBook")
	WebElement isProductExist;
	
	public void setSearch(String productName) {
		txtSearch.sendKeys(productName);
		
	}
	public void clickSearch() {
		btnSearch.click();
	}
//	public WebElement isProductExist() {
//		return btnSearch;
//	
//	}
	public void clickProduct() {
		isProductExist.click();
	}
}
