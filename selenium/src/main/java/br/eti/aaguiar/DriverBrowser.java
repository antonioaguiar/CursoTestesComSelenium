package br.eti.aaguiar;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverBrowser {

	public static String CHROME = "chrome"; 
	public static String FIREFOX = "firefox";
	private static WebDriver driver;
	
	private static String SO = System.getProperty("os.name").toLowerCase();
 
	public WebDriver getDriver(String browser) {
		String sep = File.separator;
		String path =  System.getProperty("user.dir")+sep+"src"+sep+"main"+sep+"resources"+sep;
		String drivername = "";	
		String caminho = "";
		
		if (FIREFOX.equals(browser)) {
			drivername = "geckodriver";
			if (SO.indexOf("win") >= 0){
				drivername +=".exe";
			}
			if (SO.indexOf("mac") >= 0){
				drivername ="mac/"+drivername;
			}			
			caminho = path+drivername;
			System.setProperty("webdriver.gecko.driver", caminho);
			driver = new FirefoxDriver();
		}
		
		if (CHROME.equals(browser)) {
			drivername = "chromedriver";
			if (SO.indexOf("win") >= 0){
				drivername +=".exe";
			}
			if (SO.indexOf("mac") >= 0){
				drivername ="mac/"+drivername;
			}
			
			caminho = path+drivername;
		    System.setProperty("webdriver.chrome.driver", caminho);
			driver = new ChromeDriver();
		}
		
		System.out.println("Iniciando os testes com: "+browser);
		return driver;
	}

}
