package Common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

import Common.RunManager;

import Beans.ExcelTestDetails;

@Test
public class Main extends InitializeBrowser {

	//static Logger logger = LogManager.getLogger(Main.class);
		public static ExcelTestDetails testDetails = new ExcelTestDetails();
		RunManager runmgr = new RunManager();
		public String[] Mod;
		public ArrayList modNameList = new ArrayList<String>();
		String strModuleName;
		String strBrowser;
		//private static final Logger logger = LogManager.getLogger(Main.class);
				
		public static String getPropValues(String propertyName) {
			return prop.getProperty(propertyName);
		}
		
		public synchronized void getExcelValues() throws InvalidFormatException, IOException{
			
			try{
				filePath = getPropValues("ExcelPath");
				System.out.println("Filepath: "+filePath);
				//FileInputStream fis=new FileInputStream(filePath);
				//Workbook wb=WorkbookFactory.create(fis);
				
				Mod=runmgr.FnCom_GetModule(filePath);
				
				System.out.println(Mod.length);
				
				for(int i=0;i<Mod.length;i++)
				{
					System.out.println(Mod[i]);
					
					if(Mod[i].contains(";"))
					{
						String[] s=Mod[i].split(";");
						System.out.println(s.length);
						strModuleName=s[0];
						System.out.println(strModuleName);
						strBrowser=s[1];
						System.out.println(strBrowser);
						modNameList.add(strModuleName);						
					}
					runmgr.FnCom_GetParam(filePath, strModuleName, strBrowser);
				}
				
				runmgr.executeTask(Mod.length,modNameList);
						
			}
			catch (Exception e){
				System.out.println("Exception: "+e);
			}
					
		}
}
