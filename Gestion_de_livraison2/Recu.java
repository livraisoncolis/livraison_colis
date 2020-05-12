package Gestion_de_livraison2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Recu extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3152433324944693689L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public Recu(Client client, String colisID) {
		setBounds(100, 100, 668, 361);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Re\u00E7u");
			lblNewLabel.setFont(new Font("Segoe Print", Font.PLAIN, 18));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(188, 27, 284, 44);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("ID client: ");
			lblNewLabel_1.setBounds(27, 117, 103, 30);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblIdColis = new JLabel("ID colis: ");
			lblIdColis.setBounds(27, 196, 103, 30);
			contentPanel.add(lblIdColis);
		}
		{
			JLabel lblNomDuClient = new JLabel("Nom du client:");
			lblNomDuClient.setBounds(27, 155, 103, 30);
			contentPanel.add(lblNomDuClient);
		}
		{
			JLabel label = new JLabel(Long.toString(client.getId()));
			label.setBounds(165, 117, 170, 30);
			contentPanel.add(label);
		}
		{
			JLabel lblBoualiFiras = new JLabel(client.getUsername());
			lblBoualiFiras.setBounds(165, 155, 170, 30);
			contentPanel.add(lblBoualiFiras);
		}
		{
			JLabel label = new JLabel(colisID);
			label.setBounds(165, 196, 170, 30);
			contentPanel.add(label);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("");
                        //changer l'adresse selon votre pc
			lblNewLabel_2.setIcon(new ImageIcon("D:\\Apps\\java-2020-03\\eclipse\\workspace\\livraison des colis\\src\\Resources\\signature.png"));
			lblNewLabel_2.setBounds(345, 106, 290, 105);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Imprimer");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(contentPanel, "En cours d'imprimation","imprimer",3);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
