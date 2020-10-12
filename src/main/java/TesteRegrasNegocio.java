import static br.carlosab.com.DriverFactory.getDriver;
import static br.carlosab.com.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import br.carlosab.com.DSL;

public class TesteRegrasNegocio {

	private DSL dsl;

	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}

	@After
	public void finaliza() {
		killDriver();
	}

	@Test
	public void deveValidarNomeObrigatorio() {
		getDriver().findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = getDriver().switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio", alert.getText());
	}

	@Test
	public void deveValidarSobrenomeObrigatorio() {
		dsl.escrever("elementosForm:nome", "Carlos");
		getDriver().findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = getDriver().switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
		alert.accept();
	}

	@Test
	public void deveValidarSexoObrigatorio() {
		dsl.escrever("elementosForm:nome", "Carlos");
		dsl.escrever("elementosForm:sobrenome", "AB");
		getDriver().findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = getDriver().switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
		alert.accept();
	}

	@Test
	public void deveValidarComidaObrigatorio() {
		dsl.escrever("elementosForm:nome", "Carlos");
		dsl.escrever("elementosForm:sobrenome", "AB");
		getDriver().findElement(By.id("elementosForm:sexo:0")).click();
		getDriver().findElement(By.id("elementosForm:comidaFavorita:0")).click();
		getDriver().findElement(By.id("elementosForm:comidaFavorita:3")).click();
		getDriver().findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = getDriver().switchTo().alert();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
		alert.accept();
	}

	@Test
	public void deveValidarEsporteObrigatorio() {
		dsl.escrever("elementosForm:nome", "Carlos");
		dsl.escrever("elementosForm:sobrenome", "AB");
		getDriver().findElement(By.id("elementosForm:sexo:0")).click();
		getDriver().findElement(By.id("elementosForm:comidaFavorita:0")).click();
		Select combo = new Select(getDriver().findElement(By.id("elementosForm:esportes")));
		combo.selectByVisibleText("Karate");
		combo.selectByVisibleText("O que eh esporte?");
		getDriver().findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = getDriver().switchTo().alert();
		Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
		alert.accept();
	}

}