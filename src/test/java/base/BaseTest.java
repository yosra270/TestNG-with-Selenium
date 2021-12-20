package base;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import utils.ElementUtil;

public abstract class BaseTest {

	private BasePage basePage;
	protected Properties properties;
	protected WebDriver driver;

	@BeforeSuite
	public void setUp() {
		basePage = new BasePage() {
		};
		properties = basePage.initProp();
		driver = basePage.initDriver(properties.getProperty("browser"));
	}

	@AfterMethod
	public void takesScreenshots(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			ElementUtil.implicitlyWait(driver, 10, TimeUnit.SECONDS);
			ElementUtil.getScreenshot(driver, result.getName() + "-Failed");
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			ElementUtil.implicitlyWait(driver, 10, TimeUnit.SECONDS);
			ElementUtil.getScreenshot(driver, result.getName() + "-Passed");
		}
	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
	}
}
