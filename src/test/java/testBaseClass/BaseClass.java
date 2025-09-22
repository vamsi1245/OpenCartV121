package testBaseClass;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.HasFullPageScreenshot;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;



public class BaseClass{
	public static WebDriver driver;
	public Properties p;

	@BeforeClass(groups= {"sanity","regression","master"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException {
		
		FileReader fr=new FileReader("C:\\Users\\vamsi\\eclipse-workspace\\OpenCartV121\\src\\test\\resources\\config.properties");
		p=new Properties();
        p.load(fr);		
		switch(br.toLowerCase()) {
		   case"chrome":driver=new ChromeDriver();break;
		   case"edge":driver=new ChromeDriver();break;
		   case"firefox":driver=new ChromeDriver();break;
		   default:System.out.println("browser not supported");
		   return;
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
		driver.get(p.getProperty("baseURL"));
		driver.manage().window().maximize();

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	public String randomeString() {

		@SuppressWarnings("deprecation")
		String generatedString = RandomStringUtils.randomAlphabetic(10);
		return (generatedString);
	}

	public String randomNumber() {

		@SuppressWarnings("deprecation")
		String generatedString2 = RandomStringUtils.randomNumeric(10);
		return (generatedString2);
	}

	public String randomAlphaNumeric() {

		@SuppressWarnings("deprecation")
		String str = RandomStringUtils.randomAlphabetic(4);
		@SuppressWarnings("deprecation")
		String num = RandomStringUtils.randomNumeric(3);
		return (str + "@" + num);
	}
	
	public String captureScreen(String tname) throws IOException{
		String timestamp=new java.text.SimpleDateFormat("yyyyMMddhhmmss").format(new java.util.Date());
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(org.openqa.selenium.OutputType.FILE);
		String destination=System.getProperty("user.dir") + "\\screenshots\\"+tname+"_"+timestamp+".png";
		File target=new File(destination);
		src.renameTo(target);
		return destination;
	}
}
