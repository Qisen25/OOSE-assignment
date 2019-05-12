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
        Candidate parry = new Candidate(1, "Parry", 000L, null, null);
        PolicyAreas p = new PolicyAreas();
        TextData keys = new Keywords(p);
        TextData talks = new TalkingPoints(p);
        
        p.addPolicy("Poo dog god");
        
        System.out.println(parry.toString() + " his mobile " + parry.getMobileNum());
        System.out.println(p.find("Poo dog god").toString());
        
        if(parry.getFacebookID() == null)
        {
            System.out.println("Parry doesn't fucking have facebook!");
        }
        
        keys.addData("Poo dog god", "Get out");
        talks.addData("Poo dog god","LOL");
        
        p.printKey();
        p.printTalk();
        
        p.removePolicy("Poo dog god");
        
        if(p.find("Poo dog god") == null)
        {
            System.out.println("Nothing found");
        }
        
        p.addPolicy("Crazy");
        p.addPolicy("Gucci");
        p.addPolicy("Zhan");
        
        boolean tryAgain = false;
        String pName;
        Scanner sc = new Scanner(System.in);
        do
        {
            
            System.out.print("Enter a policy name: ");  
            pName = sc.nextLine(); 
            
            try
            {
                tryAgain = false;
                if(p.find(pName) != null && !pName.equalsIgnoreCase("exit"))
                {
                    System.out.println("Policy " + pName + " found!" );
                    System.out.print("Enter a Keyword: ");  
                    String kw = sc.nextLine(); 
                    keys.addData(pName, kw);
                    System.out.print("Enter a talking pts: ");  
                    String tpts = sc.nextLine(); 
                    talks.addData(pName, tpts);                 
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
        
        System.out.println("Enter a policy to remove");
        pName = sc.nextLine();
        

        if(!p.getPolicyKeywords(pName).isEmpty())
        {
            System.out.println(pName + " has related keywords");
            for(String str : p.getPolicyKeywords(pName))
            {
                System.out.println(str);
            }
            System.out.println("do you really want remove" + pName);
            String op = sc.nextLine();
            if(op.equals("yes"))
            {
                p.removePolicy(pName);
            }
            else
            {
                System.out.println(pName + " not removed");
            }
        }
        
        p.printKey();
        p.printTalk();
        
        Set<String> k = keys.getAllThisData();
        Set<String> t = talks.getAllThisData();
        
        
        System.out.println("Printing keywords from all policy areas");
        for(String str : k)
        {
            System.out.println(str);
        }
      
        System.out.println("Printing talking points from all policy areas");
        for(String str : t)
        {
            System.out.println(str);
        }
       
    }
    
    
}
