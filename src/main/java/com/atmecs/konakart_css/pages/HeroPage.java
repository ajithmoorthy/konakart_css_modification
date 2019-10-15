package com.atmecs.konakart_css.pages;

import java.text.ParseException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.atmecs.konakart_css.helper.SeleniumHelper;
import com.atmecs.konakart_css.helper.ValidaterHelper;
import com.atmecs.konakart_css.logreports.LogReporter;
/**
 * This class contains the validate hero page method 
 * this method automate and validate the hero image product using Selenium scripts and testng.
 * @author ajith.periyasamy
 *
 */
public class HeroPage {
	LogReporter log=new LogReporter();
	SeleniumHelper help=new SeleniumHelper();
	ValidaterHelper validate=new ValidaterHelper();
	/**
	 * this method  take the below parameters
	 * @param driver
	 * @param prop
	 * @param proddata
	 *  and automate the hero image product with review,product description,specification validation.
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	public void validateHeropPage(WebDriver driver,Properties prop,String[] proddata) throws InterruptedException, ParseException{
		try {
			WebDriverWait wait2 = new WebDriverWait(driver, 20);
			wait2.until(ExpectedConditions.elementToBeClickable(help.matchElement(prop.getProperty("loc.img.hero"))));
			WebElement imgElement=driver.findElement(help.matchElement(prop.getProperty("loc.img.hero")));
		if(imgElement.getAttribute("srcset").equalsIgnoreCase(proddata[1])) 
		{
			help.clickElement(driver,prop.getProperty("loc.img.hero"));
			help.scrollPageMethod(driver,prop.getProperty("loc.tab.proddiscrip") );
			help.clickElement(driver,prop.getProperty("loc.tab.proddiscrip"));
			String proddiscrip=validate.textOfElement(driver, prop.getProperty("loc.tab.proddiscrip"));
			validate.assertValidater(proddiscrip,proddata[3]);
			validate.assertValidater(true, validate.containsValidater(driver, prop.getProperty("loc.proddiscrip.content") ,proddata[6]));
			help.clickElement(driver,prop.getProperty("loc.tab.specification"));
			String prodspecific=validate.textOfElement(driver, prop.getProperty("loc.tab.specification"));
			validate.assertValidater(prodspecific,proddata[4]);
			validate.assertValidater(true, validate.containsValidater(driver, prop.getProperty("loc.prodspecific.content"),proddata[7]));
			validate.cutomerReviewValidation(driver, prop, proddata);
	}
		}catch(Exception e) {
			System.out.println("Image is not matched");
		}
	}
	
}
