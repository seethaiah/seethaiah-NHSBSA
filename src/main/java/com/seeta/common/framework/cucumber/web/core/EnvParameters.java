package com.seeta.common.framework.cucumber.web.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


// TODO: Auto-generated Javadoc
/**
 * EnvParameters class will be loading all the environment related parameters.
 */
public class EnvParameters {

	/** The Constant PROP_FILE. */
	// General Config
	private static final String PROP_FILE = "config.properties";

	/** The Constant OBJECTREPO_FORMAT. */
	public static final String OBJECTREPO_FORMAT;
	
	/** The Constant TIME_OUT. */
	public static final int TIME_OUT;
	
	/** The Constant ENV. */
	public static final String ENV;
	
	/** The Constant TEST_ROOT_DIR. */
	public static final String TEST_ROOT_DIR;
	
	/** The Constant GRID_ENABLED. */
	public static final boolean GRID_ENABLED;
	
	/** The Constant WEB_BROWSER. */
	public static final String WEB_BROWSER;
	
	/** The Constant BROWSER_NAME. */
	public static final String BROWSER_NAME;
	
	/** The Constant BROWSER_VERSION. */
	public static final String BROWSER_VERSION;
	
	/** The Constant BROWSER_PLATFORM. */
	public static final String BROWSER_PLATFORM;
	
	/** The Constant SCREEN_RESOLUTION. */
	public static final String SCREEN_RESOLUTION;
	
	/** The Constant CAPTURE_SCREENSHOT. */
	public static final boolean CAPTURE_SCREENSHOT;
	
	/** The Constant EXECUTION_ENV. */
	public static final String EXECUTION_ENV;
	
	/** The Constant SAUCE_USER. */
	public static final String SAUCE_USER;
	
	/** The Constant SAUCE_APIKEY. */
	public static final String SAUCE_APIKEY;
	
	/** The Constant SAUCE_CUSTOM_TESTNAME. */
	public static final String SAUCE_CUSTOM_TESTNAME;
	
	/** The Constant ENABLE_EXTENSION. */
	public static final boolean ENABLE_EXTENSION;
	
	/** The Constant DESKTOP_APP. */
	public static final String DESKTOP_APP;
	
	/** The Constant HUB_IP. */
	// Grid Config
	public static final String HUB_IP;
	
	/** The Constant HUB_PORT. */
	public static final String HUB_PORT;

	/** The properties. */
	private static Properties properties = new Properties();

	static {
		// Loading General Configurations
		TEST_ROOT_DIR = System.getProperty("user.dir");
		FileInputStream in = null;
		try {
			in = new FileInputStream(TEST_ROOT_DIR + File.separator + PROP_FILE);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new CustomException(PROP_FILE + " -> Config file not found, Please specify the correct config file");
		}

		try {
			properties.load(in);
			in.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new CustomException("Failure loading property file -> " + e.getMessage());
		}
		// Load EXECUTION_ENV
		if (System.getProperty("execution.env") != null
				&& !(System.getProperty("execution.env").equalsIgnoreCase(""))) {
			EXECUTION_ENV = System.getProperty("execution.env");
		} else if (properties.getProperty("execution.env") != null
				&& !(properties.getProperty("execution.env").equalsIgnoreCase(""))) {
			EXECUTION_ENV = properties.getProperty("execution.env");
		} else {
			throw new CustomException(
					"execution.env property not set, " + "it is mandate to define the execution.env property");
		}

		// Load SAUCE_USER
		if (System.getProperty("sauce.user") != null && !(System.getProperty("sauce.user").equalsIgnoreCase(""))) {
			SAUCE_USER = System.getProperty("sauce.user");
		} else if (properties.getProperty("sauce.user") != null
				&& !(properties.getProperty("sauce.user").equalsIgnoreCase(""))) {
			SAUCE_USER = properties.getProperty("sauce.user");
		} else {
			throw new CustomException(
					"sauce.user property not set, " + "it is mandate to define the saucelabs.url property");
		}

		// Load SAUCE_APIKEY
		if (System.getProperty("sauce.apikey") != null && !(System.getProperty("sauce.apikey").equalsIgnoreCase(""))) {
			SAUCE_APIKEY = System.getProperty("sauce.apikey");
		} else if (properties.getProperty("sauce.apikey") != null
				&& !(properties.getProperty("sauce.apikey").equalsIgnoreCase(""))) {
			SAUCE_APIKEY = properties.getProperty("sauce.apikey");
		} else {
			throw new CustomException(
					"sauce.apikey property not set, " + "it is mandate to define the saucelabs.url property");
		}

		// Load SAUCE_CUSTOM_TESTNAME
		if (EnvParameters.EXECUTION_ENV.equals("saucelabs")) {
			if (System.getProperty("sauce.custom.testname") != null
					&& !(System.getProperty("sauce.custom.testname").equalsIgnoreCase(""))) {
				SAUCE_CUSTOM_TESTNAME = System.getProperty("sauce.custom.testname");
			} else if (properties.getProperty("sauce.custom.testname") != null
					&& !(properties.getProperty("sauce.custom.testname").equalsIgnoreCase(""))) {
				SAUCE_CUSTOM_TESTNAME = properties.getProperty("sauce.custom.testname");
			} else {
				SAUCE_CUSTOM_TESTNAME = null;
			}
		} else {
			SAUCE_CUSTOM_TESTNAME = null;
		}

		// Load OBJECTREPO_FORMAT
		if (System.getProperty("objectrepo.format") != null
				&& !(System.getProperty("objectrepo.format").equalsIgnoreCase(""))) {
			OBJECTREPO_FORMAT = System.getProperty("objectrepo.format");
		} else if (properties.getProperty("objectrepo.format") != null
				&& !(properties.getProperty("objectrepo.format").equalsIgnoreCase(""))) {
			OBJECTREPO_FORMAT = properties.getProperty("objectrepo.format");
		} else {
			throw new CustomException(
					"objectrepo.format property not set, " + "it is mandate to define the objectrepo.format property");
		}

		// Load TIME_OUT from time.out
		if (System.getProperty("time.out") != null && !(System.getProperty("time.out").equalsIgnoreCase(""))) {
			TIME_OUT = Integer.parseInt(System.getProperty("time.out"));
		} else if (properties.getProperty("time.out") != null
				&& !(properties.getProperty("time.out").equalsIgnoreCase(""))) {
			TIME_OUT = Integer.parseInt(properties.getProperty("time.out"));
		} else {
			throw new CustomException("Time out property not set, " + "it is mandate to define the Time out property");
		}

		// Load GRID_ENABLED from grid.enabled property
		if (System.getProperty("grid.enabled") != null && !(System.getProperty("grid.enabled").equals(""))) {
			GRID_ENABLED = System.getProperty("grid.enabled").equalsIgnoreCase("true") ? true : false;
		} else if (properties.getProperty("grid.enabled") != null
				&& !(properties.getProperty("grid.enabled").equals(""))) {

			GRID_ENABLED = properties.getProperty("grid.enabled").equalsIgnoreCase("true") ? true : false;
		} else {

			GRID_ENABLED = false;
		}

		// Load HUB_IP from hub.ip
		if (GRID_ENABLED) {
			if (System.getProperty("hub.ip") != null && !(System.getProperty("hub.ip").equals(""))) {
				HUB_IP = System.getProperty("hub.ip");
			} else if (properties.getProperty("hub.ip") != null && !(properties.getProperty("hub.ip").equals(""))) {
				HUB_IP = properties.getProperty("hub.ip");
			} else {
				throw new CustomException(
						"hub.ip property not set, " + "it is mandate for tests to run on selenium grid");
			}
		} else {
			HUB_IP = null;
		}

		// Load HUB_PORT from hub.port
		if (GRID_ENABLED) {
			if (System.getProperty("hub.port") != null && !(System.getProperty("hub.port").equals(""))) {
				HUB_PORT = System.getProperty("hub.port");
			} else if (properties.getProperty("hub.port") != null && !(properties.getProperty("hub.port").equals(""))) {
				HUB_PORT = properties.getProperty("hub.port");
			} else {
				throw new CustomException(
						"hub.port property not set, " + "it is mandate for tests to run on selenium grid");
			}
		} else {
			HUB_PORT = null;
		}

		// Load ENV from Env property

		if (System.getProperty("Env") != null && !(System.getProperty("Env").equalsIgnoreCase(""))) {
			ENV = System.getProperty("Env");
		} else if (properties.getProperty("Env") != null && !(properties.getProperty("Env").equalsIgnoreCase(""))) {
			ENV = properties.getProperty("Env");
		} else {
			throw new CustomException("Environment property not set, " + "it is mandatory to set the Environment");
		}

		// Load WEB_BROWSER from Browser property
		if (System.getProperty("Browser") != null && !(System.getProperty("Browser").equalsIgnoreCase(""))) {
			WEB_BROWSER = System.getProperty("Browser");
		} else if (properties.getProperty("Browser") != null
				&& !(properties.getProperty("Browser").equalsIgnoreCase(""))) {
			WEB_BROWSER = properties.getProperty("Browser");

		} else {
			throw new CustomException("Browser property not set");
		}
		
		// Load DESKTOP_APP from desktopApp
		if (WEB_BROWSER.equalsIgnoreCase("chromium") && EnvParameters.EXECUTION_ENV.equals("local")) {
		if (System.getProperty("desktopApp") != null && !(System.getProperty("desktopApp").equalsIgnoreCase(""))) {
			DESKTOP_APP = System.getProperty("desktopApp");
		} else if (properties.getProperty("desktopApp") != null
				&& !(properties.getProperty("desktopApp").equalsIgnoreCase(""))) {
			DESKTOP_APP = properties.getProperty("desktopApp");
		} else {
			throw new CustomException(
					"desktopApp property not set, " + "it is mandate to define the desktopApp property");
		}
		} else {
			DESKTOP_APP = null;
		}


		// Load BROWSER_NAME from browser.name property
		if (System.getProperty("browser.name") != null && !(System.getProperty("browser.name").equalsIgnoreCase(""))) {
			BROWSER_NAME = System.getProperty("browser.name");
		} else if (properties.getProperty("browser.name") != null
				&& !(properties.getProperty("browser.name").equalsIgnoreCase(""))) {
			BROWSER_NAME = properties.getProperty("browser.name");
		} else {
			BROWSER_NAME = "";
		}

		// Load BROWSER_VERSION from browser.version property
		if (System.getProperty("browser.version") != null
				&& !(System.getProperty("browser.version").equalsIgnoreCase(""))) {
			BROWSER_VERSION = System.getProperty("browser.version");
		} else if (properties.getProperty("browser.version") != null
				&& !(properties.getProperty("browser.version").equalsIgnoreCase(""))) {
			BROWSER_VERSION = properties.getProperty("browser.version");
		} else {
			BROWSER_VERSION = "";
		}

		// Load BROWSER_PLATFORM from browser.platform property
		if (System.getProperty("browser.platform") != null
				&& !(System.getProperty("browser.platform").equalsIgnoreCase(""))) {
			BROWSER_PLATFORM = System.getProperty("browser.platform");
		} else if (properties.getProperty("browser.platform") != null
				&& !(properties.getProperty("browser.platform").equalsIgnoreCase(""))) {
			BROWSER_PLATFORM = properties.getProperty("browser.platform");
		} else {
			BROWSER_PLATFORM = "";
		}

		// Load SCREEN_RESOLUTION from screen.resolution property
		if (System.getProperty("screen.resolution") != null
				&& !(System.getProperty("screen.resolution").equalsIgnoreCase(""))) {
			SCREEN_RESOLUTION = System.getProperty("screen.resolution");
		} else if (properties.getProperty("screen.resolution") != null
				&& !(properties.getProperty("screen.resolution").equalsIgnoreCase(""))) {
			SCREEN_RESOLUTION = properties.getProperty("screen.resolution");
		} else {
			SCREEN_RESOLUTION = "";
		}

		// Load CAPTURE SCREENSHOT from capture.screenshot
		if (System.getProperty("capture.screenshot") != null
				&& !(System.getProperty("capture.screenshot").equalsIgnoreCase(""))) {
			CAPTURE_SCREENSHOT = Boolean.valueOf(System.getProperty("capture.screenshot"));
		} else if (properties.getProperty("capture.screenshot") != null
				&& !(properties.getProperty("capture.screenshot").equalsIgnoreCase(""))) {
			CAPTURE_SCREENSHOT = Boolean.valueOf(properties.getProperty("capture.screenshot"));
			System.setProperty("capture.screenshot", properties.getProperty("capture.screenshot"));
		} else {
			throw new CustomException("Video Capture property not set, " + "it is mandate define the Capture Video");
		}

		// Load ENABLE EXTENSION from enable.extension
		if (System.getProperty("enable.extension") != null
				&& !(System.getProperty("enable.extension").equalsIgnoreCase(""))) {
			ENABLE_EXTENSION = Boolean.valueOf(System.getProperty("enable.extension"));
		} else if (properties.getProperty("enable.extension") != null
				&& !(properties.getProperty("enable.extension").equalsIgnoreCase(""))) {
			ENABLE_EXTENSION = Boolean.valueOf(properties.getProperty("enable.extension"));
			System.setProperty("enable.extension", properties.getProperty("enable.extension"));
		} else {
			ENABLE_EXTENSION = false;
		}

	}

}