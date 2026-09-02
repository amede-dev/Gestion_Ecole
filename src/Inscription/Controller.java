/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inscription;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hp
 */
public class Controller {

    private static final String url =
            "jdbc:postgresql://aws-1-eu-west-1.pooler.supabase.com:5432/postgres?sslmode=require";

    private Connection connexion;
    private View view;
    // Table pour afficher les données
    private DefaultTableModel tableModel;
    int idVraie;
    private int télephone;
    private int Argent;

    public Controller(View view) {
        this.view = view;
        this.tableModel = (DefaultTableModel) view.getjTableClients().getModel();
        // Créer la table si elle n'existe pas encore
        try {
            creerTableSiAbsente();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Action pour le bouton Ajouter
        view.getjButtonAjouter().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Ajouter();
                } catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //Action pour le bouton Effacer
        view.getjButtonEffacer().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Effacer();
            }
        });

        //Action boutton Fermer
        view.getjButtonFermer().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Fermer();
            }
        });

        //Action button Supprimer
        view.getjButtonSupprimer().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Supprimer();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //Action buton Recherche
        view.getjButtonRecherche().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Recherche(view.getjTextFieldRecherche().getText());
                view.getjTextFieldRecherche().setText("");
            }
        });

        //Action boutton modifier
        view.getjButtonModifier().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Modifier();
            }
        });
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // FONCTION POUR LE BOUTTON
    // Boutton Ajouter des Clients
    public void Ajouter() throws SQLException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String id = view.getjTextFieldId().getText();
        String nom = view.getjTextFieldNom().getText();
        String prénoms = view.getjTextFieldPrénom().getText();
        String mention = view.getjTextFieldMention().getText();
        String parcour = view.getjTextFieldParcour().getText();
        String niveau = view.getjComboBox1().getSelectedItem().toString();
        Date dateChoisie = view.getjDateChoose().getDate();
        if (dateChoisie == null) {
            JOptionPane.showMessageDialog(view, "Sélectionnez une date de naissance.", "Champ obligatoire", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String dateNaissance = dateFormat.format(dateChoisie);
        String phone = view.getjTextFieldTélephone().getText();
        String argent = view.getjTextFieldArgent().getText();

        int comp = 0; //initialisation de textField complete de départ vide
        String vérification = view.getjTextFieldTélephone().getText();  // Vérifier que age est un entier
        if (vérification.length() > 9 && vérification.length() < 11) {
            try {
                idVraie = Integer.parseInt(id);
                télephone = Integer.parseInt(phone);
                Argent = Integer.parseInt(argent);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(view, "type entrer en textArgent est letter", "Message", JOptionPane.ERROR_MESSAGE);
                comp++;
            }
            if (id.isEmpty() || nom.isEmpty() || prénoms.isEmpty() || mention.isEmpty() || parcour.isEmpty() || dateNaissance.isEmpty() || phone.isEmpty() || argent.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Verifier que le champs ne complète!", "Message", JOptionPane.ERROR_MESSAGE);
            } else if (comp < 1) {

                try {
                    String ajout = "INSERT INTO mytable (id,nom,prenom,mention,parcour,niveau,date_naissance,telephone,argent) VALUES(?,?,?,?,?,?,?,?,?)";
                    ConnecteSupabase();

                    try {
                        PreparedStatement pstmt = connexion.prepareStatement(ajout);
                        pstmt.setInt(1, idVraie);
                        pstmt.setString(2, nom);
                        pstmt.setString(3, prénoms);
                        pstmt.setString(4, mention);
                        pstmt.setString(5, parcour);
                        pstmt.setString(6, niveau);
                        pstmt.setString(7, dateNaissance);
                        pstmt.setString(8, phone);
                        pstmt.setInt(9, Argent);
                        // ligne affiche des clients en tableau
                        int ligne = pstmt.executeUpdate();
                        tableModel.addRow(new Object[]{idVraie, nom, prénoms, mention, parcour, niveau, dateNaissance, télephone, Argent});
                        if (ligne > 0) {
                            JOptionPane.showMessageDialog(view, "ajouter est réçue");
                        }
                        connexion.close();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(view, " erreur:" + ex.getMessage());
                    }
                    // Effacer les Champs de texte après ajout
                    view.getjTextFieldId().setText("");
                    view.getjTextFieldNom().setText("");
                    view.getjTextFieldPrénom().setText("");
                    view.getjTextFieldMention().setText("");
                    view.getjTextFieldParcour().setText("");
                    view.getjTextFieldTélephone().setText("");
                    view.getjTextFieldArgent().setText("");

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                // comp >= 1 : rien à faire, message déjà affiché
            }

        } else {
            JOptionPane.showMessageDialog(view, "non +261 et debut 03 l'introduire et ", "Attantion!", JOptionPane.ERROR_MESSAGE);
        }

    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Fonction pour se connecter à Supabase (PostgreSQL)
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void ConnecteSupabase() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String user = System.getenv("SUPABASE_DB_USER");
        String password = System.getenv("SUPABASE_DB_PASSWORD");
        if (user == null || user.isBlank() || password == null || password.isBlank()) {
            throw new SQLException("Variables SUPABASE_DB_USER et SUPABASE_DB_PASSWORD manquantes.");
        }
        connexion = DriverManager.getConnection(url, user, password);
    }

    // Crée la table mytable si elle n'existe pas encore (pratique au premier lancement)
    private void creerTableSiAbsente() throws ClassNotFoundException, SQLException {
        ConnecteSupabase();
        String creation = "CREATE TABLE IF NOT EXISTS mytable ("
                + "id INTEGER PRIMARY KEY,"
                + "nom TEXT,"
                + "prenom TEXT,"
                + "mention TEXT,"
                + "parcour TEXT,"
                + "niveau TEXT,"
                + "date_naissance DATE,"
                + "telephone TEXT,"
                + "argent INTEGER"
                + ")";
        try (PreparedStatement pstmt = connexion.prepareStatement(creation)) {
            pstmt.executeUpdate();
        } finally {
            if (connexion != null) {
                connexion.close();
            }
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Effacer le champ de textField
    private void Effacer() {
        view.getjTextFieldId().setText("");
        view.getjTextFieldNom().setText("");
        view.getjTextFieldPrénom().setText("");
        view.getjTextFieldMention().setText("");
        view.getjTextFieldParcour().setText("");
        view.getjTextFieldArgent().setText("");
        view.getjTextFieldTélephone().setText("");
    }

    // Fermer le fenettre
    private void Fermer() {
        int conf = JOptionPane.showConfirmDialog(view, "est-ce que vous quittez,", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (conf == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    //Fonction Boutton Supprimer
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void Supprimer() throws ClassNotFoundException {
        DefaultTableModel model = (DefaultTableModel) view.getjTableClients().getModel();
        int row = view.getjTableClients().getSelectedRow();
        if (view.getjTableClients().getRowCount() == 0) {
            JOptionPane.showMessageDialog(view, "le tableClients est vide");
        } else if (row < 0) {
            JOptionPane.showMessageDialog(view, "selectionner le ligne lorqu'il supprimer!", "selectionne", JOptionPane.ERROR_MESSAGE
            );
        } else {
            int validation = JOptionPane.showConfirmDialog(view, "vous-voulez vraiment suprimer?", "Choix", JOptionPane.YES_NO_OPTION);
            if (validation == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(view, "d'après la selectionne,la supprimer est réçue");
                try {
                    String supprimer = "DELETE FROM mytable WHERE id = ?";
                    ConnecteSupabase();
                    PreparedStatement prstmt = connexion.prepareStatement(supprimer);
                    prstmt.setString(1, model.getValueAt(view.getjTableClients().getSelectedRow(), 0).toString());
                    prstmt.executeUpdate();
                    connexion.close();

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(view, " erreur:" + e.getMessage());
                }
                model.removeRow(row);
            }
        }

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Fonction buton recherche

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void Recherche(String rechercheTerm) {
        try {
            ConnecteSupabase();
            String rechercheSupabase = "SELECT * FROM mytable WHERE CAST(id AS TEXT) LIKE ? OR nom LIKE ? OR prenom LIKE ? "
                    + "OR mention LIKE ? OR parcour LIKE ? OR niveau LIKE ? OR CAST(date_naissance AS TEXT) LIKE ? "
                    + "OR telephone LIKE ? OR CAST(argent AS TEXT) LIKE ?";
            PreparedStatement stmt = connexion.prepareStatement(rechercheSupabase);
            String recherche = "%" + rechercheTerm + "%";
            stmt.setString(1, recherche);
            stmt.setString(2, recherche);
            stmt.setString(3, recherche);
            stmt.setString(4, recherche);
            stmt.setString(5, recherche);
            stmt.setString(6, recherche);
            stmt.setString(7, recherche);
            stmt.setString(8, recherche);
            stmt.setString(9, recherche);
            ResultSet rs = stmt.executeQuery();

            // On vide le tableau avant d'afficher les résultats de la recherche
            tableModel.setRowCount(0);

            boolean trouve = false;
            while (rs.next()) {
                trouve = true;
                String Id = rs.getString("id");
                String Nom = rs.getString("nom");
                String Prenom = rs.getString("prenom");
                String Mentions = rs.getString("mention");
                String Parcours = rs.getString("parcour");
                String Niveaux = rs.getString("niveau");
                String Dates = rs.getString("date_naissance");
                String Téls = rs.getString("telephone");
                String Argents = rs.getString("argent");
                tableModel.addRow(new Object[]{Id, Nom, Prenom, Mentions, Parcours, Niveaux, Dates, Téls, Argents});
            }

            if (!trouve) {
                JOptionPane.showMessageDialog(view, "l'element ne trouve!", "Message", JOptionPane.INFORMATION_MESSAGE);
            }

            connexion.close();

        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
        }

    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Fonction Modifier
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void Modifier() {

        int comp = 0;
        int TEL = 0;
        int ARGENT = 0;
        DefaultTableModel tableauClient = (DefaultTableModel) view.getjTableClients().getModel();
        int row = view.getjTableClients().getSelectedRowCount();
        // Récupération des nouvelles valeurs depuis les champs de texte
        String nouveauNom = view.getjTextFieldNom().getText();
        String nouveauPrénom = view.getjTextFieldPrénom().getText();
        String nouveauMention = view.getjTextFieldMention().getText();
        String nouveauParcour = view.getjTextFieldParcour().getText();
        String nouveauNiveau = view.getjComboBox1().getSelectedItem().toString();
        Date dateChoisie = view.getjDateChoose().getDate();
        if (dateChoisie == null) {
            JOptionPane.showMessageDialog(view, "Sélectionnez une date de naissance.", "Champ obligatoire", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String nouveauDate = new SimpleDateFormat("yyyy-MM-dd").format(dateChoisie);
        String nouveauTélephone = view.getjTextFieldTélephone().getText();
        String nouveauArgent = view.getjTextFieldArgent().getText();
        String idASelectionne = view.getjTextFieldId().getText();

        // Vérification de la validité des données
        if (row >= 1) {
            try {
                TEL = Integer.parseInt(nouveauTélephone);
                ARGENT = Integer.parseInt(nouveauArgent);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(view, "Le téléphone doivent être des nombres!", "Message", JOptionPane.ERROR_MESSAGE);
                comp++;
            }
            if (comp < 1) {
                try {

                    ConnecteSupabase();
                    connexion.setAutoCommit(false);

                    // Mise à jour de la ligne correspondant à l'Id sélectionné
                    String modify = "UPDATE mytable SET nom = ?, prenom = ?, mention = ?, parcour = ?, niveau = ?, date_naissance = ?, telephone = ?, argent = ? WHERE id = ?";
                    PreparedStatement stmt = connexion.prepareStatement(modify);
                    stmt.setString(1, nouveauNom);
                    stmt.setString(2, nouveauPrénom);
                    stmt.setString(3, nouveauMention);
                    stmt.setString(4, nouveauParcour);
                    stmt.setString(5, nouveauNiveau);
                    stmt.setString(6, nouveauDate);
                    stmt.setString(7, nouveauTélephone);
                    stmt.setInt(8, ARGENT);
                    stmt.setString(9, idASelectionne);
                    stmt.executeUpdate();
                    connexion.commit();
                    connexion.close();

                    // Mise à jour de la ligne dans le tableau affiché
                    int selectedRow = view.getjTableClients().getSelectedRow();
                    tableauClient.setValueAt(nouveauNom, selectedRow, 1);
                    tableauClient.setValueAt(nouveauPrénom, selectedRow, 2);
                    tableauClient.setValueAt(nouveauMention, selectedRow, 3);
                    tableauClient.setValueAt(nouveauParcour, selectedRow, 4);
                    tableauClient.setValueAt(nouveauNiveau, selectedRow, 5);
                    tableauClient.setValueAt(nouveauDate, selectedRow, 6);
                    tableauClient.setValueAt(TEL, selectedRow, 7);
                    tableauClient.setValueAt(ARGENT, selectedRow, 8);

                    JOptionPane.showMessageDialog(view, "Modifier est bien succès", "Modification", JOptionPane.INFORMATION_MESSAGE);
                } catch (ClassNotFoundException | SQLException e) {
                    JOptionPane.showMessageDialog(view, e.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(view, "Lettre n'est pas valider par l'age", "Message", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(view, "Selectionné la ligne de tableau que vous modifier", "ERREUR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
