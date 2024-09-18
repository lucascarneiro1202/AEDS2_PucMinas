class Lab02_Q02 {
  public static String inverso (int n)
  {
  //Definir dados locais
    String strInverso = "";
    int inverso = 0;
    int aux = n;
    int temp = 0;
  //Inverter numero
    while (aux > 0)
    {
      temp = aux % 10;
      inverso = inverso * 10 + temp;
      aux /= 10;
    }
  //Testar condicao especial
    aux = n;
    while (aux > 0 && aux % 10 == 0)
    {
      strInverso += "0";
      aux /= 10;
    }
    strInverso += inverso;
  //Retornar
    return strInverso;
  }

  public static String sequenciaDireta (int x, int y)
  {
  //Definir dados locais
    String s = "";
  //Passar por cada elemento 
    for (int i = x; i <= y; i++)
      s += i;
  //Retornar
    return s;
  }

  public static String sequenciaInversa (int x, int y)
  {
  //Definir dados locais
    String s = "";
  //Passar por cada elemento 
    for (int i = y; i >= x; i--)
      s += inverso(i);
  //Retornar
    return s;
  }

  public static void main (String args [])
  {
  //Definir dados
    int x, y;
    String seqDireta, seqInversa;
  //Ler dados
    x = MyIO.readInt();
    y = MyIO.readInt();
  //Condicao de repeticao
    while (x != -1 && y != -1)
    {
    //Formar sequencias
      seqDireta = sequenciaDireta(x, y);
      seqInversa = sequenciaInversa(x, y);
    //Mostrar resultado
      MyIO.println(seqDireta + seqInversa);
    //Ler dados novamente
      x = MyIO.readInt();
      y = MyIO.readInt();
    }
  }
}