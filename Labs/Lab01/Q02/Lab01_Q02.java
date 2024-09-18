class Lab01_Q02 {
  public static int upperCaseRecursive (String s, int i)
  {
  //definir dados locais
    int qtd = 0;
  //testar a posicao
    if (i < s.length())
    {
    //testar se e' letra maiuscula
      if ('A' <= s.charAt(i) && s.charAt(i) <= 'Z')
        qtd++;
      i++;
      qtd = qtd + upperCaseRecursive(s, i);
    } 
  //retornar
    return qtd;
  } //end upperCaseRecursive ()

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
      qtd = upperCaseRecursive(str, 0);
    //mostrar quantidade
      MyIO.println(qtd);
    //ler nova string
      str = MyIO.readLine();
    }
  }
}