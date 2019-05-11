/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
import ecm.model.*;
/**
 *
 * @author beepbeep
 */
public class testPerson
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Candidate parry = new Candidate(1, "Parry", "00011111", null, null);
        PolicyAreas p = new PolicyAreas();
        Keywords key = new Keywords();
        TalkingPoints tp = new TalkingPoints();
        
        p.addPolicy("Poo dog god");
        
        System.out.println(parry.toString() + " his mobile " + parry.getMobileNum());
        System.out.println(p.find("Poo dog god").toString());
        
        if(parry.getFacebookID() == null)
        {
            System.out.println("Parry doesn't fucking have facebook!");
        }
        
        p.addPolicyTalkPoint("Get out", "Poo dog god");
        p.addPolicyKeyword("LOL", "Poo dog god");
        
        p.printKey();
        p.printTalk();
        
        p.removePolicy("Poo dog god");
        
        if(p.find("Poo dog god") == null)
        {
            System.out.println("Nothing found");
        }
        
        p.addPolicy("Crazy bets dream");
        p.addPolicy("Gucci I'm rich");
        p.addPolicy("Zhan musi ha deng");
        
        boolean tryAgain = false;
        String pName;
        Scanner sc = new Scanner(System.in);
        do
        {
            
            System.out.print("Enter a policy name: ");  
            pName = sc.nextLine(); 
            
            try
            {
                if(p.find(pName) != null && !pName.equalsIgnoreCase("exit"))
                {
                    System.out.println("Policy " + pName + " found!" );
                    System.out.print("Enter a Keyword: ");  
                    String kw = sc.nextLine(); 
                    key.addData(pName, kw);
                    System.out.print("Enter a talking pts: ");  
                    String tpts = sc.nextLine(); 
                    tp.addData(pName, tpts);
                    p.setPolicyKeywords(key);
                    p.setPolicyTalkPoints(tp);
                    tryAgain = false;
                }
                else if(!pName.equalsIgnoreCase("exit"))
                {
                    throw new IllegalArgumentException("Policy not existing");                   
                }
            }
            catch(IllegalArgumentException e)
            {
                System.out.println(e.getMessage() + " try again");
                tryAgain = true;
            }
            
        }while(!pName.equalsIgnoreCase("exit") || tryAgain);

        p.printKey();
        p.printTalk();
        
        Set<String> keys = key.getAllThisData();
        Set<String> points = tp.getAllThisData();
        
        System.out.println("print all keywords");
        for(String keyz : keys)
        {
            System.out.println(keyz);
        }
        
        System.out.println("print all talkpoints");
        for(String ptz : points)
        {
            System.out.println(ptz);
        }
        
    }
    
    
}
