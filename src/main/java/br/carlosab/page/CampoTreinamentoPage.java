package br.carlosab.page;

import org.openqa.selenium.By;

import br.carlosab.core.BasePage;

public class CampoTreinamentoPage extends BasePage {

	public void setNome(String nome) {
		dsl.escrever("elementosForm:nome", nome);
	}

	public void setSobrenome(String sobrenome) {
		dsl.escrever("elementosForm:sobrenome", sobrenome);
	}

	public void setSexoMasculino() {
		dsl.clicaRadio("elementosForm:sexo:0");
	}

	public void setSexoFeminino() {
		dsl.clicaRadio("elementosForm:sexo:1");
	}

	public void setComidaCarne() {
		dsl.clicarCheck("elementosForm:comidaFavorita:0");
	}

	public void setComidaFrango() {
		dsl.clicarCheck("elementosForm:comidaFavorita:1");
	}

	public void setComidaPizza() {
		dsl.clicarCheck("elementosForm:comidaFavorita:2");
	}

	public void setComidaVegetariano() {
		dsl.clicarCheck("elementosForm:comidaFavorita:3");
	}

	public void setEscolaridade(String valor) {
		dsl.selecionarCombo("elementosForm:escolaridade", valor);
	}

	public void setEsportes(String valor) {
		dsl.selecionarCombo("elementosForm:esportes", valor);
	}

	// Método para selecionar mais de uma opção no combo
	public void setEsporteMultiplo(String... valores) {
		for (String valor : valores)
			dsl.selecionarCombo("elementosForm:esportes", valor);
	}

	public void cadastrar() {
		dsl.clicarBotao("elementosForm:cadastrar");
	}

	public String obterResultadoCadastro() {
		// return dsl.obterTexto("resultado"); //via texto na tela
		return dsl.obterTexto(By.xpath("//*[@id='resultado']/span")); // via xpath
	}

	public String obterNomeCadastro() {
		// return dsl.obterTexto("descNome"); //via texto na tela
		return dsl.obterTexto(By.xpath("//*[@id='descNome']/span")); // via xpath
	}

	public String obterSobrenomeCadastro() {
		// return dsl.obterTexto("descSobrenome");
		return dsl.obterTexto(By.xpath("//*[@id='descSobrenome']/span")); // via xpath
	}

	public String obterSexoCadastro() {
		// return dsl.obterTexto("descSexo");
		return dsl.obterTexto(By.xpath("//*[@id='descSexo']/span")); // via xpath
	}

	public String obterComidaCadastro() {
		// return dsl.obterTexto("descComida");
		return dsl.obterTexto(By.xpath("//*[@id='descComida']/span")); // via xpath
	}

	public String obterEscolaridadeCadastro() {
		// return dsl.obterTexto("descEscolaridade");
		return dsl.obterTexto(By.xpath("//*[@id='descEscolaridade']/span")); // via xpath
	}

	public String obterEsportesCadastro() {
		// return dsl.obterTexto("descEsportes");
		return dsl.obterTexto(By.xpath("//*[@id='descEsportes']/span")); // via xpath
	}

}
