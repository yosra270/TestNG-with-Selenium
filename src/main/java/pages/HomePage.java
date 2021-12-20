package pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;

public class HomePage extends BasePage {
	private WebDriver driver;

	// JavaScript examples (Backbone.js, AngularJs, Vue.js, ..)
	@FindBy(css = "iron-pages.app-lists > div:nth-child(1) li")
	private List<WebElement> jsExamplesLinks;
	
	// Examples in programming languages that compile to JavaScript (Kotlin+React, Spine, Dart, ..)
	@FindBy(css = "iron-pages.app-lists > div:nth-child(2) li")
	private List<WebElement> compiledToJsExamplesLinks;
	
	// Examples that are still in the evaluation stage (DUEL, Dijon, ..)
	@FindBy(css = "iron-pages.app-lists > div:nth-child(3) li")
	private List<WebElement> underEvaluationExamplesLinks;
	
	// Dictionaries of platforms and their links
	private Map<String, WebElement> jsExamplesPlatforms;
	private Map<String, WebElement> compiledToJsExamplesPlatforms;
	private Map<String, WebElement> underEvaluationExamplesPlatforms;

	
	public HomePage(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);	
		initializeDictionaries();
	}

    
    public void openHomePage() {
    	driver.navigate().to(properties.getProperty("driver.base-url"));
    }
    
    
	public void choosePlatform(String platform) {
		if(jsExamplesPlatforms.containsKey(platform.toLowerCase()))
			jsExamplesPlatforms.get(platform.toLowerCase()).click();
		
		else if(compiledToJsExamplesPlatforms.containsKey(platform.toLowerCase()))
			compiledToJsExamplesPlatforms.get(platform.toLowerCase()).click();
		
		else if(underEvaluationExamplesPlatforms.containsKey(platform.toLowerCase()))
			underEvaluationExamplesPlatforms.get(platform.toLowerCase()).click();
	}
	
	
	
	private void initializeDictionaries() {
		// JS Examples
		jsExamplesPlatforms = new HashMap<String, WebElement>();
		for(WebElement jsExampleLink : jsExamplesLinks) {
			jsExamplesPlatforms.put(jsExampleLink.getText().toLowerCase(), jsExampleLink);
		}
		
		// Compiled to JS Examples
		compiledToJsExamplesPlatforms = new HashMap<String, WebElement>();
		for(WebElement compiledToJsExampleLink : compiledToJsExamplesLinks) {
			compiledToJsExamplesPlatforms.put(compiledToJsExampleLink.getText().toLowerCase(), compiledToJsExampleLink);
		}
		
		// Under Evaluation Examples
		underEvaluationExamplesPlatforms = new HashMap<String, WebElement>();
		for(WebElement underEvaluationExampleLink : underEvaluationExamplesLinks) {
			underEvaluationExamplesPlatforms.put(underEvaluationExampleLink.getText().toLowerCase(), underEvaluationExampleLink);
		}
	}
}
