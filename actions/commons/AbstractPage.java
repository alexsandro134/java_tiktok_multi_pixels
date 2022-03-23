package commons;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {

	protected final Log log;

	protected AbstractPage() {
		log = LogFactory.getLog(getClass());
	}

	public void openAnyUrl(WebDriver driver, String url) {
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		log.info("Open Url: " + url);
	}

	public String getTitle(WebDriver driver) {
		String title = driver.getTitle();
		log.info("Get title of url: " + title);
		return title;
	}

	public String getCurrentUrl(WebDriver driver) {
		String currentUrl = driver.getCurrentUrl();
		log.info("Get current url: " + currentUrl);
		return currentUrl;
	}

	public String getPageSource(WebDriver driver) {
		String pageSource = driver.getPageSource();
		log.info("Get page source");
		return pageSource;
	}

	public void back(WebDriver driver) {
		driver.navigate().back();
		log.info("Press back button in browser");
	}

	public void forward(WebDriver driver) {
		driver.navigate().forward();
		log.info("Press forward button in browser");
	}

	public void refresh(WebDriver driver) {
		driver.navigate().refresh();
		log.info("Press refresh button in browser");
	}

	public void clickToElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();
		log.info("Click to element with xpath: " + locator);
	}

	/**
	 * Using this method to click dynamic locator
	 * 
	 * @param driver
	 * @param locator
	 * @param value   : all of the value after is same type
	 */
	public void clickToElement(WebDriver driver, String locator, String... value) {
		locator = String.format(locator, (Object[]) value);
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();
		log.info("Click to element with dynamic xpath: " + locator);
	}

	public void sendkeyToElement(WebDriver driver, String locator, String text) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.clear();
		element.sendKeys(text);
		log.info("Sendkey " + text + " to element with xpath: " + locator);
	}

	/**
	 * Send key to dynamic locator
	 * 
	 * @param driver
	 * @param text
	 * @param locator
	 * @param value   : all of the value after is same type
	 */
	public void sendkeyToElement(WebDriver driver, String text, String locator, String... value) {
		locator = String.format(locator, (Object[]) value);
		WebElement element = driver.findElement(By.xpath(locator));
		element.clear();
		element.sendKeys(text);
		log.info("Sendkey " + text + " to element with dynamic xpath: " + locator);
	}

	public void selectItemInDropdown(WebDriver driver, String locator, String textItem) {
		WebElement element = driver.findElement(By.xpath(locator));
		Select select = new Select(element);
		select.selectByVisibleText(textItem);
		log.info("Select " + textItem + " in dropdown with xpath: " + locator);
	}

	public String getFirstItemSelected(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		Select select = new Select(element);
		String firstItemText = select.getFirstSelectedOption().getText();
		log.info("Select first item " + firstItemText + "in dropdown with xpath: " + locator);
		return firstItemText;
	}

	public String getAttributeValue(WebDriver driver, String locator, String attribute) {
		WebElement element = driver.findElement(By.xpath(locator));
		String attValue = element.getAttribute(attribute);
		log.info("Get text of attribute " + attribute + " with xpath: " + locator);
		return attValue;
	}

	public String getTextElement(WebDriver driver, String locator) {
		waitForControlVisible(driver, locator);
		WebElement element = driver.findElement(By.xpath(locator));
		String text = element.getText();
		log.info("Text of element with xpath: " + locator + " is " + text);
		return text;
	}

	/**
	 * Get text of element with dynamic xpath
	 * 
	 * @param driver
	 * @param locator
	 * @param value
	 * @return text of Element
	 */
	public String getTextElement(WebDriver driver, String locator, String... value) {
		locator = String.format(locator, (Object[]) value);
		WebElement element = driver.findElement(By.xpath(locator));
		String textElement = element.getText();
		return textElement;
	}

	public int getSizeElement(WebDriver driver, String locator) {
		List<WebElement> elements = driver.findElements(By.xpath(locator));
		int size = elements.size();
		log.info("Size of list elements with xpath: " + locator + " is: " + size);
		return size;
	}

	public void checkTheCheckbox(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (!element.isSelected()) {
			element.click();
			log.info("Select the checkbox with xpath: " + locator);
		} else {
			log.info("Can't select the checkbox with xpath: " + locator);
		}
	}

	public void uncheckTheCheckbox(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isSelected()) {
			element.click();
			log.info("Uncheck the checkbox with xpath: " + locator);
		} else {
			log.info("Can't uncheck the checkbox with xpath: " + locator);
		}
	}

	public boolean isControlDisplayed(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		boolean flag = element.isDisplayed();
		if (flag == true) {
			log.info("Element with xpath: " + locator + " is displayed");
		} else {
			log.info("Element with xpath: " + locator + " is not displayed");
		}
		return flag;
	}

	public boolean isControlNotDisplayed(WebDriver driver, String locator) {
		overrideGlobalTimeout(driver, 10);
		List<WebElement> elements = driver.findElements(By.xpath(locator));
		if (elements.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Verify element display with dynamic locator
	 * 
	 * @param driver
	 * @param locator
	 * @param value   : all of the value after is same type
	 * @return
	 */
	public boolean isControlDisplayed(WebDriver driver, String locator, String... value) {
		locator = String.format(locator, (Object[]) value);
		WebElement element = driver.findElement(By.xpath(locator));
		boolean flag = element.isDisplayed();
		if (flag == true) {
			log.info("Element with dynamic xpath: " + locator + "is displayed");
		} else {
			log.info("Element with dynamic xpath: " + locator + "is not displayed");
		}
		return flag;
	}

	/**
	 * Verify element not display with dynamic locator
	 * 
	 * @param driver
	 * @param locator
	 * @param value   : all of the value after is same type
	 * @return
	 */
	public boolean isControlNotDisplayed(WebDriver driver, String locator, String... value) {
		locator = String.format(locator, (Object[]) value);
		overrideGlobalTimeout(driver, 10);
		List<WebElement> elements = driver.findElements(By.xpath(locator));
		if (elements.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isControlSelected(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		boolean flag = element.isSelected();
		if (flag == true) {
			log.info("Element with dynamic xpath: " + locator + "is selected");
		} else {
			log.info("Element with dynamic xpath: " + locator + "is not selected");
		}
		return flag;
	}

	public boolean isControlEnabled(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		boolean flag = element.isEnabled();
		if (flag == true) {
			log.info("Element with dynamic xpath: " + locator + "is enabled");
		} else {
			log.info("Element with dynamic xpath: " + locator + "is not enabled");
		}
		return flag;
	}

	public void acceptAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		alert.accept();
		log.info("Accept the alert");
	}

	public void cancelAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
		log.info("Dismiss the alert");
	}

	public String getTextAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		log.info("Text of alert is: " + text);
		return text;
	}

	public void sendkeyToAlert(WebDriver driver, String value) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(value);
		log.info("Input : " + value + "to the alert");
	}

	public void keyPress(WebDriver driver, String locator, Keys button) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.sendKeys(button);
		log.info("Press : " + button.toString() + "to the element with xpath " + locator);
	}

	public void keyPress(WebDriver driver, Keys button, String locator, String... value) {
		locator = String.format(locator, (Object[]) value);
		WebElement element = driver.findElement(By.xpath(locator));
		element.sendKeys(button);
		log.info("Press : " + button.toString() + "to the element with dynamic xpath " + locator);
	}

	public void switchWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String a : allWindows) {
			if (!a.equals(parentID)) {
				driver.switchTo().window(a);
				log.info("Switch to Window with id:" + a);
				break;
			}
		}
	}

	public void switchWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String windowHandle : allWindows) {
			driver.switchTo().window(windowHandle);
			if (driver.getTitle().equals(title)) {
				log.info("Switch to Window with title:" + title);
				break;
			}
		}
	}

	public void closeAllWindowsWithoutParent(WebDriver driver, String parentWindow) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String windowHandle : allWindows) {
			if (!windowHandle.equals(parentWindow)) {
				driver.switchTo().window(windowHandle);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
		log.info("Close all windows without parent successful");
	}

	public void switchToIframe(WebDriver driver, String frameName) {
		driver.switchTo().frame(frameName);
		log.info("Swith to frame with name: " + frameName);
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.doubleClick(element);
		log.info("Double click to element with xpath: " + locator);
	}

	public void hoverMouseToElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.moveToElement(element);
		log.info("Hover mouse to element with xpath: " + locator);
	}

	public void rightClickToElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.contextClick(element);
		log.info("Right click to element with xpath: " + locator);
	}

	public void dragAndDrop(WebDriver driver, String locatorSource, String locatorTarget) {
		WebElement elementSource = driver.findElement(By.xpath(locatorSource));
		WebElement elementTarget = driver.findElement(By.xpath(locatorTarget));
		Actions action = new Actions(driver);
		action.dragAndDrop(elementSource, elementTarget);
		log.info("Drag from element with xpath: " + locatorSource + " to element with xpath: " + locatorTarget);
	}

	public void uploadBySendkey(WebDriver driver, String locator, String path) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.sendKeys(path);
		log.info("Upload by sendkey to element with xpath: " + locator);
	}

	public void uploadByAutoIT(WebDriver driver, String locator, String filePath) throws Exception {
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();
		Runtime.getRuntime().exec(new String[] { ".\\autoIT\\chrome.exe", filePath });
		log.info("Upload by AutoIT to element with xpath: " + locator);
	}

	public void uploadByRobot(WebDriver driver, String locator, String filePath) throws Exception {
		WebElement uploadChrome = driver.findElement(By.xpath(locator));
		uploadChrome.click();

		StringSelection select = new StringSelection(filePath);

		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

		Robot robot = new Robot();

		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(1000);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		Thread.sleep(3000);
		log.info("Upload by Robot to element with xpath: " + locator);
	}

	public Object executeJavascriptToBrowser(WebDriver driver, String js) {
		try {
			return ((JavascriptExecutor) driver).executeScript(js);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object executeJavascriptToElement(WebDriver driver, String xpath, String js) {
		try {
			WebElement element = driver.findElement(By.xpath(xpath));
			return ((JavascriptExecutor) driver).executeScript(js, element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object scrollToBottomPage(WebDriver driver) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public void highlightElement(WebDriver driver, WebElement element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].style.border='2px groove green';", element);
	}

	public Object scrollToElement(WebDriver driver, WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object removeAttributeOfElement(WebDriver driver, String xpath, String attributeName) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].removeAttribute('" + attributeName + "');",
					driver.findElement(By.xpath(xpath)));
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public boolean checkAnyImageLoaded(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (boolean) js.executeScript("return arguments[0].complete && "
				+ "typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", element);
	}

	public void waitForControlPresence(WebDriver driver, String locator) {
		By by = By.xpath(locator);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
		log.info("Wait until element with xpath: " + locator + " present");
	}

	public void waitForControlVisible(WebDriver driver, String locator) {
		By by = By.xpath(locator);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		log.info("Wait until element with xpath: " + locator + " visible");
	}

	public void waitForControlVisible(WebDriver driver, String locator, String... value) {
		locator = String.format(locator, (Object[]) value);
		By by = By.xpath(locator);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		log.info("Wait until element with dynamic xpath: " + locator + " visible");
	}

	public void waitForControlClickable(WebDriver driver, String locator) {
		By by = By.xpath(locator);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(by));
		log.info("Wait until element with xpath: " + locator + " clickable");
	}

	public void waitForControlNotVisible(WebDriver driver, String locator) {
		By by = By.xpath(locator);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		log.info("Wait until element with xpath: " + locator + " invisible");
	}

	public void waitForAlertPresence(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.alertIsPresent());
		log.info("Wait until alert present");
	}

	public void overrideGlobalTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	public void selectCustomDropdownList(WebDriver driver, String dropdown, String listItems, String valueItem)
			throws Exception {
		// Click vào dropdown
		WebElement dropdownElement = driver.findElement(By.xpath(dropdown));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdownElement);
		dropdownElement.click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		// Get tất cả item trong dropdown vào 1 list element (List <WebElement>)
		List<WebElement> allItems = driver.findElements(By.xpath(listItems));
		// Wait để tất cả phần tử trong dropdown được hiển thị
		wait.until(ExpectedConditions.visibilityOfAllElements(allItems));
		// Dùng vòng lặp for duyệt qua từng phần tử
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(valueItem)) {
				// Scroll to item
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", item);
				// Check item displayed
				item.isDisplayed();
				// Nếu actual text = expected text thì click vào phần tử đó và
				// break khỏi vòng lặp
				item.click();
				break;
			}
		}
	}
}
