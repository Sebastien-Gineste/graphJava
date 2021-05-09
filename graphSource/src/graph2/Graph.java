package graph;
public interface Graph{
  public int nbOfVertices();
  public int nbOfEdges();
  public void addVertex(Vertex v);
  enum EdgeKind { directed,undirected};
  public void addEdge(Vertex v,Vertex v1,EdgeKind edg);// EdgeKind:  ‘directed’ or ‘undirected’
  public boolean isConnected(Vertex v1,Vertex v2);
  public boolean isConnected(); // says whether all vertices are interconnected
  public Edge[] getEdges(Vertex v1, Vertex v2);  // give edge(s) connecting these vertice
  public Edge[] getEdges();  // give all edges of the graph
  public Vertex[] getVertices(); // give all vertices of the graph
  public Edge[] getNeighborEdges(Vertex v);// give edges connected to this vertex
}