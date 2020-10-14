package br.carlosab.suites;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.carlosab.core.DriverFactory;
import br.carlosab.test.TesteCadastro;
import br.carlosab.test.TesteRegrasCadastro;

@RunWith(Suite.class)
@SuiteClasses({
	//TesteCadastro.class, 
	//TesteCampoTreinamento.class, 
	TesteRegrasCadastro.class
	})

public class SuiteTeste {
	
	@AfterClass
	public static void finalizaTudo() {		//classe para finalizar o driver/browser após as execuçõe seguidas na mesma página
		DriverFactory.killDriver();
	}

}
