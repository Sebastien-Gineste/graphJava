package test;
import java.awt.Color;
import graph.*;
import graph2.impl.*;
import java.util.Arrays;
public class TestGraphImpl{
   public static void main(String[] args) {
    Vertex vertices[] = new Vertex[4];
    vertices[0]=new Vertex("test",Color.BLACK);
    vertices[1]=new Vertex("test",Color.BLACK);
    vertices[2]=new Vertex("test",Color.BLACK);
    vertices[3]=new Vertex("test",Color.BLACK);
     Edge[] edges = new Edge[4];
     edges[0]= new DirectedEdge( Color.BLACK, 5,vertices[0], vertices[1],0);
     edges[1]= new DirectedEdge( Color.BLACK, 5,vertices[1], vertices[2],1);
     edges[2]= new DirectedEdge( Color.BLACK, 5,vertices[2], vertices[0],0);
     edges[3]= new DirectedEdge( Color.BLACK, 5,vertices[0], vertices[3],1);
    IncidenceArrayGraph array = new IncidenceArrayGraph(vertices,edges);

    Vertex v1 = new Vertex("test",Color.BLACK);
    Vertex v2 = new Vertex("test",Color.BLACK);
    Vertex v3 = new Vertex("test",Color.BLACK);
    array.addEdge(v1,v2,Graph.EdgeKind.directed);
    array.addEdge(v1,v3,Graph.EdgeKind.directed);

    System.out.println("Test Graph Impl IncidenceArrayGraph");
    System.out.println(" Liste Sommets : "+Arrays.toString(array.getVertices()));
    System.out.println(" Liste Arêtes : "+Arrays.toString(array.getEdges()));
    System.out.println(" Est-ce qu'il y a bien un chemin entre (4) et (5) ? : "+Arrays.toString(array.getEdges(v1,v2)));
    System.out.println(" Est-ce qu'il y a bien un chemin entre (4) et (6) ? : "+Arrays.toString(array.getEdges(v1,v3)));
    System.out.println(" Est-ce qu'il y a bien pas de chemin entre (4) et (0) ? :"+Arrays.toString(array.getEdges(v1, vertices[0])));


  }
}