package com.mgu.codingpuzzle.brackets.core;

/**
 * Recursive-descent parser with 1-character lookahead
 * for the grammar defined by the underneath EBNF.
 *
 * <p>
 * INPUT       := PARENTHESES | BRACES | BRACKETS
 * PARENTHESES := () | (BRACES) | ()PARENTHESES
 * BRACES      := {} | {BRACKETS} | {}BRACES
 * BRACKETS    := [] | [BRACKETS] | [PARENTHESES] | [BRACES] | []BRACKETS
 * </p>
 *
 * @author Markus Günther <markus.guenther@gmail.com>
 */
public class Parser {

    private final Lexer lexer;

    private Token lookahead;

    public Parser(final String input) {
        this.lexer = new Lexer(input);
        consume();
    }

    /**
     * Parses the input and raises a <code>ParserException</code> if the
     * input does not adhere to the implemented grammar (see class definition
     * for EBNF).
     *
     * @throws ParserException
     *      If the input is not parseable regarding the implemented  grammar
     */
    public void parse() {
        if (isEof()) {
            throw new ParserException("given input is empty");
        }

        matchInput();

        if (tokensLeft()) {
            throw new ParserException("unable to parse the whole input");
        }
    }

    private boolean isEof() {
        return this.lookahead.equals(Token.EOF);
    }

    private void matchInput() {
        if (isOpeningParenthesis()) {
            matchParentheses();
        } else if (isOpeningCurlyBrace()) {
            matchCurlyBraces();
        } else if (isOpeningBracket()) {
            matchBrackets();
        }
    }

    private boolean isOpening() {
        return isOpeningParenthesis() ||
               isOpeningBracket() ||
               isOpeningCurlyBrace();
    }

    private boolean isOpeningParenthesis() {
        return this.lookahead.equals(Token.PARENTHESIS_LEFT);
    }

    private boolean isOpeningCurlyBrace() {
        return this.lookahead.equals(Token.CURLY_BRACE_LEFT);
    }

    private boolean isOpeningBracket() {
        return this.lookahead.equals(Token.BRACKET_LEFT);
    }

    private void matchParentheses() {
        while (isOpeningParenthesis()) {
            match(Token.PARENTHESIS_LEFT);
            if (isOpeningCurlyBrace()) {
                matchCurlyBraces();
            }
            match(Token.PARENTHESIS_RIGHT);
        }
    }

    private void matchCurlyBraces() {
        while (isOpeningCurlyBrace()) {
            match(Token.CURLY_BRACE_LEFT);
            if (isOpeningBracket()) {
                matchBrackets();
            }
            match(Token.CURLY_BRACE_RIGHT);
        }
    }

    private void matchBrackets() {
        while (isOpeningBracket()) {
            match(Token.BRACKET_LEFT);
            if (isOpening()) {
                matchInput();
            }
            match(Token.BRACKET_RIGHT);
        }
    }

    private boolean tokensLeft() {
        return !this.lookahead.equals(Token.EOF);
    }

    private void match(final Token expectedToken) {
        if (expectedToken.equals(this.lookahead)) {
            consume();
        } else {
            throw new ParserException("expected " + expectedToken + " but got " + this.lookahead);
        }
    }

    private void consume() {
        this.lookahead = this.lexer.nextToken();
    }
}