import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastro {

	private WebDriver driver;
	private DSL dsl;

	@Before
	public void inicializa() {
		System.setProperty("webdriver.chrome.driver", "C://chromedriver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}

	@Ignore
	public void finaliza() {
		driver.quit();
	}

	@Test
	public void cadastro() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Carlos");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("AB");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		combo.selectByVisibleText("Mestrado");
		driver.findElement(By.id("elementosForm:cadastrar")).click();

		Assert.assertEquals("Carlos", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
		Assert.assertEquals("AB", driver.findElement(By.id("elementosForm:sobrenome")).getAttribute("value"));
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected());
		Assert.assertEquals("Mestrado", combo.getFirstSelectedOption().getText());
		Assert.assertEquals("Cadastrado!", driver.findElement(By.id("resultado")).getText());
	}

	@Test
	public void cadastroProfessor() {
		dsl.escreve("elementosForm:nome", "Carlos");
		dsl.escreve("elementosForm:sobrenome", "AB");
		dsl.clicarRadio("elementosForm:sexo:0");
		dsl.clicarRadio("elementosForm:comidaFavorita:2");
		dsl.selecionarCombo("elementosForm:escolaridade", "Mestrado");
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.clicarBotao("elementosForm:cadastrar");
		
		Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
		Assert.assertTrue(dsl.obterTexto("descNome").endsWith("Carlos"));
		Assert.assertEquals("Sobrenome: AB", dsl.obterTexto("descSobrenome"));
		Assert.assertEquals("Sexo: Masculino", dsl.obterTexto("descSexo"));
		Assert.assertEquals("Comida: Pizza", dsl.obterTexto("descComida"));
		Assert.assertEquals("Escolaridade: mestrado", dsl.obterTexto(By.id("descEscolaridade")));
		Assert.assertEquals("Esportes: Natacao", dsl.obterTexto(By.id("descEsportes")));
	}
}
