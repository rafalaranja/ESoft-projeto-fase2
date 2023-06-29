import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class EditarProvas extends JFrame{
    private JPanel painelPrincipal;
    private JButton atletasButtonSide;
    private JButton estatisticasButtonSide;
    private JButton sobreButtonSide;
    private JButton eventosButtonSide;
    private JButton loginButtonSide;
    private JButton menuInicialButtonSide;
    private JButton provasButtonSide;
    private JLabel nomeUser;
    private JLabel fotoUser;
    private JLabel adicionarEventosButton;
    private JButton editarButton;
    private JTextField nomeTextField;
    private JComboBox generoComboBox;
    private JComboBox provaComboBox;
    private JComboBox catPesoComboBox;
    private JComboBox eventoComboBox;

    private void carregarProvas() {
        // Ler as provas do arquivo "provas.txt" e atualizar o modelo da ComboBox
        try {
            BufferedReader reader = new BufferedReader(new FileReader("provas.txt"));
            String linha;
            DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(":");
                modelo.addElement(dados[0].trim());
            }
            reader.close();
            provaComboBox.setModel(modelo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarEventos() {
        // Ler os eventos do arquivo "eventos.txt" e atualizar o modelo da ComboBox
        try {
            BufferedReader reader = new BufferedReader(new FileReader("eventos.txt"));
            String linha;
            DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(":");
                modelo.addElement(dados[0].trim());
            }
            reader.close();
            eventoComboBox.setModel(modelo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public EditarProvas() {
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

        provasButtonSide.addActionListener(e ->  {
            PaginaProvas paginaProvas = new PaginaProvas();
            paginaProvas.setVisible(true);
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

        provaComboBox.setSelectedItem(null);
        carregarProvas();
        carregarEventos();

         DefaultComboBoxModel<String> generoComboBoxModel = new DefaultComboBoxModel<>(
                new String[]{"Masculino", "Feminino"});

         DefaultComboBoxModel<String> catPesoComboBoxModel = new DefaultComboBoxModel<>(
                new String[]{"50", "65", "80", "95", "110"});

        generoComboBox.setModel(generoComboBoxModel);
        catPesoComboBox.setModel(catPesoComboBoxModel);


        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String provaSelecionada = (String) provaComboBox.getSelectedItem();
                if (provaSelecionada != null) {
                    // Implemente a lógica para editar o evento com as informações dos campos
                    String nome = nomeTextField.getText();
                    String genero = (String) generoComboBox.getSelectedItem();
                    String catPeso = (String)catPesoComboBox.getSelectedItem();
                    String evento = (String)eventoComboBox.getSelectedItem();

                    try {
                        File arquivoProvas = new File("provas.txt");
                        File arquivoTemp = new File("temp.txt");

                        BufferedReader reader = new BufferedReader(new FileReader(arquivoProvas));
                        PrintWriter writer = new PrintWriter(new FileWriter(arquivoTemp));

                        String linha;
                        while ((linha = reader.readLine()) != null) {
                            String[] dados = linha.split(":");
                            String nomeProva = dados[0].trim();
                            if (nomeProva.equals(provaSelecionada)) {
                                // Editar a linha correspondente ao evento encontrado
                                linha = nome + ":" + evento + ":" + genero + ":" + catPeso;
                            }
                            writer.println(linha);
                        }

                        reader.close();
                        writer.close();

                        // Substituir o arquivo original pelo arquivo temporário
                        if (arquivoProvas.delete() && arquivoTemp.renameTo(arquivoProvas)) {
                            JOptionPane.showMessageDialog(null, "Prova editada com sucesso!");
                            PaginaProvas paginaProvas = new PaginaProvas();
                            paginaProvas.setVisible(true);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Falha ao editar a prova.");
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erro ao acessar o arquivo provas.txt");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Nenhuma prova selecionada.");
                }
            }
        });
        provaComboBox.setSelectedItem(null);   //por predefinição não tem nenhuma prova selecionado
        provaComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String provaSelecionada = (String) provaComboBox.getSelectedItem();
                if (provaSelecionada != null) {
                    //obter as informações do evento selecionado
                    String[] informacoes = obterInformacoesProva(provaSelecionada);

                    if (informacoes != null && informacoes.length >= 4) {
                        nomeTextField.setText(informacoes[0]);
                        eventoComboBox.setSelectedItem(informacoes[1]);
                        generoComboBox.setSelectedItem(informacoes[2]);
                        catPesoComboBox.setSelectedItem(informacoes[3]);
                    }
                } else {
                    // Limpar os campos se nenhum evento estiver selecionado
                    nomeTextField.setText("");
                    eventoComboBox.setSelectedItem(null);
                    generoComboBox.setSelectedItem(null);
                    catPesoComboBox.setSelectedItem(null);
                }
            }

            private String[] obterInformacoesProva(String evento) {
                // obter as informações da prova com base no nome da prova
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("provas.txt"));
                    String linha;
                    while ((linha = reader.readLine()) != null) {
                        String[] dados = linha.split(":");
                        if (dados.length >= 5 && dados[0].trim().equals(evento)) {
                            reader.close();
                            return dados;
                        }
                    }
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }
}
