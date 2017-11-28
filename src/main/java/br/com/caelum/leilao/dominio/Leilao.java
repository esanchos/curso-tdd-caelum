package br.com.caelum.leilao.dominio;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {

	private String descricao;
	private List<Lance> lances = new ArrayList<>();
	
	public Leilao(String descricao) {
		if(descricao==null) {
			throw new IllegalArgumentException();
		}
		this.descricao = descricao;
		
	}
	
	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}
	
	public void propoe(Lance lance) {
		if (lances.size()==0) {
			lances.add(lance);
		} else {
			Lance ultimoLance = getLastLance();
			if (lance.getValor()>ultimoLance.getValor()) {
				if (!lance.getUsuario().getName().equals(lances.get(lances.size()-1).getUsuario().getName())) {
					if (userHasLessThan5Bids(lance.getUsuario().getName())) {
						lances.add(lance);
					}
				}
			}
		}
	}
	
	private boolean userHasLessThan5Bids(String name) {
		int count = 0;
		for (Lance lance : lances) {
			if (lance.getUsuario().getName().equals(name)) {
				count++;
			}
		}
		if (count<5) return true;
		else return false;
	}
	
	private Lance getLastLance() {
		return lances.get(lances.size()-1);
	}
	
	public String getDescricao() {
		return descricao;
	}
}
