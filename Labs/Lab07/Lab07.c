#include <stdio.h>
#include <stdlib.h>

//Função de intercalacao que conta as inversões

int intercalar (int * arr, int * temp, int left, int mid, int right) 
{
//Definir dados locais
  int i = left;    // Índice da primeira metade
  int j = mid + 1; // Índice da segunda metade
  int k = left;    // Índice do array temporário
  int inversoes = 0;
//Merge das duas metades e contagem de inversões
  while (i <= mid && j <= right) 
  {
    if (arr[i] <= arr[j]) 
    {
      temp[k++] = arr[i++];
    } 
    else 
    {
      temp[k++] = arr[j++];
      inversoes += (mid - i + 1); // Conta as inversões
    }
  }
//Copia os elementos restantes da primeira metade, se houver
  while (i <= mid) 
    temp[k++] = arr[i++];
//Copia os elementos restantes da segunda metade, se houver
  while (j <= right) 
    temp[k++] = arr[j++];
//Copia de volta para o array original
  for (i = left; i <= right; i++)
    arr[i] = temp[i];
//Retornar
  return inversoes;
}

//Função recursiva de Merge Sort que conta as inversões

int mergesort (int * arr, int * temp, int left, int right) 
{
  int mid, inversoes = 0;
  if (left < right) 
  {
    mid = (left + right) / 2;
    inversoes += mergesort (arr, temp, left, mid);
    inversoes += mergesort (arr, temp, mid + 1, right);
    inversoes += intercalar (arr, temp, left, mid, right);
  }
  return inversoes;
}

//Função que calcula o número mínimo de ultrapassagens

int minimo_ultrapassagens (int * grid_largada, int * grid_chegada, int n) 
{
//Cria um array de posições baseado no grid de largada
  int * posicao = (int *) malloc(n * sizeof(int));
  for (int i = 0; i < n; i++) 
    posicao[grid_largada[i] - 1] = i;
//Cria um array com os índices relativos ao grid de chegada
  int * chegada_indices = (int *) malloc(n * sizeof(int));
  for (int i = 0; i < n; i++) 
    chegada_indices[i] = posicao[grid_chegada[i] - 1]; //Preencher, na posicao de chegada de cada competidor, a sua respectiva posicao de largada
//Array temporário para o Merge Sort
  int * temp = (int *) malloc(n * sizeof(int));
//Calcular o número de inversões
  int ultrapassagens = mergesort (chegada_indices, temp, 0, n - 1);
//Desalocar memoria
  free(posicao);
  free(chegada_indices);
  free(temp);
//Retornar
  return ultrapassagens;
}

int main() 
{
//Definir dados locais
  int n;
  int * k;
//Ler número de competidores
  while (scanf("%d", &n) != EOF)
  {
  //Alocar os arranjos dos grid
    int * grid_largada = (int *) malloc(n * sizeof(int));
    int * grid_chegada = (int *) malloc(n * sizeof(int));
  //Leitura do grid de largada
    for (int i = 0; i < n; i++)
      scanf("%d", &grid_largada[i]);
  //Leitura do grid de chegada
    for (int i = 0; i < n; i++) 
      scanf("%d", &grid_chegada[i]);
  //Calcula e imprime o número mínimo de ultrapassagens
    int resultado = minimo_ultrapassagens(grid_largada, grid_chegada, n);
    printf("%d\n", resultado);
  // Libera a memória alocada
    free(grid_largada);
    free(grid_chegada);
  }
//Retornar
  return 0;
}

/*
Análise de complexidade:

1 - Leitura das entradas: O(n)
  A leitura dos grids de largada e chegada é feita com dois laços de repeticao com tamanho n, em que n é o número de competidores. 
  Portanto, o custo dessa etapa é O(n) para cada iteração.

2 - Construção do array de posições: O(n)
  Para cada competidor no grid de largada, sua posição inicial é armazenada em um array.
  Isso também tem custo O(n), pois itera-se sobre todos os competidores uma vez.

3 - Transformação do grid de chegada em índices relativos O(n)
   Para cada competidor no grid de chegada, buscamos sua posição inicial no array de posições. 
   Isso tem custo O(n), pois é feita uma única iteração sobre o grid de chegada.

4 - Contagem de ultrapassagens usando Merge Sort O(n log n):
   A contagem de ultrapassagens é realizada por meio do algoritmo Merge Sort modificado.
   A complexidade do Merge Sort é O(n log n), onde n é o tamanho do array, pois cada divisão do array custa O(log n) e cada merge custa O(n). 
   Assim, a etapa de contagem de ultrapassagens é a mais custosa.

5. Complexidade total:
   A complexidade total para cada caso de teste é O(n log n), pois a etapa que realiza mais operações é a contagem de ultrapassagens. 
   As outras operações (leitura, construção de arrays e transformações) têm complexidade O(n), que é menor em relação ao O(n log n) do Merge Sort.
   Portanto, para cada caso de teste, o tempo de execução é O(n log n). 
*/

