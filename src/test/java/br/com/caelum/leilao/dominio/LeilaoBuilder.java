package br.com.caelum.leilao.dominio;

public class LeilaoBuilder {
	
	private Leilao leilao;
	
	public LanceBuilder comProduto(String produto) {
		this.leilao = new Leilao(produto);
		return new LanceBuilder();
	}
	
	public class LanceBuilder {
		public LanceBuilder comLanceDo(Usuario usuario, double valor) {
			leilao.propoe(new Lance(usuario, valor));
			return this;
		}
		
		public Leilao build() {
			return leilao;
		}
	}

}
