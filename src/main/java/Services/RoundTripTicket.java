package Services;

import Beans.RunManagerInfo;
import Common.CommonMethods;
import Common.InitializeBrowser;
import Common.XPathObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RoundTripTicket extends InitializeBrowser {
	
	public boolean blnFlag;
	XPathObjects xpo = new XPathObjects();
	CommonMethods cm = new CommonMethods();
	
	public synchronized boolean SelectRoundTrip(WebDriver driver,RunManagerInfo runManagerInfoObj) throws Exception{
		
		try{
			
			if(cm.ExplicitWait("XPATH", xpo.RoundTripRadioButton, driver)){
				System.out.println("Round Trip is displayed");
				WebElement roundTrip = driver.findElement(By.xpath(xpo.RoundTripRadioButton));
				if(roundTrip.isSelected()){
					System.out.println("Round Trip is already selected");
					blnFlag = true;
				}
				else{
					roundTrip.click();
					System.out.println("Round Trip is selected");
					blnFlag = true;
				}				
			}
			else{
				System.out.println("Round Trip is not displayed");
				blnFlag = false;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			blnFlag = false;
		}
		
		return blnFlag;
 
	}

}
