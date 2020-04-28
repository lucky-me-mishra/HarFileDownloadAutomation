
//https://github.com/lightbody/browsermob-proxy#using-with-selenium
//using with selenium
package Example;

import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;

import java.io.FileOutputStream;

/*import org.browsermob.core.har.Har;
import org.browsermob.proxy.ProxyServer;
import org.openqa.selenium.Proxy;*/

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.proxy.CaptureType;

public class PerfTest {

	public static void main(String[] args) throws Exception {

		String strFilePath = "D:/test.har";
		
		System.setProperty("webdriver.chrome.driver", "D:/Main_Folder/code/chromedriver_win32/chromedriver.exe");
		/*
		 * no need of Proxyserver, it should be done by the BrowserMobProxy in order to work for ssl based sites and head less chrome browser
		// start the proxy
		ProxyServer server = new ProxyServer(4444);
		server.start();
		// captures the moouse movements and navigations
		server.setCaptureHeaders(true);
		server.setCaptureContent(true);*/

		BrowserMobProxy proxy1 = new BrowserMobProxyServer();
		proxy1.setTrustAllServers(true);
		proxy1.start(0);

		// Proxy proxy = server.seleniumProxy(); // Do not create proxy object from server object

		Proxy proxy = ClientUtil.createSeleniumProxy(proxy1);

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--ignore-certificate-errors");

		// configure it as a desired capability
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.PROXY, proxy);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

		// start the browser up
		WebDriver driver = new ChromeDriver(capabilities);

		proxy1.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);

		// do not create new HAR  from server object with the label "google.com", create from BrowserMobProxy class which is proxy1
		// server.newHar("google.com");

		proxy1.newHar("google.com");

		driver.get("https://lex.infosysapps.com/login");
		Thread.sleep(3000);
		driver.get("https://www.infosys.com/");
		
		Thread.sleep(3000);
		driver.findElement(By.id("btn-search")).click();
		
		driver.findElement(By.name("k")).clear();
		
		driver.findElement(By.name("k")).sendKeys("jobs");

		try {
			// do not create new HAR  from server object
			// Har har = server.getHar();

			net.lightbody.bmp.core.har.Har har = proxy1.getHar();
			FileOutputStream fos = new FileOutputStream(strFilePath);
			har.writeTo(fos);
			//server.stop();
			driver.quit();
		} finally {

			//server.stop();
			driver.quit();
			proxy1.abort();
		}

	}

}