package Gestion_de_livraison2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class User_interface extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField1;
    private JPanel panel_3;
    private JPanel panel_4;
    private JPanel panel_5;
    private JLabel lblNewLabel_3;
    private boolean testPayer = false;
    public Colis colis;

    public User_interface(Client client) throws IOException {
        Societe s = new Societe();
        colis = new Colis();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 880, 524);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(253, 245, 230));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel.setBackground(new Color(253, 245, 230));
        contentPane.add(panel, BorderLayout.WEST);
        panel.setLayout(new GridLayout(2, 0, 0, 5));

        JButton btnNewButton = new JButton("Ajouter des colis");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	colis = new Colis();
                panel_3.setVisible(true);
                panel_4.setVisible(false);
                panel_5.setVisible(false);

            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(btnNewButton);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(253, 245, 230));
        contentPane.add(panel_1, BorderLayout.NORTH);
        panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 45, 5));

        JButton btnNewButton_6 = new JButton("Logout");
        btnNewButton_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Login login = new Login();
                login.setVisible(true);
                setVisible(false);
            }
        });
        panel_1.add(btnNewButton_6);

        JLabel label = new JLabel(client.getUsername());   
        label.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panel_1.add(label);

        JLabel lblNewLabel_1 = new JLabel(client.getPts() + "pts"); // la label representant les pts acquis par le client
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panel_1.add(lblNewLabel_1);

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(253, 245, 230));
        contentPane.add(panel_2, BorderLayout.CENTER);
        panel_2.setLayout(null);
        
        // partie consultation des colis
        panel_4 = new JPanel();
        panel_4.setBackground(new Color(253, 245, 230));
        panel_4.setBounds(0, 0, 717, 445);
        panel_2.add(panel_4);
        panel_4.setLayout(null);
        panel_4.setVisible(false);

        JLabel lblNewLabel_4 = new JLabel("Le temps estim\u00E9");     // la label represantant le temps restant pour chaque colis
        lblNewLabel_4.setFont(new Font("Segoe Script", Font.BOLD, 26));
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4.setBounds(188, 34, 331, 77);
        panel_4.add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel();     // la label represantant la destination finale
        lblNewLabel_5.setFont(new Font("Segoe Script", Font.PLAIN, 15));
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_5.setBounds(233, 122, 241, 36);
        panel_4.add(lblNewLabel_5);

        JProgressBar progressBar = new JProgressBar();     // le bar de progression pour d'ecrire le temps restant
        progressBar.setMaximum(200);
        progressBar.setValue(0);
        progressBar.setBounds(177, 183, 363, 14);
        panel_4.add(progressBar);

        JLabel lblNewLabel_6 = new JLabel("Localisation actuelle: ");   // la label represantant le temps restant pour chaque colis
        lblNewLabel_6.setFont(new Font("Segoe Print", Font.BOLD, 16));
        lblNewLabel_6.setBounds(212, 239, 177, 29);
        panel_4.add(lblNewLabel_6);

        JLabel lblNewLabel_7 = new JLabel("Tunis");       // label representant la position actuelle du colis en temps reel
        lblNewLabel_7.setFont(new Font("Segoe Print", Font.BOLD, 16));
        lblNewLabel_7.setBounds(413, 239, 96, 29);
        panel_4.add(lblNewLabel_7);

        JButton btnNewButton_4 = new JButton("Annuler la commande");
        btnNewButton_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) { // ce bouton declanche l'evenement de l'annulation de la commande si elle est encore en cours de traitement
        		try {
					boolean check = client.annulerColis(colis,Long.parseLong(textField1.getText()));
					System.out.println(colis.getTimeDepart());
					if(check) {
						JOptionPane.showMessageDialog(contentPane, "Annulation du commande avec succée");   // succée de 'annulation
					}else {
						JOptionPane.showMessageDialog(contentPane, "La livraison a deja commencé");   // l'echec de l'annulation
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        });
        btnNewButton_4.setBounds(169, 351, 197, 23);
        panel_4.add(btnNewButton_4);

        JLabel lblDestinationFinale = new JLabel("Destination finale:");
        lblDestinationFinale.setFont(new Font("Segoe Print", Font.BOLD, 16));
        lblDestinationFinale.setBounds(212, 279, 177, 29);
        panel_4.add(lblDestinationFinale);

        JLabel lblSousse = new JLabel();                  // la label representant le nom de l'utilisateur authentifié 
        lblSousse.setFont(new Font("Segoe Print", Font.BOLD, 16));
        lblSousse.setBounds(413, 279, 96, 29);
        panel_4.add(lblSousse);

        JButton btnNewButton_7 = new JButton("Imprimer le re\u00E7u"); 
        btnNewButton_7.addActionListener(new ActionListener() { // ce bouton fait apparaitre le recu de chaque client pour l'imprimer
            public void actionPerformed(ActionEvent arg0) {
                Recu recu = new Recu(client, textField1.getText());
                recu.setVisible(true);

            }
        });
        btnNewButton_7.setBounds(388, 351, 197, 23);
        panel_4.add(btnNewButton_7);
        
        
        // la partie du paiement
        panel_3 = new JPanel();
        panel_3.setBackground(new Color(253, 245, 230));
        panel_3.setBounds(0, 0, 717, 445);
        panel_2.add(panel_3);
        panel_3.setLayout(null);

        JLabel lblNewLabel_2 = new JLabel("Entrer le poids du colis (en gramme)");
        lblNewLabel_2.setBounds(163, 52, 188, 37);
        panel_3.add(lblNewLabel_2);

        textField = new JTextField();     // pour recevoir le poids du nouveau colis
        lblNewLabel_2.setLabelFor(textField);
        textField.setBounds(378, 52, 98, 37);
        panel_3.add(textField);
        textField.setColumns(10);

        JLabel lblChoisirLaDestination = new JLabel("Choisir la destination");
        lblChoisirLaDestination.setBounds(163, 118, 188, 37);
        panel_3.add(lblChoisirLaDestination);

        JComboBox<String> comboBox = new JComboBox<String>();    // une combo box qui contient les destinantions a choisir 
        comboBox.setModel(new DefaultComboBoxModel<String>(s.getZoneGeographique()));
        comboBox.setBounds(378, 118, 98, 37);
        panel_3.add(comboBox);

        lblNewLabel_3 = new JLabel();        // va afficher le prix calculé de la colis
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setBounds(234, 235, 242, 23);
        panel_3.add(lblNewLabel_3);

        JButton btnNewButton_2 = new JButton("Calculer le prix");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (!textField.getText().equals("")) {
                    try {   
                        // checking valid float 
                        Float.parseFloat(textField.getText());
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(contentPane, "Le poids doit etre un number rÃ©el ou entier", "Erreur", 0);
                    }
                    if (comboBox.getSelectedItem() != null) {   // checking valid destination
                        colis.setDestination(comboBox.getSelectedItem().toString());
                        colis.setPoids(Float.parseFloat(textField.getText()));
                        try {
                            lblNewLabel_3.setText(Double.toString(colis.calculPrix(s)) + " DT");   // affecter le prix a la label
                            testPayer = true;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(contentPane, "Vous devez choisir la destination", "Erreur", 0);
                    }
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Vous devez remplir le champs du poids", "Erreur", 0);
                }

            }
        });

        btnNewButton_2.setBounds(298, 193, 134, 23);
        panel_3.add(btnNewButton_2);

        JButton btnUtiliserLesPoints = new JButton("Utiliser les points");
        btnUtiliserLesPoints.addActionListener(new ActionListener() { // bouton pour le paiement du colis avec les points
            public void actionPerformed(ActionEvent arg0) {
                if (testPayer) {
                    try {
                        int pointNecessaire = (int) (colis.calculPrix(s) * 100);
                        if (client.getPts() >= pointNecessaire) {
                        	//confirmer le paiement du colis avec les points de fidelité
                            int response = JOptionPane.showConfirmDialog(contentPane, "Vous etes sur ? Vous devez utiliser " + pointNecessaire + " points", "Check", JOptionPane.YES_NO_OPTION);
                            if (response == JOptionPane.YES_OPTION) {
                                lblNewLabel_5.setText(colis.calculDureeTotal(s));
                                boolean check = colis.preciseDepartureTime(); // si les livreurs sont encore en travail
                                colis.calculDureeTotal(s);
                                colis.calculArrivalTime();
                                boolean availibility = Livreur.checkAvailability(colis);  // si les livreur sont disponible
                                if (availibility && check) {

                                    client.UtiliserLesPoints(colis);
                                    //colis.updateCaisse();
                                    lblNewLabel_1.setText(Integer.toString(client.getPts()) + " pts"); // mise a jour des points

                                } else {
                                    JOptionPane.showMessageDialog(contentPane, "Il n'ya pas de coursier disponible pour le moment essayer plus tard");
                                }
                                testPayer = false;
                            }
                        } else {
                            JOptionPane.showMessageDialog(contentPane, "Impossible de payer avec les points. Vous devez avoir " + pointNecessaire + " points", "Erreur", 0);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(User_interface.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Vous devez calculer le prix avant de payer", "Erreur", 0);
                }
            }
        });
        btnUtiliserLesPoints.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnUtiliserLesPoints.setBounds(354, 286, 153, 37);
        panel_3.add(btnUtiliserLesPoints);

        JButton btnNewButton_3 = new JButton("Payer");
        btnNewButton_3.addActionListener(new ActionListener() {   // bouton pour payer les frais de livraison 
            public void actionPerformed(ActionEvent arg0) {
                if (testPayer) {
                    int response = JOptionPane.showConfirmDialog(contentPane, "Vous etes sur ?", "Check", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        try {
                            lblNewLabel_5.setText(colis.calculDureeTotal(s));
                            boolean check = colis.preciseDepartureTime(); // si les livreurs sont encore en travail
                            colis.calculDureeTotal(s);
                            colis.calculArrivalTime();
                            boolean availibility = Livreur.checkAvailability(colis);  // si les livreur sont disponible
                            if (availibility && check) { 
                                client.payer(colis);     // payment du frais
                                colis.updateCaisse();    // mise a jour du fichier caisse.txt
                                lblNewLabel_1.setText(Integer.toString(client.getPts()) + " pts");  // mise a jour de l'affichage des points

                            } else {
                                JOptionPane.showMessageDialog(contentPane, "Il n'ya pas de coursier disponible pour le moment essayer plus tard");
                            }
                            testPayer = false;

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Vous devez calculer le prix avant de payer", "Erreur", 0);
                }
            }
        });
        btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNewButton_3.setBounds(234, 286, 110, 37);
        panel_3.add(btnNewButton_3);

        //changement
        panel_5 = new JPanel();
        panel_5.setBackground(new Color(253, 245, 230));
        panel_5.setBounds(0, 0, 693, 451);
        panel_2.add(panel_5);
        panel_5.setLayout(null);
        panel_5.setVisible(false);

        JLabel lblNewLabel_9 = new JLabel("La liste des colis");
        lblNewLabel_9.setFont(new Font("Sitka Text", Font.PLAIN, 21));
        lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_9.setBounds(72, 11, 244, 64);
        panel_5.add(lblNewLabel_9);

        JScrollPane scrollPane_3 = new JScrollPane();
        scrollPane_3.setBounds(39, 132, 627, 169);
        panel_5.add(scrollPane_3);

        JTable table_3 = new JTable();
        table_3.setBackground(new Color(253, 245, 230));
        table_3.setFillsViewportHeight(true);
        scrollPane_3.setViewportView(table_3);
        JLabel lblNewLabel_3_1 = new JLabel("id de coli");
        lblNewLabel_3_1.setBounds(72, 104, 57, 14);
        panel_5.add(lblNewLabel_3_1);

        textField1 = new JTextField();
        textField1.setBounds(120, 99, 119, 23);
        panel_5.add(textField1);
        textField1.setColumns(10);

        JButton btnNewButton_5 = new JButton("Rechercher");
        btnNewButton_5.addActionListener(new ActionListener() { // le bouton qui fait la recherche d'un colis a travers son id
            public void actionPerformed(ActionEvent arg0) {
                try {
                    boolean test = false;
                    String col[] = null;
                    for (String[] s : client.getColisMatrice()) {
                        if (textField1.getText().equals(s[0])) {    // recherche de l'existance de l'id du colis dans colis.txt
                            col = s;
                            test = true;
                            break;
                        }
                    }
                    if (test) {
                        int max = Integer.parseInt(col[5].split(":")[0].replace("h", "")) * 60 + Integer.parseInt(col[5].split(":")[1].replace("min", ""));
                        lblSousse.setText(col[2]);    // la destination finale
                        lblNewLabel_5.setText(client.formatTime(col));    // le temps restant ou l'etat du colis
                        lblNewLabel_7.setText(client.updateColisPosition(col));  // la position actuelle du colis
                        progressBar.setMaximum(max);     // le max de la bar de progression
                        progressBar.setValue(max - ((int) client.updateColisTime(col)));   // mise a jour de la bar de progression
                        panel_3.setVisible(false);  
                        panel_4.setVisible(true);    // afficher l'interface de cette colis
                        panel_5.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(contentPane, "L'id n'est pas valable", "erreur", 0);
                    }
                } catch (IOException e1) {
                    System.out.println("erreu");
                }
            }
        });
        btnNewButton_5.setBounds(265, 101, 113, 23);
        panel_5.add(btnNewButton_5);

        JButton btnNewButton_1 = new JButton("Consulter les colis"); 
        btnNewButton_1.addActionListener(new ActionListener() {   // le bouton qui affiche la liste des colis delivré ou en cours 
            public void actionPerformed(ActionEvent e) {
                try {
                    table_3.setModel(new DefaultTableModel(
                            client.getColisMatrice(),
                            new String[]{
                                "Colis id",
                                "Poids(Kg)", "Destination", "Position", "Date", "Temps estimï¿½", "Temps de dÃ©part", "Status"
                            }
                    ));
                    table_3.getColumnModel().getColumn(0).setPreferredWidth(118);
                    table_3.getColumnModel().getColumn(1).setPreferredWidth(119);
                } catch (IOException ex) {
                    Logger.getLogger(User_interface.class.getName()).log(Level.SEVERE, null, ex);
                }
                panel_3.setVisible(false);
                panel_4.setVisible(false);
                panel_5.setVisible(true);

            }
        });
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(btnNewButton_1);
    }
}
