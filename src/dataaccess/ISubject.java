package dataaccess;

import view.IObserver;

public interface ISubject {
	boolean register(IObserver obs);

	boolean unRegister(IObserver obs);

	void notifyObservers();

}
