import java.util.*;

/*******************************************
/** Author:  Manel Casado
/*  Course:  CSC 122, Fall 2022
/*  Lab:     Lab 7: Own Data Structure: Set
/*  Created: November 2022
/*  Class:   Person
*******************************************/

public class Person {
    public static String MALE = "Male";
    public static String FEMALE = "Female";
	
    private int id;
    private String name;
    private String lastName;
    private Date birthDay;
    private String gender;
    
    public Person(int id, String name, String lastName, Date birthday, String gender) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.birthDay = birthday;
        this.gender = gender;
    }
    
    
    // Next method is needed to compare objects with Set class
    public int hashCode() {
        return Objects.hash(id);
    }

    // Next method is needed to compare objects with Set class
    @Override
    public boolean equals(Object obj) {
        Person otherPerson;

        if (obj == null) {
            return false;
        }
        otherPerson = (Person) obj;
        if (id == otherPerson.id) {
            return true;
        }
	
        return false ;
    }
    
    public int compareTo(Person otherPerson) {
        return name.compareTo(otherPerson.name);
    }
    
    public boolean equals(Person otherPerson) {
        return false;
    }
    
    public String toString() {
        return "Person with id:= " + id 
                + ", name:" + name
                + ", lastName:"+ lastName
                + ", birthDay:"+ birthDay
                + ", gender:"+ gender
                + "\n";
    }
}
