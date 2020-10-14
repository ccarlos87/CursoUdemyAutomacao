package br.carlosab.test;
import static br.carlosab.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.carlosab.core.DSL;
import br.carlosab.core.DriverFactory;

public class TestFrameEJanela {

	private DSL dsl;

	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}

	@Ignore
	public void finaliza() {
		DriverFactory.killDriver();
	}

	@Test
	public void deveInteragirComFrame() {
		getDriver().switchTo().frame("frame1");
		getDriver().findElement(By.id("frameButton")).click();
		Alert alerta = getDriver().switchTo().alert();
		String msg = alerta.getText();
		Assert.assertEquals("Frame OK!", msg);
		alerta.accept();

		getDriver().switchTo().defaultContent();
		getDriver().findElement(By.id("elementosForm:nome")).sendKeys(msg);
	}

	@Ignore
	public void deveInteragirComJanela() {
		getDriver().findElement(By.id("buttonPopUpEasy")).click();
		getDriver().switchTo().window("Popup");
		getDriver().findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		getDriver().close();
		getDriver().switchTo().window("");
		getDriver().findElement(By.tagName("textarea")).sendKeys("e agora?");
	}
	
	@Test
	public void deveInteragirComFrameEscondido() {
		WebElement frame = getDriver().findElement(By.id("frame2"));
		dsl.executarJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
		dsl.entrarFrame("frame2");
		dsl.clicarBotao("frameButton");
		String msg = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", msg);
	}
}