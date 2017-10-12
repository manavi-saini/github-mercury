package Services;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Beans.RunManagerInfo;
import Common.CommonMethods;
import Common.InitializeBrowser;
import Common.XPathObjects;
import jxl.Workbook;

public class FrequentCall extends InitializeBrowser {
	
	XPathObjects xpo = new XPathObjects();
	CommonMethods cm = new CommonMethods();
	//String workBookName = filePath;
	public static int index = 0;
	int intRowCount = 0;
	int intColCount = 0;
	public String PassengerFirstName = null;
	public String PassengerLastName = null;
	public String CreditCardNumber = null;
	
	
	public synchronized boolean ClickContinue(WebDriver driver,RunManagerInfo runManagerInfoObj) throws Exception{
		
		boolean blnFlag = false;
		
		try{
			
			if(cm.ExplicitWait("XPATH", xpo.ContinueButton, driver)){
				System.out.println("Continue button is displayed");
				WebElement continueButton = driver.findElement(By.xpath(xpo.ContinueButton));
				continueButton.click();
				if(cm.ExplicitWait("XPATH", xpo.SelectFlightImg, driver)){
					System.out.println("Continue button is clicked and User is on Select Flight page");
					blnFlag = true;
				}
				else{
					System.out.println("Continue button is not clicked and User is not on Select OR Book Flight page");
					blnFlag = false;
				}				
			}
			else if(cm.ExplicitWait("XPATH", xpo.ContinueButtonOne, driver)){
				System.out.println("Continue button is displayed");
				WebElement continueButton = driver.findElement(By.xpath(xpo.ContinueButtonOne));
				continueButton.click();
				if(cm.ExplicitWait("XPATH", xpo.BookFlightImg, driver)){
					System.out.println("Continue button is clicked and User is on Book Flight page");
					blnFlag = true;
				}
				else{
					System.out.println("Continue button is not clicked and User is not on Select OR Book Flight page");
					blnFlag = false;
				}	
			}
			else{
				System.out.println("Continue button is not displayed");
				blnFlag = false;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			blnFlag = false;
		}
		return blnFlag;
	}
	
	public synchronized boolean SelectFlight(WebDriver driver,RunManagerInfo runManagerInfoObj) throws Exception{
		
		boolean blnFlag = false;
		
		try{
			
			if(cm.ExplicitWait("XPATH", xpo.SelectFlightRadioButton, driver)){				
				WebElement selectFlight = driver.findElement(By.xpath(xpo.SelectFlightRadioButton));
				System.out.println("Flight: "+selectFlight.getAttribute("value").toString() +"is displayed");
				if(selectFlight.isSelected()){
					System.out.println("Flight: "+ selectFlight.getAttribute("value").toString() +"is already selected");
					blnFlag = true;
				}
				else{
					selectFlight.click();
					System.out.println("Flight: "+ selectFlight.getAttribute("value").toString() +"is selected");
					blnFlag = true;
				}					
			}
			else{
				System.out.println("Flight is not displayed");
				blnFlag = false;
			}
			
			if(cm.ExplicitWait("XPATH", xpo.SelectFlightRadioButtonOne, driver)){				
				WebElement selectFlightOne = driver.findElement(By.xpath(xpo.SelectFlightRadioButtonOne));
				System.out.println("Flight: "+selectFlightOne.getAttribute("value").toString() +"is displayed");
				if(selectFlightOne.isSelected()){
					System.out.println("Flight: "+ selectFlightOne.getAttribute("value").toString() +"is already selected");
					blnFlag = true;
				}
				else{
					selectFlightOne.click();
					System.out.println("Flight: "+ selectFlightOne.getAttribute("value").toString() +"is selected");
					blnFlag = true;
				}					
			}
			else{
				System.out.println("Flight is not displayed");
				blnFlag = false;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			blnFlag = false;
		}
		return blnFlag;
	}
	
public synchronized boolean EnterUserDetails(WebDriver driver,RunManagerInfo runManagerInfoObj) throws Exception{
		
		boolean blnFlag = false;
		
		try{
			System.out.println("In User Details method");
			Workbook strWorkBook = Workbook.getWorkbook(new File(filePath));
			String moduleName = runManagerInfoObj.getstrModuleName();
			intRowCount=strWorkBook.getSheet(moduleName).getRows();
			intColCount=strWorkBook.getSheet(moduleName).getColumns();
			String strTestCaseName=runManagerInfoObj.getstrCaseName();

			for(int iLoop=0;iLoop<intRowCount;iLoop++){	
				if(strWorkBook.getSheet(moduleName).getCell(1, iLoop).getContents().contains(strTestCaseName)){
					index = strWorkBook.getSheet(moduleName).getCell(1, iLoop).getRow();
					break;
				}
			}


			System.out.println(index);
			
			PassengerFirstName = strWorkBook.getSheet(moduleName).getCell(10, index).getContents();
			PassengerLastName = strWorkBook.getSheet(moduleName).getCell(11, index).getContents();
			CreditCardNumber = strWorkBook.getSheet(moduleName).getCell(12, index).getContents();
			
			if(cm.ExplicitWait("XPATH", xpo.FirstName, driver)){
				System.out.println("First Name field present");
				WebElement firstName = driver.findElement(By.xpath(xpo.FirstName));
				Thread.sleep(3000);
				firstName.sendKeys(PassengerFirstName);
				System.out.println("Passenger First Name entered: "+PassengerFirstName);
				blnFlag = true;
			}
			else{
				System.out.println("First Name field not present");
				blnFlag = false;
			}
		
			if(cm.ExplicitWait("XPATH", xpo.LastName, driver)){
				System.out.println("Last Name field present");
				WebElement lastName = driver.findElement(By.xpath(xpo.LastName));
				Thread.sleep(3000);
				lastName.sendKeys(PassengerLastName);
				System.out.println("Passenger Last Name entered: "+PassengerLastName);
				blnFlag = true;
			}
			else{
				System.out.println("Last Name field not present");
				blnFlag = false;
			}
			
			if(cm.ExplicitWait("XPATH", xpo.CardNumber, driver)){
				System.out.println("Card Number field present");
				WebElement cardNum = driver.findElement(By.xpath(xpo.CardNumber));
				Thread.sleep(3000);
				cardNum.sendKeys(CreditCardNumber);
				System.out.println("Card Number entered: "+CreditCardNumber);
				blnFlag = true;
			}
			else{
				System.out.println("Card Number field not present");
				blnFlag = false;
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
			blnFlag = false;
		}
		return blnFlag;
	}

public synchronized boolean ClickSecurePurchase(WebDriver driver,RunManagerInfo runManagerInfoObj) throws Exception{
	
	boolean blnFlag = false;
	
	try{
		
		if(cm.ExplicitWait("XPATH", xpo.SecurePurchase, driver)){
			System.out.println("Secure Purchase button is displayed");
			WebElement SecurePurchase = driver.findElement(By.xpath(xpo.SecurePurchase));
			SecurePurchase.click();
			if(cm.ExplicitWait("XPATH", xpo.FlightConfirmImg, driver) && cm.ExplicitWait("XPATH", xpo.FlightBookConfirmMessage, driver)){
				WebElement FlightBookConfirmMessage = driver.findElement(By.xpath(xpo.FlightBookConfirmMessage));
				System.out.println("Secure Purchase button is clicked and User is on Flight Confirmation page. Message displayed: "+FlightBookConfirmMessage.getText());
				blnFlag = true;
			}
			else{
				System.out.println("Secure Purchase button is not clicked and User is not on Flight Confirmation page");
				blnFlag = false;
			}				
		}
		else{
			System.out.println("Secure Purchase button is not displayed");
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
