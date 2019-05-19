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
public interface KeywordObserver
{
    public void subscribe();
    public void unsubscribe();
    public void keywordSetUpdate(Set<String> data, String recentPolicy, String keyword);
    public void removeKeywordSetUpdate(Set<String> data, String policy, String keyword);
    public void keywordMapUpdate(Map<String, Set<String>> data);
    public void removeKeywordMapUpdate(Map<String, Set<String>> data);
}
