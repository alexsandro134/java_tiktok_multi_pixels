package tiktokpixels;

public class WelcomeUI {
	public static final String PIXEL_TITLE = "//input[@placeholder=\"Enter a name for your pixel\"]";
	public static final String PIXEL_ID = "//input[@placeholder=\"Copy pixel ID from TikTok and paste here\"]";
	public static final String COMPLETE_BTN = "//span[text()='Complete']/ancestor::button";
	public static final String COMPLETE_CHKBOX = "//span[contains(@class,\"Polaris-Icon--colorPrimary\")]";
	public static final String CODE_TEXT_AREA = "//div[@aria-expanded=\"true\" and @id=\"basic-collapsible\"]";
	public static final String COMPLETE_SETUP_BTN = "//span[text()='Complete setup']/ancestor::button";
	public static final String HOWDOIGETIT_LINK = "//a[contains(text(),'How do i get it')]";
	public static final String ERROR_MSG = "div[id*=\"Error\"]";
	public static final String TOAST_ERROR_MSG = "div[class='Polaris-Frame-Toast Polaris-Frame-Toast--error']";
	public static final String TOAST_MSG_CLOSE_BTN = "button[class='Polaris-Frame-Toast__CloseButton']";
	public static final String BACK_BTN = "span[class='button-back']";
	public static final String CHAT_BOX = "[class=crisp-client]";
}
