#include <stdio.h>

int main ()
{
//Definir dados locais
  FILE * arquivo = fopen("Q09.bin", "wb");
  int n;
  double d = 0.0;
//Ler quantidade
  scanf("%d", &n);
//Repetir a quantidade de vezes informada
  for (int i = 0; i < n; i++)
  {
  //Ler dado
    scanf("%lf", &d);
  //Escrever dado no arquivo
    fwrite(&d, sizeof(double), 1, arquivo);
  }
//Fechar arquivo de escrita
  fclose(arquivo);
//Reabrir arquivo para leitura
  arquivo = fopen("Q09.bin", "rb");
//Repetir para a quantidade de vezes informada
  for (int i = n - 1; i >= 0; i--)
  {
  //Posicionar o cabecote
    fseek( arquivo, i * sizeof(double), SEEK_SET );
  //Ler dado
    fread(&d, sizeof(double), 1, arquivo);
  //Mostrar resultado
    printf("%g\n", d);
  }
//Fechar arquivo
  fclose(arquivo);
//Retornar
  return 0;
}