package JeuPuissance4.vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import JeuPuissance4.Controleur;

public class PanelInteraction extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private JLabel     lblInfo;
	private JButton    btnRejouer;

	public PanelInteraction (Controleur ctrl)
	{
		JPanel panelRejouer;
		JPanel panelList;

		
		this.setLayout(new BorderLayout());
		/*******************************/
		/*   Creation Des Composants   */
		/*******************************/
		this.ctrl = ctrl;

		this.lblInfo    = new JLabel();
		this.btnRejouer = new JButton("Rejouer");

		panelRejouer    = new JPanel();
		panelList       = new JPanel();

		panelRejouer.add(this.btnRejouer);
		panelList   .add(this.lblInfo   );

		/*************************************/
		/*   Positionnement Des Composants   */
		/*************************************/

		this.add(this.lblInfo,BorderLayout.CENTER);
		this.add(panelRejouer,BorderLayout.EAST);

		/*********************************/
		/*   Activation Des Composants   */
		/*********************************/

		this.btnRejouer.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnRejouer)
		{
			this.ctrl.rejouer();
		}
	}

	public void ajouterInformation(String message)
	{
		this.lblInfo.setText(message);
	}
}
