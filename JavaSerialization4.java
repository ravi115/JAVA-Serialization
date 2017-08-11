package com.serialize.org;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/*
 *it is always advisable to use  this [private static final long serialVersionUID = 1L;].
 *suppose if any changes happens in the class after getting serialized then while de-serialization using that version we can get the object from 
 *byte stream file. other wise it throws exception.
 * 
 *as we have seen in the below class, before serialization we have transient keyword after serializationis done we removed the transient keyword and
 *trying to deserialize it and that time only it thrown error. why we didn't have any serialVersionUID. because of that the mismatch occurs.
 *so after adding serialVersionUID it works fine.  
 * 
 */
class Episode implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String str;
	private int age;
	
	public Episode(String str, int age ) {
		this.age = age;
		this.str = str;
	}
	
	@Override
	public String toString() {
		return "Episode : { Name : " + str + " age : " + age + "}";
	}
}

class MySerialization4 {

	public static void serialize(Object obj, String fileName) throws IOException {

		if((null != fileName) && (fileName.length() > 0 ) ) {

			if(obj instanceof Episode) {

				FileOutputStream fOut = new FileOutputStream(fileName);
				/*
				 * ObjectOutputStream serialize the object into given file.
				 */
				ObjectOutputStream  out = new ObjectOutputStream(fOut);
				out.writeObject(obj);
				out.close();
			}
		}
	}
	public static Object deserialize(final String fileName ) throws IOException, ClassNotFoundException {

		Episode objEpisode = null;
		if((null != fileName ) && (fileName.length() > 0 ) ) {
			FileInputStream fIn = new FileInputStream(fileName);
			/*
			 *  ObjectInputStream deserialize the byte code from file to object.
			 */
			ObjectInputStream oIn = new ObjectInputStream(fIn);
			Object obj = oIn.readObject();
			if(obj instanceof Episode) {
				objEpisode = (Episode) obj;
			}
			oIn.close();
		}
		return objEpisode;
	}

}
public class JavaSerialization4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*Episode objEpisode = new Episode("ravi" , 23);
		try {
			MySerialization4.serialize(objEpisode, "ByteStream.ser");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		Episode newobjEpisode= null;
		//System.out.println(objEpisode);
		
		try {
			newobjEpisode = (Episode) MySerialization4.deserialize("ByteStream.ser");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(newobjEpisode);
		
	}

}
