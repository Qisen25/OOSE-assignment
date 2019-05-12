package ecm.model;

import java.util.*;

/*
 *Keywords
 */
public class Keywords implements TextData
{
    private Map<String, Set<String>> policyKeywords;
    
    public Keywords()
    {
        this.policyKeywords = new HashMap<String, Set<String>>();
    }   

    /*
     *Add a keyword to map
     *IMPORT: related policy name and keyword
     */
    @Override
    public void addData(String polName, String data)
    {
        Set<String> keywords = new HashSet<String>();
        keywords.add(data);
        policyKeywords.put(polName, keywords);
    }

    @Override
    public Set<String> getPolicyData(String polName)
    {
        return policyKeywords.get(polName);
    }

    @Override
    public Set<String> getAllThisData()
    {
        Set<String> mergeSet = new HashSet<String>();
        
        for(Set<String> wordSet : policyKeywords.values())
        {
            mergeSet.addAll(wordSet);
        }
        
        return mergeSet;
    }
}
