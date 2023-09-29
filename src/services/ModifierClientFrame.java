package services;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.ClientDao;
import modeles.Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifierClientFrame extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private final ClientDao clientDao;
    private final DefaultTableModel model;
    private final Client client;
    private final JTextField idClientField;
    private final JTextField nomField;
    private final JTextField adresseField;
    private final JTextField telephoneField;
    private final JButton modifierButton;

    public ModifierClientFrame(ClientDao clientDao, DefaultTableModel model, Client client) {
        super("Modifier un client");
        this.clientDao = clientDao;
        this.model = model;
        this.client = client;

        JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JLabel("ID client"));
        idClientField = new JTextField(Integer.toString(client.getId_client()), 10);
        idClientField.setEditable(false);
        panel.add(idClientField);
        panel.add(new JLabel("Nom :"));
        nomField = new JTextField(client.getNom_client(), 10);
        panel.add(nomField);
        panel.add(new JLabel("Adresse :"));
        adresseField = new JTextField(client.getAdresse_client(), 10);
        panel.add(adresseField);
        panel.add(new JLabel("Téléphone :"));
        telephoneField = new JTextField(client.getTelephone_client(), 10);
        panel.add(telephoneField);
        modifierButton = new JButton("Modifier");
        modifierButton.addActionListener(this);
        panel.add(modifierButton);

        this.add(panel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == modifierButton) {
            try {
                // Vérifier que tous les champs ont été remplis
                if (nomField.getText().isEmpty() ||  adresseField.getText().isEmpty() || telephoneField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Tous les champs sont obligatoires.");
                    return;
                }

                // Mettre à jour le client dans la base de données
                client.setNom_client(nomField.getText());
                
                client.setAdresse_client(adresseField.getText());
                client.setTelephone_client(telephoneField.getText());
                clientDao.updateClient(client);
                model.fireTableDataChanged();
                JOptionPane.showMessageDialog(this, "Le client a été modifié avec succès.");
                this.dispose(); // Fermeture de la fenêtre
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur lors de la modification du client : " + ex.getMessage());
            }
        }
    }
}

