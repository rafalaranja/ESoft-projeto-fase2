import javax.swing.*;

public class PaginaInicial extends JFrame {
    private JPanel painelPrincipal;
    private JButton eventosButton;
    private JButton atletasButton;

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
