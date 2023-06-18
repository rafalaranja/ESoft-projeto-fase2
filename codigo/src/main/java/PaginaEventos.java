import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class PaginaEventos extends JFrame{
    private JPanel painelPrincipal;
    private JButton atletasButton1;
    private JButton estatisticasButton;
    private JButton sobreButton;
    private JButton eventosButton;
    private JLabel nomeUser;
    private JLabel fotoUser;
    private JTable table1;
    private JButton importarEventosButton;
    private JButton adicionarEventosButton;
    private JButton loginButtonSide;

    private void carregarEventos() {
        // Ler os eventos do arquivo "eventos.txt" e atualizar o modelo da tabela
        try {
            BufferedReader reader = new BufferedReader(new FileReader("eventos.txt"));
            String linha;
            DefaultTableModel modelo = (DefaultTableModel) table1.getModel();
            modelo.setRowCount(0); // Limpar os dados existentes na tabela
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(":");
                modelo.addRow(dados);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public PaginaEventos() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(painelPrincipal);
        pack();

        // colocar a foto e nome do user logado

        String username = "Guest";

        if (Login.nomeUser != null){
            nomeUser.setText(Login.nomeUser); //mostrar o nome do user logado
        } else {
            nomeUser.setText(username);
        }

        if (nomeUser.getText().equals("Guest")){
            adicionarEventosButton.setVisible(false);
            importarEventosButton.setVisible(false);
        } else {
            loginButtonSide.setVisible(false);
        }

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

        loginButtonSide.addActionListener(e -> {
            Login login = new Login();
            login.setVisible(true);
            dispose();
        });

        // Criar uma matriz de eventos (supondo que você já tenha os eventos em uma matriz/lista)
        String[][] eventos = {{"Evento 1", "Data 1"}, {"Evento 2", "Data 2"}, {"Evento 3", "Data 3"}};

        // Criar um array de nomes das colunas
        String[] colunas = {"Nome", "Arte Marcial", "Data Inicial"};


        // Criar um DefaultTableModel com os eventos e colunas
        DefaultTableModel modelo = new DefaultTableModel(eventos, colunas){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Impede a tabela de ser editada pelo utilizador
            }
        };

        table1.setModel(modelo);
        carregarEventos(); // Carregar os eventos do arquivo "eventos.txt"


        adicionarEventosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ir para adicionar eventos
                AdicionarEventos adicionarEventos = new AdicionarEventos();
                adicionarEventos.setVisible(true);
                dispose();
            }
        });
    }




}

