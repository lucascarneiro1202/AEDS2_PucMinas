class Ponto 
{
  private double x;
  private double y;
  private int id;
  private static int nextID = 0;

  public Ponto ()
  {
    setx(0);
    sety(0);
    this.id = this.nextID;
    this.nextID++;
  }

  public Ponto (double x, double y)
  {
    setx(x);
    sety(y);
    this.id = nextID;
    nextID++;
  }

  public double getx ()
  {
    return this.x;
  }

  public void setx (double x)
  {
    this.x = x;
  }

  public double gety ()
  {
    return this.y;
  }

  public void sety (double y)
  {
    this.y = y;
  }

  public double dist (Ponto p)
  {
    return Math.sqrt ( Math.pow(this.getx() - p.getx(), 2) + Math.pow(this.gety() - p.gety(), 2) );
  }

  public double dist (double a, double b)
  {
    return Math.sqrt ( Math.pow(this.getx() - a, 2) + Math.pow(this.gety() - b, 2) );
  }

  public static double dist (Ponto p1, Ponto p2)
  {
    return Math.sqrt ( Math.pow(p1.getx() - p2.getx(), 2) + Math.pow(p1.getx() - p2.getx(), 2) );
  }

  public static double dist (double a, double b, double c, double d)
  {
    return Math.sqrt( Math.pow(a - c, 2) + Math.pow(b - d, 2) );
  }

  public static boolean isTriangulo ( Ponto p1, Ponto p2, Ponto p3 )
  {
    boolean resp = true;
    double d12 = dist(p1, p2);
    double d13 = dist(p1, p3);
    double d23 = dist(p2, p3);
    if (d12 > d13 && d12 > d23)
      resp = d13 + d23 > d12;
    else if (d13 > d12 && d13 > d23)
      resp = d12 + d23 > d13;
    else if (d23 > d12 && d23 > d13)
      resp = d12 + d13 > d23;
    return resp;
  }

  public double getAreaRetangulo (Ponto p)
  {
    return p.getx() * p.gety();
  }

  public int getID ()
  {
    return this.id;
  }

  public static int getNextID ()
  {
    return nextID;
  }
}