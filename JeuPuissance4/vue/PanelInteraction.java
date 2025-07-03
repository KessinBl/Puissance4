package JeuPuissance4.vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
	private JLabel     lblVictoireJ1;
	private JLabel     lblVictoireJ2;

	public PanelInteraction (Controleur ctrl)
	{
		JPanel panelRejouer;
		JPanel panelList;
		JPanel panelVictoire;

		
		this.setLayout(new BorderLayout());
		/*******************************/
		/*   Creation Des Composants   */
		/*******************************/
		this.ctrl = ctrl;

		this.lblInfo       = new JLabel("" ,JLabel.RIGHT);
		this.lblVictoireJ1 = new JLabel(""+this.ctrl.getJoueur(1).getNbVictoire());
		this.lblVictoireJ2 = new JLabel(""+this.ctrl.getJoueur(2).getNbVictoire());

		this.btnRejouer    = new JButton("Rejouer");

		panelRejouer       = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelList          = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelVictoire      = new JPanel(new FlowLayout(FlowLayout.CENTER));

		panelRejouer.add(this.btnRejouer);
		panelList   .add(this.lblInfo   );

		panelVictoire.add(new JLabel(this.ctrl.getJoueur(1).getNom()  + " : "));
		panelVictoire.add(lblVictoireJ1);
		panelVictoire.add(new JLabel(this.ctrl.getJoueur(2).getNom()  + " : "));
		panelVictoire.add(lblVictoireJ2);

		/*************************************/
		/*   Positionnement Des Composants   */
		/*************************************/

		this.add(this.lblInfo ,BorderLayout .WEST );
		this.add(panelVictoire,BorderLayout.CENTER);
		this.add(panelRejouer ,BorderLayout .EAST );

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

	public void setLblVictoire()
	{
		this.lblVictoireJ1.setText(""+this.ctrl.getJoueur(1).getNbVictoire());
		this.lblVictoireJ2.setText(""+this.ctrl.getJoueur(2).getNbVictoire());
	}
}
