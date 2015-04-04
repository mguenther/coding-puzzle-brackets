package com.mgu.codingpuzzle.brackets;

import com.mgu.codingpuzzle.brackets.core.Parser;
import com.mgu.codingpuzzle.brackets.core.ParserException;

/**
 * Runs a check on a single input string sequentially.
 *
 * @author Markus Günther <markus.guenther@gmail.com>
 */
public class SequentialRunner {
    public static void execute(final String inputString) {
        System.out.println("Running sequentially...");
        final Parser parser = new Parser(inputString);
        try {
            parser.parse();
            System.out.println("True");
        } catch (ParserException e) {
            System.out.println("False");
        }
    }
}