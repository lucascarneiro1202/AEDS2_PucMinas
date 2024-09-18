class Test {
  public static void main (String args [])
  {
    /*
    Arq.openWrite("exemplo.txt");

    Arq.println(1);
    Arq.println(5.3);
    Arq.println('X');
    Arq.println(true);
    Arq.println("Algoritmos");

    Arq.close();

    Arq.openRead("exemplo.txt");

    int x = Arq.readInt();
    double y = Arq.readDouble();
    char c = Arq.readChar();
    boolean b = Arq.readBoolean();
    String s = Arq.readString();

    MyIO.println(x); 
    MyIO.println(y); 
    MyIO.println(c); 
    MyIO.println(b); 
    MyIO.println(s);

    Arq.close(); 
    */

    Arq.openRead("exemplo.txt");
    
    String str = Arq.readAll();

    Arq.close();

    Arq.openWrite("copia.txt");

    Arq.print(str);

    Arq.close();

  }
}