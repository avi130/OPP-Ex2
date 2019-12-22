package dataStructure;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map;

import utils.Point3D;

public class node implements node_data {
	int key;
	Point3D location;
	int value;
	double weight;
	String info;
	int tag;
	
	public node(int key) {
		this.key=key;
		this.location=null;
		this.value=Integer.MAX_VALUE;
		this.weight=0;
		this.info="";
		this.tag=0;
	}
	
	
	public node(int key, Point3D location ,int value,double weight, String info,int tag) {
		this.key=key;
		this.location=location;
		this.value=value;
		this.weight=weight;
		this.info=info;
		this.tag=tag;
	}
	
	public node(int key,int value,double weight, String info) {
		this.key=key;
		this.location=null;
		this.value=value;
		this.weight=weight;
		this.info=info;
		this.tag=0;
	}
	
	public node(int key, Point3D location ,int value ) {
		this.key=key;
		this.location=location;
		this.value=value;
		this.weight=0;
		this.info="";
		this.tag=0;
	}
	
	public node(int key, Point3D location ,double weight ) {
		this.key=key;
		this.location=location;
		this.value=0;
		this.weight=weight;
		this.info="";
		this.tag=0;
	}

	
	public boolean contains(int key) {
		// TODO Auto-generated method stub
		if(this.key==key)
			return true ;
		return false;
	}
	
	@Override
	public int getKey() {
		// TODO Auto-generated method stub
		return this.key ;
	}

	@Override
	public Point3D getLocation() {
		// TODO Auto-generated method stub
		return this.location;
	}

	@Override
	public void setLocation(Point3D p) {
		// TODO Auto-generated method stub
		this.location=p;
	}

	@Override
	public double getWeight() {
		// TODO Auto-generated method stub
		return this.weight;
	}

	@Override
	public void setWeight(double w) {
		// TODO Auto-generated method stub
		this.weight=w;
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