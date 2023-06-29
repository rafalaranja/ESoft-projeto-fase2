import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class PaginaAtletas extends JFrame{
    private JPanel painelPrincipal;
    private JButton atletasButtonSide;
    private JButton estatisticasButtonSide;
    private JButton sobreButtonSide;
    private JButton eventosButtonSide;
    private JButton loginButtonSide;
    private JButton menuInicialButtonSide;
    private JLabel nomeUser;
    private JLabel fotoUser;
    private JTable table1;
    private JButton adicionarAtletasButton;
    private JButton editarAtletasButton;
    private JButton importarAtletasButton;
    private JButton eliminarAtletasButton;

    private void carregarAtletas(String[] colunas) {
        // Ler os eventos do arquivo "atletas.txt" e atualizar o modelo da tabela
        try {
            BufferedReader reader = new BufferedReader(new FileReader("atletas.txt"));
            String linha;
            DefaultTableModel modelo = (DefaultTableModel) table1.getModel();
            modelo.setRowCount(0); // Limpar os dados existentes na tabela
            modelo.addRow(colunas);
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(":");

                String dataNascimento = dados[2];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date = LocalDate.parse(dataNascimento, formatter);
                int idade = Period.between(date, LocalDate.now()).getYears();
                dados[2] = String.valueOf(idade);
                modelo.addRow(dados);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PaginaAtletas() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(painelPrincipal);
        pack();

        //////////////////////////// SIDEBAR ////////////////////////////


        // colocar a foto e nome do user logado
        if (Login.nomeUser != null){
            nomeUser.setText(Login.nomeUser); //mostrar o nome do user logado
        } else {
            nomeUser.setText("Guest");
        }
        if (!nomeUser.getText().equals("Guest")){
            loginButtonSide.setVisible(false);
        }

        ImageIcon imagemIcon = new ImageIcon("fotos/profile.png"); // Caminho para a imagem
        Image imagem = imagemIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT); // Definir tamanho da imagem
        ImageIcon icon = new ImageIcon(imagem); // Criar o ImageIcon com a imagem
        fotoUser.setIcon(icon);


        // Listeners dos botões

        eventosButtonSide.addActionListener(e -> {
            PaginaEventos paginaEventos = new PaginaEventos();
            paginaEventos.setVisible(true);
            dispose();
        });

        atletasButtonSide.addActionListener(e -> {
            PaginaAtletas paginaAtletas = new PaginaAtletas();
            paginaAtletas.setVisible(true);
            dispose();
        });

        estatisticasButtonSide.addActionListener(e -> {
            PaginaEstatisticas paginaEstatisticas = new PaginaEstatisticas();
            paginaEstatisticas.setVisible(true);
            dispose();
        });

        loginButtonSide.addActionListener(e -> {
            Login login = new Login();
            login.setVisible(true);
            dispose();
        });

        sobreButtonSide.addActionListener(e -> {
            Sobre sobre = new Sobre();
            sobre.setVisible(true);
            dispose();
        });

        menuInicialButtonSide.addActionListener(e -> {
            PaginaInicial paginaInicial = new PaginaInicial();
            paginaInicial.setVisible(true);
            dispose();
        });

        //////////////////////////// FIM DA SIDEBAR ////////////////////////////

        // Criar uma matriz de eventos (supondo que você já tenha os eventos em uma matriz/lista)
        String[][] atletas = {{"Atleta 1", "Data 1"}, {"Atleta 2", "Data 2"}, {"Atleta 3", "Data 3"}};

        // Criar um array de nomes das colunas
        String[] colunas = {"Nome", "Género", "Idade", "Equipa", "Nacionalidade"};

        DefaultTableModel modelo = new DefaultTableModel(atletas, colunas){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Impede a tabela de ser editada pelo utilizador
            }
        };

        table1.setModel(modelo);
        carregarAtletas(colunas); // Carregar os atletas do arquivo "atletas.txt"

        adicionarAtletasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ir para adicionar eventos
                AdicionarAtletas adicionarAtletas = new AdicionarAtletas();
                adicionarAtletas.setVisible(true);
                dispose();
            }
        });


        editarAtletasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditarAtletas editarAtletas = new EditarAtletas();
                editarAtletas.setVisible(true);
                dispose();
            }
        });

        eliminarAtletasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApagarAtletas apagarAtletas = new ApagarAtletas();
                apagarAtletas.setVisible(true);
                dispose();
            }
        });

        if (nomeUser.getText().equals("Guest")) {
            importarAtletasButton.setVisible(false);
            adicionarAtletasButton.setVisible(false);
            eliminarAtletasButton.setVisible(false);
            editarAtletasButton.setVisible(false);
        }
    }
}



