package arvorebinaria;

public class Main {
    public static void main(String[] args) {
        Binaria b = new Binaria();
        
       b.insereRecursivo(b.getRaiz(), 5);
       b.insereRecursivo(b.getRaiz(), 20);
       b.insereRecursivo(b.getRaiz(), 50);
       //b.insereRecursivo(b.getRaiz(), 10);
       System.out.println("======ANTES===================");
       b.imprimeRecursiva(b.getRaiz());
       System.out.println("======DEPOUIS===================");
       
       
       System.out.println("=========================");
       
       b.deleta(10);
       b.deleta(50);
       b.imprimeRecursiva(b.getRaiz());
       System.out.println("teste");
       
       
    }
}
