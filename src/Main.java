import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Dictionary;

public class Main {
    public static void main(String[] args) throws IOException {

        int k = 0;
        ArrayList<String> file = TraitementGraphe.LectureFichier("/testSet1/formula" + k);
        Graph<String> graph = TraitementGraphe.CreationGraph(file);
        boolean result = Kosaraju.estSatifiable(graph);
        System.out.println(graph);
        System.out.println(result);

        file = TraitementGraphe.LectureFichier("formule-2-sat");
        graph = TraitementGraphe.CreationGraph(file);
        result = Kosaraju.estSatifiable(graph);
        System.out.println(graph);
        System.out.println(result);

    }
}
