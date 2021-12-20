package utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
	
	public static int implicitWait;
    public static int explicitWait;
    public static int pageRefreshWait;

	public ElementUtil(Properties properties) {
		implicitWait = Integer.parseInt(properties.getProperty("driver.wait.implicitWait").trim());
        explicitWait = Integer.parseInt(properties.getProperty("driver.wait.explicitWait").trim());
        pageRefreshWait = Integer.parseInt(properties.getProperty("driver.wait.pageRefreshWait").trim());
	}
	
	
	/******************** Screenshots Utility *******************************/
	public static String getScreenshot(WebDriver driver) {
		File src =((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	public static String getScreenshot(WebDriver driver, String name) {
		File src =((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots" + System.currentTimeMillis() + name + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	/****************** Wait Utilities***********************************/
	/*public static WebElement waitForElementPresence(WebDriver driver, By selector, int timeOut) {
		return (new WebDriverWait(driver, timeOut)).until(ExpectedConditions.presenceOfElementLocated(selector));
	}
	public static WebElement waitForElementToBeClickable(WebDriver driver, By selector, int timeOut) {
		return (new WebDriverWait(driver, timeOut)).until(ExpectedConditions.elementToBeClickable(selector));
	}
	public static List<WebElement> waitForVisibilityOfAllElements(WebDriver driver, By locator, int timeOut) {
		return (new WebDriverWait(driver, timeOut)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	public static WebElement waitForElementToAppear(WebDriver driver, By locator, int timeOut) {
	       return  (new WebDriverWait(driver, timeOut)).until(ExpectedConditions.visibilityOfElementLocated(locator));
	    }

	public static Boolean waitForElementToDisappear(WebDriver driver, By locator, int timeOut) {
		return (new WebDriverWait(driver, timeOut)).until(ExpectedConditions.invisibilityOfElementLocated(locator));
	    }

	public static Boolean waitForTextToDisappear(WebDriver driver, By locator, String text, int timeOut) {
		return (new WebDriverWait(driver, timeOut)).until(ExpectedConditions.not(ExpectedConditions.textToBe(locator, text)));
	    }
	
	 public static void scrollToElement(WebElement element, WebDriver driver){
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView()",element);
	    }*/
	    
	    public static Boolean waitForElementPresence(WebDriver driver, By selector) {
	    	try {
	            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(explicitWait)).
	            		ignoring(NoSuchElementException.class).ignoring(WebDriverException.class);
	            wait.until(ExpectedConditions.presenceOfElementLocated(selector));
	        } catch (TimeoutException e) {
	            return false;
	        }
	        return true;
		}
	    
	    public static boolean waitForElementDisplay(WebElement webElement) {
	        try {
	            Wait<WebElement> wait = new FluentWait<WebElement>(webElement).withTimeout(Duration.ofSeconds(explicitWait)).
	            		ignoring(NoSuchElementException.class).ignoring(WebDriverException.class);
	            wait.until(new Function<WebElement, Boolean>() {
	                           public Boolean apply(WebElement element) {
	                               return element.isDisplayed();
	                           }
	                       }
	            );
	        } catch (TimeoutException e) {
	            return false;
	        }
	        return true;
	    }
		public static Boolean waitForElementToDisappear(WebDriver driver, By locator) {
			try {
	            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(explicitWait)).
	            		ignoring(NoSuchElementException.class).ignoring(WebDriverException.class);
	            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	        } catch (TimeoutException e) {
	            return false;
	        }
	        return true;
		    }

		public static Boolean waitForTextToDisappear(WebDriver driver, By locator, String text) {
			try {
	            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(explicitWait)).
	            		ignoring(NoSuchElementException.class).ignoring(WebDriverException.class);
	            wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(locator, text)));
	        } catch (TimeoutException e) {
	            return false;
	        }
	        return true;
		    }
		
	    public static boolean waitForElementToBeClickable(WebDriver driver,By selector, String locator) {
	        try {
	            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(explicitWait)).
	            		ignoring(NoSuchElementException.class).ignoring(WebDriverException.class);
	            wait.until(ExpectedConditions.elementToBeClickable(selector));
	        } catch (TimeoutException e) {
	            return false;
	        }
	        return true;
	    }
	    public static boolean waitForElementToBeClickable(WebDriver driver, WebElement webElement) {
	        try {
	            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(explicitWait)).
	                    ignoring(NoSuchElementException.class).ignoring(WebDriverException.class);
	            wait.until(ExpectedConditions.elementToBeClickable(webElement));
	        } catch (TimeoutException e) {
	            return false;
	        }
	        return true;
	    }
	    public static boolean waitForElementToBeClickable(WebDriver driver, WebElement webElement, int duration) {
			try {
				Thread.sleep(duration);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
	        try {
	            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(explicitWait)).
	                    ignoring(NoSuchElementException.class).ignoring(WebDriverException.class);
	            wait.until(ExpectedConditions.elementToBeClickable(webElement));
	        } catch (TimeoutException e) {
	            return false;
	        }

	        return true;
	    }
	    public static Boolean waitForVisibilityOfAllElements(WebDriver driver, By locator) {
			try {
	            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(explicitWait)).
	                    ignoring(NoSuchElementException.class).ignoring(WebDriverException.class);
	            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	        } catch (TimeoutException e) {
	            return false;
	        }
	        return true;
		}
	    public static void waitForPageToLoad(WebDriver driver) {
	        WebDriverWait wait = new WebDriverWait(driver, pageRefreshWait);
	        wait.until(new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver driver) {
	                return ((JavascriptExecutor) driver).executeScript(
	                        "return document.readyState"
	                ).equals("complete");
	            }
	        });
	    }
	    public static void waitForPageToLoad(WebDriver driver, int duration) {
	        WebDriverWait wait = new WebDriverWait(driver, pageRefreshWait);
	        wait.until(new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver driver) {
	                return ((JavascriptExecutor) driver).executeScript(
	                        "return document.readyState"
	                ).equals("complete");
	            }
	        });
			try {
				Thread.sleep(duration);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
	    }

	    public static WebElement explicitWait(WebDriver driver, final String elementLoc) {
	        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	                .withTimeout(Duration.ofSeconds(explicitWait))
	                .pollingEvery(Duration.ofSeconds(1))
	                .ignoring(NoSuchElementException.class);
	        if (elementLoc.contains(".//")) {
	            WebElement element = wait.until(new Function<WebDriver, WebElement>() {

	                public WebElement apply(WebDriver driver) {
	                    return driver.findElement(By.xpath(elementLoc));
	                }
	            });
	            return element;
	        } else {
	            WebElement element = wait.until(new Function<WebDriver, WebElement>() {

	                public WebElement apply(WebDriver driver) {
	                    return driver.findElement(By.id(elementLoc));
	                }
	            });
	            return element;
	        }
	    }
	    public static void implicitlyWait(WebDriver driver, int timeOut, TimeUnit timeUnit) {
	        driver.manage().timeouts().implicitlyWait(timeOut, timeUnit);
	    }


	    public static void wait(int seconds) {
	        try {
	            Thread.sleep(1000 * seconds);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	
	/***************** Dropdown Utilities******************************************/


	    
	    public static void selectByValueFromDropDown(WebElement element, String value) {
	        Select selectFromDropDown = new Select(element);
	        selectFromDropDown.selectByValue(value);
	    }

	    
	    public static void selectByValueFromDropDown(WebDriver driver, By locator, String value) {
	    	selectByValueFromDropDown(driver.findElement(locator), value);
	    }

	    public static void selectByVisibleTextFromDropDown(WebElement element, String value) {
	        Select selectFromDropDown = new Select(element);
	        selectFromDropDown.selectByVisibleText(value);
	    }

	    public static void selectByVisibleTextFromDropDown(WebDriver driver, By locator, String value) {
	    	selectByVisibleTextFromDropDown(driver.findElement(locator), value);
	    }

	    public static void selectByIndexFromDropDown(WebElement element, int index) {
	        Select selectFromDropDown = new Select(element);
	        selectFromDropDown.selectByIndex(index);
	    }

	    public static void selectByIndexFromDropDown(WebDriver driver, By locator, int index) {
	    	selectByIndexFromDropDown(driver.findElement(locator),index);
	    }
	    
	    /********************** other elements utils *********************************/
	    public static boolean isAlertPresent(WebDriver driver) {
	        try {
	            driver.switchTo().alert();
	            return true;
	        } catch (NoAlertPresentException e) {
	            return false;
	        }
	    }

	    public static void moveToElement(WebDriver driver, WebElement element) {
	        try {
	            Actions actions = new Actions(driver);
	            actions.moveToElement(element).perform();
	        } catch (StaleElementReferenceException e) {
	            Actions actions = new Actions(driver);
	            actions.moveToElement(element).perform();
	        }
	    }
}
