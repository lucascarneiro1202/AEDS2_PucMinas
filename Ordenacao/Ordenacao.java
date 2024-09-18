class Ordenacao {
  public static void printArray (int a [])
  {
    MyIO.print("[");
    for (int i = 0; i < a.length; i++)
    {   MyIO.print(" " + a[i]);    }
    MyIO.println(" ]");
  }

  public static void readArray (int a [])
  {
    for (int i = 0; i < a.length; i++)
    {
      a[i] = MyIO.readInt();
    }
  }
  
  public static int [] gerarArrayCrescente (int n)
  {
  //Definir dados locais
    int a [] = new int[n];
  //Preencher
    for (int i = 0; i < n; i++)
    {
      a[i] = i + 1;
    }
  //Retornar
    return a;
  }

  public static int [] gerarArrayDecrescente (int n)
  {
  //Definir dados locais
    int a [] = new int[n];
    int x = 0;
  //Preencher
    for (int i = n; i > 0; i--)
    {
      a[x++] = i;
    }
  //Retornar
    return a;
  }

  public static void printComplexity (int comparacoes, int movimentacoes, int n)
  {
    MyIO.println("Comparações: " + comparacoes + " (" + n + ")");
    MyIO.println("Movimentações: " + movimentacoes + " (" + n + ")");
  }

  public static void selectionSort (int a [])
  {
  //Definir dados locais
    int comparacoes = 0, movimentacoes = 0;
    int n = a.length;
  //Mostrar antes
    MyIO.print("Antes: ");
    printArray(a);
    MyIO.println("");
  //ALGORITMO DE ORDENACAO
    for (int i = 0; i < n - 1; i++)
    {
      int menor = i;
      for (int j = i + 1; j < n; j++)
      {
        comparacoes++;
        if (a[menor] > a[j])
        {
          menor = j;
        }
      }
      movimentacoes += 3;
      int temp = a[menor];
      a[menor] = a[i];
      a[i] = temp;
    }
  //Mostrar complexidade
    printComplexity(comparacoes, movimentacoes, n);
  //Mostrar depois
    MyIO.println("");
    MyIO.print("Depois: ");
    printArray(a);
  }

  public static void insertionSort (int a [])
  {
  //Definir dados locais
    int comparacoes = 0, movimentacoes = 0;
    int n = a.length;
  //Mostrar antes
    MyIO.print("Antes: ");
    printArray(a);
    MyIO.println("");
  //ALGORITMO DE ORDENACAO
    for (int i = 1; i < n; i++)
    {
      int tmp = a[i];
      int j = i - 1;
      while ( j >= 0 && a[j] > tmp )
      {
        comparacoes++;
        movimentacoes++;
        a[j + 1] = a[j];
        j--;
      }
      comparacoes++;
      a[j + 1] = tmp;
    }
  //Mostrar complexidade
    printComplexity(comparacoes, movimentacoes, n);
  //Mostrar depois
    MyIO.println("");
    MyIO.print("Depois: ");
    printArray(a);
  }

  public static void bubbleSort (int a [])
  {
  //Definir dados locais
    int comparacoes = 0, movimentacoes = 0;
    int n = a.length;
  //Mostrar antes
    MyIO.print("Antes: ");
    printArray(a);
    MyIO.println("");  
  //ALGORITMO DE ORDENACAO
    for (int i = 1; i < n; i++)
    {
      for (int j = 0; j < n - i; j++)
      {
        comparacoes++;
        if (a[j] > a[j + 1])
        {
          movimentacoes++;
          int temp = a[j];
          a[j] = a[j + 1];
          a[j + 1] = temp;
        }
      }
    }  
  //Mostrar complexidade
    printComplexity(comparacoes, movimentacoes, n);
  //Mostrar depois
    MyIO.println("");
    MyIO.print("Depois: ");
    printArray(a);
  }

  public static void main (String args [])
  {
  //Definir dados locais
    MyIO.setCharset("UTF-8");
    int x = 0;
  //Ler quantidade
    MyIO.print("Entrar com uma quantidade de elementos: ");
    int n = MyIO.readInt();
  //Definir arranjo
    // int a [] = gerarArrayCrescente(n);
    int a [] = gerarArrayDecrescente(n);
  //Selecionar metodo
    do
    {
    //Mostrar opcoes
      MyIO.println("\n1 - Selection Sort");
      MyIO.println("2 - Insertion Sort");
      MyIO.println("3 - Bubble Sort");
      MyIO.println("4 - Shell Sort");
      MyIO.println("5 - Quick Sort");
      MyIO.println("0 - Sair");
      MyIO.print("\nEscolha uma das opcoes acima: ");
    //Ler dados
      x = MyIO.readInt();
      MyIO.println("");
    //Chamar metodo
      switch (x)
      {
        case 0: break;
        case 1: selectionSort(a); break;
        case 2: insertionSort(a); break;
        case 3: bubbleSort(a); break;
        // case 4: shellSort(a); break;
        // case 5: quickSort(a); break;
        default: MyIO.println("ERRO: Opcao invalida."); break;
      }
    }
    while (x != 0);
  }
}