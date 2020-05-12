package Gestion_de_livraison2;

import java.awt.EventQueue;

import javax.swing.UIManager;

public class Main {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");   // le theme de notre application
					Login window = new Login();
					window.setVisible(true);   // affichage de l'interface du login comme page d'acceuil

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
