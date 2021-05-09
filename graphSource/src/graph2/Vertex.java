package graph;
import java.awt.Color;
public class Vertex {
  private static int count =0;
   private  int id;
   private  Object info;
   private java.awt.Color color ;
   
   public Vertex(Object info,java.awt.Color color){
     this.id=count;
     this.info=info;
     this.color=color;
     count++;
   }

   public int getId(){
     return this.id;
   }

   public void setColor(Color c1){
     this.color = c1;
   }
   
   public Color getColor(){
     return this.color; 
   }

   public String toString(){
     return "("+id+")";
   }
}