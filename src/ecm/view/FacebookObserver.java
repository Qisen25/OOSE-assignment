package ecm.view;

import java.util.Map;
import java.util.Set;

/**
 *
 * @author beepbeep
 */
public interface FacebookObserver
{
    public void subscribe();
    public void unsubscribe();
    public void trendUpdate(Map<String, Integer> data);
}
