package com.mgu.codingpuzzle.brackets.core;

/**
 * Exception type that is raised when the <code>Lexer</code> is unable to
 * recognize a given symbol or the parser is unable to match tokens according
 * to the implemented grammar.
 *
 * @author Markus Günther <markus.guenther@gmail.com>
 */
public class ParserException extends RuntimeException {

    public ParserException() {
        super();
    }

    public ParserException(final String message) {
        super(message);
    }
}