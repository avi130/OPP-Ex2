package gui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileSystemView;

import algorithms.Graph_Algo;
import algorithms.graph_algorithms;
import dataStructure.DGraph;
import dataStructure.edge;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node;
import dataStructure.node_data;
import utils.Point3D;


public class window extends JFrame implements ActionListener, MouseListener {

	
	MenuItem Node;
	//JTextField tf;
	JButton load;
	JButton save;
   
   // JFileChooser jfc;
	int key;
	DGraph graph;
	
	Point3D p;
	graph graph2;
	
	
	public window(graph g) {
		this.graph2=g;
		initGUI();
	}
	

	
	public  window() {
		// TODO Auto-generated constructor stub
		initGUI();
	}
	
	private void initGUI() 
	{
		this.setSize(1000, 1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu");
		menuBar.add(menu);
		this.setMenuBar(menuBar);
		
		MenuItem item1 = new MenuItem("load");
		item1.addActionListener(this);
		load= new JButton("load");
		
		MenuItem item2 = new MenuItem("save");
		item2.addActionListener(this);
		save = new JButton("save");
		
		
		
		menu.add(item1);
		menu.add(item2);
		
		
		
		Menu menu2 = new Menu("Algo");
		menuBar.add(menu2);
		this.setMenuBar(menuBar);
	
		
		
		
		Menu menu3 = new Menu("Tests");
		menuBar.add(menu3);
		this.setMenuBar(menuBar);
		
		
		
		
		this.addMouseListener(this);
	}
	
	
	
	public void paint(Graphics g)
	{
		super.paint(g);
		
		
		for (node_data p : graph2.getV() ) 
		{
			g.setColor(Color.BLUE);
			Point3D srcPoint = p.getLocation();
			g.fillOval((int)srcPoint.x(), (int)srcPoint.y(), 10, 10);
			
			
			for(edge_data e: graph2.getE(p.getKey())) {
				
				g.setColor(Color.RED);
				
				Point3D destPoint = graph2.getNode(e.getDest()).getLocation();
				g.drawLine((int)srcPoint.x(), (int)srcPoint.y(), (int)destPoint.x(), (int)destPoint.y());
				
				g.fillOval((int)destPoint.x(), (int)destPoint.y(), 10, 10);
				g.drawString(""+e.getWeight(), (int)((srcPoint.x()+destPoint.x())/2),(int)((srcPoint.y()+destPoint.y())/2));														
			
				g.setColor(Color.yellow);
				g.fillOval((int)((0.9*srcPoint.x()+0.1*destPoint.x())), (int)((0.9*srcPoint.y()+0.1*destPoint.y())), 10, 10);
				
			}
			
		}
	}
	

	private void save()
	{
		 JFileChooser fileChooser = new JFileChooser();
		 Graph_Algo ga = new Graph_Algo();
			ga.init(graph2);
		    int retval = fileChooser.showSaveDialog(save);
		    if (retval == JFileChooser.APPROVE_OPTION) {
		    	try {
					ga.save(fileChooser.getSelectedFile()+".txt");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		    }
		
	}
	
	
	private void load()
	{
		 JFileChooser fileChooser = new JFileChooser();
		 Graph_Algo ga = new Graph_Algo();
		    int retval = fileChooser.showSaveDialog(load);
		    if (retval == JFileChooser.APPROVE_OPTION) {
		    	try {
		    		File selectedFile = fileChooser.getSelectedFile();
					ga.init(selectedFile.getAbsolutePath());
					this.graph2 = ga.copy();
					repaint();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		    }
		    
		   
		/*
		    Graph_Algo ga = new Graph_Algo();
			JFileChooser jf = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			int returnV = jf.showOpenDialog(null);
			if (returnV == JFileChooser.APPROVE_OPTION) {
				File selectedFile = jf.getSelectedFile();
				ga.init(selectedFile.getAbsolutePath());
				this.graph2 = ga.copy();
				repaint();
			}
		    
		*/
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
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String str = e.getActionCommand();
		
		if(str.equals("save"))
		{
			save();
		}
		if(str.equals("load"))
		{
			load();
		}
		
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		graph x= new DGraph();
		
		node_data a= new node(1,new Point3D(800,705,10),3);
		node_data b= new node(2,new Point3D(600,350,90),12);
		node_data c= new node(3,new Point3D(300,250,80),7);
		node_data d= new node(4,new Point3D(900,550,60),6);
		
		edge_data aa=new edge(200, 400, 10);
		edge_data ab=new edge(100, 190, 60);
		edge_data ac=new edge(500, 800, 20);
		edge_data ad=new edge(400, 720, 70);
		
		x.addNode(a);
		x.addNode(b);
		x.addNode(c);
		x.addNode(d);
		x.connect(1, 2, 5);
		x.connect(1, 3, 5);
		x.connect(1, 4, 5);
		x.connect(2, 3, 10);
		x.connect(4, 3, 10);
		x.connect(3, 2, 10);
		x.connect(2, 1, 10);
		x.connect(2, 4, 10);
		x.connect(4, 1, 10);
		
		
		
		
		
		window GUI = new window();
		GUI.setVisible(true);
	}

}
