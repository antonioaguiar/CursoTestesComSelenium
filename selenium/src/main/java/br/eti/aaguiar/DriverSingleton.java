package br.eti.aaguiar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSingleton {

	public static String CHROME = "chrome"; 
	public static String FIREFOX = "firefox";
	private static WebDriver driver;

	public static WebDriver getDriver(String browser) {
				
		
		if (FIREFOX.equals(browser)) {
			String caminho = "/home/aguiar/Downloads/drivers/geckodriver";
			System.setProperty("webdriver.gecko.driver", caminho);
			driver = new FirefoxDriver();
		}
		
		if (CHROME.equals(browser)) {
			String caminho = "/home/aguiar/Downloads/drivers/chromedriver";
		    System.setProperty("webdriver.chrome.driver", caminho);
			driver = new ChromeDriver();
		}
		

		System.out.println("Iniciando os testes com: "+browser);
		return driver;
	}

}
