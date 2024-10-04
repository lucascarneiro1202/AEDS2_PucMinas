import java.util.Date;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Quicksort { 
/* ---------------------------- ATRIBUTOS PRIVADOS ---------------------------- */

  private static int n;
  private static int comparacoes;
  private static int movimentacoes;

/* ---------------------------- METODOS AUXILIARES ---------------------------- */

  public static String printLine ()
  {    return n + ", " + comparacoes + ", " + movimentacoes;    }

  public static void print (int array [])
  {
    for (int i = 0; i < array.length; i++)
      MyIO.println(i + ": " + array[i]);
  }

  public static Random random = new Random();

  public static int gerarAleatorio ()
  {
    return Math.abs(random.nextInt());
  }

  public static void swap (int array [], int x, int y)
  {
    int temp = array[x];
    array[x] = array[y];
    array[y] = temp;
  }

  public static int median (int array[], int esq, int dir)
  {
  //Definir dados locais
    int mid;
    int median = 0;
  //Testar tamanho
    if (array.length == 1)
      mid = array[0];
    else if (array.length == 2)
      mid = (array[0] + array[1]) / 2;
    else
    {
    //Definir dados
      mid = esq + (dir - esq) / 2;
      int a = array[esq], b = array[mid], c = array[dir];
    //Calcular a posicao do meio entre tres numeros
      if ( (b < a && a < c) || (c < a && a < b) )
        median = esq;
      else if ( (a < b && b < c) || (c < b && b < a) )
        median = mid;
      else
        median = dir;
    }
  //Retornar
    return median;
  }

/* ---------------------------- QUICKSORT COM PIVO NO INICIO ---------------------------- */

  public static void quicksortFirstPivot (int array [], int esq, int dir) 
  {
    int i = esq, j = dir;
    int pivo = array[esq];
    while (i <= j)
    {
      while (array[i] < pivo) {  i++; comparacoes++;  }
      while (array[j] > pivo) {  j--; comparacoes++;  }
      if (i <= j) 
      {
        swap(array, i, j);
	      movimentacoes++;
        i++;
        j--;
      }
    }
      if (esq < j)  quicksortFirstPivot (array, esq, j);
      if (i < dir)  quicksortFirstPivot (array, i, dir);
  }

/* ---------------------------- QUICKSORT COM PIVO NO FINAL ---------------------------- */

  public static void quicksortLastPivot (int array [], int esq, int dir) 
  {
    int i = esq, j = dir;
    int pivo = array[dir];
    while (i <= j)
    {
      while (array[i] < pivo) {  i++; comparacoes++;  }
      while (array[j] > pivo) {  j--; comparacoes++;  }
      if (i <= j) 
      {
        swap(array, i, j);
	      movimentacoes++;
        i++;
        j--;
      }
    }
      if (esq < j)  quicksortLastPivot (array, esq, j);
      if (i < dir)  quicksortLastPivot (array, i, dir);
  }

/* ---------------------------- QUICKSORT COM PIVO ALEATORIO ---------------------------- */

  public static int contador = 0;

  public static void quicksortRandomPivot (int array [], int esq, int dir) 
  {
    int i = esq, j = dir;
    int pivo = array[esq + gerarAleatorio() % (dir - esq + 1)];
    while (i <= j)
    {
      while (array[i] < pivo) {  i++; comparacoes++;  }
      while (array[j] > pivo) {  j--; comparacoes++;  }
      if (i <= j) 
      {
        swap(array, i, j);
	      movimentacoes++;
        i++;
        j--;
      }
    }
      if (esq < j)  quicksortRandomPivot (array, esq, j);
      if (i < dir)  quicksortRandomPivot (array, i, dir);
  }

/* ---------------------------- QUICKSORT COM PIVO MEDIANA DE TRES VALORES ---------------------------- */

  public static void quicksortMedianOfThree (int array [], int esq, int dir) 
  {
    int i = esq, j = dir;
    int pivo = array[ median(array, esq, dir) ];
    while (i <= j)
    {
      while (array[i] < pivo) {  i++; comparacoes++;  }
      while (array[j] > pivo) {  j--; comparacoes++;  }
      if (i <= j) 
      {
        swap(array, i, j);
	      movimentacoes++;
        i++;
        j--;
      }
    }
      if (esq < j)  quicksortMedianOfThree (array, esq, j);
      if (i < dir)  quicksortMedianOfThree (array, i, dir);
  }

/* ---------------------------- METODOS PARA GERAR ARRANJOS ---------------------------- */
 
  public static int [] arrayCrescente (int n)
  {
  //Definir dados locais
    int array [] = new int[n];
  //Preencher array
    for (int i = 0; i < n; i++)
      array[i] = i + 1;
  //Retornar
    return array;
  }

  public static int [] arrayDecrescente (int n)
  {
  //Definir dados locais
    int array [] = new int[n];
  //Preencher array
    for (int i = 0; i < n; i++)
      array[i] = n - i;
  //Retornar
    return array;
  }

  public static int [] arrayQuaseOrdenado (int n, int heap)
  {
  //Definir dados locais
    int array [] = arrayCrescente(n);
  //Bagunçar arranjo
    for (int i = 0; i < heap; i += 2)
      swap ( array, i * heap, (i + 1) * heap );
  //Retornar
    return array;
  }

  public static int [] arrayRandom (int n)
  {
  //Definir dados locais
    int array [] = new int[n];
  //Preencher arranjo com valores aleatorios
    for (int i = 0; i < n; i++)
      array[i] = gerarAleatorio() % n;
  //Retornar
    return array;
  }

/* ---------------------------- METODO PRINCIPAL ---------------------------- */

  public static void main (String args [])
  {
  //Definir arranjos
    int rows = 30;
    int a100 [][] = new int[rows][100];
    int a1000 [][] = new int[rows][1000];
    int a10000 [][] = new int[rows][10000];
  //Definir strings a serem adicionadas no arquivo
    StringBuilder str100 = new StringBuilder();
    StringBuilder str1000 = new StringBuilder();
    StringBuilder str10000 = new StringBuilder();
    StringBuilder strMedias = new StringBuilder();
  //Definir dados de media
    int c100 = 0, m100 = 0;
    int c1000 = 0, m1000 = 0;
    int c10000 = 0, m10000 = 0;
  //Manipular dados
    int h = 4;
    for (int i = 0; i < rows; i++)
    {
    //Array de tamanho 100
      comparacoes = movimentacoes = 0;
      a100[i] = arrayRandom(100);
      quicksortRandomPivot(a100[i], 0, a100[i].length - 1);
      str100.append(comparacoes + ", " + movimentacoes + "\n");
      c100 += comparacoes; m100 += movimentacoes;
    //Array de tamanho 1000
      comparacoes = movimentacoes = 0;
      a1000[i] = arrayRandom(1000);
      quicksortRandomPivot(a1000[i], 0, a1000[i].length - 1);
      str1000.append(comparacoes + ", " + movimentacoes + "\n");
      c1000 += comparacoes; m1000 += movimentacoes;
    //Array de tamanho 10000
      comparacoes = movimentacoes = 0;
      a10000[i] = arrayRandom(10000);
      quicksortRandomPivot(a10000[i], 0, a10000[i].length - 1);
      str10000.append(comparacoes + ", " + movimentacoes + "\n");
      c10000 += comparacoes; m10000 += movimentacoes;
    }
  //Calcular medias
    int mediaC100 = c100 / rows;
    int mediaM100 = m100 / rows;
    int mediaC1000 = c1000 / rows;
    int mediaM1000 = m1000 / rows;
    int mediaC10000 = c10000 / rows;
    int mediaM10000 = m10000 / rows;
    strMedias.append(mediaC100 + ", " + mediaM100 + "\n");
    strMedias.append(mediaC1000 + ", " + mediaM1000 + "\n");
    strMedias.append(mediaC10000 + ", " + mediaM10000 + "\n");
  //Definir caminhos dos arquivos
    String path100 = "/home/lucas/Desktop/PucMinas/2024-2/AEDS2/Labs/Lab06/RandomPivot/Random/random-random-100.csv";
    String path1000 = "/home/lucas/Desktop/PucMinas/2024-2/AEDS2/Labs/Lab06/RandomPivot/Random/random-random-1000.csv";
    String path10000 = "/home/lucas/Desktop/PucMinas/2024-2/AEDS2/Labs/Lab06/RandomPivot/Random/random-random-10000.csv";
    String pathMedia = "/home/lucas/Desktop/PucMinas/2024-2/AEDS2/Labs/Lab06/RandomPivot/Random/random-random-medias.csv";
  //Tentar abrir arquivos
    BufferedWriter file100 = null;
    BufferedWriter file1000 = null;
    BufferedWriter file10000 = null;
    BufferedWriter medias = null;
    try
    {
    //Abrir arquivos
      file100 = Files.newBufferedWriter( Paths.get(path100) );
      file1000 = Files.newBufferedWriter( Paths.get(path1000) );
      file10000 = Files.newBufferedWriter( Paths.get(path10000) );
      medias = Files.newBufferedWriter( Paths.get(pathMedia) );
    //Preencher arquivos
      file100.write(str100.toString());
      file1000.write(str1000.toString());
      file10000.write(str10000.toString());
      medias.write(strMedias.toString());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    finally
    {
      if (file100 != null)
      {
        try
          {   file100.close();    }
        catch (IOException e)
          {   e.printStackTrace();    }
      }
      if (file1000 != null)
      {
        try
          {   file1000.close();    }
        catch (IOException e)
          {   e.printStackTrace();    }
      }
      if (file10000 != null)
      {
        try
          {   file10000.close();    }
        catch (IOException e)
          {   e.printStackTrace();    }
      }
      if (medias != null)
      {
        try
          {   medias.close();    }
        catch (IOException e)
          {   e.printStackTrace();    }
      }
    }
  }
}