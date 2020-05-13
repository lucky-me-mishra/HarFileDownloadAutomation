package Example;

import java.io.FileOutputStream;

import org.browsermob.proxy.ProxyServer;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import  org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.proxy.CaptureType;
public class NewTest {

	/*
	 ProxyServer server;
	 String strFilePath = "D:/test.har";
	public NewTest() {
		// TODO Auto-generated constructor stub
	}
	
	@BeforeSuite
    public void initDriver() throws Exception {
		// start the proxy
	    server = new ProxyServer(4444);
	    
	    server.start();
	    System.setProperty("webdriver.chrome.driver", "D:/Main_Folder/code/chromedriver_win32/chromedriver.exe");
    }
	
	@Test
	public void test() throws Exception{
		
	    //captures the moouse movements and navigations
	    server.setCaptureHeaders(true);
        server.setCaptureContent(true);
 
	    // get the Selenium proxy object
        BrowserMobProxy proxy1 = new BrowserMobProxyServer();
        proxy1.setTrustAllServers(true);
        proxy1.start(0);
        
	   // Proxy proxy = server.seleniumProxy();
	   
	    //uncoment below line for secured site
	    Proxy proxy = ClientUtil.createSeleniumProxy(proxy1);
	    
	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("--ignore-certificate-errors");
	    options.addArguments("--headless");
 
	    // configure it as a desired capability
	    DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setCapability(CapabilityType.PROXY, proxy);
	    capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
 
	    // start the browser up
	    WebDriver driver = new ChromeDriver(capabilities);
	    
	   proxy1.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
 
	    // create a new HAR with the label "apple.com"
	 //   server.newHar("google.com");
	    
	    proxy1.newHar("google.com");
 
	    // open yahoo.com
	   
 
	    driver.get("https://lex.infosysapps.com/login");
	    Thread.sleep(3000);
	    driver.get("https://www.google.co.in/");
	    Thread.sleep(3000);
	   // driver.get("http://assertselenium.com");
	   // System.out.print("................................"+driver.findElement(By.id("crayon-5ea30760e867b427856089-5")).getTagName().toString());
	    try{	
	    // get the HAR data
       // Har har = server.getHar();
        
        net.lightbody.bmp.core.har.Har har = proxy1.getHar();
        FileOutputStream fos = new FileOutputStream(strFilePath);
        har.writeTo(fos);
        server.stop();
		driver.quit();
	    }
		finally {
			
	        
			driver.quit();
			proxy1.abort();
		}
		
	}
	@AfterSuite
    public void quitDriver() throws Exception {
		server.stop();
    }

*/}
