package ArvoreAVL;

public class AVL {
	private Nodo raiz;

	// primeira forma

	public Nodo getRaiz() {
		return raiz;
	}

	public void setRaiz(Nodo raiz) {
		this.raiz = raiz;
	}

	// parecido com o nosso metodo binaria, achei mais bagunçado, mas eu prefiro
	// esse
	public void imprimeRecursiva(Nodo aux) {

		if (this.raiz == null) {
			System.out.println("Árvore vazia!");
			return;
		}

		if (aux != null) {
			System.out.println("Valor: " + aux.getValor() + " | Altura: " + aux.getAltura() + " | FB: "
					+ aux.getFatorBalanceamento());
			imprimeRecursiva(aux.getEsquerda());
			imprimeRecursiva(aux.getDireita());
		}
	}

	// outra forma de fazer o imprime, mais visual [pesquisei esse para eu acertar
	// as contas]
	// ele é muito visual, verifica se é null

	// chama o metodo aqui para imprimir, mas esse eu não estou usando para nada
	// além do calculo do fator b.
	public void imprimir() {
		imprimir(raiz, "", true);
	}

	private void imprimir(Nodo nodo, String prefixo, boolean ehUltimo) {
		if (this.raiz == null) {
			System.out.println("Lista vazia!!! não é possivel imprimir");
			return;
		}
		if (nodo != null) {
			System.out.println(prefixo + (ehUltimo ? "└── " : "├── ") + nodo.valor + " (FB: "
					+ nodo.getFatorBalanceamento() + ")");
			imprimir(nodo.esquerda, prefixo + (ehUltimo ? "    " : "│   "), false);
			imprimir(nodo.direita, prefixo + (ehUltimo ? "    " : "│   "), true);
		}
	}

	// metodo que irá ser chamado
	public void inserir(int valor) {

		raiz = inserir(raiz, valor);
	}

	private Nodo inserir(Nodo nodo, int valor) {
		if (nodo == null)
			return new Nodo(valor); // se valor é vazio, cria um novo nodo

		if (valor < nodo.valor) {
			nodo.esquerda = inserir(nodo.esquerda, valor);
			nodo.esquerda.pai = nodo;
		} else if (valor > nodo.valor) {
			nodo.direita = inserir(nodo.direita, valor);
			nodo.direita.pai = nodo;
		} else // se valor não é menor ou maior ele é igual e eu nao quero
			return nodo;

		atualizarAltura(nodo);
		return balancear(nodo);
		// atualiza e faz as rotações (Está no final do código)
	}
	// metodo deleta usei praticamente igual o antigo da binária, mas eu adicionei a
	// rotação e atualização do fb

	public void deleta(int valor) {
		raiz = removerRecursivo(raiz, null, valor);
	}

	private Nodo removerRecursivo(Nodo aux, Nodo pai, int valor) {
		if (aux == null) {
			System.out.println("Valor não encontrado!");
			return null;
		}

		if (valor < aux.getValor()) {
			aux.setEsquerda(removerRecursivo(aux.getEsquerda(), aux, valor));
		} else if (valor > aux.getValor()) {
			aux.setDireita(removerRecursivo(aux.getDireita(), aux, valor));
		} else {
			// Valor encontrado
			if (aux.getEsquerda() == null && aux.getDireita() == null) {
				return removeFolha(aux, pai);
			} else if (aux.getEsquerda() != null && aux.getDireita() == null) {
				return removeComFilhoEsquerda(aux, pai);
			} else if (aux.getEsquerda() == null && aux.getDireita() != null) {
				return removeComFilhoDireita(aux, pai);
			} else {
				return removeComDoisFilhos(aux);
			}
		}

		// Atualiza altura e balanceia
		atualizarAltura(aux);
		return balancear(aux);
	}

	private Nodo removeFolha(Nodo aux, Nodo pai) {
		return null;
	}

	private Nodo removeComFilhoEsquerda(Nodo aux, Nodo pai) {
		Nodo filho = aux.getEsquerda();
		filho.setPai(pai);
		return filho;
	}

	private Nodo removeComFilhoDireita(Nodo aux, Nodo pai) {
		Nodo filho = aux.getDireita();
		filho.setPai(pai);
		return filho;
	}

	private Nodo removeComDoisFilhos(Nodo aux) {
		Nodo sucessor = aux.getDireita();
		Nodo paiSucessor = aux;

		while (sucessor.getEsquerda() != null) {
			paiSucessor = sucessor;
			sucessor = sucessor.getEsquerda();
		}

		aux.setValor(sucessor.getValor());

		if (sucessor.getDireita() != null) {
			aux.setDireita(removerRecursivo(aux.getDireita(), aux, sucessor.getValor()));
		} else {
			aux.setDireita(removerRecursivo(aux.getDireita(), aux, sucessor.getValor()));
		}

		atualizarAltura(aux);
		return balancear(aux);
	}

	//////////////////////////////////////////////////////////////////////////

	private Nodo menorValor(Nodo nodo) {
		while (nodo.esquerda != null)
			nodo = nodo.esquerda;
		return nodo;
	}

//////////////////////////////////////////
	// usei o metodo da Binária anterior e adaptei
	public void pesquisa(int valor) {
		if (raiz == null) {
			System.out.println("Árvore vazia.");
		} else {
			boolean encontrado = pesquisaRecursiva(valor, raiz);
			if (!encontrado) {
				System.out.println("Valor não encontrado: " + valor);
			}
		}
	}

	private boolean pesquisaRecursiva(int valor, Nodo aux) {
		if (aux == null) {
			return false;
		}

		if (aux.getValor() == valor) {
			System.out.println("Valor encontrado: " + valor);
			return true;
		}

		if (valor < aux.getValor()) {
			return pesquisaRecursiva(valor, aux.getEsquerda());
		} else {
			return pesquisaRecursiva(valor, aux.getDireita());
		}
	}
////////////////////////////////////////////

	//
	private void atualizarAltura(Nodo nodo) {
		int alturaEsquerda = 0;
		int alturaDireita = 0;

		if (nodo.getEsquerda() != null) {

			alturaEsquerda = nodo.getEsquerda().getAltura();
		}

		if (nodo.getDireita() != null) {
			alturaDireita = nodo.getDireita().getAltura();
		}
		// se existir pegar a altura da esquerda ou direita

		int maiorAltura;

		if (alturaEsquerda > alturaDireita) {
			maiorAltura = alturaEsquerda;
		} else {
			maiorAltura = alturaDireita;
		}
		// compara as alturas
		nodo.setAltura(maiorAltura + 1);
	}

	//toda vez que eu adicionar ou deletar tenho que chamar esse metodo
	//ele calcula o fator b.
	private Nodo balancear(Nodo nodo) {
	    int fb = nodo.getFatorBalanceamento();

	    // Casos de desbalanceamento para a direita
	    if (fb > 1) {
	        // Caso de rotação dupla (Direita-Esquerda)
	        if (nodo.direita.getFatorBalanceamento() < 0) {
	            nodo.direita = rotacaoDireita(nodo.direita);
	        }
	        // Rotação simples à esquerda
	        return rotacaoEsquerda(nodo);
	    }

	    // Casos de desbalanceamento para a esquerda
	    if (fb < -1) {
	        // Caso de rotação dupla (Esquerda-Direita)
	        if (nodo.esquerda.getFatorBalanceamento() > 0) {
	            nodo.esquerda = rotacaoEsquerda(nodo.esquerda);
	        }
	        // Rotação simples à direita
	        return rotacaoDireita(nodo);
	    }

	    // Se FB está entre -1 e 1, está balanceado
	    return nodo;
	}


	private Nodo rotacaoEsquerda(Nodo x) {
		Nodo y = x.direita;
		Nodo T2 = y.esquerda;

		y.esquerda = x;
		x.direita = T2;

		if (T2 != null)
			T2.pai = x;
		y.pai = x.pai;
		x.pai = y;

		atualizarAltura(x);
		atualizarAltura(y);

		return y;
	}

	private Nodo rotacaoDireita(Nodo y) {
		Nodo x = y.esquerda;
		Nodo T2 = x.direita;

		x.direita = y;
		y.esquerda = T2;

		if (T2 != null)
			T2.pai = y;
		x.pai = y.pai;
		y.pai = x;

		atualizarAltura(y);
		atualizarAltura(x);

		return x;
	}
}
