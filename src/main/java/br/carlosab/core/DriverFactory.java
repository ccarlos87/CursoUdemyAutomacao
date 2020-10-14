package br.carlosab.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

	private static WebDriver driver;

	private DriverFactory() {
	}

	/********************* chamada do chromedriver *******************/
	public static WebDriver getDriver() {
		if (driver == null) {
			System.setProperty("webdriver.chrome.driver", "C://chromedriver/chromedriver.exe");
			driver = new ChromeDriver();
		}
		return driver;
	}

	/********************* finaliza chromedriver *******************/
	public static void killDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
