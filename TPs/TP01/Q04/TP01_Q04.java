import java.util.Random;

class TP01_Q04 {
  /**
   * @varialble gerador - Variavel estatica para gerar numero aleatorio
   */
  public static Random gerador = new Random();

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
   * gerarAleatorio - Funcao para gerar uma letra minuscula aleatoria
   * @return c - Letra minuscula aleatoria gerada
   */
  public static char gerarAleatorio ()
  {
  //Definir dados locais
    char c;
  //Gerar letra minuscula aleatoria
    c = (char) ( 'a' + Math.abs(gerador.nextInt() % 26) );
  //Retornar
    return c;
  }

  /**
   * substituir - Funcao para substituir as ocorrencias de um caractere por outro
   * @param c1 - Caractere a ser trocado
   * @param c2 - Caractere a ser inserido
   * @return s - String resultante da troca de caracteres
   */
  public static String substituir (String str, char c1, char c2)
  {
  //Definir dados locais
    int length = str.length();
    String s = "";
  //Passar por cada elemento
    for (int i = 0; i < length; i++)
    {
      if ( str.charAt(i) == c1 )
        s += c2;
      else
        s += str.charAt(i);
    }
  //Retornar
    return s;
  }

  public static void main (String args [])
  {
  //Definir dados
    MyIO.setCharset("UTF-8");
    gerador.setSeed(4);
    String str, s;
    char c1, c2;
  //Ler do teclado
    str = MyIO.readLine();
  //Condicao
    while ( ! equals(str, "FIM") )
    {
    //Gerar aleatorios
      c1 = gerarAleatorio();     
      c2 = gerarAleatorio(); 
    //Substituir
      s = substituir(str, c1, c2);
    //Mostrar resultado
      MyIO.println(s);  
    //Ler nova linha
      str = MyIO.readLine();
    }
  }
}