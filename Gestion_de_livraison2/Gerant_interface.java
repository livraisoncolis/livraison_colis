package Gestion_de_livraison2;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Gerant_interface extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3217468049935677387L;
	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JPanel panel_3;
	private JPanel panel_5;
	private JPanel panel_4;
	private JPanel panel_6;
	private JTable table_2;
	private JTable table_3;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public Gerant_interface() throws IOException {
		Societe s = new Societe();
		Gerant gerant = new Gerant();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 836, 524);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(253, 245, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(253, 245, 230));
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(4, 0, 0, 20));
		
		JButton btnNewButton = new JButton("Liste des Colis");   
		btnNewButton.addActionListener(new ActionListener() {  // le bouton qui affiche la liste des colis
			public void actionPerformed(ActionEvent arg0) {
				panel_3.setVisible(true);
				panel_4.setVisible(false);
				panel_5.setVisible(false);
				panel_6.setVisible(false);
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("liste des coursiers"); // le bouton qui affiche la liste des coursiers
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_3.setVisible(false);
				panel_4.setVisible(true);
				panel_5.setVisible(false);
				panel_6.setVisible(false);
			}
		});
		
		JButton btnNewButton_4 = new JButton("Liste des clients ");  // le bouton qui affiche la liste des clients
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_6.setVisible(true);
				panel_3.setVisible(false);
				panel_4.setVisible(false);
				panel_5.setVisible(false);
			}
		});
		panel.add(btnNewButton_4);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("Caisse");       // le bouton qui affiche la partie de la caisse
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_3.setVisible(false);
				panel_4.setVisible(false);
				panel_5.setVisible(true);
				panel_6.setVisible(false);
			}
		});
		panel.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(253, 245, 230));
		contentPane.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Admin");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI Symbol", Font.BOLD, 17));
		panel_1.add(lblNewLabel, BorderLayout.CENTER);
		
		JButton btnNewButton_3 = new JButton("Logout");     // le bouton qui deconnecte l'admin de son compte a affiche l'interface du login
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login login = new Login();
				login.setVisible(true);
				setVisible(false);
			}
		});
		panel_1.add(btnNewButton_3, BorderLayout.EAST);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		
		// la partie caisse
		panel_5 = new JPanel();
		panel_5.setBackground(new Color(253, 245, 230));
		panel_5.setBounds(0, 0, 693, 451);
		panel_2.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("La caisse");
		lblNewLabel_1.setFont(new Font("Sitka Text", Font.PLAIN, 21));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(72, 11, 244, 64);
		panel_5.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(Double.toString(s.calculCaisse(s.listeCaisse))+" DT");   // affiche le montant totale dans la caisse
		lblNewLabel_2.setFont(new Font("Sitka Text", Font.PLAIN, 17));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(302, 13, 237, 64);
		panel_5.add(lblNewLabel_2);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(39, 132, 627, 169);
		panel_5.add(scrollPane_3);
		
		table_3 = new JTable();
		table_3.setBackground(new Color(253, 245, 230));
		table_3.setFillsViewportHeight(true);
		scrollPane_3.setViewportView(table_3);
		table_3.setModel(new DefaultTableModel(    // affiche la liste des montant versés dans la caisse par date 
			s.listeCaisse,
			new String[] {
				"Date", "Monatnt Pay\u00E9"    
			}
		));
		
		JLabel lblNewLabel_3 = new JLabel("1ere Date");
		lblNewLabel_3.setBounds(44, 105, 57, 14);
		panel_5.add(lblNewLabel_3);
		
		textField = new JTextField();             // l'input de la 1ere date pour filtrer les montant versés dans la caisse
		textField.setBounds(100, 99, 119, 23);
		panel_5.add(textField);
		textField.setColumns(10);
		
		JLabel lblemeDate = new JLabel("2eme Date");
		lblemeDate.setBounds(241, 105, 73, 14);
		panel_5.add(lblemeDate);
		
		textField_1 = new JTextField();          // l'input de la 2eme date pour filtrer les montant versés dans la caisse
		textField_1.setColumns(10);         
		textField_1.setBounds(302, 99, 125, 23);
		panel_5.add(textField_1);
		
		JLabel lblNewLabel_5 = new JLabel("0 DT");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(382, 337, 73, 32);
		panel_5.add(lblNewLabel_5);
		table_3.getColumnModel().getColumn(0).setPreferredWidth(118);
		table_3.getColumnModel().getColumn(1).setPreferredWidth(119);
		
		JButton btnNewButton_5 = new JButton("Filtrer");
        btnNewButton_5.addActionListener(new ActionListener() {   // ce bouton affiche la liste des montants par date apres l'operation de filtration
            public void actionPerformed(ActionEvent arg0) {
                String d1 = textField.getText();                   
                String d2 = textField_1.getText();
                if (d1.equals("") && d2.equals("")) {   // si l'admin a laissé les champs des dates vides
                    d1 = "1900-01-01";
                    d2 = "2999-12-31";
                }
                if (d1.matches("([0-9]{4})-([0-1][0-9])-([0-3][0-9])") && d2.matches("([0-9]{4})-([0-1][0-9])-([0-3][0-9])")) {  // validation de la structure de la date YYYY-MM-DD
                    try {
                        LocalDate date11 = LocalDate.parse(d1); // changement de la date de string en objet LocalDate 
                        LocalDate date22 = LocalDate.parse(d2);
                        if(date11.isBefore(date22)){
                            table_3.setModel(new DefaultTableModel(
                                    s.filterCaisse(d1, d2),
                                    new String[]{
                                        "Date", "Monatnt Pay\u00E9"
                                    }
                            ));
                            lblNewLabel_5.setText(s.calculCaisseFromDate1ToDate2(s.listeCaisse, d1, d2));
                        }else{
                            JOptionPane.showMessageDialog(contentPane, "Date 1 doit etres infrieur ï¿½ Date 2", "Erreur", 0);
                        }
                    } catch (DateTimeException e) {
                        JOptionPane.showMessageDialog(contentPane, "Vous devez entrer des dates valide", "Erreur", 0);
                    }
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Les 2 dates doivent respecter la forme suivant : YYYY-MM-DD", "Erreur", 0);
                }
            }
        });
		btnNewButton_5.setBounds(465, 101, 89, 23);
		panel_5.add(btnNewButton_5);
		
		JLabel lblNewLabel_4 = new JLabel("La caisse dans cette periode contient: ");   // affiche le montant versé dans la caisse pendant ces deux dates
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(100, 334, 272, 38);
		panel_5.add(lblNewLabel_4);
		String[][]listcoursier = s.getListeCoursier();
		

		// la partie des coursiers
		panel_4 = new JPanel();
		panel_4.setBackground(new Color(253, 245, 230));
		panel_4.setBounds(0, 0, 693, 451);
		panel_2.add(panel_4);
		panel_4.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 102, 673, 328);
		panel_4.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setBackground(new Color(253, 245, 230));
		table_1.setFillsViewportHeight(true);
		scrollPane_1.setViewportView(table_1);  // affiche la liste des coursiers stockés dans coursier.txt
		table_1.setModel(new DefaultTableModel(
			listcoursier,        // martrice des coursiers
			new String[] {
				"Coursier id", "Nom", "Prenom", "Disponibilit\u00E9", "List des Colis", "Destination"   // entetes du tableau
			}
		));
		
		textField_2 = new JTextField(); // l'input du nom du coursier a ajouter ou a supprimer
		textField_2.setBounds(10, 62, 162, 25);
		panel_4.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();   //l'input du prenom du coursier a ajouter ou a supprimer
		textField_3.setColumns(10);
		textField_3.setBounds(197, 62, 162, 27);
		panel_4.add(textField_3);
		
		JButton btnNewButton_6 = new JButton("Ajouter Un Coursier"); // le bouton qui ajoute un coursier dans coursier.txt
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nom = textField_2.getText();
				String prenom = textField_3.getText();
				gerant.ajoutCoursier(nom, prenom);
				}
		});
                
		btnNewButton_6.setBounds(388, 63, 138, 23);
		panel_4.add(btnNewButton_6);
		JButton btnNewButton_7 = new JButton("Supprimer Un Coursier"); // le bouton qui supprime un coursier de coursier.txt
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nom = textField_2.getText();
				String prenom = textField_3.getText();
				if(gerant.supprimerCoursier(nom, prenom)==1){      // suppression du coursier et test du succée
                  JOptionPane.showMessageDialog(contentPane, "Vous devez entrer un nom et un prenom valide ", "Erreur", 0);  
                }else if(gerant.supprimerCoursier(nom, prenom)==2){
                  JOptionPane.showMessageDialog(contentPane, "Impossible de supprimer ce coursier. Il a des colis Ã  transporter! ", "Erreur", 0);  
                }else {
                	JOptionPane.showMessageDialog(contentPane, "Suppression avec succée","Suppression",3);
                }
			}
        });
		btnNewButton_7.setBounds(525, 63, 138, 23);
		panel_4.add(btnNewButton_7);
		
		JLabel lblNewLabel_6 = new JLabel("Le nom du coursier:");
		lblNewLabel_6.setBounds(10, 26, 162, 25);
		panel_4.add(lblNewLabel_6);
		
		JLabel lblLePrenomDu = new JLabel("Le prenom du coursier:");
		lblLePrenomDu.setBounds(197, 26, 162, 25);
		panel_4.add(lblLePrenomDu);
		
		// la partie clients
		panel_6 = new JPanel();
		panel_6.setBackground(new Color(253, 245, 230));
		panel_6.setBounds(0, 0, 683, 451);
		panel_2.add(panel_6);
		panel_6.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 11, 663, 430);
		panel_6.add(scrollPane_2);
		
		table_2 = new JTable();
		String[][] listclient = s.getListeClient();  // affiche la liste des clients
		table_2.setFillsViewportHeight(true);
		scrollPane_2.setViewportView(table_2);
		table_2.setModel(new DefaultTableModel(
			listclient,   // la listes des clients
			new String[] {
				"Client id", "username", "password", "les points de fidelit\u00E9", "la liste des colis(id)"    // entetes du tableau
			}
		));
		table_2.setBackground(new Color(253, 245, 230));
		panel_3 = new JPanel();
		panel_3.setBackground(new Color(253, 245, 230));
		panel_3.setBounds(0, 0, 693, 451);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 673, 419);
		panel_3.add(scrollPane);
		
		table = new JTable();    // affiche la liste des colis
		table.setBorder(new CompoundBorder());    
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(false);
		table.setBackground(new Color(253, 245, 230));
		scrollPane.setViewportView(table);
		String[][] listcolis= s.getListeColis();
		table.setModel(new DefaultTableModel(
			listcolis,   //la liste des colis
			new String[] {
				"Colis id","Poids(g)", "Destination","Position","Date","Temps estimé", "Status"   // entetes du tableau
			}
		));
		
		//afficher la partie de la caisse lorsque l'admin s'authentifie
		panel_3.setVisible(false);
		panel_4.setVisible(false);
		panel_5.setVisible(true);
		panel_6.setVisible(false);
	}
}
