package Gestion_de_livraison2;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Login {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}
	public void setVisible(boolean bool) {
		this.frame.setVisible(bool);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(253, 245, 230));
		frame.setBounds(100, 100, 812, 538);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to Fast Delivery");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("MV Boli", Font.BOLD, 27));
		lblNewLabel.setBounds(193, 50, 437, 130);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(261, 191, 350, 43);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Utilisateur");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(139, 196, 82, 29);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblPassword = new JLabel("Mot de passe");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(139, 266, 92, 29);
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(261, 261, 350, 43);
		frame.getContentPane().add(passwordField);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("souviens-toi de moi !");
		chckbxNewCheckBox.setBackground(new Color(253, 245, 230));
		chckbxNewCheckBox.setBounds(261, 321, 143, 23);
		frame.getContentPane().add(chckbxNewCheckBox);
		
		JButton btnNewButton = new JButton("Se connecter");
		btnNewButton.setMnemonic('S');
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							String pass = new String(passwordField.getPassword());
							String username = textField.getText();
							try {
								if(Client.login(username, pass) != null){
									Client client = Client.login(username, pass);
									User_interface user = new User_interface(client);
									user.setVisible(true);
									frame.setVisible(false);
									
								}else if(textField.getText().equals("admin") && pass.equals("admin")) {
									Gerant_interface gerant = new Gerant_interface();
									gerant.setVisible(true);
									frame.setVisible(false);
								}else {
									JOptionPane.showMessageDialog(frame, "Combination utilisateur/mot de passe est incorrect","Erreur", 0);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}	
					});
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(291, 377, 132, 43);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnSinscrire = new JButton("S'inscrire");
		btnSinscrire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						try {
							Inscription inscription = new Inscription();
							inscription.setVisible(true);
							frame.setVisible(false);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}	
				});
				
			}
		});
		btnSinscrire.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSinscrire.setBounds(450, 377, 132, 43);
		frame.getContentPane().add(btnSinscrire);
		
		
	}
}
