package Common;

import Beans.RunManagerInfo;

public class ThreadExecutor implements Runnable{
	
	public static final String USERNAME = "manavi-saini";
	public static final String ACCESS_KEY = "fa7e10dc-d29c-4429-93f5-ef5f07d791d5";
	public static final String URL = "http://" + USERNAME + ":" + ACCESS_KEY
			+ "@ondemand.saucelabs.com:80/wd/hub";
	
	String strModuleName ;
	String strManualTC  ;
	String strCaseName ;
	String strBrowser ;
	RunManagerInfo runManagerInfoObj;
	
	
	public ThreadExecutor(RunManagerInfo runmangerInfo) {
		this.strModuleName = runmangerInfo.getstrModuleName();
		this.strCaseName = runmangerInfo.getstrCaseName();
		this.strBrowser = runmangerInfo.getstrBrowser();
		this.runManagerInfoObj = runmangerInfo;
	}
	
	public synchronized void run() {
		RunManager runManggerObj = new RunManager();		
		try {
			System.out.println("Thread state " +Thread.currentThread().getState());
			System.out.println("Thread Name " +Thread.currentThread().getName());
			System.out.println("Thread run() Method");
			runManggerObj.FnCom_ExecuteTC(runManagerInfoObj);			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}
