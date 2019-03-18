package gui;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class SupprActivationBut  extends JButton {
	public SupprActivationBut(String string){
		super(string);
	    this.setPreferredSize(new Dimension(214,53));
		ImageIcon ic = new ImageIcon("SupprimerActivation.png");
		this.setIcon(ic);
		this.setOpaque(false);
		this.repaint();
		this.revalidate();
	}

}
