package com.serialization.graph;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 *   ***HAS - A RelationShip ****
 *   in Has-A relationship all the classes must implement serialzable interface other wise we will get run time exception saying
 *   NotSerialzationError.
 */

class University implements Serializable {

	private String clgName;
	private String address;
	
	public String getClgName() {
		return clgName;
	}
	public void setClgName(String clgName) {
		this.clgName = clgName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}


}

/* object graph in serialization is nothing but how a class contains reference of other class in serialization.
 * in serialization every class object will get serialize. 
 * 
 */
class College implements Serializable{

	String name;
	transient int id;
	//this is university class object
	University uni;

	public College(final String name, final int id, final University uni) {
		this.name = name;
		this.id = id;
		this.uni = uni;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " : { id : " + id + " |  name : " + name + " | University Name : " + uni.getClgName() + " | address : " + uni.getAddress() + " }";

	}
}


public class ObjectGraphSerialization {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		University uni = new University();
		uni.setClgName("VTU");
		uni.setAddress("Bangalore");	

		College obj1 = new College("RNSIT" , 1 ,uni);

		//serialization takes place hare
		FileOutputStream fOs = new FileOutputStream("abc.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fOs);
		oos.writeObject(obj1);
		oos.close();
		System.out.println("*----after serialization----*");
		System.out.println(obj1);

		//de-serialization takes place here

		FileInputStream fin = new FileInputStream("abc.ser");
		ObjectInputStream oin = new ObjectInputStream(fin);
		//read object in Object class if you don't know the de-serialized object of class.
		Object obj = oin.readObject();
		oin.close();

		if(obj instanceof College) {
			College col = (College) obj;
			System.out.println("*-----after deserialization-----*");
			System.out.println(col);
		}

	}

}
