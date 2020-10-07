package com.seeta.common.framework.cucumber.web.core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;

// TODO: Auto-generated Javadoc
/**
 * PageFactory will initiate the corresponding driver 
 * that will be used in the tests. 
 */
public class PageFactory {
	
	/**
	 * Instantiate page.
	 *
	 * @param <T> the generic type
	 * @param driver the driver
	 * @param pageClassToProxy the page class to proxy
	 * @return the t
	 */
	public static <T> T instantiatePage(WebDriver driver, Class<T> pageClassToProxy) {
		try {
			try {
				Method m = null;
				if (driver instanceof AndroidDriver || driver instanceof IOSDriver){
					m = pageClassToProxy.getMethod("setDriver", AppiumDriver.class);
				}else{
					m = pageClassToProxy.getMethod("setDriver", WebDriver.class);
				} 
			//	m = pageClassToProxy.getMethod("setDriver", WebDriver.class);
				T classInstance = pageClassToProxy.newInstance();
				Object parameters[] = {driver};
				m.invoke(classInstance, parameters);
				return classInstance;
			} catch (NoSuchMethodException e) {
				System.out.println(e.getMessage());
				throw new RuntimeException("Page Class doesnot inherit WebDriverBase/AppiumBase class",e);
			}
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

}
