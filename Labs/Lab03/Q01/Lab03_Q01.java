class Lab03_Q01 {
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
  } // end equals ( )

  public static boolean isParenthesesCorrect (String str)
  {
  //Definir dados locais
    boolean resp = true;
    int length = str.length();
    int controleAberto = 0;
    int controleFechado = 0;
    int i = 0;
  //Passar por cada elemento
    while (i < length && resp)
    {
    //Definir dados locais
      char c = str.charAt(i);
    //Testar
      if (c == '(')
      {
        controleAberto++;
      }
      else if (c == ')' && controleAberto > 0 && controleAberto != controleFechado)
      {
        controleFechado++;
      }
      else if (c == ')' && controleAberto == controleFechado)
      {
        resp = false;
      }
    //Variacao
      i++;
    }
    if (resp)
    {
      resp = controleAberto == controleFechado;
    }
  //Retornar
    return resp;
  }

  public static void main (String args [])
  { 
  //Definir dados locais
    MyIO.setCharset("UTF-8");
    String str;
    boolean resp;
  //Ler do teclado
    str = MyIO.readLine();
  //Condicao
    while ( ! equals(str, "FIM") )
    {
    //Chamar funcao
      resp = isParenthesesCorrect(str);
    //Mostrar resultado
      if (resp)
        MyIO.println("correto");
      else
        MyIO.println("incorreto");
    //Ler proxima string
      str = MyIO.readLine();
    }
  }
}