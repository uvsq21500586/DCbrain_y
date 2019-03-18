package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import gui.*;
import objets.*;

public class Controler implements ActionListener,MouseListener {
	Gui gui;
	
	public Controler(Gui gui) {
		this.gui = gui;
		gui.getExit().addActionListener(this);
		gui.getSimulation().addActionListener(this);
		gui.getAjoutactivation().addActionListener(this);
		addActionListeners();
	}

	private void addActionListeners() {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() instanceof ExitBut){
			System.exit(0);
		}
		if (arg0.getSource() instanceof SimulBut){
			gui.simuler();
			gui.getEvaluation().setText("evaluation:" + gui.getValeurevaluation());
			gui.repaint();
			gui.validate();
			return;
		}
		if (arg0.getSource() instanceof AjoutActivationBut){
			String options[] = {"alleger usine","approvisionner entrepot","servir client","annuler"};
			
			int x = JOptionPane.showOptionDialog(gui, "Pourquoi voulez-vous activer une ligne ?",null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,options, options[3]);
			
			if (x<3){
				ArrayList<Lien> lignespossibles = new ArrayList<Lien>();
				for (int i=0;i<gui.getListliens().size();i++){
					Noeud s = gui.getListliens().get(i).getSource();
					Noeud d = gui.getListliens().get(i).getDest();
					if (x==0 && !(d instanceof Client) && s instanceof Usine){
						lignespossibles.add(gui.getListliens().get(i));
					} else if (x==1 && !(d instanceof Client)) {
						lignespossibles.add(gui.getListliens().get(i));
					}  else if (x==2 && d instanceof Client) {
						lignespossibles.add(gui.getListliens().get(i));
					}
				}
				
				try {
					new Gui2(gui,lignespossibles);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return;
		}
	}

}
