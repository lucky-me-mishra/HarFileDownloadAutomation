package Example;

import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.testng.annotations.Configuration;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

public class ReadProperties {
	
	String result = "";
	InputStream inputStream;
	private static Map<String, String> map;
	public Map getPropValues() throws IOException {
		 
		try {
			Properties prop = new Properties();
			String propFileName = "config/UI_Input.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
			Date time = new Date(System.currentTimeMillis());
 
			// get the property value and print it out
			String url = prop.getProperty("url");
			String action1 = prop.getProperty("action1");
			map = new ConcurrentHashMap<String, String>();
			map.put("url", url);
			map.put("action1", action1);
			Collections.unmodifiableMap(map);
			
			System.out.println(result + "\nProgram Ran on " + time + " by user=" + action1);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return map;
	}
	public static void main(String[] args) {
		try {
			new ReadProperties().getPropValues();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

