package algorithms;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import dataStructure.DGraph;
import dataStructure.graph;
import dataStructure.node_data;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms, Serializable{

	public graph mygraph;
	
	
	@Override
	public void init(graph g) {
		// TODO Auto-generated method stub
		mygraph=g;
	}

	@Override
	public void init(String file_name) {
		// TODO Auto-generated method stub
		try {
			FileInputStream fi = new FileInputStream(new File(file_name));
			ObjectInputStream oi = new ObjectInputStream(fi);

			graph pr1= (graph)oi.readObject();
			
			System.out.println(pr1.toString());

			oi.close();
			fi.close();	
			
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found");
		}
		catch (IOException e)
		{
			System.out.println("Error initializing stream");
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	
	@Override
	public void save(String file_name) {
		// TODO Auto-generated method stub
		try {
			FileOutputStream f = new FileOutputStream(new File(file_name));
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			o.writeObject(this.mygraph);
			o.close();
			f.close();			
			
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found");
		}
		catch (IOException e)
		{
			System.out.println("Error initializing stream");
		}

	}
	

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	@Override
	public graph copy() {//made with Serializable copy(same as save\read from file)
		// TODO Auto-generated method stub
		graph x= new DGraph();
		Object obj = new DGraph();
	        try {
	            // Write the object out to a byte array
	            ByteArrayOutputStream myarray = new ByteArrayOutputStream();
	            ObjectOutputStream write = new ObjectOutputStream(myarray);
	            write.writeObject(this.mygraph);
	            write.flush();
	            write.close();

	            // Make an input stream from the byte array and read
	            // a copy of the object back in.
	            ObjectInputStream insert = new ObjectInputStream( new ByteArrayInputStream(myarray.toByteArray()));
	            obj = insert.readObject();
	        }
	        catch(IOException e) {
	            e.printStackTrace();
	        }
	        catch(ClassNotFoundException cnfe) {
	            cnfe.printStackTrace();
	        }
	        x=(graph)obj;
	        return x;
	    }
		
}
