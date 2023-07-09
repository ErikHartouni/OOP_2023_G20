package Model.Location;

public class ComplexNumber {
    private int vertexIndex;
    private int distance;
    public ComplexNumber(int vertexIndex, int distance){
        this.vertexIndex=vertexIndex;
        this.distance=distance;
    }
    public int getVIndex(){
        return this.vertexIndex;
    }public int getDistance(){
        return this.distance;
    }
}
