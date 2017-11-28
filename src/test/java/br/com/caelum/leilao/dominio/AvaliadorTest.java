package br.com.caelum.leilao.dominio;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AvaliadorTest {
	
	private Usuario joao;
	private Usuario jose;
	private Usuario maria;
	
	@Rule
	public ExpectedException thrower = ExpectedException.none();
	
	@Before
	public void setup() {
		joao = new Usuario("Joao");
		jose = new Usuario("Jose");
		maria = new Usuario("Maria");
	}

	@Test
	public void ordemCrescente() {
		Leilao leilao = new LeilaoBuilder()
				.comProduto("TV")
				.comLanceDo(joao, 200)
				.comLanceDo(jose, 300)
				.comLanceDo(maria, 400)
				.build();

		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		assertEquals(400, leiloeiro.getMaiorLance(), 0.0000001);
		assertEquals(200, leiloeiro.getMenorLance(), 0.0000001);
		assertEquals(300, leiloeiro.getValorMedio(), 0.0000001);
		List<Lance> lances = leiloeiro.getTop3();
		assertEquals(3, lances.size());
		assertEquals(400, lances.get(0).getValor(),0.0000001);
		assertEquals(300, lances.get(1).getValor(),0.0000001);
		assertEquals(200, lances.get(2).getValor(),0.0000001);
	}
	
	@Test
	public void umLance() {
		Leilao leilao = new LeilaoBuilder()
				.comProduto("TV")
				.comLanceDo(joao, 200)
				.build();

		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		assertEquals(200, leiloeiro.getMaiorLance(), 0.0000001);
		assertEquals(200, leiloeiro.getMenorLance(), 0.0000001);
		assertEquals(200, leiloeiro.getValorMedio(), 0.0000001);
		List<Lance> lances = leiloeiro.getTop3();
		assertEquals(1, lances.size());
		assertEquals(200, lances.get(0).getValor(),0.0000001);
	}
	
	/*@Test
	public void SemLances() {
		Leilao leilao = new LeilaoBuilder()
				.comProduto("TV")
				.build();
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		assertEquals(0, leiloeiro.getMaiorLance(), 0.0000001);
		assertEquals(0, leiloeiro.getMenorLance(), 0.0000001);
		assertEquals(0, leiloeiro.getValorMedio(), 0.0000001);
		assertEquals(0, leiloeiro.getTop3().size());
	}*/
	
	@Test
	public void leilaoComDoisLances() {
		Leilao leilao = new LeilaoBuilder()
				.comProduto("TV")
				.comLanceDo(joao, 200)
				.comLanceDo(jose, 300)
				.build();

		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		assertEquals(300, leiloeiro.getMaiorLance(), 0.0000001);
		assertEquals(200, leiloeiro.getMenorLance(), 0.0000001);
		assertEquals(250, leiloeiro.getValorMedio(), 0.0000001);
		List<Lance> lances = leiloeiro.getTop3();
		assertEquals(2, lances.size());
		assertEquals(300, lances.get(0).getValor(),0.0000001);
		assertEquals(200, lances.get(1).getValor(),0.0000001);
	}
	
	@Test
	public void leilaoComQuatroOuMaisLances() {
		Leilao leilao = new LeilaoBuilder()
				.comProduto("TV")
				.comLanceDo(joao, 200)
				.comLanceDo(jose, 300)
				.comLanceDo(maria, 400)
				.comLanceDo(joao, 500)
				.comLanceDo(jose, 600)
				.build();
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		assertEquals(600, leiloeiro.getMaiorLance(), 0.0000001);
		assertEquals(200, leiloeiro.getMenorLance(), 0.0000001);
		assertEquals(400, leiloeiro.getValorMedio(), 0.0000001);
		List<Lance> lances = leiloeiro.getTop3();
		assertEquals(3, lances.size());
		assertEquals(600, lances.get(0).getValor(),0.0000001);
		assertEquals(500, lances.get(1).getValor(),0.0000001);
		assertEquals(400, lances.get(2).getValor(),0.0000001);
	}
	
	@Test
	public void deveSelecionarLancesEntre1000e3000() {
		Usuario joao = new Usuario("Joao");
		
		FiltroDeLances filtro = new FiltroDeLances();
		List<Lance> resultado = filtro.filtra(Arrays.asList(
				new Lance(joao, 2000),
				new Lance(joao, 1000),
				new Lance(joao, 3000),
				new Lance(joao, 800)));
		
		assertEquals(1, resultado.size());
		assertEquals(2000, resultado.get(0).getValor(), 0.00001);
	}
	
	@Test
	public void deveSelecionarLancesEntre500e700() {
		Usuario joao = new Usuario("Joao");
		
		FiltroDeLances filtro = new FiltroDeLances();
		List<Lance> resultado = filtro.filtra(Arrays.asList(
				new Lance(joao, 600),
				new Lance(joao, 500),
				new Lance(joao, 700),
				new Lance(joao, 800)));
		
		assertEquals(1, resultado.size());
		assertEquals(600, resultado.get(0).getValor(), 0.00001);
	}
	
	@Test
	public void garanteQueLeilaoSemLancesDeveGerarException() {
		
		thrower.expect(IllegalArgumentException.class);
		thrower.expectMessage("Leilao deve ter pelo menos um lance.");
		
		Leilao leilao = new LeilaoBuilder()
				.comProduto("TV")
				.build();
			
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
	}
	
	/*@Test
	public void SemProduto() {
		Leilao leilao = new Leilao(null);
		
		Assert.assertFalse("deu ruim", leilao.getDescricao() == null);
	}*/
}
