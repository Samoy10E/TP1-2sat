import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class KosarajuTest {

    @Test
    public void testSet0() throws IOException {
        System.out.println("testSet0---------------");
        ArrayList<String> file;
        Graph<String> graph;
        boolean result;
        for (int k=0;k<10;k++){
            file = TraitementGraphe.LectureFichier("/testSet0/formula" + k);
            graph = TraitementGraphe.CreationGraph(file);
            result = Kosaraju.estSatifiable(graph);
            System.out.println("formula"+k+" : "+result+";"+graph.getEstSatisfiable());
            assertEquals(graph.getEstSatisfiable(),result);
        }
    }
    @Test
    public void testSet1() throws IOException {
        System.out.println("testSet1---------------");
        ArrayList<String> file;
        Graph<String> graph;
        boolean result;
        for (int k=0;k<10;k++){
            file = TraitementGraphe.LectureFichier("/testSet1/formula" + k);
            graph = TraitementGraphe.CreationGraph(file);
            result = Kosaraju.estSatifiable(graph);
            System.out.println("formula"+k+" : "+result+";"+graph.getEstSatisfiable());
            assertEquals(graph.getEstSatisfiable(),result);
        }
    }

    @Test
    public void testFormula() throws IOException {
        System.out.println("testFormula-----------");
        ArrayList<String> file = TraitementGraphe.LectureFichier("formula");
        Graph graph = TraitementGraphe.CreationGraph(file);
        boolean result = Kosaraju.estSatifiable(graph);
        System.out.println("formula : "+result+";"+graph.getEstSatisfiable());
        assertEquals(graph.getEstSatisfiable(),result);
    }
}
