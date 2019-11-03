import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class TesteGraficos {

    // Reference:
    // http://ateoriadadesevolucao.blogspot.com/2012/05/desenhando-graficos-simples-com-java-2d.html

    public static void main(String[] args) {
        // cria painel para desenhar grafico
        final DesenhandoGraficos desenhando = new DesenhandoGraficos();

        desenhando.setTitulos("Amostras por Periodo", "Mes", "Quantidade de Amostras");
        // desenhando.setParametros(30, "Jan.");
        // desenhando.setParametros(60, "Fev.");
        // desenhando.setParametros(25, "Mar.");
        // desenhando.setParametros(45, "Abr.");
        // desenhando.setParametros(115, "Mai.");
        // desenhando.setParametros(73, "Jun.");
        // desenhando.setParametros(87, "Jul.");
        // desenhando.setParametros(55, "Ago.");
        // desenhando.setParametros(19, "Set.");
        // desenhando.setParametros(90, "Out.");
        // desenhando.setParametros(45, "Nov.");
        // desenhando.setParametros(102, "Dez.");

        // cria comboBox
        final JComboBox<Object> jComboBoxGrafico = new JComboBox();
        jComboBoxGrafico.setModel(
                new javax.swing.DefaultComboBoxModel(new String[] { "Grafico em Colunas", "Grafico em Linha" }));
        jComboBoxGrafico.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desenhando.setTipoGrafico(jComboBoxGrafico.getSelectedIndex());
            }
        });

        JFrame frame = new JFrame();
        frame.add(jComboBoxGrafico, BorderLayout.NORTH);
        frame.add(desenhando);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 355);
        frame.setVisible(true);
    }
} // fim da classe TesteGraficos
