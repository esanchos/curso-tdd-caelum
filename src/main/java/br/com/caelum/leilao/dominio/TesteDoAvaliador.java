package br.com.caelum.leilao.dominio;

public class TesteDoAvaliador {

	public static void main(String[] args) {
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("Jose");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Playstation 4");
		
		leilao.propoe(new Lance(joao, 300));
		leilao.propoe(new Lance(jose, 400));
		leilao.propoe(new Lance(maria, 250));

		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		System.out.println(leiloeiro.getMaiorLance());
		System.out.println(leiloeiro.getMenorLance());
	}

}
