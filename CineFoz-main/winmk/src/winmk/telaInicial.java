package winmk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

@SuppressWarnings({ "serial" })
public class telaInicial extends JFrame 
{
	private JButton[][] assentos = new JButton[5][10];
	
	public telaInicial() 
    {
        telaLogin(); // Exibe a tela inicial assim que a janela é criada
    }
    
	
	public void telaLogin() {
	    setTitle("🎬 CineFoz - Login");
	    setSize(500, 400);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
	    setLayout(new BorderLayout());

	    // Painel de fundo
	    JPanel background = new JPanel();
	    background.setBackground(new Color(30, 30, 30));
	    background.setLayout(new GridBagLayout());
	    add(background, BorderLayout.CENTER);

	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.insets = new Insets(10, 10, 10, 10);
	    gbc.fill = GridBagConstraints.HORIZONTAL;

	    // Logo
	    JLabel logo = new JLabel("🎬 CineFoz", SwingConstants.CENTER);
	    logo.setForeground(new Color(255, 215, 0)); // Dourado
	    logo.setFont(new Font("SansSerif", Font.BOLD, 32));
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.gridwidth = 2;
	    background.add(logo, gbc);

	    // Nome de usuário
	    JTextField txtNome = new JTextField();
	    txtNome.setFont(new Font("SansSerif", Font.PLAIN, 18));
	    txtNome.setToolTipText("Usuário: admin");
	    txtNome.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Usuário", 0, 0, new Font("SansSerif", Font.PLAIN, 14), Color.WHITE));
	    txtNome.setBackground(new Color(50, 50, 50));
	    txtNome.setForeground(Color.WHITE);
	    gbc.gridy = 1;
	    gbc.gridwidth = 2;
	    background.add(txtNome, gbc);

	    // Senha
	    JPasswordField txtSenha = new JPasswordField();
	    txtSenha.setFont(new Font("SansSerif", Font.PLAIN, 18));
	    txtSenha.setToolTipText("Senha: 1234");
	    txtSenha.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Senha", 0, 0, new Font("SansSerif", Font.PLAIN, 14), Color.WHITE));
	    txtSenha.setBackground(new Color(50, 50, 50));
	    txtSenha.setForeground(Color.WHITE);
	    gbc.gridy = 2;
	    background.add(txtSenha, gbc);

	    // Botão de login
	    JButton btnLogin = new JButton("Entrar");
	    btnLogin.setFont(new Font("SansSerif", Font.BOLD, 18));
	    btnLogin.setBackground(new Color(255, 215, 0));
	    btnLogin.setForeground(Color.BLACK);
	    btnLogin.setFocusPainted(false);
	    gbc.gridy = 3;
	    gbc.gridwidth = 2;
	    background.add(btnLogin, gbc);

	    // Ação do botão
	    btnLogin.addActionListener(e -> {
	        String nome = txtNome.getText();
	        String senha = new String(txtSenha.getPassword());

	        if (nome.equals("admin") && senha.equals("1234")) {
	            getContentPane().removeAll();
	            Inicio(); // Chamada da tela principal
	            revalidate();
	            repaint();
	        } else {
	            JOptionPane.showMessageDialog(this, "Nome ou senha incorretos.", "Erro", JOptionPane.ERROR_MESSAGE);
	        }
	    });

	    // Deixa a janela visível
	    setVisible(true);
	}

	public void Inicio() 
	{
	    // Nome do painel
	    setTitle("CineFoz");
	    setSize(900, 700);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
	    setLayout(new BorderLayout());

	    // Topo com logotipo
	    JPanel body = new JPanel(new BorderLayout());
	    body.setBackground(new Color(40, 40, 40));
	    body.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));
	    JLabel logo = new JLabel("🎬 CineFoz");
	    logo.setForeground(Color.WHITE);
	    logo.setFont(new Font("Arial", Font.BOLD, 26));
	    body.add(logo, BorderLayout.WEST);
	    add(body, BorderLayout.NORTH);

	    // Painel com GridLayout horizontal para os pôsteres
	    JPanel painelFilmes = new JPanel(new GridLayout(1, 0, 20, 0));
	    painelFilmes.setBackground(new Color(22, 22, 22));

	    String[][] filmes = 
	    {
	        { "/imgs/duna.jpg", "Duna" },
	        { "/imgs/avengers.jpg", "Vingadores" },
	        { "/imgs/matrix.jpg", "Matrix" },
	        { "/imgs/origem.jpg", "Origem" }
	    };

	    for (String[] filme : filmes) 
	    {
	        String imgCaminho = filme[0];
	        String titulo = filme[1];

	        URL recurso = getClass().getResource(imgCaminho);
	        if (recurso == null) 
	        {
	            System.err.println("Imagem não encontrada: " + imgCaminho);
	            continue;
	        }

	        ImageIcon ogIcon = new ImageIcon(recurso);
	        Image imgP = ogIcon.getImage().getScaledInstance(300, 450, Image.SCALE_SMOOTH);
	        ImageIcon iconP = new ImageIcon(imgP);

	        Image imgG = ogIcon.getImage().getScaledInstance(320, 480, Image.SCALE_SMOOTH);
	        ImageIcon iconG = new ImageIcon(imgG);

	        JLabel poster = new JLabel(iconP);
	        poster.setToolTipText(titulo);
	        poster.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
	        poster.setPreferredSize(new Dimension(320, 480));

	        poster.addMouseListener(new java.awt.event.MouseAdapter() 
	        {
	            public void mouseEntered(java.awt.event.MouseEvent evt) 
	            {
	                poster.setIcon(iconG);
	            }

	            public void mouseExited(java.awt.event.MouseEvent evt) 
	            {
	                poster.setIcon(iconP);
	            }

	            public void mouseClicked(java.awt.event.MouseEvent evt) 
	            {
	                getContentPane().removeAll();
	                selecionarFilme(imgCaminho, titulo);
	                revalidate();
	                repaint();
	            }
	        });

	        painelFilmes.add(poster);
	    }

	    // Scroll automático horizontal
	    JScrollPane scroll = new JScrollPane(painelFilmes,
	            JScrollPane.VERTICAL_SCROLLBAR_NEVER,
	            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    scroll.setBorder(BorderFactory.createEmptyBorder());
	    scroll.getHorizontalScrollBar().setUnitIncrement(20);

	    // Painel central
	    JPanel painelCentral = new JPanel(new BorderLayout());
	    painelCentral.setBackground(new Color(20, 20, 20));
	    painelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	    painelCentral.add(scroll, BorderLayout.CENTER);

	    add(painelCentral, BorderLayout.CENTER);

	    getContentPane().setBackground(new Color(20, 20, 20));
	    setVisible(true);
	}

    public void reservas() 
    {
        setTitle("CineFoz - Escolha de Assentos");
        setLayout(new BorderLayout());

        JPanel mainWin = new JPanel(new BorderLayout());

        JLabel titulo = new JLabel("Escolha seus assentos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        mainWin.add(titulo, BorderLayout.NORTH);

        JPanel painelAssentos = new JPanel(new GridLayout(5, 10, 5, 5));
        for (int i = 0; i < 5; i++) 
        {
            for (int j = 0; j < 10; j++) 
            {
                JButton botao = new JButton((char)('A' + i) + String.valueOf(j + 1));
                botao.setBackground(Color.LIGHT_GRAY);
                botao.addActionListener(e -> 
                {
                    if (botao.getBackground() == Color.GREEN) 
                    {
                    	botao.setBackground(Color.LIGHT_GRAY);
                    }
                    else 
                    {
                        botao.setBackground(Color.GREEN);
                    }
                });
                assentos[i][j] = botao;
                painelAssentos.add(botao);
            }
        }

        mainWin.add(painelAssentos, BorderLayout.CENTER);

        JButton confirmar = new JButton("Confirmar Reserva");
        confirmar.setFont(new Font("Arial", Font.BOLD, 16));
        confirmar.addActionListener(e -> 
        {
            StringBuilder selecionados = new StringBuilder("Assentos selecionados:\n");
            for (int i = 0; i < 5; i++) 
            {
                for (int j = 0; j < 10; j++) 
                {
                    if (assentos[i][j].getBackground() == Color.GREEN) 
                    {
                        selecionados.append(assentos[i][j].getText()).append(" ");
                    }
                }
            }

            JOptionPane.showMessageDialog(this, selecionados.toString());

            // Volta para a tela inicial após confirmar
            getContentPane().removeAll();
            Inicio();
            revalidate();
            repaint();
        });

        mainWin.add(confirmar, BorderLayout.SOUTH);
        add(mainWin, BorderLayout.CENTER);
        revalidate();
        repaint();
    }   
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(() -> 
        {
            telaInicial tela = new telaInicial();
            tela.setExtendedState(JFrame.MAXIMIZED_BOTH); // Janela maximizada (mantém barra)
            tela.setVisible(true); // Exibe a janela
        });
    }
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            URL resource = getClass().getResource(imagePath);
            if (resource != null) {
                backgroundImage = new ImageIcon(resource).getImage();
            } else {
                System.err.println("Imagem de fundo não encontrada: " + imagePath);
            }
        }
    }
    
}