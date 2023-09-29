package services;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
//import java.text.ParseException;
//import java.text.ParseException;
//import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
//import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.OrdonanceDao;
import modeles.Ordonance;
//import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class AjouterOrdonanceFrame extends JFrame implements ActionListener {
    
    private static final long serialVersionUID = 1L;
    private final OrdonanceDao ordonnanceDao;
    private final DefaultTableModel model;
    private final JTextField idOrdonField;
    private final JTextField idClientField;
    private final JTextField idMedicamentField;
    private final JTextField dateField;
    private final JTextField quantiteField;
    private final JButton ajouterButton;

    public AjouterOrdonanceFrame(OrdonanceDao ordonnanceDao, DefaultTableModel model) {
        super("Ajouter une ordonnance");
        this.ordonnanceDao = ordonnanceDao;
        this.model = model;
        
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JLabel("ID ordonnance"));
        idOrdonField=new JTextField(10);
        panel.add(idOrdonField);
        panel.add(new JLabel("ID client :"));
        idClientField = new JTextField(10);
        panel.add(idClientField);
        panel.add(new JLabel("ID médicament :"));
        idMedicamentField = new JTextField(10);
        panel.add(idMedicamentField);
        panel.add(new JLabel("Date :"));
        dateField = new JTextField(10);
        panel.add(dateField);
        panel.add(new JLabel("Quantité :"));
        quantiteField = new JTextField(10);
        panel.add(quantiteField);
        ajouterButton = new JButton("Ajouter");
        ajouterButton.addActionListener(this);
        panel.add(ajouterButton);
        
        this.add(panel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ajouterButton) {
            try {
                int idord = 0;
                int idClient = 0;
                int idMedicament = 0;
                int quantite = 0;

                // Vérifier que tous les champs ont été remplis
                if (idOrdonField.getText().isEmpty()|| idClientField.getText().isEmpty() || idMedicamentField.getText().isEmpty() || dateField.getText().isEmpty() || quantiteField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Tous les champs sont obligatoires.");
                    return;
                }

                // Vérifier que les champs ID contiennent des entiers valides
                try {
                    idord = Integer.parseInt(idOrdonField.getText());
                    idClient = Integer.parseInt(idClientField.getText());
                    idMedicament = Integer.parseInt(idMedicamentField.getText());
                    quantite = Integer.parseInt(quantiteField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Les champs ID client, ID médicament et quantité doivent être des nombres entiers");
                    return;
                }

                // Vérifier que la date est valide
                DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                DateTimeFormatter dbFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date;

                try {
                    date = LocalDate.parse(dateField.getText(), inputFormat);
                    String formattedDate = dbFormat.format(date);
                    java.sql.Date sqlDate = java.sql.Date.valueOf(formattedDate);
                    // Insérez le code pour insérer la date formatée dans votre base de données ici
                    Ordonance ordonnance = new Ordonance(idord, idClient, idMedicament, sqlDate, quantite);
                    ordonnanceDao.add_ord(ordonnance);
                    Object[] row = {ordonnance.getId_ordonnance(), ordonnance.getId_client(), ordonnance.getId_medicament(), ordonnance.getDate_ordonnance(), ordonnance.getQuantite()};
                    model.addRow(row);
                    JOptionPane.showMessageDialog(this, "Ordonnance ajoutée avec succès");
                    this.dispose();
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(this, "Le format de la date est incorrect. Le format attendu est : jj/mm/aaaa.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout de l'ordonnance : " + ex.getMessage());
                }
            } finally {
                // Dans tous les cas, effacer les champs
                idOrdonField.setText("");
                idClientField.setText("");
                idMedicamentField.setText("");
                dateField.setText("");
                quantiteField.setText("");
            }
        }
    }
    }
    
            
        
    
        