package dev.koh.libs.utils;

enum TimeUnit {

    NANO_SECOND("ns", 1),
    MICRO_SECOND("us", NANO_SECOND.getTimeInMilliSeconds() * 1000),
    MILLI_SECOND("ms", MICRO_SECOND.getTimeInMilliSeconds() * 1000),
    SECOND("s", MILLI_SECOND.getTimeInMilliSeconds() * 1000),
    MINUTE("min", 60 * SECOND.timeInMilliSeconds),
    HOUR("hr", 60 * MINUTE.timeInMilliSeconds),
    DAY("day", 24 * HOUR.timeInMilliSeconds),
    WEEK("week", 7 * DAY.timeInMilliSeconds),
    MONTH("month", 30 * DAY.timeInMilliSeconds),
    YEAR("year", 12 * MONTH.timeInMilliSeconds),
    DECADE("decade", 10 * YEAR.timeInMilliSeconds);

    private final String unit;
    private final double timeInMilliSeconds;

    TimeUnit(String unit, double timeInMilliSeconds) {
        this.unit = unit;
        this.timeInMilliSeconds = timeInMilliSeconds;
    }

    public String getUnit() {
        return unit;
    }

    public double getTimeInMilliSeconds() {
        return timeInMilliSeconds;
    }

    @Override
    public String toString() {
        return getUnit();
    }
}

public class MyTimer {
    private double currentTime;
    private double totalTimeTaken;
    private double pauseTime;
    private double totalPauseTime;
    private TimeUnit timeUnit;

    public void startTimer() {
        currentTime = System.nanoTime();
    }

    public void stopTimer() {

        double endTime = System.nanoTime();
        totalTimeTaken = endTime - currentTime;
        findTimeUnit();

    }

    public void pauseTimer() {
        pauseTime = System.nanoTime();
    }

    public void continueTimer() {
        double endTime = System.nanoTime();
        totalPauseTime = endTime - pauseTime;
        currentTime += totalPauseTime;
    }

    public void stopTimer(boolean shouldDisplayTimeTaken) {

        double endTime = System.nanoTime();
        totalTimeTaken = endTime - currentTime;
        findTimeUnit();

        if (shouldDisplayTimeTaken)
            displayTimeTaken();

    }

    private void displayTimeTaken() {
        System.out.println("Time Taken: " + this.getTotalTimeTaken() + " " + this.getTimeUnit());
    }

    private void findTimeUnit() {

    /*
        final int SECOND = 1000;
        final int MINUTE = 60 * SECOND;
        final int HOUR = 60 * MINUTE;
        final int DAY = 24 * HOUR;
        final int WEEK = 7 * DAY;
        final long MONTH = 30L * DAY;
        final long YEAR = 12 * MONTH;
        final long DECADE = 10 * YEAR;
    */

        if (totalTimeTaken < TimeUnit.MICRO_SECOND.getTimeInMilliSeconds())
            timeUnit = TimeUnit.NANO_SECOND;
        else if (totalTimeTaken < TimeUnit.MILLI_SECOND.getTimeInMilliSeconds())
            timeUnit = TimeUnit.MICRO_SECOND;
        else if (totalTimeTaken < TimeUnit.SECOND.getTimeInMilliSeconds())
            timeUnit = TimeUnit.MILLI_SECOND;
        else if (totalTimeTaken < TimeUnit.MINUTE.getTimeInMilliSeconds())
            timeUnit = TimeUnit.SECOND;
        else if (totalTimeTaken < TimeUnit.HOUR.getTimeInMilliSeconds())
            timeUnit = TimeUnit.MINUTE;
        else if (totalTimeTaken < TimeUnit.DAY.getTimeInMilliSeconds())
            timeUnit = TimeUnit.HOUR;
        else if (totalTimeTaken < TimeUnit.WEEK.getTimeInMilliSeconds())
            timeUnit = TimeUnit.DAY;
        else if (totalTimeTaken < TimeUnit.MONTH.getTimeInMilliSeconds())
            timeUnit = TimeUnit.WEEK;
        else if (totalTimeTaken < TimeUnit.YEAR.getTimeInMilliSeconds())
            timeUnit = TimeUnit.MONTH;
        else if (totalTimeTaken < TimeUnit.DECADE.getTimeInMilliSeconds())
            timeUnit = TimeUnit.YEAR;

        totalTimeTaken /= timeUnit.getTimeInMilliSeconds();

    }

    public double getTotalTimeTaken() {
        return totalTimeTaken;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }
}