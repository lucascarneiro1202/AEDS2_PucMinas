class TP01_Q14 {
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
   * isLetter - Funcao para testar se caractere e' uma letra
   * @param c - Caractere a ser testado
   * @return boolean - True, se for letra. False, se contrario
   */
  public static boolean isLetter (char c)
  {
    return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z');
  }

  /**
   * isVowel - Funcao para testar se caractere e' uma vogal
   * @param c - Caractere a ser testado
   * @return boolean - True, se for vogal. False, se contrario
   */
  public static boolean isVowel (char c)
  {
    return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
  }

  /**
   * isConsonant - Funcao para testar se caractere e' uma consoante
   * @param c - Caractere a ser testado
   * @return boolean - True, se for consoante. False, se contrario
   */
  public static boolean isConsonant (char c)
  {
    return isLetter(c) && ! isVowel(c);
  }

  /**
   * isInteger - Funcao para testar se caractere e' um inteiro
   * @param c - Caractere a ser testado
   * @return boolean - True, se for inteiro. False, se contrario
   */
  public static boolean isInteger (char c)
  {
    return '0' <= c && c <= '9';
  }

  /**
   * isAllVowelRecursive - Funcao recursiva para testar se uma string possui so' vogais
   * @param s - String a ser testada
   * @return resp - True, se for composta apenas por vogais. False, se contrario.
   */
  public static boolean isAllVowelRecursive (String s, int i)
  {
  //Definir dados locais
    boolean resp = true;
    int length = s.length();
  //Passar por cada elemento
    if (i < length)
      resp = isVowel( s.charAt(i) ) && isAllVowelRecursive (s, ++i);
  //Retornar
    return resp;
  }

  /**
   * isAllConsonantRecursive - Funcao recursiva para testar se uma string possui só consoantes
   * @param s - String a ser testada
   * @return resp - True, se for composta apenas por consoantes. False, se contrario.
   */
  public static boolean isAllConsonantRecursive (String s, int i)
  {
  //Definir dados locais
    boolean resp = true;
    int length = s.length();
  //Passar por cada elemento
    if (i < length)
      resp = isConsonant( s.charAt(i) ) && isAllConsonantRecursive (s, ++i);
  //Retornar
    return resp;
  }

  /**
   * isAllIntegerInteger - Funcao recursiva para testar se uma string e' um numero inteiro
   * @param s - String a ser testada
   * @return resp - True, se for um numero inteiro. False, se contrario.
   */
  public static boolean isAllIntegerRecursive (String s, int i)
  {
  //Definir dados locais
    boolean resp = true;
    int length = s.length();
  //Passar por cada elemento
    if (i < length)
      resp = isInteger( s.charAt(i) ) && isAllIntegerRecursive(s, ++i);
  //Retornar
    return resp;  
  }

  /**
   * isAllDoubleRecursive - Funcao recursiva para testar se uma string e' um numero real
   * @param s - String a ser testada
   * @return resp - True, se for um numero real. False, se contrario.
   */
  public static boolean isAllDoubleRecursive (String s, int i, int x)
  {
  //Definir dados locais
    boolean resp = true;
    int length = s.length();
  //Passar por cada elemento
    if (i < length)
    {
      if ( s.charAt(i) == '.' || s.charAt(i) == ',' )
        resp = ++x <= 1 && isAllDoubleRecursive(s, ++i, x);
      else
        resp = x <= 1 && isInteger( s.charAt(i) ) && isAllDoubleRecursive(s, ++i, x);
    }
  //Retornar
    return resp;
  }

  /**
   * mostrarResultado - Funcao para mostrar o resultado do teste
   */
  public static void mostrarResultado (boolean resp)
  {
    if (resp)
      MyIO.print("SIM");
    else
      MyIO.print("NAO");
  }

  public static void main (String args [])
  {
  //Definir dados
    MyIO.setCharset("UTF-8");
    String str;
  //Ler linha
    str = MyIO.readLine();
  //Condicao
    while ( ! equalsRecursive(str, "FIM", 0) )
    {
    //Mostrar resultado
      mostrarResultado( isAllVowelRecursive(str, 0) );
      MyIO.print(" ");
      mostrarResultado( isAllConsonantRecursive(str, 0) );
      MyIO.print(" ");
      mostrarResultado( isAllIntegerRecursive(str, 0) );
      MyIO.print(" ");
      mostrarResultado( isAllDoubleRecursive(str, 0, 0) );
      MyIO.println("");
    //Ler proxima linha
      str = MyIO.readLine();
    }
  }
}