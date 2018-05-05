package br.eti.aaguiar;


import java.util.List;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
 

public class TestarCadastro
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
    System.out.println("Iniciando os testes de cadastro");
  }
  
  @AfterClass
  public static void endTestClass()
  {
    driver.quit();
    System.out.println("Fim dos testes");
  }
  
  @Before
  public void setUp()
  {
    System.out.println("Configurar Teste");
    
    driver.manage().window().setSize(new Dimension(1200, 765));
    driver.navigate().to("file:///" + baseUrl + "/src/main/resources/componentes.html");
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
  public void validarCadastroNvo() {
	    System.out.println(".. iniciando o cadastro ...");

	    driver.findElement(By.id("elementosForm:nome")).click();
	    driver.findElement(By.id("elementosForm:nome")).clear();
	    driver.findElement(By.id("elementosForm:nome")).sendKeys("ANTONIO");
	    driver.findElement(By.id("elementosForm:sobrenome")).clear();
	    driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("AGUIAR");
	    driver.findElement(By.id("elementosForm:sexo:0")).click();
	    driver.findElement(By.xpath("//table[@id='elementosForm:comidaFavorita']/tbody/tr/td[3]/label")).click();
	    driver.findElement(By.xpath("//table[@id='elementosForm:comidaFavorita']/tbody/tr/td/label")).click();
	    driver.findElement(By.xpath("//table[@id='elementosForm:comidaFavorita']/tbody/tr/td[2]/label")).click();
	    driver.findElement(By.id("elementosForm:escolaridade")).click();
	    new Select(driver.findElement(By.id("elementosForm:escolaridade"))).selectByVisibleText("Especializacao");
	    driver.findElement(By.xpath("//option[@value='especializacao']")).click();
	    // ERROR: Caught exception [ERROR: Unsupported command [addSelection | id=elementosForm:esportes | label=Natacao]]
	    driver.findElement(By.xpath("//option[@value='natacao']")).click();
	    // ERROR: Caught exception [ERROR: Unsupported command [addSelection | id=elementosForm:esportes | label=Karate]]
	    driver.findElement(By.xpath("//option[@value='Karate']")).click();
	    driver.findElement(By.id("elementosForm:sugestoes")).click();
	    driver.findElement(By.id("elementosForm:sugestoes")).clear();
	    driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("CINEMA");
	    driver.findElement(By.xpath("//form[@id='elementosForm']/table/tbody/tr[3]/td[2]")).click();
	    driver.findElement(By.id("elementosForm:cadastrar")).click();
  
	    //validar se foi cadastrado com sucesso.
	    //comparar o texto do elemento:  id=resultado -> tag: span = Cadastrado!
	    //Assert.assertEquals("Cadastrado!", driver.findElement(By.id("resultado")).findElement(By.tagName("span")).getText());
  
	    try {
	    	String texto = driver.findElement(By.xpath("//div[@id='resultado']/span")).getText();
	        assertEquals("Cadastrado!", texto);
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }
  } 


  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
