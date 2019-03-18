package gui;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ValideBut  extends JButton {
	public ValideBut(String string){
		super(string);
	    this.setPreferredSize(new Dimension(214,53));
		ImageIcon ic = new ImageIcon("Valider.png");
		this.setIcon(ic);
		this.setOpaque(false);
		this.repaint();
		this.revalidate();
	}

}
