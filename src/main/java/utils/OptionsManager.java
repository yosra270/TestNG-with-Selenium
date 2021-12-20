package utils;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	private Properties properties;
	private ChromeOptions chromeOptions;
	private FirefoxOptions firefoxOptions;
	
	public OptionsManager(Properties properties) {
		this.properties = properties;
	}
	public ChromeOptions getChromeOptions() {
		chromeOptions = new ChromeOptions();
		if(properties.getProperty("driver.browser.headless").trim().equals("true"))
			chromeOptions.addArguments("--headless");

		if(properties.getProperty("driver.browser.incongnito").trim().equals("true"))
			chromeOptions.addArguments("--incongnito");
		return chromeOptions;
	}
	public FirefoxOptions getFirefoxOptions() {
		firefoxOptions = new FirefoxOptions();
		if(properties.getProperty("driver.browser.headless").trim().equals("true"))
			firefoxOptions.addArguments("--headless");

		if(properties.getProperty("driver.browser.incongnito").trim().equals("true"))
			firefoxOptions.addArguments("--incongnito");
		return firefoxOptions;
	}
}
