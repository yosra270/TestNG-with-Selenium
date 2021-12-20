package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.FindBy;

import utils.ElementUtil;
import utils.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class BasePage {
	public WebDriver driver;
	public Properties properties;
	public OptionsManager optionsManager;

	@FindBy(css ="#_desktop_logo > h1 > a")
	private WebElement homeLink;

	// ThreadLocal static WebDriver is used here for parallel execution matters
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	/*
	 * This method is used to initialize the browser based on the input given
	 * 
	 * @param browser
	 * 
	 * @return WebDriver driver
	 */
	public WebDriver initDriver(String browser) {
		System.out.println("Browser value is : " + browser);
		optionsManager = new OptionsManager(properties);
		
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		} 
		
		else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		} 
		
		else if (browser.equalsIgnoreCase("safari")) {
			tlDriver.set(new SafariDriver());
		} 
		
		else {
			System.out.println("Please enter the right browser name : " + browser);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		ElementUtil.implicitlyWait(getDriver(), 10, TimeUnit.SECONDS);
		return getDriver();
	}
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * This method loads all the input data from config.properties to the properties object
	 * 
	 * @return properties
	 */
	public Properties initProp() {
		properties = new Properties();
		try {
			FileInputStream ip = new FileInputStream("../config/config.properties");
			properties.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	 public static void closeDriver() {
	        if (tlDriver != null) {
	        	tlDriver.get().quit();
	        	tlDriver.remove();
	        }
	 }

	 
	 public void returnToHome() {
		 homeLink.click();
	 }
}
