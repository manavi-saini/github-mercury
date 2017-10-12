package Beans;

public class RunManagerInfo {
	public RunManagerInfo(String strModuleName,String strCaseName, String strBrowser, ExcelTestDetails testDetails) {
		this.strModuleName = strModuleName;
		this.strCaseName = strCaseName;
		this.strBrowser = strBrowser; 
		this.testDetailsOne = testDetails;
	}
		
		String strModuleName ;
		String strCaseName ;
		String strBrowser ;
		ExcelTestDetails testDetailsOne;
		
		
		public String getstrModuleName(){
			return strModuleName;
		}
		
		public void setstrModuleName(String strModuleName){
			this.strModuleName = strModuleName;
		}
		
		public String getstrCaseName(){
			return strCaseName;
		}
		
		public void setstrCaseName(String strCaseName){
			this.strCaseName = strCaseName;
		}
		
		public String getstrBrowser(){
			return strBrowser;
		}
		
		public void setstrBrowser(String strBrowser){
			this.strBrowser = strBrowser;
		}
		
		public ExcelTestDetails getExcelTestDetails(){
			return testDetailsOne;
		}
		
		public void setExcelTestDetails(ExcelTestDetails testDetails){
			this.testDetailsOne = testDetails;
		}
		
	}

