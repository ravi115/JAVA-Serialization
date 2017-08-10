# JAVA-Serialization
This repository will contain all the java serialization sample code with proper explanations. 
------------------------------------------------------------------------------------------------------------------------------------

Serialization in java is a mechanism of writing the state of an object into a byte stream.
  It is mainly used in Hibernate, RMI, JPA, EJB and JMS technologies.
  The reverse operation of serialization is called deserialization. - > it means getting the object state form byte 
  stream.
  
 why we use? - for security purpose over the network. 
 It is mainly used to travel object's state on the network (known as marshaling).
 @author ravir
 
 @Visit here : https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
 
 use Java.io.Serializable interface to perform serialization of any class.Typically the  serialization interface 
 has no methods or fields and serves only to identify the semantics of being serializable.

 Serializable is a marker interface (has no data member and method). 
 It is used to "mark" java classes so that objects of these classes may get certain capability. 
 The Cloneable and Remote are also marker interfaces.
 
