import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/*******************************************
/** Author:  Manel Casado
/*  Course:  CSC 122, Fall 2022
/*  Lab:     Lab 7: Own Data Structure: Set
/*  Created: November 2022
/*  Class:   SwimmingPlayer
*******************************************/

public class SwimingPlayer {
    private Set<Team> teams;
    
    // Constructor load test data, 
    // in future this load can be from a file
    public SwimingPlayer() {
        super();
        Team aTeam;
        
        aTeam = new Team("BU-IN", "Bethel University (IN)", 0);
        addTeam(aTeam);
        
        aTeam = new Team("ONU", "Olivet Nazarene University-IL", 0);
        addTeam(aTeam);

        aTeam = new Team("IN-WE", "Indiana Wesleyan University-IN", 0);
        addTeam(aTeam);
    }

    public static void main(String[] args) {
        SwimingPlayer swimingPlayer = new SwimingPlayer();
        Team aTeam;
        Person aSwimmer;
        Date birthday;
        
        System.out.println("Current teams are: ");
        swimingPlayer.showAllTeams(null);

        // Remove a team
        aTeam = new Team("BU-IN", "Bethel University (IN)", 0);
        System.out.println("Removing: " + aTeam);
        swimingPlayer.removeTeam(aTeam);
        System.out.println("Current teams are: ");
        swimingPlayer.showAllTeams(null);
        
        // Remove another team
        aTeam = new Team("ONU", "Olivet Nazarene University-IL", 0);
        swimingPlayer.removeTeam(aTeam);
        System.out.println("Current teams are: ");
        swimingPlayer.showAllTeams(null);
        
        // Create a new swimmer
        birthday = null;
        try {
            birthday =new SimpleDateFormat("MM/dd/yyyy").parse("05/12/2003");
        } catch (ParseException e) {
            System.out.println("Invalid date");
        }
        aSwimmer = new Person(1, "Manel", "Casado Garrigues", birthday, Person.MALE);
        
        aTeam = new Team("BU-IN", "Bethel University (IN)", 0);
        // Add to a team
        try {
            swimingPlayer.addMember(aTeam, aSwimmer);
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage() 
                    + " " + aTeam);
        }
        
        // Adding Bethel again to teams
        swimingPlayer.addTeam(aTeam);
        
        // Just check in screen!
        swimingPlayer.showAllTeams(null);
        swimingPlayer.addTeam(aTeam);
        swimingPlayer.showAllTeams(null);
        
        // Now add Manel again to Bethel
        aTeam.addPerson(aSwimmer);
        swimingPlayer.showAllTeams(null);
        
        // Add another swimmer to Bethel
        birthday = null;
        try {
            birthday =new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2001");
        } catch (ParseException e) {
            System.out.println("Invalid date");
        }
        aSwimmer = new Person(2, "Paula", "Ronda Bou", birthday, Person.FEMALE);
        aTeam.addPerson(aSwimmer);
        swimingPlayer.showAllTeams(null);

        
        // Purpose test showMembers method
        // TODO swimingPlayer.showMembers(aTeam);
    }
    
    // In future show the main menu with options
    // to add team, remove, ...
    private void showOptions() {
        
    }
    
    // List team members
    // Add a member to a team. If it already exists, error
    // Delete a member from a team. If it doesn't exist, error


    // List all teams
    private void showAllTeams(String sortField) {
        System.out.println(teams);
    }
    
    // Add a team,
    // TODO if exists throw error
    private void addTeam(Team team) {
        if (teams == null) {
            teams = new HashSet<Team>();
        }
        teams.add(team);
        System.out.println("Team added.");
    }
    
    // Remove a team
    private void removeTeam(Team team) {
        if (teams != null) {
            // Remove calls automatically equals method in class Team
            teams.remove(team);
            System.out.println("Team removed.");
        }
        
    }
    
    // Add a swimmer to the team
    // if team does not exists we throw an exception with error
    private void addMember(Team team, Person person) throws Exception {
        team = findTeam(team);
    	
        if (team == null) {
            throw new Exception("Team does not exists");
        } else {
            team.addPerson(person);
        }
    }
   
    // Remove a swimmer from a team
    // if team does not exists we throw an exception with error
    private void removeMember(Team team, Person person) {
        team = findTeam(team);
        
        if (team != null) {
            team.removePerson(person);
        }
    }
    
    // This method returns found team or null if not found
    private Team findTeam(Team team) {
        for (Team aTeam: this.teams) {
            if (aTeam.equals(team)) {
                return aTeam;
            }
        }
    	
        return null;
    }

    // Show team members
    private void showMembers(Team team) {
        System.out.println(team.getMembersOrderedBy(null));
    }
    
}
