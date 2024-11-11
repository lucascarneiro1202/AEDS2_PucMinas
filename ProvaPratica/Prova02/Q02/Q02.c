#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct {
  char * nome;
  double preco;
  int preferencia;
} Presente;

Presente * new_Presente () {
  Presente * presente = malloc(sizeof(Presente));
  presente->nome = calloc (100, sizeof(char));
  strcpy(presente->nome, "teste");
  presente->preco = 0.0;
  presente->preferencia = 0;
  return presente;
}




typedef struct {
  int length;
  char * nome;
  Presente ** listaPresentes;
} Lista;

Lista * new_Lista () {
  Lista * lista = malloc(sizeof(Lista));
  lista->length = 0;
  lista->nome = calloc(100, sizeof(char));
  lista->listaPresentes = malloc( 100 * sizeof(Presente *) );
  // for (int i = 0; i < 100; i++) {
  //   lista->listaPresentes[i] = new_Presente();
  // }
  return lista;
}

void Lista_add (Lista * lista, Presente * presente) {
  lista->listaPresentes[lista->length] = presente;
  lista->length++;
}




void sort (Lista * lista) {
  int n = lista->length;

  for (int i = 0; i < n - 1; i++) {
    int posMaior = i;
    for (int j = i + 1; j < n; j++) {
      Presente * maior = lista->listaPresentes[posMaior];
      Presente * presenteTmp = lista->listaPresentes[j];
      if (maior->preferencia < presenteTmp->preferencia) {
        posMaior = j;
      } 
      else if (maior->preferencia == presenteTmp->preferencia && maior->preco > presenteTmp->preco) {
        posMaior = j;
      } else if (maior->preferencia == presenteTmp->preferencia && maior->preco == presenteTmp->preco && strcmp(maior->nome, presenteTmp->nome) > 0 ) {
        posMaior = j;
      }
    }
    Presente * tmp = lista->listaPresentes[i];
    lista->listaPresentes[i] = lista->listaPresentes[posMaior];
    lista->listaPresentes[posMaior] = tmp;
  }
}


int main () {
  char * nome = calloc(100, sizeof(char));
  while ( scanf("%s", nome) != EOF ) {
    getchar();
    Lista * lista = new_Lista();
    strcpy(lista->nome, nome);

    int qtdPresentes = 0;
    scanf("%d", &qtdPresentes); getchar();


    for (int i = 0; i < qtdPresentes; i++) {
      Presente * presente = new_Presente();

      char * tmpNome = calloc(100, sizeof(char));
      double tmpPreco = 0.0;
      int tmpPreferencia = 0;

      fgets(tmpNome, 99 * sizeof(char), stdin);
      tmpNome[strcspn(tmpNome, "\n")] = '\0';
      scanf("%lf", &tmpPreco); getchar();
      scanf("%d", &tmpPreferencia); getchar();

      strcpy(presente->nome, tmpNome);
      presente->preco = tmpPreco;
      presente->preferencia = tmpPreferencia;

      Lista_add(lista, presente);
    }

    sort(lista);

    printf("Lista de %s\n", nome);
    int i = 0;
    while (lista->listaPresentes[i] != NULL) {
      printf("%s - R$%.2lf\n", lista->listaPresentes[i]->nome, lista->listaPresentes[i]->preco);
      i++;
    }
    printf("\n");

    getchar();
  }
}