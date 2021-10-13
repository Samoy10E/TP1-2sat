import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class Pb2satTest {

    @Test
    public void testSet0() throws IOException {
        //test unitaire pour la série set0
        System.out.println("testSet0---------------");
        ArrayList<String> file;
        Graph<String> graph;
        boolean result;
        for (int k=0;k<10;k++){
            result = Pb2sat.solve("/testSet0/formula"+k);

            /*Récupération de la donnée "Satisfiable" ou "Not satisfiable" dans le fichier*/
            file = TraitementGrapheImplication.LectureFichier("/testSet0/formula" + k);
            graph = TraitementGrapheImplication.CreationGraph(file);
            System.out.println("formula"+k+" : "+result+";"+graph.getEstSatisfiableDonne());

            assertEquals(graph.getEstSatisfiableDonne(),result);
        }
    }
    @Test
    public void testSet1() throws IOException {
        //test unitaire pour la série set1
        System.out.println("testSet1---------------");
        ArrayList<String> file;
        Graph<String> graph;
        boolean result;
        for (int k=0;k<10;k++){
            result = Pb2sat.solve("/testSet1/formula"+k);

            /*Récupération de la donnée "Satisfiable" ou "Not satisfiable" dans le fichier*/
            file = TraitementGrapheImplication.LectureFichier("/testSet1/formula" + k);
            graph = TraitementGrapheImplication.CreationGraph(file);
            System.out.println("formula"+k+" : "+result+";"+graph.getEstSatisfiableDonne());

            assertEquals(graph.getEstSatisfiableDonne(),result);
        }
    }

}
