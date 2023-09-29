package services;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.MedicamentDao;
import modeles.Medicament;

public class AjouterMedicamentFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private final JTextField nomTextField;
    private final JTextField descriptionTextField;
    private final JTextField stockTextField;
    private final JTextField prixTextField;
    @SuppressWarnings("unused")
	private  final MedicamentDao medicamentdao;
    @SuppressWarnings("unused")
	private final DefaultTableModel model;

    public AjouterMedicamentFrame(MedicamentDao medicamentdao,DefaultTableModel model) {
    	this.medicamentdao=medicamentdao;
    	this.model=model;
    	
        setTitle("Ajouter un médicament");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel nomLabel = new JLabel("Nom:");
        nomLabel.setBounds(30, 30, 100, 25);
        panel.add(nomLabel);

        nomTextField = new JTextField();
        nomTextField.setBounds(140, 30, 200, 25);
        panel.add(nomTextField);

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(30, 70, 100, 25);
        panel.add(descriptionLabel);

        descriptionTextField = new JTextField();
        descriptionTextField.setBounds(140, 70, 200, 25);
        panel.add(descriptionTextField);

        JLabel stockLabel = new JLabel("Stock:");
        stockLabel.setBounds(30, 110, 100, 25);
        panel.add(stockLabel);

        stockTextField = new JTextField();
        stockTextField.setBounds(140, 110, 200, 25);
        panel.add(stockTextField);

        JLabel prixLabel = new JLabel("Prix:");
        prixLabel.setBounds(30, 150, 100, 25);
        panel.add(prixLabel);

        prixTextField = new JTextField();
        prixTextField.setBounds(140, 150, 200, 25);
        panel.add(prixTextField);

        JButton ajouterButton = new JButton("Ajouter");
        ajouterButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	    String nom = nomTextField.getText();
        	    if (nom.isEmpty()) {
        	        JOptionPane.showMessageDialog(AjouterMedicamentFrame.this, "Veuillez saisir un nom");
        	        return;
        	    }

        	    String description = descriptionTextField.getText();

        	    String stockStr = stockTextField.getText();
        	    if (stockStr.isEmpty()) {
        	        JOptionPane.showMessageDialog(AjouterMedicamentFrame.this, "Veuillez saisir une quantité en stock");
        	        return;
        	    }
        	    int stock;
        	    try {
        	        stock = Integer.parseInt(stockStr);
        	        if (stock < 0) {
        	            JOptionPane.showMessageDialog(AjouterMedicamentFrame.this, "La quantité en stock doit être positive");
        	            return;
        	        }
        	    } catch (NumberFormatException ex) {
        	        JOptionPane.showMessageDialog(AjouterMedicamentFrame.this, "La quantité en stock doit être un nombre entier");
        	        return;
        	    }

        	    String prixStr = prixTextField.getText();
        	    if (prixStr.isEmpty()) {
        	        JOptionPane.showMessageDialog(AjouterMedicamentFrame.this, "Veuillez saisir un prix");
        	        return;
        	    }
        	    double prix;
        	    try {
        	        prix = Double.parseDouble(prixStr);
        	        if (prix <= 0) {
        	            JOptionPane.showMessageDialog(AjouterMedicamentFrame.this, "Le prix doit être strictement positif");
        	            return;
        	        }
        	    } catch (NumberFormatException ex) {
        	        JOptionPane.showMessageDialog(AjouterMedicamentFrame.this, "Le prix doit être un nombre décimal");
        	        return;
        	    }

        	    try {
        	        Medicament medicament = new Medicament(0, nom, description, stockStr, prix);

        	        medicamentdao.ajouterMedicament(medicament);
        	        Object[] row = {medicament.getId_medicament(), medicament.getNom_medicament(), medicament.getDescription_medicament(), medicament.getStock_medicament(), medicament.getPrix_medicament()};
        	        model.addRow(row);
        	        JOptionPane.showMessageDialog(AjouterMedicamentFrame.this, "Médicament ajouté avec succès !");
        	    } catch (SQLException ex) {
        	        ex.printStackTrace();
        	        JOptionPane.showMessageDialog(AjouterMedicamentFrame.this, "Erreur lors de l'ajout du médicament");
        	    }
        	}

        });
        ajouterButton.setBounds(140, 190, 100, 25);
        panel.add(ajouterButton);

        setVisible(true);
        
    }

}

