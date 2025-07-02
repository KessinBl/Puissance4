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
		this.ihm    = null;
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
		String nomJoueurEnCours ="";
		this.joueurEnCours = this.joueurEnCours == 1 ? 2 : 1;
		nomJoueurEnCours   = this.metier.getJoueur(this.joueurEnCours).getNom();
		this.ihm.ajouterInformation(nomJoueurEnCours + " à toi de jouer");
	}

	/***********************/
	/*   Autres Methodes   */
	/***********************/

	private Integer joueurAuHasard()
	{
		return 1 + (int)(Math.random() * 2);
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
		
		
		while (!Controleur.debuterPartie) 
		{
			try 
			{
				Thread.sleep(100); 
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
			

		nomJ1 = this.metier.getJoueur(1).getNom();
		nomJ2 = this.metier.getJoueur(2).getNom();

		int joueur = this.getJoueurEnCours();
		
		this.ihm = new FramePuissance4(this);
		this.ihm.afficherGrille();

		this.ihm.ajouterInformation(this.metier.getJoueur(this.getJoueurEnCours()).getNom() + " à toi de jouer");

		while( !this.metier.victoire() )
		{
			try 
			{
				Thread.sleep(100); 
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		
		

		if(this.metier.victoire())
		{
			System.out.println((joueur == 1 ? nomJ1 : nomJ2) + " vous avez gagné");
			this.ihm.ajouterInformation((joueur == 1 ? nomJ1 : nomJ2) + " vous avez gagné");
			this.ihm.gagner();
		}

	}

	public static void main(String[] args)
	{
		new Controleur().jouer();
	}


}
