#include <stdio.h>
#include <stdlib.h>

void escrever (char * fileName, int * a, int n)
{
//definir dados locais
  FILE * arquivo = fopen(fileName, "w");
//testar se o arquivo foi aberto corretamente
  if (arquivo)
  {
  //escrever quantidade
    fprintf(arquivo, "%d", n);
  //escrever dados
    for (int i = 0; i < n; i++)
    {
      fprintf(arquivo, "\n%d", a[i]);
    }
  //fechar arquivo
    fclose(arquivo);
  }
}

int * ler (char * fileName)
{
//definir dados locais
  FILE * arquivo = fopen(fileName, "r");
  int * a = NULL;
  int n;
//testar se o arquivo foi aberto corretamente
  if (arquivo)
  {
  //ler quantidade
    fscanf(arquivo, "%d", &n);
  //definir arranjo
    a = (int *) malloc (n * sizeof(int));
  //definir valores iniciais
    int i = 0;
  //condicao
    while ( !feof(arquivo) && i < n)
    {
    //atribuir valor lido
      fscanf(arquivo, "%d", &a[i]);
    //variacao
      i++;
    }
  //fechar arquivo
    fclose(arquivo);
  }
//retornar
  return a;
}

void method_01 ()
{
//definir dados locais
  int n;
//ler quantidade
  scanf("%d", &n);
//testar a quantidade
  if (n > 0)
  {    
  //definir arranjo de quantidade n
    int * a = (int *) malloc (n * sizeof(int));
  //testar se o arranjo foi alocado corretamente
    if (a)
    {
    //ler arranjo
      for (int i = 0; i < n; i++)
      {
        scanf("%d", &a[i]);
      }
    //escrever arranjo em arquivo
      escrever("arquivo.txt", a, n);
    //liberar espaco alocado
      free(a);
    //ler arquivo
      a = ler("arquivo.txt");
    //testar se a leitura foi concluida com sucesso
      if (a)
      {
      //mostrar resultado
        for (int i = 0; i < n; i++)
        {
          printf("%d: %d\n", i, a[i]);
        }    
      }
    }
  }
}

void method_02 ()
{
//definir dados 
  int n;
//ler quantidade
  scanf("%d", &n);
//testar a quantidade
  if (n > 0)
  {
  //definir arquivo
    FILE * arquivo = fopen("arquivo2.bin", "wb+");
  //testar se arquivo foi aberto corretamente
    if (arquivo)
    {
    //gravar dados
      for (int i = 0; i < n; i++)
      {
        int x;
        scanf("%i", &x);
        fwrite(&x, sizeof(int), 1, arquivo);
      }
    //recolocar o cabecote
      rewind(arquivo);
    //ler dados
      for (int i = n - 1; i >= 0; i--)
      {
        int x;
        fseek(arquivo, (i) * sizeof(int), SEEK_SET);
        fread(&x, sizeof(int), 1, arquivo);
        printf("%d\n", x);
      }
    }
  }
}

void method_03 ()
{
//definir dados 
  int n;
//ler quantidade
  scanf("%d", &n);
//testar a quantidade
  if (n > 0)
  {
  //definir arquivo
    FILE * arquivo = fopen("arquivo3.bin", "wb+");
  //testar se arquivo foi aberto corretamente
    if (arquivo)
    {
    //gravar dados
      for (int i = 0; i < n; i++)
      {
        int x;
        scanf("%i", &x);
        fwrite(&x, sizeof(int), 1, arquivo);
      }
    //recolocar o cabecote
      rewind(arquivo);
    //definir dados
      int x, y, z;
      int length = n / 2;
    //ler dados
      for (int i = 0; i < length; i++)
      {
      //ler o x
        fseek(arquivo, i * sizeof(int), SEEK_SET);
        fread(&x, sizeof(int), 1, arquivo);
      //ler o y
        fseek(arquivo, (n - i - 1) * sizeof(int), SEEK_SET);
        fread(&y, sizeof(int), 1, arquivo);
      //somar
        z = x + y;
        printf("%d + %d = %d\n", x, y, z);
      //recolocar o apontador
        rewind(arquivo);
      }
    }
  }
}

int main ()
{
  int x;
  scanf("%d", &x);
  switch (x)
  {
    case 1: method_01(); break;
    case 2: method_02(); break;
    case 3: method_03(); break;
    default: printf("ERRO: Valor invalido.\n"); break;
  }
  return 0;
}