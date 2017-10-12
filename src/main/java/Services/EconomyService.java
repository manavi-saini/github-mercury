package Services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Beans.RunManagerInfo;
import Common.CommonMethods;
import Common.InitializeBrowser;
import Common.XPathObjects;

public class EconomyService extends InitializeBrowser{
	
	XPathObjects xpo = new XPathObjects();
	CommonMethods cm = new CommonMethods();
	
	
	public synchronized boolean SelectEconomyTicket(WebDriver driver,RunManagerInfo runManagerInfoObj) throws Exception{
		
		boolean blnFlag = false;
		
		try{
			if(cm.ExplicitWait("XPATH", xpo.EconomyTicket, driver)){
				System.out.println("Economy Ticket Radio button is displayed");
				WebElement economy = driver.findElement(By.xpath(xpo.EconomyTicket));
				if(economy.isSelected()){
					System.out.println("Economy Ticket Radio button is already selected");
					blnFlag = true;
				}
				else{
					economy.click();
					System.out.println("Economy Ticket Radio button is selected");
					blnFlag = true;
				}				
			}
			else{
				System.out.println("Economy Ticket Radio button is not displayed");
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
