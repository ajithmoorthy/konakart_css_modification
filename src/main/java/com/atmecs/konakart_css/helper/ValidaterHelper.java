package com.atmecs.konakart_css.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.atmecs.konakart_css.extentreport.Extent;
import com.atmecs.konakart_css.logreports.LogReporter;
import com.relevantcodes.extentreports.LogStatus;

public class ValidaterHelper extends Extent {
	LogReporter log=new LogReporter();
	SeleniumHelper help=new SeleniumHelper();
	
	/**
	 * This urlValidater method take the below parameters
	 * @param Driver
	 * @param Expected_Url
	 * and validated the URL of the driver using expected URL.
	 * validation is done by the assertion using Assert class and its methods.
	 */
	public void urlValidater(WebDriver Driver,String Expected_Url){
		try {
			Assert.assertEquals(Driver.getCurrentUrl(),Expected_Url);
			log.logReportMessage("Successfully Validated the correct Url is :"+ Driver.getCurrentUrl());
			logger.log(LogStatus.INFO,"Successfully Validated the correct Url is :" +Driver.getCurrentUrl());
		}catch(AssertionError e) {
			System.out.println("Navigate to wrong Webpage");
			log.logReportMessage("Navigate to wrong Webpage");
			logger.log(LogStatus.INFO, "Navigate to wrong Webpage");
		}	
	}
	/**
	 * This titleValidater method take the below parameters
	 * @param driver
	 * @param documentTitle
	 * and validated the title of the web page using expected page title.
	 * validation is done by the assertion using Assert class and its method.
	 */
	public void titleValidater(WebDriver driver, String documentTitle){
		try {
			Assert.assertEquals(driver.getTitle(), documentTitle);
			log.logReportMessage("Document title is validated :"+driver.getTitle());
			logger.log(LogStatus.INFO,"Document title is validated :" +driver.getTitle());
		}
		catch(AssertionError e)
		{
			System.out.println("Document title is not match with Expected :"+driver.getTitle());
			log.logReportMessage("Document title is not match with Expected :"+driver.getTitle());
			logger.log(LogStatus.INFO,"Document title is not match with Expected :"+driver.getTitle());	
		}
	}
	/**
	 * This textOfElement method take below parameters
	 * @param webdriver
	 * @param locator
	 * get the text of the element  
	 *  and @return the content string.
	 */
	public String textOfElement(WebDriver webdriver,String locator) {
		String content = null;
		try {
			@SuppressWarnings("deprecation")
			Wait<WebDriver> wait = new FluentWait<WebDriver>(webdriver)
			.withTimeout(30, TimeUnit.SECONDS)
			.pollingEvery(5, TimeUnit.SECONDS)
			.ignoring(ElementClickInterceptedException.class)
			.ignoring(NoSuchElementException.class);
			WebElement element=wait.until(new Function<WebDriver,WebElement>() 
			{
				public WebElement apply(WebDriver driver) {
					return driver.findElement(matchElement(locator));
				}
			});
			content=element.getText();
		}catch (Exception e) {
			System.out.println("Element is not available or not clickable");
		}
		return content;
	}
	/**
	 * This method take input as below parameters:
	 * @param locators
	 * and perform the separate the locators and options.
	 * using that locators create the Object of By class
	 * and @return by object.
	 */
	public By matchElement(String locators) {
		By by = null;
		String[] input=locators.split(",");
		switch(input[0].toUpperCase())
		{
		case "XPATH":
			by=By.xpath(input[1]);
			break;
		case "ID":
			by=By.id(input[1]);
			break;
		case "NAME":
			by=By.name(input[1]);
			break;
		case "CSS":
			by=By.cssSelector(input[1]);
			break;
		case "CLASS":
			by=By.className(input[1]);
			break;
		case "LINK_TEXT":
			by=By.linkText(input[1]);
			break;
		case "PARTIAL_LINK_TEXT":
			by=By.partialLinkText(input[1]);
			break;
		case "TAG_NAME":
			by=By.tagName(input[1]);
			break;
		}
		return by;
	}
	/**
	 * This webElementsValidater method take the below parameters
	 * @param driver
	 * @param locators
	 * @param footerarray
	 * and validate the each web elements is present or not using assertions.
	 */
	public void webElementsValidater(WebDriver driver,String locators,String[] footerarray) {
		WebDriverWait wait2 = new WebDriverWait(driver, 20);
		wait2.until(ExpectedConditions.elementToBeClickable(matchElement(locators)));
		List<WebElement> list=driver.findElements(matchElement(locators));
		int count=0;
		while(count<1) {
			for(WebElement element:list)
			{
				String[] elementarray=element.getText().split("\n");
				for(int variable=0; variable<elementarray.length; variable++) {
					assertValidater(elementarray[variable],footerarray[count]);
					count++;
				}
			}
		}
	}
	/**
	 * This assertValidater method take the below parameters
	 * @param actual
	 * @param expected
	 * and check the actual and expected are equal or not by using the assertion.
	 */
	public void assertValidater(String actual,String expected) {
		try {
			Assert.assertEquals(actual,expected);
			log.logReportMessage("Actual Value :"+actual+" and Expected :"+expected+" is validated successfully");
			logger.log(LogStatus.INFO,"Actual Value :"+actual+" and Expected :"+expected+" is validated successfully");	
		}
		catch(AssertionError e)
		{
			System.out.println("Actual Value :"+actual+" not match with the Expected value :"+expected);
			log.logReportMessage("Actual Value :"+actual+" not match with the Expected value :"+expected);
			logger.log(LogStatus.INFO,"Actual Value :"+actual+" not match with the Expected value :"+expected);
		}
	}
	/**
	 * This contains Validations take the below parameters
	 * @param webdriver
	 * @param locator
	 * @param expected
	 * and check the element content and expected value using the if content is equal 
	 *  then @return boolean variable bool true.
	 */
	public boolean containsValidater(WebDriver webdriver,String locator,String expected) {
		boolean bool=false;
		WebElement element=webdriver.findElement(matchElement(locator));
		String content=element.getText();
		if(content.contains(expected))
		{
			bool=true;
		}
		return bool;
	}
	/**
	 * This assertValidater method take the below parameters
	 * @param actual
	 * @param expected
	 * and check the actual and expected are equal or not by using the assertion.
	 */
	public void assertValidater(boolean actual,boolean expected) {
		try {
			Assert.assertEquals(actual,expected);
			log.logReportMessage("Actual Value :"+actual+" and Expected :"+expected+" is validated successfully");
			logger.log(LogStatus.INFO,"Actual Value :"+actual+" and Expected :"+expected+" is validated successfully");	
		}
		catch(AssertionError e)
		{
			System.out.println("Actual Value :"+actual+" not match with the Expected value :"+expected);
			log.logReportMessage("Actual Value :"+actual+" not match with the Expected value :"+expected);
			logger.log(LogStatus.INFO,"Actual Value :"+actual+" not match with the Expected value :"+expected);
		}
	}
	/**
	 * This method take the below array of values 
	 * @param datearray
	 * and check the array of element in the ascending order or not.
	 */
	public void arrayValidaterMostRecent(int[] datearray){
		boolean variable=false;
		for(int count=0; count<datearray.length-1; count++) {
			if(datearray[count+1]-datearray[count]<0) 
			{ 
				variable=false; 
			}
			else {
				variable=true;
			}
		} 
		assertValidater(variable, true);
	}
	/**
	 * This method take the below array of values 
	 * @param datearray
	 * and check the array of element in the descending order or not.
	 */
	public void arrayValidaterOldest(int[] datearray){
		boolean variable=false;
		for(int count=0; count<datearray.length-1; count++) {
			if(datearray[count+1]-datearray[count]>0) 
			{ 
				variable=false; 
			}
			else {
				variable=true;
			}
		} 
		assertValidater(variable, true);
	}
	/**
	 * This method take the below parameters 
	 * @param driver
	 * @param locator
	 * find the no of days between current date to review date for each review and stored in array.  
	 *  finally @return the array.
	 * @throws ParseException
	 */
	public int[] validateBlogContent(WebDriver driver,String locator) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date=new Date();
		WebDriverWait wait2 = new WebDriverWait(driver, 20);
		wait2.until(ExpectedConditions.elementToBeClickable(matchElement(locator)));
		List<WebElement> list=driver.findElements(matchElement(locator));
		int count=0;
		int[] datearray=new int[(list.size()/2)];
		for(WebElement element:list) {
			if(count<list.size()/2) {
			String[] tempdate=element.getText().split(" ");	
			String exam=tempdate[1]+"/"+getMonthNumber(tempdate[2])+"/"+tempdate[3];
			exam=exam.replace("\\s", "");
			String secon=formatter.format(date);
			Date first=formatter.parse(exam);
			Date second=formatter.parse(secon);
			long numdays=daysBetween(first,second);
			if(numdays>90) {
				System.out.println(numdays);
				datearray[count]=(int) numdays;
			}
			count++;
			}
		}
		return datearray;
	}
	/**
	 * This method take this below parameters
	 * @param driver
	 * @param locator
	 * @param locators
	 * and get the rating of the each review and stored in on array.
	 * finally @return the array.
	 */
	public int[] ratingValidation(WebDriver driver,String locator,String locators) {
		int length=findListlength(driver,locator);
		int size=length/2;
		int[] ratingarray=new int[size]; 
		for(int initial=0; initial<length/2; initial++) {
			int rating=findListlength(driver,locators.replace("index", (initial+1)+""));
			System.out.println(rating/2);
			ratingarray[initial]=rating/2;
		}
		return ratingarray;
	}
	/**
	 * This method take the two dates:
	 * @param one
	 * @param two
	 * Convert the difference between two dates 
	 *  and @return the difference between two dates
	 */
	private static long daysBetween(Date one, Date two) {
		long difference =  (one.getTime()-two.getTime())/86400000;
		return Math.abs(difference);
	}
	/**
	 * This method take the below parameter
	 * @param monthName
	 * Covert the monthName to number
	 * and @return integer value of the month.
	 */
	private int getMonthNumber(String monthName) {
		return Month.valueOf(monthName.toUpperCase()).getValue();
	}
	/**
	 * this method takes the below parameters
	 * @param webdriver
	 * @param locator
	 * and find the length of the webElement list
	 *  finally @return the length of the list
	 */
	public int findListlength(WebDriver webdriver,String locator) {
		List<WebElement> list=webdriver.findElements(matchElement(locator));
		int length=list.size();
		return length;
	}
	/**
	 * This method takes the below parameters:
	 * @param driver
	 * @param prop
	 * @param proddata
	 * and validate the customer review details by the sorting.
	 * sorting is based on the date and the reviews. 
	 * @throws ParseException
	 * @throws InterruptedException
	 */
	public void cutomerReviewValidation(WebDriver driver,Properties prop,String[] proddata) throws ParseException, InterruptedException {	
		help.clickElement(driver,prop.getProperty("loc.tab.reviews"));
		String prodreviews=textOfElement(driver, prop.getProperty("loc.tab.reviews"));
		assertValidater(prodreviews,proddata[5]);
		int[] datearray=validateBlogContent(driver, prop.getProperty("loc.reviewtxt.date"));
		arrayValidaterMostRecent(datearray);
		Thread.sleep(3000);
		help.dropDown(prop.getProperty("loc.dopdown.sort"), driver, 1);
		int[] datearray1=validateBlogContent(driver, prop.getProperty("loc.reviewtxt.date"));
		arrayValidaterOldest(datearray1);
		Thread.sleep(3000);
		help.dropDown(prop.getProperty("loc.dopdown.sort"), driver, 2);
		int[] ratingarray=ratingValidation(driver,prop.getProperty("loc.size.review"),prop.getProperty("loc.review.rating"));
		arrayValidaterOldest(ratingarray);
		Thread.sleep(3000);
		help.dropDown(prop.getProperty("loc.dopdown.sort"), driver, 3);
		int[] ratingarray1=ratingValidation(driver,prop.getProperty("loc.size.review"),prop.getProperty("loc.review.rating"));
		arrayValidaterMostRecent(ratingarray1);
	}
	/**
	 * this method take the below parameters 
	 * @param elementarray
	 * @param numofprod
	 * This method store the product details in array and array added to the Set 
	 *  finally @return the Set
	 */
	public Set<String[]> prodDetails(String[] elementarray,int numofprod) {
		Set<String[]> prodset=new HashSet<String[]>();
		
		int initialvariable=0;
		for(initialvariable=0; initialvariable<elementarray.length+(numofprod-1); initialvariable++) 	
		{
			String[] prodarray=new String[3];
			if(elementarray[initialvariable]!=" ") {
			int variable=0;
			while(variable<=2) 
			{
			prodarray[variable]=elementarray[initialvariable];
			if(variable==2) {
				prodset.add(prodarray);
			}
			variable++;
			initialvariable++;
			}
		}
		}
		return prodset;
	}
}
