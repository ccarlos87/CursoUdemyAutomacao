package br.carlosab.test;
import static br.carlosab.core.DriverFactory.getDriver;
import static br.carlosab.core.DriverFactory.killDriver;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.carlosab.core.DSL;

public class TesteSincronismo {
	
	private DSL dsl;

	@Before
	public void inicializa() {
		//getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
		//getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Ignore
	public void finaliza() {
		killDriver();
	}
	
	@Test
	public void deveUtilizarEsperaFixa() throws InterruptedException {
		dsl.clicarBotao("buttonDelay");
		Thread.sleep(5000);
		dsl.escrever("novoCampo", "Deu certo?");
	}
	
	@Test
	public void deveUtilizarEsperaImplicita() {
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //este comando, deve ser aplicado no Before como boas praticas
		dsl.clicarBotao("buttonDelay");
		dsl.escrever("novoCampo", "Deu certo?");
		getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		
	}
	
	@Test
	public void deveUtilizarEsperaExplicita() {
		dsl.clicarBotao("buttonDelay");
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo"))); //aguarda o campo abaixo ficar visivel para poder prosseguir
		dsl.escrever("novoCampo", "Deu certo?");
	}

}

