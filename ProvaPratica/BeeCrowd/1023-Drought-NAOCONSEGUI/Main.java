import java.io.IOException;
import java.util.Scanner;

public class Main { 
  public static Scanner sc = new Scanner (System.in);

  public static String ordenarResidencias (Residencia residencias [])
  {
  //Definir dados locais
    String residenciasOrdenadas = "";
    int n = residencias.length;
  //Ordenar
    for (int i = 1; i < n; i++)
    {
      Residencia tmp = residencias[i];
      int j = i - 1;
      while ( j >= 0 && residencias[j].getConsumoPessoa() > tmp.getConsumoPessoa() )
      {
        residencias[j + 1] = residencias[j];
        j--;
      }
      residencias[j + 1] = tmp;
    }
  //Formar string de retorno
    residenciasOrdenadas += residencias[0].getQuantidadePessoas() + "-" + residencias[0].getConsumoPessoa();
    for (int i = 1; i < n; i++)
    {
    //Testar se a o consumo e' igual a posicao anterios
      if (residencias[i].getConsumoPessoa() == residencias[i - 1].getConsumoPessoa() && residencias[i].getConsumoPessoa() < 10)
      {
      //Definir dados locais
        System.out.println("str = " + residenciasOrdenadas);
        char charAnterior = residenciasOrdenadas.charAt( residenciasOrdenadas.length() - 3 );
        int intAnterior = charAnterior - '0';
        int intNovo = intAnterior + residencias[i].getQuantidadePessoas();
        char charNovo = (char) (intNovo + 48);
      //Trocar de posicao
        residenciasOrdenadas  = residenciasOrdenadas.replace( charAnterior, charNovo );
      }
      else if (residencias[i].getConsumoPessoa() == residencias[i - 1].getConsumoPessoa() && residencias[i].getConsumoPessoa() > 9)
      {
      //Definir dados locais
        System.out.println("str = " + residenciasOrdenadas);
        char charAnterior = residenciasOrdenadas.charAt( residenciasOrdenadas.length() - 4 );
        int intAnterior = charAnterior - '0';
        int intNovo = intAnterior + residencias[i].getQuantidadePessoas();
        char charNovo = (char) (intNovo + 48);
      //Trocar de posicao
        residenciasOrdenadas  = residenciasOrdenadas.replace( charAnterior, charNovo );        
      }
      else
      {
        residenciasOrdenadas += " " + residencias[i].getQuantidadePessoas() + "-" + residencias[i].getConsumoPessoa();
      }
    }
  //Retornar
    return residenciasOrdenadas;
  }

  public static double consumoMedio (Residencia residencias [])
  {
  //Definir dados locais
    double media = 0.0;
    int consumoTotal = 0;
    int quantidade = 0;
  //Calcular o consumo e a quantidade totais
    for (int i = 0; i < residencias.length; i++)
    {
      consumoTotal += residencias[i].getConsumoTotal();
      quantidade += residencias[i].getQuantidadePessoas();
    }
  //Calcular o consumo medio
    media = 1.0 * consumoTotal / quantidade;
  //Retornar
    return media;
  }

  public static void main(String[] args) throws IOException 
  { 
  //Definir dados
    int N;
    int cidade = 1;
  //Ler dados
    N = sc.nextInt();
  //Testar dado
    while (1 <= N & N <= 1000000 && N != 0)
    {
      // System.out.println("N = " + N + "\n");
    //Definir dados locais
      Residencia residencias [] = new Residencia[N];
      for (int i = 0; i < N; i++)
        residencias[i] = new Residencia();
      int X, Y;
    //Ler dados
      X = sc.nextInt();
      Y = sc.nextInt();
    //Laco de repeticao
      int i = 0;
      while ( i < N && 0 <= X && X <= 10 && 1 <= Y && Y <= 200 )
      {
      //Atribuir dados
        residencias[i].setQuantidadePessoas(X);
        residencias[i].setConsumoTotal(Y);
        residencias[i].setConsumoPessoa();
        // System.out.println(residencias[i].getQuantidadePessoas() + " " + residencias[i].getConsumoTotal());
        // System.out.println("consumo por pessoa = " + residencias[i].getConsumoPessoa());
      //Testar se ainda existem dados a serem lidos
        // System.out.println("Antes: X = " + X + " | Y = " + Y);  
        if (i < N - 1)
        {
          X = sc.nextInt();
          Y = sc.nextInt();  
        } 
        // System.out.println("Depois: X = " + X + " | Y = " + Y);   
      //Variacao
        i++; 
      }
    //Ordenar arranjo de residencias
      String residenciasOrdenadas = ordenarResidencias(residencias);
    //Calcular o consumo medio
      double consumoMedio = consumoMedio(residencias);
    //Mostrar resultado
      System.out.println("Cidade# " + cidade + ": ");
      System.out.println(residenciasOrdenadas);
      System.out.println("Consumo medio: " + String.format( "%.2f", consumoMedio ) + " m3.");
    //Printar linha em branco
      if (cidade < N)
        System.out.println("");
    //Variacao
      cidade++;    
    //Ler dados
      N = sc.nextInt();
    }
  } 
}

class Residencia {
  private int quantidadePessoas;
  private int consumoTotal;
  private int consumoPessoa;

  public Residencia ()
  {
    setQuantidadePessoas (0);
    setConsumoTotal (0);
    setConsumoPessoa ();
  }

  public Residencia (int quantidadePessoas, int consumoTotal, int consumoPessoa)
  {
    this.setQuantidadePessoas ( quantidadePessoas );
    this.setConsumoTotal ( consumoTotal );
    this.setConsumoPessoa ();
  }

  public int getQuantidadePessoas ()
  {
    return this.quantidadePessoas;
  }

  public int getConsumoTotal ()
  {
    return this.consumoTotal;
  }

  public int getConsumoPessoa ()
  {
    return this.consumoPessoa;
  }

  public void setQuantidadePessoas (int quantidadePessoas)
  {
    this.quantidadePessoas = quantidadePessoas;
  }

  public void setConsumoTotal (int consumoTotal)
  {
    this.consumoTotal = consumoTotal;
  }

  public void setConsumoPessoa ()
  {
    if (getQuantidadePessoas() > 0)
      this.consumoPessoa = getConsumoTotal() / getQuantidadePessoas();
  }
}