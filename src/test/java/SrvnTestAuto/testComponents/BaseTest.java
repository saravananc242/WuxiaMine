package SrvnTestAuto.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SrvnTestAuto.reusables.TestReporter;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	protected static ThreadLocal<WebDriver> driverThreads = new ThreadLocal<>();
	public Properties prop;
	
	
	public WebDriver initializeDriver() throws FileNotFoundException, IOException {
		WebDriver driver=null;
		prop = new Properties();
		prop.load(new FileInputStream(System.getProperty("user.dir")+"/src/main/java/SrvnTestAuto/resources/Environment.properties"));
		
		String browser = prop.getProperty("browser");
		
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().clearDriverCache().setup();;
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("user-agent=\"Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)\"");
			//options.addArguments("start-maximized");
			driver= new ChromeDriver(options);
		}else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().clearDriverCache().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}
		driver.manage().window().maximize();
		return driver;
		
	}
	
	@BeforeMethod(alwaysRun = true)
	public void navigateToApp() throws FileNotFoundException, IOException {
		
		driverThreads.set(initializeDriver());
		//ThreadGuard.protect(driver);
		
		getDriver().get(prop.getProperty("baseUrl"));
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		System.out.println("driver thread instance to close: "+Thread.currentThread().getId());
		getDriver().close();
		driverThreads.remove();

	}
	
	@AfterSuite
	public void afSuite() {
		TestReporter.report.flush();
		
	}

	
	public static WebDriver getDriver() {
		return driverThreads.get();
	}
	
	public List<HashMap<String, String>> retrieveDataFromJson(String filePath) throws IOException {
		
		String jsonData = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> dataList = mapper.readValue(jsonData, new TypeReference<List<HashMap<String,String>>>() {
		}); 
		return dataList;
	}
	
	@BeforeSuite(alwaysRun = true)
	public TestReporter setUpReporter() {
		
		System.setProperty("logFile", System.getProperty("user.dir")+"/Reports/execution.log");
		System.out.println(System.getProperty("logFile"));
		
		TestReporter reporter = new TestReporter();
		ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir")+"//Reports//index.html");
		
		spark.config().setDocumentTitle("Srvn Report");
		spark.config().setReportName("Auto Report");
		spark.config().setTheme(Theme.DARK);
		
		TestReporter.report = new ExtentReports();
		TestReporter.report.attachReporter(spark);
		
		return reporter;
	}
	
	
	public TestReporter startTest(String testName) {
		TestReporter reporter = new TestReporter();
		
		reporter.test = TestReporter.report.createTest(testName);
		return reporter;
		
		
	}
}
