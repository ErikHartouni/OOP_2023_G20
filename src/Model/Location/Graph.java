package Model.Location;

import java.util.ArrayList;

public class Graph {
    private static ArrayList<Graph> maps=new ArrayList<>();
    private ArrayList<Vertex> vertices;
    //ArrayList<ArrayList<ComplexNumuber>> setOfBestPaths=new ArrayList<>();
    public Graph(){
        maps.add(this);
        vertices=new ArrayList<>(1000);
    }
    public void add(int n){
        for(int i=0;i<n;i++)
            vertices.add(new Vertex());
    }
    public Vertex get(int i){
        return this.vertices.get(i+1);
    }
    public void setLine(int i , int j , int d){
        vertices.get(i-1).addToNeighbour(new ComplexNumber(j-1,d));
        vertices.get(j-1).addToNeighbour(new ComplexNumber(i-1,d));
    }
}
