package com.tiktokpixels;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import tiktokpixels.pageobjects.PageManagerDriver;
import tiktokpixels.pageobjects.PixelManagerPO;
import tiktokpixels.pageobjects.WelcomePO;

public class TC_01_VerifyCanAddANewPixel extends AbstractTest {

	WebDriver driver;
	private WelcomePO welcomePage;
	private PixelManagerPO pixelManagerPage;

	String expectedUrl = "https://help.omegatheme.com/en/article/tiktok-how-to-create-a-developer-mode-pixel-1wwzs1a/";
	String successfulTxt = "You have successfully installed 1 Titok pixel.";
	String tokenSuccessfulTxt = "Please click to check TikTok Events API working";

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browser) throws Exception {
		driver = openMultiBrowser(browser);
		welcomePage = (WelcomePO) PageManagerDriver.getInstance(driver, "WelcomePage");
	}

	@Test
	public void TC_VerifyCanAddANewPixel() {
		String actualLink = welcomePage.getHowDoIGetItLink();
		verifyEquals(actualLink, expectedUrl);
		welcomePage.inputPixelTitle("Auto_1");
		welcomePage.inputPixelID("C64D4GKGJNOBIGP7A9L0");
		welcomePage.clickToCompleteBtn();
		verifyTrue(welcomePage.verifyCompleteChkBoxIsDisplayed());
		verifyTrue(welcomePage.verifyCodeTextAreaIsDisplayed());
		welcomePage.clickToCompleteSetupBtn();

		pixelManagerPage = (PixelManagerPO) PageManagerDriver.getInstance(driver, "PixelManagerPage");
		verifyTrue(pixelManagerPage.verifySuccessfulTextIsDisplayed());
		String actualSuccessfulTxt = pixelManagerPage.getDescriptionText();
		verifyEquals(actualSuccessfulTxt, successfulTxt);
		pixelManagerPage.clickToClosePopupBtn();

		// verify the info of pixel
		String pixelID = pixelManagerPage.getPixelID();
		verifyEquals(pixelID, "C64D4GKGJNOBIGP7A9L0");
		String pixelTitle = pixelManagerPage.getPixelTitle();
		verifyEquals(pixelTitle, "Auto_1");
		String pixelStatus = pixelManagerPage.getPixelStatus();
		verifyEquals(pixelStatus, "Active");
		verifyFalse(pixelManagerPage.verifyPixelServerSideBtnOnIsDisplayed());

		pixelManagerPage.clickToServerSideAPIBtn();
		pixelManagerPage.inputTikTokAccessToken("a5173e3ab5dcb2d668faea218d3b88366287d156");
		String typeValueBefore = pixelManagerPage.getTypeOfInputToken();
		verifyEquals(typeValueBefore, "password");
		pixelManagerPage.clickToShowAccessTokenChkBox();
		String typeValueAfter = pixelManagerPage.getTypeOfInputToken();
		verifyEquals(typeValueAfter, "text");
		
		pixelManagerPage.clickToSaveBtn();
		verifyTrue(pixelManagerPage.verifySuccessfulTextIsDisplayed());
		String actualTokenSuccessfulTxt = pixelManagerPage.getDescriptionText();
		verifyEquals(actualTokenSuccessfulTxt, tokenSuccessfulTxt);
		verifyTrue(pixelManagerPage.verifyClickToCheckBtnIsDisplayed());
		pixelManagerPage.clickToClosePopupBtn();
		verifyTrue(pixelManagerPage.verifyPixelServerSideBtnOnIsDisplayed());
		
		pixelManagerPage.clickToAddPixelBtn();
		welcomePage.inputPixelTitle("Auto_2");
		welcomePage.inputPixelID("C6S045C17T5EBD1O5E2G");
		pixelManagerPage.clickToSaveBtn();
		verifyTrue(pixelManagerPage.verifySuccessfulTextIsDisplayed());
		pixelManagerPage.clickToClosePopupBtn();
		
		
	}

	@AfterClass
	public void afterClass() {
		closeBrowser(driver);
	}

}
