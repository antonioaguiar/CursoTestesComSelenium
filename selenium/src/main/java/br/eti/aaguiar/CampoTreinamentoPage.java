package br.eti.aaguiar;

import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {
	
	private DSL dsl;
	
	public CampoTreinamentoPage(WebDriver driver) {
		dsl = new DSL(driver);
	}
	
	public void setNome(String nome) {
		dsl.escrever("elementosForm:nome", nome);
	}
	
	public void setSobrenome(String sobrenome) {
		dsl.escrever("elementosForm:sobrenome", sobrenome);
	}
	
	public void setSexoMasculino() {
		dsl.marcarElementoRadio("elementosForm:sexo:0");
	}
	
	public void setSexoFeminino() {
		dsl.marcarElementoRadio("elementosForm:sexo:1");
	}
	
	public void setComidaPizza() {
		dsl.marcarElementoCheckBox("elementosForm:comidaFavorita:2");
	}
	
	public void setEscolaridade(String escolaridade) {
		dsl.selecionarComboTexto("elementosForm:escolaridade", escolaridade);
	}
	
	public void setEsporte(String esporte) {
		dsl.selecionarComboTexto("elementosForm:esportes", esporte);		
	}
	
	public void setSugestoes(String texto) {
		dsl.escrever("elementosForm:sugestoes", texto);
	}
	
	public void cadastrar() {
		dsl.clicarBotao("elementosForm:cadastrar");
	}
	
	//retornos
	
	public String obterResultadoCadastro() {
		return dsl.obterTexto("resultado");
	}

	public String obterNomeCadastro() {
		return dsl.obterTexto("descNome");
	}

	public String obterSobreNomeCadastro() {
		return dsl.obterTexto("descSobrenome");
	}
	
	public String obterSexoCadastro() {
		return dsl.obterTexto("descSexo");
	}

	public Object obterComidaCadastro() {
		return dsl.obterTexto("descComida");
	}

	public Object obterEsporteCadastro() {
		return dsl.obterTexto("descEsportes");
	}

	public Object obterEscolaridadeCadastro() {
		return dsl.obterTexto("descEscolaridade");
	} 
}
