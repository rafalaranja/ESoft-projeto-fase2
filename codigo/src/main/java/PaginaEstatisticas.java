import javax.swing.*;

public class PaginaEstatisticas extends JFrame {

    private JPanel painelPrincipal;
    private JButton voltarAoMenuButton;

    public PaginaEstatisticas() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(painelPrincipal);
        pack();





        //Listeners
        voltarAoMenuButton.addActionListener(e -> {
            PaginaInicial paginaInicial = new PaginaInicial();
            paginaInicial.setVisible(true);
            dispose();
        });
    }
}
