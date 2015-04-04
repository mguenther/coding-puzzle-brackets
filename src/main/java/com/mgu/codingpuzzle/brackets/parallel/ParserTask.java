package com.mgu.codingpuzzle.brackets.parallel;

/**
 * Task data holder for parser tasks that are executed in parallel
 * and need to retain the order of appearance for input strings.
 *
 * @author Markus Günther <markus.guenther@gmail.com>
 */
public class ParserTask {

    private final int taskNumber;

    private final String inputString;

    private boolean isWellFormed;

    public ParserTask(final int index, final String inputString) {
        this.taskNumber = index+1; // task numbers are 1-based
        this.inputString = inputString;
    }

    public String getInputString() {
        return this.inputString;
    }

    public void setWellFormed(final boolean isWellFormed) {
        this.isWellFormed = isWellFormed;
    }

    private String isWellFormed() {
        return this.isWellFormed ? "True" : "False";
    }

    @Override
    public String toString() {
        return String.valueOf(this.taskNumber) + ":" + isWellFormed();
    }
}