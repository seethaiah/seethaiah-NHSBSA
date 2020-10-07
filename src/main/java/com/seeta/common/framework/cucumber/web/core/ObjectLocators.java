package com.seeta.common.framework.cucumber.web.core;

import java.io.File;
import java.util.Properties;

import org.jdom2.input.SAXBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


import io.appium.java_client.MobileBy;

// TODO: Auto-generated Javadoc
/**
 * This class loads the Object Locator Property or XML files.
 */
public class ObjectLocators {
	
	/** The driver. */
	protected WebDriver driver;
	
	/** The wait. */
	protected WebDriverWait wait;
	
	/** The props. */
	protected static Properties props = new Properties();
	
	/** The builder. */
	protected static SAXBuilder builder;
	
	/** The xml file. */
	protected static File xmlFile;

	/**
	 * function to get the object locator by.
	 *
	 * @param propKey            element locator in the form locator;locator_value
	 * @return By
	 */
	public static By getBySelector(String propKey) {
		// get the value from selenium.properties and split the type and value
		String[] split = propKey.split(";");
		String type = split[0];

		// generate the By selector based on the type
		if (type.equalsIgnoreCase("id")) {
			return By.id(split[1]);
		} else if (type.equalsIgnoreCase("css")) {
			return By.cssSelector(split[1]);
		} else if (type.equalsIgnoreCase("tagname")) {
			return By.tagName(split[1]);
		} else if (type.equalsIgnoreCase("classname") || type.equalsIgnoreCase("class")) {
			return By.className(split[1]);
		} else if (type.equalsIgnoreCase("name")) {
			return By.name(split[1]);
		} else if (type.equalsIgnoreCase("xpath")) {
			return By.xpath(split[1]);
		} else if (type.equalsIgnoreCase("link")) {
			return By.linkText(split[1]);
		} else if (type.equalsIgnoreCase("AccessibilityId")) {
			return MobileBy.AccessibilityId(split[1]);
		} else if (type.equalsIgnoreCase("android")) {
			return MobileBy.AndroidUIAutomator(split[1]);
		} else if (type.equalsIgnoreCase("ios")) {
			return MobileBy.IosUIAutomation(split[1]);
		} else {
return null;		}
	}
}
