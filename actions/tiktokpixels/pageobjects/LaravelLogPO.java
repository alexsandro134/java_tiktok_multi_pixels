package tiktokpixels.pageobjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import tiktokpixels.LaravelLogUI;

public class LaravelLogPO extends AbstractPage {
	WebDriver driver;

	public LaravelLogPO(WebDriver _driver) {
		driver = _driver;
	}

	public String getLogContent(){
		waitForControlVisible(driver, LaravelLogUI.LOG_CONTENT);
		return getTextElement(driver, LaravelLogUI.LOG_CONTENT);
	}
}
