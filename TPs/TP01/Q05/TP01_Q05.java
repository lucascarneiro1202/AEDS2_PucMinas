import java.util.ArrayList;

class TP01_Q05 {
  /**
   * equals - Funcao para testar se uma string e' igual a outra
   * @param s - String a ser comparada
   * @param t - String a ser comparada
   * @return resp - True, se forem iguais. False, se contrario
   */
  public static boolean equals (String s, String t)
  {
  //Definir dados locais
    boolean resp;
    int sLength = s.length();
    int tLength = t.length();
    int i = 0;
  //Testar se sao do mesmo tamanho
    resp = sLength == tLength;
  //Condicao
    while (resp && i < sLength)
    {
      resp = s.charAt(i) == t.charAt(i);
      i++;
    }
  //Retornar
    return resp;
  }

  /**
   * replace - Funcao para substituir certo intervalo de uma string por um valor inteiro
   * @param str - String a ser manipulada
   * @param var - Valor inteiro a ser inserido
   * @param abre - Valor inicial do intervalo a ser retirado
   * @param fecha - Valor final do intervalo a ser retirado
   * @param n - Quantidade de casas a serem retiradas antes do primeiro valor (para retirar "not", "and", ou "or")
   * @return s - String resultante do reposicionamento
   */
  public static String replace (String str, int var, int abre, int fecha, int n)
  {
  //Definir dados locais
    StringBuilder s = new StringBuilder();
  //Testar se intervalo e' valido
    if (abre < fecha)
    {
    //Concatenar primeira parte
      for (int i = 0; i < abre - n; i++)
      {
        s.append( str.charAt(i) );
      }
    //Concatenar valor desejado
      s.append(var);
    //Concatenar segunda parte
      for (int i = fecha + 1; i < str.length(); i++)
      {
        s.append( str.charAt(i) );
      }
    }
  //Retornar
    return s.toString();
  }

  /**
   * substr - Funcao para extrair certo intervalo de uma string
   * @param str - String a ser manipulada
   * @param inicio - Inicio do intervalo a ser extraido
   * @param fim - FInal do intervalo a ser extraido
   * @return s - String resultante
   */
  public static String substr (String str, int inicio, int fim)
  {
  //Definir dados locais
    StringBuilder s = new StringBuilder();
  //Concatenar as posicoes
    for (int i = inicio; i <= fim; i++)
    {
      s.append( str.charAt(i) );
    }
  //Retornar
    return s.toString();
  }

  /**
   * lerPosicoes - Funcao para encontrar um arranjo com os valores das variaveis de determinada expressao
   * @param str - Expressao booleana a ser testada
   * @param a - Arranjo com os valores originais de cada variavel
   * @return variables - Arranjo com os valores posionais das variaveis da expressao
   */
  public static int [] lerPosicoes (String str, int a [])
  {
  //Definir dados locais
    ArrayList<Integer> vars = new ArrayList<Integer>();
    int length = str.length();
    int j = 0;
  //Passar por cada posicao
    for (int i = 0; i < length; i++)
    {
    //Definir caracter iterativo
      char c = str.charAt(i);
    //Testar caractere
      if ( 'A' <= c && c <= 'Z' )
        vars.add( a[c - 65] );
      else if ( c == '0' )
        vars.add( 0 );
      else if ( c == '1' )
        vars.add( 1 ); 
    }
  //Converter ArrayList to int []
    int size = vars.size();
    int variables [] = new int[size];
    for (int i = 0; i < size; i++)
    {
      variables[i] = vars.get(i);
    }
  //Retornar
    return variables;
  }

  /**
   * not - Funcao para resolver uma expressao booleana minima de negacao
   * @param str - Expressao booleana
   * @param a - Arranjo com os valores originais de cada variavel
   * @return b - Valor booleano resultante da expressao
   */
  public static int not (String str, int a [])
  {
  //Definir dados locais
    int var = 0;
    char c;
    boolean b = true;
  //Testar posicoes
    if ( str.length() == 1 )
    {
    //Definir dados locais
      c = str.charAt(0);
    //Testar caractere
      if ( 'A' <= c && c <= 'Z' )
        var = a[c - 65];
      else if ( c == '0' )
        var = 0;
      else if ( c == '1' )
        var = 1;
    //Converter inteiro
      b = ( var == 1 ) ? true : false;
    }
  //Retornar
    return (!b == true) ? 1 : 0;
  }

  /**
   * and - Funcao para resolver uma expressao booleana minima de conjuncao
   * @param str - Expressao booleana
   * @param a - Arranjo com os valores originais de cada variavel
   * @return b - Valor booleano resultante da expressao
   */
  public static int and (String str, int a [])
  {
  //Definir dados locais
    int variables [];
    boolean b = true;
  //Passar pelas posicoes
    variables = lerPosicoes(str, a);
  //Testar todas as variaveis
    for (int i = 0; i < variables.length; i++)
    {
      boolean b1 = (variables[i] == 1) ? true : false;
      b = b && b1;
    }
    return b ? 1 : 0;
  }

  /**
   * or - Funcao para resolver uma expressao booleana minima de desjuncao
   * @param str - Expressao booleana
   * @param a - Arranjo com os valores originais de cada variavel
   * @return b - Valor booleano resultante da expressao
   */
  public static int or (String str, int a [])
  {
  //Definir dados locais
    int variables [];
    boolean b = false;
  //Passar pelas posicoes
    variables = lerPosicoes(str, a);
  //Testar todas as variaveis
    for (int i = 0; i < variables.length; i++)
    {
      boolean b1 = (variables[i] == 1) ? true : false;
      b = b || b1;
    }
    return b ? 1 : 0;
  }

  /**
   * algebraBooleana - Funcao para manipular a leitura da expressao booleana total de tras para frente
   * @param str - Expressao booleana
   * @param a - Arranjo com os valores originais de cada variavel
   * @return b - Valor booleano final da expressao
   */
  public static boolean algebraBooleana (String str, int a [])
  {
  //Definir dados locais
    boolean resp = false;
    String s = str;
    int i = str.length() - 1;
    int abre = 0, fecha = 0;
  //Condicao de parada
    while ( s.length() > 1 && i >= 0 )
    {
    //Definir dados locais
      char c = s.charAt(i);
    //Testar se e' um "abre parenteses"
      if (c == '(')
      {
      //Definir dados locais
        abre = i;
        int j = abre;
        int var = 0;
      //Procurar o "fecha parenteses"
        while (c != ')' && j < s.length())
        {
        //Definir dados locais
          c = s.charAt(j);
        //Testar se e' o "fecha parenteses"
          if (c == ')')
          {
            fecha = j;
          }
        //Variacao
          j++;
        }
      //Identificar operacao antes do "abre parenteses"
        char op = s.charAt(abre - 1);
      //Testar operacao
        if (op == 't')
        {
          var = not( substr(s, abre + 1, fecha - 1), a );
          s = replace(s, var, abre, fecha, 3);
        }
        else if (op == 'd')
        {
          var = and( substr(s, abre + 1, fecha - 1), a );
          s = replace(s, var, abre, fecha, 3);
        }
        else if (op == 'r')
        {
          var = or( substr(s, abre + 1, fecha - 1), a );
          s = replace(s, var, abre, fecha, 2);
        }
        i = s.length();
      }
    //Variacao
      i--;
    }
  //Retornar
    return ( ( s.length() == 1 || s.length() == 2 ) && s.charAt(0) == '1' ) ? true : false;
  }

  public static void main (String args [])
  {
  //Definir dados
    String str;
    int n;
    int a [];
    boolean resp = false;
  //Ler quantidade de entradas
    n = MyIO.readInt();
  //Testar quantidade
    while (n > 0)
    {
    //Definir arranjo
      a = new int[n];
    //Ler valor das entradas
      for (int i = 0; i < n; i++)
      {
        int x = MyIO.readInt();
        a[i] = (x == 1) ? 1 : 0;
      }
    //Ler expressao
      str = MyIO.readLine();
    //Calcular resposta
      resp = algebraBooleana(str, a);
    //Mostrar resposta
      if (resp)
        MyIO.println(1);
      else
        MyIO.println(0);
    //Desalocar memoria
      a = null;
    //Ler quantidade de entradas
      n = MyIO.readInt();
    }
  }
}