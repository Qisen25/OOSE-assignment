/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecm.model;

import java.util.List;

/**
 *
 * @author beepbeep
 */
public interface PolicyObserver
{
    public void subscribe();
    public void dataUpdate(List<Policy> data);   
}
