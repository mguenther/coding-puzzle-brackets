package com.mgu.codingpuzzle.brackets.core;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Testsuite for {{@link com.mgu.codingpuzzle.brackets.core.Lexer}}.
 *
 * @author Markus Günther <markus.guenther@gmail.com>
 */
public class LexerTest {

    @Test
    public void shouldReturnImmediatelyIfEmptyStringIsGiven() {
        final Lexer lexer = new Lexer("");
        final Token token = lexer.nextToken();
        assertEquals(Token.EOF, token);
    }

    @Test
    public void nextTokenShouldReturnActualNextToken() {
        final Lexer lexer = new Lexer("{");
        final Token token = lexer.nextToken();
        assertEquals(Token.CURLY_BRACE_LEFT, token);
    }

    @Test
    public void lexerShouldProgressAfterYieldingAToken() {
        final Lexer lexer = new Lexer("{}");
        final Token firstToken = lexer.nextToken();
        final Token secondToken = lexer.nextToken();
        assertEquals(Token.CURLY_BRACE_LEFT, firstToken);
        assertEquals(Token.CURLY_BRACE_RIGHT, secondToken);
    }

    @Test(expected = ParserException.class)
    public void lexerShouldThrowParseExceptionOnUnrecognizableSymbol() {
        final Lexer lexer = new Lexer("a");
        Token currentToken = null;
        while ((currentToken = lexer.nextToken()) != Token.EOF) {
        }
    }

    @Test
    public void lexerShouldRecognizeAllSymbolsOfTheAlphabet() {
        final Lexer lexer = new Lexer("{}[]()");
        Token currentToken = null;
        while ((currentToken = lexer.nextToken()) != Token.EOF) {
        }
        assertEquals(Token.EOF, currentToken);
    }

    @Test(expected = ParserException.class)
    public void lexerShouldThrowExceptionOnWhitespace() {
        final Lexer lexer = new Lexer(" ");
        Token currentToken = null;
        while ((currentToken = lexer.nextToken()) != Token.EOF) {
        }
    }
}