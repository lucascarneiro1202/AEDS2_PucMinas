class Lab04_Q01 {
  public static void print(int a [])
  {
    for (int i = 0; i < a.length; i++)
    {
      MyIO.println(a[i]);
    }
  }

  public static int [] ler (int N)
  {
  //Definir dados locais
    int a[] = new int[N];
  //Ler cada posicao 
    for (int i = 0; i < N; i++)
    {
      a[i] = MyIO.readInt();
    }
  //Retornar
    return a;
  }

  public static void mostrarResultado (int a[], int N, int M)
  {
    MyIO.print(N + " " + M);
    for (int i = 0; i < N; i++)
    {
      MyIO.print("\n" + a[i]);
    }
    MyIO.println("");
  }

  public static int [] mods(int a[], int M)
  {
  //Definir dados locais
    int length = a.length;
    int b [] = new int[length];
  //Calcular cada valor individual
    for (int i = 0; i < length; i++)
    {
      b[i] = a[i] % M;
    }
  //Retornar
    return b;
  }

  public static void swap (int x, int y, int a[])
  {
    int temp = a[x];
    a[x] = a[y];
    a[y] = temp;
  }

  public static void comparar (int a[], int b[])
  {
  //Definir dados locais
    int length = a.length;
    int x;
  //Percorrer o arranjo de mods
    x = b[0];
    for (int i = 1; i < length; i++)
    {
    //Testar se e' igual
      if (x == b[i])
      {
      //Definir dados locais
        int p1 = a[i - 1], p2 = a[i];
        int mod1 = p1 % 2, mod2 = p2 % 2;
      //Testar casos de empate
        if ( mod1 == 0 && mod2 == 0 )
        {
        //Testar qual e' o maior (o menor vem antes)
          if (p1 > p2)
          {
            swap(i - 1, i, a);
            i = 0;
          }
        }
        else if ( mod1 == 1 && mod2 == 1 )
        {
        //Testar qual e' o maior (o maior vem antes)
          if (p1 < p2)
          {
            swap(i - 1, i, a);
            i = 0;
          }
        }
        else if ( mod1 == 0 && mod2 == 1 )
        {
          swap(i - 1, i, a);
          i = 0;
        }
      }
    //Atualizar posicao
      x = b[i];
    }
  }

  public static void selecao (int a[], int b[])
  {
  //Definir dados locais
    int length = a.length;
    int c [];
  //Algoritmo
    for (int i = 0; i < length - 1; i++)
    {
      int menor = i;
      for (int j = i + 1; j < length; j++)
      {
        if (b[menor] > b[j])
        {
          menor = j;
        }
      }
      swap(menor, i, b);
      swap(menor, i, a);
    }
  //Chamar funcao para comparar valores
    comparar(a, b);
  }

  public static void ordenar(int a [], int M)
  {
  //Definir dados locais
    int length = a.length;
    int b [];
  //Preencher valores com o resto de cada valor
    b = mods(a, M);
  //Encontrar ordem
    selecao(a, b);
  }

  public static void main (String args [])
  {
  //Definir dados locais
    int N, M;
    int a [], b[];
  //Ler dados
    N = MyIO.readInt();
    M = MyIO.readInt();
  //Testar quantidade
    while (0 < N && N <= 10000 && 0 < M && M <= 10000)
    {
    //Ler numeros
      a = ler(N);
    //Ordenar o arranjo
      ordenar(a, M);
    //Mostrar resultado
      mostrarResultado(a, N, M);
    //Ler novos valores
      N = MyIO.readInt();
      M = MyIO.readInt();      
    }
    MyIO.println("0 0");
  }
}