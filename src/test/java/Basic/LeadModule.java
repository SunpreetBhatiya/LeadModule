package Basic;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import CommonUtils.PropertiesFileUtils;

public class LeadModule {
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		PropertiesFileUtils utils = new PropertiesFileUtils();
		utils.getdatafromPropertFile("browser");
		
		
		WebDriver cd = new ChromeDriver();
		
		cd.manage().window().maximize();
		
		cd.get(utils.getdatafromPropertFile("url"));
		
		cd.findElement(By.name("user_name")).sendKeys(utils.getdatafromPropertFile("username"));
		Thread.sleep(2000);
		cd.findElement(By.name("user_password")).sendKeys(utils.getdatafromPropertFile("password"));
		Thread.sleep(2000);
		cd.findElement(By.id("submitButton")).click();
		
		cd.findElement(By.xpath("(//a[text()='Leads'])[1]")).click();
		
		
		cd.findElement(By.cssSelector("img[title='Create Lead...']")).click();
		
		cd.findElement(By.cssSelector("input[name='firstname']")).sendKeys(utils.getdatafromPropertFile("fname"));
		cd.findElement(By.name("lastname")).sendKeys(utils.getdatafromPropertFile("lname"));
		cd.findElement(By.name("company")).sendKeys(utils.getdatafromPropertFile("company"));
		
		cd.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
		
		WebElement dd = cd.findElement(By.name("assigned_group_id"));
		Select s = new Select(dd);
		s.selectByVisibleText(utils.getdatafromPropertFile("dd"));
		
		cd.findElement(By.cssSelector("input[class='crmbutton small save']")).click();
		
		WebElement ids1 = cd.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a=new Actions(cd);
		a.moveToElement(ids1).perform();
		cd.findElement(By.xpath("//a[text()='Sign Out']")).click(); 
		
		
		
		
		
	}

}
