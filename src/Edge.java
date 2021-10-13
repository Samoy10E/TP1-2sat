public class Edge<Label> {

    private int source;
    private int destination;
    private Label label;

    public Label getLabel(){return label;}
    public int src(){return source;}
    public int dst(){return destination;}

    public Edge(int from, int to, Label label) {
        this.source = from;
        this.destination = to;
        this.label = label;
    }

    public String toString(int cardinal){
        String result =  "";
        result = result.concat(TraitementGrapheImplication.convIndexToVar(source,cardinal)+ " -> ");
        result = result.concat(TraitementGrapheImplication.convIndexToVar(destination,cardinal) + ", étiquette : ");
        result = result.concat(label.toString() + "\n");
        return result;
    }
}
