import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class ListaPokemon {
  public static Scanner sc = new Scanner (System.in);

//-------------------------- Definir atributos privados --------------------------
  private Pokemon [] pks;
  private int n;

//--------------------------- Definir construtores -------------------------------
  public ListaPokemon () {
    this.pks = new Pokemon[6];
    this.n = 0;
  }

  public ListaPokemon (int x) {
    this.pks = new Pokemon[x];
    this.n = 0;
  }

//---------------------- Definir metodos de Inserção e Remoção ------------------
  public void inserirInicio (Pokemon pokemon) throws Exception {
  //Testar se a lista está cheia
    if (this.n >= pks.length)
      throw new Exception ("[ERRO]: Lista cheia! (inserirInicio).");
  //Shift dos elementos para a direita
    for (int i = this.n; i > 0; i--)
      pks[i] = pks[i - 1];
  //Inserir novo Pokemon no inicio
    pks[0] = pokemon;
    this.n++;
  }

  public void inserir (Pokemon pokemon, int posicao) throws Exception {
  //Testar se a lista está cheia
    if (this.n >= pks.length)
      throw new Exception ("[ERRO]: Lista cheia! (inserir)");
  //Testar se a posicao é válida
    if (posicao < 0 || posicao > this.n)
      throw new Exception ("[ERRO]: Posição inválida! (inserir)");
  //Shift dos elementos até 'posicao' para a direita
    for (int i = this.n; i > posicao; i--)
      pks[i] = pks[i - 1];
  //Inserir novo Pokemon na posição indicada
    pks[posicao] = pokemon;
    this.n++;
  }

  public void inserirFim (Pokemon pokemon) throws Exception {
  //Testar se a lista está cheia
    if (this.n >= pks.length)
      throw new Exception ("[ERRO]: Lista cheia! (inserirFim)");
  //Inserir novo Pokemon no fim
    pks[this.n] = pokemon;
    this.n++;
  }

  public Pokemon removerInicio() throws Exception {
  //Testar se a lista possui elementos
    if (this.n <= 0)
      throw new Exception ("[ERRO]: Lista vazia! (removerInicio)");
  //Separar elemento a ser removido
    Pokemon pokemon = pks[0];
  //Shift dos elementos para a esquerda
    for (int i = 0; i < this.n - 1; i++)
      pks[i] = pks[i + 1];
    this.n--;
  //Retornar
    return pokemon;
  }

public Pokemon remover (int posicao) throws Exception {
  //Testar se a lista possui elementos
    if (this.n <= 0)
      throw new Exception ("[ERRO]: Lista vazia! (remover)");
  //Testar se a posicao é válida
    if (posicao < 0 || posicao >= this.n)
      throw new Exception ("[ERRO]: Posição inválida! (remover)");
  //Separar elemento a ser removido
    Pokemon pokemon = pks[posicao];
  //Shift dos elementos depois de 'posicao' para a esquerda
    for (int i = posicao; i < this.n - 1; i++)
      pks[i] = pks[i + 1];
    this.n--;
  //Retornar
    return pokemon;
  }

  public Pokemon removerFim () throws Exception {
  //Testar se a lista possui elementos
    if (this.n <= 0)
      throw new Exception ("[ERRO]: Lista vazia! (removerFim)");
  //Separar elemento a ser removido
    Pokemon pokemon = pks[this.n - 1];
  //Diminuir o tamanho
    this.n--;
  //Retornar 
    return pokemon;
  }

  public static void main (String args []) {
  //Definir dados locais
    ListaPokemon lista = new ListaPokemon(50);
    ListaPokemon removidos = new ListaPokemon(50);
    String allContent = Pokemon.readAll();
    String line;
  //Ler primeira linha
    line = sc.nextLine();
  //Laço de repeticao
    while ( ! line.equals("FIM") ) {
    //Definir dados locais
      Pokemon pk = new Pokemon();
      int id = Integer.parseInt(line);
    //Ler pokemon do determinado id
      pk.read(id, allContent);
      try                 {   lista.inserirFim(pk);                } 
      catch (Exception e) {   System.out.println(e.getMessage());  }
    //Ler proxima linha
      line = sc.nextLine();
    }
  //Definir dados de comandos
    String comando;
    int i = 0, id = 0, posicao = 0;
    int k = sc.nextInt();
  //Laço de repetição
    while (i < k) {
    //Ler instruções de comando
      comando = sc.next();
    //Testar se o comando lê duas entradas
      if (comando.charAt(1) == '*') 
        posicao = sc.nextInt();
    //Ler id do pokemon a ser manipulado
      if (comando.charAt(0) == 'I')
        id = sc.nextInt();
    //Definir dados locais
      Pokemon pk = new Pokemon ();
      pk.read(id, allContent);
    //Testar o comando (Inserir/Remover)
      if (comando.charAt(0) == 'I') {
        if (comando.charAt(1) == 'I') {
          try                 {   lista.inserirInicio(pk);    } 
          catch (Exception e) {   e.printStackTrace();        }
        } else if (comando.charAt(1) == '*') {
          try                 {   lista.inserir(pk, posicao);   }
          catch (Exception e) {   e.printStackTrace();          }
        } else if (comando.charAt(1) == 'F') {
          try                 {   lista.inserirFim(pk);   }
          catch (Exception e) {   e.printStackTrace();    }
        }
      } else if (comando.charAt(0) == 'R' ) {
        if (comando.charAt(1) == 'I') {
          try                 {   removidos.inserirFim(lista.removerInicio());    }
          catch (Exception e) {   e.printStackTrace();                            }
        } else if (comando.charAt(1) == '*') {
          try                 {   removidos.inserirFim(lista.remover(posicao));   }
          catch (Exception e) {   e.printStackTrace();                            }
        } else if (comando.charAt(1) == 'F') {
          try                 {   removidos.inserirFim(lista.removerFim());   }
          catch (Exception e) {   e.printStackTrace();                        }
        }
      }
    //Variacao
      i++;
    }
  //Mostrar os removidos
    while (removidos.n > 0) {
      try {
        Pokemon pokemon = removidos.removerInicio();
        System.out.println("(R) " + pokemon.getName());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  //Mostrar a lista resultante
    i = 0;
    while (lista.n > 0) {
      try {
        Pokemon pokemon = lista.removerInicio();
        System.out.print("[" + (i++) + "] ");
        pokemon.print();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}

 
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