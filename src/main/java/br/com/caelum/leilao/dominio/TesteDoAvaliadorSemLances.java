package br.com.caelum.leilao.dominio;

public class TesteDoAvaliadorSemLances {
  public static void main(String[] args) {
		
		Leilao leilao = new Leilao("Playstation 4");
	
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		System.out.println(leiloeiro.getMaiorLance());
		System.out.println(leiloeiro.getMenorLance());
  }
}
