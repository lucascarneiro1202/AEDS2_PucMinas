import java.util.Scanner;

public class Q01 {
  public static Scanner sc = new Scanner (System.in);
  public static void main (String args []) {
  //Definir dados locais
    int qtd = 0;

  //Ler quntidade de criancas
    qtd = sc.nextInt();

  //Ler todas as criancas
    while (qtd > 0) {
    //Definir dados locais
      Lista lista = new Lista();

    //Ler dados de cada crianca
      for (int i = 0; i < qtd; i++) {
        String nome = sc.next();
        int valor = sc.nextInt();
        Celula cel = new Celula(nome, valor);
        lista.inserirFim(cel);
      }

    //Definir dados locais
      int valorPrimeira = lista.primeira.prox.valor;
      Celula inicio = (valorPrimeira % 2 == 0) ? lista.primeira.ant : lista.primeira.prox.prox;

    //Laco de repeticao para caminhar na fila
      while ( lista.length > 1 ) {
        Celula c;
        if (valorPrimeira % 2 == 0) {
          c = lista.removerHorario(inicio, valorPrimeira);
          inicio = c.ant;
        } else {
          c = lista.removerAntiHorario(inicio, valorPrimeira);
          inicio = c.prox;
        }
        valorPrimeira = c.valor;
      }

      System.out.println("Vencedor(a): " + lista.ultima.nome);

      qtd = sc.nextInt();
    }
  }
}

class Celula {
  public String nome;
  public int valor;  
  public Celula prox;
  public Celula ant;

  public Celula () {
    nome = "";
    valor = -1;
    prox = ant = null;
  }

  public Celula (String s, int x) {
    nome = s;
    valor = x;
    prox = ant = null;
  }

  public boolean equals (Celula cel) {
    return this.nome.equals(cel.nome) && this.valor == cel.valor;
  }
}

class Lista {
  public Celula primeira;
  public Celula ultima;
  int length;

  public Lista () {
    primeira = new Celula();
    ultima = primeira;
    length = 0;
  }

  public void inserirFim (Celula cel) {
    ultima.prox = cel;
    cel.ant = ultima;
    ultima = cel;

    cel.prox = primeira;
    primeira.ant = cel;

    length++;
  }

  public void printHorario () {
    int j = 0;
    Celula i = primeira.prox;
    System.out.println((j++) + ": " + i.nome + " (" + i.valor + ")");
    i = ultima;
    while (i != primeira) {
      System.out.println((j++) + ": " + i.nome + " (" + i.valor + ")");
      i = i.ant;
    }
  }

  public void printAntiHorario () {
    int j = 0;
    Celula i = primeira.prox;
    while (i != primeira) {
      System.out.println((j++) + ": " + i.nome + " (" + i.valor + ")");
      i = i.prox;
    }
  }

  public Celula remover (Celula cel) {
    Celula i = primeira.prox;

    while ( !i.equals(cel) && !i.equals(primeira)) {
      i = i.prox;
    }

    if (i.equals(ultima)) {
      ultima = i.ant;
    }

    i.ant.prox = i.prox;
    i.prox.ant = i.ant;

    // i.prox = null;
    // i.ant = null;

    length--;

    return i;
  }

  public Celula removerHorario (Celula inicio, int valor) {
    Celula i = inicio;
    int j = 1;

    while (j < valor) {
      i = i.ant;
      if (i.equals(primeira))
        i = i.ant;
      j++;
    }

    return remover(i);
  }

  public Celula removerAntiHorario (Celula inicio, int valor) {
    Celula i = inicio;
    int j = 1;

    while (j < valor) {
      i = i.prox;
      if (i.equals(primeira))
        i = i.prox;
      j++;
    }
 
    return remover(i);
  }
}