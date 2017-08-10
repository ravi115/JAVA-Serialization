package com.serialize.org;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/*
 *in order to serialize the object of student class, we must have to implement Serializable interface. 
 */
class Student implements Serializable {

	private static final long serialVersionUID = 10245420L; /**/

	protected int age;
	protected String name;
	transient protected int salary;

	public Student(final int age, final String name, final int salary ) {
		this.age = age;
		this.name = name;
		this.salary = salary;
	}

	/*@Override
	public String toString() {
		return "Student{name : " + name + " age : " + age + " salary : " + salary +" }";
	}
*/
}

/*
 * in the inheritance if the parent class's object is getting serialized then same way at the same time the child
 * class object will also get serialized. otherwise  NotSerializableException is thrown at runtime.
 * 
 */
class User extends Student {

	private static final long serialVersionUID = 1L;
	
	private String male;
	transient int height;
	
	public User(int age, String name, int salary, final String male, final int height ) {
		super(age, name, salary);
		this.male = male;
		this.height = height;
	}
	
	@Override
	public String toString() {
		
		return "User: {" + " gender : " + male + " height : " + height + " Student{name : " + name + " age : " + age + " salary : " + salary +" }";
	}

	
	
}

class MySerialization {

	public static void serialize(Object obj, String fileName) throws IOException {

		if((null != fileName) && (fileName.length() > 0 ) ) {

			if(obj instanceof User) {

				FileOutputStream fOut = new FileOutputStream(fileName);
				ObjectOutputStream  out = new ObjectOutputStream(fOut);
				out.writeObject(obj);
				out.close();
			}
		}
	}
	public static Object deserialize(final String fileName ) throws IOException, ClassNotFoundException {

		User student = null;
		if((null != fileName ) && (fileName.length() > 0 ) ) {
			FileInputStream fIn = new FileInputStream(fileName);
			ObjectInputStream oIn = new ObjectInputStream(fIn);
			Object obj = oIn.readObject();
			if(obj instanceof User) {
				student = (User) obj;
			}
			oIn.close();
		}
		return student;
	}

}

/**
 * Serialization in java is a mechanism of writing the state of an object into a byte stream.
 * It is mainly used in Hibernate, RMI, JPA, EJB and JMS technologies.
 * The reverse operation of serialization is called deserialization. - > it means getting the object state form byte 
 * stream.
 * 
 * why we use? - for security purpose over the network. 
 * It is mainly used to travel object's state on the network (known as marshaling).
 * @author ravir
 *
 *@Visit here : https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
 *
 *use Java.io.Serializable interface to perform serialization of any class.Typically the  serialization interface 
 *has no methods or fields and serves only to identify the semantics of being serializable.
 *
 *
 *Serializable is a marker interface (has no data member and method). 
 *It is used to "mark" java classes so that objects of these classes may get certain capability. 
 *The Cloneable and Remote are also marker interfaces.
 */
public class JavaSerialization {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User stud = new User(23, "ravi", 250000, "male", 6);
		try {
			MySerialization.serialize(stud, "ByteData.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Student studNew= null;
		System.out.println(stud);
		try {
			studNew = (Student) MySerialization.deserialize("ByteData.txt");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(studNew);
		
		System.out.println(stud == studNew);
	}

}
