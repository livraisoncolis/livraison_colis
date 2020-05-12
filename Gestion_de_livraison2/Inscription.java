package Gestion_de_livraison2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Inscription {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JFrame frame;

	/**
	 * Create the frame.
	 */
	public Inscription() {
		frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 473, 622);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(253, 245, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Creer Votre Comptre");
		lblNewLabel.setFont(new Font("Sitka Text", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(84, 11, 295, 82);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();  // l'input du nom 'dutilisateur 
		textField.setBounds(113, 150, 226, 41);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();    // l'input du password
		passwordField.setBounds(113, 238, 226, 41);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();  // l'input du verification du password
		passwordField_1.setBounds(113, 332, 226, 41);
		contentPane.add(passwordField_1);
		
		JLabel lblNewLabel_1 = new JLabel("Saisir votre nom d'utilisateur");
		lblNewLabel_1.setBounds(113, 125, 186, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblSaisirVotreMot = new JLabel("Saisir votre mot de passe");
		lblSaisirVotreMot.setBounds(113, 213, 186, 14);
		contentPane.add(lblSaisirVotreMot);
		
		JLabel lblResaisirVotreMot = new JLabel("Resaisir votre mot de passe");
		lblResaisirVotreMot.setBounds(113, 307, 186, 14);
		contentPane.add(lblResaisirVotreMot);
		
		JButton btnNewButton = new JButton("S'inscrire");
		btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String username = textField.getText();   // username
                String password = new String(passwordField.getPassword());   // password
                String password2 = new String(passwordField_1.getPassword());    // password 2
                if (username.equals("") || password.equals("") || password2.equals("")) {
                    JOptionPane.showMessageDialog(contentPane, "Vous devez remplir tous les champs", "", 3);  // verficaition des champs vide
                } else if (VerifUserName(username)&& !username.equals("admin")) {   // verfication de username et password
                    if (password.equals(password2)) {    // test si password1 et password 2 sont egaux
                        password = Client.securemdp(password); // crypter le mot de passe en md5
                        Client.register(username, password);   // creattion d'objet client et l'ajout dans le fichier client.txt 
                        JOptionPane.showMessageDialog(contentPane, "Vous pouvez vous connectez sur votre compte maintenant", "S'inscrire", 3);
                        Login login = new Login();
                        login.setVisible(true);    //s'authentifier
                        frame.setVisible(false);

                    } else {
                        JOptionPane.showMessageDialog(contentPane, "Mot de passe non compatible", "", 3);

                    }
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Username déjà  existant", "", 3);
                }
            }

        });
	
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(159, 423, 120, 41);
		contentPane.add(btnNewButton);
	}
	 public static boolean VerifUserName(String username) {
	        File file = new File("src/Resources/client.txt");
	        try {
	            @SuppressWarnings("resource")
	            Scanner reader = new Scanner(file);
	            while (reader.hasNextLine()) {
	                String data = reader.nextLine();
	                String[] tab = data.split(",");
	                if (tab[1].equals(username)) {
	                    return false;
	                }
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	        return true;
	    }

	public void setVisible(boolean bool) {
		this.frame.setVisible(bool);
	}
}
