#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

#define MAX_TAM 386

bool equalsRecursive (char * s, char * f, int i)
{
//Definir dados locais
  bool resposta = strlen(s) == strlen(f);
//Condicao
  if ( resposta && s[i] != '\0' && f[i] != '\0')
    resposta = s[i] == f[i] && equalsRecursive(s, f, ++i);
//retornar
  return resposta;
} //end equalsRecursive ()

bool isPalindromoRecursive (char * str, int i)
{
//Definir dados locais
  bool resp = true;
  int length = strlen(str);
  int metade = length / 2;
//Testar
  if (i < metade && resp)
  {
  //Definir dados locais
    char c1 = str[i];
    char c2 = str[length - i - 1];
    if ( 0 <= c1 && c1 <= 127 && 0 <= c2 && c2 <= 127 )
    {
      resp = c1 == c2 && isPalindromoRecursive(str, ++i);
    }
    else
    {
      resp = isPalindromoRecursive(str, ++i);
    }
  }
//Retornar
  return resp;
}

void ler ( char * str, int i )
{
//Definir dados locais
  char c = '\0';
//Condicao de parada
  if (i < MAX_TAM && c != '\n')
  {
  //Ler caractere
    c = getchar();
  //Testar caractere
    if (c != '\n')
    {
    //Testar se esta entre 0 e 127
      if ( 0 <= c && c <= 127 )
      {
      //Atribuir o caractere
        str[i] = c;
      //Motor da recursao
        ler(str, ++i);
      }
      else
      {
      //Atribuir espaco em branco
        str[i] = (char) 32;
      //Motor da recursao
        ler(str, ++i);      
      }
    }
  }
}

int main ()
{
//Definir dados locais
  char * str = calloc(MAX_TAM, sizeof(char));
  bool resp;
  bool teste;
//Ler dados
  ler(str, 0);
//Condicao
  while (!equalsRecursive(str, "FIM", 0))
  {
  //Chamar funcao para testar se e' palindromo
    resp = isPalindromoRecursive(str, 0);
  //Mostrar resultado
    if (resp)
      printf("%s\n", "SIM");
    else
      printf("%s\n", "NAO");
  //Ler proxima string
    free(str);
    str = calloc(MAX_TAM, sizeof(char));
    ler(str, 0);
  }
//Retornar
  return 0;
}