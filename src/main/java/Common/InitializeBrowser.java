package Common;

import org.testng.annotations.Test;

import Beans.RunManagerInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Beans.ExcelTestDetails;
import Beans.FlightDetailsOne;
import Beans.RunManagerInfo;
import TestScripts.Login;

public class InitializeBrowser {

	protected static WebDriver driver;
	public ExcelTestDetails testDetails = new ExcelTestDetails();
	public Map<Integer,String> moduledetails=new HashMap<Integer,String>();
	public static List<RunManagerInfo> runManagerInfoList = new ArrayList<RunManagerInfo>();
	public static Map<Integer,FlightDetailsOne> flightValuesList = new HashMap<Integer,FlightDetailsOne>();
	public String strPresentDir;
	public static Properties prop;
	private static InputStream inputStream;
	String[] strConfigValues = new String[10];
	public static Map m1= new HashMap<String, String>();
	public static String filePath = null;
	
		
	public static String getRelativePath()
	{
		System.out.println(new File(System.getProperty("user.dir")).getAbsolutePath());
		String relativePath = new File(System.getProperty("user.dir")).getAbsolutePath();
		return relativePath;
	}
	
	@BeforeSuite
	public void getConfigValues(){
		
		try{
			strPresentDir = getRelativePath();
			prop= new Properties();
			String path = "\\src\\main\\resources\\config.properties";
			String presentDir = strPresentDir+path;
			inputStream = new FileInputStream(new File(presentDir));
			
			if (inputStream != null) {
				prop.load(inputStream);
				System.out.println("Successfully loaded properties file");
				//logger.info("Successfully loaded properties file");
			} else {
				System.out.println("Error occurred while loading properties file");
				throw new FileNotFoundException("property file '" + presentDir + "' not found in the classpath");
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
			System.err.println("Initialization file error :");
			System.err.println("	Function : getfilePath");
			System.err.println("	Message : "+e.getMessage());
			System.err.println("	Cause : "+e.getCause());
		}
		
	}
	
	@AfterTest
	public void Close() {
		try{
			System.out.println("Closing the session");	
			driver.close();
		}
		catch (Exception e) {
			System.out.println("Issue while Closing the session" + e);
		}
	}
			
	@AfterSuite
	public void Quit() {
		try{
			System.out.println("Closing the session");	
			driver.quit();
		}
		catch (Exception e) {
			System.out.println("Issue while Closing the session" + e);
		}
	}
}
