package interfaces;

import java.awt.Canvas;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


import dao.ClientDao;
import dao.MedicamentDao;
import dao.SingletonConnection;
import modeles.Client;
import modeles.Medicament;
import services.ModifierMedicamentFrame;
import services.AjouterClientFrame;
import services.AjouterMedicamentFrame;
import services.ModifierClientFrame;
//import java.sql.SQLException;
public class AdminInterface extends JFrame {
	private static final long serialVersionUID = 1L;
	public  CardLayout cardlayout; 

public AdminInterface() throws SQLException {
	getContentPane().setLayout(null);
	
	JLabel lblNewLabel_2 = new JLabel("");
	lblNewLabel_2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(0, 0, 102), new Color(0, 0, 102), new Color(0, 0, 102), new Color(0, 0, 102)));
	lblNewLabel_2.setForeground(new Color(0, 0, 102));
	lblNewLabel_2.setBackground(new Color(0, 0, 102));
	lblNewLabel_2.setIcon(new ImageIcon(PharmacienInterface.class.getResource("/interfaces/pharmacist (10).png")));
	lblNewLabel_2.setBounds(50, 48, 128, 135);
	getContentPane().add(lblNewLabel_2);
	
	JPanel panel = new JPanel();
	panel.setBackground(new Color(0, 0, 51));
	panel.setBounds(6, 262, 240, 332);
	getContentPane().add(panel);
	panel.setLayout(null);
	
	JLabel lblNewLabel_1 = new JLabel("Bienvenue a votre espace Admin");
	lblNewLabel_1.setFont(new Font(".AppleSystemUIFont", Font.BOLD, 16));
	lblNewLabel_1.setForeground(new Color(255, 255, 255));
	lblNewLabel_1.setBounds(507, 28, 412, 16);
	getContentPane().add(lblNewLabel_1);
	
	JLabel lblNewLabel = new JLabel("");
	lblNewLabel.setBackground(new Color(51, 102, 255));
	lblNewLabel.setIcon(new ImageIcon(AdminInterface.class.getResource("/interfaces/back_7.png")));
	lblNewLabel.setBounds(270, 0, 837, 579);
	getContentPane().add(lblNewLabel);
	
	Canvas canvas = new Canvas();
	canvas.setBackground(new Color(0, 0, 51));
	canvas.setBounds(0, 0, 270, 600);
	getContentPane().add(canvas);
	
	cardlayout=new CardLayout();
	JPanel card_panel_main = new JPanel(cardlayout);
	card_panel_main.setBounds(283, 77, 803, 428);
	getContentPane().add(card_panel_main);
	
	JPanel ClientPanel = new JPanel();
	card_panel_main.add(ClientPanel, "clients");
	JPanel MedicPanel= new JPanel();
	card_panel_main.add(MedicPanel,"medicament");
	
	JButton btnNewButtonmed = new JButton("Gérer Medicament");
	btnNewButtonmed.setBackground(new Color(0, 0, 102));
	btnNewButtonmed.setForeground(new Color(0, 0, 0));
	btnNewButtonmed.setBounds(30, 111, 181, 29);
	btnNewButtonmed.addActionListener(e->{Connection conn=SingletonConnection.getInstance();
	
	MedicamentDao medDao = new MedicamentDao(conn);
    List<Medicament> medicament = null;
	try {
		medicament = medDao.listerMedicaments();
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    
    // Création du modèle de la JTable avec les colonnes "Nom", "Adresse", "Téléphone" et "Crédit"
    DefaultTableModel model = new DefaultTableModel(new String[]{"ID","Nom", "DESCRIPTION", "STOCK", "PRIX (DT)"}, 0);
    
    // Ajout des données des clients au modèle de la JTable
    for (Medicament i : medicament) {
        model.addRow(new Object[]{i.getId_medicament(),i.getNom_medicament(), i.getDescription_medicament(), i.getStock_medicament(), i.getPrix_medicament()});
    }
    
    // Création de la JTable avec le modèle créé et ajout à la card "Consulter Clients"
    JTable table = new JTable(model);
    JPanel consulterMedicamentPanel = MedicPanel;
 // Définir les couleurs des cellules de la table
    table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
        private static final long serialVersionUID = 1L;

		@Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            c.setBackground(row%2==0 ? Color.WHITE : new Color(240,240,240)); // Alterner les couleurs de fond
            return c;
        }
    });

    // Modifier la police et la taille de la police de la table
    table.setFont(new Font("Calibri", Font.PLAIN, 12));

    // Ajouter des bordures à la table
    table.setBorder(BorderFactory.createLineBorder(Color.GRAY));

    // Modifier la couleur de sélection des cellules de la table
    table.setSelectionBackground(new Color(200,200,255));

    // Modifier la couleur de la police des cellules sélectionnées
    table.setSelectionForeground(Color.BLACK);

    // Ajouter des en-têtes à la table
    JTableHeader header = table.getTableHeader();
    header.setBackground(Color.WHITE);
    header.setFont(new Font("Calibri", Font.BOLD, 14));
    header.setBorder(BorderFactory.createLineBorder(Color.GRAY));

    // Modifier la largeur des colonnes
    table.getColumnModel().getColumn(0).setPreferredWidth(100); // ID
    table.getColumnModel().getColumn(1).setPreferredWidth(120); // Nom
    table.getColumnModel().getColumn(2).setPreferredWidth(150); // Adresse
    table.getColumnModel().getColumn(3).setPreferredWidth(100); // Téléphone
    table.getColumnModel().getColumn(4).setPreferredWidth(100); // Crédit

    // Ajouter un espacement entre les lignes de la table
    table.setRowHeight(25);

    // Ajouter une marge entre les cellules et les bords de la table
    table.setRowMargin(5);
    table.setIntercellSpacing(new Dimension(10, 5));

    table.setPreferredScrollableViewportSize(new Dimension(700, 400));
    table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
    JPopupMenu menu = new JPopupMenu();
    JMenuItem ajouter = new JMenuItem("Ajouter");
    JMenuItem modifier = new JMenuItem("Modifier");
    JMenuItem supprimer = new JMenuItem("Supprimer");
    menu.add(ajouter);
    menu.add(modifier);
    menu.add(supprimer);

    // Ajout d'un MouseListener à la JTable pour détecter les clics avec le bouton droit
    table.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
            if (e.isPopupTrigger()) {
                int row = table.rowAtPoint(e.getPoint());
                if (row >= 0 && row < table.getRowCount()) {
                    // Sélection de la ligne cliquée
                    table.setRowSelectionInterval(row, row);
                } else {
                    // Si l'utilisateur a cliqué en dehors des lignes de la JTable, ne rien faire
                    return;
                }
                // Affichage du menu contextuel
                menu.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    });

    // Ajout d'un ActionListener à chaque option du menu contextuel
    ajouter.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	AjouterMedicamentFrame fr=new AjouterMedicamentFrame(medDao, model);
        	fr.setVisible(true);
        }
    });
    modifier.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	int selectedRow = table.getSelectedRow();
            
            // Vérifier si une ligne est bien sélectionnée
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner un médicament.");
                return;
            }
            
            // Récupérer l'objet Medicament correspondant à la ligne sélectionnée
            int idMedicament = (int) model.getValueAt(selectedRow, 0);
				Medicament medicamentt;
				try {
					medicamentt = medDao.trouverMedicamentParId(idMedicament);
					ModifierMedicamentFrame mod=new ModifierMedicamentFrame(medDao, model, medicamentt);
					mod.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}        	
        
       
    }
    });
    supprimer.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
            // Code pour supprimer la ligne sélectionnée de la base de données
        	if (e.getSource() == supprimer) {
        	    int selectedRow = table.getSelectedRow();
        	    if (selectedRow == -1) {
        	        JOptionPane.showMessageDialog(null, "Veuillez sélectionner une ligne à supprimer");
        	    } else {
        	        int confirm = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer cette medicament et tout les ordonnaces contenant cette medicament ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        	        if (confirm == JOptionPane.YES_OPTION) {
        	            int id = (int) model.getValueAt(selectedRow, 0);
        	            try {
        	                medDao.supprimerMedicament(id);
        	                model.removeRow(selectedRow);
        	                JOptionPane.showMessageDialog(null, "Ordonnance supprimée avec succès");
        	            } catch (SQLException ex) {
        	                JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de la suppression de l'ordonnance");
        	                ex.printStackTrace();
        	            }
        	        }
        	    }
        	}

        }
    	
    	


    });

    


    consulterMedicamentPanel.removeAll();
    consulterMedicamentPanel.add(new JScrollPane(table));
    consulterMedicamentPanel.revalidate(); // Ajout de la méthode revalidate()
    System.out.println("table avec Succes");

    cardlayout.show(card_panel_main, "medicament");
    System.out.println("show avec succes");

		
	});
	panel.add(btnNewButtonmed);
	
	JButton btnconsulter_c = new JButton("Gérer Client");
	btnconsulter_c.setBackground(new Color(0, 0, 102));
	btnconsulter_c.setForeground(new Color(0, 0, 0));
	btnconsulter_c.setBounds(30, 45, 181, 29);
	btnconsulter_c.addActionListener(e->{
		Connection conn=SingletonConnection.getInstance();
		
		ClientDao clientDao = new ClientDao(conn);
	    List<Client> clients = null;
		try {
			clients = clientDao.getAllClients();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    // Création du modèle de la JTable avec les colonnes "Nom", "Adresse", "Téléphone" et "Crédit"
	    DefaultTableModel model = new DefaultTableModel(new String[]{"ID","Nom", "Adresse", "Téléphone", "Crédit"}, 0);
	    
	    // Ajout des données des clients au modèle de la JTable
	    for (Client client : clients) {
	        model.addRow(new Object[]{client.getId_client(),client.getNom_client(), client.getAdresse_client(), client.getTelephone_client(), client.getCredit_client()});
	    }
	    
	    // Création de la JTable avec le modèle créé et ajout à la card "Consulter Clients"
	    JTable table = new JTable(model);
	    JPanel consulterClientsPanel = ClientPanel;
	 // Définir les couleurs des cellules de la table
	    table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
	        private static final long serialVersionUID = 1L;

			@Override
	        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
	            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	            c.setBackground(row%2==0 ? Color.WHITE : new Color(240,240,240)); // Alterner les couleurs de fond
	            return c;
	        }
	    });

	    // Modifier la police et la taille de la police de la table
	    table.setFont(new Font("Calibri", Font.PLAIN, 12));

	    // Ajouter des bordures à la table
	    table.setBorder(BorderFactory.createLineBorder(Color.GRAY));

	    // Modifier la couleur de sélection des cellules de la table
	    table.setSelectionBackground(new Color(200,200,255));

	    // Modifier la couleur de la police des cellules sélectionnées
	    table.setSelectionForeground(Color.BLACK);

	    // Ajouter des en-têtes à la table
	    JTableHeader header = table.getTableHeader();
	    header.setBackground(Color.WHITE);
	    header.setFont(new Font("Calibri", Font.BOLD, 14));
	    header.setBorder(BorderFactory.createLineBorder(Color.GRAY));

	    // Modifier la largeur des colonnes
	    table.getColumnModel().getColumn(0).setPreferredWidth(30); // ID
	    table.getColumnModel().getColumn(1).setPreferredWidth(100); // Nom
	    table.getColumnModel().getColumn(2).setPreferredWidth(100); // Adresse
	    table.getColumnModel().getColumn(3).setPreferredWidth(100); // Téléphone
	    table.getColumnModel().getColumn(4).setPreferredWidth(100); // Crédit

	    // Ajouter un espacement entre les lignes de la table
	    table.setRowHeight(25);

	    // Ajouter une marge entre les cellules et les bords de la table
	    table.setRowMargin(5);
	    table.setIntercellSpacing(new Dimension(10, 5));

	 // Définir les couleurs des cellules de la table
	    table.setPreferredScrollableViewportSize(new Dimension(700, 400));
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);	
	    JPopupMenu menu = new JPopupMenu();
	    JMenuItem ajouter = new JMenuItem("Ajouter");
	    JMenuItem modifier = new JMenuItem("Modifier");
	    JMenuItem supprimer = new JMenuItem("Supprimer");
	    menu.add(ajouter);
	    menu.add(modifier);
	    menu.add(supprimer);

	    // Ajout d'un MouseListener à la JTable pour détecter les clics avec le bouton droit
	    table.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	            if (e.isPopupTrigger()) {
	                int row = table.rowAtPoint(e.getPoint());
	                if (row >= 0 && row < table.getRowCount()) {
	                    // Sélection de la ligne cliquée
	                    table.setRowSelectionInterval(row, row);
	                } else {
	                    // Si l'utilisateur a cliqué en dehors des lignes de la JTable, ne rien faire
	                    return;
	                }
	                // Affichage du menu contextuel
	                menu.show(e.getComponent(), e.getX(), e.getY());
	            }
	        }
	    });

	    // Ajout d'un ActionListener à chaque option du menu contextuel
	    ajouter.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            // Code pour ajouter une ligne la base de données
	        	AjouterClientFrame ac=new AjouterClientFrame(clientDao, model);
	        	ac.setVisible(true);
	        	ac.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        }
	    });
	    modifier.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	int selectedRow = table.getSelectedRow();
	            
	            // Vérifier si une ligne est bien sélectionnée
	            if (selectedRow == -1) {
	                JOptionPane.showMessageDialog(null, "Veuillez sélectionner un médicament.");
	                return;
	            }
	            
	            // Récupérer l'objet Medicament correspondant à la ligne sélectionnée
	            int idclient = (int) model.getValueAt(selectedRow, 0);
					Client cl;
					try {
						cl = clientDao.getClientById(idclient);
						ModifierClientFrame mc=new ModifierClientFrame(clientDao, model, cl);
						mc.setVisible(true);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}        	
	        
	        	
	        
	        }
	    }
	    );
	    supprimer.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	            if (e.getSource() == supprimer) {
	                int selectedRow = table.getSelectedRow();
	                if (selectedRow == -1) {
	                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner un client à supprimer.");
	                } else {
	                    int idClient = (int) table.getValueAt(selectedRow, 0);
	                    int confirmation = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir supprimer ce client et toutes ses ordonnances ?", "Confirmation", JOptionPane.YES_NO_OPTION);
	                    if (confirmation == JOptionPane.YES_OPTION) {
	                        try {
	                            clientDao.supprimerOrdonnancesClient(idClient); // Supprimer toutes les ordonnances du client
	                            clientDao.supprimerClientEtOrdonnances(idClient); // Supprimer le client
	                            JOptionPane.showMessageDialog(null, "Le client a été supprimé avec succès.");
	                            DefaultTableModel model = (DefaultTableModel) table.getModel();
	                            model.removeRow(selectedRow);
	                        } catch (SQLException ex) {
	                            ex.printStackTrace();
	                            JOptionPane.showMessageDialog(null, "Erreur lors de la suppression du client.");
	                        }
	                    }

	                }
	            }
	        }


	    });
	    consulterClientsPanel.removeAll();
	    consulterClientsPanel.add(new JScrollPane(table));
	    consulterClientsPanel.revalidate(); // Ajout de la méthode revalidate()
	    System.out.println("table avec Succes");

	    cardlayout.show(card_panel_main, "clients");
	    System.out.println("show avec succes");
	});
	
	
	panel.add(btnconsulter_c);
	
	
	

	
}
}

	