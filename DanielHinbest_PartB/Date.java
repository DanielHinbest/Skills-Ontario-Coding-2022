package ca.danielhinbest;

/**
 * Class for the date. Used in Birthday for the Animal class
 */
public class Date {
    /**
     * The year
     */
    private int year;
    /**
     * The month
     */
    private int month;
    /**
     * The date
     */
    private int day;

    /**
     * Constructor to create a new Date object with default values
     */
    public Date() {
        setYear(1900);
        setDay(01);
        setMonth(01);
    }

    /**
     * Constructor to set a date with integer values
     * @param year the year
     * @param month the month
     * @param day the day
     */
    public Date(int year, int month, int day) {
        setDay(day);
        setMonth(month);
        setYear(year);
    }

    /**
     * Constructor to set a new date with an existing date object
     * @param date a date object
     */
    public Date(Date date) {
        setDay(date.getDay());
        setMonth(date.getMonth());
        setYear(date.getYear());
    }

    /**
     * Return the year
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     * Set the year
     * @param year year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Get the month
     * @return month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Set the month
     * @param month month
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * Get the day
     * @return day
     */
    public int getDay() {
        return day;
    }

    /**
     * Set the day
     * @param day day
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Output the Date elements as a string
     * @return Date as a string
     */
    @Override
    public String toString() {
        return getYear() + "-" + getMonth() + "-" + getDay();
    }
}
