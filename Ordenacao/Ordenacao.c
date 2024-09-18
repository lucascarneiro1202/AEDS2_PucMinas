#include <stdio.h>
#include <stdlib.h>

void printArray (int * a, int n)
{
  printf("[");
  for (int i = 0; i < n; i++)
  {   printf(" %d", a[i]);    }
  printf(" ]\n");
}

void readArray (int * a, int n)
{
  for (int i = 0; i < n; i++)
  {
    scanf("%d", &a[i]);
  }
}

int * gerarArrayCrescente (int n)
{
//Definir dados locais
  int * a = (int *) malloc(n * sizeof(int));
//Preencher
  for (int i = 0; i < n; i++)
  {
    a[i] = i + 1;
  }
//Retornar
  return a;
}

int * gerarArrayDecrescente (int n)
{
//Definir dados locais
  int * a = (int *) malloc(n * sizeof(int));
  int x = 0;
//Preencher
  for (int i = n; i > 0; i--)
  {
    a[x++] = i;
  }
//Retornar
  return a;
}

void printComplexity (int comparacoes, int movimentacoes, int n)
{
  printf("Comparacoes: %d (%d)\n", comparacoes, n);
  printf("Movimentacoes: %d (%d)\n", movimentacoes, n);
}

void selectionSort (int * a, int n)
{
//Definir dados locais
  int comparacoes = 0, movimentacoes = 0;
//Mostrar antes
  printf("Antes: ");
  printArray(a, n);
  printf("\n");
//ALGORITMO DE ORDENACAO
  for (int i = 0; i < n - 1; i++)
  {
    int menor = i;
    for (int j = i + 1; j < n; j++)
    {
      comparacoes++;
      if (a[menor] > a[j])
      {
        menor = j;
      }
    }
    movimentacoes += 3;
    int temp = a[menor];
    a[menor] = a[i];
    a[i] = temp;
  }
//Mostrar complexidade
  printComplexity(comparacoes, movimentacoes, n);
//Mostrar depois
  printf("\nDepois: ");
  printArray(a, n);
}

void insertionSort (int * a, int n)
{
//Definir dados locais
  int comparacoes = 0, movimentacoes = 0;
//Mostrar antes
  printf("Antes: ");
  printArray(a, n);
  printf("\n");
//ALGORITMO DE ORDENACAO
  for (int i = 1; i < n; i++)
  {
    int tmp = a[i];
    int j = i - 1;
    while ( j >= 0 && a[j] > tmp )
    {
      comparacoes++;
      movimentacoes++;
      a[j + 1] = a[j];
      j--;
    }
    comparacoes++;
    a[j + 1] = tmp;
  }
//Mostrar complexidade
  printComplexity(comparacoes, movimentacoes, n);
//Mostrar depois
  printf("\nDepois: ");
  printArray(a, n);
}

void bubbleSort (int * a, int n)
{
//Definir dados locais
  int comparacoes = 0, movimentacoes = 0;
//Mostrar antes
  printf("Antes: ");
  printArray(a, n);
  printf("\n");
//ALGORITMO DE ORDENACAO
  for (int i = 1; i < n; i++)
  {
    for (int j = 0; j < n - i; j++)
    {
      comparacoes++;
      if (a[j] > a[j + 1])
      {
        movimentacoes++;
        int temp = a[j];
        a[j] = a[j + 1];
        a[j + 1] = temp;
      }
    }
  }  
//Mostrar complexidade
  printComplexity(comparacoes, movimentacoes, n);
//Mostrar depois
  printf("\nDepois: ");
  printArray(a, n);
}

int main ()
{
//Definir dados locais
  int x = 0;
  int n = 0;
//Ler quantidade
  printf("Entrar com uma quantidade de elementos: ");
  scanf("%d", &n);
//Definir arranjo
  // int * a = gerarArrayCrescente(n);
  int * a = gerarArrayDecrescente(n);
//Selecionar metodo
  do
  {
  //Mostrar opcoes
    printf("\n1 - Selection Sort\n");
    printf("2 - Insertion Sort\n");
    printf("3 - Bubble Sort\n");
    printf("4 - Shell Sort\n");
    printf("5 - Quick Sort\n");
    printf("0 - Sair\n");
    printf("\nEscolha uma das opcoes acima: ");
  //Ler dados
    scanf("%d", &x);
    printf("\n");
  //Chamar metodo
    switch (x)
    {
      case 0: break;
      case 1: selectionSort(a, n); break;
      case 2: insertionSort(a, n); break;
      case 3: bubbleSort(a, n); break;
      // case 4: shellSort(a, n); break;
      // case 5: quickSort(a, n); break;
      default: printf("ERRO: Opcao invalida.\n"); break;
    }
  }
  while (x != 0);
//Retornar
  return 0;
}