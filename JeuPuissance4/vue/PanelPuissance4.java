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
	private JButton [] taButtons;
	private JPanel   panelDessin;

	public PanelPuissance4(Controleur ctrl)
	{
		JPanel panelButtons;

		this.setLayout(new BorderLayout());

		/*******************************/
		/*   Creation Des Composants   */
		/*******************************/
		this.ctrl = ctrl;

		this.taButtons = new JButton[this.ctrl.getNbColonne()];
		for(int cpt = 0; cpt < this.taButtons.length; cpt++)
		{
			this.taButtons[cpt] = new JButton("↓");
		}

		panelButtons = new JPanel(new GridLayout(1,this.ctrl.getNbColonne()-1));
		for(JButton b : this.taButtons)
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

		for(JButton b : this.taButtons)
		{
			b.addActionListener(this);
		}
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		int x = 35;
		int y = 100;

		g = (Graphics2D) g;

		g.setColor(new Color(10,20,255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g.setColor(new Color(255,255,255));
		for(int lig = 0; lig < this.ctrl.getNbLigne(); lig++)
		{
			for(int col = 0; col < this.ctrl.getNbColonne(); col++)
			{
				g.fillOval(x + (col * 210), y, 100, 100);
			}
			x = 35;
			y += 130;
		}

		x = 35;
		y = 100;
		for(int lig = 0; lig < this.ctrl.getNbLigne(); lig++)
		{
			for(int col = 0; col < this.ctrl.getNbColonne(); col++)
			{
				if(this.ctrl.getCase(lig, col) == 1)
				{
					g.setColor(new Color(255,10,10));
					g.fillOval(x + (col * 210), y, 100, 100);
				}

				if(this.ctrl.getCase(lig, col) == 1)
				{
					g.setColor(new Color(255,10,10));
					g.fillOval(x + (col * 210), y, 100, 100);
				}
			}
			x = 35;
			y += 130;
		}
		
	}

	public void actionPerformed(ActionEvent e)
	{

	}

	public void majIhm()
	{
		this.panelDessin.repaint();
	}
}
