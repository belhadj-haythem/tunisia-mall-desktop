package tunisia.mall.interfaces;

import javax.ejb.Remote;

import tunisia.mall.persistance.Event;

@Remote
public interface EventServiceRemote {

	public boolean ajouter(Event v);
}
