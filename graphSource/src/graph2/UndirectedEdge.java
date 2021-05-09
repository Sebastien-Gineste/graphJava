package graph;
public class UndirectedEdge extends Edge{
   private int source;
   public UndirectedEdge ( java.awt.Color color, double value,Vertex vertex1, Vertex vertex2){
      super(color,value,vertex1,vertex2);
   }

   public Vertex getSource(){
     if(source==0||source==1){
      return super.getEnds()[source];
     }
     return null;
     
   }
   

}