package graph2.impl;
import graph.*;
import java.awt.Color;
//enum EdgeKind { directed,undirected}
public class IncidenceArrayGraph implements Graph{
  private Vertex[] vertices; // sommmet 
  private Edge[] edges; // arête 
  private Edge[][] tab; // tab sommet 

  public IncidenceArrayGraph(Vertex[] vertices,Edge[] edges){

    this.vertices=vertices;
    this.edges=edges;
    tab = new Edge[vertices.length][];
    for (int i =0; i<vertices.length; i++){
      int k = 0;
      for(int j = 0; j<edges.length;j++){
        if(edges[j].getEnds()[0].getId() == vertices[i].getId() || edges[j].getEnds()[1].getId() == vertices[i].getId()){
          k++;
        }
      }
      tab[i] = new Edge[k];
      k = 0;
      for(int j = 0; j<edges.length;j++){
        if(edges[j].getEnds()[0].getId() == vertices[i].getId() || edges[j].getEnds()[1].getId() == vertices[i].getId()){
          tab[i][k]=edges[j];
          k++;
        }

      }  
    }
  }

  public int nbOfVertices(){
    return this.vertices.length;
  }

  public int nbOfEdges(){
    return this.edges.length;
  }

  public void addVertex(Vertex v){
    // ajout dans vertices
    Vertex [] mem =  new Vertex[this.vertices.length+1];
    for(int i = 0; i<mem.length-1;i++){
      if(this.vertices[i].getId() == v.getId()){ // sommet déjà présent 
        System.out.println("Sommet "+v.getId()+" déjà présent");
        return; // on arrête la fonction ici 
      }
      mem[i] = this.vertices[i];
    }
    mem[mem.length-1] = v;
    this.vertices = mem;

    // ajout dans tab
    Edge[][] memtab = new Edge[this.vertices.length][];
    for(int i = 0; i<memtab.length-1;i++){
      memtab[i] = this.tab[i];
    }
    memtab[memtab.length-1] = new Edge[0];
    this.tab = memtab;
  }
  
  public void addEdge(Vertex v,Vertex v1,EdgeKind edg){
    int tabSommet[] = new int [2];
    tabSommet[0] = 0;tabSommet[1] = 0;
    for(int i =0;i<this.vertices.length;i++){ // on regarde si les sommets sont présent
      if(this.vertices[i].getId() == v.getId()){
        tabSommet[0] = 1;
      }
      if(this.vertices[i].getId() == v1.getId()){
        tabSommet[1] = 1;
      }
    }
    if(tabSommet[0] == 0){ // sommet v non présent 
      addVertex(v); // on l'ajoute 
    }
    if(tabSommet[1] == 0){ // sommet v1 non présent 
      addVertex(v1); // on l'ajoute
    }
   Edge[] mem =  new Edge[this.edges.length+1];
   for(int i = 0; i<mem.length-1;i++){
      if(this.vertices[i].getId() == v.getId()){ // si on est sur un sommet de l'arête, on  (unique to each e si elle existe 
        for(int j = 0 ; j<this.tab[i].length; j++){
          if((this.tab[i][j].getEnds()[0].getId() == v.getId() && this.tab[i][j].getEnds()[1].getId() == v1.getId()) || (this.tab[i][j].getEnds()[1].getId() == v.getId() && this.tab[i][j].getEnds()[0].getId() == v1.getId())){ // arête déjà présente 
            System.out.println("arête "+v.getId()+" déjà présent");
            return; // on arrête la fonction ici 
          } 
        }
      }
      mem[i] = this.edges[i];
    }
    if(edg == EdgeKind.directed){
      mem[mem.length-1] = new DirectedEdge(Color.WHITE, 1,v, v1,0); // on l'ajoute à la fin
    }
    else{
      mem[mem.length-1] = new UndirectedEdge(Color.WHITE, 1,v,v1); // on l'ajoute à la fin
    }
    this.edges = mem;
    
    // ajout dans tab
    for(int i = 0; i<this.vertices.length;i++){
      if(this.vertices[i].getId() == v.getId() || this.vertices[i].getId() == v1.getId()){ // sommet de l'arête
        Edge [] memEdge = new Edge[this.tab[i].length+1];
        for(int j = 0;j<this.tab[i].length;j++){
          memEdge[j] = this.tab[i][j];
        }
        memEdge[memEdge.length-1]=mem[mem.length-1];
        this.tab[i] = memEdge;
      }
    }

  }
    
  
  public boolean isConnected(Vertex v1,Vertex v2){ 
    if(v1.getId() == v2.getId()){
      return true;
    }
    for(int i =0;i<this.nbOfVertices();i++){
      this.vertices[i].setColor(Color.WHITE); // on met les sommets en blanc 
    }
    return isConnectedAux(v1,v2);
  }

  public boolean isConnectedAux(Vertex v1, Vertex v2){
    boolean trouve = false;
    for(int i = 0;i<this.nbOfVertices();i++){ // pour chaque sommet 
      if(this.vertices[i].getColor().equals(Color.WHITE)){ // on l'a pas déjà traité 
        this.vertices[i].setColor(Color.GRAY); // on le met en gris 
        for(int j = 0; j<this.tab[i].length;j++){ // pour chacune de ses arêtes 
            if(this.tab[i][j].getEnds()[0].getId() == v1.getId()){ // le premier sommet de l'arrête est le v1 
              if(this.tab[i][j].getEnds()[1].getId() == v2.getId()){ // on l'a trouvé
                return true;
              }
              else{
                trouve = isConnectedAux(this.tab[i][j].getEnds()[1], v2); // le recherche 
                if(trouve){return true;}
              } 
            }
        }
        this.vertices[i].setColor(Color.BLACK);
      }
    }
    return trouve;
  }

  public boolean isConnected(){ // says whether all vertices are interconnected
    Vertex v = vertices[0];
    int i = 1;
    boolean resultat=true;
    while(i<vertices.length&&resultat){
      resultat = isConnected(v,vertices[i]);
      i++;
    }
    return resultat;
  }
  
  public Edge[] getEdges(Vertex v1, Vertex v2){
    int i = 0;
    Edge array[] = new Edge[2];
    int nbValue = 0;
    while(i<vertices.length){
      
      if(vertices[i].getId()==v1.getId()){
        int j = 0;
        while(j<tab[i].length){
          if(tab[i][j].getEnds()[0].getId()==v1.getId()&&tab[i][j].getEnds()[1].getId()==v2.getId()){
            array[nbValue]=tab[i][j];
          }
          j++;
        }
      }
      i++;
    }
    return array;
  }
  
  public Edge[] getEdges(){
    return edges;
  }

  public Vertex[] getVertices(){
    return vertices;
  }
  
  public Edge[] getNeighborEdges(Vertex v){
    int i = 0;
    int indice = -1;
    while(i<vertices.length){
      if(vertices[i].getId()==v.getId()){
        return tab[i];
      }
      i++;
    }
    return new Edge[0];
  }
}