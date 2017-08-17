package com.serialization.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*  ***final | static final | static final transient**
 *  
 * what if we use final keyword on any variable. see when we use final on variable they will get replaced by 
 * their actual value at compile time only. hence they are no longer variable. even if you use 
 * 1. final with static 
 * 2. final with transient
 * 3. final static transient
 * their all impact will be same. that variable get replaced with their original data at compile time only.
 * hence they no longer variable so the won't participate in serialization. 
 * 
 */
class Student implements Serializable {
	
	int id;
	final String name = "ravi";
	final static int age = 23;
	final static transient String password = "google111";
	
	public Student(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + " : { id : " + id + " |  name : " + name + " | age : " + age + " | password : " + password + " }";
	}
	
}


/*	***static|static Vs Transient***
 * 
 * use static variable in serialization. if we have any static variable then that variable will not participate 
 * in serialization. since they belong to class not instance. so what if you use transient static they also
 * don't have any effect on it. there value will be same after de-serialization.
 */
class User implements Serializable {

	int id;
	static String name;
	String password;
	transient static int age;
	
	public User(final int id, final String password) {
		this.id = id;
		this.password = password;
		name = "ravi";
		age = 23;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + " : { id : " + id + " |  name : " + name + " | age : " + age + " | password : " + password + " }";
	}
	
}

/* ***Transient***
 * if we don't want to serialize a variable then we have to declare that variable with transient keyword.
 * while serialization that transient variable will get default value. 
 * 
 */
class Person implements Serializable{
	
	int id;
	
	transient String name;
	
	transient int age;
	String password;
	
	public Person(final int id, final String name, final int age, final String passowrd ) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.password = passowrd;
	}
	

	@Override
	public String toString() {
		return getClass().getSimpleName() + " : { id : " + id + " |  name : " + name + " | age : " + age + " | password : " + password + " }";
	}
	
	
}


/*
 * this below Employee1 class is getting serialized. in order to do that the Employee1 class has to implement
 * Serializable interface. otherwise we will get run time exception NotSerializableError.  
 * 
 * 
 */
class Employee1 implements Serializable {
	
	int id;
	String name;
	int age;
	String password;
	
	public Employee1(final int id, final String name, final int age, final String passowrd ) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.password = passowrd;
	}
	

	@Override
	public String toString() {
		return getClass().getSimpleName() + " : { id : " + id + " |  name : " + name + " | age : " + age + " | password : " + password + " }";
	}
}






public class BasicsSerialization {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		//Employee1 obj1 = new Employee1(1,"ravi",23,"goole111");
		//Person obj1 = new Person(1,"ravi",23,"goole111");
		//User obj1 = new User(1, "google111");
		Student obj1 = new Student(1);
		
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
		if(obj instanceof Employee1 ) {
			Employee1 em = (Employee1) obj;
			System.out.println("*-----after deserialization-----*");
			System.out.println(em);
		}
		if(obj instanceof Person ) {
			Person pm= (Person) obj;
			System.out.println("*-----after deserialization-----*");
			System.out.println(pm);
		}
		if(obj instanceof User ) {
			User pm= (User) obj;
			System.out.println("*-----after deserialization-----*");
			System.out.println(pm);
		}
		if(obj instanceof Student ) {
			Student pm= (Student) obj;
			System.out.println("*-----after deserialization-----*");
			System.out.println(pm);
		}
		
	}

}
