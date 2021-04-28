package properties;

import java.io.FileInputStream;
import java.util.Properties;

public class TestingProperties {
	private static Properties prop;

	private static void getPropertiesFile() {
		prop = new Properties();
		try {
			String projDir = System.getProperty("user.dir");
			FileInputStream fin = new FileInputStream(projDir + "/src/test/java/properties/test.properties");
			prop.load(fin);
			fin.close();
		} catch (Exception e) {
			System.err.println("An exception has occurred while reading the properties file");
			e.printStackTrace();
		}
	}

	public static String getWinDriverLocation() {
		getPropertiesFile();
		return prop.getProperty("windows.drivers");
	}

	public static String getLinuxDriverLocation() {
		getPropertiesFile();
		return prop.getProperty("linux.drivers");
	}

	public static String getMacDriverLocation() {
		getPropertiesFile();
		return prop.getProperty("mac.drivers");
	}

	public static String getDesignatedBrowser() {
		getPropertiesFile();
		return prop.getProperty("designatedBrowser.type");
	}

	public static String getLoadAndWaitTimeout() {
		getPropertiesFile();
		return prop.getProperty("loadAndWait.timeout");
	}

	public static String getExtentReportLocation() {
		getPropertiesFile();
		return prop.getProperty("extentreport.file");
	}
	
	public static String getSeleniumHubURL() {
		getPropertiesFile();
		return prop.getProperty("selenium.huburl");
	}
	
	public static String getWebsiteURL() {
		getPropertiesFile();
		return prop.getProperty("website.url");
	}
	
	public static String getAutoITScript() {
		getPropertiesFile();
		String scriptLocation = null;
		if(getDesignatedBrowser().equalsIgnoreCase("chrome"))
			scriptLocation = prop.getProperty("medicon.autoit.chrome");
		else if(getDesignatedBrowser().equalsIgnoreCase("firefox"))
			scriptLocation = prop.getProperty("medicon.autoit.firefox");
		return scriptLocation;
	}
	
	public static String getSSLocation() {
		getPropertiesFile();
		return prop.getProperty("ss.loc");
	}
	
	public static boolean ssEnabled() {
		getPropertiesFile();
		boolean ss = false;
		if(prop.getProperty("ss.enabled").equals("true"))
			ss=true;
		return ss;
	}
}
