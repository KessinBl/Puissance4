package JeuPuissance4.modele;

public class Joueur 
{
	private static int nbInstance = 0;

	private String        nom;
	private int        numero;
	private int    nbVictoire;

	public Joueur (String nom)
	{
		this.nom        = nom;
		this.nbVictoire = 0;
		this.numero     = ++Joueur.nbInstance;
	}

	public String getNom()
	{
		return this.nom;
	}

	public int getNumero()
	{
		return this.numero;
	}

	public int getNbVictoire()
	{
		return this.nbVictoire;
	}

	public void setNbVictoire()
	{
		this.nbVictoire++;
	}


}
