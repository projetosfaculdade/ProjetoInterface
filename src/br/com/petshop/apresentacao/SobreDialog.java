package br.com.petshop.apresentacao;

import java.awt.Font;

import javax.swing.JDialog;

import br.com.petshop.utils.Utils;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class SobreDialog extends JDialog {
	public SobreDialog() {
		setTitle(Utils.nomePrograma + "Sobre");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 150);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Este sistema foi desenvolvido por Richard, John e Leandro ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 62, 424, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("como projeto da disciplina de laborat\u00F3rio de programa\u00E7\u00E3o.");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 78, 424, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Informa\u00E7\u00F5es sobre o programa");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 11, 424, 40);
		getContentPane().add(lblNewLabel_2);
		
		JButton demonButton = new JButton("");
		demonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(null, "Cara, você tem certeza do que está fazendo?",
						Utils.nomePrograma + "Cuidado", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "Parabéns, você encontrou este easter egg.");
				}
			}
		});
		demonButton.setBounds(443, 109, 1, 12);
		getContentPane().add(demonButton);
	}
}
