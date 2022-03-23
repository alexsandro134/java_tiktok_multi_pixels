package tiktokpixels.pageobjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import tiktokpixels.PixelManagerUI;

public class PixelManagerPO extends AbstractPage {

	WebDriver driver;

	public PixelManagerPO(WebDriver _driver) {
		driver = _driver;
	}

	public String getDescriptionText() {
		waitForControlVisible(driver, PixelManagerUI.DESC_TXT);
		return getTextElement(driver, PixelManagerUI.DESC_TXT);
	}

	public boolean verifySuccessfulTextIsDisplayed() {
		waitForControlVisible(driver, PixelManagerUI.SUCESSFUL_TXT);
		return isControlDisplayed(driver, PixelManagerUI.SUCESSFUL_TXT);
	}

	public void clickToClosePopupBtn() {
		clickToElement(driver, PixelManagerUI.CLOSE_POPUP_BTN);
	}

	public void clickToSelectAllChkBox() {
		clickToElement(driver, PixelManagerUI.SELECT_ALL_CHKBOX);
	}

	public void clickToBulkActionBtn() {
		clickToElement(driver, PixelManagerUI.BULK_ACTION_BTN);
	}

	public void clickToSetAsDraftBtn() {
		clickToElement(driver, PixelManagerUI.SET_AS_DRAFT_BTN);
	}

	public void clickToDeletePixelsBtn() {
		clickToElement(driver, PixelManagerUI.DELETE_PIXELS_BTN);
	}

	public void clickToConfirmBtn() {
		clickToElement(driver, PixelManagerUI.CONFIRM_BTN);
	}

	public void clickToVisitLink() {
		clickToElement(driver, PixelManagerUI.VISIT_LINK);
	}

	public String getPixelID() {
		waitForControlVisible(driver, PixelManagerUI.PIXEL_ID);
		return getTextElement(driver, PixelManagerUI.PIXEL_ID);
	}

	public String getPixelTitle() {
		waitForControlVisible(driver, PixelManagerUI.PIXEL_TITLE);
		return getTextElement(driver, PixelManagerUI.PIXEL_TITLE);
	}

	public String getPixelStatus() {
		waitForControlVisible(driver, PixelManagerUI.PIXEL_STATUS);
		return getTextElement(driver, PixelManagerUI.PIXEL_STATUS);
	}

	public void clickToFirstPixelServerSideBtn() {
		clickToElement(driver, PixelManagerUI.FIRST_PIXEL_SERVER_SIDE_BTN);
	}

	public void clickToFirstPixelEditBtn() {
		clickToElement(driver, PixelManagerUI.FIRST_PIXEL_EDIT_BTN);
	}

	public void clickToServerSideAPIBtn() {
		clickToElement(driver, PixelManagerUI.SERVER_SIDE_API_BTN);
	}

	public void inputTikTokAccessToken(String token) {
		sendkeyToElement(driver, PixelManagerUI.TIKTOK_ACCESS_TOKEN_TXT, token);
	}

	public void clickToShowAccessTokenChkBox() {
		clickToElement(driver, PixelManagerUI.SHOW_ACCESS_TOKEN_CHKBOX);
	}

	public String getTypeOfInputToken() {
		String typeValue = getAttributeValue(driver, PixelManagerUI.TIKTOK_ACCESS_TOKEN_TXT, "type");
		return typeValue;
	}

	public void clickToSaveBtn() {
		clickToElement(driver, PixelManagerUI.SAVE_BTN);
	}

	public boolean verifyClickToCheckBtnIsDisplayed() {
		waitForControlVisible(driver, PixelManagerUI.CLICK_TO_CHECK_BTN);
		return isControlDisplayed(driver, PixelManagerUI.CLICK_TO_CHECK_BTN);
	}

	public boolean verifyPixelServerSideBtnOnIsDisplayed() {
		waitForControlVisible(driver, PixelManagerUI.FIRST_PIXEL_SERVER_SIDE_BTN);
		return isControlDisplayed(driver, PixelManagerUI.FIRST_PIXEL_SERVER_SIDE_BTN);
	}

	public void clickToAddPixelBtn() {
		clickToElement(driver, PixelManagerUI.ADD_PIXEL_BTN);
	}
}
