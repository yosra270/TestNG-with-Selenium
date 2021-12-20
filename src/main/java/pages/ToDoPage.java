package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;

public class ToDoPage extends BasePage {
	private WebDriver driver;
	
	@FindBy(css = "input.new-todo")
	private WebElement newToDoInput;
	
	@FindBy(css = "ul.todo-list li")
	private List<WebElement> toDos;
	
	@FindBy(css = "span.todo-count")
	private WebElement itemsLeftSpan;
	

	public ToDoPage(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);	
	}
	
	public void addToDo(String toDo) {
		newToDoInput.sendKeys(toDo);
		newToDoInput.sendKeys(Keys.ENTER);
	}
	
	public void removeToDo(int index) {
		toDos.get(index).findElement(By.cssSelector("input.toggle")).click();
	}
	
	public int itemsLeft() {
		return Integer.parseInt(itemsLeftSpan.getText().replaceAll("[^0-9]", ""));
	}
	
//	public int completedToDos() {
//		int completedToDos = 0;
//		for(WebElement toDo : toDos) {
//			if(toDo.getAttribute("class").equals("completed"))
//				completedToDos ++;
//		}
//		return completedToDos;
//	}
//	
//	public int toDosLeft() {
//		return toDos.size() - completedToDos();
//	}

}
