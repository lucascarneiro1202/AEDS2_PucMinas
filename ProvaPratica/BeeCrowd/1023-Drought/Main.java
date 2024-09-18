import java.io.IOException;
import java.util.Scanner;

public class Main { 
  public static Scanner sc = new Scanner (System.in);

  public static void main(String[] args) throws IOException { 

  } 
}

class Residencia {
  private int quantidadePessoas;
  private int consumoTotal;
  private int consumoPessoa;

  public Residencia ()
  {
    setQuantidadePessoas (0);
    consumoTotal (0);
    consumoPessoa (0);
  }

  public Residencia (int quantidadePessoas, int consumoTotal, int consumoPessoa)
  {
    setQuantidadePessoas ( quantidadePessoas );
    consumoTotal ( consumoTotal );
    consumoPessoa ( consumoPessoa );
  }
}