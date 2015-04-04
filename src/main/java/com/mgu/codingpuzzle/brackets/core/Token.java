package com.mgu.codingpuzzle.brackets.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Static token definitions for symbols of the alphabet.
 *
 * @author Markus Günther <markus.guenther@gmail.com>
 */
public class Token {

    public static final Token EOF = new Token((char)-1);

    public static final Token CURLY_BRACE_LEFT = new Token('{');

    public static final Token CURLY_BRACE_RIGHT = new Token('}');

    public static final Token PARENTHESIS_LEFT = new Token('(');

    public static final Token PARENTHESIS_RIGHT = new Token(')');

    public static final Token BRACKET_LEFT = new Token('[');

    public static final Token BRACKET_RIGHT = new Token(']');

    private static final Map<Character, String> TOKEN_TYPES = new HashMap<Character, String>();

    static {
        TOKEN_TYPES.put((char)-1, "EOF");
        TOKEN_TYPES.put('{', "CURLY_BRACE_LEFT");
        TOKEN_TYPES.put('}', "CURLY_BRACE_RIGHT");
        TOKEN_TYPES.put('(', "PARENTHESIS_LEFT");
        TOKEN_TYPES.put(')', "PARENTHESIS_RIGHT");
        TOKEN_TYPES.put('[', "BRACKET_LEFT");
        TOKEN_TYPES.put(']', "BRACKET_RIGHT");
    }

    private final char value;

    private Token(final char value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return TOKEN_TYPES.get(this.value);
    }
}