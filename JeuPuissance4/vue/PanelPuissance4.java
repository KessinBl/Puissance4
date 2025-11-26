package JeuPuissance4.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import JeuPuissance4.Controleur;

public class PanelPuissance4 extends JPanel implements ActionListener
{
	private Controleur      ctrl;
	private JButton [] tabButtons;
	private JPanel   panelDessin;

	public PanelPuissance4(Controleur ctrl)
	{
		JPanel panelButtons;

		this.setLayout(new BorderLayout());

		/*******************************/
		/*   Creation Des Composants   */
		/*******************************/
		this.ctrl = ctrl;

		this.tabButtons = new JButton[this.ctrl.getNbColonne()];
		for(int cpt = 0; cpt < this.tabButtons.length; cpt++)
		{
			this.tabButtons[cpt] = new JButton("↓");
		}

		panelButtons = new JPanel(new GridLayout(1,this.ctrl.getNbColonne()-1));
		for(JButton b : this.tabButtons)
		{
			panelButtons.add(b);
		}

		this.panelDessin = new JPanel();
		this.panelDessin.setOpaque(false);
		

		/*************************************/
		/*   Positionnement Des Composants   */
		/*************************************/

		this.add(panelButtons,BorderLayout.NORTH );
		this.add(panelDessin ,BorderLayout.CENTER);

		/*********************************/
		/*   Activation Des Composants   */
		/*********************************/

		for(JButton b : this.tabButtons)
		{
			b.addActionListener(this);
		}
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		int nbLignes  = this.ctrl.getNbLigne();
		int nbColonnes = this.ctrl.getNbColonne();

		// On récupère l’espace disponible pour la grille (sous les flèches)
		int topMargin = 60;    // laisse la place aux flèches
		int leftMargin = 20;
		int rightMargin = 20;
		int bottomMargin = 20;

		int zoneWidth  = this.getWidth()  - leftMargin - rightMargin;
		int zoneHeight = this.getHeight() - topMargin  - bottomMargin;

		// Taille d'une cellule → carrée
		int cellSize = Math.min(zoneWidth / nbColonnes, zoneHeight / nbLignes);

		// Décalage réel si la grille ne prend pas tout l’espace horizontal
		int totalGridWidth = cellSize * nbColonnes;
		int xOffset = (this.getWidth() - totalGridWidth) / 2;

		// Couleur du plateau
		g2.setColor(new Color(10, 20, 255));
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());

		// --- DESSIN DES TROUS BLANCS ---
		g2.setColor(Color.white);
		for (int lig = 0; lig < nbLignes; lig++)
		{
			for (int col = 0; col < nbColonnes; col++)
			{
				int x = xOffset + col * cellSize;
				int y = topMargin + lig * cellSize;

				int circleSize = (int)(cellSize * 0.8);
				int gap = (cellSize - circleSize) / 2;

				g2.fillOval(x + gap, y + gap, circleSize, circleSize);
			}
		}

		// --- DESSIN DES JETONS ---
		for (int lig = 0; lig < nbLignes; lig++)
		{
			for (int col = 0; col < nbColonnes; col++)
			{
				Integer value = this.ctrl.getCase(lig, col);
				if (value == null) continue;

				int x = xOffset + col * cellSize;
				int y = topMargin + lig * cellSize;

				int circleSize = (int)(cellSize * 0.8);
				int gap = (cellSize - circleSize) / 2;

				if (value == 1)
					g2.setColor(new Color(255, 10, 10));   // Rouge
				else if (value == 2)
					g2.setColor(new Color(200, 200, 10));  // Jaune

				g2.fillOval(x + gap, y + gap, circleSize, circleSize);
			}
		}
	}


	/*****************************/
	/*   Écoute Des Composants   */
	/*****************************/
	public void actionPerformed(ActionEvent e)
	{
		if(this.ctrl.estGrillePleine())
		{
			for(JButton b : this.tabButtons)
			{
				b.setEnabled(false);
			}
		}

		for(int cpt = 0; cpt < this.tabButtons.length; cpt++)
		{
			if(e.getSource() == this.tabButtons[cpt])
			{
				if(!this.ctrl.estPleine(cpt))
				{
					this.ctrl.setCase(this.ctrl.getJoueurEnCours(), cpt);
					this.ctrl.setJoueurEnCours();
				}
			}
		}
	}

	/***********************/
	/*   Autres Methodes   */
	/***********************/
	public void gagner()
	{
		for(JButton b : this.tabButtons)
		{
			b.setEnabled(false);
		}
	}

	public void rejouer()
	{
		for(JButton b : this.tabButtons)
		{
			b.setEnabled(true);
		}
	}

	public void majIhm()
	{
		this.panelDessin.repaint();
	}
}
