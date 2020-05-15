package Automation._Selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {

	static WebDriver driver1;
	
	enum month { January,February,March,April,May,June,July,August,September,October,November,December;}
	
	public int getmonth (String mon) {
		
		return month.valueOf(mon).ordinal();
		
	}
	
	public static WebDriver getChromeDriver() {
		System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe"); //jar/chromedriver.exe
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\Gokul  Kannan.B\\Downloads\\Jars\\chromeDriver_80\\chromedriver.exe"); //jar/chromedriver.exe
		return new ChromeDriver();
	}
	
	public static WebDriver getFirefoxDriver() {
		System.setProperty("webdriver.gecko.driver","Driver/geckodriver.exe");
		return new FirefoxDriver();
	}
	
	
}
