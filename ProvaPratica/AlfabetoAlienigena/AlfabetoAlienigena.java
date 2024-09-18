class AlfabetoAlienigena {
  public static boolean testarMensagem (String alfabeto, String mensagem)
  {
  //Definir dados locais
    boolean resp = true;
    int i = 0, j = 0;
  //Testar cada caractere da mensagem
    while (i < mensagem.length() && resp)
    {
    //Definir dados locais
      char c = mensagem.charAt(i);
      j = 0;
    //Testar se o caractere e' valido
      while (j < alfabeto.length() && resp)
      {
      //Testar
        resp = c != alfabeto.charAt(j);
      //Variacao  
        j++;
      }
    //Inverter variavel de controle
      resp = !resp;
    //Variacao
      i++;
    }
  //Retornar
    return resp;
  }

  public static void main (String args [])
  {
  //Definir dados locais
    int K, N;
  //Ler dados
    K = MyIO.readInt();
    N = MyIO.readInt();
  //Testar entradas
    if ( 1 <= K && K <= 68 && 1 <= N && N <= 1000 )
    {
    //Definir dados locais
      String alfabeto, mensagem;
      boolean resp;
    //Ler alfabeto alienigena
      alfabeto = MyIO.readString();
    //Ler mensagem a ser analisada
      mensagem = MyIO.readString();
    //Testar mensagem
      resp = testarMensagem(alfabeto, mensagem);
    //Mostrar resultado
      MyIO.println( (resp) ? 'S' : 'N' );
    }
  }
}