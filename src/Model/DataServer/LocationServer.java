package Model.DataServer;

import Model.Location.ComplexNumber;
import Model.Location.Graph;
import Model.Location.Operations;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;

public class LocationServer {
    private Graph city;
    public void getFile()  {
        city=new Graph();
        // Get the class object
        Class<?> clazz = LocationServer.class;

        // Get the URL of the resource
        URL url = clazz.getResource("graph.txt");

        // Convert URL to URI and get the file path
        String filePath = null;
        try {
            filePath = String.valueOf(Paths.get(url.toURI()));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        File file = new File(filePath);//LocationServer.class.getResource("Model/DataServer/graph.txt").toString());
        try{
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            int number = Integer.parseInt(line.split("\\s+")[0]);
            city.add(number);
            line = bufferedReader.readLine();
            while(line!=null){
                city.setLine(Integer.parseInt(line.split("\\s+")[0]),
                        Integer.parseInt(line.split("\\s+")[1]),
                        Integer.parseInt(line.split("\\s+")[2]));
                line=bufferedReader.readLine();
            }

;        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String showTheShortestWayNumber(int from , int to){
        ArrayList<ComplexNumber> ans = Operations.bestPathFinder(city,from,to);
        //ArrayList<Integer>path = Operations.routeFinder(city,ans,i,j);
        for (ComplexNumber an : ans) {
            if (an.getVIndex() == 4) {
                return Integer.toString(an.getDistance());
            }
        }
        return null;
    }

    public ArrayList<Integer> showTheShortestWay(int from , int to){
        ArrayList<ComplexNumber> ans = Operations.bestPathFinder(city,from,to);
        return Operations.routeFinder(city,ans,from,to);
    }


}
