package JeuPuissance4.vue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.GridLayout;
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

	private boolean estDebuter;

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
		this.setLayout(new GridLayout(4,1));

		/*******************************/
		/*   Creation Des Composants   */
		/*******************************/

		this.ctrl = ctrl;

		this.estDebuter = false;

		this.tabBtnValider = new JButton[2];
		this.tabBtnValider[0] = new JButton("Valider");
		this.tabBtnValider[1] = new JButton("Valider");

		this.btnCommencer     = new JButton("Commencer la partie");
		this.btnCommencer.setEnabled(false);

		this.txtJoueur1       = new JTextField(30);
		this.txtJoueur2       = new JTextField(30);

		panelTitre = new JPanel();
		panelTitre.setOpaque(false);
		panelTitre.add(new JLabel(new ImageIcon("JeuPuissance4/vue/images/Puissance4.png")));

		panelJ1 = new JPanel();
		panelJ1.add(new JLabel("Joueur 1 : "));
		panelJ1.add(this.txtJoueur1);
		panelJ1.add(this.tabBtnValider[0]);

		panelJ2 = new JPanel();
		panelJ2.add(new JLabel("Joueur 2 : "));
		panelJ2.add(this.txtJoueur2);
		panelJ2.add(this.tabBtnValider[1]);

		panelCommencer = new JPanel();
		panelCommencer.add(this.btnCommencer);

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

		this.pack();

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

		int y = (int)(dimension.getHeight() - this.getHeight()) / 2;
		int x = (int)(dimension.getWidth () - this.getWidth ()) / 2; 

		this.setLocation(x,y);
		
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
			}
		}

		this.btnCommencer.setEnabled(!this.tabBtnValider[0].isEnabled() && !this.tabBtnValider[1].isEnabled());

		if(e.getSource() == this.btnCommencer)
		{
			this.estDebuter = true;
			this.dispose();
		}
	}

	public boolean estDebuter()
	{
		return this.estDebuter;
	}

	public String getNomJ2() 
	{ 
		if(!this.tabBtnValider[1].isEnabled() && this.txtJoueur2.getText().trim().matches(".*[A-Za-z].*"))
			return this.txtJoueur2.getText().trim();
		return "";
	}
	public String getNomJ1() 
	{ 
		if(!this.tabBtnValider[0].isEnabled() && this.txtJoueur1.getText().trim().matches(".*[A-Za-z].*"))
			return this.txtJoueur1.getText().trim();
		return "";
	}
}
