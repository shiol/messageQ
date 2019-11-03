import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Graph {

    public static void draw(ArrayList<Double> list1, ArrayList<Double> list2, Color cor1, Color cor2) {

        final DesenhandoGraficos desenhando = new DesenhandoGraficos();

        desenhando.setTitulos("Valores de títulos", "Nome", "Valor");

        list1.forEach((x) -> {
            desenhando.setParametros(x, "$", cor1);
            list1.remove(list1.size());
        });

        list2.forEach((x) -> {
            desenhando.setParametros(x, "$", cor2);
            list2.remove(list2.size());
        });

        desenhando.setTipoGrafico(1);

        JFrame frame = new JFrame();
        frame.add(desenhando);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 355);
        frame.setVisible(true);
    }
}
