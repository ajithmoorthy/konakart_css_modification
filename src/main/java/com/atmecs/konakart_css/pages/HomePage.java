package com.atmecs.konakart_css.pages;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.atmecs.konakart_css.helper.SeleniumHelper;
import com.atmecs.konakart_css.helper.ValidaterHelper;
import com.atmecs.konakart_css.logreports.LogReporter;
/**
 * this class contains the konakartValidate method to validate and automate the drop down and validate product
 * with negative and positive scenario.
 */
public class HomePage {
	LogReporter log=new LogReporter();
	SeleniumHelper help=new SeleniumHelper();
	ValidaterHelper validate=new ValidaterHelper();
	/**
	 * this method take the below parameters
	 * @param driver
	 * @param prop
	 * @param data
	 * and call the recursive method to validate the each product.
	 */
	public void konakartValidate(WebDriver driver,Properties prop,String[] data) {
		validate.titleValidater(driver,data[0]);
		help.dropDown(prop.getProperty("loc.dropdown.ecom"), driver, Integer.parseInt(data[1]));
		String temp = null;
		for(int initialvariable=3; initialvariable<data.length; initialvariable++)
		{
			if(initialvariable==3) {
				temp=data[initialvariable];
			}else {
				temp=temp+","+data[initialvariable];
			}
		}
		String[] temparray=temp.split(",");
		recursiveMethod(driver,prop,data[2],temparray);
	}
	/**
	 * this method take the below parameters
	 * @param driver
	 * @param prop
	 * @param option
	 * @param temparray
	 * and validate the product name,reviews and price of the product.
	 */
	public void recursiveMethod(WebDriver driver,Properties prop,String option,String[] temparray)
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//help.sendKeys(prop.getProperty("loc.txtfield.search"), driver, option);
		help.sendKeysDropDown(driver,prop.getProperty("loc.txtfield.search"),prop.getProperty("loc.txtfield.dropdown"), option);
		//help.clickElement(driver,prop.getProperty("loc.btn.search"));
		help.clickElement(driver,prop.getProperty("loc.txtfield.dropdown") );
		if(driver.findElement(help.matchElement(prop.getProperty("loc.container.numofproduct"))).getText()!=" ") {
			int numofprod=validate.findListlength(driver, prop.getProperty("loc.container.numofproduct"));
			WebElement prodelement=driver.findElement(help.matchElement(prop.getProperty("loc.panel.konakartproduct")));
			String[] elementarray=prodelement.getText().split("\n");
			Set<String[]> prodset=validate.prodDetails(elementarray,numofprod);
			int initial=0;
			for(String[] exam:prodset)
			{
				for(int count1=0; count1<exam.length; count1++) 
				{
					validate.assertValidater(exam[count1], temparray[initial]); 
					initial++;
				}

			}
		}else {
			WebElement emptyelement=driver.findElement(help.matchElement(prop.getProperty("loc.txt.prodnotavailabe")));
			validate.assertValidater(emptyelement.getText(), "There are no available products.");	
		}
	}
}
