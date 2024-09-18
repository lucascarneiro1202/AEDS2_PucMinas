class TP01_Q12 {
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
   * ciframentoRecursivo - Funcao recursiva para cifrar uma string a partir do Ciframento de Cesar com chave 3
   * @param str - String a ser cifrada
   * @return cpt - String cifrada com chave 3
   */
  public static String ciframentoRecursivo (String str, int i)
  {
  //Definir dados locais
    StringBuilder cpt = new StringBuilder();
    int chave = 3;
    int length = str.length();
  //Condicao de parada
    if (i < length)
    {
    //Definir dados iterativos
      char c = str.charAt(i);
      int intC = (int) c;
    //Testar caracteres
      if ( intC + chave < 65535  )
        cpt.append( (char) ( intC + chave ) );
      else
        cpt.append(c);
    //Motor da recursividade
      cpt.append( ciframentoRecursivo(str, ++i) );
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
    while ( ! equalsRecursive(str, "FIM", 0) )
    {
    //Chamar funcao do ciframento de cesar
      cpt = ciframentoRecursivo(str, 0);
    //Mostrar resultado
      System.out.println(cpt);
    //Ler proxima linha
      str = MyIO.readLine();
    }
  }
}