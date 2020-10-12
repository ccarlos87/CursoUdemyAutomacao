import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;

import br.carlosab.com.DSL;
import br.carlosab.com.DriverFactory;


public class TestePrime {

	private DSL dsl;

	@Before
	public void inicializa() {
		//driver.get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
		DriverFactory.getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
	}

	@Ignore
	public void finaliza() {
		DriverFactory.killDriver();
	}
	
	@Test
	public void deveInteragirComRadioPrime() {
		dsl.clicarRadio(By.xpath("//input[@id='j_idt728:console:0']/../..//span"));
		Assert.assertTrue(dsl.isRadioMarcado("j_idt728:console:0"));
	}
	
	@Test
	public void deveInteragirComComboPrime() {
		dsl.selecionarComboPrime("j_idt728:console", "Xbox One");
		Assert.assertEquals("Xbox One", dsl.obterTexto("j_idt728:console_label"));
	}
	
}
