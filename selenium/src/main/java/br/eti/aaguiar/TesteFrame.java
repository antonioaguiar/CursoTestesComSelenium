package br.eti.aaguiar;


import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
 
 

public class TesteFrame
{
  private static WebDriver driver;
  private static String baseUrl = System.getProperty("user.dir");
  private static boolean acceptNextAlert = true;
  private static StringBuffer verificationErrors = new StringBuffer();
  
  @BeforeClass
  public static void startTestClass()
  {
    System.setProperty("webdriver.gecko.driver", "/home/aguiar/Downloads/drivers/geckodriver");
    driver = new FirefoxDriver();
    System.out.println("Iniciando os testes com frames");
  }
  
  @AfterClass
  public static void endTestClass()
  {
    //driver.quit();
    System.out.println("Fim dos testes");
  }
  
  @Before
  public void setUp()
  {
    System.out.println("Configurar Teste");
    
    driver.manage().window().setSize(new Dimension(1200, 765));
    driver.navigate().to("file:///" + baseUrl + "/src/main/resources/componentes.html");
    //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  
  @After
  public void tearDown() throws Exception {
    String verificationErrorString = verificationErrors.toString();
    System.out.println(verificationErrorString);
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
    System.out.println("Teste encerrado!");
  }
  
   @Test
   public void deveInteragirComFrames() {
	 System.out.println(">>  Interação com frames...");
	 
	 driver.switchTo().frame("frame1");
	 driver.findElement(By.id("frameButton")).click();
	 
	 Alert alert = driver.switchTo().alert();	 
	 
	 String msg = alert.getText();
	 Assert.assertEquals("Frame OK!", msg);
	 alert.accept();
	 
	 driver.switchTo().defaultContent();
	 driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);
   }
}
