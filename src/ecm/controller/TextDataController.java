package ecm.controller;

import ecm.model.PolicyNotFoundException;
import ecm.model.TextData;
//import ecm.model.Keywords;
/**
 *
 * @author beepbeep
 */
public class TextDataController
{
    private TextData keywords;
    private TextData talkingPoints;

    public TextDataController(TextData keywords, TextData talkingPoints)
    {
        this.keywords = keywords;
        this.talkingPoints = talkingPoints;
    }
    
    public void addKeyword(String policyName, String key) throws PolicyNotFoundException
    {
        keywords.addData(policyName, key);
    }
    
    public void addTalkingPoint(String policyName, String key) throws PolicyNotFoundException
    {
        talkingPoints.addData(policyName, key);
    }
    
    public void removeKeyword(String policyName, String key)
    {
        keywords.removeData(key);
    }
    
    public void removeTalkingPoint(String policyName, String key)
    {
        talkingPoints.removeData(key);
    }
}
