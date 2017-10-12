package Common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import Beans.ExcelTestDetails;
import Beans.RunManagerInfo;

public class ReportingFunction extends InitializeBrowser {
	
	XPathObjects xpo = new XPathObjects();
	CommonMethods cm = new CommonMethods();
	
	public synchronized WebDriver Initialize(RunManagerInfo runManagerInfoObj){
		
		try{
			//Main.loadProps();
			//testDetails = Main.getExcelValues();
			System.out.println("After Main");
			if(runManagerInfoObj.getstrBrowser().equalsIgnoreCase("IE")){
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				System.setProperty("webdriver.ie.driver", ".//IEDriverServer.exe");	
				driver = new InternetExplorerDriver(capabilities);
				System.out.println("IE launched successfully");			
				System.out.println("Initiating the session");
			}
			else if(runManagerInfoObj.getstrBrowser().equalsIgnoreCase("Chrome")){
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				//capabilities.setCapability(ChromeDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				System.setProperty("webdriver.chrome.driver", ".//chromedriver.exe");
				driver = new ChromeDriver(capabilities);
				System.out.println("CHROME launched successfully");			
				System.out.println("Initiating the session");
			}
			else if(runManagerInfoObj.getstrBrowser().equalsIgnoreCase("Firefox")){
				
			}

		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Issue while launching: "+e);
		}
		
		return driver;
		
	}
	
	public synchronized boolean FnLaunchMercuryApp(WebDriver driver,RunManagerInfo runManagerInfoObj)
	{
		boolean flag = false;
		try{

			System.out.println("Launching Mercury Application URL");
			Thread.sleep(5000);
			String url = runManagerInfoObj.getExcelTestDetails().getExecURL();
			System.out.println("Exec URL: "+runManagerInfoObj.getExcelTestDetails().getExecURL());
			driver.get(url);
			System.out.println("Launch 1");
			Thread.sleep(5000);
			
			System.out.println("URL: "+driver.getCurrentUrl());
			System.out.println("Title: "+driver.getTitle());

			String currentUrl=driver.getCurrentUrl();
			System.out.println("Current URL: "+currentUrl);
			System.out.println("Current BROWSER: "+runManagerInfoObj.getstrBrowser());
			
			if(currentUrl.contains("newtours")){
				System.out.println("Browser: "+runManagerInfoObj.getstrBrowser()+" is launched");
				if (cm.ExplicitWait("XPATH", xpo.UserName,driver))
				{	
					flag=true;					
					System.out.println("Required URL get launched sucessfully: "+url);

				}else
				{
					System.out.println("Required URL not launched sucessfully: "+url);
					flag=false;
				}
			}
			else{
				System.out.println("Browser: "+runManagerInfoObj.getstrBrowser()+" is not launched");
				flag=false;
			}
		}

		catch(Exception e){
			System.out.println("Application not launched successfully: "+e);
			flag = false;
		}
		return flag;
	}
	
	public synchronized boolean UserLogin(WebDriver driver,RunManagerInfo runManagerInfoObj)
	{
		boolean flag = false;
		try{
			String userId = runManagerInfoObj.getExcelTestDetails().getUserID();
			String Password = runManagerInfoObj.getExcelTestDetails().getPassword();
			
			driver.manage().window().maximize();
			
			if(cm.ExplicitWait("XPATH", xpo.UserName, driver)){
				System.out.println("On Login page");
				
				if(cm.ExplicitWait("XPATH", xpo.UserName, driver)){
					System.out.println("Username field present");
					WebElement userName = driver.findElement(By.xpath(xpo.UserName));
					userName.sendKeys(userId);
					System.out.println("Username entered");
					
					if(cm.ExplicitWait("XPATH", xpo.Password, driver)){
						System.out.println("Password field present");
						WebElement password = driver.findElement(By.xpath(xpo.Password));
						password.sendKeys(Password);
						System.out.println("Password entered");
						flag = true;						
					}
					else{
						System.out.println("Password field not present");
						flag = false;
					}
				}
				else{
					System.out.println("User credentials not entered correctly");
					flag = false;
				}
				
				if(cm.ExplicitWait("XPATH", xpo.LoginButton, driver)){
					System.out.println("Login button present");
					WebElement loginButton = driver.findElement(By.xpath(xpo.LoginButton));
					loginButton.click();
					System.out.println("Login button clicked");
					
					Thread.sleep(5000);
					
					if((cm.ExplicitWait("XPATH", xpo.FlightFinderImg, driver)) || (cm.ExplicitWait("XPATH", xpo.SignOffLink, driver))){
						WebElement user = driver.findElement(By.xpath(xpo.SignOffLink));
						if(user.getText().contains("SIGN-OFF")){
							System.out.println("User logged in successfully: "+user.getText());
							flag = true;							
						}
						else{
							System.out.println("Welcome text not displayed");
							flag = false;
						}
					}
					else{
						System.out.println("Welcome text not displayed");
						flag = false;
					}
					
				}
				else{
					System.out.println("Login button not present");
					flag = false;
				}
				
			}
			else{
				System.out.println("User not on Login page");
				flag = false;
			}
		}
		
		catch(Exception e){
			System.out.println("User not logged in successfully: "+e);
			flag = false;
		}
		
		return flag;
		
	}
	
	public synchronized boolean Logout(WebDriver driver,RunManagerInfo runManagerInfoObj)
	{
		boolean blnFlag = false;
		try{
			if(cm.ExplicitWait("XPATH", xpo.BackToHome, driver)){
				System.out.println("Back To Home button is displayed");
				WebElement BackToHome = driver.findElement(By.xpath(xpo.BackToHome));
				BackToHome.click();
				if(cm.ExplicitWait("XPATH", xpo.UserName, driver)){
					System.out.println("Back To Home button is clicked and User is on Login page");
					blnFlag = true;
				}
				else{
					System.out.println("Back To Home button is not clicked and User is not on Login page");
					blnFlag = false;
				}				
			}
			else{
				System.out.println("Back To Home button is not displayed");
				blnFlag = false;
			}
		}
		catch(Exception e){
			System.out.println("User not logged out successfully: "+e);
			e.printStackTrace();
			blnFlag = false;
		}
		return blnFlag;
	}
	
	public synchronized void FnCom_AbortExec(WebDriver driver)
	{
		driver.quit();
	}

}
