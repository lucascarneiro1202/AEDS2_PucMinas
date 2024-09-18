public class TP01_Q01 {
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
   * isPalindromo - Funcao para testar se uma string e' um palindromo
   * @param str - String a ser testada
   * @return resp - Resultado do teste (true, se for palindromo. false, se contrario)
   */
  public static boolean isPalindromo (String str)
  {
  //Definir dados locais
    boolean resp = true;
    int length = str.length();
    int metade = length / 2;
    int i = 0;
  //Testar
    while (i < metade && resp)
    {
      resp = str.charAt(i) == str.charAt(length - i - 1);
      i++;
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
      resp = isPalindromo(str);
    //Mostrar resultado
      if (resp)
        MyIO.println("SIM");
      else
        MyIO.println("NAO");
    //Ler proxima string
      str = MyIO.readLine();
    }
  }
}