package JeuPuissance4;

import JeuPuissance4.vue.FrameDebutPartie;
import JeuPuissance4.vue.FramePuissance4;

import java.util.Scanner;

import JeuPuissance4.modele.Metier;

public class Controleur 
{
	private Metier          metier;
	private FramePuissance4    ihm;

	public Controleur()
	{
		this.metier = new Metier();
		this.ihm    = new FramePuissance4(this);
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

	/*******************************/
	/*   Modificateurs attributs   */
	/*******************************/

	public boolean affecterJoueur(int numJoueur,String nomJoueur)
	{
		return this.metier.affecterJoueur(numJoueur, nomJoueur);
	}

	/***********************/
	/*   Autres Methodes   */
	/***********************/

	public void rejouer()
	{
		this.metier.rejouer();
		this.ihm.majIhm();
	}

	public void jouer()
	{
		FrameDebutPartie f = new FrameDebutPartie(this);
		String nomJ1,nomJ2;
		nomJ1 = nomJ2 = "n";
		
		while(!f.estDebuter());
	
		System.out.println();

		nomJ1 = f.getNomJ1();
		nomJ2 = f.getNomJ2();
		

		Scanner sc = new Scanner(System.in);

		int joueur = 1 + (int) (Math.random() * 3);

		this.ihm.afficherGrille();
		while(!this.metier.victoire() )
		{
			int col;
			do
			{
				System.out.print( (joueur == 1 ? nomJ1 : nomJ2 ) + " choississez une colonne : ");
				col = sc.nextInt();
			}
			while(col > this.getNbColonne() || col < 0);

			this.metier.setCase(joueur, col - 1);
			this.ihm.afficherGrille();
			
			joueur = (joueur == 1) ?  2 : 1;

		}

		if(this.metier.victoire())
		{
			System.out.println((joueur == 1 ? nomJ2 : nomJ1) + " vous avez gagnez");
		}

		sc.close();
	}

	public static void main(String[] args)
	{
		new Controleur().jouer();
	}


}
