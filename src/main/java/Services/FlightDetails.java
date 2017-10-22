package Services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Beans.RunManagerInfo;
import Common.CommonMethods;
import Common.InitializeBrowser;
import Common.XPathObjects;
import jxl.Sheet;
import jxl.Workbook;

public class FlightDetails extends InitializeBrowser {
	
	
	//String workBookName = filePath;
	int intRowCount = 0;
	int intColCount = 0;
	public String NoOfPassengers = null;
	public String DepartureLocation = null;
	public String DepartureMonth = null;
	public String DepartureDate = null;
	public String ArrivalLocation = null;
	public String ArrivalMonth = null;
	public String ArrivalDate = null;
	public static int index = 0;
	XPathObjects xpo = new XPathObjects();
	CommonMethods cm = new CommonMethods();
	
	public synchronized boolean EnterFlightDetails(WebDriver driver,RunManagerInfo runManagerInfoObj) throws Exception{
		
		boolean blnFlag = false;

		try{
			
			System.out.println("In Flight Details method");
			Workbook strWorkBook = Workbook.getWorkbook(new File(filePath));
			String moduleName = runManagerInfoObj.getstrModuleName();
			System.out.println("Module Name New: "+moduleName);
			intRowCount=strWorkBook.getSheet(moduleName).getRows();
			intColCount=strWorkBook.getSheet(moduleName).getColumns();
			String strTestCaseName=runManagerInfoObj.getstrCaseName();
			System.out.println("Test case name: "+strTestCaseName);

			for(int iLoop=0;iLoop<intRowCount;iLoop++){	
				System.out.println("Row content: "+strWorkBook.getSheet(moduleName).getCell(1, iLoop).getContents());
				if(strWorkBook.getSheet(moduleName).getCell(1, iLoop).getContents().contains(strTestCaseName)){
					index = strWorkBook.getSheet(moduleName).getCell(1, iLoop).getRow();
					break;
				}
			}


			System.out.println(index);

			NoOfPassengers = strWorkBook.getSheet(moduleName).getCell(3, index).getContents();
			System.out.println("Passengers: "+NoOfPassengers);
			DepartureLocation = strWorkBook.getSheet(moduleName).getCell(4, index).getContents();
			System.out.println("Dep Loc: "+DepartureLocation);
			DepartureMonth = strWorkBook.getSheet(moduleName).getCell(5, index).getContents();
			DepartureDate = strWorkBook.getSheet(moduleName).getCell(6, index).getContents();
			ArrivalLocation = strWorkBook.getSheet(moduleName).getCell(7, index).getContents();
			ArrivalMonth = strWorkBook.getSheet(moduleName).getCell(8, index).getContents();
			ArrivalDate = strWorkBook.getSheet(moduleName).getCell(9, index).getContents();


				
				if(cm.ExplicitWait("XPATH", xpo.NoOfPassengers, driver)){
					System.out.println("Passengers field present");
					Select number = new Select(driver.findElement(By.xpath(xpo.NoOfPassengers)));
					Thread.sleep(3000);
					number.selectByValue(NoOfPassengers);
					System.out.println("No of passengers selected: "+NoOfPassengers);
					blnFlag = true;
				}
				else{
					System.out.println("Passengers field not present");
					blnFlag = false;
				}
			
				if(cm.ExplicitWait("XPATH", xpo.DepartureLoc, driver)){
					System.out.println("Departure Location field present");
					Select deploc = new Select(driver.findElement(By.xpath(xpo.DepartureLoc)));
					Thread.sleep(3000);
					deploc.selectByValue(DepartureLocation);
					System.out.println("Departure Location selected: "+DepartureLocation);
					blnFlag = true;
				}
				else{
					System.out.println("Departure Location field not present");
					blnFlag = false;
				}
				
				if(cm.ExplicitWait("XPATH", xpo.DepartureMonth, driver)){
					System.out.println("Departure Month field present");
					Select depmon = new Select(driver.findElement(By.xpath(xpo.DepartureMonth)));
					Thread.sleep(3000);
					depmon.selectByVisibleText(DepartureMonth);
					System.out.println("Departure Month selected: "+DepartureMonth);
					blnFlag = true;
				}
				else{
					System.out.println("Departure Month field not present");
					blnFlag = false;
				}

				if(cm.ExplicitWait("XPATH", xpo.DepartureDate, driver)){
					System.out.println("Departure Date field present");
					Select depdate = new Select(driver.findElement(By.xpath(xpo.DepartureDate)));
					Thread.sleep(3000);
					depdate.selectByValue(DepartureDate);
					System.out.println("Departure Date selected: "+DepartureDate);
					blnFlag = true;
				}
				else{
					System.out.println("Departure Date field not present");
					blnFlag = false;
				}
				
				if(cm.ExplicitWait("XPATH", xpo.ArrivalLoc, driver)){
					System.out.println("Arrival Location field present");
					Select arrloc = new Select(driver.findElement(By.xpath(xpo.ArrivalLoc)));
					Thread.sleep(3000);
					arrloc.selectByValue(ArrivalLocation);
					System.out.println("Arrival Location selected: "+ArrivalLocation);
					blnFlag = true;
				}
				else{
					System.out.println("Arrival Location field not present");
					blnFlag = false;
				}

				if(cm.ExplicitWait("XPATH", xpo.ArrivalMonth, driver)){
					System.out.println("Arrival Month field present");
					Select arrmon = new Select(driver.findElement(By.xpath(xpo.ArrivalMonth)));
					Thread.sleep(3000);
					arrmon.selectByVisibleText(ArrivalMonth);
					System.out.println("Arrival Month selected: "+ArrivalMonth);
					blnFlag = true;
				}
				else{
					System.out.println("Arrival Month field not present");
					blnFlag = false;
				}
				
				if(cm.ExplicitWait("XPATH", xpo.ArrivalDate, driver)){
					System.out.println("Arrival Date field present");
					Select arrdate = new Select(driver.findElement(By.xpath(xpo.ArrivalDate)));
					Thread.sleep(3000);
					arrdate.selectByValue(ArrivalDate);
					System.out.println("Arrival Date selected: "+ArrivalDate);
					blnFlag = true;
				}
				else{
					System.out.println("Arrival Date field not present");
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
