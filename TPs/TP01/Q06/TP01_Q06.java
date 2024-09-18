class TP01_Q06 {
  /**
   * equals - Funcao para testar se uma string e' igual a outra
   * @param s - String a ser comparada
   * @param t - String a ser comparada
   * @return resp - Resultado da comparacao (true, se for igual. false, se contrario)
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
   * isAllVowel - Funcao para testar se uma string possui so' vogais
   * @param s - String a ser testada
   * @return resp - True, se for composta apenas por vogais. False, se contrario.
   */
  public static boolean isAllVowel (String s)
  {
  //Definir dados locais
    boolean resp = true;
    int length = s.length();
    int i = 0;
  //Passar por cada elemento
    while (i < length && resp)
    {
      resp = isVowel( s.charAt(i) );
      i++;
    }
  //Retornar
    return resp;
  }

  /**
   * isAllConsonant - Funcao para testar se uma string possui só consoantes
   * @param s - String a ser testada
   * @return resp - True, se for composta apenas por consoantes. False, se contrario.
   */
  public static boolean isAllConsonant (String s)
  {
  //Definir dados locais
    boolean resp = true;
    int length = s.length();
    int i = 0;
  //Passar por cada elemento
    while (i < length && resp)
    {
      resp = isConsonant( s.charAt(i) );
      i++;
    }
  //Retornar
    return resp;
  }

  /**
   * isAllInteger - Funcao para testar se uma string e' um numero inteiro
   * @param s - String a ser testada
   * @return resp - True, se for um numero inteiro. False, se contrario.
   */
  public static boolean isAllInteger (String s)
  {
  //Definir dados locais
    boolean resp = true;
    int length = s.length();
    int i = 0;
  //Passar por cada elemento
    while (i < length && resp)
    {
      resp = isInteger( s.charAt(i) );
      i++;
    }
  //Retornar
    return resp;  
  }

  /**
   * isAllDouble - Funcao para testar se uma string e' um numero real
   * @param s - String a ser testada
   * @return resp - True, se for um numero real. False, se contrario.
   */
  public static boolean isAllDouble (String s)
  {
  //Definir dados locais
    boolean resp = true;
    int length = s.length();
    int i = 0;
    int x = 0;
  //Passar por cada elemento
    while (i < length && resp)
    {
      if ( s.charAt(i) == '.' || s.charAt(i) == ',' )
      {
        x++;
        resp = x <= 1;
      }
      else
      {
        resp = isInteger( s.charAt(i) ) && x <= 1;
      }
      i++;
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
    while ( ! equals(str, "FIM") )
    {
    //Mostrar resultado
      mostrarResultado( isAllVowel(str) );
      MyIO.print(" ");
      mostrarResultado( isAllConsonant(str) );
      MyIO.print(" ");
      mostrarResultado( isAllInteger(str) );
      MyIO.print(" ");
      mostrarResultado( isAllDouble(str) );
      MyIO.println("");
    //Ler proxima linha
      str = MyIO.readLine();
    }
  }
}