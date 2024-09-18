#include <stdio.h>
#include <string.h>
#include <stdbool.h>

bool equals (char s [], char f [])
{
//Definir dados locais
  bool resposta = strlen(s) - 1 == strlen(f);
  int i = 0;
//Condicao
  while ( resposta && s[i] != '\0' && f[i] != '\0')
  {
  //Testar igualdade
    resposta = s[i] == f[i];
  //Variacao
    i++;
  }
//retornar
  return resposta;
} //end equals ()String

int upperCase (char s [])
{
//definir dados locais
  int qtd = 0;
  int length = strlen(s);
  int i = 0;
//passar por cada caractere
  while ( i < length )
  {
  //testar se e' maiuscula
    if ('A' <= s[i] && s[i] <= 'Z')
      qtd++;
  //variacao
    i++;
  }
//retornar
  return ( qtd );
}

int main ()
{
//Definir dados locais
  int qtd = 0;;
  char str[100];
//Ler do teclado
  fgets(str, sizeof(str), stdin);
//Testar se leitura foi valida
  bool teste = str != NULL;
//Condicao
  while ( teste && !equals(str, "FIM") )
  {
  //Chamar funcao para contar a quantidade de maiusculas
    qtd = upperCase(str);
  //Mostrar a quantidade
    printf("%d\n", qtd);
  //Ler proxima string
    fgets(str, sizeof(str), stdin);
  //Testar se leitura foi valida
    teste = str != NULL;
  }
//Retornar
  return 0;
}