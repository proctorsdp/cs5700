package cs5700.hw2;

public interface IAthleteObservable {

    public void notifyObservers();

    public void subscribeObserver(IAthleteObserverList observer);

    public void unsubscribeObserver(IAthleteObserverList observer);
}
