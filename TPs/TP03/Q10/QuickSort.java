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

//-------------------------- Definir metodos auxiliares da Main --------------------------

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

class CelulaDupla {
  Pokemon pokemon;
  CelulaDupla ant;
  CelulaDupla prox;

  public CelulaDupla () {
    this.pokemon = new Pokemon();
    this.ant = this.prox = null;
  }

  public CelulaDupla (Pokemon pokemon) {
    this.pokemon = pokemon.clone();
    this.ant = this.prox = null;
  }
}

class ListaDupla {
  CelulaDupla primeira;
  CelulaDupla ultima;
  int length;

  public ListaDupla () {
    this.primeira = new CelulaDupla();
    this.ultima = this.primeira;
    this.length = 0;
  }

  public void inserirInicio (Pokemon pokemon) {
  //Definir nova CelulaDupla
    CelulaDupla celula = new CelulaDupla(pokemon);
  //Definir logica de ponteiros
    celula.ant = primeira;
    celula.prox = primeira.prox;
    primeira.prox = celula;
  //Testar se e' o ultimo elemento
    if (primeira == ultima) {
      ultima = celula;
    } else {
      celula.prox.ant = celula;
    }
  //Aterrar ponteiro
    celula = null;
  //Aumentar tamanho
    length++;
  }

  public void inserirFim (Pokemon pokemon) {
  //Definir nova CelulaDupla
    CelulaDupla celula = new CelulaDupla(pokemon);
  //Definir logica de ponteiros
    ultima.prox = celula;
    celula.ant = ultima;
    ultima = celula;
  //Aterrar ponteiro
    celula = null;
  //Aumentar tamanho
    length++;
  }

  public void inserir (Pokemon pokemon, int posicao) throws Exception {
  //Testar posicao
    if (posicao < 0 || posicao > this.length) {
      throw new Exception ("[ERRO]: Posicao invalida");
    } else if (posicao == 0) {
      inserirInicio(pokemon);
    } else if (posicao == this.length) {
      inserirFim(pokemon);
    } else {
    //Caminhar até a posicao anterior à de insercao
      CelulaDupla i = primeira;
      for (int j = 0; j < posicao; j++, i = i.prox);
    //Definir nova CelulaDupla
      CelulaDupla celula = new CelulaDupla(pokemon);
    //Definir logica de ponteiros
      celula.ant = i;
      celula.prox = i.prox;
      celula.ant.prox = celula.prox.ant = celula;
    //Aterrar ponteiros
      i = null;
      celula = null;
    //Aumentar tamanho
      this.length++;
    }
  }

  public Pokemon removerInicio () throws Exception {
  //Testar se a lista está vazia
    if (primeira == ultima) {
      throw new Exception ("[ERRO]: Lista vazia.");
    }
  //Identificar celula a ser retirada
    CelulaDupla celula = primeira.prox;
  //Testar se existe apenas um elemento
    if (celula == ultima) {
      ultima = primeira;
      primeira.prox = null;
      celula.ant = null;
    } else {
      celula.ant.prox = celula.prox;
      celula.prox.ant = celula.ant;
    }
  //Diminuir tamanho
    this.length--;
  //Retornar
    return celula.pokemon;
  }

  public Pokemon removerFim () throws Exception {
  //Testar se a lista está vazia
    if (primeira == ultima) {
      throw new Exception ("[ERRO]: Lista vazia.");
    }
  //Identificar celula a ser retirada
    CelulaDupla celula = ultima;
  //Definir logica de ponteiros
    ultima = ultima.ant;
    ultima.prox = celula.ant = null;
  //Diminuir tamanho
    this.length--;
  //Retornar
    return celula.pokemon;
  }

  public Pokemon remover (int posicao) throws Exception {
  //Definir pokemon de retorno
    Pokemon pokemon = null;
  //Testar se a lista está vazia
    if (primeira == ultima) {
      throw new Exception ("[ERRO]: Lista vazia.");
    } else if (posicao < 0 || posicao >= this.length) {
      throw new Exception ("[ERRO]: Posição inválida.");
    } else if (posicao == 0) {
      pokemon = removerInicio();
    } else if (posicao == this.length - 1) {
      pokemon = removerFim();
    } else {
    //Caminhar até a posicao anterior à de remoção
      CelulaDupla i = primeira;
      for (int j = 0; j <= posicao; j++, i = i.prox);
    //Definir logica de ponteiros
      i.ant.prox = i.prox;
      i.prox.ant = i.ant;
    //Separar o pokemon removido
      pokemon = i.pokemon;
    //Aterrar ponteiros
      i.prox = i.ant = null;
      i = null;
    //Diminuir tamanho
      this.length--;    
    }
  //Retornar
    return pokemon;
  }

  public void mostrarLista () throws Exception {
  //Testar se ha' elementos
    if (this.length == 0) {
      throw new Exception ("[ERRO]: Lista vazia.");
    }
  //Mostrar elementos existentes
    CelulaDupla i = primeira.prox;
    while (i != null) {
      i.pokemon.print();
      i = i.prox;
    }
  }

  public CelulaDupla get (int posicao) throws Exception {
  //Definir celula de retorno
    CelulaDupla celula = null;
  //Testar se a lista está vazia
    if (primeira == ultima) {
      throw new Exception ("[ERRO]: Lista vazia.");
    } else if (posicao < 0 || posicao >= this.length) {
      throw new Exception ("[ERRO]: Posição inválida." + "(" + posicao + "/" + this.length + ")");
    } else if (posicao == 0) {
      celula = primeira.prox;
    } else if (posicao == this.length - 1) {
      celula = ultima;
    } else {
    //Caminhar até a posicao desejada
      CelulaDupla i = primeira;
      for (int j = 0; j <= posicao; j++, i = i.prox);
      celula = i;
    }
  //Retornar
    return celula;
  }

  public void swap (int i, int j) {
    try {
    //Definir celulas a serem trocadas
      CelulaDupla celula1 = get(i);
      CelulaDupla celula2 = get(j);
    //Alterar o ponteiro de ultimo
      if (celula1 == ultima)
        ultima = celula2;
      else if (celula2 == ultima)
        ultima = celula1;
    //Definir celula temporaria
      CelulaDupla temp;
    //Definir logica de ponteiros
      temp = celula1.prox;
      celula1.prox = celula2.prox;
      celula2.prox = temp;

      if (celula1.prox != null)
        celula1.prox.ant = celula1;
      if (celula2.prox != null)
        celula2.prox.ant = celula2;

      temp = celula1.ant;
      celula1.ant = celula2.ant;
      celula2.ant = temp;

      if (celula1.ant != null) 
        celula1.ant.prox = celula1;
      if (celula2.ant != null)
        celula2.ant.prox = celula2;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

public class QuickSort {
  public static Scanner sc = new Scanner (System.in);

  private static ListaDupla lista;
  private static int comparacoes;
  private static int movimentacoes;

  public static void quickSort (int esq, int dir) {
    try {
    //Definir dados locais (esq/dir/pivo)
      int i = esq, j = dir;
      Pokemon pivo = lista.get( (esq + dir) / 2 ).pokemon;
    //Percorrer lista e trocar os que estao na ordem errada
      while (i <= j) {
        while ( (lista.get(i).pokemon.getGeneration() < pivo.getGeneration()) || 
                (lista.get(i).pokemon.getGeneration() == pivo.getGeneration() && lista.get(i).pokemon.getName().compareTo(pivo.getName()) < 0))
        {   i++; comparacoes++;   }
        while ( (lista.get(j).pokemon.getGeneration() > pivo.getGeneration()) || 
                (lista.get(j).pokemon.getGeneration() == pivo.getGeneration() && lista.get(j).pokemon.getName().compareTo(pivo.getName()) > 0) )
        {   j--; comparacoes++;   }
        if (i <= j) {
          lista.swap(i, j);
          movimentacoes += 3;
          i++;
          j--;
        }
      }
    //Fazer chamadas recursivas
      if (esq < j) quickSort(esq, j);
      if (i < dir) quickSort(i, dir);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void quickSort (ListaDupla lista) {
    quickSort(0, lista.length - 1);
  }

  public static void main (String args []) {
  //Definir dados locais
    String allContent = Pokemon.readAll();
    lista = new ListaDupla();
    String line;
    int id;
  //Ler dados 
    line = sc.nextLine();
  //Leitura de todos os pokemons
    while ( !line.equals("FIM") ) {
      id = Integer.parseInt(line);
      Pokemon pokemon = new Pokemon();
      pokemon.read(id, allContent);
      try {
        lista.inserirFim(pokemon);
      } catch (Exception e) {
        e.printStackTrace();
      }
      line = sc.nextLine();
    }
  //Ordenar por quicksort
    long inicio = System.currentTimeMillis();
    quickSort(lista);     
    long termino = System.currentTimeMillis();
  //Mostrar resultado
    try {
      lista.mostrarLista();
    } catch (Exception e) {
      e.printStackTrace();
    }
  //Gerar arquivo de resultado
    BufferedWriter resultado = null;
    try {
      resultado = Files.newBufferedWriter( Paths.get("./857340_quicksort3.txt") );  
      resultado.write("857340\t" + (termino - inicio) + "\t" + comparacoes + "\t" + movimentacoes);
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