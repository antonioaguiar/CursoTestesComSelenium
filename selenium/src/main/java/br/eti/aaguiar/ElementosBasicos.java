package br.eti.aaguiar;

import java.util.List;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ElementosBasicos
{
  private static WebDriver driver;
  
  @BeforeClass
  public static void startTestClass()
  {
	 driver  = DriverSingleton.getDriver(DriverSingleton.FIREFOX);
     System.out.println("Iniciando os testes");
  }
  
  @AfterClass
  public static void endTestClass()
  {
    driver.quit();
    System.out.println("Fim dos testes");
  }
  
  @Before
  public void initTest()
  {
    System.out.println("Configurar Teste");
    
    driver.manage().window().setSize(new Dimension(1200, 765));
    driver.navigate().to("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
  }
  
  @After
  public void endTest()
  {
    System.out.println("Teste encerrado!");
  }
  
  @Test
  public void deveInteragirComRadioButton()
  {
    System.out.println(".. interagindo com radio button.");
    driver.findElement(By.id("elementosForm:sexo:0")).click();
    Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
  }
  
  @Test
  public void deveInteragirComCheckBox()
  {
    System.out.println(".. interagindo com check box.");
    driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
    
    Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
  }
  
  @Test
  public void deveInteragirComCombo()
  {
    System.out.println(".. interagindo com combo box.");
    WebElement el = driver.findElement(By.id("elementosForm:escolaridade"));
    
    Select combo = new Select(el);

    combo.selectByIndex(2);
    combo.selectByValue("superior");
    combo.selectByVisibleText("Mestrado");
    
    Assert.assertEquals("Mestrado", combo.getFirstSelectedOption().getText());
  }
  
  @Test
  public void deveVerificarValoresCombo()
  {
    System.out.println(".. verificando valores numa combo box");
    WebElement el = driver.findElement(By.id("elementosForm:escolaridade"));
    
    Select combo = new Select(el);

    List<WebElement> options = combo.getOptions();
    
    Assert.assertEquals(8L, options.size());
    
    boolean achou = true;
    for (WebElement option : options) {
      if (option.getText().equals("Mestrado"))
      {
        achou = true;
        break;
      }
    }
    Assert.assertTrue(achou);
  }
  
  @Test
  public void deveIntegarirComBotoes()
  {
    System.out.println(".. Interagindo com botões");
    WebElement el = driver.findElement(By.id("buttonSimple"));
    el.click();
    
    Assert.assertEquals("Obrigado!", el.getAttribute("value"));
  }
  
  @Test
  public void deveIntegarirComLinks()
  {
    System.out.println(".. Interagindo com links...");
    WebElement el = driver.findElement(By.linkText("Voltar"));
    el.click();
    
    el = driver.findElement(By.id("resultado"));
    
    Assert.assertEquals("Voltou!", el.getText());
  }
  
  @Test
  public void deveIntegarirComTextos()
  {
    System.out.println(".. Interagindo com textos");
    
    WebElement el = driver.findElement(By.id("elementosForm")).findElement(By.tagName("h3"));
    Assert.assertEquals("Campo de Treinamento", el.getText());
    
    WebElement el2 = driver.findElement(By.className("facilAchar"));
    Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", el2.getText());
    
    
  }
  
  @Test
  public void deveIntegarirComAlertas()
  {
    System.out.println(".. Interagindo com alertas...");
    WebElement btn = driver.findElement(By.id("alert"));
    btn.click();
     
    //criar elemento alerta 
    Alert alerta = driver.switchTo().alert();
    //verificar o texto do alerta
    String texto = alerta.getText();
    Assert.assertEquals("Alert Simples", texto);
    alerta.accept();
    
    driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
  }
  
  

  @Test
  public void deveIntegarirComConfirms()
  {
    System.out.println(".. Interagindo com confirms...");
    WebElement btn = driver.findElement(By.id("confirm"));
    
    //abrir a caixa confirm 
    btn.click();
     
    //criar elemento confirm 
    Alert confirmar = driver.switchTo().alert();
    //verificar o texto da caixa confirm
    Assert.assertEquals("Confirm Simples", confirmar.getText());
    
    //clicar em cancelar
    confirmar.dismiss();
    Assert.assertEquals("Negado", confirmar.getText());
    //fechar a caixa
    confirmar.accept();
    
    //abrir o confirm de novo para o teste do OK
    btn.click();
    
    //clicar em OK
    confirmar.accept();
    Assert.assertEquals("Confirmado", confirmar.getText());
    //fechar a caixa
    confirmar.accept();
  }
  
  @Test
  public void deveIntegarirComPrompts()
  {
    System.out.println(".. Interagindo com prompts...");
    WebElement btn = driver.findElement(By.id("prompt"));
    
    //abrir a caixa prompt
    btn.click();
 
    //criar o elemento prompot
    Alert prompt = driver.switchTo().alert();
    Assert.assertEquals("Digite um numero", prompt.getText());
    
    prompt.sendKeys("5");    
    prompt.accept();
    
    Assert.assertEquals("Era 5?", prompt.getText());
    prompt.accept();
 
  }
  
}
