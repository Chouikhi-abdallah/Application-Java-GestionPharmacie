package services;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import dao.OrdonanceDao;
import modeles.Ordonance;
import java.text.SimpleDateFormat;

public class ModifierOrdonanceFrame extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JLabel labelId, labelIdClient, labelIdMedicament, labelDate, labelQuantite;
    private JTextField txtId, txtIdClient, txtIdMedicament, txtDate, txtQuantite;
    private JButton btnModifier, btnAnnuler;
    private OrdonanceDao dao;
    private Ordonance ordonnance;

    public ModifierOrdonanceFrame(OrdonanceDao dao, int id) throws SQLException {
        this.dao = dao;
        this.ordonnance = dao.getOrdById(id);
        initComponents();
        remplirChamps();
    }

    private void initComponents() {
        setTitle("Modifier Ordonnance");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout(6, 2, 5, 5));
        labelId = new JLabel("ID");
        txtId = new JTextField();
        txtId.setEditable(false);
        labelIdClient = new JLabel("ID Client");
        txtIdClient = new JTextField();
        labelIdMedicament = new JLabel("ID Medicament");
        txtIdMedicament = new JTextField();
        labelDate = new JLabel("Date");
        txtDate = new JTextField();
        labelQuantite = new JLabel("Quantite");
        txtQuantite = new JTextField();
        btnModifier = new JButton("Modifier");
        btnAnnuler = new JButton("Annuler");
        btnModifier.addActionListener(this);
        btnAnnuler.addActionListener(this);
        add(labelId);
        add(txtId);
        add(labelIdClient);
        add(txtIdClient);
        add(labelIdMedicament);
        add(txtIdMedicament);
        add(labelDate);
        add(txtDate);
        add(labelQuantite);
        add(txtQuantite);
        add(btnModifier);
        add(btnAnnuler);
        pack();
        setLocationRelativeTo(null);
    }

    private void remplirChamps() {
        txtId.setText(String.valueOf(ordonnance.getId_ordonnance()));
        txtIdClient.setText(String.valueOf(ordonnance.getId_client()));
        txtIdMedicament.setText(String.valueOf(ordonnance.getId_medicament()));
        txtDate.setText(String.valueOf(ordonnance.getDate_ordonnance()));
        txtQuantite.setText(String.valueOf(ordonnance.getQuantite()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnModifier) {
            try {
                int id = Integer.parseInt(txtId.getText());
                int idClient = Integer.parseInt(txtIdClient.getText());
                int idMedicament = Integer.parseInt(txtIdMedicament.getText());
                
                // Correction de la ligne pour analyser la date correctement
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = dateFormat.parse(txtDate.getText());
                
                int quantite = Integer.parseInt(txtQuantite.getText());
                
                Ordonance ordonnance = new Ordonance(id, idClient, idMedicament, date, quantite);
                dao.update_ord(ordonnance);
                JOptionPane.showMessageDialog(this, "Ordonnance modifiée avec succès !");
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Veuillez saisir des valeurs numériques valides !");
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Format de date invalide ! Veuillez utiliser le format dd/MM/yyyy.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erreur lors de la modification de l'ordonnance");
            }
        }
    }
    }

