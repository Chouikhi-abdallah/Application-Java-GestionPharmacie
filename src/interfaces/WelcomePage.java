package interfaces;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import dao.SingletonConnection;
import dao.UtilisateursDao;
import modeles.Utilisateur;

//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;

public class WelcomePage extends JFrame implements ActionListener {
private static final long serialVersionUID = 1L;
private CardLayout card_Pan;
private JPanel cardPanel;
private JTextField utilisateurfield;
private JTextField passfield;
private JButton connect;

public WelcomePage() { // c'est la premier page 
	 	setTitle("JPHARMACY");       // Mise en titre de la fenetre
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    // LOADING DES IMAGES
	    ImageIcon background_Image=new ImageIcon(Objects.requireNonNull(WelcomePage.class.getResource("Back.png")));
	    ImageIcon background_Image_2=new ImageIcon(Objects.requireNonNull(WelcomePage.class.getResource("Back_2.png")));
	    //ImageIcon background_Image_3=new ImageIcon(Objects.requireNonNull(WelcomePage.class.getResource("Back_III.png")));
	    ImageIcon background_Image_4=new ImageIcon(Objects.requireNonNull(WelcomePage.class.getResource("Back4.png")));

	    Image icon=Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon_pharma.png"));
	    //LOADING DES IMAGES
	    setIconImage(icon);
	     card_Pan=new CardLayout();
	     cardPanel=new JPanel(card_Pan);
	    //First Page
	    JPanel FirstPage_panel=new JPanel(){
	        private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            g.drawImage(background_Image.getImage(), 0, 0, getWidth(), getHeight(), this);
	        }
	    };
	    //Button 
	    JButton savoir_plus = new JButton("New button");
	    savoir_plus.setSelectedIcon(new ImageIcon(WelcomePage.class.getResource("/interfaces/Screenshot 2023-04-28 at 10.14.15.png")));
	    savoir_plus.setIcon(new ImageIcon(WelcomePage.class.getResource("/interfaces/Screenshot 2023-04-28 at 10.14.15.png")));
	    savoir_plus.setBounds(48, 378, 234, 91);
	    savoir_plus.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	    savoir_plus.addActionListener(this);
	    FirstPage_panel.setLayout(null);
	    FirstPage_panel.add(savoir_plus);
	    getContentPane().add(cardPanel);
	    
	    cardPanel.add(FirstPage_panel,"firstPage");
	    //Second Page
	    JPanel SecondPagePanel=new JPanel() {

			private static final long serialVersionUID = 1L;
			protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            g.drawImage(background_Image_2.getImage(), 0, 0, getWidth(), getHeight(), this);
	        }
	    
	    };
	    
	    cardPanel.add(SecondPagePanel,"secondPage" );
	    SecondPagePanel.setLayout(null);
	    JButton Connexion = new JButton("New button");
	    Connexion.setIcon(new ImageIcon(WelcomePage.class.getResource("/interfaces/Screenshot 2023-04-30 at 19.35.52.png")));
	    Connexion.setBounds(585, 301, 202, 40);
	    SecondPagePanel.add(Connexion);
	    Connexion.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
	    Connexion.addActionListener(e->card_Pan.show(cardPanel, "FourthPage"));
	    setSize(949,586);
	    JPanel FourthPagePanel=new JPanel() {

			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            g.drawImage(background_Image_4.getImage(), 0, 0, getWidth(), getHeight(), this);
	        }
			
	    
	    };
	    cardPanel.add(FourthPagePanel,"FourthPage");
	    FourthPagePanel.setLayout(null);
	    
	    utilisateurfield = new JTextField();
	    utilisateurfield.setToolTipText("");
	    utilisateurfield.setBackground(new Color(255, 255, 255));
	    utilisateurfield.setBounds(543, 140, 298, 42);
	    FourthPagePanel.add(utilisateurfield);
	    utilisateurfield.setColumns(10);
	    
	    passfield = new JTextField();
	    passfield.setColumns(10);
	    passfield.setBounds(543, 248, 298, 42);
	    FourthPagePanel.add(passfield);
	    
	    connect = new JButton("");
	    connect.setIcon(new ImageIcon(WelcomePage.class.getResource("/interfaces/Screenshot 2023-05-01 at 18.18.20.png")));
	    connect.setBounds(638, 356, 90, 80);
	    connect.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	    FourthPagePanel.add(connect);
	    //
	 // Récupération des valeurs saisies pour l'utilisateur et le mot de passe
	   connect.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String utilisateur = utilisateurfield.getText();
		    String motDePasse = passfield.getText();

		    // Connexion à la base de données
		    Connection connexion = null;
		    UtilisateursDao utilisateursDao = null;

		    try {
		        connexion = SingletonConnection.getInstance(); // Connexion à la base de données
		        utilisateursDao = new UtilisateursDao(connexion);

		        // Recherche de l'utilisateur dans la base de données
		        Utilisateur utilisateurConnecte = utilisateursDao.getUtilisateurByUsername(utilisateur);

		        // Si un utilisateur est trouvé, ouverture de la session
		        if (utilisateurConnecte != null && utilisateurConnecte.getMot_de_passe().equals(motDePasse)) {
		            String typeUtilisateur = utilisateurConnecte.getType_utilisateur();
		            // Ouvrir la session en fonction du type d'utilisateur
		            if (typeUtilisateur.equals("administrateur")) {
		                // Ouvrir la session pour l'administrateur
		            	AdminInterface admininterface=new AdminInterface();
		            	admininterface.setVisible(true);
		            	admininterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		            	admininterface.setSize(new Dimension(1100,575));
		            	admininterface.setResizable(false);
		            } else if (typeUtilisateur.equals("pharmacien")) {
		                // Ouvrir la session pour le pharmacien
		            	PharmacienInterface pharmacieninterface= new PharmacienInterface();
		            	pharmacieninterface.setSize(new Dimension(1100,575));
		            	pharmacieninterface.setVisible(true);
		            	pharmacieninterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		            	pharmacieninterface.setResizable(false);
		            }
		        } else {
		            // Afficher un message d'erreur si les informations de connexion sont incorrectes
		            JOptionPane.showMessageDialog(null, "Nom d'utilisateur ou mot de passe incorrect", "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		        }
		    } catch (SQLException e1) {
		        e1.printStackTrace();
		    } finally {
		    }
			
		}
	});
	    

	    //
	    JButton return2 = new JButton("");
	    return2.setIcon(new ImageIcon(WelcomePage.class.getResource("/interfaces/Screenshot 2023-05-01 at 18.35.47.png")));
	    return2.setBounds(763, 24, 72, 69);
	    return2.addActionListener(e->card_Pan.show(cardPanel,"thirdPage" ));
	    return2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	    FourthPagePanel.add(return2);
	    
	    JButton returnpag2 = new JButton("");
	    returnpag2.setIcon(new ImageIcon(WelcomePage.class.getResource("/interfaces/Screenshot 2023-05-01 at 18.38.52.png")));
	    returnpag2.setBounds(838, 24, 72, 69);
	    FourthPagePanel.add(returnpag2);
	    returnpag2.addActionListener(e->card_Pan.show(cardPanel,"secondPage" ));
	    returnpag2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	   
}

public void actionPerformed(ActionEvent e) {
	card_Pan.show(cardPanel, "secondPage");
    
}


public CardLayout getCard_Pan() {
	return card_Pan;
}

public void setCard_Pan(CardLayout card_Pan) {
	this.card_Pan = card_Pan;
}

public JPanel getCardPanel() {
	return cardPanel;
}

public void setCardPanel(JPanel cardPanel) {
	this.cardPanel = cardPanel;
}
}