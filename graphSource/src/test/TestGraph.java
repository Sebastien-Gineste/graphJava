package test;
import java.awt.Color;

import graph.*; 
public class TestGraph{
   public static void main(String[] args) {
    System.out.println("Helljkho world!");

    Vertex v1 = new Vertex( "v1", Color.blue);
    Vertex v2 = new Vertex( "v2", Color.RED);
    Edge e1 = new DirectedEdge( Color.BLACK, 5.0,v1, v2,0);

    System.out.println(e1.toString());
    System.out.println("Helljkho world!");

  


  }
}