package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchFunctionality {
	
	@FindBy(xpath="//input[@placeholder='Search here ...']")
	public WebElement search;

	@FindBy(id="price-filter-count")
	public WebElement priceFilter;

	@FindBy(xpath="//div[@class='input-group-overlay d-none d-md-block mx-4']//li/a")
	public List<WebElement> searchAutoSuggestions;

	@FindBy(xpath="//div[@class='text-left pl-3']/a")
	public List<WebElement> products;

	public SearchFunctionality(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}


	public void doSearch(String text) {
		search.click();
		for(int i=0;i<text.length();i++) {
			search.sendKeys(""+text.charAt(i));
		}

	}

	public String price_filter() {
		String s=priceFilter.getText();
		return s;
	}

	public List autoSuggestions() {
		return searchAutoSuggestions;

	}

	public List productsNames() {
		return products;
	}



}
