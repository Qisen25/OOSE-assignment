/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ecm.view.PolicyViewer;
import java.util.*;
import ecm.model.*;
import ecm.view.*;
import ecm.controller.*;
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
        Scanner sc = new Scanner(System.in);
        
        Candidate parry = new Candidate(10, "Parry", 000L, null, null);
        PolicyAreas p = new PolicyAreas();
        Group grp = new Group();
        SMS sms = new SMS();
        
        PolicyFactory pMaker = new PolicyFactory();
        MemberFactory maker = new MemberFactory();
        PolicyAreaController pACtrl = new PolicyAreaController(pMaker, p);
        GroupController grpCtrl = new GroupController(maker, grp);
        
        PolicyViewer polObs = new PolicyViewer(p);
        MemberViewer memObs = new MemberViewer(grp);
        KeywordViewer keyObs = new KeywordViewer(p);
        TalkingPointViewer talkObs = new TalkingPointViewer(p);
        NotificationHandler notifHand = new NotificationHandler(p, grp, sms);
        
        notifHand.subscribe();
        
        keyObs.subscribe();
        
        talkObs.subscribe();
        
//        p.addPolicy(pMaker.makePolicy("Poo dog god"));
//        
//        System.out.println(parry.toString());
//        System.out.println(p.find("Poo dog god").toString());
//        
//        if(parry.getFacebookID() == null)
//        {
//            System.out.println("Parry doesn't fucking have facebook!");
//        }
//        
//        System.out.println("**adding member**");
//        String adMoe;
//        do
//        {
//            int id;
//            long phone;
//            String namez;
//            String twit;
//            String fb;
//            
//            System.out.print("Enter a id:>");
//            id = sc.nextInt();
//            System.out.print("Enter name:>");
//            namez = sc.next();
//            System.out.print("Enter phone #:>");
//            phone = sc.nextLong();
//            System.out.print("Enter twitter:>");
//            twit = sc.next();
//            System.out.print("Enter facebook:>");
//            fb = sc.next();
//            
//            Member poop = new Volunteer(id, namez, phone, twit, fb);
//            grp.addMember(poop);
//            
//            System.out.print("Do you wish to add more y/n:>");
//            adMoe = sc.next();
//            sc.nextLine();
//            
//        }while(adMoe.equalsIgnoreCase("y"));
//        
//        grp.printDetails();
//        
//
//        
//        p.removePolicy("Poo dog god");
//        
//        if(p.find("Poo dog god") == null)
//        {
//            System.out.println("Nothing found");
//        }
//        
//        p.addPolicy(pMaker.makePolicy("Crazy"));
//        p.addPolicy(pMaker.makePolicy("Gucci"));
//        p.addPolicy(pMaker.makePolicy("Zhan"));

        
        Menu m = new Menu(grpCtrl, pACtrl, notifHand);
        //MenuController menuz = new MenuController(m);
        
        m.displayMenu(polObs, memObs, keyObs, talkObs);
    }
    
    
}
