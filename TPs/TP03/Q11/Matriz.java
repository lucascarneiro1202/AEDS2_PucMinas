import java.util.Scanner;

class Celula {
   public int elemento;
   public Celula inf, sup, esq, dir;

   public Celula(){
      this(0);
   }

   public Celula(int elemento){
      this(elemento, null, null, null, null);
   }

   public Celula(int elemento, Celula inf, Celula sup, Celula esq, Celula dir){
      this.elemento = elemento;
      this.inf = inf;
      this.sup = sup;
      this.esq = esq;
      this.dir = dir;
   }
}

public class Matriz {
  public static Scanner sc = new Scanner(System.in);

  private Celula inicio;
  private int linha, coluna;

  public Matriz (){
    this(3, 3);
  }

  public Matriz (int linha, int coluna) {
  //Definir dados de linha e coluna
    this.linha = linha;
    this.coluna = coluna;
  //Criar as colunas
    inicio = new Celula();
    Celula i = inicio, j = null;
    for (int x = 1; x < coluna; x++) {
    //Criar nova celula
      j = new Celula();
    //Conectar celulas
      i.dir = j;
      j.esq = i;
    //Avancar ponteiros
      i = i.dir;
      j = j.dir;
    }
  //Criar as linhas
    i = inicio;
    j = i.dir;
    for  (int x = 1; x < linha; x++) {
    //Criar nova celula
      i.inf = new Celula();
      i.inf.sup = i;
    //Criar ponteiros temporarios
      Celula iTemp = i;
      Celula jTemp = j;
    //Criar restante da linha
      for (int y = 1; y < coluna; y++) {
      //Criar nova celula        
        jTemp.inf = new Celula();
        jTemp.inf.sup = jTemp;
      //Conectar celula
        iTemp.inf.dir = jTemp.inf;
        jTemp.inf.esq = iTemp.inf;
      //Avancar ponteiros
        iTemp = iTemp.dir;
        jTemp = jTemp.dir;
      }
    //Avancar ponteiros
      i = i.inf;
      j = j.inf;
    }
  }

  public void ler () throws Exception {
  //Testar valores de linha e coluna
    if (linha <= 0 || coluna <= 0) throw new Exception ("Não é possível ler dados para a matriz.");
  //Ler dados e atribuir a' matriz
    Celula i = this.inicio;
    for (int x = 0; x < linha; x++) {
      Celula j = i;
      for (int y = 0; y < coluna; y++) {
        j.elemento = sc.nextInt();
        j = j.dir;
      }
      i = i.inf;
    }
  }


  public Matriz soma (Matriz m) throws Exception {
  //Testar se é possível somar as matrizes
    if (this.linha != m.linha || this.coluna != m.coluna) throw new Exception ("Não é possível somar duas matrizes.");
  //Definir matriz de resposta
    Matriz resp = new Matriz (this.linha, this.coluna);
  //Definir ponteiros
    Celula a = this.inicio;
    Celula b = m.inicio;
    Celula c = resp.inicio;
  //Percorrer as linhas
    for (int x = 0; x < linha; x++) {
    //Definir ponteiros temporarios
      Celula a_ = a;
      Celula b_ = b;
      Celula c_ = c;
    //Percorrer as colunas
      for (int y = 0; y < coluna; y++) {
      //Definir soma dos elementos
        c_.elemento = a_.elemento + b_.elemento;
      //Deslocar ponteiros 
        a_ = a_.dir;
        b_ = b_.dir;
        c_ = c_.dir;
      }
    //Deslocar ponteiros
      a = a.inf;
      b = b.inf;
      c = c.inf;
    }
  //Retornar
    return resp;
  }

  public Matriz multiplicacao(Matriz m) throws Exception {
  //Verificar se a multiplicação é possível
    if (this.coluna != m.linha) 
      throw new Exception("Não é possível multiplicar as duas matrizes");
  //Criar matriz de resposta
    Matriz resp = new Matriz(this.linha, m.coluna);
  //Definir ponteiros para percorrer as linhas e colunas
    Celula linhaResp = resp.inicio; //Linha da matriz resposta
    Celula linhaThis = this.inicio; //Linha da matriz atual
  //Percorrer as linhas
    for (int x = 0; x < resp.linha; x++) {
    //Definir ponteiros
      Celula colunaResp = linhaResp; //Coluna da matriz resposta
      Celula colunaMatriz = m.inicio; //Coluna da matriz m
    //Percorrer as colunas
      for (int y = 0; y < resp.coluna; y++) {
      //Definir ponteiros
        Celula elementoLinhaThis = linhaThis; // Elemento atual da linha de 'this'
        Celula elementoColunaMatriz = colunaMatriz; // Elemento atual da coluna de 'm'
      //Calcular o valor para resp[x][y]
        int soma = 0;
        for (int k = 0; k < this.coluna; k++) {
          soma += elementoLinhaThis.elemento * elementoColunaMatriz.elemento;
          elementoLinhaThis = elementoLinhaThis.dir; // Avançar na linha de 'this'
          elementoColunaMatriz = elementoColunaMatriz.inf; // Avançar na coluna de 'm'
        }
      //Atribuir soma ao elemento da matriz resposta
        colunaResp.elemento = soma;
      //Deslocar ponteiros
        colunaResp = colunaResp.dir; // Avançar para a próxima coluna
        colunaMatriz = colunaMatriz.dir; // Avançar para a próxima coluna de 'm'
      }
    //Deslocar ponteiros
      linhaResp = linhaResp.inf;
      linhaThis = linhaThis.inf;
    }
  //Retornar
    return resp;
  }

  public boolean isQuadrada() {
    return (this.linha == this.coluna);
  }

  public void mostrarDiagonalPrincipal () {
  //Testar se a matriz é quadrada
    if ( !isQuadrada() ) return;
  //Percorrer a diagonal principal
    Celula i = this.inicio;
    for (int x = 0; x < linha; x++) {
      System.out.print(i.elemento + " ");
      i = (i.dir != null) ? i.dir.inf : null;
    }
    System.out.println("");
  }

  public void mostrarDiagonalSecundaria () {
  //Testar se a matriz é quadrada
    if ( !isQuadrada() ) return;
  //Percorrer a diagonal secundaria
    Celula i = inicio;
    for (int x = 1; x < coluna; x++, i = i.dir);
    for (int x = 0; x < linha; x++) {
      System.out.print(i.elemento + " ");
      i = (i.esq != null) ? i.esq.inf : null;
    }
    System.out.println("");
  }

  public void mostrar () {
    if (this.linha <= 0 && this.coluna <= 0) return;
    Celula i = inicio;
    for (int x = 0; x < linha; x++, i = i.inf) {
      Celula j = i;
      for (int y = 0; y < coluna; y++, j = j.dir) {
        System.out.print(j.elemento + " ");
      }
      System.out.println("");
    }
  }

  public static void main (String args []) {
    try {
    //Definir dados locais
      int n;
    //Ler quantidade de casos
      n = sc.nextInt();
      // n = 1;
    //Laço de repetiçao
      for (int i = 0; i < n; i++) {
      //Definir dados locais
        int linha1, coluna1, linha2, coluna2;
      //Ler linhas e colunas da primeira matriz
        linha1 = sc.nextInt();
        coluna1 = sc.nextInt();
      //Definir primeira matriz
        Matriz matriz1 = new Matriz(linha1, coluna1);
        matriz1.ler();
      //Ler dados da segunda matriz
        linha2 = sc.nextInt();
        coluna2 = sc.nextInt();
      //Definir segunda matriz
        Matriz matriz2 = new Matriz(linha2, coluna2);
        matriz2.ler();
      //Mostrar diagonais da primeira matriz
        matriz1.mostrarDiagonalPrincipal();
        matriz1.mostrarDiagonalSecundaria();
      //Calcular matriz de soma
        Matriz matrizSoma = matriz1.soma(matriz2);
        matrizSoma.mostrar();
      //Calcular matriz de multiplicacao
        Matriz matrizMultiplicacao = matriz1.multiplicacao(matriz2);
        matrizMultiplicacao.mostrar();
      //Desreferenciar ponteiros
        matriz1 = matriz2 = matrizSoma = matrizMultiplicacao = null;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
