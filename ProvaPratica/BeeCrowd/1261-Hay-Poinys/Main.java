import java.io.IOException;
import java.util.Scanner;

public class Main { 
  public static Scanner sc = new Scanner (System.in);

  public static double calcularPontuacao (String text, String dicionarioNomes [], double dicionarioValores [])
  {
  //Definir dados locais
    String words [] = text.split(" ");
    double pontuacao = 0.0;
  //Percorrer arranjo de palavras
    for (int i = 0; i < words.length; i++)
    {
    //Definir dados locais
      String palavra = words[i];
    //Comparar com cada palavra do dicionario
      for (int j = 0; j < dicionarioNomes.length; j++)
      {
        if (palavra.equals(dicionarioNomes[j]))
          pontuacao += dicionarioValores[j];
      }
    }
  //Retornar
    return pontuacao;
  }

  public static void main(String[] args) throws IOException { 
  //Definir dados
    int M, N;
  //Ler dados
    M = sc.nextInt();
    N = sc.nextInt();
  //Testar dados
    if (0 <= M && M <= 1000 && 0 <= N && N <= 100)
    {
    //Definir dados locais
      String dicionarioNomes [] = new String[M];
      double dicionarioValores [] = new double[M];
    //Ler dicionario
      for (int i = 0; i < M; i++)
      {
      //Ler dados
        dicionarioNomes[i] = sc.next();
        dicionarioValores[i] = Double.parseDouble( sc.next() );
      }
    //Ler caractere sobrando
      String s = sc.nextLine();
    //Ler descricoes
      for (int i = 0; i < N; i++)
      {
      //Definir dados locais
        double pontuacao = 0.0;
        String text = "";
        String word = sc.next();
        while ( !word.equals(".") )
        {
          text = text.concat(word + " ");
          word = sc.next();
        }
      //Calcular a pontuacao
        pontuacao = calcularPontuacao(text, dicionarioNomes, dicionarioValores);
      //Mostrar resultado
        System.out.println( String.format("%.0f", pontuacao) );
      }
    }
  } 
}