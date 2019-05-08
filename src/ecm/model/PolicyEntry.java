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

    @Override
    public String toString()
    {
        return "PolicyEntry{" + "name=" + name + '}';
    }
}
