package tiktokpixels.pageobjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import tiktokpixels.WelcomeUI;

public class WelcomePO extends AbstractPage {
	WebDriver driver;

	public WelcomePO(WebDriver _driver) {
		driver = _driver;
	}

	public String getHowDoIGetItLink() {
		waitForControlVisible(driver, WelcomeUI.HOWDOIGETIT_LINK);
		return getAttributeValue(driver, WelcomeUI.HOWDOIGETIT_LINK, "href");
	}

	public void inputPixelTitle(String title) {
		sendkeyToElement(driver, WelcomeUI.PIXEL_TITLE, title);
	}

	public void inputPixelID(String id) {
		sendkeyToElement(driver, WelcomeUI.PIXEL_ID, id);
	}

	public void clickToCompleteBtn() {
		clickToElement(driver, WelcomeUI.COMPLETE_BTN);
	}

	public boolean verifyCompleteChkBoxIsDisplayed() {
		waitForControlVisible(driver, WelcomeUI.COMPLETE_CHKBOX);
		return isControlDisplayed(driver, WelcomeUI.COMPLETE_CHKBOX);
	}

	public boolean verifyCodeTextAreaIsDisplayed() {
		waitForControlVisible(driver, WelcomeUI.CODE_TEXT_AREA);
		return isControlDisplayed(driver, WelcomeUI.CODE_TEXT_AREA);
	}

	public void clickToCompleteSetupBtn() {
		clickToElement(driver, WelcomeUI.COMPLETE_SETUP_BTN);
	}
}
