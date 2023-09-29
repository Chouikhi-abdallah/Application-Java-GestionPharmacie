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

import dao.ClientDao;
import modeles.Client;

public class AjouterClientFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField nomTextField;
    private JTextField adresseTextField;
    private JTextField telephoneTextField;
    private JTextField creditTextField;
    @SuppressWarnings("unused")
	private final DefaultTableModel model;
    @SuppressWarnings("unused")
	private final ClientDao clientdao;

    public AjouterClientFrame(ClientDao clientDao,DefaultTableModel model) {
        this.clientdao=clientDao;
        this.model=model;
    	setTitle("Ajouter un client");
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

        JLabel adresseLabel = new JLabel("Adresse:");
        adresseLabel.setBounds(30, 70, 100, 25);
        panel.add(adresseLabel);

        adresseTextField = new JTextField();
        adresseTextField.setBounds(140, 70, 200, 25);
        panel.add(adresseTextField);

        JLabel telephoneLabel = new JLabel("Téléphone:");
        telephoneLabel.setBounds(30, 110, 100, 25);
        panel.add(telephoneLabel);

        telephoneTextField = new JTextField();
        telephoneTextField.setBounds(140, 110, 200, 25);
        panel.add(telephoneTextField);

        JLabel creditLabel = new JLabel("Crédit:");
        creditLabel.setBounds(30, 150, 100, 25);
        panel.add(creditLabel);

        creditTextField = new JTextField();
        creditTextField.setBounds(140, 150, 200, 25);
        panel.add(creditTextField);

        JButton ajouterButton = new JButton("Ajouter");
        ajouterButton.setBounds(140, 200, 100, 25);
        ajouterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nom = nomTextField.getText();
                String adresse = adresseTextField.getText();
                String telephone = telephoneTextField.getText();
                String creditText = creditTextField.getText();

                // Vérification des champs
                if (nom.isEmpty() || adresse.isEmpty() || telephone.isEmpty() || creditText.isEmpty()) {
                    JOptionPane.showMessageDialog(AjouterClientFrame.this, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                float credit = 0;
                try {
                    credit = Float.parseFloat(creditText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AjouterClientFrame.this, "Le champ crédit doit être un nombre", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Client client = new Client(0, nom, adresse, telephone, credit);

                try {


                    clientDao.addClient(client);
                    JOptionPane.showMessageDialog(AjouterClientFrame.this, "Le client a été ajouté avec succès !");
                    Object[] row = {client.getId_client(),client.getNom_client(),client.getAdresse_client(), client.getTelephone_client(), client.getCredit_client()};
                    model.addRow(row);
                    dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(AjouterClientFrame.this, "Erreur : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(ajouterButton);
    }
}
