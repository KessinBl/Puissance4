package JeuPuissance4.vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import JeuPuissance4.Controleur;

public class PanelInteraction extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private JButton    btnRejouer;
	private JButton    btnRecommencer;

	private JLabel     lblInfo;
	private JLabel     lblNomJ1;
	private JLabel     lblNomJ2;
	private JLabel     lblVictoireJ1;
	private JLabel     lblVictoireJ2;

	public PanelInteraction (Controleur ctrl)
	{
		JPanel panelBoutons;
		JPanel panelList;
		JPanel panelVictoire;

		
		this.setLayout(new BorderLayout());

		/*******************************/
		/*   Creation Des Composants   */
		/*******************************/

		this.ctrl = ctrl;

		this.lblInfo       = new JLabel("" ,JLabel.RIGHT);
		this.lblNomJ1      = new JLabel(   this.ctrl.getJoueur(1).getNom() + " : ");
		this.lblNomJ2      = new JLabel(   this.ctrl.getJoueur(2).getNom() + " : ");
		System.out.println(this.ctrl.getJoueur(1).getNom());
		this.lblVictoireJ1 = new JLabel(""+this.ctrl.getJoueur(1).getNbVictoire());
		this.lblVictoireJ2 = new JLabel(""+this.ctrl.getJoueur(2).getNbVictoire());

		this.btnRejouer     = new JButton("Rejouer");
		this.btnRecommencer = new JButton("Recommencer");

		panelBoutons       = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelList          = new JPanel(new FlowLayout(FlowLayout.RIGHT ));
		panelVictoire      = new JPanel(new FlowLayout(FlowLayout.CENTER));

		panelBoutons.add(this.btnRejouer);
		panelBoutons.add(this.btnRecommencer);
		panelList   .add(this.lblInfo   );

		panelVictoire.add(this.lblNomJ1);
		panelVictoire.add(lblVictoireJ1);
		panelVictoire.add(this.lblNomJ2);
		panelVictoire.add(lblVictoireJ2);

		/*************************************/
		/*   Positionnement Des Composants   */
		/*************************************/

		this.add(this.lblInfo ,BorderLayout.WEST  );
		this.add(panelVictoire,BorderLayout.CENTER);
		this.add(panelBoutons ,BorderLayout.EAST  );

		/*********************************/
		/*   Activation Des Composants   */
		/*********************************/

		this.btnRejouer    .addActionListener(this);
		this.btnRecommencer.addActionListener(this);
	}

	/*****************************/
	/*   Écoute Des Composants   */
	/*****************************/
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnRejouer)
		{
			this.ctrl.rejouer();
		}

		if(e.getSource() == this.btnRecommencer)
		{
			this.ctrl.recommencer();
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

	public void recommencer()
	{
		this.lblNomJ1.setText(this.ctrl.getJoueur(1).getNom() + " : ");
		this.lblNomJ2.setText(this.ctrl.getJoueur(2).getNom() + " : ");

		this.setLblVictoire();
	}
}
