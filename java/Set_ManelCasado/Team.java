import java.util.*;

/*******************************************
/** Author:  Manel Casado
/*  Course:  CSC 122, Fall 2022
/*  Lab:     Lab 7: Own Data Structure: Set
/*  Created: November 2022
/*  Class:   Team
*******************************************/

public class Team {
    private String code;
    private String name;
    private int score;
    private Set<Person> persons;
    
    public Team(String code, String name, int score) {
        this.code = code;
        this.name = name;
        this.score = score;
        
    }
    
    public void addPerson(Person aPerson) {
        if (persons == null) {
            persons = new HashSet<Person>();
        }
        persons.add(aPerson);
        System.out.println("Person added.");
        
    }
    
    public void removePerson(Person aPerson) {
        
    }

    public int compareTo(Team otherTeam) {
        return code.compareTo(otherTeam.code);
    }
    
    // Next method is needed to compare objects with Set class
    public int hashCode() {
        return Objects.hash(code);
    }

    // Next method is needed to compare objects with Set class
    @Override
    public boolean equals(Object obj) {
        Team otherTeam;

        if (obj == null) {
            return false;
        }
        otherTeam = (Team) obj;
        if (code == otherTeam.code) {
            return true;
        }
		
        return false ;
    }

    // TODO
    public String getMembersOrderedBy(String sortField) {
        return null;
    }

    // List team data in its members
    public String toString() {
        return "Code= " + code 
                + ", name:" + name
                + ", score:"+ score
                + ", swimmers: " + persons
                + "\n";
    }
}
