package testcases;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseClass;
import pom.SearchFunctionality;
import utils.Utils;

public class SearchTest extends BaseClass{
 
	SearchFunctionality search;

	@BeforeMethod
	public void setUp() {
		browserInitialization();
		search=new SearchFunctionality(driver);
	}

	String sheetName="searchFunctionality";
	@DataProvider
	public String[][] testData() throws Throwable{
		return Utils.setData(sheetName);
	}

	@Test(dataProvider="testData")
	public void verifySearch(String product) throws Throwable {
		search.doSearch(product);
		Utils.enterRobot();
		List<WebElement> iphones=driver.findElements(By.xpath("//div[@class='text-left pl-3']/a"));

		for(WebElement e:iphones) {

			if(e.getText().toLowerCase().contains(product.toLowerCase())) {
				continue;
			}
			else {
				System.out.println(e.getText());
				Assert.fail("Not all products are not related to iphone");
			}
		}

	}

	@Test(dataProvider="testData")
	public void verifySearchEmpty(String product) throws Throwable {
		search.doSearch(product);
		Utils.enterRobot();
		String s=search.price_filter();
		if(!s.equals("")) {
			Assert.fail("Products should not be shown on the screen");
		}

	}


	@Test(dataProvider="testData")
	public void verifySearchAutoSuggestions(String product) throws InterruptedException {
		search.doSearch(product);
		Thread.sleep(1000);
		List<WebElement> search_auto_suggestions=search.autoSuggestions();
		Random rand=new Random();
		int randomproduct=rand.nextInt(search_auto_suggestions.size());
		WebElement w=search_auto_suggestions.get(randomproduct);
		String str=w.getText();
		w.click();
		List<WebElement> products=search.productsNames();
		for(WebElement p:products) {
			String s=p.getText();
			Assert.assertTrue(Utils.containsString(str,s),"The product is not related to the search context");
		}


	}

	@AfterMethod
	public void tearDown() throws Throwable {
		Thread.sleep(2000);
		driver.close();
	}

}
