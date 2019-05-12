package ecm.model;

import java.util.*;

/*
 *Keywords
 */
public class Keywords implements TextData
{
    private Policy injectPolicy;
    private Set<String> allKeywords;
//    private Map<String, Set<String>> policyKeywords;
    
    public Keywords(Policy inject)
    {
        this.injectPolicy = inject;
//        this.policyKeywords = new HashMap<String, Set<String>>();
    }   

    /*
     *Add a keyword to map
     *IMPORT: related policy name and keyword
     */
    @Override
    public void addData(String polName, String data)
    {
        injectPolicy.addKeyword(polName, data);
//        Set<String> keywords = new HashSet<String>();
//        keywords.add(data);
//        policyKeywords.put(polName, keywords);
//        notifyObservers() TODO
    }

    @Override
    public Set<String> getPolicyData(String polName)
    {
//        return policyKeywords.get(polName);
        return null;
    }

    @Override
    public Set<String> getAllThisData()
    {
        allKeywords = injectPolicy.getKeywords();
        
        return allKeywords;
    }
}
