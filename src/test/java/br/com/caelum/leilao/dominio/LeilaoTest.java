package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LeilaoTest {
	
	private Leilao leilao;
	private Usuario jose;
	private Usuario joao;
	
	@Before
	public void setup() {
		System.out.println("Inicia");
		
		joao = new Usuario("Joao");
		jose = new Usuario("Jose");
	}
	
	@After
	public void finish() {
		System.out.println("Fim do teste");
	}
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println("Inicializando classe de teste");
	}
	
	@AfterClass
	public static void afterClass() {
		System.out.println("Finalizando classe de teste");
	}
	
	@Test
	public void LanceNaoPodeSerMenorQueAnterior() {
		leilao = new LeilaoBuilder()
				.comProduto("TV")
				.comLanceDo(jose, 200)
				.comLanceDo(joao, 100)
				.build();
		
		assertThat(leilao.getLances().size(), equalTo(1));
	}
	
	@Test
	public void BloquearDoisLancesSequenciaMesmoUsuario() {	
		leilao = new LeilaoBuilder()
				.comProduto("TV")
				.comLanceDo(joao, 300)
				.comLanceDo(joao, 400)
				.build();
		
		assertThat(leilao.getLances().size(), equalTo(1));
	}
	
	@Test
	public void BloquearCincoLancesMesmoUsuario() {	
		leilao = new LeilaoBuilder()
				.comProduto("TV")
				.comLanceDo(joao, 300)
				.comLanceDo(jose, 400)
				.comLanceDo(joao, 500)
				.comLanceDo(jose, 600)
				.comLanceDo(joao, 700)
				.comLanceDo(jose, 800)
				.comLanceDo(joao, 900)
				.comLanceDo(jose, 1000)
				.comLanceDo(joao, 1100)
				.comLanceDo(jose, 1200)
				.comLanceDo(joao, 1300)
				.build();
		
		assertThat(leilao.getLances().size(), equalTo(10));
	}
	
}
