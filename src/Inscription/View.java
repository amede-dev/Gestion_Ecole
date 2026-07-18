/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inscription;

import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hp
 *
 * Disposition entièrement réécrite à la main (BorderLayout / FlowLayout / BoxLayout)
 * pour remplacer le GroupLayout généré par NetBeans, illisible et pénible à
 * réorganiser sans l'éditeur graphique Matisse.
 */
public class View extends javax.swing.JFrame {

    public View() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        // ===================== Couleurs / styles communs =====================
        Color bleu = new Color(0, 0, 255);
        Color blanc = Color.WHITE;
        Font titreFont = new Font("Times New Roman", Font.BOLD, 24);
        Font labelFont = new Font("Times New Roman", Font.BOLD, 14);
        final int LARGEUR_CHAMP = 232; // largeur commune à TOUS les champs du formulaire (y compris la date)

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestion de Personne");

        // ===================== Panel racine =====================
        jPanel1 = new JPanel(new BorderLayout(10, 10));
        jPanel1.setBackground(bleu);
        jPanel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ---------- Titre (NORTH) ----------
        JLabel jLabel1 = new JLabel("GESTION DE PERSONNE:", SwingConstants.CENTER);
        jLabel1.setFont(titreFont);
        jLabel1.setForeground(blanc);
        try {
            jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Inscription/group.png")));
        } catch (Exception ignored) {
        }
        jPanel1.add(jLabel1, BorderLayout.NORTH);

        // ---------- Formulaire de saisie (WEST) ----------
        jPanel2 = new JPanel();
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.Y_AXIS));
        jPanel2.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        null, "Inscrire le client", TitledBorder.DEFAULT_JUSTIFICATION,
                        TitledBorder.DEFAULT_POSITION, new Font("Times New Roman", Font.BOLD, 18),
                        new Color(255, 220, 100)),
                BorderFactory.createEmptyBorder(4, 14, 10, 14)));

        jLabel10 = labelFormulaire("Id :", new Font("Times New Roman", Font.BOLD, 18));
        jTextFieldId = champTexte(LARGEUR_CHAMP);

        jLabel2 = labelFormulaire("Nom:", labelFont);
        jTextFieldNom = champTexte(LARGEUR_CHAMP);

        JLabel jLabel3 = labelFormulaire("Prénoms:", labelFont);
        jTextFieldPrénom = champTexte(LARGEUR_CHAMP);

        JLabel jLabel4 = labelFormulaire("Mention:", labelFont);
        jTextFieldMention = champTexte(LARGEUR_CHAMP);

        JLabel jLabel5 = labelFormulaire("Parcour:", labelFont);
        jTextFieldParcour = champTexte(LARGEUR_CHAMP);

        JLabel jLabel6 = labelFormulaire("Niveau:", labelFont);
        jComboBox1 = new JComboBox(new String[]{"L1", "L2", "L3", "M1", "M2", "Docteur"});
        jComboBox1.setAlignmentX(JComboBox.LEFT_ALIGNMENT);
        jComboBox1.setPreferredSize(new Dimension(LARGEUR_CHAMP, 28));
        jComboBox1.setMaximumSize(new Dimension(LARGEUR_CHAMP, 28));

        JLabel jLabel7 = labelFormulaire("Date de Naissance:", labelFont);
        jDateChoose = new JDateChooser();
        jDateChoose.setAlignmentX(JDateChooser.LEFT_ALIGNMENT);
        jDateChoose.setPreferredSize(new Dimension(LARGEUR_CHAMP, 28));
        jDateChoose.setMaximumSize(new Dimension(LARGEUR_CHAMP, 28));

        JLabel jLabel8 = labelFormulaire("Télephone:", labelFont);
        jTextFieldTélephone = champTexte(LARGEUR_CHAMP);

        JLabel jLabel9 = labelFormulaire("Argent:", labelFont);
        jTextFieldArgent = champTexte(LARGEUR_CHAMP);

        jPanel2.add(jLabel10);
        jPanel2.add(jTextFieldId);
        jPanel2.add(Box.createVerticalStrut(8));
        jPanel2.add(jLabel2);
        jPanel2.add(jTextFieldNom);
        jPanel2.add(Box.createVerticalStrut(8));
        jPanel2.add(jLabel3);
        jPanel2.add(jTextFieldPrénom);
        jPanel2.add(Box.createVerticalStrut(8));
        jPanel2.add(jLabel4);
        jPanel2.add(jTextFieldMention);
        jPanel2.add(Box.createVerticalStrut(8));
        jPanel2.add(jLabel5);
        jPanel2.add(jTextFieldParcour);
        jPanel2.add(Box.createVerticalStrut(8));
        jPanel2.add(jLabel6);
        jPanel2.add(jComboBox1);
        jPanel2.add(Box.createVerticalStrut(8));
        jPanel2.add(jLabel7);
        jPanel2.add(jDateChoose);
        jPanel2.add(Box.createVerticalStrut(8));
        jPanel2.add(jLabel8);
        jPanel2.add(jTextFieldTélephone);
        jPanel2.add(Box.createVerticalStrut(8));
        jPanel2.add(jLabel9);
        jPanel2.add(jTextFieldArgent);

        jScrollPaneFormulaire = new JScrollPane(jPanel2);
        jScrollPaneFormulaire.setBorder(null);
        jScrollPaneFormulaire.setOpaque(false);
        jScrollPaneFormulaire.getViewport().setOpaque(false);
        jScrollPaneFormulaire.setPreferredSize(new Dimension(LARGEUR_CHAMP + 40, 520));
        jScrollPaneFormulaire.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPaneFormulaire.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.add(jScrollPaneFormulaire, BorderLayout.WEST);

        // ---------- Zone centrale : tableau + boutons + recherche (CENTER) ----------
        JPanel centre = new JPanel(new BorderLayout(0, 10));
        centre.setOpaque(false);

        // Tableau des clients
        jTableClients = new JTable();
        jTableClients.setForeground(new Color(0, 153, 0));
        jTableClients.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Id", "Nom", "Prénom", "Mention", "Parcour", "Niveau", "Date", "Tél", "Argent"}
        ));
        jTableClients.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableClientsMouseClicked(evt);
            }
        });
        if (jTableClients.getColumnModel().getColumnCount() > 0) {
            jTableClients.getColumnModel().getColumn(0).setResizable(false);
            jTableClients.getColumnModel().getColumn(1).setResizable(false);
            jTableClients.getColumnModel().getColumn(2).setResizable(false);
        }
        jScrollPane1 = new JScrollPane(jTableClients);
        jScrollPane1.setPreferredSize(new Dimension(600, 260));
        centre.add(jScrollPane1, BorderLayout.CENTER);

        // Rangée unique de boutons d'action : Ajouter / Modifier / Effacer / Supprimer / Fermer
        // Fond de couleur pleine + texte blanc en gras : bon contraste (contrairement à du texte
        // coloré peu saturé sur fond gris, difficile à lire).
        jButtonAjouter = boutonAction("Ajouter", "/Inscription/ajouter-un-ami.png", new Color(34, 139, 87));
        jButtonModifier = boutonAction("Modifier", "/Inscription/update.png", new Color(41, 82, 204));
        jButtonEffacer = boutonAction("Effacer", null, new Color(120, 120, 120));
        jButtonSupprimer = boutonAction("Supprimer", "/Inscription/delete.png", new Color(200, 40, 40));
        jButtonFermer = boutonAction("Fermer", "/Inscription/exit.png", new Color(70, 70, 70));

        JPanel rangeeActions = new JPanel(new GridLayout(1, 5, 12, 0));
        rangeeActions.setOpaque(false);
        rangeeActions.setBorder(BorderFactory.createEmptyBorder(6, 0, 6, 0));
        rangeeActions.add(jButtonAjouter);
        rangeeActions.add(jButtonModifier);
        rangeeActions.add(jButtonEffacer);
        rangeeActions.add(jButtonSupprimer);
        rangeeActions.add(jButtonFermer);

        // Barre de recherche, sous la rangée de boutons
        jButtonRecherche = new JButton("Recherche");
        jButtonRecherche.setBackground(new Color(0, 90, 110));
        jButtonRecherche.setFont(new Font("Times New Roman", Font.BOLD, 16));
        jButtonRecherche.setForeground(blanc);
        jButtonRecherche.setOpaque(true);
        jButtonRecherche.setBorderPainted(false);
        try {
            jButtonRecherche.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Inscription/recherche.png")));
        } catch (Exception ignored) {
        }
        jButtonRecherche.setPreferredSize(new Dimension(150, 36));
        jButtonRecherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRechercheActionPerformed(evt);
            }
        });

        jTextFieldRecherche = new JTextField();
        jTextFieldRecherche.setPreferredSize(new Dimension(240, 30));
        jTextFieldRecherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldRechercheActionPerformed(evt);
            }
        });

        jPanel3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 8));
        jPanel3.setOpaque(false);
        jPanel3.add(jButtonRecherche);
        jPanel3.add(jTextFieldRecherche);

        // jPanel4 / jPanel5 conservés pour compatibilité mais fusionnés dans rangeeActions ci-dessus
        jPanel4 = rangeeActions;
        jPanel5 = new JPanel();
        jPanel5.setOpaque(false);

        JPanel bas = new JPanel();
        bas.setOpaque(false);
        bas.setLayout(new BoxLayout(bas, BoxLayout.Y_AXIS));
        bas.add(rangeeActions);
        bas.add(jPanel3);

        centre.add(bas, BorderLayout.SOUTH);

        jPanel1.add(centre, BorderLayout.CENTER);

        getContentPane().add(jPanel1, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    // Crée un label du formulaire en blanc (bon contraste sur le fond bleu du panneau)
    private JLabel labelFormulaire(String texte, Font police) {
        JLabel label = new JLabel(texte);
        label.setFont(police);
        label.setForeground(Color.WHITE);
        label.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        return label;
    }

    // Crée un champ de texte au style standard du formulaire, avec une largeur fixe
    // pour que TOUS les champs (y compris la date) soient parfaitement alignés.
    private JTextField champTexte(int largeur) {
        JTextField champ = new JTextField();
        champ.setFont(new Font("Times New Roman", Font.BOLD, 12));
        champ.setPreferredSize(new Dimension(largeur, 26));
        champ.setMaximumSize(new Dimension(largeur, 26));
        champ.setAlignmentX(JTextField.LEFT_ALIGNMENT);
        return champ;
    }

    // Crée un bouton d'action au style standard : fond de couleur pleine + texte
    // blanc en gras, largeur suffisante pour ne jamais tronquer le libellé.
    private JButton boutonAction(String texte, String iconePath, Color couleurFond) {
        JButton bouton = new JButton(texte);
        bouton.setBackground(couleurFond);
        bouton.setOpaque(true);
        bouton.setBorderPainted(false);
        bouton.setFont(new Font("Times New Roman", Font.BOLD, 15));
        bouton.setForeground(Color.WHITE);
        if (iconePath != null) {
            try {
                bouton.setIcon(new javax.swing.ImageIcon(getClass().getResource(iconePath)));
            } catch (Exception ignored) {
            }
        }
        bouton.setPreferredSize(new Dimension(150, 40));
        return bouton;
    }

    private void jButtonRechercheActionPerformed(java.awt.event.ActionEvent evt) {
        // Géré par le Controller (listener ajouté par-dessus dans Controller.java)
    }

    private void jTextFieldRechercheActionPerformed(java.awt.event.ActionEvent evt) {
        // Non utilisé directement
    }

    private void jTableClientsMouseClicked(java.awt.event.MouseEvent evt) {

        // Récupérer les valeurs de la ligne sélectionnée dans la JTable
        DefaultTableModel table = (DefaultTableModel) this.getjTableClients().getModel();
        int row = this.getjTableClients().getSelectedRow();
        if (row < 0) {
            return;
        }
        String idUtile = table.getValueAt(row, 0).toString();
        String nomUtile = table.getValueAt(row, 1).toString();
        String prénomsUtile = table.getValueAt(row, 2).toString();
        String mentionUtile = table.getValueAt(row, 3).toString();
        String parcourUtile = table.getValueAt(row, 4).toString();
        String niveauUtile = table.getValueAt(row, 5).toString();
        String dateUtile = table.getValueAt(row, 6).toString();
        String télUtile = table.getValueAt(row, 7).toString();
        String argentUtile = table.getValueAt(row, 8).toString();

        this.getjTextFieldId().setText(idUtile);
        this.getjTextFieldNom().setText(nomUtile);
        this.getjTextFieldPrénom().setText(prénomsUtile);
        this.getjTextFieldMention().setText(mentionUtile);
        this.getjTextFieldParcour().setText(parcourUtile);
        this.getjComboBox1().setSelectedItem(niveauUtile);

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd / MM /yyyy");
            Date date = dateFormat.parse(dateUtile);
            this.getjDateChoose().setDate(date);
        } catch (Exception e) {
        }
        this.getjTextFieldTélephone().setText(télUtile);
        this.getjTextFieldArgent().setText(argentUtile);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                View view = new View();
                Controller controller = new Controller(view);
                view.setVisible(true);
            }
        });
    }

    //Getter pour les Champs de texte et button
    public JTextField getjTextFieldId() {
        return jTextFieldId;
    }

    public JTextField getjTextFieldNom() {
        return jTextFieldNom;
    }

    public JTextField getjTextFieldPrénom() {
        return jTextFieldPrénom;
    }

    public JTextField getjTextFieldMention() {
        return jTextFieldMention;
    }

    public JTextField getjTextFieldParcour() {
        return jTextFieldParcour;
    }

    public JComboBox getjComboBox1() {
        return jComboBox1;
    }

    public JDateChooser getjDateChoose() {
        return jDateChoose;
    }

    public JTextField getjTextFieldTélephone() {
        return jTextFieldTélephone;
    }

    public JTextField getjTextFieldArgent() {
        return jTextFieldArgent;
    }

    public JTextField getjTextFieldRecherche() {
        return jTextFieldRecherche;
    }

    public JButton getjButtonAjouter() {
        return jButtonAjouter;
    }

    public JButton getjButtonEffacer() {
        return jButtonEffacer;
    }

    public JButton getjButtonFermer() {
        return jButtonFermer;
    }

    public JButton getjButtonModifier() {
        return jButtonModifier;
    }

    public JButton getjButtonRecherche() {
        return jButtonRecherche;
    }

    public JButton getjButtonSupprimer() {
        return jButtonSupprimer;
    }

    public JTable getjTableClients() {
        return jTableClients;
    }

    // Variables declaration
    private JButton jButtonAjouter;
    private JButton jButtonEffacer;
    private JButton jButtonFermer;
    private JButton jButtonModifier;
    private JButton jButtonRecherche;
    private JButton jButtonSupprimer;
    private JComboBox jComboBox1;
    private JDateChooser jDateChoose;
    private JLabel jLabel10;
    private JLabel jLabel2;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPaneFormulaire;
    private JTable jTableClients;
    private JTextField jTextFieldArgent;
    private JTextField jTextFieldId;
    private JTextField jTextFieldMention;
    private JTextField jTextFieldNom;
    private JTextField jTextFieldParcour;
    private JTextField jTextFieldPrénom;
    private JTextField jTextFieldRecherche;
    private JTextField jTextFieldTélephone;
    // End of variables declaration
}