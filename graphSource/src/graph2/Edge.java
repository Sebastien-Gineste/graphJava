package graph;
public abstract class Edge{
    private static int count =0;

   private int id ;
   private java.awt.Color color ;
   private double value; 
   private Vertex[] ends;

  public Edge( java.awt.Color color, double value,Vertex vertex1, Vertex vertex2){
    this.id=count;
    this.ends = new Vertex[2];
    this.color=color;
    this.value=value;
    this.ends[0] = vertex1;
    this.ends[1] = vertex2;
    count++;
  }
  
  public String toString(){
    return "|"+id+"|"+ends[0]+" --"+value+"-- "+ends[1];//"+id+" (color : "+color+", value : "+value+") {"+ends[0]+","+ends[1]+"}\n";
  }

  public Vertex[] getEnds(){
    return ends;
  }
  
}