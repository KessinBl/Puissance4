package JeuPuissance4.vue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import JeuPuissance4.Controleur;

public class FrameDebutPartie extends JFrame implements ActionListener
{
	private Controleur ctrl;

	private JButton[]      tabBtnValider;
	private JButton         btnCommencer;

	private JTextField        txtJoueur1;
	private JTextField        txtJoueur2;

	private PanelFond          panelFond;

	public FrameDebutPartie(Controleur ctrl)
	{
		JPanel     panelTitre;
		JPanel        panelJ1;
		JPanel        panelJ2;
		JPanel panelCommencer;

		/******************/
		/*   Info Frame   */
		/******************/

		this.setTitle("Début de la partie");
		this.setAlwaysOnTop(true);
		this.setBackground(new Color(100,255,100));

		/*******************************/
		/*   Creation Des Composants   */
		/*******************************/

		this.ctrl = ctrl;

		this.panelFond = new PanelFond("JeuPuissance4/vue/images/Fond2.png");
		this.panelFond.setLayout(new GridLayout(4,1));
		this.setContentPane(panelFond);

		this.tabBtnValider    = new JButton[2];
		this.tabBtnValider[0] = new JButton("<html> <style> p{color : WHITE;}</style>  <p>Valider</p> </html>");
		this.tabBtnValider[1] = new JButton("<html> <style> p{color : WHITE;}</style>  <p>Valider</p> </html>");

		this.tabBtnValider[0].setBackground(Color.GRAY);
		this.tabBtnValider[1].setBackground(Color.GRAY);

		this.btnCommencer     = new JButton("<html> <style> h1{ color : WHITE;}</style> <h1> START </h1></html>");
		this.btnCommencer.setEnabled(false);
		this.btnCommencer.setBackground(new Color(205,20,20));

		this.txtJoueur1       = new JTextField(30);
		this.txtJoueur2       = new JTextField(30);

		panelTitre = new JPanel();
		panelTitre.setOpaque(false);
		panelTitre.add(new JLabel(new ImageIcon("JeuPuissance4/vue/images/Puissance4.png")));

		panelJ1 = new JPanel();
		panelJ1.add(new JLabel("Joueur 1 : "));
		panelJ1.add(this.txtJoueur1);
		panelJ1.add(this.tabBtnValider[0]);
		panelJ1.setOpaque(false);

		panelJ2 = new JPanel();
		panelJ2.add(new JLabel("Joueur 2 : "));
		panelJ2.add(this.txtJoueur2);
		panelJ2.add(this.tabBtnValider[1]);
		panelJ2.setOpaque(false);

		panelCommencer = new JPanel();
		panelCommencer.add(this.btnCommencer);
		panelCommencer.setOpaque(false);

		/*************************************/
		/*   Positionnement Des Composants   */
		/*************************************/
		this.add(panelTitre);
		this.add(panelJ1);
		this.add(panelJ2);
		this.add(panelCommencer);

		/*********************************/
		/*   Activation Des Composants   */
		/*********************************/

		this.tabBtnValider[0].addActionListener(this);
		this.tabBtnValider[1].addActionListener(this);
		this.btnCommencer    .addActionListener(this);

		this.dimensionerFenetre();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}

	/*****************************/
	/*   Écoute Des Composants   */
	/*****************************/
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.tabBtnValider[0])
		{
			if(!this.txtJoueur1.getText().trim().matches(".*[A-Za-z].*"))
			{
				JOptionPane.showMessageDialog(this,"Joueur 1 Entrez un nom valide","Erreur",JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				this.ctrl.affecterJoueur(1, this.txtJoueur1.getText().trim());
				this.tabBtnValider[0].setEnabled(false);
				this.txtJoueur1      .setEnabled(false);
			}
		}

		if(e.getSource() == this.tabBtnValider[1])
		{
			if(!this.txtJoueur2.getText().trim().matches(".*[A-Za-z].*"))
			{
				JOptionPane.showMessageDialog(this,"Joueur 2 Entrez un nom valide","Erreur",JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				this.ctrl.affecterJoueur(2, this.txtJoueur2.getText().trim());
				this.tabBtnValider[1].setEnabled(false);
				this.txtJoueur2      .setEnabled(false);
			}
		}

		this.btnCommencer.setEnabled(!this.tabBtnValider[0].isEnabled() && !this.tabBtnValider[1].isEnabled());

		if(e.getSource() == this.btnCommencer)
		{
			Controleur.debuterPartie = true;
			this.setVisible(false);
			this.ctrl.afficherIhm();
		}
	}

	public void recommencer()
	{
		this.txtJoueur1.setEnabled(true);
		this.txtJoueur2.setEnabled(true);

		this.txtJoueur1.setText("");
		this.txtJoueur2.setText("");

		this.tabBtnValider[0].setEnabled(true);
		this.tabBtnValider[1].setEnabled(true);

		this.btnCommencer.setEnabled(false);
		this.dimensionerFenetre();
	}

	private void dimensionerFenetre()
	{
		this.pack();

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

		int y = (int)(dimension.getHeight() - this.getHeight()) / 2;
		int x = (int)(dimension.getWidth () - this.getWidth ()) / 2; 

		this.setLocation(x,y);
	}

	private class PanelFond extends JPanel 
	{
		private Image backgroundImage;

		public PanelFond(String chemin) 
		{
			this.backgroundImage = new ImageIcon(chemin).getImage();
		}

		public void paintComponent(Graphics g) 
		{
			super.paintComponent(g);

			if (this.backgroundImage != null)
				g.drawImage(this.backgroundImage, 0, 0, getWidth(), getHeight(), this);
		}
	}

}
