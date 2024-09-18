import java.util.ArrayList;

class TP01_Q13 {
  /**
   * equalsRecursive - Funcao recursiva para testar se uma string e' igual a outra
   * @param s - String a ser comparada
   * @param t - String a ser comparada
   * @return resp - True, se forem iguais. False, se contrario
   */
  public static boolean equalsRecursive (String s, String t, int i)
  {
  ///Definir dados locais
    boolean resp;
    int sLength = s.length();
    int tLength = t.length();
  //Testar se sao do mesmo tamanho
    resp = sLength == tLength;
  //Condicao de parada
    if (resp && i < sLength)
    {
    //Motor da recursividade
      resp = s.charAt(i) == t.charAt(i) && equalsRecursive(s, t, ++i);
    }
  //Retornar
    return resp;
  }

  /**
   * replaceRecursive - Funcao recursiva para substituir certo intervalo de uma string por um valor inteiro
   * @param str - String a ser manipulada
   * @param var - Valor inteiro a ser inserido
   * @param abre - Valor inicial do intervalo a ser retirado
   * @param fecha - Valor final do intervalo a ser retirado
   * @param n - Quantidade de casas a serem retiradas antes do primeiro valor (para retirar "not", "and", ou "or")
   * @param i - Posicao inicial a ser testada
   * @return s - String resultante do reposicionamento
   */
  public static String replaceRecursive (String str, int var, int abre, int fecha, int n, int i)
  {
  //Definir dados locais
    String s = "";
  //Testar se intervalo e' valido
    if (abre < fecha)
    {
    //Concatenar primeira parte
      if (i < abre - n)
      {
        s += str.charAt(i);
        s += replaceRecursive(str, var, abre, fecha, n, ++i);
      }
      else if (i == abre - n)
      {
      //Concatenar valor desejado
        s += "" + var;
      //Delimitar o intervalo superior
        i = fecha + 1;
      }
    //Concatenar segunda parte
      if (fecha + 1 <= i && i < str.length())
      {
        s += str.charAt(i);
        s += replaceRecursive(str, var, abre, fecha, n, ++i);
      }
    }
  //Retornar
    return s;
  }

  /**
   * substrRecursive - Funcao recursiva para extrair certo intervalo de uma string
   * @param str - String a ser manipulada
   * @param inicio - Inicio do intervalo a ser extraido
   * @param fim - FInal do intervalo a ser extraido
   * @return s - String resultante
   */
  public static String substrRecursive (String str, int inicio, int fim)
  {
  //Definir dados locais
    StringBuilder s = new StringBuilder();
  //Concatenar as posicoes
    if (inicio <= fim)
    {
      s.append( str.charAt(inicio) );
      s.append( substrRecursive(str, ++inicio, fim) );
    }
  //Retornar
    return s.toString();
  }

  /**
   * preencherVariaveisRecursive - Procedimento recursivo para preencher o ArrayList vars
   * @param str - Expressao booleana a ser testada
   * @param a - Arranjo com os valores originais de cada variavel
   * @param vars - ArrayList com os valores de cada variavel presente na expressao booleana
   * @param i - Posicao de cada execucao
   */
  public static void preencherVariaveisRecursive (String str, int a [], ArrayList<Integer> vars, int i)
  {
  //Definir dados locais
    int length = str.length();
  //Condicao de parada
    if (i < length)
    {
    //Definir caractere interativo
      char c = str.charAt(i);
    //Testar caractere
      if ( 'A' <= c && c <= 'Z' )
        vars.add( a[c - 65] );
      else if ( c == '0' )
        vars.add( 0 );
      else if ( c == '1' )
        vars.add( 1 ); 
    //Motor da recursao
      preencherVariaveisRecursive (str, a, vars, ++i);
    }
  }

  /**
   * converterRecursive - Procedimento recursivo para converter um ArrayList<Integer> para int []
   * @param variables - Arranjo de inteiros a ser preenchido
   * @param vars - ArrayList a ser convertido
   * @param i - Posicao de cada execucao
   */
  public static void converterRecursive (int variables [], ArrayList<Integer> vars, int i)
  {
  //Definir dados locais
    int size = vars.size();
  //Condicao de parada
    if (i < size)
    {
      variables[i] = vars.get(i);
      converterRecursive(variables, vars, ++i);
    }
  }

  /**
   * lerPosicoesRecursive - Funcao para encontrar um arranjo com os valores das variaveis de determinada expressao
   * @param str - Expressao booleana a ser testada
   * @param a - Arranjo com os valores originais de cada variavel
   * @return variables - Arranjo com os valores posicionais das variaveis da expressao
   */
  public static int [] lerPosicoesRecursive (String str, int a [])
  {
  //Definir dados locais
    ArrayList<Integer> vars = new ArrayList<Integer>();
    int j = 0;
  //Preencher ArrayList
    preencherVariaveisRecursive(str, a, vars, 0);
  //Converter ArrayList to int []
    int size = vars.size();
    int variables [] = new int[size];
  //Motor da recursao
    converterRecursive(variables, vars, 0);
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
      b = var == 1;
    }
  //Retornar
    return (!b == true) ? 1 : 0;
  }

  /**
   * andRecursive - Funcao para resolver uma expressao booleana minima de conjuncao
   * @param str - Expressao booleana
   * @param a - Arranjo com os valores originais de cada variavel
   * @param variables - Arranjo com os valores posicionais das variaveis da expressao
   * @param i - Posicao de cada execucao
   * @return b - Valor booleano resultante da expressao
   * @param variables - 
   */
  public static int andRecursive (String str, int a [], int variables [], int i)
  {
  //Definir dados locais
    boolean b = true;
  //Condicao de parada
    if (i < variables.length)
    {
      boolean b1 = variables[i] == 1;
      b = b1 && andRecursive(str, a, variables, ++i) == 1;
    }
  //Retornar
    return b ? 1 : 0;
  }

  /**
   * orRecursive - Funcao para resolver uma expressao booleana minima de desjuncao
   * @param str - Expressao booleana
   * @param a - Arranjo com os valores originais de cada variavel
   * @param variables - Arranjo com os valores posicionais das variaveis da expressao
   * @param i - Posicao de cada execucao
   * @return b - Valor booleano resultante da expressao
   */
  public static int orRecursive (String str, int a [], int variables [], int i)
  {
  //Definir dados locais
    boolean b = false;
  //Condicao de parada
    if (i < variables.length)
    {
      boolean b1 = (variables[i] == 1) ? true : false;
      b = b1 || ( ( orRecursive(str, a, variables, ++i) == 1 ) ? true : false );
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
      //Definir substring
          String substring = substrRecursive(s, abre + 1, fecha - 1);
      //Testar operacao
        if (op == 't')
        {
          var = not( substring, a );
          s = replaceRecursive(s, var, abre, fecha, 3, 0);
        }
        else if (op == 'd')
        {
          var = andRecursive( substring, a, lerPosicoesRecursive(substring, a), 0 );
          s = replaceRecursive(s, var, abre, fecha, 3, 0);
        }
        else if (op == 'r')
        {
          var = orRecursive( substring, a, lerPosicoesRecursive(substring, a), 0 );
          s = replaceRecursive(s, var, abre, fecha, 2, 0);
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