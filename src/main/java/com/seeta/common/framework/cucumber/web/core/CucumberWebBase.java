package com.seeta.common.framework.cucumber.web.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Level;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.Reporter;
import org.testng.SkipException;

import com.seeta.common.framework.cucumber.web.core.OSUtils.OSType;

import cucumber.api.Scenario;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
 
// TODO: Auto-generated Javadoc
/**
 * The Class CucumberWebBase.
 */
public class CucumberWebBase {

	/** The driver. */
	protected static WebDriver driver;

	/**
	 * Log.
	 *
	 * @param message the message
	 * @param level the level
	 */
	protected void log(String message, String level) {
		LoggerUtil.log(message, level);
	}

	/**
	 * Log.
	 *
	 * @param message the message
	 */
	protected void log(String message) {
		LoggerUtil.log(message);
	}

	/**
	 * Quit browser.
	 */
	public static void quitBrowser() {
		driver.close();
	}

	/**
	 * Gets the driver.
	 *
	 * @param scenario the scenario
	 * @return the driver
	 */
	public static WebDriver getDriver(Scenario scenario) {

		// if (StringUtils.isEmpty(browser)) {
		String browser = EnvParameters.WEB_BROWSER;
		// }
		if (browser.equals("chrome")) {
			try {
				setupChromeDriver();
			} catch (Exception e) {
				LoggerUtil.log(e.getMessage(), Level.DEBUG);
			}
		}
		if  (browser.equals("chromium")) {
			try {
				setupChromiumDriver();
			} catch (Exception e) {
				LoggerUtil.log(e.getMessage(), Level.DEBUG);
			}
		}
		if (browser.equals("iexplore")) {
			try {
				setupIEDriver();
			} catch (Exception e) {
				LoggerUtil.log(e.getMessage(), Level.DEBUG);
			}
		}
		if (browser.equals("edge")) {
			try {
				setupEdgeDriver();
			} catch (Exception e) {
				LoggerUtil.log(e.getMessage(), Level.DEBUG);
			}
		}
		if (browser.equals("firefox")) {
			try {
				setupGeckoDriver();
			} catch (Exception e) {
				LoggerUtil.log(e.getMessage(), Level.DEBUG);
			}
		}
		if (browser.equals("headless")) {
			try {
				setupPhantomJSDriver();
			} catch (Exception e) {
				LoggerUtil.log(e.getMessage(), Level.DEBUG);
			}
		}

		String browPlatform = null;
		if (StringUtils.isNotEmpty(EnvParameters.BROWSER_PLATFORM)) {
			browPlatform = EnvParameters.BROWSER_PLATFORM;
		}

		String browVersion = null;
		if (StringUtils.isNotEmpty(EnvParameters.BROWSER_VERSION)) {
			browVersion = EnvParameters.BROWSER_VERSION;
		}

		String scrResolution = null;
		if (StringUtils.isNotEmpty(EnvParameters.SCREEN_RESOLUTION)) {
			scrResolution = EnvParameters.SCREEN_RESOLUTION;
		}

		try {
			driver = buildWebDriver(browser, browPlatform, browVersion, scrResolution, scenario.getName());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		if (driver instanceof ChromeDriver || driver instanceof OperaDriver || driver instanceof AndroidDriver
				|| driver instanceof IOSDriver) {
			// do nothing
		} else {
			driver.manage().window().maximize();
		}
		return driver;

	}

	/**
	 * Builds the web driver.
	 *
	 * @param browser the browser
	 * @param browPlatform the brow platform
	 * @param browVersion the brow version
	 * @param scrResolution the scr resolution
	 * @param scenarioName the scenario name
	 * @return the web driver
	 * @throws MalformedURLException the malformed URL exception
	 */
	@SuppressWarnings("deprecation")
	public static WebDriver buildWebDriver(String browser, String browPlatform, String browVersion,
			String scrResolution, String scenarioName) throws MalformedURLException {

		if (EnvParameters.EXECUTION_ENV.toLowerCase().trim().toString().equals("saucelabs")) {
			DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

			/* Note: Verify the browser and browser version. */

			String sauceBrowser = EnvParameters.BROWSER_NAME;

			if (sauceBrowser.trim().toLowerCase().equals("ff") || sauceBrowser.trim().toLowerCase().equals("firefox")) {

				desiredCapabilities = DesiredCapabilities.firefox();

			} else if (sauceBrowser.trim().toLowerCase().equals("chrome")
					|| sauceBrowser.trim().toLowerCase().equals("googlechrome")) {

				ChromeOptions options = new ChromeOptions();
				if (EnvParameters.ENABLE_EXTENSION) {
					String[] extensionList = OSUtils.getFileList("extensions", "crx");
					for (int i = 0; i < extensionList.length; i++) {
						options.addExtensions(new File("extensions" + File.separator + extensionList[i]));
					}
				}
				desiredCapabilities = DesiredCapabilities.chrome();
				desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);

			} else if (sauceBrowser.trim().toLowerCase().equals("ie")
					|| sauceBrowser.trim().toLowerCase().equals("iexplore")) {
				desiredCapabilities = DesiredCapabilities.internetExplorer();

			} else if (browser.trim().toLowerCase().equals("edge")) {
				desiredCapabilities = DesiredCapabilities.edge();

			} else if (sauceBrowser.trim().toLowerCase().equals("safari")) {
				desiredCapabilities = DesiredCapabilities.safari();

			} else if (sauceBrowser.trim().toLowerCase().equals("opera")) {
				desiredCapabilities = DesiredCapabilities.opera();
			}

			desiredCapabilities.setCapability("platform", browPlatform);
			desiredCapabilities.setCapability("version", browVersion);
			desiredCapabilities.setCapability("screenResolution", scrResolution);
			if (EnvParameters.SAUCE_CUSTOM_TESTNAME == null) {
				desiredCapabilities.setCapability("name", scenarioName);
			} else {
				desiredCapabilities.setCapability("name", EnvParameters.SAUCE_CUSTOM_TESTNAME + "-" + scenarioName);
			}
			String SauceURL = "http://" + EnvParameters.SAUCE_USER + ":" + EnvParameters.SAUCE_APIKEY
					+ "@ondemand.saucelabs.com:80/wd/hub";

			driver = new RemoteWebDriver(new URL(SauceURL), desiredCapabilities);

		} else if (EnvParameters.EXECUTION_ENV.toLowerCase().trim().toString().equals("local")) {

			if (browser.equals("firefox")) {
				String mimeTypes = "application/zip,application/octet-stream,image/jpeg,application/vnd.ms-outlook,text/html,application/pdf";
				FirefoxProfile firefoxProfile = new FirefoxProfile();
				firefoxProfile.setAcceptUntrustedCertificates(true);
				firefoxProfile.setAssumeUntrustedCertificateIssuer(true);
				firefoxProfile.setPreference("browser.download.folderList", 2); // browser.download.folderList;2
				firefoxProfile.setPreference("browser.download.dir",
						System.getProperty("user.home") + File.separator + "Downloads" + File.separator);
				// firefoxProfile.setPreference("browser.download.manager.alertOnEXEOpen",
				// false);//Not There
				firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", mimeTypes);
				firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);// Not there
				// firefoxProfile.setPreference("browser.download.useDownloadDir", false);//it
				// was true here
				firefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
				firefoxProfile.setPreference("browser.download.manager.closeWhenDone", true);
				// firefoxProfile.setPreference("browser.download.manager.showAlertOnComplete",
				// false);//not there
				// firefoxProfile.setPreference("browser.download.manager.useWindow",
				// false);//not there
				// firefoxProfile.setPreference("browser.download.panel.shown", true);
				FirefoxBinary firefoxBinary = new FirefoxBinary();
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.setBinary(firefoxBinary);
				firefoxOptions.setProfile(firefoxProfile);
				if (!EnvParameters.GRID_ENABLED) {
					driver = new FirefoxDriver(firefoxOptions);
				} else {
					DesiredCapabilities FFcapability = DesiredCapabilities.firefox();
					FFcapability.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
					if (browPlatform != null) {
						FFcapability.setCapability(CapabilityType.PLATFORM, browPlatform);
					}
					if (StringUtils.isNotEmpty(browVersion)) {
						FFcapability.setCapability(CapabilityType.VERSION, browVersion);
					}
					driver = new RemoteWebDriver(new URL(
							"http://" + EnvParameters.HUB_IP + ":".concat(EnvParameters.HUB_PORT).concat("/wd/hub")),
							FFcapability);
				}
			} else if (browser.equals("iexplore")) {
				DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
				ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				try {
					if (!EnvParameters.GRID_ENABLED) {
						driver = new InternetExplorerDriver(ieCapabilities);
					} else {
						if (browPlatform != null) {
							ieCapabilities.setCapability(CapabilityType.PLATFORM, browPlatform);
						}
						if (StringUtils.isNotEmpty(browVersion)) {
							ieCapabilities.setCapability(CapabilityType.VERSION, browVersion);
						}
						driver = new RemoteWebDriver(new URL("http://" + EnvParameters.HUB_IP
								+ ":".concat(EnvParameters.HUB_PORT).concat("/wd/hub")), ieCapabilities);
					}
				} catch (Exception e) {
					Reporter.log("Cannot create InternetExplorerDriver", true);
					killIEDriver();
				}
			} else if (browser.equals("edge")) {
				DesiredCapabilities capabilities = DesiredCapabilities.edge();
				try {
					if (!EnvParameters.GRID_ENABLED) {
						driver = new EdgeDriver(capabilities);
					} else {
						if (browPlatform != null) {
							capabilities.setCapability(CapabilityType.PLATFORM, browPlatform);
						}
						if (StringUtils.isNotEmpty(browVersion)) {
							capabilities.setCapability(CapabilityType.VERSION, browVersion);
						}
						driver = new RemoteWebDriver(new URL("http://" + EnvParameters.HUB_IP
								+ ":".concat(EnvParameters.HUB_PORT).concat("/wd/hub")), capabilities);
					}
				} catch (Exception e) {
					killEdgeDriver();
					Reporter.log("Cannot instantiate EdgeDriver", true);
					throw new SkipException("Cannot instantiate EdgeDriver:" + e.getMessage());
				}

			} else if (browser.equals("chrome")) {
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				HashMap<String, Object> chromePrefs = new HashMap<String, Object>();

				chromePrefs.put("profile.default_content_settings.popups", 0);
				chromePrefs.put("download.default_directory",
						System.getProperty("user.home") + File.separator + "Downloads");

				ChromeOptions options = new ChromeOptions();
				if (EnvParameters.ENABLE_EXTENSION) {
					String[] extensionList = OSUtils.getFileList("extensions", "crx");
					for (int i = 0; i < extensionList.length; i++) {
						options.addExtensions(new File("extensions" + File.separator + extensionList[i]));
					}
				}

				HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
				options.setExperimentalOption("prefs", chromePrefs);
				options.addArguments("--test-type");
				options.addArguments("--start-maximized");

				capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);

				if (!EnvParameters.GRID_ENABLED) {
					driver = new ChromeDriver(options);
				} else {
					/*if (browPlatform != null) {
						capabilities.setCapability(CapabilityType.PLATFORM, browPlatform);
					}
					if (StringUtils.isNotEmpty(browVersion)) {
						capabilities.setCapability(CapabilityType.VERSION, browVersion);
					}*/
					driver = new RemoteWebDriver(new URL(
							"http://" + EnvParameters.HUB_IP + ":".concat(EnvParameters.HUB_PORT).concat("/wd/hub")),
							capabilities);
				}
			} else if (browser.equals("safari")) {
				DesiredCapabilities capabilities = DesiredCapabilities.safari();
				SafariOptions opt = new SafariOptions();
				// opt.useCleanSession(false);
				capabilities.setCapability(SafariOptions.CAPABILITY, opt);
				if (!EnvParameters.GRID_ENABLED) {
					return new SafariDriver(capabilities);
				} else {

					throw new SkipException(
							"Parallel Mode is not applicable for Safari browser, please disable parallel mode");
				}
			} else if (browser.equals("opera")) {
				if (!EnvParameters.GRID_ENABLED) {
					return new OperaDriver();
				} else {
					throw new SkipException(
							"Parallel Mode is not applicable for Opera browser, please disable parallel mode");
				}

			} else if (browser.equals("headless")) {
				try {
					DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
					((DesiredCapabilities) capabilities).setJavascriptEnabled(true);
					((DesiredCapabilities) capabilities).setCapability("takesScreenshot", true);
					driver = new PhantomJSDriver(capabilities);
					return driver;
				} catch (Exception e) {
					Reporter.log("Cannot create headless webdriver"+ e.getMessage(), true);
					killPhantomjsDriver();
				}
			} else if (browser.equals("chromium")) {
				ChromeOptions desktopOptions = new ChromeOptions();
				desktopOptions.setBinary(EnvParameters.DESKTOP_APP);
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability(ChromeOptions.CAPABILITY, desktopOptions);
				driver = new ChromeDriver(capabilities);
			}  
			else {
				throw new SkipException("Browser " + browser + " not supported by this framework");
			}
		} else {
			throw new SkipException(
					EnvParameters.EXECUTION_ENV + " is a invalid Execution Environment. Please use local or saucelabs");
		}

		return driver;

	}

	/**
	 * Kills the running process IEDriverServer.exe
	 */
	private static void killIEDriver() {
		String _processName = "IEDriverServer.exe";
		if (OSUtils.isProcessRuning(_processName) == true)
			OSUtils.killProcess(_processName);
	}

	/**
	 * Kills the running process phantomjsdriver.exe
	 */
	private static void killPhantomjsDriver() {
		String _processName = "phantomjsdriver.exe";
		if (OSUtils.isProcessRuning(_processName) == true)
			OSUtils.killProcess(_processName);
	}
	
	/**
	 * Kills the running process IEDriverServer.exe
	 */
	private static void killEdgeDriver() {
		String _processName = "MicrosoftWebDriver.exe";
		if (OSUtils.isProcessRuning(_processName) == true)
			OSUtils.killProcess(_processName);
	}

	/**
	 * Method to setup IE driver based on the OS type.
	 *
	 * @throws Exception the exception
	 */
	private static void setupIEDriver() throws Exception {
		String ieProperty = "webdriver.ie.driver";

		File targetIEdriver32 = null;

		// dont need it for other OS because IE is not available
		if (OSUtils.getOSname() == OSUtils.OSType.windows) {
			targetIEdriver32 = new File(EnvParameters.TEST_ROOT_DIR + File.separator + "drivers" + File.separator + "ie"
					+ File.separator + "IEDriverServer.exe");
		}

		if (targetIEdriver32.exists()) {
			System.setProperty(ieProperty, targetIEdriver32.getAbsolutePath());
			return;
		} else {
			InputStream reader = null;
			if (OSUtils.getOSname() == OSUtils.OSType.windows) {
				reader = CucumberWebBase.class.getResourceAsStream("/drivers/ie/IEDriverServer.exe");
			}

			if (reader.available() > 0) {
				new File(targetIEdriver32.getParent()).mkdirs();
				FileOutputStream writer = new FileOutputStream(targetIEdriver32);
				byte[] buffer = new byte[1024];
				int length;
				while ((length = reader.read(buffer)) > 0) {
					writer.write(buffer, 0, length);
				}
				/*
				 * int ch = reader.read(); while (ch != -1) { writer.write(ch); ch =
				 * reader.read(); }
				 */
				writer.close();
				reader.close();
				targetIEdriver32.setExecutable(true, false);
				System.setProperty(ieProperty, targetIEdriver32.getAbsolutePath());
			} else
				LoggerUtil.log(" IEDriverServer.exe is not found in the jar");
		}
	}
	
	/**
	 * Method to setup Edge driver based on the OS type.
	 *
	 * @throws Exception the exception
	 */
	private static void setupEdgeDriver() throws Exception {
		String edgeProperty = "webdriver.edge.driver";

		File targetEdgedriver = null;

		if (OSUtils.getOSname() == OSUtils.OSType.windows) {
			targetEdgedriver = new File(EnvParameters.TEST_ROOT_DIR + File.separator + "drivers" + File.separator
					+ "edge" + File.separator + "MicrosoftWebDriver.exe");
		}

		if (targetEdgedriver.exists()) {
			System.setProperty(edgeProperty, targetEdgedriver.getAbsolutePath());
			return;
		} else {
			InputStream reader = null;
			if (OSUtils.getOSname() == OSUtils.OSType.windows) {
				reader = CucumberWebBase.class.getResourceAsStream("/drivers/edge/MicrosoftWebDriver.exe");
			}

			if (reader.available() > 0) {
				new File(targetEdgedriver.getParent()).mkdirs();
				FileOutputStream writer = new FileOutputStream(targetEdgedriver);
				byte[] buffer = new byte[1024];
				int length;
				while ((length = reader.read(buffer)) > 0) {
					writer.write(buffer, 0, length);
				}
				writer.close();
				reader.close();
				targetEdgedriver.setExecutable(true, false);
				System.setProperty(edgeProperty, targetEdgedriver.getAbsolutePath());
			} else
				LoggerUtil.log("MicrosoftWebDriver.exe is not found in the jar");
			throw new CustomException(
					"Framework is missing MicrosoftWebDriver.exe file required for Edge browser. Please report this issue with Falcon team");
		}
	}


	/**
	 * Method to setup Gecko Driver based on the OS type.
	 *
	 * @throws Exception the exception
	 */
	private static void setupGeckoDriver() throws Exception {

		String GeckoProp = "webdriver.gecko.driver";
		new EnvParameters();
		File targetGeckodriver = null;
		if (OSUtils.getOSname() == OSUtils.OSType.windows) {
			targetGeckodriver = new File(EnvParameters.TEST_ROOT_DIR + File.separator + "drivers" + File.separator
					+ "gecko" + File.separator + "win" + File.separator + "geckodriver.exe");
		}

		else if (OSUtils.getOSname() == OSType.mac) {
			targetGeckodriver = new File(EnvParameters.TEST_ROOT_DIR + File.separator + "drivers" + File.separator
					+ "gecko" + File.separator + "mac" + File.separator + "geckodriver");
		} else if (OSUtils.getOSname() == OSType.linux) {
			targetGeckodriver = new File(EnvParameters.TEST_ROOT_DIR + File.separator + "drivers" + File.separator
					+ "gecko" + File.separator + "linux" + File.separator + "geckodriver");
		}

		if (targetGeckodriver.exists()) {

			System.setProperty(GeckoProp, targetGeckodriver.getAbsolutePath());

			return;
		} else {
			InputStream reader = null;
			if (OSUtils.getOSname() == OSUtils.OSType.windows) {
				reader = OSUtils.class.getResourceAsStream("/drivers/gecko/win/geckodriver.exe");
			} else if (OSUtils.getOSname() == OSUtils.OSType.mac) {
				reader = OSUtils.class.getResourceAsStream("/drivers/gecko/mac/geckodriver");
			} else if (OSUtils.getOSname() == OSUtils.OSType.linux) {
				reader = OSUtils.class.getResourceAsStream("/drivers/gecko/linux/geckodriver");
				LoggerUtil.log("Copying the driver is successfull");
			} else {
				LoggerUtil.log("The gecko driver copying is not successfull");
			}

			if (reader.available() > 0) {
				new File(targetGeckodriver.getParent()).mkdirs();
				FileOutputStream writer = new FileOutputStream(targetGeckodriver);
				byte[] buffer = new byte[1024];
				int length;
				while ((length = reader.read(buffer)) > 0) {
					writer.write(buffer, 0, length);
				}
				/*
				 * int ch = reader.read(); while (ch != -1) { writer.write(ch); ch =
				 * reader.read(); }
				 */
				writer.close();
				reader.close();
				targetGeckodriver.setExecutable(true, false);
				System.setProperty(GeckoProp, targetGeckodriver.getAbsolutePath());
			} else {
				LoggerUtil.log("Cannot find geckodriver in the jar");
			}
		}
	}

	/**
	 * Method to setup Chrome Driver based on the OS type.
	 *
	 * @throws Exception the exception
	 */
	private static void setupChromeDriver() throws Exception {

		String ChromProp = "webdriver.chrome.driver";
		new EnvParameters();
		File targetChromedriver = null;
		if (OSUtils.getOSname() == OSUtils.OSType.windows) {
			targetChromedriver = new File(EnvParameters.TEST_ROOT_DIR + File.separator + "drivers" + File.separator
					+ "chrome" + File.separator + "win" + File.separator + "chromedriver.exe");
		}

		else if (OSUtils.getOSname() == OSType.mac) {
			targetChromedriver = new File(EnvParameters.TEST_ROOT_DIR + File.separator + "drivers" + File.separator
					+ "chrome" + File.separator + "mac" + File.separator + "chromedriver");
		} else if (OSUtils.getOSname() == OSType.linux) {
		//	targetChromedriver = new File(EnvParameters.TEST_ROOT_DIR + File.separator + "drivers" + File.separator
			//		+ "chrome" + File.separator + "linux" + File.separator + "chromedriver");
			targetChromedriver = new File ("/usr/bin/chromedriver");
		}

		if (targetChromedriver.exists()) {

			System.setProperty(ChromProp, targetChromedriver.getAbsolutePath());

			return;
		} else {
			InputStream reader = null;
			if (OSUtils.getOSname() == OSUtils.OSType.windows) {
				reader = OSUtils.class.getResourceAsStream("/drivers/chrome/win/chromedriver.exe");
			} else if (OSUtils.getOSname() == OSUtils.OSType.mac) {
				reader = OSUtils.class.getResourceAsStream("/drivers/chrome/mac/chromedriver");
			} else if (OSUtils.getOSname() == OSUtils.OSType.linux) {
				reader = OSUtils.class.getResourceAsStream("/drivers/chrome/linux/chromedriver");
				LoggerUtil.log("Copying the driver is successfull");
			} else {
				LoggerUtil.log("The chrome driver copying is not successfull");
			}

			if (reader.available() > 0) {
				new File(targetChromedriver.getParent()).mkdirs();
				FileOutputStream writer = new FileOutputStream(targetChromedriver);
				byte[] buffer = new byte[1024];
				int length;
				while ((length = reader.read(buffer)) > 0) {
					writer.write(buffer, 0, length);
				}
				writer.close();
				reader.close();
				targetChromedriver.setExecutable(true, false);
				System.setProperty(ChromProp, targetChromedriver.getAbsolutePath());
			} else {
				LoggerUtil.log("Cannot find chromedriver in the jar");
			}
		}
	}

	/**
	 * Method to setup PhantomJS driver (headless) based on the OS type.
	 *
	 * @throws Exception the exception
	 */
	private static void setupPhantomJSDriver() throws Exception {

		String PhantProp = "phantomjs.binary.path";
		new EnvParameters();
		File targetPhantomjsdriver = null;
		if (OSUtils.getOSname() == OSUtils.OSType.windows) {
			targetPhantomjsdriver = new File(EnvParameters.TEST_ROOT_DIR + File.separator + "drivers" + File.separator
					+ "phantomjs" + File.separator + "win" + File.separator + "phantomjs.exe");
		}

		else if (OSUtils.getOSname() == OSType.mac) {
			targetPhantomjsdriver = new File(EnvParameters.TEST_ROOT_DIR + File.separator + "drivers" + File.separator
					+ "phantomjs" + File.separator + "mac" + File.separator + "phantomjs");
		} else if (OSUtils.getOSname() == OSType.linux) {
			//targetPhantomjsdriver = new File(EnvParameters.TEST_ROOT_DIR + File.separator + "drivers" + File.separator
				//	+ "phantomjs" + File.separator + "linux" + File.separator + "phantomjs");
		targetPhantomjsdriver= new File ("/usr/local/bin/phantomjs");
		}

		if (targetPhantomjsdriver.exists()) {

			System.setProperty(PhantProp, targetPhantomjsdriver.getAbsolutePath());

			return;
		} else {
			InputStream reader = null;
			if (OSUtils.getOSname() == OSUtils.OSType.windows) {
				reader = OSUtils.class.getResourceAsStream("/drivers/phantomjs/win/phantomjs.exe");
			} else if (OSUtils.getOSname() == OSUtils.OSType.mac) {
				reader = OSUtils.class.getResourceAsStream("/drivers/phantomjs/mac/phantomjs");
			} else if (OSUtils.getOSname() == OSUtils.OSType.linux) {
				reader = OSUtils.class.getResourceAsStream("/drivers/phantomjs/linux/phantomjs");
				LoggerUtil.log("Copying the driver is successfull");
			} else {
				LoggerUtil.log("The phantomjs driver copying is not successfull");
			}

			if (reader.available() > 0) {
				new File(targetPhantomjsdriver.getParent()).mkdirs();
				FileOutputStream writer = new FileOutputStream(targetPhantomjsdriver);
				byte[] buffer = new byte[1024];
				int length;
				while ((length = reader.read(buffer)) > 0) {
					writer.write(buffer, 0, length);
				}
				writer.close();
				reader.close();
				targetPhantomjsdriver.setExecutable(true, false);
				System.setProperty(PhantProp, targetPhantomjsdriver.getAbsolutePath());
			} else {
				LoggerUtil.log("Cannot find phantomjsdriver in the jar");
			}
		}
	}
	
	/**
	 * Method to setup Chromium Driver based on the OS type.
	 *
	 * @throws Exception the exception
	 */
	private static void setupChromiumDriver() throws Exception {

		String ChromProp = "webdriver.chrome.driver";
		new EnvParameters();
		File targetChromedriver = null;
		if (OSUtils.getOSname() == OSUtils.OSType.windows) {
			targetChromedriver = new File(EnvParameters.TEST_ROOT_DIR + File.separator + "drivers" + File.separator
					+ "chromium" + File.separator + "win" + File.separator + "chromedriver.exe");
		}

		else if (OSUtils.getOSname() == OSType.mac) {
			targetChromedriver = new File(EnvParameters.TEST_ROOT_DIR + File.separator + "drivers" + File.separator
					+ "chromium" + File.separator + "mac" + File.separator + "chromedriver");
		} else if (OSUtils.getOSname() == OSType.linux) {
			targetChromedriver = new File(EnvParameters.TEST_ROOT_DIR + File.separator + "drivers" + File.separator
					+ "chromium" + File.separator + "linux" + File.separator + "chromedriver");
		}

		if (targetChromedriver.exists()) {

			System.setProperty(ChromProp, targetChromedriver.getAbsolutePath());

			return;
		} else {
			InputStream reader = null;
			if (OSUtils.getOSname() == OSUtils.OSType.windows) {
				reader = OSUtils.class.getResourceAsStream("/drivers/chromium/win/chromedriver.exe");
			} else if (OSUtils.getOSname() == OSUtils.OSType.mac) {
				reader = OSUtils.class.getResourceAsStream("/drivers/chromium/mac/chromedriver");
			} else if (OSUtils.getOSname() == OSUtils.OSType.linux) {
				reader = OSUtils.class.getResourceAsStream("/drivers/chromium/linux/chromedriver");
				LoggerUtil.log("Copying the driver is successfull");
			} else {
				LoggerUtil.log("The chrome driver copying is not successfull");
			}

			if (reader.available() > 0) {
				new File(targetChromedriver.getParent()).mkdirs();
				FileOutputStream writer = new FileOutputStream(targetChromedriver);
				byte[] buffer = new byte[1024];
				int length;
				while ((length = reader.read(buffer)) > 0) {
					writer.write(buffer, 0, length);
				}
				/*
				 * int ch = reader.read(); while (ch != -1) { writer.write(ch); ch =
				 * reader.read(); }
				 */
				writer.close();
				reader.close();
				targetChromedriver.setExecutable(true, false);
				System.setProperty(ChromProp, targetChromedriver.getAbsolutePath());
			} else {
				LoggerUtil.log("Cannot find chromiumdriver in the jar");
				throw new CustomException(
						"Framework is missing chromedriver file required for Chromium browser. Please report this issue with Falcon team");
			}
		}
	
	}

}
