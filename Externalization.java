package com.externalization;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/*   ----*** Externalization***---------
 * 
 * when we don't to save all object's property then we should go for externalization. when a class implements externalizable
 * interface then compulsory two method has to be overridden and those method will be called by JVM automatically at the
 * time of write object and reading object respectively.
 * 
 *   1. public void writeExternal(ObjetOutputStream oos ) throws exception
 *   	--> inside this method we can serialize the specified object's properties.
 *   2. public void readExternal(ObjectInputStream in) throws Exception
 *      --> inside this method we can de-serialize that specified properties.
 *      
 *      we much have explicitly public no-argument constructor. otherwise we will get invalidCastClass exception. 
 * 
 */
class Example implements Externalizable {
	
	int i = 10; //saving to file
	int j = 20;
	String name = "ravi"; //saving to file
	
	 public Example() {
		
	}
	 
	 public Example(int i, int j, String name) {
			this.i = i;
			this.j = j;
			this.name = name;
		}
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeObject(name);
		out.writeInt(i);
		
	}
	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		
		name = (String) in.readObject();
		i = in.readInt();
		
	}
	
}

public class Externalization {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Example obj1 = new Example(25,25,"kumar");
		
		System.out.println("Serialization started--->");
		FileOutputStream fout = new FileOutputStream("abc.ser");
		ObjectOutputStream oos  = new ObjectOutputStream(fout);
		oos.writeObject(obj1);
		oos.close();
		System.out.println("i ---> " + obj1.i + " j -----> " + obj1.j + " name ----> " + obj1.name);
		
		System.out.println("De-Serialization started--->");
		FileInputStream fin = new FileInputStream("abc.ser");
		ObjectInputStream in = new ObjectInputStream(fin);
		Example exm = (Example) in.readObject();
		in.close();
		System.out.println("i ---> " + exm.i + " j -----> " + exm.j + " name ----> " + exm.name);

	}

}
