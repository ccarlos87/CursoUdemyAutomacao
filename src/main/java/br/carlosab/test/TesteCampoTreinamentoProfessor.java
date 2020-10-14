package br.carlosab.test;
import static br.carlosab.core.DriverFactory.getDriver;
import static br.carlosab.core.DriverFactory.killDriver;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import br.carlosab.core.DSL;

public class TesteCampoTreinamentoProfessor {

	private DSL dsl;

	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}

	@After
	public void finaliza() {
		killDriver();
	}

	@Test
	public void testeTextField() {
		dsl.escrever("elementosForm:nome", "Teste de escrita");
		Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));
	}

	@Test
	public void testTextFieldDuplo() {
		dsl.escrever("elementosForm:nome", "Wagner");
		Assert.assertEquals("Wagner", dsl.obterValorCampo("elementosForm:nome"));
		dsl.escrever("elementosForm:nome", "Aquino");
		Assert.assertEquals("Aquino", dsl.obterValorCampo("elementosForm:nome"));
	}

	@Test
	public void deveIntegarirComTextArea() {
		dsl.escrever("elementosForm:sugestoes", "teste\n\naasldjdlks\nUltima linha");
		Assert.assertEquals("teste\n\naasldjdlks\nUltima linha", dsl.obterValorCampo("elementosForm:sugestoes"));
	}

	@Test
	public void deveIntegarirComRadioButton() {
		dsl.clicaRadio("elementosForm:sexo:0");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
	}

	@Test
	public void deveIntegarirComCheckbox() {
		dsl.clicarCheck("elementosForm:comidaFavorita:2");
		Assert.assertTrue(dsl.isCheckMarcado("elementosForm:comidaFavorita:2"));
	}

	@Test
	public void deveIntegarirComCombo() {
		dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
		Assert.assertEquals("2o grau completo", dsl.obterValorCombo("elementosForm:escolaridade"));
	}

	@Test
	public void deveVerificarValoresCombo() {
		Assert.assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
		Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));
		
		/*
		 * retorna a quantidade de opções dentro do combo exibido. Neste caso, 8 opções.
		 * E em seguida, verifica se a opção selecionada está presente no combo.
		 */
		
	}

	@Test
	public void deveVerificarValoresComboMultiplo() {
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");

		List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());
		
		/*
		 * Método "opçõesMarcadas" retornada todos os valores marcados no combo
		 * E valida o "size". Neste caso, são 3.
		 */

		dsl.deselecionarCombo("elementosForm:esportes", "Corrida");
		opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());	//verifica quantas opções foram marcadas no combo
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?"))); //verifica se as suas opções são essas
		
		/*
		 * na ultima linha, verifica as duas opções selecionada com o "containsAll"
		 */
	}

	@Test
	public void deveinteragirComBotoes() {
		dsl.clicarBotao("buttonSimple");
		Assert.assertEquals("Obrigado!", dsl.obterValueElemento("buttonSimple"));
	}

	@Test
	public void deveinteragirComLinks() {
		dsl.clicarLink("Voltar");

		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
	}

	@Test
	public void deveBuscarTextosNaPagina() {
//		Assert.assertTrue(driver.findElement(By.tagName("body"))
//				.getText().contains("Campo de Treinamento"));
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));

		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
	}

}