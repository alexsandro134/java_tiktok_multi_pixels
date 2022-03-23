package tiktokpixels;

public class PixelManagerUI {
	public static final String DESC_TXT = "//p[@class=\"description-text\"]";
	public static final String SUCESSFUL_TXT = "//h1[@class='text-title']";
	public static final String CLOSE_POPUP_BTN = "//button[@aria-label=\"Close\"]";
	public static final String SELECT_ALL_CHKBOX = "div[class=\"Polaris-IndexTable-ScrollContainer\"] input[id^=\"PolarisCheckbox\"] + span";
	public static final String BULK_ACTION_BTN = "div[class=\"Polaris-BulkActions__BulkActionButton\"] > button";
	public static final String SET_AS_DRAFT_BTN = "";
	public static final String DELETE_PIXELS_BTN = "";
	public static final String CONFIRM_BTN = "";
	public static final String VISIT_LINK = "";
	public static final String PIXEL_ID = "//td[@class=\"Polaris-IndexTable__TableCell\"]"; // 0
	public static final String PIXEL_TITLE = "//td[@class=\"Polaris-IndexTable__TableCell\"]"; // 1
	public static final String PIXEL_STATUS = "//span[contains(@class,'Polaris-Badge--statusInfo')]";
	public static final String FIRST_PIXEL_SERVER_SIDE_BTN = "//span[contains(@class,'Mui-checked')]";
	public static final String FIRST_PIXEL_EDIT_BTN = "button[class=\"Polaris-Button Polaris-Button--iconOnly\"]";
	public static final String SERVER_SIDE_API_BTN = "//input[contains(@class,\\\"MuiSwitch-input\\\")]";
	public static final String TIKTOK_ACCESS_TOKEN_TXT = "//input[@type=\"password\"]";
	public static final String SHOW_ACCESS_TOKEN_CHKBOX = "//label[@class=\"Polaris-Choice\"]"; // 1
	public static final String SAVE_BTN = "//span[contains(text(),'Save')]/ancestor::button";
	public static final String ADD_PIXEL_BTN = "//button[span[span[text()='+ Add Pixel']]]";
	public static final String CLICK_TO_CHECK_BTN = "//span[contains(text(),'Click to check')]/ancestor::button";
}
