import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Tweet {
    private String conteudo;
    private int curtidas;

    public Tweet(String conteudo) {
        this.conteudo = conteudo;
        this.curtidas = 0;
    }

    public String getConteudo() {
        return conteudo;
    }

    public int getCurtidas() {
        return curtidas;
    }

    public void curtir() {
        curtidas++;
    }
}

public class main {
    private static ArrayList<Tweet> tweets = new ArrayList<>(); // Armazena os tweets

    public static void main(String[] args) {
        // Criar o frame principal
        JFrame frame = new JFrame("Twitter Clone");
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Configurar fonte e cores
        Font font = new Font("Arial", Font.PLAIN, 14);
        Color bgColor = new Color(245, 245, 245);
        Color buttonColor = new Color(0, 122, 255);
        Color buttonTextColor = Color.WHITE;
        Color tweetColor = new Color(255, 255, 255);
        Color tweetBorderColor = new Color(220, 220, 220);

        // Área de texto para criar novo tweet
        JTextArea tweetArea = new JTextArea();
        tweetArea.setFont(font);
        tweetArea.setBounds(20, 20, 440, 100);
        tweetArea.setLineWrap(true);
        tweetArea.setWrapStyleWord(true);
        tweetArea.setBorder(BorderFactory.createLineBorder(tweetBorderColor, 1));
        tweetArea.setBackground(tweetColor);

        // Botão para tweetar
        JButton tweetarButton = new JButton("Tweetar");
        tweetarButton.setFont(font);
        tweetarButton.setBounds(190, 130, 120, 30);
        tweetarButton.setBackground(buttonColor);
        tweetarButton.setForeground(buttonTextColor);
        tweetarButton.setFocusPainted(false);
        tweetarButton.setBorder(BorderFactory.createEmptyBorder());

        // Lista para exibir tweets
        DefaultListModel<String> tweetListModel = new DefaultListModel<>();
        JList<String> tweetList = new JList<>(tweetListModel);
        tweetList.setFont(font);
        tweetList.setBorder(BorderFactory.createLineBorder(tweetBorderColor, 1));
        JScrollPane tweetScrollPane = new JScrollPane(tweetList);
        tweetScrollPane.setBounds(20, 170, 440, 300);

        // Botão para curtir tweet
        JButton curtirButton = new JButton("Curtir Tweet");
        curtirButton.setFont(font);
        curtirButton.setBounds(180, 480, 140, 30);
        curtirButton.setBackground(buttonColor);
        curtirButton.setForeground(buttonTextColor);
        curtirButton.setFocusPainted(false);
        curtirButton.setBorder(BorderFactory.createEmptyBorder());

        // Ação ao clicar em "Tweetar"
        tweetarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String conteudo = tweetArea.getText();
                if (!conteudo.isEmpty()) {
                    Tweet novoTweet = new Tweet(conteudo);
                    tweets.add(novoTweet);
                    atualizarListaTweets(tweetListModel);
                    tweetArea.setText(""); // Limpar a área de texto após tweetar
                } else {
                    JOptionPane.showMessageDialog(frame, "Digite algo para tweetar.");
                }
            }
        });

        // Ação ao clicar em "Curtir Tweet"
        curtirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int tweetSelecionado = tweetList.getSelectedIndex();
                if (tweetSelecionado != -1) {
                    tweets.get(tweetSelecionado).curtir();
                    atualizarListaTweets(tweetListModel);
                } else {
                    JOptionPane.showMessageDialog(frame, "Selecione um tweet para curtir.");
                }
            }
        });

        // Adicionando componentes ao frame
        frame.add(tweetArea);
        frame.add(tweetarButton);
        frame.add(tweetScrollPane);
        frame.add(curtirButton);

        // Definir fundo do frame
        frame.getContentPane().setBackground(bgColor);

        // Tornar o frame visível
        frame.setVisible(true);
    }

    // Atualiza a lista de tweets exibidos na interface
    private static void atualizarListaTweets(DefaultListModel<String> model) {
        model.clear();
        for (Tweet tweet : tweets) {
            model.addElement(tweet.getConteudo() + " | Curtidas: " + tweet.getCurtidas());
        }
    }
}
