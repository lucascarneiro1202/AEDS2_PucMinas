class PapaiNoel {
  public static void ordenarCriancas (String criancas [])
  {
  //Definir dados locais
    int N = criancas.length;
  //Ordenar
    for (int i = 0; i < N - 1; i++)
    {
      int menor = i;
      for (int j = i + 1; j < N; j++)
      {
      //Definir dados locais
        int k = 0;
      //Procurar a posicao de desempate
        while ( k < criancas[menor].length() && k < criancas[j].length() && criancas[menor].charAt(k) == criancas[j].charAt(k) )
          k++;
      //Procurar o menor
        if ( criancas[menor].charAt(k) > criancas[j].charAt(k) )
          menor = j;
      }
      String temp = criancas[i];
      criancas[i] = criancas[menor];
      criancas[menor] = temp;
    }
  }

  public static void main (String args [])
  {
  //Definir dados locais
    int N;
  //Ler dados
    N = MyIO.readInt();
  //Testar quantidade
    if (0 <= N && N <= 100)
    {
    //Definir dados locais
      String criancas [] = new String[N];
      int sim = 0, nao = 0;
    //Repetir para a quantidade informada
      for (int i = 0; i < N; i++)
      {
      //Definir dados locais
        char c = '\0';
      //Ler caractere
        c = MyIO.readString().charAt(0);
      //Ler criancas
        criancas[i] = MyIO.readLine();
      //Testar se foi bem comportada
        if (c == '+')
          sim++;
        else if (c == '-')
          nao++;
      }
    //Colocar as criancas em ordem alfabetica
      ordenarCriancas(criancas);
    //Mostrar resultado
      for (int i = 0; i < N; i++)
      {
        MyIO.println(criancas[i]);
      }
      MyIO.println("Se comportaram: " + sim + " | Nao se comportaram: " + nao);
    }
  }
}