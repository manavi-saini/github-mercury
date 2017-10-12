package Services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Beans.RunManagerInfo;
import Common.CommonMethods;
import Common.InitializeBrowser;
import Common.XPathObjects;

public class BusinessService extends InitializeBrowser{
	
	XPathObjects xpo = new XPathObjects();
	CommonMethods cm = new CommonMethods();
	
	public synchronized boolean SelectBusinessTicket(WebDriver driver,RunManagerInfo runManagerInfoObj) throws Exception{
		
		boolean blnFlag = false;
		
		try{
			if(cm.ExplicitWait("XPATH", xpo.BusinessTicket, driver)){
				System.out.println("Business Ticket Radio button is displayed");
				WebElement business = driver.findElement(By.xpath(xpo.BusinessTicket));
				if(business.isSelected()){
					System.out.println("Business Ticket Radio button is already selected");
					blnFlag = true;
				}
				else{
					business.click();
					System.out.println("Business Ticket Radio button is selected");
					blnFlag = true;
				}				
			}
			else{
				System.out.println("Business Ticket Radio button is not displayed");
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
