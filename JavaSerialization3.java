package com.serialize.org;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 *	as we know static variable belongs to class only, doesn't belong to object. 
 *	and serializable can be done on object only. not on the class. 
 *	in serialization only member variable variable will get serialized not the class variable. 
 */

class Example implements Serializable {
	
	/**
	 * static variable won't be serialized.
	 */
	private static final long serialVersionUID = 1L;
	
	int age;
	static String name = "raj"; //belongs to class, not belong to object. //this variable will not get serialized.
	
	public Example(int age, String name ) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		
		return "Example: {" + " Name : " + name  + " age : " + age + "}";
	}
	
}

class MySerialization3 {

	public static void serialize(Object obj, String fileName) throws IOException {

		if((null != fileName) && (fileName.length() > 0 ) ) {

			if(obj instanceof Example) {

				FileOutputStream fOut = new FileOutputStream(fileName);
				ObjectOutputStream  out = new ObjectOutputStream(fOut);
				out.writeObject(obj);
				out.close();
			}
		}
	}
	public static Object deserialize(final String fileName ) throws IOException, ClassNotFoundException {

		Example student = null;
		if((null != fileName ) && (fileName.length() > 0 ) ) {
			FileInputStream fIn = new FileInputStream(fileName);
			ObjectInputStream oIn = new ObjectInputStream(fIn);
			Object obj = oIn.readObject();
			if(obj instanceof Example) {
				student = (Example) obj;
			}
			oIn.close();
		}
		return student;
	}

}

public class JavaSerialization3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Example exp = new Example(1,"raj");

		try {
			MySerialization3.serialize(exp, "ByteData.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Example uniNew= null;
		System.out.println(exp);
		try {
			uniNew = (Example) MySerialization3.deserialize("ByteData.txt");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(uniNew);
		
		System.out.println(exp == uniNew);
	}

}
