package br.carlosab.test;
import static br.carlosab.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.carlosab.core.BaseTest;
import br.carlosab.core.DSL;
import br.carlosab.page.CampoTreinamentoPage;

public class TesteCadastro extends BaseTest{

	private DSL dsl;
	private CampoTreinamentoPage page;

	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new CampoTreinamentoPage();
	}

	@Test
	public void cadastro() {
		page.setNome("Carlos");
		page.setSobrenome("AB");
		page.setSexoMasculino();
		page.setComidaCarne();
		page.setEscolaridade("Mestrado");
		page.setEsportes("Natacao");
		page.cadastrar();

		Assert.assertEquals("Cadastrado!", page.obterResultadoCadastro());
		Assert.assertEquals("Carlos", page.obterNomeCadastro());
		Assert.assertEquals("AB", page.obterSobrenomeCadastro());
		Assert.assertEquals("Masculino", page.obterSexoCadastro());
		Assert.assertEquals("Carne", page.obterComidaCadastro());
		Assert.assertEquals("mestrado", page.obterEscolaridadeCadastro());
		Assert.assertEquals("Natacao", page.obterEsportesCadastro());
	}

	@Test
	public void cadastroProfessor() {
		dsl.escrever("elementosForm:nome", "Wagner");
		dsl.escrever("elementosForm:sobrenome", "Costa");
		dsl.clicaRadio("elementosForm:sexo:0");
		dsl.clicaRadio("elementosForm:comidaFavorita:2");
		dsl.selecionarCombo("elementosForm:escolaridade", "Mestrado");
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.clicarBotao("elementosForm:cadastrar");
		
		Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
		Assert.assertTrue(dsl.obterTexto("descNome").endsWith("Wagner"));
		Assert.assertEquals("Sobrenome: Costa", dsl.obterTexto("descSobrenome"));
		Assert.assertEquals("Sexo: Masculino", dsl.obterTexto("descSexo"));
		Assert.assertEquals("Comida: Pizza", dsl.obterTexto("descComida"));
		Assert.assertEquals("Escolaridade: mestrado", dsl.obterTexto("descEscolaridade"));
		Assert.assertEquals("Esportes: Natacao", dsl.obterTexto("descEsportes"));

	}
	
	@Test
	public void deveValidarNomeObrigatorio(){
		page.cadastrar();
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());
		
		//dsl.clicarBotao("elementosForm:cadastrar");
		//Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveValidarSobrenomeObrigatorio(){
		page.setNome("Carlos");
		page.cadastrar();
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());
		
		//dsl.escrever("elementosForm:nome", "Nome qualquer");
		//dsl.clicarBotao("elementosForm:cadastrar");
		//Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveValidarSexoObrigatorio(){
		page.setNome("Carlos");
		page.setSobrenome("AB");
		page.cadastrar();
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());
		
		//dsl.escrever("elementosForm:nome", "Nome qualquer");
		//dsl.escrever("elementosForm:sobrenome", "Sobrenome qualquer");
		//dsl.clicarBotao("elementosForm:cadastrar");
		//Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveValidarComidaVegetariana(){
		page.setNome("Carlos");
		page.setSobrenome("AB");
		page.setSexoMasculino();
		page.setComidaCarne();
		page.setComidaVegetariano();
		page.cadastrar();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());
		
		//dsl.escrever("elementosForm:nome", "Nome qualquer");
		//dsl.escrever("elementosForm:sobrenome", "Sobrenome qualquer");
		//dsl.clicarRadio("elementosForm:sexo:1");
		//dsl.clicarRadio("elementosForm:comidaFavorita:0");
		//dsl.clicarRadio("elementosForm:comidaFavorita:3");
		//dsl.clicarBotao("elementosForm:cadastrar");
		//Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveValidarEsportistaIndeciso(){
		page.setNome("Carlos");
		page.setSobrenome("AB");
		page.setSexoMasculino();
		page.setComidaCarne();
		page.setEsporteMultiplo("Natacao", "O que eh esporte?");
		page.cadastrar();
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());
		
		//dsl.escrever("elementosForm:nome", "Nome qualquer");
		//dsl.escrever("elementosForm:sobrenome", "Sobrenome qualquer");
		//dsl.clicarRadio("elementosForm:sexo:1");
		//dsl.clicarRadio("elementosForm:comidaFavorita:0");
		//dsl.selecionarCombo("elementosForm:esportes", "Karate");
		//dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		//dsl.clicarBotao("elementosForm:cadastrar");
		//Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());
	}
}
