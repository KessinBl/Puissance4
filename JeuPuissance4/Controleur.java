package JeuPuissance4;

import JeuPuissance4.vue.FrameDebutPartie;
import JeuPuissance4.vue.FramePuissance4;

import javax.swing.JOptionPane;

import JeuPuissance4.modele.Joueur;
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
		if(!this.metier.victoire())
		{
			String nomJoueurEnCours ="";
			this.joueurEnCours = this.joueurEnCours == 1 ? 2 : 1;
			nomJoueurEnCours   = this.metier.getJoueur(this.joueurEnCours).getNom();
			this.ihm.ajouterInformation(nomJoueurEnCours + " à toi de jouer");
		}
	}

	/***********************/
	/*   Autres Methodes   */
	/***********************/

	private Integer joueurAuHasard()
	{
		int cpt = 0;
		while(cpt < 20)
		{
			System.out.println( 1 + (int)(Math.random() * 2));
			cpt ++;
		}
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

		if(this.metier.victoire())
		{
			String nomJoueur = this.metier.getJoueur(this.getJoueurEnCours()).getNom();

			this.metier.getJoueur(this.getJoueurEnCours()).setNbVictoire();
			
			this.ihm.gagner();
			this.ihm.victoire(nomJoueur);
			this.ihm.setLblVictoire();
			this.ihm.ajouterInformation(nomJoueur + " est vainqueur");

			System.out.println( nomJoueur + " est vainqueur");
		}

		if(!this.metier.victoire() && this.estGrillePleine() )
		{
			this.ihm.egalite();
			this.ihm.gagner();
		}
	}

	public void rejouer()
	{
		this.metier.rejouer();
		this.ihm   .rejouer();
		this.ihm   .majIhm ();
		
		this.jouer();
	}

	public void afficherGrille()
	{
		this.ihm.afficherGrille();
	}

	public Joueur getJoueur(int numJoueur)
	{
		return this.metier.getJoueur(numJoueur);
	}

	public void jouer()
	{
		if(!Controleur.debuterPartie)
		{
			new FrameDebutPartie(this);
			
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
			
			this.ihm = new FramePuissance4(this);
		}

		this.ihm.afficherGrille();

		this.ihm.ajouterInformation(this.metier.getJoueur(this.getJoueurEnCours()).getNom() + " à toi de jouer");

	}

	public static void main(String[] args)
	{
		new Controleur().jouer();

	}


}
