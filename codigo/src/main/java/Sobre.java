import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sobre extends JFrame{
    private JPanel painelPrincipal;
    private JButton voltarAoMenuButton;

    public static void main(String[] args) {
        Sobre sobre = new Sobre();
        sobre.setVisible(true);
    }

    public Sobre() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(painelPrincipal);
        pack();


        voltarAoMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PaginaInicial paginaInicial = new PaginaInicial();
                paginaInicial.setVisible(true);
                dispose();
            }
        });
    }
}
