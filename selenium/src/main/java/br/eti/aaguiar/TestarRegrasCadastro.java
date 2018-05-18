package br.eti.aaguiar;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Parameterized.class)
public class TestarRegrasCadastro {
	
	private static WebDriver driver;
	private static DSL dsl;
	private static CampoTreinamentoPage page;
	
	@Parameter
	public String nome;
	
	@Parameter(value=1)
	public String sobrenome;
	
	@Parameter(value=2)
	public String sexo;
	
	@Parameter(value=3)
	public List<String> comidas;
	
	@Parameter(value=4)
	public String esportes;
	
	@Parameter(value=5)
	public String escolaridade;
	
	@Parameter(value=6)
	public String sugestoes;
	
	@Parameter(value=7)
	public String msg;
	
	@Parameters
	public static Collection<Object[]>  getCollection(){
		
		return Arrays.asList(new Object[][] {
			{"","","",Arrays.asList(),"","","","Nome é obrigatóio!"},
			{"Antonio","","",Arrays.asList(),"","","","Sobrenome é obrigatório!"},
			{"Antonio","Aguiar","",Arrays.asList(),"","","","Sexo é obrigatório!"},
			{"Antonio","Aguiar","Masculino",Arrays.asList(),"","","","Nome é obrigatório!"},
			{"Antonio","Aguiar","Masculino",Arrays.asList("Carne", "Vegetariano"),"","","","Tem certeza que é vegetariano?"},
			{"Antonio","Aguiar","Masculino",Arrays.asList("Carne"),"","","","Ok"}

		});
	}
 
	@BeforeClass
	public static void configurarClasse() {
		//driver = DriverBrowser.getDriver(DriverBrowser.CHROME);	
	}
 
	@Before
	public void configurarTeste() {
		DriverBrowser drv = new DriverBrowser();
		driver = drv.getDriver(DriverBrowser.CHROME);	
 	
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.navigate().to("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");		
		dsl = new DSL(driver);
		page = new CampoTreinamentoPage(driver);
		dsl.configurarBrowser(1200, 765, "file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}

	@After
	public void encerrarTeste() {
		driver.quit();
		System.out.println("Fim do teste.");		
	}
	
	@AfterClass
	public static void encerrarClasse() {
	}
	
	@Test
	public void validarCadastroNvo() {
		System.out.println(".. iniciando o cadastro ...");
		 
		page.setNome(nome);
		page.setSobrenome(sobrenome);
		if (sexo.equals("Masculino"))
			page.setSexoMasculino();
		else
			page.setSexoFeminino();
		
		if (comidas.contains("Carne"))
			page.setComidaCarne();
		if (comidas.contains("Frango"))
			page.setComidaFrango();;
		if (comidas.contains("Pizza"))
			page.setComidaPizza();
		if (comidas.contains("Vegetariano"))
			page.setComidaVegetariano();
		
		// page.setEscolaridade(escolaridade);
		//page.setEsporte(esportes);
		
		page.setSugestoes(sugestoes);
		
		page.cadastrar();
				
		System.out.print(msg);
		
		//Assert.assertTrue(page.obterResultadoCadastro().startsWith("Cadastrado!"));
		//Assert.assertTrue(page.obterNomeCadastro().endsWith(nome));
		//Assert.assertEquals("Sobrenome: "+nome, page.obterSobreNomeCadastro());
		//Assert.assertEquals("Sexo: "+sexo, page.obterSexoCadastro());
		//Assert.assertEquals("Comida: "+comidas, page.obterComidaCadastro());
		//Assert.assertEquals("Escolaridade: "+escolaridade, page.obterEscolaridadeCadastro());
		//Assert.assertEquals("Esportes: "+esportes, page.obterEsporteCadastro());
		
	}
}
