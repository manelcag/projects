import java.io.*;
import java.util.*;

public class FSMDriver {
    public static void main(String[] args) throws IOException {
        FSM myFSM;
        
        myFSM = new FSM("simple");
        
        // test the string "aab" on that machine and report the results
        System.out.println(myFSM.test("aab"));
        if (myFSM.test("aab") == 0) {
            System.out.println("aab is a final state");
        } else {
            System.out.println("aab is not a final state");
        }
        System.out.println("");
        
        // test that string "aa" is not a final state
        System.out.println(myFSM.test("aa"));
        if (myFSM.test("aa") == 0) {
            System.out.println("aa is a final state");
        } else {
            System.out.println("aa is not a final state");     
        }
    }
}