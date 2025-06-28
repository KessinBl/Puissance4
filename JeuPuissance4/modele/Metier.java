package JeuPuissance4.modele;

public class Metier
{
	private static final int NB_LIG = 6;
	private static final int NB_COL = 7;

	private Integer[][] grilleJeu;

	private Joueur  joueur1;
	private Joueur  joueur2;

	public Metier()
	{
		this.grilleJeu = new Integer[Metier.NB_LIG][Metier.NB_COL];

		this.joueur1   = null;
		this.joueur2   = null;
	}

	/****************************/
	/*   Accesseurs attributs   */
	/****************************/

	public Integer getCase(int lig,int col)
	{
		if(this.grilleJeu[lig][col] != null) return this.grilleJeu[lig][col];
		return null;
	}

	public Joueur getJoueur(int joueur)
	{
		if(joueur == 1) return joueur1;
		if(joueur == 2) return joueur2;

		return null;
	}

	/*******************************/
	/*   Modificateurs attributs   */
	/*******************************/

	public boolean affecterJoueur(int numJoueur,String nomJoueur)
	{
		if(numJoueur != 1 && numJoueur != 2) return false;
		if(nomJoueur == null)                return false;

		if(numJoueur == 1) this.joueur1 = new Joueur(nomJoueur);
		else               this.joueur2 = new Joueur(nomJoueur);

		return true;
	}

	public void setCase(int numJoueur)
	{
		
	}

	/***********************/
	/*   Autres Methodes   */
	/***********************/

	public boolean estPleine(int col )
	{
		for(int lig = 0; lig < Metier.NB_LIG; lig++)
		{
			if(this.grilleJeu[lig][col] == null)
				return false;
		}
		return true;
	}

	public boolean estGrillePleine()
	{
		for(int lig = 0; lig < Metier.NB_LIG; lig++)
		{
			for(int col = 0; col < Metier.NB_COL; col++ )
			{
				if(this.grilleJeu[lig][col] == null)
					return false;
			}
		}

		return true;
	}

	public void rejouer()
	{
		for(int lig = 0; lig < Metier.NB_LIG; lig++)
		{
			for(int col = 0; col < Metier.NB_COL; col++ )
			{
				this.grilleJeu[lig][col] = null;
			}
		}
	}

	public boolean victoire()
	{
		return true;
	}

}
