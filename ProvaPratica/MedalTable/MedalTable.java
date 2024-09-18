import java.util.Scanner;
import java.io.IOException;

class MedalTable {
  public static Scanner sc = new Scanner (System.in);

  public static void swap (String paises [], int ouro [], int prata [], int bronze [], int i, int menor)
  {
  //Trocar o nome de posicao
    String temp1 = paises[i];
    paises[i] = paises[menor];
    paises[menor] = temp1;
  //Trocar o ouro de posicao
    int temp2 = ouro[i];
    ouro[i] = ouro[menor];
    ouro[menor] = temp2;
  //Trocar o prata de posicao
    int temp3 = prata[i];
    prata[i] = prata[menor];
    prata[menor] = temp3;
  //Trocar o bronze de posicao
    int temp4 = bronze[i];
    bronze[i] = bronze[menor];
    bronze[menor] = temp4;
  }

  public static void ordenarTabela (String paises [], int ouro [], int prata [], int bronze [])
  {
  //Definir dados locais
    int n = paises.length;
  //Ordenar (Selecao)
    for (int i = 0; i < n - 1; i++)
    {
      int menor = i;
      for (int j = i + 1; j < n; j++)
      {
        if (ouro[menor] < ouro[j])
          menor = j;
        else if (ouro[menor] == ouro[j])
        {
          if (prata[menor] < prata[j])
            menor = j;
          else if (prata[menor] == prata[j])
          {
            if (bronze[menor] < bronze[j])
              menor = j;     
            else if (bronze[menor] == bronze[j])
            {
              if (paises[menor].charAt(0) > paises[j].charAt(0))
                menor = j;
            }       
          }
        }
      }
      swap(paises, ouro, prata, bronze, i, menor);
    }
  }

  public static void main (String args []) throws IOException
  {
  //Definir dados
    int n;
    boolean controle;
  //Ler dados
    n = sc.nextInt();
  //Testar entrada de dados
    if (0 <= n && n <= 500)
    {
    //Definir dados locais
      String paises [] = new String[n];
      int ouro [] = new int[n];
      int prata [] = new int[n];
      int bronze [] = new int[n];
      int i = 0;
    //Fazer a leitura dos dados
      do
      {
      //Ler dados
        paises[i] = sc.next();
        ouro[i] = sc.nextInt();
        prata[i] = sc.nextInt();
        bronze[i] = sc.nextInt();
      //Testar dados
        controle = 0 <= ouro[i] && ouro[i] <= 10000 && 0 <= prata[i] && prata[i] <= 10000 && 0 <= bronze[i] && bronze[i] <= 10000;
      //Variacao
        i++;
      } while (i < n && controle);
    //Testar se os dados foram lidos corretamente
      if (controle)
      {
      //Ordenar os arranjos
        ordenarTabela (paises, ouro, prata, bronze);
      //Mostrar resultado
        for (i = 0; i < n; i++)
          System.out.println(paises[i] + " " + ouro[i] + " " + prata[i] + " " + bronze[i];
      }
    }
  }
}