package binaria;

import java.util.LinkedList;
import java.util.Queue;

public class ArvoreBinaria {
	private Nodo raiz;

	public ArvoreBinaria() {
		this.setRaiz(null);
	}

	public Nodo getRaiz() {
		return raiz;
	}

	public void setRaiz(Nodo raiz) {
		this.raiz = raiz;
	}

	public void insereNaoRecursivo(int valor) {
		Nodo novo = new Nodo(valor, null, null);

		if (this.raiz == null)
			this.raiz = novo;
		else {
			Nodo aux = this.raiz;
			boolean inseriu = false;
			while (inseriu == false) {
				if (novo.getValor() < aux.getValor()) {
					if (aux.getEsquerda() != null)
						aux = aux.getEsquerda();
					else {
						aux.setEsquerda(novo);
						inseriu = true;
					}
				} else {
					if (aux.getDireita() != null)
						aux = aux.getDireita();
					else {
						aux.setDireita(novo);
						inseriu = true;
					}
				}
			}
		}

	}

	// atividade extra imprimir nao recursivo

	public void imprimeNaoRec() {
		Queue<Nodo> fila = new LinkedList<>();
		fila.add(raiz); // raiz é um Nodo

		while (!fila.isEmpty()) {
			Nodo atual = fila.poll();
			System.out.println(atual.getValor()); // imprime o valor inteiro

			if (atual.getEsquerda() != null)
				fila.add(atual.getEsquerda());
			if (atual.getDireita() != null)
				fila.add(atual.getDireita());
		}
	}

	public void pesquisa(int valor, Nodo aux) {
		if (aux != null) {
			if (aux.getValor() == valor) {

				System.out.println("valor encontrado! " + valor);
				return;
			} else if (valor < aux.getValor()) {
				pesquisa(valor, aux.esquerda);
			} else {
				pesquisa(valor, aux.getDireita());
			}

		}
		if (aux == null)
			System.out.println("valor nao encontrado: " + valor);
	}

	

	public void insereRecursivo(Nodo aux, int valor) {
		Nodo novo = new Nodo(valor, null, null);

		if (aux != null) {
			if (valor < aux.getValor()) {
				if (aux.getEsquerda() != null)
					insereRecursivo(aux.getEsquerda(), valor);
				else
					aux.setEsquerda(novo);
			} else {
				if (aux.getDireita() != null)
					insereRecursivo(aux.getDireita(), valor);
				else
					aux.setDireita(novo);
			}
		} else
			setRaiz(novo);
	}

	public void imprime(Nodo aux) {
		if (aux != null) {
			System.out.println(aux.getValor());
			imprime(aux.getEsquerda());
			imprime(aux.getDireita());
		}
	}
	public void removerRecursivo(Nodo aux, Nodo pai, int valor) {
	    if (aux == null) {
	        System.out.println("Valor não encontrado!");
	        return;
	    }

	    if (valor < aux.getValor()) {
	        removerRecursivo(aux.getEsquerda(), aux, valor);
	    } else if (valor > aux.getValor()) {
	        removerRecursivo(aux.getDireita(), aux, valor);
	    } else {
	        // Valor encontrado — decidir qual remoção aplicar
	        if (aux.getEsquerda() == null && aux.getDireita() == null) {
	            removeFolha(aux, pai);
	        } else if (aux.getEsquerda() != null && aux.getDireita() == null) {
	            removeComFilhoEsquerda(aux, pai);
	        } else if (aux.getEsquerda() == null && aux.getDireita() != null) {
	            removeComFilhoDireita(aux, pai);
	        } else {
	            removeComDoisFilhos(aux);
	        }
	        System.out.println("Valor " + valor + " removido.");
	    }
	}

	private void removeFolha(Nodo aux, Nodo pai) {
	    if (pai == null) {
	        setRaiz(null);
	    } else if (pai.getEsquerda() == aux) {
	        pai.setEsquerda(null);
	    } else if (pai.getDireita() == aux) {
	        pai.setDireita(null);
	    }
	}

	private void removeComFilhoEsquerda(Nodo aux, Nodo pai) {
	    if (pai == null) {
	        setRaiz(aux.getEsquerda());
	    } else if (pai.getEsquerda() == aux) {
	        pai.setEsquerda(aux.getEsquerda());
	    } else if (pai.getDireita() == aux) {
	        pai.setDireita(aux.getEsquerda());
	    }
	}

	private void removeComFilhoDireita(Nodo aux, Nodo pai) {
	    if (pai == null) {
	        setRaiz(aux.getDireita());
	    } else if (pai.getEsquerda() == aux) {
	        pai.setEsquerda(aux.getDireita());
	    } else if (pai.getDireita() == aux) {
	        pai.setDireita(aux.getDireita());
	    }
	}

	private void removeComDoisFilhos(Nodo aux) {
	    // Encontrar o sucessor (menor valor da subárvore direita)
	    Nodo sucessor = aux.getDireita();
	    Nodo paiSucessor = aux;

	    while (sucessor.getEsquerda() != null) {
	        paiSucessor = sucessor;
	        sucessor = sucessor.getEsquerda();
	    }

	    aux.setValor(sucessor.getValor());

	    // Agora removemos o sucessor (ele tem no máximo um filho à direita)
	    if (sucessor.getDireita() != null) {
	        removeComFilhoDireita(sucessor, paiSucessor);
	    } else {
	        removeFolha(sucessor, paiSucessor);
	    }
	}

}
