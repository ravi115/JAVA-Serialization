package com.serialization.inherit;

/**
 *  *** IS-A relationship means inheritance ***
 *  
 *   in the case of inheritance two scenarios come while serialization.
 *   1. when parent class implements serializable interface but child class doesn't implements.
 *   2. when child class implements serializable interface but parent class doesn't implement.
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/* below example illustrates for case -1.
 * 
 * 
 * when parent class implements serializable interface but child class doesn't implements.
 * in this case,  if parent class is implementing serializable interface that fine for child class also. no need of child 
 * class to implement serializable interface. since child can contain each and every functionality of parent class except
 * private functionality. internally child class is also implementing serializable interface.
 * 
 */
class Parent implements Serializable {

	protected int id;
	protected String name;

}

class Child extends Parent {

	int age;
	String password;

	public Child(int id, String name, int age, String password) {
		this.id = id;
		this.age = age;
		this.name = name;
		this.password = password;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " : { id : " + id + " |  name : " + name + " | age : " + age + " | password : " + password + " }";
	}

}

/**
 * Case -2 : when child implements serializable but parent doesn't.
 *  
 * in this case also serialization works fine but there is four points to be remmembred.
 * 1 . while serialization process JVM will check whether any parent is not implementing serializable interface.
 * 2. if it is so then JVM will ignore original value of parent's class variable and save default value.
 * 3. while de-serialization again JVM will check whether parent is implementing serializable interface or not. if so
 * 4. then JVM will construct an object by checking no-argument constructor (basically known as instance flow control) and
 * 		put original value back for parent's variable in that de-serialized object.  
 *
 */

class NewParent {
	
	int i = 10;
	public NewParent() {
		System.out.println("Parent constructor");
	}
	
}

class NewChild extends NewParent implements Serializable {
	
	int j = 20;
	
	public NewChild() {
		System.out.println("Child class constructor called");
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + " : { i : " + i + " |  j : " + j + " }";
	}
}
public class InheritaceSerialization {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		//Child obj1 = new Child(1, "ravi", 23, "google111");
		NewChild obj1 = new NewChild();
		obj1.i = 999;
		obj1.j = 888;
		
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
		//check if obj belongs to your desired class or not.
		if(obj instanceof NewChild ) {
			NewChild pm= (NewChild) obj;
			System.out.println("*-----after deserialization-----*");
			System.out.println(pm);
		}
	}

}
