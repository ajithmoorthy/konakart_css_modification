package com.atmecs.konakart_css.testscripts;

import java.io.IOException;
import java.text.ParseException;
import java.util.Properties;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.atmecs.konakart_css.constants.FileConstants;
import com.atmecs.konakart_css.helper.SeleniumHelper;
import com.atmecs.konakart_css.logreports.LogReporter;
import com.atmecs.konakart_css.pages.HeroPage;
import com.atmecs.konakart_css.testbase.TestBase;
import com.atmecs.konakart_css.utils.ExcelReader;
import com.atmecs.konakart_css.utils.PropertiesReader;
public class HeroImage extends TestBase {
	LogReporter log=new LogReporter();
	SeleniumHelper help=new SeleniumHelper();
	ExcelReader excelread=new ExcelReader();
	HeroPage hero=new HeroPage();
	PropertiesReader propread=new PropertiesReader();
	/**
	 * this method is data provider for the validate HeroImage method .
	 * this method read the value from excel and pass it to the validate konakart method  
	 *  finally @return one dimensional array.
	 * @throws IOException
	 */
	@DataProvider(name = "productdata")
	public String[][] getValidationData() throws IOException {
		String Excelarray[][] = null;
		Excelarray = excelread.excelDataProviderArray(FileConstants.PROD_TEST_DATA);
		return Excelarray;
	}
	/**
	 * this method take the parameters from data provider
	 * @param proddata
	 * and validate the hero image product description, specification,reviews using the assertion and testNG
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	@Test(dataProvider="productdata")
	public void validateHeroImage(String[] proddata) throws IOException, InterruptedException, ParseException {
		Properties prop=propread.KeyValueLoader(FileConstants.HERO_PATH);
		logger=extentObject.startTest("Validate hero img");
		hero.validateHeropPage(driver, prop,proddata);
	}

}
