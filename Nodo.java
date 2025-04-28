package arvorebinaria.EstruturaDadosAtividadeArvore;


public class Nodo {
    //na arvore é diferente as nomenclaturas
    //mas o conceito permacene praticamente o mesmo

    int valor;
    Nodo esquerda,direita;

    public Nodo(){
        this.valor = 0;
        this.esquerda = this.direita = null;
    }
    public Nodo(int v, Nodo e, Nodo d) {
        // com isso aqui não precisa ficar chamando o set.novo(valor)
        this.valor = v;
        this.direita = d;
        this.esquerda = e;
    }

    public int getValor() {
        return this.valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Nodo getEsquerda() {
        return this.esquerda;
    }

    public void setEsquerda(Nodo esquerda) {
        this.esquerda = esquerda;
    }

    public Nodo getDireita() {
        return this.direita;
    }

    public void setDireita(Nodo direita) {
        this.direita = direita;
    }

}
