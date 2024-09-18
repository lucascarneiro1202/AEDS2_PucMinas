#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>

bool testarMensagem (char * alfabeto, int K, char * mensagem, int N)
{
//Definir dados locais
  bool resp = true;
  int i = 0, j = 0;
//Testar cada caractere da mensagem
  while (i < N && resp)
  {
  //Definir dados locais
    char c = mensagem[i];
    j = 0;
  //Testar se o caractere e' valido
    while (j < K && resp)
    {
    //Testar
      resp = c != alfabeto[j];
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

int main ()
{
//Definir dados locais
  int K = 0, N = 0;
//Ler dados
  scanf("%d", &K); getchar();
  scanf("%d", &N); getchar(); 
//Testar entradas
  if ( 1 <= K && K <= 68 && 1 <= N && N <= 1000 )
  {
  //Definir dados locais
    char * alfabeto = calloc(K + 1, sizeof(char));
    char * mensagem = calloc(N + 1, sizeof(char));
    bool resp;
  //Testar alocacao de memoria
    if (alfabeto && mensagem)
    {
    //Ler alfabeto alienigena
      fgets(alfabeto, K + 2, stdin);
    //Ler mensagem a ser analisada
      fgets(mensagem, N + 2, stdin);
    //Testar leitura
      if (alfabeto && mensagem)
      {
      //Testar mensagem
        resp = testarMensagem(alfabeto, K, mensagem, N);
      //Mostrar resultado
        printf("%c\n", (resp) ? 'S' : 'N');
      }
    }
  }
//Retornar
  return 0;
}