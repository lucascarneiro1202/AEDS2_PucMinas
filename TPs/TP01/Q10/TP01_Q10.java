public class TP01_Q10 {
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
   * isPalindromoRecursive - Funcao recursiva para testar se uma string e' um palindromo
   * @param str - String a ser testada
   * @return resp - Resultado do teste (true, se for palindromo. false, se contrario)
   */
  public static boolean isPalindromoResursive (String str, int i)
  {
  //Definir dados locais
    boolean resp = true;
    int length = str.length();
    int metade = length / 2;
  //Testar
    if (i < metade)
      resp = str.charAt(i) == str.charAt(length - i - 1) && isPalindromoResursive(str, ++i);
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
    while ( ! equalsRecursive(str, "FIM", 0) )
    {
    //Chamar funcao
      resp = isPalindromoResursive(str, 0);
    //Mostrar resultado
      if (resp)
        System.out.println("SIM");
      else
        System.out.println("NAO");
    //Ler proxima string
      str = MyIO.readLine();
    }
  }
}