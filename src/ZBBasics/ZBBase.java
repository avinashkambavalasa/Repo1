package ZBBasics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;


public class ZBBase {
	
		public static WebDriver driver;	
		public static Properties prop;
	
		public ZBBase() throws IOException {
			try {
				 prop = new Properties();
				 FileInputStream ip = new FileInputStream("D:/Avinash/Selenium/Workspace/ZeroBank/src/ZBBasics/config.properties");
				 prop.load(ip);
			}catch (FileNotFoundException e) {
					 e.printStackTrace();
			}catch (IOException e) {
					 e.printStackTrace();
			}
		
			System.setProperty(ZBConstants.key, ZBConstants.value);
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("incognito");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);		
			
			String implicittime = prop.getProperty("implicit_wait_time");
			long implicitwaittime = Long.parseLong(implicittime);
		
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(implicitwaittime,TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(ZBConstants.Page_Load_TimeOut, TimeUnit.SECONDS);
			 
		}
		
		
		public static void initialization() throws InterruptedException {
			
			String baseurl = prop.getProperty("URL");
			driver.get(baseurl);
		
			driver.findElement(By.id("signin_button")).click();
			driver.findElement(By.id("user_login")).sendKeys(prop.getProperty("username"));
			driver.findElement(By.id("user_password")).sendKeys(prop.getProperty("password"));
			driver.findElement(By.name("submit")).click();
			
		}
		
		
}
