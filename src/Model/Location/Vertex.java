package Model.Location;

import java.util.ArrayList;

public class Vertex {
    //String name;
    private int number;
    private ArrayList<ComplexNumber> neighbourVertices;
    private ArrayList<ComplexNumber> analysed;

    public Vertex(){
        neighbourVertices=new ArrayList<>();
    }
    public void addToNeighbour(ComplexNumber complexNumber){
        neighbourVertices.add(complexNumber);
    }
    public ComplexNumber get(int i){
        return this.neighbourVertices.get(i);
    }
    public int size(){
        return neighbourVertices.size();
    }

    //ArrayList<ComplexNumuber> bestPathToAll=new ArrayList<>();
    /*Vertex(int num){
        number=num;
    }*/
}
