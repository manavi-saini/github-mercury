package Common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Beans.ExcelTestDetails;
import Beans.RunManagerInfo;

public class CommonMethods extends InitializeBrowser{
	
	public boolean ExplicitWait(String type,String constant,WebDriver driver){
		
		boolean flag = false;
		
		try{
			System.out.println("Inside method: ExplicitWait");
			WebDriverWait wait = new WebDriverWait(driver,60,1000);
			
			if(type.equalsIgnoreCase("ID")){
				WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(constant)));
				if(element.isDisplayed())
					flag = true;				
			}
			else if(type.equalsIgnoreCase("NAME")){
				WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(constant)));
				if(element.isDisplayed())
					flag = true;
			}
			else if(type.equalsIgnoreCase("CLASSNAME")){
				WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(constant)));
				if(element.isDisplayed())
					flag = true;
			}
			else if(type.equalsIgnoreCase("CSSSELECTOR")){
				WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(constant)));
				if(element.isDisplayed())
					flag = true;
			}
			else if(type.equalsIgnoreCase("LINKTEXT")){
				WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(constant)));
				if(element.isDisplayed())
					flag = true;
			}
			else if(type.equalsIgnoreCase("PARTIALLINKTEXT")){
				WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(constant)));
				if(element.isDisplayed())
					flag = true;
			}
			else if(type.equalsIgnoreCase("TAGNAME")){
				WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(constant)));
				if(element.isDisplayed())
					flag = true;
			}
			else if(type.equalsIgnoreCase("XPATH")){
				WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(constant)));
				if(element.isDisplayed())
					flag = true;
			}
			
		}
		catch(Exception e){
			System.out.println("Element not found: "+e);
		}
		return flag;
	}



}
