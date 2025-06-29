package JeuPuissance4;

import JeuPuissance4.vue.FrameDebutPartie;
import JeuPuissance4.vue.FramePuissance4;


import JeuPuissance4.modele.Metier;

public class Controleur 
{
	public static boolean debuterPartie = false;
	private Metier          metier;
	private FramePuissance4    ihm;

	private Integer    joueurEnCours;

	public Controleur()
	{
		this.metier = new Metier();
		this.ihm    = new FramePuissance4(this);;

		this.joueurEnCours = this.joueurAuHasard();
	}

	/****************************/
	/*   Accesseurs attributs   */
	/****************************/

	public int getNbLigne()
	{
		return this.metier.getNbLigne();
	}

	public int getNbColonne()
	{
		return this.metier.getNbColonne();
	}

	public Integer getCase(int lig ,int col)
	{
		return this.metier.getCase(lig, col);
	}

	public int getJoueurEnCours()
	{
		return this.joueurEnCours;
	}


	/*******************************/
	/*   Modificateurs attributs   */
	/*******************************/

	public boolean affecterJoueur(int numJoueur,String nomJoueur)
	{
		return this.metier.affecterJoueur(numJoueur, nomJoueur);
	}

	public void setJoueurEnCours()
	{
		this.joueurEnCours = this.joueurEnCours == 1 ? 2 : 1;
	}

	/***********************/
	/*   Autres Methodes   */
	/***********************/

	private Integer joueurAuHasard()
	{
		return 1 + (int)(Math.random() * 3);
	}
	
	public boolean estGrillePleine()
	{
		return this.metier.estGrillePleine();
	}

	public boolean estPleine(int col)
	{
		return this.metier.estPleine(col);
	}

	public void setCase(Integer numJoueur,int col)
	{
		this.metier.setCase(numJoueur, col);
		this.ihm.majIhm();
		this.ihm.afficherGrille();
	}

	public void rejouer()
	{
		this.metier.rejouer();
		this.ihm   .rejouer();
		this.ihm   .majIhm ();
	}

	public void afficherGrille()
	{
		this.ihm.afficherGrille();
	}

	public void jouer()
	{
		String nomJ1,nomJ2;
		new FrameDebutPartie(this);

		nomJ1 = nomJ2 = "n";
		
		while(!Controleur.debuterPartie);
	
		System.out.println();

		nomJ1 = this.metier.getJoueur(1).getNom();
		nomJ2 = this.metier.getJoueur(2).getNom();

		int joueur = this.getJoueurEnCours();
		
		this.ihm = new FramePuissance4(this);
		this.ihm.afficherGrille();

		while( !this.metier.victoire() );
		

		if(this.metier.victoire())
		{
			System.out.println((joueur == 1 ? nomJ2 : nomJ1) + " vous avez gagnez");
		}

	}

	public static void main(String[] args)
	{
		new Controleur().jouer();
	}


}
