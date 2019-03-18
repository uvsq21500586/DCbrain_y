package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import listener.Controler2;
import objets.Lien;

public class Gui2 extends JFrame {
	private JComboBox comboproduit;
	private JComboBox combojour;
	private JComboBox combomois;
	private JComboBox comboannee;
	private JComboBox comboligne;
	private JTextField quantite;
	private ValideBut valider;

	public Gui2(Gui gui,ArrayList<Lien> lignespossibles) throws ParseException{
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date vdatedebut = formater.parse(gui.getDatedebut());
		Date vdatefin = formater.parse(gui.getDatefin());
		
		
		//créer la fenetre
		ImageIcon bg = new ImageIcon("src/background3.jpg");
		JLabel g = new JLabel(bg);
		g.setVisible(true);
		try {
			final Image backgroundImage = javax.imageio.ImageIO.read(new File("background3.jpg"));
			setContentPane(new JPanel(new BorderLayout()) {
				@Override public void paintComponent(Graphics g) {
		            g.drawImage(backgroundImage, 0, 0, null);
		        }
		    });
		} catch (IOException e) {
		    throw new RuntimeException(e);
		}
		this.add(g);
		setResizable(false); 
		setSize(702, 397);
		this.getContentPane().setLayout(null);
		this.setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		comboligne = new JComboBox();
		for (int i=0;i<lignespossibles.size();i++){
			comboligne.addItem(lignespossibles.get(i).getName());
		}
		this.add(comboligne);
		comboligne.setBounds(150, 40, 200, 40);
		JTextField quantite = new JTextField();
		this.add(quantite);
		quantite.setBounds(150, 100, 150, 40);
		
		//date
		//annee
		comboannee = new JComboBox();
		Calendar myCalendar = GregorianCalendar.getInstance();
		myCalendar.setTime(vdatedebut);
		//int anneedebut = vdatedebut.getYear();
		int anneedebut = myCalendar.get(Calendar.YEAR);
		myCalendar.setTime(vdatefin);
		int anneefin = myCalendar.get(Calendar.YEAR);
		for (int i=anneedebut;i<=anneefin;i++){
			String a = Integer.toString(i);
			comboannee.addItem(a);
		}
		this.add(comboannee);
		comboannee.setBounds(220, 150, 80, 40);
		
		//mois
		combomois = new JComboBox();
		for (int i=1;i<=12;i++){
			String m = Integer.toString(i);
			if (i<10){
				m = "0" + m;
			}
			combomois.addItem(m);
		}
		this.add(combomois);
		combomois.setBounds(320, 150, 50, 40);
		
		combojour = new JComboBox();
		for (int i=1;i<=31;i++){
			String m = Integer.toString(i);
			if (i<10){
				m = "0" + m;
			}
			combojour.addItem(m);
		}
		this.add(combojour);
		combojour.setBounds(390, 150, 50, 40);
		
		//produits
		JLabel tproduit= new JLabel("Type Produit:");
		this.add(tproduit);
		tproduit.setFont(new Font("Lucida Console", Font.BOLD, 16));
		tproduit.setForeground(Color.YELLOW);
		tproduit.setBounds(320, 100, 150, 40);
		
		comboproduit = new JComboBox();
		for (int i=0;i<gui.getListproduits().size();i++){
			comboproduit.addItem(gui.getListproduits().get(i).getName());
		}
		this.add(comboproduit);
		comboproduit.setBounds(480, 100, 100, 40);
		
		valider = new ValideBut("Valider");
		this.add(valider);
		valider.setBounds(280, 300, 159, 59);
		
		this.repaint();
		this.validate();
		
		new Controler2(gui,this);
	}

	public JComboBox getComboproduit() {
		return comboproduit;
	}

	public void setComboproduit(JComboBox comboproduit) {
		this.comboproduit = comboproduit;
	}

	public JComboBox getCombojour() {
		return combojour;
	}

	public void setCombojour(JComboBox combojour) {
		this.combojour = combojour;
	}

	public JComboBox getCombomois() {
		return combomois;
	}

	public void setCombomois(JComboBox combomois) {
		this.combomois = combomois;
	}

	public JComboBox getComboannee() {
		return comboannee;
	}

	public void setComboannee(JComboBox comboannee) {
		this.comboannee = comboannee;
	}

	public JComboBox getComboligne() {
		return comboligne;
	}

	public void setComboligne(JComboBox comboligne) {
		this.comboligne = comboligne;
	}

	public JTextField getQuantite() {
		return quantite;
	}

	public void setQuantite(JTextField quantite) {
		this.quantite = quantite;
	}

	public ValideBut getValider() {
		return valider;
	}

	public void setValider(ValideBut valider) {
		this.valider = valider;
	}
	
	
}
