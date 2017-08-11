package com.serialize.org;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;



/*
 * in case of Has-A relation. if a class has not implemented serializable interface then the class 
 * which having references to that class will not get serialized.
 * if you try so, you will get java.io.NotSerializableException. 
 */

/*
 *in order make below class work successfully, you need to declare like this :-
 *class Teacher implements Serializable  
 */
class Teacher { 
	
	String techerName;
	public Teacher(String name) {
		techerName = name;
	}
}

class University implements Serializable {
	
	private static final long serialVersionUID = 10245420L; /**/
	String uniName;
	Teacher tech;
	
	public University(Teacher tech, String name) {
		this.tech = tech;
		this.uniName = name;
	}
	@Override
	public String toString() {
		
		return "University: {" + " Name : " + uniName  + " teacher{name : " + tech.techerName + "}";
	}
}

class MySerialization2 {

	public static void serialize(Object obj, String fileName) throws IOException {

		if((null != fileName) && (fileName.length() > 0 ) ) {

			if(obj instanceof University) {

				FileOutputStream fOut = new FileOutputStream(fileName);
				ObjectOutputStream  out = new ObjectOutputStream(fOut);
				out.writeObject(obj);
				out.close();
			}
		}
	}
	public static Object deserialize(final String fileName ) throws IOException, ClassNotFoundException {

		University student = null;
		if((null != fileName ) && (fileName.length() > 0 ) ) {
			FileInputStream fIn = new FileInputStream(fileName);
			ObjectInputStream oIn = new ObjectInputStream(fIn);
			Object obj = oIn.readObject();
			if(obj instanceof University) {
				student = (University) obj;
			}
			oIn.close();
		}
		return student;
	}

}

public class JavaSerialization2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Teacher tech = new Teacher("ram");
		University uni = new University(tech, "vtu");

		try {
			MySerialization2.serialize(uni, "ByteData.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		University uniNew= null;
		System.out.println(uni);
		try {
			uniNew = (University) MySerialization2.deserialize("ByteData.txt");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(uniNew);
		
		System.out.println(uni == uniNew);
	}

}
