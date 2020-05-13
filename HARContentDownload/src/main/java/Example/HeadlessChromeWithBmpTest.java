package Example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.proxy.CaptureType;

public class HeadlessChromeWithBmpTest {
	
	
	static ReadProperties readProperties = new ReadProperties();
	private static WebDriver driver = null;
	BrowserMobProxy proxy1 = null;
	//private static final String CHROME_PATH = "D:/Main_Folder/code/chromedriver_win32/chromedriver.exe";
	private static final String CHROME_PATH = "driver/chromedriver.exe";

	private HeadlessChromeWithBmpTest() {
		// TODO Auto-generated constructor stub
	}
	
	public static String readDriver(){
		
        ClassLoader classLoader = new HeadlessChromeWithBmpTest().getClass().getClassLoader();
 
        File file = new File(classLoader.getResource(CHROME_PATH).getFile());
        System.out.print("================"+file.getAbsolutePath());
        return file.getAbsolutePath();
	}

	public static WebDriver getWebDriver(DesiredCapabilities capabilities) throws CloneNotSupportedException {
		if (driver == null) {
			synchronized (HeadlessChromeWithBmpTest.class) {
				if (driver == null) {
					System.setProperty("webdriver.chrome.driver", readDriver());
					driver = new ChromeDriver(capabilities);
				}
			}
		}
		
		return driver;
	}
	
	public static void closeDriver() throws CloneNotSupportedException{
		driver.close();
		driver.quit();
	}
	
	public  void creteProxy() throws IOException, CloneNotSupportedException{
		proxy1 = new BrowserMobProxyServer();
		proxy1.setTrustAllServers(true);
		proxy1.start(0);
		proxy1.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
		Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy1);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--headless");

		// configure it as a desired capability
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		
		proxy1.newHar("Infosys.com");
		WebDriver driver = HeadlessChromeWithBmpTest.getWebDriver(capabilities);
		proxy1.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
		driver.get((String)readProperties.getPropValues().get("url"));
		
		
	}
	
	public static void seleniumAction(String xpath) throws CloneNotSupportedException{
		driver.findElement(By.id(xpath)).click();
		driver.findElement(By.name("k")).sendKeys("performance");
		driver.findElement(By.name("k")).sendKeys(Keys.RETURN);
	}
	
	public void storeHarContent(String path) throws IOException{
		net.lightbody.bmp.core.har.Har har = proxy1.getHar();
		FileOutputStream fos = new FileOutputStream(path);
		har.writeTo(fos);
		fos.close();
	}
	
	public void closeAll() throws CloneNotSupportedException{
		proxy1.abort();
		closeDriver();
	}
	
	public static void clearHistory() throws InterruptedException {
		driver.manage().deleteAllCookies();
		//Code below to physically open the browser and clean it
		/*driver.get("chrome://settings/clearBrowserData") ;
		Thread.sleep(3000);
		driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);*/
		
	}

	public static void main(String[] args) throws InterruptedException {
readDriver();
		HeadlessChromeWithBmpTest t = new HeadlessChromeWithBmpTest();
		try {				
			t.creteProxy();
			Thread.sleep(3000);
			t.storeHarContent("D:/infosys1.har");
			Thread.sleep(3000);
			clearHistory();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {				
			//creteProxy();
			seleniumAction((String)readProperties.getPropValues().get("action1"));
			Thread.sleep(3000);
			t.storeHarContent("D:/infosys2.har");
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally{
			try {
				t.closeAll();
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// WebDriverWait wait = new WebDriverWait(driver1,
		// Duration.ofSeconds(10));

		// triets to open chercher.tech page
		
		// driver1.manage().window().maximize();

		
	}

}
