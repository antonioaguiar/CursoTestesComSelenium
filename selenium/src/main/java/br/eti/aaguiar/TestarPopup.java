package br.eti.aaguiar;

import static org.junit.Assert.fail;

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

public class TestarPopup {
	 private static WebDriver driver;
	  private static String baseUrl = System.getProperty("user.dir");
	  private static boolean acceptNextAlert = true;
	  private static StringBuffer verificationErrors = new StringBuffer();
	  
	  @BeforeClass
	  public static void startTestClass()
	  {
	    System.setProperty("webdriver.gecko.driver", "/home/aguiar/Downloads/drivers/geckodriver");
	    driver = new FirefoxDriver();
	    System.out.println("Iniciando os testes com janelas");
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
	   public void deveInteragirComJanelas() {
		 System.out.println(">>  Interação com janelas...");
		 
		  
		 driver.findElement(By.id("buttonPopUpEasy")).click();		 
		 driver.switchTo().window("Popup");		 
		 driver.findElement(By.tagName("textarea")).sendKeys("Deu certo!");		 
		 driver.close();		 
		 driver.switchTo().window("");		 
		 driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Deu certo!");
		 
	   }
	   

	   @Test
	   public void deveInteragirComJanelaSemTitulo() {
		 System.out.println(">>  Interação com janela sem titulo...");		 
		  
		 driver.findElement(By.id("buttonPopUpHard")).click();
		 
		 //janela corrente
		 System.out.println(driver.getWindowHandle());
		//todas as janelas
		 System.out.println(driver.getWindowHandles());		 
		 
		 driver.switchTo().window((String)driver.getWindowHandles().toArray()[1]);		 
		 driver.findElement(By.tagName("textarea")).sendKeys("Deu certo!");		 
		 //driver.close();		 
		 driver.switchTo().window("");		 
		 driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Deu certo!");		 
		 
	   }
}
