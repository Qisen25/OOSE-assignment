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

    @Override
    public void addKeyword(String pName, String keyword) throws DuplicateException
    {
        boolean notExist;
        
        if(this.name.equals(pName))
        {
            notExist = keywords.add(keyword);
            
            if(!notExist)
                throw new DuplicateException("Keyword: " + keyword + " already exists in policy: " + pName);
        }
    }

    @Override
    public void addTalkingPoint(String pName, String talkPoint) throws DuplicateException
    {
        boolean notExist;
        
        if(this.name.equals(pName))
        {
            notExist = talkingPoints.add(talkPoint); 
            
            if(!notExist)
                throw new DuplicateException("Talking point: " + talkPoint + " already exists in policy: " + pName);
        }
    }
    
    @Override
    public void removeKeyword(String polName, String key) throws NoSuchElementException
    {
        if(!this.keywords.contains(key))
            throw new NoSuchElementException("Keyword " + key + " does not exist");
        
        this.keywords.remove(key);
    }

    @Override
    public void removeTalkingPoint(String polName, String talk)
    {
        if(!this.talkingPoints.contains(talk))
            throw new NoSuchElementException("Talking point " + talk + " does not exist");
        
        this.talkingPoints.remove(talk);
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
}
