package dataStructure;

public class edge implements edge_data {

	int src;
	int dest;
	double weight;
	int tag;
	String info;
	
	 public edge(int src, int dest) {
		 this.src=src;
		 this.dest=dest;
		 this.weight=0;
		 this.tag=0;
		 this.info="";
		
	}
	 public edge(int src, int dest,double weight,String info, int tag) {
		 this.src=src;
		 this.dest=dest;
		 this.weight=weight;
		 this.tag=tag;
		 this.info=info;
		
	}
	 
	 public edge(int src, int dest,double weight) {
		 this.src=src;
		 this.dest=dest;
		 this.weight=weight;
		 this.tag=0;
		 this.info="";
		
	}
	 public edge(int src, int dest,double weight,String info) {
		 this.src=src;
		 this.dest=dest;
		 this.weight=weight;
		 this.tag=0;
		 this.info=info;
		
	}

	
	@Override
	public int getSrc() {
		// TODO Auto-generated method stub
		return this.src;
	}

	@Override
	public int getDest() {
		// TODO Auto-generated method stub
		return this.dest;
	}

	@Override
	public double getWeight() {
		// TODO Auto-generated method stub
		return this.weight;
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return this.info;
	}

	@Override
	public void setInfo(String s) {
		// TODO Auto-generated method stub
		this.info=s;
	}

	@Override
	public int getTag() {
		// TODO Auto-generated method stub
		return this.tag;
	}

	@Override
	public void setTag(int t) {
		// TODO Auto-generated method stub
		this.tag=t;
	}

}
