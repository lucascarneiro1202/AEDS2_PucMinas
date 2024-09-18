import java.io.RandomAccessFile;
import java.io.IOException;

class TP01_Q08 {
  public static void main (String args [])
  {
    try
    {
    //Definir dados locais
      int n;
      double d;
      RandomAccessFile raf = new RandomAccessFile ("Q08.txt", "rw");
    //Ler dados
      n = MyIO.readInt();
    //Escrever os numeros reais
      for (int i = 0; i < n; i++)
      {
        d = MyIO.readDouble();
        raf.writeDouble(d);
      }
    //Fechar arquivo
      raf.close();
    //Reabrir arquivo
      raf = new RandomAccessFile ("Q08.txt", "r");
      raf.seek( n * 8 );
    //Ler os numeros reais de tras para frente
      for (int i = 1; i <= n; i++)
      {
        raf.seek( (n - i) * 8 );
        d = raf.readDouble();
        if (d % 1 == 0)
          MyIO.println( String.format("%.0f", d) );
        else
          MyIO.println(d);
      }
    //Fechar o arquivo novamente
      raf.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}