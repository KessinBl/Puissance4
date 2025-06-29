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

	public Integer getCase(int lig ,int col)
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

	public int getNbLigne()
	{
		return Metier.NB_LIG;
	}

	public int getNbColonne()
	{
		return Metier.NB_COL;
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

	public void setCase(Integer numJoueur , int col)
	{
		boolean estVisite = false;
		if(!this.estPleine(col))
		{
			for(int lig = Metier.NB_LIG -1; lig >= 0; lig--)
			{
				if(!estVisite && this.grilleJeu[lig][col] == null)
				{
					this.grilleJeu[lig][col] = numJoueur;
					estVisite = true;
				}
			}
		}
	}

	/***********************/
	/*   Autres Methodes   */
	/***********************/

	public boolean estPleine(int col )
	{
		return this.grilleJeu[0][col] != null;
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
		// vérification de victoire en ligne
		for(int lig = 0; lig < Metier.NB_LIG; lig++)
		{
			for(int col = 0; col < Metier.NB_COL / 2; col++)
			{
				Integer caseActu = this.grilleJeu[lig][col];

				if(caseActu != null)
				{
					//j'utilise ça pour éviter d'utiliser break (Smiley qui rigole)
					int suivant = 0;

					for(int cpt = 0; cpt < 3; cpt++)
					{
						if(this.grilleJeu[lig][col + (cpt + 1)] == caseActu)
						{
							suivant++;
						}
					}

					if(suivant == 3) return true;
				}
			}
		}

		// vérification de victoire en colonne
		for(int lig = 0; lig < Metier.NB_LIG / 2; lig++)
		{
			for(int col = 0; col < Metier.NB_COL ; col++)
			{
				Integer caseActu = this.grilleJeu[lig][col];

				if(caseActu != null)
				{
					//j'utilise ça pour éviter d'utiliser break (Smiley qui rigole)
					int suivant = 0;

					for(int cpt = 0; cpt < 3; cpt++)
					{
						if(this.grilleJeu[lig + (cpt + 1)][col] == caseActu)
						{
							suivant++;
						}
					}

					if(suivant == 3) return true;
				}
			}
		}

		// vérification de victoire en diagonale vers la droite
		for(int lig = 0; lig < Metier.NB_LIG / 2; lig++)
		{
			for(int col = 0; col < Metier.NB_COL / 2; col++)
			{
				Integer caseActu = this.grilleJeu[lig][col];

				if(caseActu != null)
				{
					//j'utilise ça pour éviter d'utiliser break (Smiley qui rigole)
					int suivant = 0;

					for(int cpt = 0; cpt < 3; cpt++)
					{
						if(this.grilleJeu[lig + (cpt + 1)][col + (cpt + 1)] == caseActu)
						{
							suivant++;
						}
					}

					if(suivant == 3) return true;
				}
			}
		}

		// vérification de victoire en diagonale vers la gauche
		for(int lig = Metier.NB_LIG -1; lig >= Metier.NB_LIG / 2; lig--)
		{
			for(int col = Metier.NB_COL -1; col >= Metier.NB_COL / 2; col--)
			{
				Integer caseActu = this.grilleJeu[lig][col];

				if(caseActu != null)
				{
					//j'utilise ça pour éviter d'utiliser break (Smiley qui rigole)
					int suivant = 0;

					for(int cpt = 0; cpt < 3; cpt++)
					{
						if(this.grilleJeu[lig - (cpt + 1)][col - (cpt + 1)] == caseActu)
						{
							suivant++;
						}
					}

					if(suivant == 3) return true;
				}
			}
		}

		return false;
	}

}