package cs5700.hw2.gui.controllers;

import cs5700.hw2.application.observers.IAthleteObserver;

public interface IDisplayController {

    public IAthleteObserver getObserver();

    public void execute();
}
