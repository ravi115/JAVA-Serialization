package com.serialization.custom;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/*
 *in the serialization every data will get stored in file. but if we don't want to store any variable ORIGINAL DATA 
 *in serialization we use transient key word. so that variable in serialization gets initialized
 *with default value. but we want to retain the same original value. here we need to do some extra work.
 *
 * we will use two callback methods which will call by JVM automatically at the time of writing object and reading object.
 * 
 * 1. private void writeObject(ObjectOutputStream oos) throws Exception
 * 
 * 2. private void readObject(objectInputStream oin) throws Exception
 * 
 * these methods should be implemented in that class which object has to be serialized.
 * 
 */
class Student implements Serializable {
	
	int id;
	String name;
	transient int marks; //don't want to store in file
	transient String password; //password is sensitive data. so we don't want to store in file. so we ecrypt it and then store . 
	
	public Student(final int id, final String name, final int marks, final String password ) {
		this.id = id;
		this.name = name;
		this.marks  =marks;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + " : { id : " + id + " |  name : " + name + " | marks : " + marks + " | password : " + password + " }";
	}
	
	/*
	 * see the order of serialization of transient variable. 
	 * 1. password
	 * 2. marks  
	 * 
	 * so in the same order we have to de-serialize it otherwise we wont't get original data in respective variables. 
	 */
	private void writeObject(ObjectOutputStream os) throws Exception {
		os.defaultWriteObject();
		String pwd = "123"+password;
		os.writeObject(pwd);
		int mk = 444+marks;
		os.writeInt(mk);
	}
	
	private void readObject(ObjectInputStream in) throws Exception {
	
		in.defaultReadObject();
		String pwd = (String) in.readObject();
		password = pwd.substring(3);
		int var = in.readInt();
		marks = var - 444;
	}
	
}

public class CustomizedSerialization {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Student obj1 = new Student(1, "ravi", 80 , " google111");
		
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
		
		if(obj instanceof Student ) {
			Student st= (Student) obj;
			System.out.println("*-----after deserialization-----*");
			System.out.println(st);
		}
		
	}

}
