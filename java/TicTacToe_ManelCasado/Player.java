/*******************************************
/** Author:  Manel Casado
/*  Course:  CSC 221, Fall 2023
/*  Lab:     Lab 3: Objects Lab, UTicTacToe
/*  Class:   Player
*******************************************/

public class Player {
    private String name;
    public char symbol;
    
    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }
    
    public String getName() {
        return name;
    }
    
    public char getSymbol() {
        return symbol;
    }
    
    public boolean equals(Player other) {
        if (name.equals(other.getName()) && symbol == other.getSymbol()){
            return true;
        }
        return false;
    }
    
    public String toString() {
        return "("+name+","+symbol+")";
    }
    
}