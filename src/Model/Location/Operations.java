package Model.Location;

import java.util.ArrayList;

public class Operations {

    static int onHoldChooser(ArrayList<ComplexNumber> onHold) {
        //System.out.println(onHold.size());
        int index = 0;
        int min = onHold.get(index).getDistance();
        for (int i = 1; i < onHold.size(); i++) {
            if (onHold.get(i).getDistance() < min) {
                index = i;
                min = onHold.get(index).getDistance();
            }
        }
        return index;
    }


    static void NeighbourFinder(Graph graph, int index, ArrayList<ComplexNumber> onHold, ArrayList<ComplexNumber> analysed) {
        analysed.add(onHold.get(index));
        onHold.remove(index);
        int distance = analysed.get(analysed.size() - 1).getDistance();
        int vertex = analysed.get(analysed.size() - 1).getVIndex();
        //System.out.println(vertex);
        for (int i = 0; i < graph.get(vertex).size(); i++) {
            boolean error1 = false;
            boolean error2 = false;
            for (int j = 0; j < onHold.size(); j++) {
                if (graph.get(vertex).get(i).getVIndex() == onHold.get(j).getVIndex()) {
                    if (graph.get(vertex).get(i).getDistance() + distance > onHold.get(j).getDistance()) {
                        error1 = true;
                    } else {

                        onHold.remove(j);
                        //onHold.add();
                    }
                }
            }
            for (int k = 0; k < analysed.size(); k++) {
                if (graph.get(vertex).get(i).getVIndex() == analysed.get(k).getVIndex()) {
                    error2 = true;
                }
            }
            if (!error1 && !error2) {
                //System.out.println(distance);
                ComplexNumber complexNumber = new ComplexNumber(graph.get(vertex).get(i).getVIndex(), graph.get(vertex).get(i).getDistance() + distance);
                onHold.add(complexNumber);
            }

        }
    }

    public static ArrayList<ComplexNumber> bestPathFinder(Graph graph, int origin, int destination) {
        ArrayList<ComplexNumber> analysed = new ArrayList<>();
        ArrayList<ComplexNumber> onHold=new ArrayList<>();
        ComplexNumber complexNumber = new ComplexNumber(origin, 0);
        analysed.add(complexNumber);
        for (int i = 0; i < graph.get(origin).size(); i++) {
            ComplexNumber complexNumber1 = new ComplexNumber(graph.get(origin).get(i).getVIndex(), graph.get(origin).get(i).getDistance());
            onHold.add(complexNumber1);
        }
        boolean reachedEnd = false;

        while (!reachedEnd) {
            if (analysed.get(analysed.size() - 1).getVIndex() == destination) {
                reachedEnd = true;
            }
            if (onHold.size() > 0) {
                Operations.NeighbourFinder(graph, Operations.onHoldChooser(onHold), onHold, analysed);
            }

        }
        return analysed;
    }


    public static ArrayList<Integer> routeFinder(Graph graph, ArrayList<ComplexNumber> analysed, int origin,int destination) {

        ArrayList<Integer> path= new ArrayList<>();
        int vertex = Operations.analysedMemberFinder(analysed,destination).getVIndex();
        path.add(vertex);
        int distance = Operations.analysedMemberFinder(analysed,destination).getDistance();

        while (!path.contains(origin)) {
            System.out.println("sth");
            System.out.println(origin);
            System.out.println(path);
            int distanceCheck;
            for (int j = 0; j < graph.get(vertex).size(); j++) {
                distanceCheck = distance - graph.get(vertex).get(j).getDistance();
                ComplexNumber complexNumber = Operations.analysedMemberFinder(analysed, graph.get(vertex).get(j).getVIndex());
                if(complexNumber!=null) {
                    if (distanceCheck == complexNumber.getDistance()) {
                        System.out.println(complexNumber.getVIndex());
                        path.add(complexNumber.getVIndex());
                        vertex = complexNumber.getVIndex();
                        distance = complexNumber.getDistance();
                    }
                }
            }

        }
        ArrayList<Integer> finalPath=new ArrayList<>();
        for(int i=path.size()-1;i>=0;i--){
            finalPath.add(path.get(i));
        }

        return finalPath;


    }
    static ComplexNumber analysedMemberFinder(ArrayList<ComplexNumber> analysed,int vertex){
        for(int i=0;i<analysed.size();i++){
            if(analysed.get(i).getVIndex()==vertex){
                ComplexNumber complexNumber=new ComplexNumber(analysed.get(i).getVIndex(),analysed.get(i).getDistance());
                return complexNumber;
            }
        }
        return null;
    }
}
