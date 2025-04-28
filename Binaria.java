package arvorebinaria.EstruturaDadosAtividadeArvore;

import java.util.LinkedList;
import java.util.Queue;

public class Binaria {
	private Nodo raiz; // não esquece que é tipo Nodo

	public Binaria() {
		this.raiz = null;
	}

	public Nodo getRaiz() {
		return this.raiz;
	}

	public void setRaiz(Nodo raiz) {
		this.raiz = raiz;
	}

	// inserir nodo sem recursivo
	public void insere(int valor) {
		Nodo novo = new Nodo(valor, null, null);
		boolean achou = false;
		if (raiz == null) {
			this.raiz = novo;
			return;
		}
		// tenho que pensar na direita e esquerda
		Nodo aux = this.raiz;
		while (!achou) {
			if (valor < aux.getValor()) {
				// tem duas condições a esquerda pode ser null ou ja colocada
				if (aux.getEsquerda() != null) {
					aux = aux.getEsquerda();
				} else {
					aux.setEsquerda(novo);
					achou = true;
				}
			} else {
				if (aux.getDireita() != null) {
					aux = aux.getDireita();

				} else {
					aux.setDireita(novo);
					achou = true;
				}
			}
		} // fim do while
	} // fim do metodo

	public void insereRecursivo(Nodo aux, int valor) {
		Nodo novo = new Nodo(valor, null, null);
		if (aux != null) {
			// esquerda
			if (valor < aux.getValor()) {

				if (aux.getEsquerda() != null) {
					insereRecursivo(aux.getEsquerda(), valor);
				} else
					aux.setEsquerda(novo);
			} else {
				if (aux.getDireita() != null) {
					insereRecursivo(aux.getDireita(), valor);
				} else
					aux.setDireita(novo);
			}
		} else {
			// direita
			setRaiz(novo); // this.raiz = novo;
			// esse set raiz auxilia em algumas linhas
		}
	}

	public void imprimeRecursiva(Nodo aux) {
		int i = 0;
		if (this.raiz == null) {
			System.out.println("Lista vazia!");
			return;
		}
		if (aux != null) {
			
			
			System.out.println("Valores: " + aux.getValor());
			imprimeRecursiva(aux.getEsquerda());
			imprimeRecursiva(aux.getDireita());
		}
		
		// se colocar else vai dar errado pq é recursivo
	}

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

	/*
	 * 1. DELETA FOLHA () 2. DELETA A RAIZ() 3. DELETA NETOS ()
	 * 
	 * 
	 * 
	 */
	public void deleta(int valor) {
		Nodo aux = this.raiz;
		if (this.raiz == null) {
			System.out.println("Lista vazia, não é possível pesquisar!");
			return;
		}
		// relação de comparação == valor

		boolean achou = false;
		while (aux != null) {
			if (valor < aux.getValor()) {
				// primeiro quem não tem filho
				if (aux.getEsquerda() != null && aux.getEsquerda().getValor() == valor) {
					Nodo esquerda = aux.getEsquerda();
					if (esquerda.getEsquerda() == null && esquerda.getDireita() == null) {
						aux.setEsquerda(null);
						System.out.println("Valor deletado! " + valor + " este valor é uma folha!!!!");
						achou = true;
						break;
					} else {
						aux = aux.getEsquerda();
					}
				}
				if (achou == false) {
					System.out.println("Valor não encontrado " + valor);
						return;
				}
			} else {
				if (aux.getDireita() != null && aux.getDireita().getValor() == valor) {
					Nodo direita = aux.getDireita();
					if (direita.getEsquerda() == null && direita.getDireita() == null) {
						aux.setDireita(null);
						System.out.println("Valor deletado! " + valor + " este valor é uma folha!!!!");
						achou = true;
						break;
					} else {
						System.out.println("Nó encontrado mas não é folha!");
						break;
					}
				} else {
					aux = aux.getDireita();
				}

				if (achou == false) {
					System.out.println("Valor não encontrado " + valor);
					return;
				}
			}

		} // while

	}
}
