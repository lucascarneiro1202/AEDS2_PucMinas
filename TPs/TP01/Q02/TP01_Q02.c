#include <stdio.h>
#include <stdbool.h>
#include <wchar.h>
#include <locale.h>

#define MAX_TAM 360

/*
  equals - Funcao para testar se uma string e' igual a outra
  @param s - String a ser comparada
  @param t - String a ser comparada
  @return resp - True, se forem iguais. False, se contrario
*/
bool equals (wchar_t s [], wchar_t f [])
{
//Definir dados locais
  bool resposta = wcslen(s) == wcslen(f);
  int i = 0;
//Condicao
  while ( resposta && s[i] != L'\0' && f[i] != L'\0')
  {
  //Testar igualdade
    resposta = s[i] == f[i];
  //Variacao
    i++;
  }
//retornar
  return resposta;
}

/*
  isPalindromo - Funcao para testar se uma string e' um palindromo
  @param str - String a ser testada
  @return resp - Resultado do teste (true, se for palindromo. false, se contrario)
*/
bool isPalindromo (wchar_t str [])
{
//Definir dados locais
  bool resp = true;
  size_t length = wcslen(str);
  int metade = (length - 1) / 2;
  int i = 0;
//Testar
  while (i < metade && resp)
  {
    resp = str[i] == str[length - i - 2];
    i++;
  }
//Retornar
  return resp;
}

int main ()
{
//Definir dados locais
  setlocale(LC_CTYPE, "");
  wchar_t str[MAX_TAM];
  bool resp;
  bool teste;
//Ler dados
  fgetws(str, MAX_TAM, stdin);
//Testar se leitura e' valida
  teste = str != NULL;
//Condicao
  while (teste && !equals(str, L"FIM\n"))
  {
  //Chamar funcao para testar se e' palindromo
    resp = isPalindromo(str);
  //Mostrar resultado
    if (resp)
      printf("%s\n", "SIM");
    else
      printf("%s\n", "NAO");
  //Ler proxima string
    fgetws(str, MAX_TAM, stdin);
  //Testar leitura
    teste = str != NULL;
  }
//Retornar
  return 0;
}