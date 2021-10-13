import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Dictionary;

public class Main {
    public static void main(String[] args) throws IOException {

        boolean resultat = Pb2sat.solve("formula");

        System.out.println("résultat du fichier formula :" + resultat);

    }
}
