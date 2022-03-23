package tiktokpixels.pageobjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class PageManagerDriver {

	private static WelcomePO welcomePage;
	private static PixelManagerPO pixelManagerPage;
	private static LaravelLogPO laravelLogPage;

	public static AbstractPage getInstance(WebDriver driver, String page) {
		switch (page) {
		case "WelcomePage":
			if (welcomePage == null) {
				return new WelcomePO(driver);
			}
			return welcomePage;

		case "PixelManagerPage":
			if (pixelManagerPage == null) {
				return new PixelManagerPO(driver);
			}
			return pixelManagerPage;

		case "LaravelLogPage":
			if (laravelLogPage == null) {
				return new LaravelLogPO(driver);
			}
			return laravelLogPage;

		default:
			return null;
		}
	}
}
