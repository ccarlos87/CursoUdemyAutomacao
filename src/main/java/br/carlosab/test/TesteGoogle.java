package br.carlosab.test;
import org.junit.After;
import org.junit.Before;

import br.carlosab.core.DriverFactory;

public class TesteGoogle {

	@Before
	public void inicializa() {
		DriverFactory.getDriver()
				.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}

	@After
	public void finaliza() {
		DriverFactory.killDriver();
	}

}
