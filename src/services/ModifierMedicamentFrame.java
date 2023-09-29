package services;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.MedicamentDao;
import modeles.Medicament;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifierMedicamentFrame extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private final MedicamentDao medicamentDao;
    private final DefaultTableModel model;
    private final Medicament medicament;
    private final JTextField idMedicamentField;
    private final JTextField nomField;
    private final JTextField descriptionField;
    private final JTextField prixField;
    private final JTextField stockField;
    private final JButton modifierButton;

    public ModifierMedicamentFrame(MedicamentDao medicamentDao, DefaultTableModel model, Medicament medicament) {
        super("Modifier un médicament");
        this.medicamentDao = medicamentDao;
        this.model = model;
        this.medicament = medicament;

        JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JLabel("ID médicament"));
        idMedicamentField = new JTextField(Integer.toString(medicament.getId_medicament()), 10);
        idMedicamentField.setEditable(false);
        panel.add(idMedicamentField);
        panel.add(new JLabel("Nom :"));
        nomField = new JTextField(medicament.getNom_medicament(), 10);
        panel.add(nomField);
        panel.add(new JLabel("Description :"));
        descriptionField = new JTextField(medicament.getDescription_medicament(), 10);
        panel.add(descriptionField);
        panel.add(new JLabel("Prix :"));
        prixField = new JTextField(Double.toString(medicament.getPrix_medicament()), 10);
        panel.add(prixField);
        panel.add(new JLabel("Stock :"));
        stockField = new JTextField(medicament.getStock_medicament(), 10);
        panel.add(stockField);
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
                            double prix = 0.0;
                            @SuppressWarnings("unused")
							int stock = 0;

                            // Vérifier que tous les champs ont été remplis
                            if (nomField.getText().isEmpty() || descriptionField.getText().isEmpty() || prixField.getText().isEmpty() || stockField.getText().isEmpty()) {
                                JOptionPane.showMessageDialog(this, "Tous les champs sont obligatoires.");
                                return;
                            }

                            // Vérifier que le champ Prix contient un double valide
                            try {
                                prix = Double.parseDouble(prixField.getText());
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(this, "Le prix doit être un nombre décimal.");
                                return;
                            }

                            // Vérifier que le champ Stock contient un entier valide
                            try {
                            	
                                stock = Integer.parseInt(stockField.getText());
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(this, "Le stock doit être un nombre entier.");
                                return;
                            }

                            // Mettre à jour le médicament dans la base de données
                            medicament.setNom_medicament(nomField.getText());
                            medicament.setDescription_medicament(descriptionField.getText());
                            medicament.setPrix_medicament(prix);
                            medicament.setStock_medicament(stockField.getText());
                            medicamentDao.modifierMedicament(medicament);
                            model.fireTableDataChanged();
                            JOptionPane.showMessageDialog(this, "Le médicament a été modifié avec succès.");
                            this.dispose(); // Fermeture de la fenêtre
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(this, "Erreur lors de la modification du médicament : " + ex.getMessage());
                        }
                    }
                }
                
            } // Fermeture de la classe ModifierMedicamentFrame

