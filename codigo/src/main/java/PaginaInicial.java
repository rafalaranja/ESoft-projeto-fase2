import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaginaInicial extends JFrame {
    private JPanel painelPrincipal;
    private JButton eventosButton;
    private JButton atletasButton;
    private JButton eventosButton1;
    private JButton atletasButton1;
    private JButton estatisticasButton;
    private JButton sobreButton;

    public static void main(String[] args) {
        PaginaInicial paginaInicial = new PaginaInicial();
        paginaInicial.setVisible(true);
    }

    public PaginaInicial() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(painelPrincipal);
        pack();
    }


}
