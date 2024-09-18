class Lab01_Q01 {
  public static int upperCase (String s)
  {
  //definir dados locais
    int qtd = 0;
    int length = s.length();
  //passar por cada caractere
    for (int i = 0; i < length; i++)
    {
    //definir dados locais
      char c = s.charAt(i);
    //testar se e' maiucula
      if ('A' <= c && c <= 'Z')
        qtd++;
    }
  //retornar
    return qtd;
  } //end upperCase ()
  
  public static void main (String args [])
  {
  //definir dados locais
    int qtd;
    String str;
  //ler do teclado
    str = MyIO.readLine();
  //condicao
    while (!( str.equals("FIM") ))
    {
    //chamar funcao
      qtd = upperCase(str);
    //mostrar quantidade
      MyIO.println(qtd);
    //ler nova string
      str = MyIO.readLine();
    }
  }
}