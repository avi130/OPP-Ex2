package dataStructure;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Iterator;
import java.util.Set;


public class DGraph implements graph,Serializable {

	Map<Integer, node_data> hmap1 = new HashMap<Integer,node_data>();
	Map<Integer, HashMap<Integer, edge_data>> hmap2 = new HashMap<Integer,HashMap<Integer, edge_data>>();


	int MC=0;
	int edgesize=0;


	public DGraph() 
	{
		hmap1=new HashMap<Integer,node_data>();
		hmap2=new HashMap<Integer, HashMap<Integer,edge_data>>();
	}


	public DGraph(Collection<node_data> nodes, Collection<edge_data> edges) {

		for(node_data n : nodes ) {
			hmap1.put(n.getKey(),n);
		}
		for ( edge_data e :edges) {
			connect(e.getSrc(), e.getDest(), e.getWeight());
		}
	}


	@Override
	public node_data getNode(int key) {
		// TODO Auto-generated method stub	
		if(hmap1.containsKey(key))
			return hmap1.get(key);
		else
			return null;
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		// TODO Auto-generated method stub
		if( src == dest)
			return null;
		if((hmap1.get(src)==null) || (hmap1.get(dest)==null))
			return null;
		//node_data key=hmap1.get(src);
		if(hmap2.get(hmap1.get(src).getKey()).containsKey(dest)) 
			return hmap2.get(src).get(dest);
		
		return null;
		

	}


	public void addNode(node_data n) {
		// TODO Auto-generated method stub
		if((!hmap1.containsKey(n.getKey()))) {
			hmap1.put(n.getKey(), n);
			MC++;
		}




	}

	@Override
	public void connect(int src, int dest, double w) {
		// TODO Auto-generated method stub
		try {
		if(src!=dest && w>=0) {

			if(hmap1.containsKey(src)&&hmap1.containsKey(dest)) {
				if(hmap2.containsKey(src)==true ) {
					if(hmap2.get(src).containsKey(dest)==true) {
						if(hmap2.get(src).get(dest).getWeight()!=w) {
							edge_data edge=new edge(src,dest,w);
							hmap2.get(src).put(dest,edge);
							MC++;
						}
					}
				}
				if(hmap2.containsKey(src)==false) //check if there is a hashmap for key src
				{
					HashMap<Integer, edge_data> edgesVer=new HashMap<Integer,edge_data> ();
					hmap2.put(src, edgesVer);

				}
				if(hmap2.get(src).containsKey(dest)==false)//check if the edge is already exist
				{
					edge_data edge=new edge(src,dest,w);
					hmap2.get(src).put(dest,edge);
					edgesize++;
					MC++;


				}

			}
		}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}






	@Override
	public Collection<node_data> getV() {
		// TODO Auto-generated method stub	
		return hmap1.values();
	}


	@Override
	public Collection<edge_data> getE(int node_id) {
		// TODO Auto-generated method stub
		if(hmap1.containsKey(node_id)&& hmap2.containsKey(node_id)) {
			return hmap2.get(node_id).values();	
		}
		return null;
	}

	@Override
	public node_data removeNode(int key) {
		// TODO Auto-generated method stub
		try {
			if(hmap1.containsKey(key)) {
				node_data temp=hmap1.get(key);
				for(Entry<Integer, node_data> entry : hmap1.entrySet()) {
					int currentKey=entry.getKey();
					if(currentKey==key && hmap2.containsKey(key)) {
						int a=hmap2.get(key).size();
						hmap2.remove(key);
						//hmap1.remove(key);
						edgesize=edgesize-a;
						break;

					}
					if(currentKey!=key && hmap2.containsKey(currentKey)) {

						if(hmap2.get(currentKey).containsKey(key)) {
							int a=hmap2.get(currentKey).size();
							hmap2.get(currentKey).remove(key);
							edgesize=edgesize-a;
							break;
						}

					}
				}
				hmap1.remove(key);
				MC++;
				return temp;	
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;



	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		// TODO Auto-generated method stub

		if(this.hmap1.containsKey(src)&&this.hmap1.containsKey(dest))
		{
			MC++;
			if(this.hmap2.get(src).containsKey(dest))
			{
				int a=this.hmap2.get(src).size();
				edgesize=edgesize-a;
				return this.hmap2.get(src).remove(dest);
				//	}

			}
			else 
				throw new RuntimeException ("this edge is not exist");
		}
		else
			throw new RuntimeException ("one of the nodes not exist");
	}

	@Override
	public int nodeSize() {
		// TODO Auto-generated method stub
		return hmap1.size();
	}

	@Override
	public int edgeSize() {
		// TODO Auto-generated method stub

		return edgesize;

	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return MC;
	}

}
