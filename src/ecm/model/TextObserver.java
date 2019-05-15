package ecm.model;

import java.util.Set;
/**
 *
 * @author beepbeep
 */
public interface TextObserver
{
    public void subscribe();
    public void dataUpdate(Set<String> data);
}
