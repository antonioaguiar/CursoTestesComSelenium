package br.eti.aaguiar;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSingleton {

	public static String CHROME = "chrome"; 
	public static String FIREFOX = "firefox";
	private static WebDriver driver;
	private static String SO = System.getProperty("os.name").toLowerCase();
	private static String PATHDRIVER =  System.getProperty("user.dir");
	
	public static WebDriver getDriver(String browser) {
		String drivername = "";
		String sep = File.separator;
		PATHDRIVER += sep+"src"+sep+"main"+sep+"resources"+sep;
		
		if (FIREFOX.equals(browser)) {
			drivername = "geckodriver";
			if (SO.indexOf("win") >= 0){
				drivername +=".exe";
			}
			String caminho = PATHDRIVER+drivername;
			System.setProperty("webdriver.gecko.driver", caminho);
			driver = new FirefoxDriver();
		}
		
		if (CHROME.equals(browser)) {
			drivername = "chromedriver";
			if (SO.indexOf("win") >= 0){
				drivername +=".exe";
			}
			String caminho = PATHDRIVER+drivername;
		    System.setProperty("webdriver.chrome.driver", caminho);
			driver = new ChromeDriver();
		}
		

		System.out.println("Iniciando os testes com: "+browser);
		return driver;
	}

}
