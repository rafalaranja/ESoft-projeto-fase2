import javax.swing.*;
import java.awt.*;

public class Eventos extends JFrame{
    private JPanel painelPrincipal;
    private JButton atletasButton1;
    private JButton estatisticasButton;
    private JButton sobreButton;
    private JButton eventosButton1;
    private JLabel nomeUser;
    private JLabel fotoUser;
    private JList list1;

    public static void main(String[] args) {
        Eventos eventos = new Eventos();
        eventos.setVisible(true);
    }

    public Eventos() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(painelPrincipal);
        pack();

        // colocar a foto e nome do user logado

        nomeUser.setText(Login.nomeUser); //mostrar o nome do user logado

        ImageIcon imagemIcon = new ImageIcon("fotos/profile.png"); // Caminho para a imagem
        Image imagem = imagemIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT); // Definir tamanho da imagem
        ImageIcon icon = new ImageIcon(imagem); // Criar o ImageIcon com a imagem
        fotoUser.setIcon(icon);


        // Listeners dos botões
        sobreButton.addActionListener(e -> {
            Sobre sobre = new Sobre();
            sobre.setVisible(true);
            dispose();
        });


        //mostra a lista de eventos
        String[] eventos = {"Evento 1", "Evento 2", "Evento 3"};

        list1.setListData(eventos);
    }
}

