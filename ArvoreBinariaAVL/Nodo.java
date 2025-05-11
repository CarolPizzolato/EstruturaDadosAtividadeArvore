package ArvoreAVL;

public class Nodo {

	int valor, balanceamento, altura;
	Nodo esquerda, direita, pai;
	
	
	
	public Nodo () {
		setValor(0);
		setAltura(0);
		setBalanceamento(0);
		setEsquerda(null);
		setDireita(null);
		setPai(null);
		
	}
	public Nodo(int valor) {
		setValor (valor);
		setAltura(0);
		setBalanceamento(0);
		setEsquerda(null);
		setDireita(null);
		setPai(null);
	}
	
	/*Tem outra forma de fazer [pesquisei] achei muito complicado
	
	
	int getFatorBalanceamento() {
	    int altEsq = (esquerda != null) ? esquerda.altura : 0;
	    int altDir = (direita != null) ? direita.altura : 0;
	    return altDir - altEsq;
	}

	*/
	
	//metodo melhor
	int getFatorBalanceamento() {
		
	    int altEsq = 0;
	    int altDir = 0;
	    if (esquerda != null) {
	        altEsq = esquerda.altura; // vá até o filho da esquerda e pegue o valor da altura dele.
	    }
	    if (direita != null) {
	        altDir = direita.altura;
	    }
	    return altDir - altEsq;
	}


	public int getValor() {
		return valor;
	}



	public void setValor(int valor) {
		this.valor = valor;
	}



	public int getBalanceamento() {
		return balanceamento;
	}



	public void setBalanceamento(int balanceamento) {
		this.balanceamento = balanceamento;
	}



	public int getAltura() {
		return altura;
	}



	public void setAltura(int altura) {
		this.altura = altura;
	}



	public Nodo getEsquerda() {
		return esquerda;
	}



	public void setEsquerda(Nodo esquerda) {
		this.esquerda = esquerda;
	}



	public Nodo getDireita() {
		return direita;
	}



	public void setDireita(Nodo direita) {
		this.direita = direita;
	}



	public Nodo getPai() {
		return pai;
	}



	public void setPai(Nodo pai) {
		this.pai = pai;
	}
	
}
