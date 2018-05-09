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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NovosTestesComDSL {

	private static WebDriver driver;
	private static DSL dsl;

	@BeforeClass
	public static void iniciarClasseTeste() {
		driver = DriverSingleton.getDriver(DriverSingleton.FIREFOX);
		dsl = new DSL(driver);
	}

	@AfterClass
	public static void fimClasseTeste() {
		// driver.quit();
		System.out.println("Fim dos testes");
	}

	@Before
	public void configurarTeste() {
		dsl.configurarBrowser(1200, 765,
				"file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}

	@After
	public void encerrarTeste() {
		System.out.println("Teste encerrado!");
	}

	@Test
	public void deveInteragirComRadioButton() {
		System.out.println(".. interagindo com radio button.");

		dsl.marcarElementoRadio("elementosForm:sexo:0");

		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
	}

	@Test
	public void deveInteragirComCheckBox() {
		System.out.println(".. interagindo com check box.");
		dsl.marcarElementoCheckBox("elementosForm:comidaFavorita:2");
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
	}

	@Test
	public void deveInteragirComCombo() {
		System.out.println(".. interagindo com combo box.");

		dsl.selecionarComboIndice("elementosForm:escolaridade", 2);

		Assert.assertEquals("2o grau incompleto", dsl.obterValorCombo("elementosForm:escolaridade"));

	}

	@Test
	public void deveVerificarValoresCombo() {
		System.out.println(".. verificando valores numa combo box");

		List<WebElement> options = dsl.obterValoresCombo("elementosForm:escolaridade");
		Assert.assertEquals(8L, options.size());
		boolean achou = true;
		for (WebElement option : options) {
			if (option.getText().equals("2o grau incompleto")) {
				achou = true;
				break;
			}
		}
		Assert.assertTrue(achou);
	}

	@Test
	public void deveIntegarirComBotoes() {
		System.out.println(".. Interagindo com botões");
		dsl.clicarBotao("buttonSimple");
		Assert.assertEquals("Obrigado!", dsl.obterValor("buttonSimple"));
	}

	@Test
	public void deveIntegarirComLinks() {
		System.out.println(".. Interagindo com links...");

		dsl.clicarLink(By.linkText("Voltar"));

		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
	}

	@Test
	public void deveIntegarirComTextos() {
		System.out.println(".. Interagindo com textos");

		WebElement el = driver.findElement(By.id("elementosForm")).findElement(By.tagName("h3"));
		Assert.assertEquals("Campo de Treinamento", el.getText());

		WebElement el2 = driver.findElement(By.className("facilAchar"));
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", el2.getText());

	}

	@Test
	public void deveIntegarirComAlertas() {
		System.out.println(".. Interagindo com alertas...");

		WebElement btn = driver.findElement(By.id("alert"));
		btn.click();

		// criar elemento alerta
		Alert alerta = driver.switchTo().alert();
		// verificar o texto do alerta
		String texto = alerta.getText();
		Assert.assertEquals("Alert Simples", texto);
		alerta.accept();

		driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
	}

	@Test
	public void deveIntegarirComConfirms() {
		System.out.println(".. Interagindo com confirms...");
		WebElement btn = driver.findElement(By.id("confirm"));

		// abrir a caixa confirm
		btn.click();

		// criar elemento confirm
		Alert confirmar = driver.switchTo().alert();
		// verificar o texto da caixa confirm
		Assert.assertEquals("Confirm Simples", confirmar.getText());

		// clicar em cancelar
		confirmar.dismiss();
		Assert.assertEquals("Negado", confirmar.getText());
		// fechar a caixa
		confirmar.accept();

		// abrir o confirm de novo para o teste do OK
		btn.click();

		// clicar em OK
		confirmar.accept();
		Assert.assertEquals("Confirmado", confirmar.getText());
		// fechar a caixa
		confirmar.accept();
	}

	@Test
	public void deveIntegarirComPrompts() {
		System.out.println(".. Interagindo com prompts...");
		WebElement btn = driver.findElement(By.id("prompt"));

		// abrir a caixa prompt
		btn.click();

		// criar o elemento prompot
		Alert prompt = driver.switchTo().alert();
		Assert.assertEquals("Digite um numero", prompt.getText());

		prompt.sendKeys("5");
		prompt.accept();

		Assert.assertEquals("Era 5?", prompt.getText());
		prompt.accept();

	}

}
