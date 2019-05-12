package ecm.model;

import java.util.*;

public class PolicyEntry implements Policy
{

    private String name;
    private Set<String> keywords;
    private Set<String> talkingPoints;

    public PolicyEntry(String name)
    {
        this.name = name;
        this.keywords = new HashSet<String>();
        this.talkingPoints = new HashSet<String>();
    }

    @Override
    public PolicyEntry find(String name)
    {
        PolicyEntry theEntry = null;

        if (this.name.equals(name))
        {
            theEntry = this;
        }

        return theEntry;
    }

    public String getName()
    {
        return name;
    }

    public void addKeyword(String pName, String keyword)
    {
        if(this.name.equals(pName))
        {
            keywords.add(keyword);
        }
    }

    public void addTalkingPoint(String pName, String talkPoint)
    {
        if(this.name.equals(pName))
        {
            talkingPoints.add(talkPoint); 
        }
    }
    
    public void setTalkingPoints(Set<String> points)
    {
        this.talkingPoints = points; 
    }
    
    public void setKeywords(Set<String> keywords)
    {
        this.keywords = keywords; 
    }
    
    @Override
    public Set<String> getKeywords()
    {
        return this.keywords;
    }

    @Override
    public Set<String> getTalkPoints()
    {
        return this.talkingPoints;
    }
    
    @Override
    public String toString()
    {
        return "PolicyEntry{" + "name=" + name + '}';
    }
    
    @Override
    public void printKey()
    {
        if(!this.keywords.isEmpty())
        {
            for(String key : keywords)
            {
                System.out.println(this.name + " keyword - " + key);
            }
        }
    }
    
    @Override
    public void printTalk()
    {
        if(!this.talkingPoints.isEmpty())
        {
            for(String talk : talkingPoints)
            {
                System.out.println(this.name + " talking point - " + talk);
            }
        }
    }
}
