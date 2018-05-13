package br.eti.aaguiar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class TestarCadastro {
	
	private static WebDriver driver;
	private static DSL dsl;

	private static CampoTreinamentoPage page;
	
	@BeforeClass
	public static void iniciarClasseTeste() {
		driver = DriverSingleton.getDriver(DriverSingleton.CHROME);
		dsl = new DSL(driver);
		page = new CampoTreinamentoPage(driver);
	}

	@AfterClass
	public static void fimClasseTeste() {
		driver.quit();
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
	public void validarCadastroNvo() {
		System.out.println(".. iniciando o cadastro ...");
		
		page.setNome("ANTONIO");
		page.setSobrenome("AGUIAR");
		page.setSexoMasculino();
		page.setComidaPizza();
		page.setEscolaridade("Especializacao");
		page.setEsporte("Natacao");
		page.setSugestoes("CINEMA");
		page.cadastrar();
				
		Assert.assertTrue(page.obterResultadoCadastro().startsWith("Cadastrado!"));
		Assert.assertTrue(page.obterNomeCadastro().endsWith("ANTONIO"));
		Assert.assertEquals("Sobrenome: AGUIAR", page.obterSobreNomeCadastro());
		Assert.assertEquals("Sexo: Masculino", page.obterSexoCadastro());
		Assert.assertEquals("Comida: Pizza", page.obterComidaCadastro());
		Assert.assertEquals("Escolaridade: especializacao", page.obterEscolaridadeCadastro());
		Assert.assertEquals("Esportes: Natacao", page.obterEsporteCadastro());
		
	}

}
