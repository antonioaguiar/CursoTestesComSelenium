package br.eti.aaguiar;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {

	private WebDriver driver;

	public DSL(WebDriver driver) {
		this.driver = driver;
	}

	public void configurarBrowser(int largura, int altura, String url) {
		System.out.println("Configurando browser para Teste");
		driver.manage().window().setSize(new Dimension(largura, altura));
		driver.navigate().to(url);
	}

	public void escrever(String id_campo, String texto) {
		driver.findElement(By.id(id_campo)).sendKeys(texto);
	}

	public String obterValor(String id_campo) {
		return driver.findElement(By.id(id_campo)).getAttribute("value");
	}

	public void marcarElementoRadio(String id_campo) {
		driver.findElement(By.id(id_campo)).click();
	}

	public boolean isRadioMarcado(String id_campo) {
		return driver.findElement(By.id(id_campo)).isSelected();
	}

	public void marcarElementoCheckBox(String id_campo) {
		driver.findElement(By.id(id_campo)).click();
	}

	public void selecionarComboIndice(String id_campo, int indice) {
		WebElement el = driver.findElement(By.id(id_campo));
		Select combo = new Select(el);
		combo.selectByIndex(indice);
	}

	public void selecionarComboValor(String id_campo, String valor) {
		WebElement el = driver.findElement(By.id(id_campo));
		Select combo = new Select(el);
		combo.selectByValue(valor);
	}

	public void selecionarComboTexto(String id_campo, String texto) {
		WebElement el = driver.findElement(By.id(id_campo));
		Select combo = new Select(el);
		combo.selectByVisibleText(texto);
	}

	public String obterValorCombo(String id_campo) {
		WebElement el = driver.findElement(By.id(id_campo));
		Select combo = new Select(el);
		return combo.getFirstSelectedOption().getText();
	}

	public List<WebElement> obterValoresCombo(String id_campo) {
		WebElement el = driver.findElement(By.id(id_campo));
		Select combo = new Select(el);
		List<WebElement> options = combo.getOptions();
		return options;
	}

	public void clicarBotao(String ib_button) {
		WebElement el = driver.findElement(By.id(ib_button));
		el.click();
	}

	public void clicarLink(String id_link) {
		WebElement el = driver.findElement(By.id(id_link));
		el.click();
	}

	public void clicarLink(By by) {
		WebElement el = driver.findElement(by);
		el.click();
	}

	public String obterTexto(By by) {
		return driver.findElement(by).getText();
	}

	public String obterTexto(String id_campo) {
		return driver.findElement(By.id(id_campo)).getText();
	}

}
