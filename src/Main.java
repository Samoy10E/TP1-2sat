import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Graph<String> c3;

        c3 = new Graph<String>(3);
        c3.addArc(0,1,"");
        c3.addArc(1,2,"");
        c3.addArc(2,3,""); // Quel est le problème ici ?

        System.out.print(c3.toString());

        LectureFichier lf = new LectureFichier("formule-2-sat");

    }
}
