import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import java.lang.Double;

public class DesenhandoGraficos extends JPanel {

    // Reference:
    // http://ateoriadadesevolucao.blogspot.com/2012/05/desenhando-graficos-simples-com-java-2d.html

    private static final long serialVersionUID = 1L;
    private int altura = 200; // altura do grafico
    private int espaco = 30; // espaco entre valores
    private int margem = 30;
    private List<String> rotulos = new ArrayList<String>();
    private List<Double> valores = new ArrayList<Double>();
    private Color cor;
    private int tipoGrafico; // 0 - Colunas; 1 - Linha
    private String tituloGrafico;
    private String tituloHorizontal;
    private String tituloVertical;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.setBackground(Color.WHITE);

        int tamanhoLinha = rotulos.size() * espaco + 50; // comprimento da linha horizontal

        // desenha linha horizontal
        g.setColor(Color.BLACK);
        g.drawLine(margem, altura + margem, tamanhoLinha, altura + margem);

        for (int i = 0; i < rotulos.size(); i++) {
            // desenha valores
            g.setColor(Color.BLACK);
            g.drawString(rotulos.get(i), i * espaco + 40, altura + 55);

            // desenha tracos da linha horizontal
            g.drawLine((i + 1) * espaco + 25, altura + margem, (i + 1) * espaco + 25, altura + 40);

            // desenha valores
            int valor = (int) (valores.get(i) * 10000);
            g.drawString("" + valores.get(i), (i + 1) * espaco + 15, (altura + margem) - valor - 5);

            // desenha graficos
            g.setColor(cor);
            switch (tipoGrafico) {
            case 0: // grafico em colunas
                g.fillRect((i + 1) * espaco + 15, (altura + margem) - valor, 20, valor);
                break;
            case 1: // grafico em linha
                if (i > 0) {
                    Double aux = valores.get(i - 1) * 10000;
                    int valor2 = aux.intValue();
                    g.drawLine(i * espaco + 25, (altura + margem) - valor2, (i + 1) * espaco + 25,
                            (altura + margem) - valor);
                }

                g.fillOval((i + 1) * espaco + 22, (altura + margem) - valor - 3, 7, 7);
                break;
            } // fim do switch
        } // fim do for

        // titulo do grafico
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        g.drawString(tituloGrafico, margem, margem);

        // titulo horizontal
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        g.drawString(tituloHorizontal, margem, altura + margem + 45);

        Graphics2D g2d = (Graphics2D) g; // faz conversão de g para Graphics2D

        // titulo vertical
        g2d.setColor(Color.BLACK);
        // rotaciona o sistema de coordenadas (gira -90 graus)
        g2d.rotate(Math.PI / -2.0); // PI equivale a 180 graus
        g2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        g2d.drawString(tituloVertical, -30 - altura, margem - 5);

    } // fim do metodo paintComponent

    public void setTitulos(String tituloGrafico, String tituloHorizontal, String tituloVertical) {
        this.tituloGrafico = tituloGrafico;
        this.tituloHorizontal = tituloHorizontal;
        this.tituloVertical = tituloVertical;
    }

    public void setParametros(Double valor, String rotulo, Color cor) {
        valores.add(valor);
        rotulos.add(rotulo);
        this.cor = cor;
    } // fim do metodo setParametros

    public void setTipoGrafico(int indice) {
        tipoGrafico = indice;

        repaint();
    } // fim do metodo setTipoGrafico
} // fim da classe DesenhandoGraficos
