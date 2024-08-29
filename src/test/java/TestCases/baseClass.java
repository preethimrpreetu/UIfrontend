package TestCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class baseClass {
	public static WebDriver driver;
	public Logger log;
	public Properties pro;
	
	@BeforeClass(groups= {"sanity", "regression"})

	@Parameters({"browser", "os"})
	public void setUp(String br, String os) throws IOException {
		FileReader fr = new FileReader("./src//test//resources//config.properties");
		pro=new Properties();
		pro.load(fr);
		
		log=LogManager.getLogger(this.getClass());
		
		if(pro.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cap = new DesiredCapabilities();
			//OS
			if(os.equalsIgnoreCase("windows"))
			{
			cap.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				cap.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("no matching found");
				return;
			}
			
			//browser
			
			switch(br)
			{
			case "chrome":
				cap.setBrowserName("chrome");
				break;	
				
			case "edge":
				cap.setBrowserName("edge");
				break;
				
			default:
				System.out.println("invalid browser");
				return;
			}
			
			driver= new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
			
			
		}
		
		if(pro.getProperty("execution_env").equalsIgnoreCase("local"))
		{
		
		switch(br.toLowerCase())
		{
		case "chrome":
		driver = new ChromeDriver();
		break;
		
		case "edge":
			driver = new EdgeDriver();
			break;
			
		case "firefox":
			driver = new FirefoxDriver();
			break;
			default:
				System.out.println("invalid browser");
				return;
				
		}
		}
				
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(pro.getProperty("AppUrl"));
		driver.manage().window().maximize();
		
	}
	public String randomString() {
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public String randomNumber() {
		String generatedString=RandomStringUtils.randomNumeric(10);
		return generatedString;
	}

	public String randomAlphaNumeric() {
		String generatedNo=RandomStringUtils.randomNumeric(3);
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return (generatedString+"@"+generatedNo);
	}
	
	public String captureScreen(String tname) {
		String timeStamp= new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts= (TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		String targetfilePath=System.getProperty("user.dir")+"\\Screenshots\\"+ tname +"_"+ timeStamp +".png";
		File target = new File(targetfilePath);
		src.renameTo(target);
		return targetfilePath;
	}
	
	@AfterClass(groups= {"sanity", "regression"})

	public void tearDown() {
		driver.quit();
	}



}
