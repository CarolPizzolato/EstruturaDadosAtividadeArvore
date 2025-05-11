package ArvoreAVL;

public class Main {

	public static void main(String[] args) {
		
		AVL avl = new AVL ();
		avl.inserir(3);
		avl.inserir(10);
		avl.inserir(1);
		avl.inserir(2);
		avl.inserir(3);
		avl.inserir(4);
		avl.inserir(5);
		avl.inserir(0);
		
		//avl.imprimeRecursiva(avl.getRaiz());
		
		//metodo abaixo para visualização do calculo
		avl.imprimir();
	}

}
