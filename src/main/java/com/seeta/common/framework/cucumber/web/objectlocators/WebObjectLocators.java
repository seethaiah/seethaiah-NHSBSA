package com.seeta.common.framework.cucumber.web.objectlocators;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathFactory;

import com.seeta.common.framework.cucumber.web.core.CustomException;
import com.seeta.common.framework.cucumber.web.core.EnvParameters;
import com.seeta.common.framework.cucumber.web.core.ObjectLocators;



// TODO: Auto-generated Javadoc
/**
 * The Class WebObjectLocators.
 */
public class WebObjectLocators extends ObjectLocators {

  static {

    FileInputStream webStream = null;
    try {
      if (EnvParameters.OBJECTREPO_FORMAT.equalsIgnoreCase("PROP")) {
        webStream = new FileInputStream(EnvParameters.TEST_ROOT_DIR + File.separator + "ObjectRepo"
            + File.separator + "PROP" + File.separator + "Web_ObjectRepository.properties");
        props.load(webStream);
      } else if (EnvParameters.OBJECTREPO_FORMAT.equalsIgnoreCase("XML")) {

        builder = new SAXBuilder();
        xmlFile = new File(
            new File(EnvParameters.TEST_ROOT_DIR + File.separator + "ObjectRepo" + File.separator
                + "XML" + File.separator + "Web_ObjectRepository.xml").getAbsolutePath());

        if (!xmlFile.exists()) {
          throw new CustomException("WebObjectRepository.XML file not found");

        }
      }

    } catch (IOException e) {
      props = null;

      // do nothing
      // System.err.println("Failed to read:
      // ObjectRepository.properties");
    }
  }

  /**
   * Retrieve the property value based on the property name.
   *
   * @param locatorName the locator name
   * @return property value
   * @throws CustomException the custom exception
   */
  public static String getLocator(String locatorName) throws CustomException {
    if (props == null) {
      // System.err.println("Failed to read:
      // ObjectRepository.properties");
      throw new CustomException(
          "Failed to read: Web_ObjectRepository.properties -> It is either not present or not readable");

    }
    String locvalue = props.getProperty(locatorName);
    return locvalue;
  }

  /**
   * Retrieve the property value based on the property name and page name from XML.
   *
   * @param PageName the page name
   * @param locatorName the locator name
   * @return property value
   */
  public static String getLocatorfromXML(String PageName, String locatorName) {

    String locvalue = "";
    Document doc = null;
    try {
      doc = (Document) builder.build(xmlFile);
      Element root = doc.getRootElement();
      XPathFactory xpath = XPathFactory.instance();
      Element locator = (Element) xpath
          .compile("//page[@name='" + PageName + "']/object[@name='" + locatorName + "']")
          .evaluateFirst(root);
      locvalue = locator.getChildText("locatortype") + ";" + locator.getChildText("locatorvalue");
    } catch (JDOMException | IOException e) {
      e.printStackTrace();
      locvalue = "";
    }
    return locvalue;
  }
}
