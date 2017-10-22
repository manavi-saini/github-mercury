package TestScripts;

import Common.RunManager;

import Common.ReportingFunction;

import Services.BusinessService;
import Services.EconomyService;
import Services.FirstClassService;
import Services.FlightDetails;
import Services.FrequentCall;
import Services.RoundTripTicket;
import Services.OneWayTicket;

import Beans.RunManagerInfo;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Common.CommonMethods;

import Common.InitializeBrowser;
import Common.Listerners;


public class Flights extends InitializeBrowser {
	RunManager runmngr = new RunManager();
	ReportingFunction repfn = new ReportingFunction();
	CommonMethods cnf = new CommonMethods();
	BusinessService bs=new BusinessService();
	EconomyService es=new EconomyService();
	FirstClassService fcs=new FirstClassService();
	RoundTripTicket rt=new RoundTripTicket();
	OneWayTicket owt=new OneWayTicket();
	FlightDetails flt=new FlightDetails();
	FrequentCall fc = new FrequentCall();
	public static boolean blnFlag = false;
	long startTime = System.currentTimeMillis();
	long endtime = System.currentTimeMillis();

	@Test
	public synchronized void bookEconomyRoundTrip(WebDriver driver, RunManagerInfo runManagerInfoObj) throws Exception {

		try{

			for (int iCount = 0; iCount < 10; iCount++) {
				switch (iCount) {
					case 0:
						System.out.println("Case 0");
						blnFlag=repfn.UserLogin(driver,runManagerInfoObj);
						break;
					case 1:
						System.out.println("Case 1");
						blnFlag=rt.SelectRoundTrip(driver,runManagerInfoObj);
						break;
					case 2:
						System.out.println("Case 2");
						blnFlag=flt.EnterFlightDetails(driver,runManagerInfoObj);
						break;
					case 3:
						System.out.println("Case 3");
						blnFlag=es.SelectEconomyTicket(driver,runManagerInfoObj);
						break;
					case 4:
						System.out.println("Case 4");
						blnFlag=fc.ClickContinue(driver,runManagerInfoObj);
						break;
					case 5:
						System.out.println("Case 5");
						blnFlag=fc.SelectFlight(driver,runManagerInfoObj);
						break;
					case 6:
						System.out.println("Case 6");
						blnFlag=fc.ClickContinue(driver,runManagerInfoObj);
						break;
					case 7:
						System.out.println("Case 7");
						blnFlag=fc.EnterUserDetails(driver,runManagerInfoObj);
						break;
					case 8:
						System.out.println("Case 8");
						blnFlag=fc.ClickSecurePurchase(driver,runManagerInfoObj);
						break;
					case 9:
						System.out.println("Case 9");
						blnFlag=repfn.Logout(driver,runManagerInfoObj);
						break;
				}
				if (!blnFlag) {
					repfn.FnCom_AbortExec(driver);
					break;
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		
		}
	}
	
	@Test
	public synchronized void bookBusinessRoundTrip(WebDriver driver, RunManagerInfo runManagerInfoObj) throws Exception {

		try{

			for (int iCount = 0; iCount < 10; iCount++) {
				switch (iCount) {
					case 0:
						System.out.println("Case 0");
						blnFlag=repfn.UserLogin(driver,runManagerInfoObj);
						break;
					case 1:
						System.out.println("Case 1");
						blnFlag=rt.SelectRoundTrip(driver,runManagerInfoObj);
						break;
					case 2:
						System.out.println("Case 2");
						blnFlag=flt.EnterFlightDetails(driver,runManagerInfoObj);
						break;
					case 3:
						System.out.println("Case 3");
						blnFlag=bs.SelectBusinessTicket(driver,runManagerInfoObj);
						break;
					case 4:
						System.out.println("Case 4");
						blnFlag=fc.ClickContinue(driver,runManagerInfoObj);
						break;
					case 5:
						System.out.println("Case 5");
						blnFlag=fc.SelectFlight(driver,runManagerInfoObj);
						break;
					case 6:
						System.out.println("Case 6");
						blnFlag=fc.ClickContinue(driver,runManagerInfoObj);
						break;
					case 7:
						System.out.println("Case 7");
						blnFlag=fc.EnterUserDetails(driver,runManagerInfoObj);
						break;
					case 8:
						System.out.println("Case 8");
						blnFlag=fc.ClickSecurePurchase(driver,runManagerInfoObj);
						break;
					case 9:
						System.out.println("Case 9");
						blnFlag=repfn.Logout(driver,runManagerInfoObj);
						break;
				}
				if (!blnFlag) {
					repfn.FnCom_AbortExec(driver);
					break;
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
