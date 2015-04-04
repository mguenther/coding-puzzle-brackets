package com.mgu.codingpuzzle.brackets.core;

import org.junit.Test;

/**
 * Testsuite for {{@link com.mgu.codingpuzzle.brackets.core.Parser}}.
 *
 * @author Markus Günther <markus.guenther@gmail.com>
 */
public class ParserTest {

    @Test(expected = ParserException.class)
    public void parseShouldThrowExceptionOnEmptyString() {
        parse("");
    }

    @Test(expected = ParserException.class)
    public void parseShouldThrowExceptionOnWhitespaceString() {
        parse("  ");
    }

    @Test
    public void parseShouldRecognizeSingleParentheses() {
        parse("()");
    }

    @Test(expected = ParserException.class)
    public void parseShouldThrowParserExceptionIfSingleParenthesisIsNotClosed() {
        parse("(");
    }

    @Test
    public void parseShouldRecognizeSubsequentParentheses() {
        parse("()()");
    }

    @Test
    public void parseShouldRecognizeSingleBraces() {
        parse("{}");
    }

    @Test(expected = ParserException.class)
    public void parseShouldThrowExceptionIfSingleBraceIsNotClosed() {
        parse("{");
    }

    @Test
    public void parseShouldRecognizeSingleBrackets() {
        parse("[]");
    }

    @Test(expected = ParserException.class)
    public void parseShouldThrowExceptionIfSingleBracketIsNotClosed() {
        parse("[");
    }

    @Test
    public void parseShouldRecognizeBracesNestedInParentheses() {
        parse("({})");
    }

    @Test
    public void parseShouldRecognizeBracketsNestedInBraces() {
        parse("{[]}");
    }

    @Test
    public void parseShouldRecognizeBracketsNestedInBrackets() {
        parse("[[]]");
    }

    @Test
    public void parseShouldRecognizeParenthesesInBrackets() {
        parse("[()]");
    }

    @Test
    public void parseShouldRecognizeBracesInBrackets() {
        parse("[{}]");
    }

    @Test(expected = ParserException.class)
    public void parseShouldThrowParserExceptionIfClosingBraceAppearsWithoutOpeningBrace() {
        parse("{}}");
    }

    @Test(expected = ParserException.class)
    public void parseShouldThrowParserExceptionIfClosingParenthesisAppearsWithoutOpeningParenthesis() {
        parse("())");
    }

    @Test(expected = ParserException.class)
    public void parseShouldThrowParserExceptionIfClosingBracketAppearsWithoutOpeningBracket() {
        parse("[]]");
    }

    @Test(expected = ParserException.class)
    public void parseShouldThrowParserExceptionIfLastOpenedElementIsNotClosedDirectly() {
        parse("({)}");
    }

    @Test(expected = ParserException.class)
    public void parseShouldThrowParserExceptionIfBracketsAreNestedInParentheses() {
        parse("([])");
    }

    @Test(expected = ParserException.class)
    public void parseShouldThrowParserExceptionIfParenthesesAreNestedInParentheses() {
        parse("(())");
    }

    @Test(expected = ParserException.class)
    public void parseShouldThrowParserExceptionIfParenthesesAreNestedInBraces() {
        parse("{()}");
    }

    @Test(expected = ParserException.class)
    public void parseShouldThrowParserExceptionOnErrorInDeeplyNestedBrackets() {
        parse("[([])]");
    }

    @Test
    public void parseShouldRecognizeSubsequentBrackets() {
        parse("[][]");
    }

    @Test
    public void parseShouldRecognizeNestedSubsequentBrackets() {
        parse("{[][()()]}");
    }

    @Test
    public void parseShouldRecognizeSubsequentBraces() {
        parse("{}{}");
    }

    private void parse(final String input) {
        final Parser parser = new Parser(input);
        parser.parse();
    }
}