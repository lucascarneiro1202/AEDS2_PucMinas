#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

void ordenarCriancas (char ** criancas, int N)
{
//Ordenar
  for (int i = 0; i < N - 1; i++)
  {
    int menor = i;
    for (int j = i + 1; j < N; j++)
    {
    //Definir dados locais
      int k = 0;
    //Procurar a posicao de desempate
      while ( k < strlen(criancas[menor]) && k < strlen(criancas[j]) && criancas[menor][k] == criancas[j][k] )
        k++;
    //Procurar o menor
      if ( criancas[menor][k] > criancas[j][k] )
        menor = j;
    }
    char * temp = criancas[i];
    criancas[i] = criancas[menor];
    criancas[menor] = temp;
  }
}

int main ()
{
//Definir dados locais
  int N;
//Ler dados
  scanf("%d", &N); getchar();
//Testar quantidade
  if (0 > N && N > 100)
    return 0;  
//Definir dados locais
  char ** criancas = (char **) malloc (N * sizeof(char *));
//Testar alocacao do arranjo de apontadores
  if (criancas == NULL)
    return 0;
//Definir dados locais
  int i = 0;
  bool controle = true;
//Preencher arranjo de apontadores
  while (i < N && controle)
  {
  //Tentar alocar arranjo de caracteres em cada posicao
    criancas[i] = (char *) malloc (21 * sizeof(char));
  //Testar se foi possivel alocar memoria
    controle = criancas[i] != NULL;
  //Variacao
    i++;
  }
//Testar alocacao final
  if (controle == false)
    return 0;
//Definir dados locais
  int sim = 0, nao = 0;
//Repetir para a quantidade informada
  for (int i = 0; i < N; i++)
  {
  //Definir dados locais
    char c = '\0';
  //Ler caractere
    scanf("%c", &c); getchar();
  //Ler criancas
    scanf("%s", criancas[i]); getchar();
  //Testar se foi bem comportada
    if (c == '+')
      sim++;
    else if (c == '-')
      nao++;
  }
//Colocar as criancas em ordem alfabetica
  ordenarCriancas(criancas, N);
//Mostrar resultado
  for (int i = 0; i < N; i++)
    printf("%s\n", criancas[i]);
  printf("Se comportaram: %d | Nao se comportaram: %d\n", sim, nao);
//Desalocar memoria
  for (int i = 0; i < N; i++)
    free(criancas[i]);
  free(criancas);
//Retornar
  return 0;
}