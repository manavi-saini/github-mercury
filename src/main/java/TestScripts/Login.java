package TestScripts;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Beans.ExcelTestDetails;
import Common.CommonMethods;
import Common.InitializeBrowser;
import Common.XPathObjects;

public class Login extends InitializeBrowser{

	XPathObjects xpo = new XPathObjects();
	CommonMethods cm = new CommonMethods();
	
	@Test
	public synchronized boolean UserLogin(WebDriver driver,ExcelTestDetails testDetails){
		boolean flag = false;
				
		try{
			System.out.println("In Login method");
			Thread.sleep(5000);
			String url = testDetails.getExecURL();
			driver.get(url);
			
			Thread.sleep(10000);
			
			if(cm.ExplicitWait("XPATH", xpo.UserName, driver)){
				System.out.println("On Login page");
				
				if(cm.ExplicitWait("XPATH", xpo.UserName, driver)){
					System.out.println("Username field present");
					WebElement userName = driver.findElement(By.xpath(xpo.UserName));
					userName.sendKeys(testDetails.getUserID());
					System.out.println("Username entered");
					
					if(cm.ExplicitWait("XPATH", xpo.Password, driver)){
						System.out.println("Password field present");
						WebElement password = driver.findElement(By.xpath(xpo.Password));
						password.sendKeys(testDetails.getPassword());
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
}
