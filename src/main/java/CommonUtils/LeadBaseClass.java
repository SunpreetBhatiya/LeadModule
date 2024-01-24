package CommonUtils;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class LeadBaseClass {
	
	PropertiesFileUtils utils = new PropertiesFileUtils();
	WebDriver cd;
	
	@BeforeSuite
	public void BS()
	{
		System.out.println("Connect to database");
	}
	
	@BeforeClass//browser selection
	public void BC() throws IOException
	{
		String urll = utils.getdatafromPropertFile("url");
		String Browser = utils.getdatafromPropertFile("browser");
		if(Browser.equalsIgnoreCase("Chrome"))
		{
			cd = new ChromeDriver();
		}
		else if(Browser.equalsIgnoreCase("Edge"))
		{
			cd = new EdgeDriver();
		}
		else
		{
			
			cd = new FirefoxDriver();
			System.out.println("DefaultBrowser");
		}
		
	
		cd.manage().window().maximize();
		
        cd.get(utils.getdatafromPropertFile("url"));
		
	}
	
	
	@BeforeMethod
	public void BM() throws IOException, InterruptedException
	{
		cd.findElement(By.name("user_name")).sendKeys(utils.getdatafromPropertFile("username"));
		Thread.sleep(2000);
		cd.findElement(By.name("user_password")).sendKeys(utils.getdatafromPropertFile("password"));
		Thread.sleep(2000);
		cd.findElement(By.id("submitButton")).click();
		
	}
	
	@Test
	public void leadTest() throws IOException, InterruptedException
	{
	
	cd.findElement(By.xpath("(//a[text()='Leads'])[1]")).click();
	Thread.sleep(2000);
	
	
	cd.findElement(By.cssSelector("img[title='Create Lead...']")).click();
	Thread.sleep(2000);
	
	cd.findElement(By.cssSelector("input[name='firstname']")).sendKeys(utils.getdatafromPropertFile("fname"));
	Thread.sleep(2000);
	cd.findElement(By.name("lastname")).sendKeys(utils.getdatafromPropertFile("lname"));
	Thread.sleep(2000);
	cd.findElement(By.name("company")).sendKeys(utils.getdatafromPropertFile("company"));
	Thread.sleep(2000);
	
	cd.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
	Thread.sleep(2000);
	
	WebElement dd = cd.findElement(By.name("assigned_group_id"));
	Select s = new Select(dd);
	s.selectByVisibleText(utils.getdatafromPropertFile("dd"));
	
	cd.findElement(By.cssSelector("input[class='crmbutton small save']")).click();
	
	
	}
	
	@AfterMethod
	public void AM()
	{
		WebElement ids1 = cd.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a=new Actions(cd);
		a.moveToElement(ids1).perform();
		cd.findElement(By.xpath("//a[text()='Sign Out']")).click(); 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	@AfterSuite
	public void AS()
	{
		System.out.println("Disconnect from database");
	}

}
