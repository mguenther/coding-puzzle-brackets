package com.mgu.codingpuzzle.brackets;

/**
 * Simple driver that invokes the parser on a single input. Writes
 * <code>True</code> to the console if the input string is well-formed,
 * <code>False</code> otherwise.
 *
 * @author Markus Günther <markus.guenther@gmail.com>
 */
public class Driver {
    public static void main(String[] args) {
        if (args.length == 1) {
            SequentialRunner.execute(args[0]);
        } else {
            ParallelRunner.execute(args);
        }
    }
}