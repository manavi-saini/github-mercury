package Common;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import Common.RunManager;

import Common.ReportingFunction;
//import com.charter.sb.utils.WebDriver;

import Common.ThreadExecutor;
import Beans.ExcelTestDetails;
import Beans.RunManagerInfo;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class RunManager extends InitializeBrowser {
	
	public static final String USERNAME = "manavi-saini";
	public static final String ACCESS_KEY = "fa7e10dc-d29c-4429-93f5-ef5f07d791d5";
	public static final String URL = "http://" + USERNAME + ":" + ACCESS_KEY
			+ "@ondemand.saucelabs.com:80/wd/hub";
	
	public static String[] strModValues;
	public int intRowCount=0;
	public int intColCount=0; 
	public String strModuleName=null;
	public String strBrowser=null;
	public String strCaseName=null;
	public final int MAX_Permissible_Thread= 40;
	ReportingFunction repf=new ReportingFunction();
	
	public synchronized String[] FnCom_GetModule(String strWorkBookName)	{
		
		try{
			
			Workbook strWorkBook = Workbook.getWorkbook(new File(strWorkBookName));
			intRowCount=strWorkBook.getSheet("Modules").getRows();
			intColCount=strWorkBook.getSheet("Modules").getColumns();	
			System.out.println("intRowCount: "+intRowCount);
			System.out.println("intColCount: "+intColCount);
			String[] arrColHeader=new String[intColCount];
			int intC=0;
			
			for(int i=0;i<intRowCount;i++)
			{
				String strExecFlag=strWorkBook.getSheet("Modules").getCell(1, i).getContents();
				if(strExecFlag.equalsIgnoreCase("Y"))
				{
					intC=intC+1;
				}
			}
			
			strModValues = new String[intC];
			int Counter=0;
			for(int iLoop=0;iLoop<intRowCount;iLoop++){
				String strExecFlag=strWorkBook.getSheet("Modules").getCell(1, iLoop).getContents();
				if(strExecFlag.equalsIgnoreCase("Y"))
				{
					strModuleName=strWorkBook.getSheet("Modules").getCell(0, iLoop).getContents();	
					strBrowser=strWorkBook.getSheet("Modules").getCell(2, iLoop).getContents();
					moduledetails.put(iLoop, strModuleName);
					
					if(Counter<=intC)
					{
						strModValues[Counter]=strModuleName+";"+ strBrowser;					
						Counter=Counter+1;
					}
				}
			}			
		}
		
		catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return strModValues;
	}
	
	public synchronized ExcelTestDetails getAppDetails(String strWorkBookName){
				
		try{
			Workbook strWorkBook = Workbook.getWorkbook(new File(strWorkBookName));
			Sheet curSheet = strWorkBook.getSheet("Login");
			testDetails.setExecURL((curSheet.getCell(0,1)).getContents());
			System.out.println(curSheet.getCell(0,1).getContents());
			testDetails.setUserID((curSheet.getCell(1,1)).getContents());
			System.out.println(curSheet.getCell(1,1).getContents());
			testDetails.setPassword((curSheet.getCell(2,1)).getContents());
			System.out.println(curSheet.getCell(2,1).getContents());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return testDetails;
	}
	
	public synchronized String FnCom_GetParam(String strWorkBookName, String strModuleName, String strBrowser){
		
		try{
			ExcelTestDetails testDet = getAppDetails(strWorkBookName);
			Workbook strWorkBook = Workbook.getWorkbook(new File(strWorkBookName));
			intRowCount=strWorkBook.getSheet(strModuleName).getRows();
			intColCount=strWorkBook.getSheet(strModuleName).getColumns();
			String[] arrColHeader=new String[intColCount];
			int intScript=0;
			int intFlag=0;
			int threadCounter=0;
			
			for(int iLoop=0;iLoop<intColCount;iLoop++)
			{
				String strFlag=strWorkBook.getSheet(strModuleName).getCell(iLoop, 0).getContents();
				if((strFlag.contains("Script")))
				{
					intScript=iLoop;
				}
				
				if((strFlag.contains("ExecFlag")))
				{
					intFlag=iLoop;			
				}
				
			}
			
			for(int iLoop=0;iLoop<intRowCount;iLoop++)
			{			
				String strExecFlag=strWorkBook.getSheet(strModuleName).getCell(intFlag, iLoop).getContents();
				if(strExecFlag.equalsIgnoreCase("Y") )
				{					
					threadCounter++;				
				}				
			}
			
			System.out.println("No. of threads to be run"+threadCounter);
			
			for(int iLoop=0;iLoop<intRowCount;iLoop++)
			{
				System.out.println(strModuleName);
				String strExecFlag=strWorkBook.getSheet(strModuleName).getCell(intFlag, iLoop).getContents();
				System.out.println(strExecFlag);				
				if(strExecFlag.equalsIgnoreCase("Y"))
				{
					strCaseName = strWorkBook.getSheet(strModuleName).getCell(intScript, iLoop).getContents();
					System.out.println("Test Case Name: "+strCaseName);
					RunManagerInfo runManagerInfo=new RunManagerInfo(strModuleName,strCaseName,strBrowser,testDet);
					runManagerInfoList.add(runManagerInfo);
				}
				
			}
		}

		catch (BiffException e) {

			System.err.println("Run Manager error :");
			System.err.println("	Function : FnCom_GetParam");
			System.err.println("	Message : "+e.getMessage());
			System.err.println("	Cause : "+e.getCause());
			
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
		
			System.err.println("Run Manager error :");
			System.err.println("	Function : FnCom_GetParam");
			System.err.println("	Message : "+e.getMessage());
			System.err.println("	Cause : "+e.getCause());
			e.printStackTrace();
			
		}
		catch(Exception ex){
			System.err.println("Exception occured :");
			System.err.println("	Function : FnCom_GetParam");
			System.err.println("	Message : "+ex.getMessage());
			System.err.println("	Cause : "+ex.getCause());
			ex.printStackTrace();
		}
		
		return strCaseName;	
	}
	
	public synchronized void executeTask(int modLength,List<String> mod){
		Iterator<RunManagerInfo> iter = runManagerInfoList.iterator();
		System.out.println("RunManagerInfoList size: "+runManagerInfoList.size());
		ExecutorService executor = Executors.newFixedThreadPool(MAX_Permissible_Thread);
		RunManagerInfo runMangeInfo = null;
		while(iter.hasNext()){
			runMangeInfo = iter.next();	
			Runnable testObbbj = new ThreadExecutor(runMangeInfo);
			executor.execute(testObbbj);		
		}
		executor.shutdown();
		try {
			executor.awaitTermination(1000, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.out.println("Thread terminates due to " +  e.getMessage());
		}
		/*Iterator<RunManagerInfo> iter1 = runManagerInfoList.iterator();
		System.out.println("THREADS END-------------------------/n----------");
		while(iter1.hasNext()){
			runMangeInfo = iter1.next();			
		}*/
	}
	
	public synchronized void FnCom_ExecuteTC(RunManagerInfo runManagerInfoObj)
	{

		try{
			WebDriver driver=repf.Initialize(runManagerInfoObj);
			String ClassName="TestScripts."+runManagerInfoObj.getstrModuleName();
			System.out.println("Test Class Name: "+ClassName);
			Class cls = Class.forName(ClassName);
			Object obj = cls.newInstance();	
			System.out.println("Browser: "+runManagerInfoObj.getstrBrowser());
			boolean blnFlag=LaunchMercuryApp(runManagerInfoObj,driver);
			
			if(blnFlag){
				System.out.println("Launched Mercury Application");
				Class[] argTypes = { WebDriver.class,RunManagerInfo.class };	
				Method method = cls.getMethod(runManagerInfoObj.getstrCaseName(),argTypes);
				method.invoke(obj,driver,runManagerInfoObj);
				System.out.println("Method name: "+method);
			}   
			else{
				repf.FnCom_AbortExec(driver);
			}

		}
		catch (Exception e){
			System.err.println("	Message : "+e.getMessage());
			System.err.println("	Cause : "+e.getCause());
			e.printStackTrace();
		}
	}
	
	public synchronized boolean LaunchMercuryApp(RunManagerInfo runManagerInfoObj,WebDriver driver){
		boolean isLoginSuccess = false;
		isLoginSuccess=repf.FnLaunchMercuryApp(driver,runManagerInfoObj);  
		return isLoginSuccess;
	}

}
