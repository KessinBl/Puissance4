package JeuPuissance4.vue;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import JeuPuissance4.Controleur;

public class FramePuissance4 extends JFrame
{
	private Controleur ctrl;
	private PanelPuissance4 panelPuissance4;
	private PanelInteraction panelInteraction;

	public FramePuissance4(Controleur ctrl)
	{
		/******************/
		/*   Info Frame   */
		/******************/
		this.setSize(1000,1000);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		/*******************************/
		/*   Creation Des Composants   */
		/*******************************/
		this.ctrl = ctrl;

		this.panelPuissance4  = new PanelPuissance4 (this.ctrl);
		this.panelInteraction = new PanelInteraction(this.ctrl);

		/*************************************/
		/*   Positionnement Des Composants   */
		/*************************************/

		this.add(this.panelPuissance4 ,BorderLayout.CENTER);
		this.add(this.panelInteraction,BorderLayout.SOUTH);

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/***********************/
	/*   Autres Methodes   */
	/***********************/

	public void afficherGrille()
	{
		String sRet  = "";
		String barre = "**********************\n";

		for(int lig = 0 ; lig < this.ctrl.getNbLigne(); lig++)
		{
			sRet += "|";

			for(int col = 0; col < this.ctrl.getNbColonne(); col++)
			{
				String value = this.ctrl.getCase(lig, col) == null ? " " : "" + this.ctrl.getCase(lig, col);

				sRet += String.format("%2s|",value);
			}

			sRet += "\n";
		}

		sRet += barre;

		for(int col = 0; col < this.ctrl.getNbColonne(); col++)
		{
			sRet+= String.format("%3s",""+(col+1));
		}

		sRet += "\n";

		System.out.println(sRet);
	}

	public void majIhm()
	{
		this.panelPuissance4.majIhm();
	}

	public void gagner()
	{
		this.panelPuissance4.gagner();
	}

	public void rejouer()
	{
		this.panelPuissance4.rejouer();
	}

	public void ajouterInformation(String message)
	{
		this.panelInteraction.ajouterInformation(message);
	}

	public void egalite()
	{
		JOptionPane.showMessageDialog(this,"Égalité..");
	}

	public void victoire(String nomJoueur)
	{
		JOptionPane.showMessageDialog(this,nomJoueur + " est vainqueur");
	}

	public void setLblVictoire()
	{
		this.panelInteraction.setLblVictoire();
	}

	public void recommencer()
	{
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.panelInteraction.recommencer();
	}
}
