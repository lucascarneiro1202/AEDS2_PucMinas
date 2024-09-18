import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;

class TP01_Q07 {
  /**
   * equals - Funcao para testar se uma string e' igual a outra
   * @param s - String a ser comparada
   * @param t - String a ser comparada
   * @return resp - True, se forem iguais. False, se contrario
   */
  public static boolean equals (String s, String t)
  {
  //Definir dados locais
    boolean resp;
    int sLength = s.length();
    int tLength = t.length();
    int i = 0;
  //Testar se sao do mesmo tamanho
    resp = sLength == tLength;
  //Condicao
    while (resp && i < sLength)
    {
      resp = s.charAt(i) == t.charAt(i);
      i++;
    }
  //Retornar
    return resp;
  }

  /**
   * isConsonant - Funcao para testar se determinado caractere e' uma consoante
   * @param c - Caractere a ser testado
   * @return b - Valor booleano resultante
   */
  public static boolean isConsonant (char c)
  {
    return ( 'a' <= c && c <= 'z' ) && ( c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u' );
  }

  /**
   * getHTML - Funcao para extrair o HTML de uma pagina web
   * @param strURL - Url da pagina web a ser consultada
   * @return response - Conteudo HTML da pagina web
   */
  public static String getHTML (String strURL)
  {  
  //Definir dados
    String response = "";
  //Tentar fazer a chamada
    try
    {
    //Definir dados locais
      URI uri = new URI(strURL);
      URL url = uri.toURL();
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      int responseCode = connection.getResponseCode();
      //Testar se a chamada foi sucedida
      if (responseCode == HttpURLConnection.HTTP_OK)
      {
        byte [] responseBytes = connection.getInputStream().readAllBytes();
        response = new String (responseBytes);
      }
    }
    catch (URISyntaxException e)
    {
      MyIO.println("Erro. Message: " + e.getMessage());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  //Retornar
    return response;
  }

  /**
   * calcularResultado - Funcao para testar a ocorrencia dos casos indicados no enunciado
   * @param strHTML - Conteudo HTML a ser testado
   * @return a - Arranjo contendo o resultado de cada teste
   */
  public static int [] calcularResultado (String strHTML)
  {
  //Definir dados locais
    int a [] = new int [25];
  //Passar por cada elemento
    for (int i = 0; i < strHTML.length(); i++)
    {
    //Definir dados
      char c = strHTML.charAt(i);
    //Testar  
      if ( isConsonant(c) )
      {
        a[22] += 1;
      }
      else if ( c == '<' )
      {
        String strTeste = "";
        int j = 0;
        for (j = 0; j < 7; j++)
        {   
          strTeste += strHTML.charAt(i + j);  
        }
        if ( equals(strTeste, "<table>") )
        {
          a[24] += 1;
          i = i + j;
        }
        else
        {
          String s = "";
          s += strTeste.charAt(0); s += strTeste.charAt(1); s += strTeste.charAt(2); s += strTeste.charAt(3);
          if ( equals( s, "<br>" ) )
          {
            i = i + 4;
            a[23] += 1;
          }
        }          
      }
      else
      {
        switch (c)
        {
          case 'a': a[0] += 1; break;
          case 'e': a[1] += 1; break;
          case 'i': a[2] += 1; break;
          case 'o': a[3] += 1; break;
          case 'u': a[4] += 1; break;
          case (char) 225: a[5] += 1; break;
          case (char) 233: a[6] += 1; break;
          case (char) 237: a[7] += 1; break;
          case (char) 243: a[8] += 1; break;
          case (char) 250: a[9] += 1; break;
          case (char) 224: a[10] += 1; break;
          case (char) 232: a[11] += 1; break;
          case (char) 236: a[12] += 1; break;
          case (char) 242: a[13] += 1; break;
          case (char) 249: a[14] += 1; break;
          case (char) 227: a[15] += 1; break;
          case (char) 245: a[16] += 1; break;
          case (char) 226: a[17] += 1; break;
          case (char) 234: a[18] += 1; break;
          case (char) 238: a[19] += 1; break;
          case (char) 244: a[20] += 1; break;
          case (char) 251: a[21] += 1; break;
        }    
      }
    }
  //Retornar
    return a;
  }

  /**
   * mostrarResultado - Procedimento para mostrar o resultado de cada caractere na saida padrao
   * @param a - Arranjo contendo o resultado de cada teste
   * @param nome - Nome do site testado
   */
  public static void mostrarResultado (int a [], String nome)
  {
  //Mostrar o resultado
    MyIO.print("a("+a[0]+") e("+a[1]+") i("+a[2]+") o("+a[3]+") u("+a[4]+") ");
    MyIO.print("\u00E1("+a[5]+") \u00E9("+a[6]+") \u00ED("+a[7]+") \u00F3("+a[8]+") \u00FA("+a[9]+") ");
    MyIO.print("\u00E0("+a[10]+") \u00E8("+a[11]+") \u00EC("+a[12]+") \u00F2("+a[13]+") \u00F9("+a[14]+") ");
    MyIO.print("\u00E3("+a[15]+") \u00F5("+a[16]+") \u00E2("+a[17]+") \u00EA("+a[18]+") \u00EE("+a[19]+") ");
    MyIO.print("\u00F4("+a[20]+") \u00FB("+a[21]+") consoante("+a[22]+") <br>("+a[23]+") <table>("+a[24]+") ");
    MyIO.print(nome + '\n');
  }

  public static void main (String args [])
  {  
  //Definir dados
    MyIO.setCharset("UTF-8");    
    String nome, strUrl;
    boolean controle;
  //Ler do teclado
    nome = MyIO.readLine();
    controle = ! equals(nome, "FIM");
  //Testar 
    if ( controle )
    {
    //Ler proxima linha
      strUrl = MyIO.readLine();
      controle = ! equals(nome, "FIM");
    //Condicao
      while ( controle )
      {
      //Ler pagina HTML
        String strHTML = getHTML(strUrl);
      //Testar os valores da pagina
        int a [] = calcularResultado(strHTML);
      //Mostrar resultado
        mostrarResultado(a, nome);
      //Ler novas entradas
        nome = MyIO.readLine();
        controle = ! equals(nome, "FIM");
        if (controle)
        {
          strUrl = MyIO.readLine();
          controle = ! equals(strUrl, "FIM");
        }
      }
    }
  }
}