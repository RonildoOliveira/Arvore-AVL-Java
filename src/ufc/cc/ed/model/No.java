package ufc.cc.ed.model;

public class No {
  
	private No noEsquerda;
	private No noDireita;
	private No noPai;
	private int noValor;
	private int balanceamento;

	public No(int k) {
		setEsquerda(setDireita(setPai(null)));
		setBalanceamento(0);
		setValor(k);
	}

	public String toString() {
		return Integer.toString(getValor());
	}

	public int getValor() {
		return noValor;
	}

	public void setValor(int valor) {
		this.noValor = valor;
	}

	public int getBalanceamento() {
		return balanceamento;
	}

	public void setBalanceamento(int balanceamento) {
		this.balanceamento = balanceamento;
	}

	public No getPai() {
		return noPai;
	}

	public No setPai(No noPai) {
		this.noPai = noPai;
		return noPai;
	}

	public No getDireita() {
		return noDireita;
	}

	public No setDireita(No noDireita) {
		this.noDireita = noDireita;
		return noDireita;
	}

	public No getEsquerda() {
		return noEsquerda;
	}

	public void setEsquerda(No noEsquerda) {
		this.noEsquerda = noEsquerda;
	}
}