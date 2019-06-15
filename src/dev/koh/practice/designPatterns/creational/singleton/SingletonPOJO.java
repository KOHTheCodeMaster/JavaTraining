package dev.koh.practice.designPatterns.creational.singleton;

import dev.koh.libs.utils.MyTimer;

class SingletonPOJO {

    private static SingletonPOJO instance;

    private SingletonPOJO() {
        System.out.println("Instantiating SingletonPOJO");
    }

    static SingletonPOJO getInstance() {

        if (instance == null) {
            synchronized (SingletonPOJO.class) {
                if (instance == null) {
                    instance = new SingletonPOJO();
                }
            }
        }

        return SingletonPOJO.instance;

    }

    void printTimeTaken() {
        MyTimer myTimer = new MyTimer();
        myTimer.startTimer();
        myTimer.stopTimer(true);
    }

}

/*
 *  Time Stamp : 15th June 2K19, 05:31 AM..!!
 *
 *  Singleton Pattern..!!
 *
 *  Problem :
 *      To restrict the class to be instantiated only once &
 *      not allowing more than one instance of that class to be existing at the same time
 *
 *  Solution :
 *      Singleton Pattern uses private Constructor to prohibit its instantiation outside of that class
 *      It has a static instance of the class itself along with public method named getInstance for instantiation
 *      if the instance is not null, it'll instantiate it only once for the first time
 *      otherwise return that same static instance without instantiating it all over again
 *      This way, it ensure there exists only 1 Single Instance of the class!
 *
 *  <| ================================================================ |>
 *
 *  Code Developed By,
 *  ~K.O.H..!! ^__^
 */