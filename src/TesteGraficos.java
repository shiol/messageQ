import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JFrame;

public class TesteGraficos {

	// Reference:
	// http://ateoriadadesevolucao.blogspot.com/2012/05/desenhando-graficos-simples-com-java-2d.html

	public static void main(String[] args) {
		// cria painel para desenhar grafico
		final DesenhandoGraficos desenhando = new DesenhandoGraficos();

		desenhando.setTitulos("Amostras por Periodo", "Mes", "Quantidade de Amostras");
		desenhando.setParametros(30, "Jan.", Color.BLUE);
		desenhando.setParametros(60, "Fev.", Color.CYAN);
		desenhando.setParametros(25, "Mar.", Color.CYAN);
		desenhando.setParametros(45, "Abr.", Color.CYAN);
		desenhando.setParametros(115, "Mai.", Color.CYAN);
		desenhando.setParametros(73, "Jun.", Color.GREEN);
		desenhando.setParametros(87, "Jul.", Color.CYAN);
		desenhando.setParametros(55, "Ago.", Color.CYAN);
		desenhando.setParametros(19, "Set.", Color.GRAY);
		desenhando.setParametros(90, "Out.", Color.CYAN);
		desenhando.setParametros(45, "Nov.", Color.CYAN);
		desenhando.setParametros(102, "Dez.", Color.PINK);

		// cria comboBox
		final JComboBox<Object> jComboBoxGrafico = new JComboBox<Object>();
		jComboBoxGrafico.setModel(new javax.swing.DefaultComboBoxModel<Object>(
				new String[] { "Grafico em Colunas", "Grafico em Linha" }));
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
		frame.setSize(1000, 500);
		frame.setVisible(true);
	}
} // fim da classe TesteGraficos
