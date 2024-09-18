class Lab02_Q01 {
  public static void main (String args [])
  {
  //definir scanner
    Scanner sc = new Scanner(System.in);
  //definir dados locais
    String s1, s2;
    int i = 0;
  //ler dados
    while (sc.hasNext())
    {
    //atribuir dados
      s1 = sc.next();
      s2 = sc.next(); 
    //trocar de posicao
      i = 0;
      String s3 = "";
      while ( i < s1.length() || i < s2.length() )
      {
      //testar os caracteres
        if ( i < s1.length() )
          s3 += s1.charAt(i);
        if ( i < s2.length() )
          s3 += s2.charAt(i);
      //variar a posicao
        i++;
      }
    //mostrar resultado
      MyIO.println(s3);
    }
  }
}