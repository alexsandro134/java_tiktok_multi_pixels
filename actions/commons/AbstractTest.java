package commons;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AbstractTest {
	WebDriver driver;
	protected final Log log;

	public AbstractTest() {
		log = LogFactory.getLog(getClass());
	}

//	@SuppressWarnings("deprecation")
	protected WebDriver openMultiBrowser(String browser) {
		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else {
			System.out.println("Can't init browser!");
		}
		log.info("Before Class: Open Url of Login Page");
		driver.get(
				"https://dev.omegatheme.com/shopify/tiktok-multi-pixels/server.php?forceRedirect=0&shop=luffy-straw-hat.myshopify.com&hmac=2556a8c5bac4d79b3f42cbba48c6c4437cb35e30cf1ecf8eff4aaa66a8d49dba");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

	public String generateEmail() {
		StringBuilder randomEmail = new StringBuilder();
		randomEmail.append(RandomStringUtils.random(10, "abcdefghijklmnopqrstuvxyz1234567890")).append("@gmail.com");
		return randomEmail.toString();
	}

	private boolean checkPassed(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
			log.info("----------------- PASSED -----------------");
		} catch (Throwable e) {
			log.info("----------------- FAILED -----------------");
			pass = false;
			// Attach exception to ReportNG
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	public boolean verifyTrue(boolean condition) {
		return checkPassed(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertFalse(condition);
			log.info("----------------- PASSED -----------------");
		} catch (Throwable e) {
			log.info("----------------- FAILED -----------------");
			pass = false;
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	public boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(actual + " is equals " + expected);
			log.info("----------------- PASSED -----------------");
		} catch (Throwable e) {
			log.info(actual + " is not equals " + expected);
			log.info("----------------- FAILED -----------------");
			pass = false;
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	public boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}

	protected void closeBrowser(WebDriver driver) {
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			String cmd = "";
			driver.quit();
			if (driver.toString().toLowerCase().contains("chrome")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill chromedriver";
				} else {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver.exe*\"";
				}
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			}
			if (driver.toString().toLowerCase().contains("internetexplorer")) {
				cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			}
			if (driver.toString().toLowerCase().contains("gecko")) {
				cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver.exe*\"";
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			}
			log.info("---------- QUIT BROWSER SUCCESS ----------");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
