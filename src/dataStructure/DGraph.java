package dataStructure;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Iterator;
import java.util.Set;


public class DGraph implements graph,Serializable {
//Map<node_data, edge_data> hmap1 = new HashMap<node_data,edge_data>();
Map<Integer, node_data> hmap1 = new HashMap<Integer,node_data>();
Map<Integer, HashMap<Integer, edge_data>> hmap2 = new HashMap<Integer,HashMap<Integer, edge_data>>();


int MC=0;



public DGraph() 
{
	hmap1=new HashMap<Integer,node_data>();
	hmap2=new HashMap<Integer, HashMap<Integer,edge_data>>();
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
		if(hmap2.get(src).get(dest)==null)
			return null;
		else
			return hmap2.get(src).get(dest);
	}

	@Override
	public void addNode(node_data n) {
		// TODO Auto-generated method stub
		if((!hmap1.containsKey(n.getKey())))
			hmap1.put(n.getKey(), n);
		MC++;
		
	}

	@Override
	public void connect(int src, int dest, double w) {
		// TODO Auto-generated method stub
		if(src!=dest && w>0) {
			if(hmap1.containsKey(src)&&hmap1.containsKey(dest)) {
				
				edge_data tempdata=new edge(src,  dest,  w);//new edge	
				HashMap<Integer, edge_data> temp=new HashMap<Integer, edge_data>();
				temp.put(dest, tempdata);
				if(!this.hmap2.get(src).containsKey(dest)) {//if the edge is not exist
					hmap2.put(src, temp);
					MC++;
				}
			}
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
		if(hmap1.containsKey(node_id)) {
			return hmap2.get(node_id).values();	
		}
		return null;
	}

	@Override
	public node_data removeNode(int key) {
		// TODO Auto-generated method stub
	/*	
		if(hmap1.containsKey(key)) {
			node_data temp=hmap1.get(key);
			Iterator<Entry<Integer, HashMap<Integer, edge_data>>> it = hmap2.entrySet().iterator();
			// iterating every set of entry in the HashMap. 
			while (it.hasNext()) {
				Map.Entry<Integer, HashMap<Integer, edge_data>> set = it.next();
				int x=set.getKey();//x==source
				if(x==key) {
					hmap2.remove(key);
			}
				if(set.getValue().get(key)!=null) {
					it.remove();
				}
			}
				hmap2.remove(key);	
				hmap1.remove(key);
				MC++;
				hmap2.con)
			return temp;
			}
		
			return null;
		*/
		if(hmap1.containsKey(key)) {
			node_data temp=hmap1.get(key);
			Iterator<Entry<Integer, HashMap<Integer, edge_data>>> it = hmap1.entrySet().iterator();
			// iterating every set of entry in the HashMap. 
			while (it.hasNext()) {
				Map.Entry<Integer, HashMap<Integer, edge_data>> set = it.next();
				int x=set.getKey();//x==source
				if(x==key && hmap2.containsKey(x) ){
					hmap2.remove(key);
					MC =+ hmap2.get(key).size();
					
				}
				if (x!= key && hmap2.containsKey(x) && hmap2.get(x).containsKey(key)) {
					hmap2.get(x).remove(key);
					MC++;
				}
			}
			hmap1.remove(key);
			MC++;
		}
			return null;
			
		
		
	}
	
	
	@Override
	public edge_data removeEdge(int src, int dest) {
		// TODO Auto-generated method stub
	/*	
		if(hmap1.containsKey(src) && hmap1.containsKey(dest)) {
			if(hmap1.containsKey(src) == hmap1.containsKey(dest)) {
				removeNode(src);
			}
			else {
				for (Map.Entry<Integer, HashMap<Integer, edge_data>> check : hmap2.entrySet()) {
					if(check.getValue().get(dest)) 
							removeNode(src);
					if(check.getValue().get
						removeNode(dest);
				}	
				}	
		}
		return null;
		*/
		if(hmap2.containsKey(src) && hmap2.get(src).containsKey(dest)) 
			hmap2.get(src).remove();
			
		else 
			return null;	
	}

	@Override
	public int nodeSize() {
		// TODO Auto-generated method stub
		return hmap1.size();
	}

	@Override
	public int edgeSize() {
		// TODO Auto-generated method stub
		//????
		return this.hmap2.size();
		
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return MC;
	}

}
