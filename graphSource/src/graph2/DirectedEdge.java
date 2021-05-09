package graph;
public class DirectedEdge extends Edge{
   private int source;
   public DirectedEdge ( java.awt.Color color, double value,Vertex vertex1, Vertex vertex2,int source){
      super(color,value,vertex1,vertex2);
      this.source=source;
   }

   public Vertex getSource(){
     if(source==0||source==1){
      return super.getEnds()[source];
     }
     return null;
     
   }
   
   public Vertex getSink(){
     if(source==0){
      return super.getEnds()[1];
     }
     else if(source==1){
      return super.getEnds()[0];
     }
     return null;
   }

  public String toString(){
    if(source == 0){
        return "=> "+super.toString();
    }
    else{
      return "<= "+super.toString();
    }
  }
}