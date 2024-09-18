class TP01_Q03 {
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
   * ciframento - Funcao para cifrar uma string a partir do Ciframento de Cesar com chave 3
   * @param str - String a ser cifrada
   * @return cpt - String cifrada com chave 3
   */
  public static String ciframento (String str)
  {
  //Definir dados locais
    int chave = 3;
    int length = str.length();
    StringBuilder cpt = new StringBuilder();
  //Passar por cada elemento
    for (int i = 0; i < length; i++)
    {
    //Definir dados iterativos
      char c = str.charAt(i);
      int intC = (int) c;
    //Testar caracteres
      if ( intC + chave >= 65535  )
        cpt.append(c);
      else
        cpt.append( (char) ( intC + chave ) );
    }
  //Retornar
    return cpt.toString();
  }

  public static void main (String[] args)
  {
  //Definir dados locais
    MyIO.setCharset("UTF-8");
    String str;
    String cpt;
  //Ler primeira linha
    str = MyIO.readLine();
  //Testar condicao
    while ( ! equals(str, "FIM") )
    {
    //Chamar funcao do ciframento de cesar
      cpt = ciframento(str);
    //Mostrar resultado
      MyIO.println(cpt);
    //Ler proxima linha
      str = MyIO.readLine();
    }
  }
}
