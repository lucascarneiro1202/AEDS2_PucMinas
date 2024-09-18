import java.io.IOException;
import java.util.Scanner;

public class Main {
  public static Scanner sc = new Scanner (System.in);

  public static boolean isTautogram (String palavras [])
  {
  //Definir dados locais
    boolean resp = true;
    int length = palavras.length;
    int caractere = palavras[0].charAt(0);
    int i = 0;
  //Percorrer arranjo de palavras
    while (i < length && resp)
    {
    //Definir dados locais
      char c = palavras[i].charAt(0);
    //Testar igualdade
      resp = c == caractere;
    //Testar qual e' maior
      if (!resp && c > caractere)
        resp = c == caractere + 32;
      else if (!resp && c < caractere)
        resp = c == caractere - 32;
    //Variacao
      i++;
    }
  //Retornar
    return resp;
  }

  public static void main (String args []) throws IOException
  {
  //Definir dados 
    String str;
    boolean resp;
  //Ler linha
    str = sc.nextLine();
  //Laco de repeticao
    while ( !str.equals("*") )
    {
    //Dividir linha entre as palavras
      String palavras [] = str.split(" ");
    //Testar se e' um tautograma
      resp = isTautogram(palavras);
    //Mostrar resposta
      System.out.println( (resp) ? "Y" : "N" );
    //Ler proxima linha
      str = sc.nextLine();
    }
  }
}