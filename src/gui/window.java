package gui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
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
import java.text.ParseException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.MaskFormatter;

import algorithms.Graph_Algo;
import algorithms.graph_algorithms;
import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import elements.edge;
import elements.node;
import utils.Point3D;


public class window extends JFrame implements ActionListener, MouseListener {

	public static boolean global_flag;
	public static int global_key;
	
	MenuItem Node;
	//JTextField tf;
	JButton load;
	JButton save;
	JButton isConnected;
	JButton shortestpathdist;
	JButton shortestpath;
	JButton TSP;

	int key;
	DGraph graph;

	Point3D p;
	graph graph2;


	public window(graph g) {
		this.graph2=g;
		initGUI();
	}

 

	private void initGUI() 
	{
		this.setSize(1500, 1500);
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



		Menu Algo = new Menu("Algo");
		menuBar.add(Algo);
		this.setMenuBar(menuBar);

		MenuItem item3 = new MenuItem("is Connected");
		item3.addActionListener(this);
		isConnected= new JButton("isConnected");

		MenuItem item4 = new MenuItem("shortest path dist");
		item4.addActionListener(this);
		shortestpathdist = new JButton("shortestpathdist");

		MenuItem item5 = new MenuItem("shortest path");
		item5.addActionListener(this);
		shortestpath = new JButton("shortestpath");

		MenuItem item6 = new MenuItem("TSP");
		item6.addActionListener(this);
		TSP = new JButton("TSP");

		Algo.add(item3);
		Algo.add(item4);
		Algo.add(item5);
		Algo.add(item6);


		this.addMouseListener(this);
	}



	public void paint(Graphics g)
	{
		super.paint(g);

		if(graph2!=null) {
			for (node_data p : graph2.getV() ) 
			{
				g.setColor(Color.BLUE);
				Point3D srcPoint = p.getLocation();
				g.fillOval((int)srcPoint.x(), (int)srcPoint.y(), 10, 10);
				g.drawString(""+p.getKey(), (int)srcPoint.x() ,(int)srcPoint.y()-10);														


				if(graph2.getE(p.getKey())!=null) {

					for(edge_data e: graph2.getE(p.getKey())) {


						g.setColor(Color.LIGHT_GRAY);
						if(e.getInfo()=="do" ) {
							e.setInfo("");;
							g.setColor(Color.black);}
						Point3D destPoint = graph2.getNode(e.getDest()).getLocation();
						g.drawLine((int)srcPoint.x(), (int)srcPoint.y(), (int)destPoint.x(), (int)destPoint.y());

						//g.fillOval((int)destPoint.x(), (int)destPoint.y(), 10, 10);
						g.drawString(""+e.getWeight(),(int)((0.1*srcPoint.x()+0.9*destPoint.x()+10)), (int)((0.1*srcPoint.y()+0.9*destPoint.y()+20)));														

						g.setColor(Color.yellow);
						g.fillOval((int)((0.1*srcPoint.x()+0.9*destPoint.x())), (int)((0.1*srcPoint.y()+0.9*destPoint.y())), 10, 10);
						g.setColor(Color.BLUE);
						g.drawString(""+e.getDest() , (int)destPoint.x(), (int)destPoint.y()-10);

					}
				}
			}
		}
	}

	private void TSP() {
		try {
			JFrame jinput2 = new JFrame();
			String nodes = JOptionPane.showInputDialog(jinput2,"Enter nodes whith  ,  between each one(without spaces) ");		
			List<Integer> nodesList= new LinkedList<Integer>();
			String temp;
			boolean flag=true;
			int input;
			for(int i=0;( i <nodes.length()) &&flag==true; i++) {
				temp="";
				if(nodes.charAt(i)!=',') {
					while(nodes.charAt(i)!=',') {
						temp+=nodes.charAt(i);
						if(i+1<nodes.length()) {
							i++;
						}
						else {
							flag=false;
							break;
						}

					}
					i--;
				}

				if(temp!="") {
					input = Integer.parseInt(temp);
					nodesList.add(input);

				}
				if(flag==false)
					break;
			}


			Graph_Algo ga = new Graph_Algo();
			ga.init(graph2);
			List <node_data> ans=ga.TSP(nodesList);
			String final_ans="";
			for (int i=0; i<ans.size(); i++) {
				if(i!=0)
					final_ans+=" -> "+ans.get(i).getKey();
				else
					final_ans+=""+ans.get(i).getKey();
			}
			JOptionPane.showMessageDialog(jinput2, "the path is: "+final_ans);
			repaint();

		} catch (Exception ex) {
				System.out.println("eror");
			ex.printStackTrace();
		}

	}



	private void shortestpath() {
		try {

			String fromS = JOptionPane.showInputDialog(null,"Enter From");		
			String to = JOptionPane.showInputDialog(null,"Enter To");
		

			int inputfrom = Integer.parseInt(fromS);
			int inputto = Integer.parseInt(to);
			Graph_Algo ga = new Graph_Algo();
			ga.init(graph2);
			
			boolean contains1=false;
			boolean contains2=false;
			List<node_data> nodesList= new LinkedList<node_data>(graph2.getV());
			for(node_data a : nodesList) {
				if(a.getKey()==inputfrom) {
					contains1=true;
				}
				if(a.getKey()==inputto) {
					contains2=true;
				}
				if(contains1 && contains2) {
					break;
				}
				
			}
			if(!contains1 || !contains2) {
				JOptionPane.showMessageDialog(null, "you insert a node number that not exist. try again. ");
					return;
		 }
			
			List <node_data> ans= ga.shortestPath(inputfrom, inputto);
			int x= ans.size();
			//	System.out.println(x);

			for (int j = 0; j < x-1; j++) {

				graph2.getEdge(ans.get(j).getKey(), ans.get(j+1).getKey()).setInfo("do");
				if(graph2.getEdge(ans.get(j+1).getKey(), ans.get(j).getKey())!=null)
				graph2.getEdge( ans.get(j+1).getKey(),ans.get(j).getKey()).setInfo("do");


			}
			repaint();
		} catch (Exception ex) {
			//	System.out.println("eror");
			ex.printStackTrace();
		}

	}


	private void shortestpathdist() {
		try {
			JFrame jinput2 = new JFrame();
			String fromS = JOptionPane.showInputDialog(jinput2,"Enter From");		
			String to = JOptionPane.showInputDialog(jinput2,"Enter To");
			int inputfrom = Integer.parseInt(fromS);
			int inputto = Integer.parseInt(to);
			Graph_Algo ga = new Graph_Algo();
			ga.init(graph2);
			
			
			boolean contains1=false;
			boolean contains2=false;
			List<node_data> nodesList= new LinkedList<node_data>(graph2.getV());
			for(node_data a : nodesList) {
				if(a.getKey()==inputfrom) {
					contains1=true;
				}
				if(a.getKey()==inputto) {
					contains2=true;
				}
				if(contains1 && contains2) {
					break;
				}
				
			}
			if(!contains1 || !contains2) {
				JOptionPane.showMessageDialog(null, "you insert a node number that not exist. try again  ");
					return;
			}
			

			double ans= ga.shortestPathDist(inputfrom, inputto);
			JOptionPane.showMessageDialog(jinput2, "the shortest way takes: "+ans);
			repaint();

		} catch (Exception ex) {
			System.out.println("eror");
			ex.printStackTrace();
		}

	}


	private void isConnected() {
		try {
			JFrame jinput2 = new JFrame();
			Graph_Algo ga = new Graph_Algo();
			ga.init(graph2);

			boolean ans= ga.isConnected();
			if (ans==true)
				JOptionPane.showMessageDialog(jinput2, "the graph is connected");
			else
				JOptionPane.showMessageDialog(jinput2, "the graph is not connected ! !");


			
		} catch (Exception ex) {
			System.out.println("eror");
			ex.printStackTrace();
		}

	}







	private void save()
	{
		JFileChooser fileChooser = new JFileChooser();
		Graph_Algo savaGraph = new Graph_Algo();
		savaGraph.init(graph2);
		int retval = fileChooser.showSaveDialog(save);
		if (retval == JFileChooser.APPROVE_OPTION) {
			try {
				savaGraph.save(fileChooser.getSelectedFile()+".txt");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}


	private void load()
	{
		JFileChooser fileChooser = new JFileChooser();
		Graph_Algo loadGraph = new Graph_Algo();
		int retval = fileChooser.showOpenDialog(load);
		if (retval == JFileChooser.APPROVE_OPTION) {
			try {
				File selectedFile = fileChooser.getSelectedFile();
				loadGraph.init(selectedFile.getAbsolutePath());
				this.graph2 = loadGraph.copy();
				repaint();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {
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
		try {
		global_flag=true;
		int x = e.getX();
		int y = e.getY();
		Point3D location=new Point3D(x, y);
		//node p = new node(location);
		List<node_data> nodes= new LinkedList<node_data>(graph2.getV());
		boolean flag=false;

		//int temp=nodes.get(nodes.size()).getKey();
		int temp=nodes.get(nodes.size()-1).getKey();
		int key=temp;

		while(flag==false) {
			key++;
			for(node_data nodekey: nodes)	{
				if(nodekey.getKey()==key) {
					flag=false;
					break;
				}
				else
					flag=true;
			}

		}
		boolean flag2=true;
		for(node_data nodekey: nodes)	{
			if(Math.abs(x-nodekey.getLocation().x())<30 && Math.abs(y-nodekey.getLocation().y())<30) {
				node p = new node(nodekey.getKey(),nodekey.getLocation());
				graph2.addNode(p);
				flag2=false;
				global_key= nodekey.getKey();
				global_flag=false;
				break;
			}


		}

		if(flag2) {
			node p = new node(key,location);
			graph2.addNode(p);
		}
		
		repaint();
		
	} catch (Exception ex) {
		ex.printStackTrace();
	}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		try {
		int x = e.getX();
		int y = e.getY();
		Point3D location=new Point3D(x, y);
		//node p = new node(location);
		List<node_data> nodes= new LinkedList<node_data>(graph2.getV());
		boolean flag=false;

		//int temp=nodes.get(nodes.size()).getKey();
		int temp=nodes.get(nodes.size()-1).getKey();
		int key=temp;

		while(flag==false) {
			key++;
			for(node_data nodekey: nodes)	{
				if(nodekey.getKey()==key) {
					flag=false;
					break;
				}
				else
					flag=true;
			}

		}

		boolean flag2=true;
		for(node_data nodekey: nodes)	{
			if(global_flag && Math.abs(x-nodekey.getLocation().x())<20 && Math.abs(y-nodekey.getLocation().y())<20) {
				graph2.connect( key-1,nodekey.getKey(), 10);
				flag2=false;
				break;
			}
			else if(global_flag==false && Math.abs(x-nodekey.getLocation().x())<20 && Math.abs(y-nodekey.getLocation().y())<20) {
				graph2.connect( global_key ,nodekey.getKey(), 10);
				flag2=false;
				break;

				
			}
		}
		if(global_flag==false && flag2) {
			node p = new node(key,location);
			graph2.addNode(p);
			graph2.connect(global_key, key, 10);
		}

		else if(flag2) {
			node p = new node(key,location);
			graph2.addNode(p);
			graph2.connect(key-1, key, 10);
		}

		repaint();
				
		} catch (Exception ex) {
			ex.printStackTrace();
		}
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

		if(str.equals("shortest path"))
		{
			shortestpath();
		}

		if(str.equals("shortest path dist"))
		{

			shortestpathdist();
		}


		if(str.equals("is Connected"))
		{

			isConnected();
		}
		if(str.equals("TSP"))
		{

			TSP();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph_Algo x= new Graph_Algo();
		graph xx= new DGraph();

		node_data a= new node(1,new Point3D(800,705,10),3);
		node_data b= new node(2,new Point3D(600,350,90),12);
		node_data c= new node(3,new Point3D(300,250,80),7);
		node_data d= new node(4,new Point3D(900,550,60),6);
		node_data e= new node(5,new Point3D(150,550,60),6);
		node_data aa= new node(6,new Point3D(190,705,10),3);
		node_data ba= new node(7,new Point3D(100,350,90),12);
		node_data ca= new node(8,new Point3D(97,123,80),7);
		node_data da= new node(9,new Point3D(300,550,60),6);
		node_data ea= new node(11,new Point3D(345,512,60),6);



		xx.addNode(a);
		xx.addNode(b);
		xx.addNode(c);
		xx.addNode(d);
		xx.addNode(e);
		xx.addNode(aa);
		xx.addNode(ba);
		xx.addNode(ca);
		xx.addNode(da);
		xx.addNode(ea);

		xx.connect(1, 2, 1);
		xx.connect(1, 3, 50);
		xx.connect(1, 4, 50);
		xx.connect(2, 3, 1);
		xx.connect(4, 3, 10);
		xx.connect(3, 4, 10);
		xx.connect(2, 1, 10);
		xx.connect(2, 4, 10);
		xx.connect(4, 1, 10);
		xx.connect(5, 2, 3);
		xx.connect(2, 5, 8);
		xx.connect(1, 5,9);

		xx.connect(9, 1, 10);
		xx.connect(4, 7, 10);
		xx.connect(8, 2, 10);
		xx.connect(2, 9, 10);
		xx.connect(4, 7, 10);
		xx.connect(7, 6, 10);
		xx.connect(5, 1, 10);
		xx.connect(2, 8, 2);
		xx.connect(8, 5, 2);
		xx.connect(2, 11, 8);
		xx.connect(11, 9, 1);
		xx.connect(6, 7, 1);
		xx.connect(7, 4, 1);

		x.init(xx);




		/*
		//System.out.println((int)Math.random()*900);
		node_data pp= new node(0,new Point3D(3,2,0+31),12);
		xx.addNode(pp);
		for(int i=1; i<1000000; i++) {
			node_data p= new node(i,new Point3D(i+0.123,i+0.002,i+0.003),12);
			xx.addNode(p);

			xx.connect(i,i,1);

		}

		x.init(xx);
		 */
		//System.out.println(x.shortestPathDist(1,5));

		//	List<node_data> q=x.shortestPath(1, 5);

		//	for(int i=0;i<q.size();i++){
		//System.out.print(q.get(i).getKey()+" ,");		} 
		//	System.out.println(x.isConnected());
		List <Integer> list= new LinkedList<Integer>();
		list.add(5);
		list.add(1);
		list.add(3);
		list.add(8);


		//		List<node_data> q=x.TSP(list);

		//		for(int i=0;i<q.size();i++){
		//			System.out.print(q.get(i).getKey()+","); }


		window GUI = new window(xx);
		GUI.setVisible(true);
	}





}
