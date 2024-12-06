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

class NoAN {
  public Pokemon pokemon;
  public NoAN esq, dir; 
  boolean cor;

  public NoAN() {
    this(new Pokemon());
  }

  public NoAN(Pokemon pokemon) {
    this(pokemon, false, null, null);
  }

  public NoAN(Pokemon pokemon, boolean cor) {
    this(pokemon, cor, null, null);
  }

  public NoAN(Pokemon pokemon, boolean cor, NoAN esq, NoAN dir) {
    this.pokemon = pokemon;
    this.cor = cor;
    this.esq = esq;
    this.dir = dir;
  }
}

public class ArvoreBinariaRedBlack {
  public static Scanner sc = new Scanner (System.in);

  private NoAN raiz;
  private static int comparacoes;

  public ArvoreBinariaRedBlack() {
    raiz = null;
  }

  private boolean pesquisar(String nomePokemon) {
    System.out.print("raiz ");
    return pesquisar(nomePokemon, raiz);
  }

  private boolean pesquisar(String nomePokemon, NoAN no) {
  //Definir dados locais
    boolean resp;
  //Testar condições
    if (no == null) {
      resp = false;
    } else if (nomePokemon.compareTo(no.pokemon.getName()) == 0) {
      comparacoes++;
      resp = true;
    } else if (nomePokemon.compareTo(no.pokemon.getName()) < 0) {
      comparacoes++;
      System.out.print("esq ");
      resp = pesquisar(nomePokemon, no.esq);
    } else {
      comparacoes++;
      System.out.print("dir ");
      resp = pesquisar(nomePokemon, no.dir);
    }
  //Retornar
    return resp;
  }

  public void inserir(Pokemon pokemon) throws Exception {
  //Testar se a arvore está vazia
    if (raiz == null) {
      raiz = new NoAN(pokemon);
    } else if (raiz.esq == null && raiz.dir == null) { //Testar se a arvore tem um pokemon  
      if (pokemon.getName().compareTo(raiz.pokemon.getName()) < 0) {
        raiz.esq = new NoAN(pokemon);
      } else {
        raiz.dir = new NoAN(pokemon);
      }
      comparacoes++;    
    } else if (raiz.esq == null) { //Testar se a arvore tem dois pokemons (raiz e dir)
      if (pokemon.getName().compareTo(raiz.pokemon.getName()) < 0) {
        raiz.esq = new NoAN(pokemon);
      } else if (pokemon.getName().compareTo(raiz.dir.pokemon.getName()) < 0) {
        raiz.esq = new NoAN(raiz.pokemon);
        raiz.pokemon = pokemon;
      } else {
        raiz.esq = new NoAN(raiz.pokemon);
        raiz.pokemon = raiz.dir.pokemon;
        raiz.dir.pokemon = pokemon;
      }
      raiz.esq.cor = raiz.dir.cor = false;
      comparacoes++;
    } else if (raiz.dir == null) { //Testar se a arvore tem dois pokemons (raiz e esq)
      if (pokemon.getName().compareTo(raiz.pokemon.getName()) > 0) {
        raiz.dir = new NoAN(pokemon);
      } else if (pokemon.getName().compareTo(raiz.esq.pokemon.getName()) > 0) {
        raiz.dir = new NoAN(raiz.pokemon);
        raiz.pokemon = pokemon;
      } else {
        raiz.dir = new NoAN(raiz.pokemon);
        raiz.pokemon = raiz.esq.pokemon;
        raiz.esq.pokemon = pokemon;
      }
      raiz.esq.cor = raiz.dir.cor = false;
      comparacoes++;
    } else { //A arvore tem tres ou mais pokemons
      inserir(pokemon, null, null, null, raiz);
    }
    raiz.cor = false;
  }

  private void inserir(Pokemon pokemon, NoAN bisavo, NoAN avo, NoAN pai, NoAN noAN) throws Exception {
    if (noAN == null) {
      if (pokemon.getName().compareTo(pai.pokemon.getName()) < 0) {
        noAN = pai.esq = new NoAN(pokemon, true);
      } else {
        noAN = pai.dir = new NoAN(pokemon, true);
      }
      if (pai.cor == true) {
        balancear(bisavo, avo, pai, noAN);
      }
    } else {
    //Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
      if (noAN.esq != null && noAN.dir != null && noAN.esq.cor == true && noAN.dir.cor == true) {
        noAN.cor = true;
        noAN.esq.cor = noAN.dir.cor = false;
        if (noAN == raiz) {
          noAN.cor = false;
        } else if (pai.cor == true) {
          balancear(bisavo, avo, pai, noAN);
        }
      }
      if (pokemon.getName().compareTo(noAN.pokemon.getName()) < 0) {
        inserir(pokemon, avo, pai, noAN, noAN.esq);
      } else if (pokemon.getName().compareTo(noAN.pokemon.getName()) > 0) {
        inserir(pokemon, avo, pai, noAN, noAN.dir);
      } else {
        throw new Exception("Erro inserir (pokemon repetido)!");
      }
    }
  }

  private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
  //Testar se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
    if (pai.cor == true) {
    //4 tipos de reequilibrios e acoplamento
      if (pai.pokemon.getName().compareTo(avo.pokemon.getName()) > 0) { // rotacao a esquerda ou direita-esquerda
        if (i.pokemon.getName().compareTo(pai.pokemon.getName()) > 0) {
          avo = rotacaoEsq(avo);
        } else {
          avo = rotacaoDirEsq(avo);
        }
      } else { // rotacao a direita ou esquerda-direita
        if (i.pokemon.getName().compareTo(pai.pokemon.getName()) < 0) {
          avo = rotacaoDir(avo);
        } else {
          avo = rotacaoEsqDir(avo);
        }
      }
      if (bisavo == null) {
        raiz = avo;
      } else if (avo.pokemon.getName().compareTo(bisavo.pokemon.getName()) < 0) {
        bisavo.esq = avo;
      } else {
        bisavo.dir = avo;
      }
    //Reestabelecer as cores apos a rotacao
      avo.cor = false;
      avo.esq.cor = avo.dir.cor = true;
    }
  }

  private NoAN rotacaoDir(NoAN no) {
  //Definir dados locais
    NoAN noEsq = no.esq;
    NoAN noEsqDir = noEsq.dir;
  //Lógica de ponteiros
    noEsq.dir = no;
    no.esq = noEsqDir;
  //Retornar
    return noEsq;
  }

  private NoAN rotacaoEsq(NoAN no) {
  //Definir dados locais
    NoAN noDir = no.dir;
    NoAN noDirEsq = noDir.esq;
  //Lógica de ponteiros
    noDir.esq = no;
    no.dir = noDirEsq;
  //Retornar
    return noDir;
  }

  private NoAN rotacaoDirEsq(NoAN no) {
    no.dir = rotacaoDir(no.dir);
    return rotacaoEsq(no);
  }

  private NoAN rotacaoEsqDir(NoAN no) {
    no.esq = rotacaoEsq(no.esq);
    return rotacaoDir(no);
  }

  public static void main (String args []) {
  //Definir dados locais
    String allContent = Pokemon.readAll();
    ArvoreBinariaRedBlack arvoreBinaria = new ArvoreBinariaRedBlack();
    String line;
    int id;
  //Ler dados 
    line = sc.nextLine();
  //Leitura de todos os pokemons a serem inseridos
    long inicio = System.currentTimeMillis();
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
      System.out.println(line);
      resp = arvoreBinaria.pesquisar(line);
      System.out.println((resp) ? "SIM" : "NAO");
    //Ler novos dados
      line = sc.nextLine();
    }
  //Gerar arquivo de resultado
    long termino = System.currentTimeMillis();
    BufferedWriter resultado = null;
    try {
      resultado = Files.newBufferedWriter( Paths.get("./857340_alvinegra.txt") );  
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