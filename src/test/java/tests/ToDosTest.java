package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.ToDoPage;

public class ToDosTest extends BaseTest {
	HomePage homePage;
	ToDoPage toDoPage;
	String[] todos;
	
	@BeforeClass
	public void initialization() {
		homePage = new HomePage(driver);
		toDoPage = new ToDoPage(driver);
		todos = properties.getProperty("todos").split(",");
	}

	@Test()
	@Parameters({"platform1", "platform2", "platform3", "platform4"})
    public void userCanAddAndRemoveToDoSuccessfully(String platform) {
        homePage.openHomePage();
        homePage.choosePlatform(platform);
        for(String todo : todos)
        	toDoPage.addToDo(todo);
        toDoPage.removeToDo(1);
        
        Assert.assertEquals(2, toDoPage.itemsLeft());
        
        //assertItemsLeft(2, toDoPage.getItemsLeftSpan());
    }
	
//	private void assertItemsLeft(int expectedLeft, WebElement element) {
//		String expectedTest = (expectedLeft == 1 ) ? String.format("$d item left", expectedLeft) : String.format("$d items left", expectedLeft) ;
//		validateInnerText(element,expectedTest);
//    }
//	
//	private void validateInnerText(WebElement element, String expectedTest) {
//		ExpectedConditions.textToBePresentInElement(element, expectedTest);
//	}

}
