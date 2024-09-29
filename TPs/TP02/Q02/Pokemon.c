#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define MAX_TAM_NUM_LINES 802
#define MAX_TAM_LINE_MAIOR 200
#define MAX_TAM_LINE_MENOR 50
#define MAX_TAM_VALUES 12
#define MAX_TAM_ARRAYLIST 7

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

char ** split(char * content, char c, int n, int q)
{
//Testar tamanho
  if (n <= 0)
    return NULL;
//Definir arranjo de ponteiros para cadeia de caracteres
  char ** result = calloc(n, sizeof(char *) );
//Testar alocacao
  if (result == NULL)
    return NULL;
//Definir cadeia de caracteres para cada arranjo
  for (int i = 0; i < n; i++)
  {
  //Alocar cadeia de caracteres
    result[i] = calloc(q, sizeof(char) );
  //Testar alocacao
    if (result[i] == NULL)
    {    
    //Desalocar memoria previamente alocada
      for (int j = 0; j < i; j++)
        free(result[j]);
    //Desalocar memoria do array de ponteiros
      free(result); 
    //Retornar
      return NULL;    
    }
  }
//Definir dados locais
  int length = strlen(content);
  int esq = 0, dir = 0;
  int x = 0;
//Percorrer conteudo
  while (dir < length && x < n)
  {
  //Definir dados locais
    char character = content[dir];
  //Testar se o caractere e' o procurado ou a ultima posicao
    if (character == c || dir == length - 1)
    {
    //Definir dados locais
      int final = (character == c) ? dir : dir + 1;
      int substrLenght = final - esq;
    //Testar se o tamanho a ser inserido e' maior que o tamanho alocado
      if (substrLenght >= q)
        substrLenght = q - 1;
    //Atribuir substring resultante
      strncpy ( result[x], content + esq, substrLenght);
      result[x][substrLenght] = '\0';
    //Variar posicao do array de strings
      x++;
    //Definir a posicao inicial da proxima substring
      esq = dir + 1;
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
  for (int i = 0; i < a->length; i++)
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
  a->content[a->length] = strdup(s); //duplicar para os ponteiros nao serem iguais
//Testar alocacao da string duplicada
  if (a->content[a->length] == NULL)
    return false;
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
  free(pokemon->name);
  free(pokemon->description);
  free_ArrayList(pokemon->types);
  free_ArrayList(pokemon->abilities);
  free_Date (pokemon->captureDate);
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

void Pokemon_assignValues (Pokemon * pokemon, char ** values, char ** abilities)
{
//Testar condicoes iniciais
  if (pokemon == NULL || values == NULL || abilities == NULL)
    return;
  for (int i = 0; i < MAX_TAM_VALUES; i++)
    if (values[i] == NULL) return;
  for (int i = 0; i < MAX_TAM_ARRAYLIST; i++)
    if (abilities[i] == NULL) return;
//Definir dados iniciais
  pokemon->types = new_ArrayList();
  pokemon->abilities = new_ArrayList();
  pokemon->captureDate = new_Date();
//Setar id e geracao
  pokemon->id = atoi(values[0]); 
  pokemon->generation = atoi(values[1]);
//Setar nome e descricao
  free(pokemon->name); //desalocar memoria antiga
  pokemon->name = strdup(values[2]);
  free(pokemon->description); //desalocar memoria antiga
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
  char * abilities = ArrayList_toString( pokemon->abilities );
//Definir weight
  char * weight = calloc(6, sizeof(char));
  if (weight == NULL)
    return;
  snprintf(weight, 6, "%.1lf", pokemon->weight);
//Definir height
  char * height = calloc(6, sizeof(char));
  if (height == NULL)
  {   free(weight); return;   }
  snprintf(height, 6, "%.1lf", pokemon->height);
//Mostrar pokemon
  printf( "[#%d -> %s: %s - %s - %s - %skg - %sm - %d%% - %s - %d gen] - %s\n", pokemon->id, pokemon->name, pokemon->description, types, abilities, weight, height, pokemon->captureRate, (pokemon->isLegendary) ? "true" : "false", pokemon->generation, Date_format( pokemon->captureDate ) );
//Desalocar memoria
  free(weight);
  free(height);
}

/* --------------------------- DEFINIR MAIN --------------------------- */

int main ()
{
//Definir dados da leitura de arquivo
  char * allContent = readFile("/tmp/pokemon.csv");
  if (allContent == NULL)
    return 1;
  char ** allLines = split(allContent, '\n', MAX_TAM_NUM_LINES, MAX_TAM_LINE_MAIOR);
  if (allLines == NULL)
    {    free(allContent); return 1;    }
//Definir dados da leitura da entrada padrao
  char * line = readLine();
  int i = 0;
//Laco de repeticao
  while (line != NULL && !equals(line, "FIM"))
  {
    if (isStringNumber(line))
    {
    //Definir dados locais
      Pokemon * pokemon = new_Pokemon();
      int id = atoi(line);
    //Chamar metodos de leitura e impressao
      Pokemon_read(pokemon, id, allLines);
      Pokemon_print(pokemon);
      free_Pokemon(pokemon);
    }
  //Ler proxima linha
    free(line);
    line = readLine();
  }
//Desalocar memoria do 'FIM'
  free(line);
//Retornar
  return 0;
}
