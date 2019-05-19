/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecm.model;

import java.util.Map;
import java.util.Set;

/**
 *
 * @author beepbeep
 */
public interface TalkingPointObserver
{
    public void subscribe();
    public void unsubscribe();
    public void talkingPointSetUpdate(Set<String> data, String policyName, String recentTalkPoint);  
    public void removeTalkingPointSetUpdate(Set<String> data, String policyName, String recentTalkPoint); 
    public void talkingPointMapUpdate(Map<String, Set<String>> data);
    public void removeTalkingPointMapUpdate(Map<String, Set<String>> data); 
}
