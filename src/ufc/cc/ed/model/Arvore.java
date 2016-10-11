package ufc.cc.ed.model;

import javax.swing.JOptionPane;


public class Arvore {

  private No noRraiz;
  
	public No getNoRraiz() {
		return noRraiz;
	}

	public void setNoRraiz(No noRraiz) {
		this.noRraiz = noRraiz;
	}
	
	public void inserir(int valor) {
		No no = new No(valor);
		inserirAVL(this.getNoRraiz(), no);
	}

	public void inserirAVL(No aComparar, No aInserir) {

		if (aComparar == null) {
			this.setNoRraiz(aInserir);

		} else {

			if (aInserir.getValor() < aComparar.getValor()) {

				if (aComparar.getEsquerda() == null) {
					aComparar.setEsquerda(aInserir);
					aInserir.setPai(aComparar);
					verificarBalanceamento(aComparar);

				} else {
					inserirAVL(aComparar.getEsquerda(), aInserir);
				}

			} else if (aInserir.getValor() > aComparar.getValor()) {

				if (aComparar.getDireita() == null) {
					aComparar.setDireita(aInserir);
					aInserir.setPai(aComparar);
					verificarBalanceamento(aComparar);

				} else {
					inserirAVL(aComparar.getDireita(), aInserir);
				}

			} else {
				JOptionPane.showMessageDialog(null, "Elemento " + aInserir.getValor() +
						" já existe", "Atenção",2);
			}
		}
	}
	
	public No procurar(int valor, No no){
		if (no == null) {
			return null;
		}
		if (no.getValor()==valor) {
			return no;
		}
		else if (valor < no.getValor()) {
			return procurar(valor, no.getEsquerda());
		} else {
			return procurar(valor, no.getDireita());
		}
	}
	
	public void verificarBalanceamento(No noAtual) {
		setBalanceamento(noAtual);
		int balanceamento = noAtual.getBalanceamento();

		if (balanceamento == -2) {

			if (altura(noAtual.getEsquerda().getEsquerda()) >= 
					altura(noAtual.getEsquerda().getDireita())) {
				noAtual = rotacaoDireita(noAtual);

			} else {
				noAtual = duplaRotacaoEsquerdaDireita(noAtual);
			}

		} else if (balanceamento == 2) {

			if (altura(noAtual.getDireita().getDireita()) >= altura(noAtual.getDireita().getEsquerda())) {
				noAtual = rotacaoEsquerda(noAtual);

			} else {
				noAtual = duplaRotacaoDireitaEsquerda(noAtual);
			}
		}

		if (noAtual.getPai() != null) {
			verificarBalanceamento(noAtual.getPai());
		} else {
			this.setNoRraiz(noAtual);
		}
	}

	public void remover(int valor) {
		removerAVL(this.getNoRraiz(), valor);
	}

	public void removerAVL(No atual, int valor) {
		if (atual == null) {
			return;

		} else {

			if (atual.getValor() > valor) {
				removerAVL(atual.getEsquerda(), valor);

			} else if (atual.getValor() < valor) {
				removerAVL(atual.getDireita(), valor);

			} else if (atual.getValor() == valor) {
				removerNoEncontrado(atual);
			}
		}
	}

	public void removerNoEncontrado(No aRemover) {
		No noTemp;

		if (aRemover.getEsquerda() == null || aRemover.getDireita() == null) {

			if (aRemover.getPai() == null) {
				this.setNoRraiz(null);
				aRemover = null;
				return;
			}
			noTemp = aRemover;

		} else {
			noTemp = sucessor(aRemover);
			aRemover.setValor(noTemp.getValor());
		}

		No noReordena;
		
		if (noTemp.getEsquerda() != null) {
			noReordena = noTemp.getEsquerda();
		} else {
			noReordena = noTemp.getDireita();
		}

		if (noReordena != null) {
			noReordena.setPai(noTemp.getPai());
		}

		if (noTemp.getPai() == null) {
			this.setNoRraiz(noReordena);
		} else {
			if (noTemp == noTemp.getPai().getEsquerda()) {
				noTemp.getPai().setEsquerda(noReordena);
			} else {
				noTemp.getPai().setDireita(noReordena);
			}
			verificarBalanceamento(noTemp.getPai());
		}
		noTemp = null;
	}

	public No rotacaoEsquerda(No inicial) {

		No direita = inicial.getDireita();
		direita.setPai(inicial.getPai());

		inicial.setDireita(direita.getEsquerda());

		if (inicial.getDireita() != null) {
			inicial.getDireita().setPai(inicial);
		}

		direita.setEsquerda(inicial);
		inicial.setPai(direita);

		if (direita.getPai() != null) {

			if (direita.getPai().getDireita() == inicial) {
				direita.getPai().setDireita(direita);
			
			} else if (direita.getPai().getEsquerda() == inicial) {
				direita.getPai().setEsquerda(direita);
			}
		}

		setBalanceamento(inicial);
		setBalanceamento(direita);

		return direita;
	}

	public No rotacaoDireita(No inicial) {

		No esquerda = inicial.getEsquerda();
		esquerda.setPai(inicial.getPai());

		inicial.setEsquerda(esquerda.getDireita());

		if (inicial.getEsquerda() != null) {
			inicial.getEsquerda().setPai(inicial);
		}

		esquerda.setDireita(inicial);
		inicial.setPai(esquerda);

		if (esquerda.getPai() != null) {

			if (esquerda.getPai().getDireita() == inicial) {
				esquerda.getPai().setDireita(esquerda);
			
			} else if (esquerda.getPai().getEsquerda() == inicial) {
				esquerda.getPai().setEsquerda(esquerda);
			}
		}

		setBalanceamento(inicial);
		setBalanceamento(esquerda);

		return esquerda;
	} 	

	public No duplaRotacaoEsquerdaDireita(No inicial) {
		inicial.setEsquerda(rotacaoEsquerda(inicial.getEsquerda()));
		return rotacaoDireita(inicial);
	}

	public No duplaRotacaoDireitaEsquerda(No inicial) {
		inicial.setDireita(rotacaoDireita(inicial.getDireita()));
		return rotacaoEsquerda(inicial);
	}

	public No sucessor(No q) {
		if (q.getDireita() != null) {
			No r = q.getDireita();
			while (r.getEsquerda() != null) {
				r = r.getEsquerda();
			}
			return r;
		} else {
			No p = q.getPai();
			while (p != null && q == p.getDireita()) {
				q = p;
				p = q.getPai();
			}
			return p;
		}
	}

	private int altura(No atual) {
		if (atual == null) {
			return -1;
		}

		if (atual.getEsquerda() == null && atual.getDireita() == null) {
			return 0;
		
		} else if (atual.getEsquerda() == null) {
			return 1 + altura(atual.getDireita());
		
		} else if (atual.getDireita() == null) {
			return 1 + altura(atual.getEsquerda());
		
		} else {
			return 1 + Math.max(altura(atual.getEsquerda()), altura(atual.getDireita()));
		}
	}

	private void setBalanceamento(No no) {
		no.setBalanceamento(altura(no.getDireita()) - altura(no.getEsquerda()));
	}

}