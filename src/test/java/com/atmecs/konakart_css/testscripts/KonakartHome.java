package com.atmecs.konakart_css.testscripts;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.atmecs.konakart_css.constants.FileConstants;
import com.atmecs.konakart_css.helper.SeleniumHelper;
import com.atmecs.konakart_css.logreports.LogReporter;
import com.atmecs.konakart_css.pages.HomePage;
import com.atmecs.konakart_css.testbase.TestBase;
import com.atmecs.konakart_css.utils.ExcelReader;
import com.atmecs.konakart_css.utils.PropertiesReader;
/**
 * this konakartHome class is extend by the TestBase
 * this contains the test scripts for automate the the konakart home page  
 * @author ajith.periyasamy
 *
 */
public class KonakartHome extends TestBase {
	LogReporter log=new LogReporter();
	SeleniumHelper help=new SeleniumHelper();
	HomePage home=new HomePage();
	PropertiesReader propread=new PropertiesReader();
	ExcelReader excelread=new ExcelReader();
	/**
	 * this method is data provider for the validate konakart method .
	 * this method read the value from excel and pass it to the validate konakart method  
	 *  finally @return one dimensional array.
	 * @throws IOException
	 */
	@DataProvider(name = "welcomedata")
	public String[][] getValidationData() throws IOException {
		String Excelarray[][] = null;
		Excelarray = excelread.excelDataProviderArray(FileConstants.WELCOME_DATA);
		return Excelarray;
	}
	/**
	 * this method take the above parameter data provider array 
	 * @param data
	 *  and contains the test scripts for validate the home page and product with positive and negative scenario
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test(dataProvider="welcomedata")
	public void validateKonakart(String[] data) throws IOException, InterruptedException {
		Properties prop=propread.KeyValueLoader(FileConstants.HOME_PATH);
		logger=extentObject.startTest("validate konakart");
		driver.manage().window().maximize();
		home.konakartValidate(driver, prop,data);
	}
	

}
