#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <sys/time.h>
#include <ctype.h>

#define MAX_TAM_NUM_LINES 802
#define MAX_TAM_LINE_MAIOR 200
#define MAX_TAM_LINE_MENOR 50
#define MAX_TAM_VALUES 12
#define MAX_TAM_ARRAYLIST 8

/* --------------------------- DADOS PARA CALCULAR COMPLEXIDADE --------------------------- */

int comparacoes = 0;
int movimentacoes = 0;

/* --------------------------- FUNCOES PARA MANIPULAR ARQUIVOS --------------------------- */

char * readFile( char * fileName )
{
//Definir dados locais
  FILE * file = fopen(fileName, "r");
  if (file == NULL)
    return NULL;
//Calcular o tamanho do arquivo
  fseek(file, 0, SEEK_END);
  long size = ftell(file);
  if (size <= 0)
    {    fclose(file); return NULL;    }
//Definir arranjo com todo o conteudo
  char * allContent = calloc ( size + 1, sizeof(char) );
  if (allContent == NULL)
    {    fclose(file); return NULL;    }
//Retornar cabecote ao inicio do arquivo
  fseek(file, 0, SEEK_SET);
//Ler todo o arquivo
  long bytes = fread(allContent, sizeof(char), size, file);
//Testar se a quantidadede bytes lido e' igual a' do tamanho do arquivo
  if (bytes != size)
    {    free(allContent); fclose(file); return NULL;    } 
//Fechar arquivo
  fclose(file);
//Retornar
  return allContent;
}

/* --------------------------- FUNCOES PARA MANIPULAR STRINGS --------------------------- */

char * readLine ()
{
//Definir alocacao de memoria
  char * line = calloc (MAX_TAM_LINE_MAIOR, sizeof(char));
//Testar alocacao
  if (!line)
    return NULL;
//Definir dados locais
  char c;  
  int i = 0;
//Laco de repeticao
  while (i < MAX_TAM_LINE_MAIOR - 1 && (c = getchar()) != '\n')
  {
  //Atribuir o caractere
    line[i] = c;
  //Variacao
    i++;
  }
//Adicionar o caractere final
  line[i] = '\0';
//Retornar
  return line;
}

bool equals (char * str1, char * str2)
{
//Testar apontadores
  if (!str1 || !str2)
    return false;
//Retornar
  return strcmp(str1, str2) == 0;
}

char ** split (char * content, char c, int n, int q)
{
//Testar condicoes
  if (content == NULL || n <= 0 || q <= 0) return NULL;
//Alocar ponteiro para array de strings
  char ** result = malloc(n * sizeof(char *));
  if (result == NULL) return NULL;
//Alocar memoria para cada string
  for (int i = 0; i < n; i++)
  {
    result[i] = calloc(q, sizeof(char));
    if (result[i] == NULL)
    {
      for (int j = 0; j < i; j++) free(result[j]);
      free(result);
      return NULL;
    }
  }
//Definir dados locais
  int length = strlen(content);
  int esq = 0, dir = 0, x = 0;
//Percorrer conteudo
  while (dir <= length && x < n)
  {
  //Definir dados locais
    char character = content[dir];
  //Testar se o caractere e' o delimitador ou o final da sttring
    if (character == c || dir == length)
    {
      int final = (character == c) ? dir : dir + 1;
      int substrLength = final - esq;
    //Prefinir overflow
      if (substrLength >= q) substrLength = q - 1;
    //Copiar a substring
      strncpy(result[x], content + esq, substrLength);
      result[x][substrLength] = '\0'; //Garantir fechamento da string
      x++;
      esq = dir + 1; //Mover para o inicio da proxima substring
    }
  //Variacao
    dir++;
  }
//Retornar
  return result;
}

void changeComma (char * s)
{
//Definir dados locais
  int length = strlen(s);
  int controle = 0;
//Percorrer string
  for (int i = 0; i < length; i++)
  {
  //Definir dados locais
    char c = s[i];
  //Testar caracteres
    if (c == '[')
      controle++;
    else if (c == ']')
    {
      controle--;
      if (controle < 0)
        controle = 0;
    }
  //Testar caractere
    else if (controle > 0 && c == ',')
      s[i] = ';';
  }
}

char ** splitAbilities (char * s)
{
//Testar se string existe
  if (s == NULL)
    return NULL;
//Definir arranjo de string a ser retornado
  char ** result = malloc (MAX_TAM_ARRAYLIST * sizeof(char *));
//Testar alocacoa deo arranjo de ponteiros
  if (result == NULL)
    return NULL;
//Alocar strings do vetor
  for (int i = 0; i < MAX_TAM_ARRAYLIST; i++)
  {
  //Alocar string em cada ponteiro do vetor
    result[i] = calloc (MAX_TAM_LINE_MENOR, sizeof(char));
  //Testar alocacao de memoria
    if (result[i] == NULL)
    {
    //Desalocar strings previamente alocadas
      for (int j = 0; j < i; j++)
        free(result[j]);
    //Desalocar arranjo de ponteiros para strings
      free(result);
    //Retornar
      return NULL;
    }
  }
//Percorrer conteudo
  int length = strlen(s);
  int controle = 0, x = 0, y = 0;
  for (int i = 0; i < length && y < MAX_TAM_LINE_MENOR; i++)
  {
    char c = s[i];
    if (c == '\'' && controle == 0)
      controle++;
    else if (c == '\'' && controle == 1)
    {
      controle--;
      result[x][y] = '\0'; //garantir ultimo caractere da string
      x++;
      y = 0;
      if (x >= MAX_TAM_ARRAYLIST)
        break;
    }
    else if (controle == 1 && y < MAX_TAM_LINE_MENOR - 1)
      result[x][y++] = c;
  }
//Testar se nao houve habilidades
  if (x == 0 || controle > 0) 
  {
    for (int i = 0; i < MAX_TAM_ARRAYLIST; i++) 
        free(result[i]);
    free(result);
    return NULL;
  }
//Retornar
  return result;
}

bool isCharNumber (char c)
{   return '0' <= c && c <= '9';    }

bool isStringNumber (char * s)
{
//Testar se string existe
  if (s == NULL)
    return false;
//Definir dados locais
  bool resp = true;
  int length = strlen(s);
  int i = 0;
//Laco de repeticao
  while (i < length && resp)
  {
  //Testar
    resp = resp && isCharNumber(s[i]);
  //Variacao
    i++;
  }
//Retornar
  return resp;
}

/* --------------------------- DEFINIR ARRAY LIST E SEUS METODOS --------------------------- */

typedef struct
{
  int size;
  int length;
  char ** content; //lines: MAX_TAM_ARRAYLIST, columns: MAX_TAM_SUBLINE
} ArrayList;

void free_ArrayList(ArrayList * a)
{
//Testar objeto
  if (a == NULL)
    return;
//Desalocar strings
  for (int i = 0; i < MAX_TAM_ARRAYLIST; i++)
    free(a->content[i]);
//Desalocar arranjo de apontadores para strings
  free(a->content);
//Desalocar ArrayList
  free(a);
}

ArrayList * new_ArrayList()
{
//Alocar memoria
  ArrayList * a = malloc ( sizeof(ArrayList) );
//Testar alocacao
  if (a == NULL)
    return NULL;
//Alocar memoria
  a->content = malloc (MAX_TAM_ARRAYLIST * sizeof(char *));
//Testar alocacao
  if (a->content == NULL)
  {
  //Desalocar memoria do objeto
    free(a);
  //Retornar
    return NULL;  
  }
//Alocar strings do arranjo de strings
  for (int i = 0; i < MAX_TAM_ARRAYLIST; i++)
  {
  //Alocar memoria de cada string
    a->content[i] = calloc (MAX_TAM_LINE_MENOR, sizeof(char));
  //Testar alocacao de memoria
    if (a->content[i] == NULL)
    {
    //Desalocar toda a memoria
      free_ArrayList(a);
    //Retornar
      return NULL;
    }
  }
//Atualizar valores internos
  a->size = MAX_TAM_ARRAYLIST;
  a->length = 0;
//Retornar
  return a;
}

bool ArrayList_add (ArrayList * a, char * s)
{
//Testar se a operacao e' valida
  if (a == NULL || a->length == a->size || strlen(s) > MAX_TAM_LINE_MENOR)
    return false;
//Adicionar novo elemento
  //if (a->content[a->length] != NULL) {  free(a->content[a->length]); a->content[a->length] = NULL;  }
  free(a->content[a->length]);
  //printf("s = %s (%d)\n", s, strlen(s));
  //strcpy(a->content[a->length], s);
  a->content[a->length] = strdup(s);
//Aumentar o tamanho atual do objeto
  a->length++;
//Retornar
  return true;
}	

char * ArrayList_get (ArrayList * a, int i)
{
//Testar se operacao e' valida
  if (a == NULL || a->length == 0 || i >= a->length)
    return NULL;
//Retornar
  return (i < a->length) ? a->content[i] : NULL;
}

ArrayList * ArrayList_clone (ArrayList * original)
{
//Testar ArrayList original
  if (original == NULL) return NULL;
//Criar novo ArrayList
  ArrayList * clone = new_ArrayList();
  if (clone == NULL) return NULL;
//Copiar dados
  clone->size = original->size;
//Copiar conteudo
  for (int i = 0; i < original->length; i++)
  {
    if (! ArrayList_add(clone, original->content[i]))
    {  free_ArrayList(clone); return NULL;  }
  }
//Retornar
  return clone;
}

char * ArrayList_toString (ArrayList * a)
{
//Testar condicoes
  if (a == NULL || a->content == NULL)
    return NULL;
  for (int i = 0; i < a->size; i++)
  {
    if (a->content[i] == NULL)
      return NULL;
  }
//Definir string resultante
  char * result = calloc(a->length * (MAX_TAM_LINE_MENOR + 1), sizeof(char));
  if (result == NULL)
    return NULL;
//Adicionar inicio
  strcat(result, "[");
//Preencher string Result
  for (int i = 0; i < a->length; i++)
  {
  //Adicionar elemento
    strcat(result, "'");
    strcat(result, ArrayList_get(a, i));
    strcat(result, "'");
  //Testar se e' o ultimo
    if ( ArrayList_get(a, i + 1) != NULL )
      strcat(result, ", ");
  }
//Adicionar final
  strcat(result, "]");
//Retornar
  return result;
}

/* --------------------------- DEFINIR DATE E SEUS METODOS --------------------------- */

typedef struct
{
  int day;
  int month;
  int year;
} Date;

Date * new_Date ()
{
//Definir dados locais
  Date * date = malloc ( sizeof(Date) );
//Testar alocacao
  if (date == NULL)
    return NULL;
//Determinar dados iniciais
  date->day = 0;
  date->month = 0;
  date->year = 0;
//Retornar
  return date;
}

void free_Date (Date * date)
{
//Testar se objeto esta alocado
  if (date == NULL)
    return;
//Desalocar memoria
  free(date);
}

Date * Date_clone(Date * original)
{
//Testar data original
  if (original == NULL) return NULL;
//Alocar nova data
  Date * clone = new_Date();
  if (clone == NULL) return NULL;
//Copiar atributos
  clone->day = original->day;
  clone->month = original->month;
  clone->year = original->year;
//Retornar
  return clone;
}


bool Date_isDateString(char * s)
{
  return isCharNumber(s[0]) && isCharNumber(s[1]) && s[2] == '/' && isCharNumber(s[3]) && isCharNumber(s[4]) && s[5] == '/' && isCharNumber(s[6]) && isCharNumber(s[7]) && isCharNumber(s[8]) && isCharNumber(s[9]);
}

bool Date_isValidDate (int day, int month, int year) 
{
//Testar se dia e' valido
  if (day < 1) 
    return false;
//Testar se mes e' valido
  if (month < 1 || month > 12) 
    return false;
//Definir os dias de cada mes
  int daysMonth[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
//Testar se o mes e' fevereiro e o ano e' bissexto
  if (month == 2 && ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))) 
      daysMonth[2] = 29;
  return day <= daysMonth[month];
}

bool Date_parse (Date * date, char * s)
{
//Testar condicoes
  if (date == NULL || strlen(s) != 10 || !Date_isDateString(s))
    return false;
//Atribuir valores
  char ** result = split(s, '/', 3, 6);
//Testar se alocacao foi bem sucedida
  if (result == NULL)
    return false;
  for (int i = 0; i < 3; i++)
  {
    if (result[i] == NULL)
      {    free(result); return false;    }
  }
//Atribuir valores de data
  int day = atoi(result[0]);
  int month = atoi(result[1]);
  int year = atoi(result[2]);
//Testar valores de data
  if ( !Date_isValidDate(day, month, year) )
    return false;
//Atribuir valores de data
  date->day = day;
  date->month = month;
  date->year = year; 
//Deslocar memoria
  for (int i = 0; i < 3; i++)
    free(result[i]);
  free(result);
//Retornar
  return true;
}

char * Date_format(Date * date)
{
//Testar se objeto existe
  if (date == NULL)
    return NULL;
//Definir string
  char * result = calloc (11, sizeof(char));
//Testar alocacao
  if (result == NULL)
    return NULL;
//Construir resposta
  snprintf(result, 11, "%02d/%02d/%04d", date->day, date->month, date->year);
//Retornar
  return result;
}

/* --------------------------- DEFINIR POKEMON E SEUS METODOS --------------------------- */

typedef struct 
{
  int id;
  int generation;
  char * name;
  char * description;
  ArrayList * types;
  ArrayList * abilities;
  double weight;
  double height;
  int captureRate;
  bool isLegendary;
  Date * captureDate;
} Pokemon;

void free_Pokemon (Pokemon * pokemon)
{
//Testar se objeto existe
  if (pokemon == NULL)
    return;
//Desalocar dados
  if (pokemon->name != NULL) free(pokemon->name);
  if (pokemon->description != NULL) free(pokemon->description);
  if (pokemon->types != NULL) free_ArrayList(pokemon->types);
  if (pokemon->abilities != NULL) free_ArrayList(pokemon->abilities);
  if (pokemon->captureDate != NULL) free_Date(pokemon->captureDate);
//Desalocar apontador para pokemon
  free(pokemon);
}

Pokemon * new_Pokemon ()
{
//Alocar memoria de um objeto Pokemon
  Pokemon * pokemon = malloc ( sizeof(Pokemon) );
//Testar alocacao
  if (pokemon == NULL)
    return NULL;
//Inicializar dados
  pokemon->id = 0;
  pokemon->generation = 0;
//Alocar nome
  pokemon->name = calloc ( MAX_TAM_LINE_MENOR, sizeof(char) );
  if (pokemon->name == NULL)
  {
    free(pokemon);
    return NULL;
  }
//Alocar descricao
  pokemon->description = calloc ( MAX_TAM_LINE_MENOR, sizeof(char) );
  if (pokemon->description == NULL)
  {
    free(pokemon->name);
    free(pokemon);
    return NULL;
  }
//Alocar tipos
  pokemon->types = new_ArrayList();
  if (pokemon->types == NULL)
  {
    free(pokemon->name);
    free(pokemon->description);
    free(pokemon);
    return NULL;
  }
//Alocar habilidades
  pokemon->abilities = new_ArrayList();
  if (pokemon->abilities == NULL)
  {
    free(pokemon->name);
    free(pokemon->description);
    free_ArrayList(pokemon->types);
    free(pokemon);
    return NULL;
  }
//Inicialiar outros campos
  pokemon->weight = 0.0;
  pokemon->height = 0.0;
  pokemon->captureRate = 0;
  pokemon->isLegendary = false;
//Alocar data de captura
  pokemon->captureDate = new_Date();
  if (pokemon->captureDate == NULL)
  {
    free(pokemon->name);
    free(pokemon->description);
    free_ArrayList(pokemon->types);
    free_ArrayList(pokemon->abilities);
    free(pokemon);
    return NULL;
  }
//Retornar
  return pokemon;
}

Pokemon * Pokemon_clone (Pokemon * pokemon)
{
//Testar se pokemon original e' valido
  if (pokemon == NULL) return NULL;
//Alocar novo pokemon
  Pokemon * novoPokemon = new_Pokemon();
  if (novoPokemon == NULL)
    return NULL;
//Copiar id e generation
  novoPokemon->id = pokemon->id;
  novoPokemon->generation = pokemon->generation;
//Copiar nome e testar sucesso
  free(novoPokemon->name);
  novoPokemon->name = strdup(pokemon->name);  
  if (novoPokemon->name == NULL) {   free_Pokemon(novoPokemon); return NULL;   }
//Copiar descricao e testar sucesso
  free(novoPokemon->description);
  novoPokemon->description = strdup(pokemon->description);  
  if (novoPokemon->description == NULL) {  free_Pokemon(novoPokemon); return NULL;  }
//Copiar types e testar sucesso
  free_ArrayList(novoPokemon->types);
  novoPokemon->types = ArrayList_clone(pokemon->types);
  if (novoPokemon->types == NULL) {  free_Pokemon(novoPokemon); return NULL;  }
//Copiar abilities e testar sucesso
  free_ArrayList(novoPokemon->abilities);
  novoPokemon->abilities = ArrayList_clone(pokemon->abilities); 
  if (novoPokemon->abilities == NULL) {  free_Pokemon(novoPokemon); return NULL;  }
//Copiar weight, height, captureRate e isLegendary 
  novoPokemon->weight = pokemon->weight;
  novoPokemon->height = pokemon->height;
  novoPokemon->captureRate = pokemon->captureRate;
  novoPokemon->isLegendary = pokemon->isLegendary;
//Copiar captureDate e testar sucesso
  free_Date(novoPokemon->captureDate);
  novoPokemon->captureDate = Date_clone(pokemon->captureDate);
  if (novoPokemon->captureDate == NULL) {  free_Pokemon(novoPokemon); return NULL;  }
//Retornar
  return novoPokemon;
}

void Pokemon_assignValues (Pokemon * pokemon, char ** values, char ** abilities)
{
//Testar condicoes iniciais
  if (pokemon == NULL || values == NULL || abilities == NULL) return;
  for (int i = 0; i < MAX_TAM_VALUES; i++) if (values[i] == NULL) return;
  for (int i = 0; i < MAX_TAM_ARRAYLIST; i++) if (abilities[i] == NULL) return;
//Setar id e geracao
  pokemon->id = atoi(values[0]); 
  pokemon->generation = atoi(values[1]);
//Setar nome e descricao
  if (pokemon->name != NULL) free(pokemon->name); //desalocar memoria antiga
  pokemon->name = strdup(values[2]);
  if (pokemon->description != NULL) free(pokemon->description); //desalocar memoria antiga
  pokemon->description = strdup(values[3]);
//Setar tipos
  ArrayList_add( pokemon->types, values[4] );
  if ( !equals(values[5], "") )
    ArrayList_add( pokemon->types, values[5] );
//Setar habilidades
  for (int i = 0; i < MAX_TAM_ARRAYLIST && !equals(abilities[i], ""); i++)
    ArrayList_add( pokemon->abilities, abilities[i] );
//Setar peso, altura, taxa de captura e se e' lendario
  pokemon->weight = atof(values[7]);
  pokemon->height = atof(values[8]);
  pokemon->captureRate = atoi(values[9]);
  pokemon->isLegendary = values[10][0] == '1';
//Setar data de captura
  Date_parse ( pokemon->captureDate, values[11] );
}

void Pokemon_read (Pokemon * pokemon, int id, char ** allLines)
{
//Testar condicoes
  if (pokemon == NULL || allLines == NULL)
    return;
  for (int i = 0; i < MAX_TAM_NUM_LINES; i++)
  {
    if (allLines[i] == NULL)
      return;
  }
//Encontrar a linha
  char * line = strdup(allLines[id]);
//Alterar a linha
  changeComma (line);
//Separar a linha
  char ** values = split(line, ',', MAX_TAM_VALUES, MAX_TAM_LINE_MAIOR );
  if (values == NULL)
    return;
//Separar as habilidades
  char ** abilities = splitAbilities(values[6]);  
  if (abilities == NULL)
    return;
//Atribuir dados
  Pokemon_assignValues(pokemon, values, abilities);
//Desalocar memoria
  free(line);
  for (int i = 0; i < MAX_TAM_VALUES; i++)
    free(values[i]);
  free(values);
  for (int i = 0; i < MAX_TAM_ARRAYLIST; i++)
    free(abilities[i]); 
  free(abilities);
}	

void Pokemon_print(Pokemon * pokemon)
{
//Testar condicoes
  if (pokemon == NULL || pokemon->name == NULL || pokemon-> description == NULL || pokemon->types == NULL || pokemon->abilities == NULL || pokemon->captureDate == NULL)
	  return;
//Definir formatacao
  char format [] = "[#%d -> %s: %s - %s - %s - %skg - %sm - %d %% - %s - %d gen] %s\n";
//Definir types e abilities
  char * types = ArrayList_toString( pokemon->types );
  if (types == NULL) return;
  char * abilities = ArrayList_toString( pokemon->abilities );
  if (abilities == NULL) {  free(types); return;  }
//Definir weight
  char * weight = calloc(6, sizeof(char)); if (weight == NULL) {  free(types); free(abilities); return;  }
  snprintf(weight, 6, "%.1lf", pokemon->weight);
//Definir height
  char * height = calloc(6, sizeof(char));
  if (height == NULL) {  free(types); free(abilities); free(weight); return; }
  snprintf(height, 6, "%.1lf", pokemon->height);
//Definir captureDate
  char * captureDate = Date_format( pokemon->captureDate );
//Mostrar pokemon
  printf( "[#%d -> %s: %s - %s - %s - %skg - %sm - %d%% - %s - %d gen] - %s\n", pokemon->id, pokemon->name, pokemon->description, types, abilities, weight, height, pokemon->captureRate, (pokemon->isLegendary) ? "true" : "false", pokemon->generation, captureDate );
//Desalocar memoria
  free(types);
  free(abilities);
  free(weight);
  free(height);
  free(captureDate);
}

/* --------------------------- DEFINIR LISTA DE POKEMON E SEUS METODOS --------------------------- */

typedef struct Celula {
  Pokemon * pokemon;
  struct Celula * prox;
} Celula;

Celula * new_CelulaVazia () {
//Definir objeto
  Celula * celula = malloc (1 * sizeof(Celula));
  if (celula == NULL) return NULL;
//Definir dados iniciais
  celula->pokemon = NULL;
  celula->prox = NULL;
//Retornar
  return celula;
}

Celula * new_Celula (Pokemon * pokemon) {
//Definir objeto
  Celula * celula = malloc (1 * sizeof(Celula));
  if (celula == NULL) return NULL;
//Definir dados iniciais
  celula->pokemon = Pokemon_clone(pokemon);
  celula->prox = NULL;
//Retornar
  return celula;
}

void free_Celula (Celula * celula) {
//Testar ponteiro
  if (celula == NULL) return;
//Desalocar memoria
  if (celula->pokemon != NULL) free_Pokemon(celula->pokemon);
  celula->prox = NULL;
  free(celula);
}

typedef struct {
  Celula * primeiro;
  Celula * ultimo;
  int length;
} Lista;

Lista * new_Lista () {
//Definir objeto
  Lista * lista = malloc (1 * sizeof(Lista));
  if (lista == NULL)  return NULL;
//Definir dados iniciais
  lista->primeiro = new_CelulaVazia();
  if (lista->primeiro == NULL) {  free(lista);  return NULL;  }
  lista->ultimo = lista->primeiro;
  lista->length = 0;
//Retornar
  return lista;
}

void free_Lista (Lista * lista) {
//Testar ponteiro
  if (lista == NULL) return;
//Desalocar memoria
  Celula * i = lista->primeiro;
  Celula * j = i;
  while (j != NULL) {
    j = j->prox;
    free_Celula(i);
    i = j;
  }
  free(lista);
}

void inserirInicio (Lista * lista, Pokemon * pokemon) {
//Testar lista
  if (lista == NULL)
  {    printf("[ERRO]: Lista invalida.\n"); return;   }
//Testar pokemon a ser inserido
  if (pokemon == NULL)
  {    printf("[ERRO]: Pokemon invalido.\n"); return;   }
//Definir nova celula
  Celula * novaCelula = new_Celula(pokemon);
//Definir logica de ponteiros
  novaCelula->prox = lista->primeiro->prox;
  lista->primeiro->prox = novaCelula;
  if (lista->primeiro == lista->ultimo) lista->ultimo = novaCelula;
  novaCelula = NULL;
//Aumentar o tamanho
  lista->length++;
}

void inserirFim (Lista * lista, Pokemon * pokemon) {
//Testar lista
  if (lista == NULL)
  {    printf("[ERRO]: Lista invalida.\n"); return;   }
//Testar pokemon a ser inserido
  if (pokemon == NULL)
  {    printf("[ERRO]: Pokemon invalido.\n"); return;   }
//Definir nova celula
  Celula * novaCelula = new_Celula(pokemon);
//Definir logica de ponteiros
  lista->ultimo->prox = novaCelula;
  lista->ultimo = novaCelula;
//Aumentar o tamanho
  lista->length++;
}

void inserir (Lista * lista, Pokemon * pokemon, int posicao) {
//Testar lista
  if (lista == NULL)
  {    printf("[ERRO]: Lista invalida.\n"); return;   }
//Testar pokemon a ser inserido
  if (pokemon == NULL)
  {   printf("[ERRO]: Pokemon invalido.\n"); return;   }
//Testar posicao
  if (posicao < 0 || posicao > lista->length)
  {   printf("[ERRO]: Posicao invalida.\n"); return;   }
//Testar se e' inserir no inicio
  if (posicao == 0) 
  {   inserirInicio(lista, pokemon);    }
//Testar se e' inserir no fim
  else if (posicao == lista->length)
  {   inserirFim(lista, pokemon);   }
//Qualquer outra posicao
  else {
  //Definir nova celula
    Celula * novaCelula = new_Celula(pokemon);
  //Definir logica de ponteiros
    Celula * cel = lista->primeiro;
    for (int i = 0; i < posicao; cel = cel->prox, i++);
    Celula * tmp = cel->prox;
    cel->prox = novaCelula;
    novaCelula->prox = tmp;
  //Aumentar o tamanho
    lista->length++;
  }
}

Pokemon * removerInicio (Lista * lista) {
//Testar lista
  if (lista == NULL)
  {    printf("[ERRO]: Lista invalida.\n"); return NULL;   }
//Testar se ha' elementos
  if (lista->length <= 0)
  {    printf("[ERRO]: Lista vazia.\n"); return NULL;    }
//Separar elemento a ser removido
  Celula * celula = lista->primeiro->prox;
//Logica de ponteiros
  lista->primeiro->prox = celula->prox;
//Diminuir o tamanho
  lista->length--;
//Desalocar memoria
  Pokemon * pokemon = Pokemon_clone(celula->pokemon);
  free_Celula(celula);
//Retornar
  return pokemon;
}

Pokemon * removerFim (Lista * lista) {
//Testar lista
  if (lista == NULL)
  {    printf("[ERRO]: Lista invalida.\n"); return NULL;   }
//Testar se ha' elementos
  if (lista->length <= 0)
  {    printf("[ERRO]: Lista vazia.\n"); return NULL;    }
//Separar elemento a ser removido
  Celula * celula = lista->ultimo;
//Logica de ponteiros
  Celula * i;
  for (i = lista->primeiro; i->prox != lista->ultimo; i = i->prox);
  lista->ultimo = i;
//Diminuir o tamanho
  lista->length--;
//Desalocar memoria
  Pokemon * pokemon = Pokemon_clone(celula->pokemon);
  free_Celula(celula);
  i = lista->ultimo->prox = NULL;
//Retornar
  return pokemon;
}

Pokemon * remover (Lista * lista, int posicao) {
//Definir dados locais
  Pokemon * pokemon = NULL;
//Testar lista
  if (lista == NULL)
  {    printf("[ERRO]: Lista invalida.\n"); return NULL;   }
//Testar se ha' elementos
  if (lista->length <= 0)
  {    printf("[ERRO]: Lista vazia.\n"); return NULL;    }
//Testar posicao
  if (posicao < 0 || posicao >= lista->length)
  {    printf("[ERRO]: Posicao invalida.\n"); return NULL;   }
//Testar se e' remover no inicio
  if (posicao == 0) 
  {   pokemon = removerInicio(lista);    }
//Testar se e' remover no fim
  else if (posicao == lista->length)
  {   pokemon = removerFim(lista);   }
//Qualquer outra posicao
  else {
  //Definir logica de ponteiros
    Celula * tmp = lista->primeiro;
    int i;
    for (i = 0; i < posicao; i++, tmp = tmp->prox);
    Celula * removida = tmp->prox;
    tmp->prox = tmp->prox->prox;
    pokemon = Pokemon_clone(removida->pokemon);
  //Diminuir o tamanho
    lista->length--;
  }
//Retornar
  return pokemon;
}

void mostrarLista (Lista * lista) {
  int i = 0;
  while (lista->length > 0) {
    Pokemon * pokemon = removerInicio(lista);
    printf("[%d] ", i++);
    Pokemon_print(pokemon);
    free_Pokemon(pokemon);
  }
}

/* --------------------------- DEFINIR MAIN --------------------------- */

int main () {
//Definir dados da leitura de arquivo
  char * allContent = readFile("/tmp/pokemon.csv"); 
  if (allContent == NULL) return 1;
  char ** allLines = split(allContent, '\n', MAX_TAM_NUM_LINES, MAX_TAM_LINE_MAIOR); 
  if (allLines == NULL) {  free(allContent); return 1;  }
//Definir arranjo de pokemon
  Lista * lista = new_Lista();
  if (lista == NULL) return -1;
//Definir dados da leitura da entrada padrao
  char * line = readLine();
//Laco de repeticao
  while (line != NULL && !equals(line, "FIM")) {
  //Testar se string e' um numero
    if (isStringNumber(line)) {
    //Definir dados locais
      Pokemon * pokemon = new_Pokemon();
      int id = atoi(line);
    //Chamar metodo de leitura
      Pokemon_read(pokemon, id, allLines);
    //Adicionar 'a lista
      inserirFim(lista, pokemon);
    //Desalocar memoria
      free_Pokemon(pokemon);
    }
  //Ler proxima linha
    free(line);
    line = readLine();
  }
//Definir dados de comandos
  char * comando = calloc(10, sizeof(char));
  if (comando == NULL) return -1;
  int i = 0, id = 0, posicao = 0, k;
  scanf("%d", &k);
//Laço de repetição
  while (i < k) {
  //Ler instruções de comando
    scanf("%s", comando);
  //Testar se o comando lê duas entradas
    if (comando[1] == '*') 
      scanf("%d", &posicao);
  //Ler id do pokemon a ser manipulado
    if (comando[0] == 'I')
      scanf("%d", &id);
  //Definir dados locais
    Pokemon * pokemon = new_Pokemon();
    Pokemon_read(pokemon, id, allLines);
  //Testar o comando (Inserir/Remover)
    if (comando[0] == 'I') {
      if (comando[1] == 'I') {
        inserirInicio(lista, pokemon);
      } else if (comando[1] == '*') {
        inserir(lista, pokemon, posicao);
      } else if (comando[1] == 'F') {
        inserirFim(lista, pokemon);
      }
    } else if (comando[0] == 'R' ) {
      if (comando[1] == 'I') {
        Pokemon * tmp = removerInicio(lista);
        printf("(R) %s\n", tmp->name);
        free_Pokemon(tmp);
      } else if (comando[1] == '*') {
        Pokemon * tmp = remover(lista, posicao);
        printf("(R) %s\n", tmp->name);
        free_Pokemon(tmp);
      } else if (comando[1] == 'F') {
        Pokemon * tmp = removerFim(lista);
        printf("(R) %s\n", tmp->name);
        free_Pokemon(tmp);
      }       
    }
  //Desalocar memoria
    free_Pokemon(pokemon);
  //Variacao
    i++;
  }
//Mostrar a lista resultante
  mostrarLista(lista);
//Desalocar memorias
  free(line);
  free(allContent);
  for (int i = 0; i < MAX_TAM_NUM_LINES; i++)
    free(allLines[i]);
  free(allLines); 
  free(comando);
  free_Lista(lista);
//Retornar
  return 0;
}