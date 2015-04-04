package com.mgu.codingpuzzle.brackets.core;

/**
 * Simple lexer that tokenizes all symbols in the alphabet
 * \Sigma = { '(', ')', '{', '}', '[', ’]’} and throws a
 * <code>ParserException</code> if it encounters symbols
 * it cannot recognize.
 *
 * @author Markus Günther
 */
public class Lexer {

    private final String input;

    private int index = 0;

    private char currentCharacter;

    public Lexer(final String input) {
        this.input = input;
    }

    /**
     * Yields the next token of the input or <code>Token.EOF</code> if there
     * is no such token. This method does not omit whitespace. If this method
     * encounters whitespaces or any other symbols that are not in \Sigma
     * a <code>ParserException</code> is raised.
     *
     * @throws ParserException
     *      If the next token is not in the alphabet \Sigma
     * @return
     *      The next token in the input, represented by an instance
     *      of <code>Token</code>
     */
    public Token nextToken() {

        if (isAtEnd()) {
            return Token.EOF;
        }

        consume();

        switch(this.currentCharacter) {
            case '{':
                return Token.CURLY_BRACE_LEFT;
            case '}':
                return Token.CURLY_BRACE_RIGHT;
            case '(':
                return Token.PARENTHESIS_LEFT;
            case ')':
                return Token.PARENTHESIS_RIGHT;
            case '[':
                return Token.BRACKET_LEFT;
            case ']':
                return Token.BRACKET_RIGHT;
            default:
                throw new ParserException();
        }
    }

    private boolean isAtEnd() {
        return this.index >= this.input.length();
    }

    private void consume() {
        this.currentCharacter = this.input.charAt(this.index);
        this.index++;
    }
}