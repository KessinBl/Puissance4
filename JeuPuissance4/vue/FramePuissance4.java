package JeuPuissance4.vue;

import javax.swing.JFrame;

import JeuPuissance4.Controleur;

public class FramePuissance4 extends JFrame
{
	private Controleur ctrl;
	private PanelPuissance4 panelPuissance4;

	public FramePuissance4(Controleur ctrl)
	{
		/******************/
		/*   Info Frame   */
		/******************/
		

		/*******************************/
		/*   Creation Des Composants   */
		/*******************************/
		this.ctrl = ctrl;

		this.panelPuissance4 = new PanelPuissance4(this.ctrl);

		/*************************************/
		/*   Positionnement Des Composants   */
		/*************************************/

		this.add(this.panelPuissance4);


		/*********************************/
		/*   Activation Des Composants   */
		/*********************************/
		this.pack();
		this.setVisible(true);
	}

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

				sRet+= String.format("%2s|",value);
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

	public void rejouer()
	{
		this.panelPuissance4.rejouer();
	}
}
