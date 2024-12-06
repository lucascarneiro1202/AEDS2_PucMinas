import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class Pokemon {
//-------------------------- Definir atributos privados --------------------------

  private int id;
  private int generation;
  private String name;
  private String description;
  private ArrayList<String> types;
  private ArrayList<String> abilities;
  private double weight;
  private double height;
  private int captureRate;
  private boolean isLegendary;
  private Date captureDate; 

//-------------------------- Definir construtores --------------------------

  public Pokemon () {
    setId(0);
    setGeneration(0);
    setName("");
    setDescription("");
    setTypes(new ArrayList<String>());
    setAbilities(new ArrayList<String>());
    setWeight(0.0);
    setHeight(0.0);
    setCaptureRate(0);
    setIsLegendary (false);
    setCaptureDate (new Date());
  }

  public Pokemon ( int id, int generation, String name, String description, ArrayList<String> types, ArrayList<String> abilities, double weight, double height, int captureRate, boolean isLegendary, Date captureDate) {
    setId(id);
    setGeneration(generation);
    setName(name);
    setDescription(description);
    setTypes(types);
    setAbilities(abilities);
    setWeight(weight);
    setHeight(height);
    setCaptureRate(captureRate);
    setIsLegendary (isLegendary);
    setCaptureDate (captureDate);
  }

//--------------------------  Definir getters --------------------------

  public int getId ()
  {   return this.id;   }

  public int getGeneration ()
  {   return this.generation;   }

  public String getName ()
  {   return this.name;   }

  public String getDescription ()
  {   return this.description;    }

  public ArrayList<String> getTypes ()
  {   return this.types;    }

  public ArrayList<String> getAbilities ()
  {   return this.abilities;    }

  public double getWeight ()
  {   return this.weight;   }

  public double getHeight ()
  {   return this.height;   }

  public int getCaptureRate ()
  {   return this.captureRate;    }

  public boolean getIsLegendary ()
  {   return this.isLegendary;    }

  public Date getCaptureDate ()
  {   return this.captureDate;    }

// --------------------------Definir setters --------------------------

  public void setId (int id)
  {   this.id = id;   }

  public void setGeneration (int generation)
  {   this.generation = generation;   }

  public void setName (String name)
  {   this.name = name;   }

  public void setDescription (String description)
  {   this.description = description;   }

  public void setTypes (ArrayList<String> types)
  {   this.types = types;   }

  public void setAbilities (ArrayList<String> abilities)
  {   this.abilities = abilities;   }

  public void setWeight (double weight)
  {   this.weight = weight;   }

  public void setHeight (double height)
  {   this.height = height;    }

  public void setCaptureRate (int captureRate)
  {   this.captureRate = captureRate;   }

  public void setIsLegendary (boolean isLegendary)
  {   this.isLegendary = isLegendary;   }

  public void setCaptureDate (Date captureDate)
  {   this.captureDate = captureDate;   }

//-------------------------- Definir metodo de clone --------------------------

  public Pokemon clone() {
    return new Pokemon (this.getId(), this.getGeneration(), this.getName(), this.getDescription(), this.getTypes(), this.getAbilities(), this.getWeight(), this.getHeight(), this.getCaptureRate(), this.getIsLegendary(), this.getCaptureDate());
  }

//-------------------------- Definir metodo de print --------------------------

  public String arrayListToString(ArrayList<String> list) {
  //Definir dados locais
    StringBuilder strResult = new StringBuilder();
    int size = list.size();
  //Adicionar inicio
    strResult.append("[");
  //Preencher string Result
    for (int i = 0; i < size; i++)
    {
    //Adicionar elemento
      strResult.append("'" + list.get(i) + "'");
    //Testar se e' o ultimo
      if (i < size - 1)
        strResult.append(", ");
    }
  //Adicionar final
    strResult.append("]");
  //Retornar
    return strResult.toString();
  }

  public void print () {
  //Definir dados locais
    String strTypes = arrayListToString ( getTypes() );
    String strAbilities = arrayListToString ( getAbilities() );
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    String data = df.format( getCaptureDate() );
  //Mostrar resultado final
    System.out.println("[#" + getId() + " -> " + getName() + ": " + getDescription() + " - " + strTypes + " - " + strAbilities +" - " + String.format("%.1f", getWeight()) + "kg - " + getHeight() + "m - " + getCaptureRate() +"% - " + getIsLegendary() + " - " + getGeneration() + " gen] - " + data);
  }

//-------------------------- Definir metodo de ler de arquivo --------------------------

  public static String readAll () {
  //Definir dados locais
    String allContent = "";
  //Tentar ler todas as linhas
    try
    {
      String path = "/tmp/pokemon.csv";
      Scanner sc = new Scanner (new File(path));
      return sc.useDelimiter("\\Z").next();
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();    
      return "";
    }
  }

//-------------------------- Definir metodos auxiliares --------------------------

  public String changeComma (String line) {
  //Definir dados locais
    StringBuilder newLine = new StringBuilder();
    int length = line.length();
    int controle = 0;
  //Percorrer string
    for (int i = 0; i < length; i++)
    {
    //Definir dados locais
      char c = line.charAt(i);
    //Testar caracteres
      if (c == '[')
      {   controle++;   newLine.append(c);    }
      else if (c == ']')
      {   controle--;   newLine.append(c);    }
    //Testar caractere
      else if (controle > 0 && c == ',')
        newLine.append(";");
      else
        newLine.append(c);
    }
  //Retornar
    return newLine.toString();
  }

  public String [] reallocate (String oldStr []) {
  //Definir dados locais
    int length = oldStr.length;
    String newStr [] = new String[length * 2];
  //Copiar valores
    for (int i = 0; i < length; i++)
    {   newStr[i] = oldStr[i];    }
  //Retornar
    return newStr;
  }

  public String [] split(String content, char c, int n) {
  //Definir dados locais
    String result [] = new String[n];
    int length = content.length();
    int esq = 0, dir = 0;
    int x = 0;
  //Percorrer conteudo
    for (dir = 0; dir < length; dir++)
    {
    //Definir dados locais
      char character = content.charAt(dir);
    //Testar caractere
      if (character == c)
      {
      //Testar espaco do arranjo de resultado
        if (x == result.length)
          result = reallocate(result);
      //Atribuir substring resultante
        result[x] = content.substring(esq, dir);
      //Movimentar a posicao da esquerda
        esq += result[x++].length() + 1;
      }
    }
  //Atribuir ultimo valor
    result[x] = content.substring(esq, dir);
  //Retornar
    return result;
  }

  public String [] splitAbilities (String content) {
  //Definir dados locais
    String result [] = new String[6];
    int length = content.length();
    int controle = 0;
    int x = 0;
    StringBuilder str = new StringBuilder();
  //Percorrer conteudo
    for (int i = 0; i < length; i++)
    {
    //Definir dados locais
      char c = content.charAt(i);
    //Testar caractere
      if (c == '\'' && controle == 0)
        controle++;
      else if (c == '\'' && controle == 1)
      {   
        controle--; 
        result[x++] = str.toString();   
        str = new StringBuilder();
      }
      else if (controle == 1)
        str.append(c);
    }
  //Retornar
    return result;
  }

  public void assignValues (String values[], String strAbilities []) {
  //Definir dados locais
    ArrayList<String> types = new ArrayList<String>();
    ArrayList<String> abilities = new ArrayList<String>();
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date capture_date = new Date();
  //Identificar id e geracao
    int id = Integer.parseInt(values[0]);
    int generation = Integer.parseInt(values[1]);
  //Identificar nome e descricao
    String name =values[2];
    String description = values[3];
  //Identificar tipos
    types.add(values[4]);
    if ( !values[5].equals("") )
      types.add(values[5]);
  //Identificar habilidades
    for (int i = 0; i < strAbilities.length && strAbilities[i] != null; i++)
      abilities.add(strAbilities[i]);
  //Identificar peso e altura
    double weight = (values[7].equals("")) ? 0.0 : Double.parseDouble(values[7]);
    double height = (values[8].equals("")) ? 0.0 : Double.parseDouble(values[8]);
  //Identificar taxa de captura e se e' lendario
    int capture_rate = Integer.parseInt(values[9]);
    boolean isLegendary = ( values[10].equals("1") ) ? true : false;
  //Identificar data
    try
    {   capture_date = df.parse( values[11] );    }
    catch (ParseException e)
    {   e.printStackTrace();    }
  //Setar valores
    setId(id);
    setGeneration(generation);
    setName(name);
    setDescription(description);
    setTypes(types);
    setAbilities(abilities);
    setWeight(weight);
    setHeight(height);
    setCaptureRate(capture_rate);
    setIsLegendary(isLegendary);
    setCaptureDate(capture_date);
  }

  public void read (int id, String allContent) {
  //Splitar as linhas
    String lines [] = split(allContent, '\n', 900);
  //Encontrar a linha
    String line = lines[id];
  //Alterar a linha 
    line = changeComma(line);
  //Separar a linha
    String values [] = split(line, ',', 12);
  //Separar as habilidades
    String abilities [] = splitAbilities(values[6]);
  //Atribuir dados
    assignValues(values, abilities);
  }
}

class No1 {
  public int captureRate;
  public No1 esq, dir; 
  public No2 no2;

  public No1 (int captureRate) {
    this(captureRate, null, null, null);
  }

  public No1 (int captureRate, No1 esq, No1 dir, No2 no2) {
    this.captureRate = captureRate;
    this.esq = esq;
    this.dir = dir;
    this.no2 = no2;
  }
}

class No2 {
  public String nomePokemon;
  public No2 esq, dir;

  public No2 (String nomePokemon) {
    this(nomePokemon, null, null);
  }

  public No2 (String nomePokemon, No2 esq, No2 dir) {
    this.nomePokemon = nomePokemon;
    this.esq = esq;
    this.dir = dir;
  }
}

public class ArvoreDeArvoreBinaria {
  public static Scanner sc = new Scanner (System.in);

  private No1 raiz;
  private static int comparacoes;

  public ArvoreDeArvoreBinaria() {
    raiz = null;
  }

  /**
   * Chamar método rescursivo para pesquisar nome do pokemon na árvore secundária
   */
  private boolean pesquisar(String nomePokemon) {
    System.out.print("raiz ");
    return pesquisar(nomePokemon, raiz);
  }

  /**
   * Pesquisar nome do pokemon na árvore secundária
   */
  private boolean pesquisar(String nomePokemon, No2 no2) {
  //Definir dados locais
    boolean resp;
  //Testar condições
    if (no2 == null) {
      resp = false;
    } else if (nomePokemon.compareTo(no2.nomePokemon) == 0) {
      comparacoes++;
      resp = true;
    } else if (nomePokemon.compareTo(no2.nomePokemon) < 0) {
      comparacoes++;
      System.out.print("esq ");
      resp = pesquisar(nomePokemon, no2.esq);
    } else {
      comparacoes++;
      System.out.print("dir ");
      resp = pesquisar(nomePokemon, no2.dir);
    }
  //Retornar
    return resp;
  }

  /**
   * Percorrer a árvore binária principal e pesquisar o nome em cada uma das árvores binárias secundárias
   */
  private boolean pesquisar(String nomePokemon, No1 no1) {
  //Testar se o nó atual é nulo
    if (no1 == null) {
      return false;
    }
  //Testar pesquisa na árvore interna do nó atual
    if (pesquisar(nomePokemon, no1.no2)) {
      return true;
    }
  //Avançar a pesquisa para o nó à esquerda
    System.out.print(" ESQ ");
    if (pesquisar(nomePokemon, no1.esq)) {
      return true;
    }
  //Avançar pesquisa para o nó à direita
    System.out.print(" DIR ");
    return pesquisar(nomePokemon, no1.dir);
  }

  /**
   * Chamar função recursiva para inserir nós primários
   */
  private void inserir(int captureRate) throws Exception {
    raiz = inserir(captureRate, raiz);
  }

  /**
   * Inserir nós na árvore binária principal
   */
  private No1 inserir(int captureRate, No1 no1) throws Exception {
  //Testar condições
    if (no1 == null) {
      no1 = new No1(captureRate);
    } else if (captureRate < no1.captureRate) {
      comparacoes++;
      no1.esq = inserir(captureRate, no1.esq);
    } else if (captureRate > no1.captureRate) {
      comparacoes++;
      no1.dir = inserir(captureRate, no1.dir);
    } else {
      throw new Exception("Erro ao inserir! (Elemento já está na Árvore)");
    }
  //Retornar
    return no1;
  }

  /**
   * Chamar função recursiva para inserir pokemon
   */
  private void inserir(Pokemon pokemon) throws Exception {
    raiz = inserir(pokemon, raiz);
  }

  /**
   * Identificar o nó1 com o captureRate % 15 do pokemon e chamar método para inserir o seu nome na árvore binária secundária correspondente
   */
  private No1 inserir(Pokemon pokemon, No1 no1) throws Exception {
  //Testar condições
    if (no1 == null) {
      no1 = new No1(pokemon.getCaptureRate() % 15);
      no1.no2 = new No2(pokemon.getName());
    } else if (pokemon.getCaptureRate() % 15 == no1.captureRate) {
      comparacoes++;
      no1.no2 = inserir(pokemon, no1.no2);
    } else if (pokemon.getCaptureRate() % 15 < no1.captureRate) {
      comparacoes++;
      no1.esq = inserir(pokemon, no1.esq);
    } else {
      no1.dir = inserir(pokemon, no1.dir);
    }
  //Retornar
    return no1;
  }

  /**
   * Inserir o nome do pokemon dentro da árvore binária secundária
   */
  private No2 inserir(Pokemon pokemon, No2 no2) throws Exception {
  //Testar condições
    if (no2 == null) {
      no2 = new No2(pokemon.getName());
    } else if (pokemon.getName().compareTo(no2.nomePokemon) < 0) {
      comparacoes++;
      no2.esq = inserir(pokemon, no2.esq);
    } else if (pokemon.getName().compareTo(no2.nomePokemon) > 0) {
      comparacoes++;
      no2.dir = inserir(pokemon, no2.dir);
    } else {
      throw new Exception("Erro ao inserir! (Elemento já está na Árvore)");
    }
  //Retornar
    return no2;
	}

  public static void main(String args []) {
  //Definir dados locais
    String allContent = Pokemon.readAll();
    ArvoreDeArvoreBinaria arvoreBinaria = new ArvoreDeArvoreBinaria();
    int captureRates [] = {7, 3, 11, 1, 5, 9, 13, 0, 2, 4, 6, 8, 10, 12, 14};
    String line;
    int id;
  //Inserir captureRates na árvore binária principal
    for (int i = 0; i < captureRates.length; i++) {
      try {
        // System.out.println(captureRates[i]);
        arvoreBinaria.inserir(captureRates[i]);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  //Ler dados 
    line = sc.nextLine();
  //Contar o tempo inicial
    long inicio = System.currentTimeMillis();
  //Leitura de todos os pokemons a serem inseridos
    while ( !line.equals("FIM") ) {
      id = Integer.parseInt(line);
      Pokemon pokemon = new Pokemon();
      pokemon.read(id, allContent);
      try {
        arvoreBinaria.inserir(pokemon);
      } catch (Exception e) {
        e.printStackTrace();
      }
      line = sc.nextLine();
    }
  //Leitura de todos os pokemons a serem pesquisados
    line = sc.nextLine();
    while ( !line.equals("FIM") ) {
    //Mostrar resultado
      boolean resp;
      System.out.println("=> " + line);
      resp = arvoreBinaria.pesquisar(line);
      System.out.println((resp) ? " SIM" : " NAO");
    //Ler novos dados
      line = sc.nextLine();
    }
  //Gerar arquivo de resultado
    long termino = System.currentTimeMillis();
    BufferedWriter resultado = null;
    try {
      resultado = Files.newBufferedWriter( Paths.get("./857340_arvoreArvore.txt") );  
      resultado.write("857340\t" + (termino - inicio) + "\t" + comparacoes);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (resultado != null) {
        try {    
          resultado.close();    
        } catch (IOException e) {    
          e.printStackTrace();    
        }
      }
    }
  }
}