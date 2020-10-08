import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCampoTreinamento {

	private WebDriver driver;
	private DSL dsl;

	@Before
	public void inicializa() {
		System.setProperty("webdriver.chrome.driver", "C://chromedriver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}

	@After
	public void finaliza() {
		driver.quit();
	}

	@Test
	public void testeTextField() {
		dsl.escrever("elementosForm:nome", "Carlos");
		Assert.assertEquals("Carlos", dsl.obterValorCampo("elementosForm:nome"));
	}

	@Test
	public void deveInteragirComTextArea() {
		dsl.escrever("elementosForm:sugestoes", "Sugestão");
		Assert.assertEquals("Sugestão", dsl.obterValorCampo("elementosForm:sugestoes"));

		// driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Sugestão");
		// Assert.assertEquals("Sugestão",
		// driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
	}

	@Test
	public void deveInteragirComRadioButton() {
		dsl.clicarRadio("elementosForm:sexo:0");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));

		// driver.findElement(By.id("elementosForm:sexo:0")).click();
		// Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
	}

	@Test
	public void deveInteragirComCheckBox() {
		dsl.clicarCheck("elementosForm:comidaFavorita:0");
		Assert.assertTrue(dsl.isCheckMarcado("elementosForm:comidaFavorita:0"));

		// driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		// Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected());
	}

	@Test
	public void deveInteragirComCombo() {
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		// combo.selectByIndex(2); seleciona opção no combo por ordem
		combo.selectByVisibleText("Mestrado"); // seleciona opção no combo por texto

		Assert.assertEquals("Mestrado", combo.getFirstSelectedOption().getText()); // validando
	}

	@Test
	public void deveInteragirComValoresCombo() {
		dsl.selecionarCombo("elementosForm:escolaridade", "Mestrado");
		Assert.assertEquals("Mestrado", dsl.obterValorCombo("elementosForm:escolaridade"));

		/*
		 * WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		 * Select combo = new Select(element); List<WebElement> options =
		 * combo.getOptions(); Assert.assertEquals(8, options.size());
		 * 
		 * boolean encontrou = false; for (WebElement option : options) { if
		 * (option.getText().equals("Mestrado")) { encontrou = true; break; } }
		 * Assert.assertTrue(encontrou);
		 */
	}

	@Test
	public void deveInteragirComValoresComboMultiplo() {
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");

		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		// combo.selectByVisibleText("Natacao");
		// combo.selectByVisibleText("Corrida");
		// combo.selectByVisibleText("O que eh esporte?");

		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectedOptions.size());
	}

	@Test
	public void deveInteragirComBotoes() {
		dsl.clicarBotao("buttonSimple");

		// WebElement botao = driver.findElement(By.id("buttonSimple"));
		// botao.click();
		// Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
	}

	@Test
	public void deveInteragirComLinks() {
		dsl.clicarLink("Voltar");
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
	}

	@Test
	public void deveBuscarTextosNaPagina() {
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facil achar")));

	}
}