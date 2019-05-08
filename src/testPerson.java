/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        
        p.addPolicy("Poo dog god");
        
        System.out.println(parry.toString() + " his mobile " + parry.getMobileNum());
        System.out.println(p.find("Poo dog god").toString());
        
        if(parry.getFacebookID() == null)
        {
            System.out.println("Parry doesn't fucking have facebook!");
        }
        
        p.removePolicy("Poo dog god");
        
        if(p.find("Poo dog god") == null)
        {
            System.out.println("Nothing found");
        }
    }
    
    
}
