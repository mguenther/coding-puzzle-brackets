This repository features a solution to an interesting problem which I came across recently on Github (cf. Github users bkircher and emaskovsky) and which - as it seems - is also a recurring puzzle that is used during interview processes at various companies. Let me introduce the problem first, and then we'll talk about possible solutions and my personal take on this.

## Problem description ##

Given a string comprising just of the characters `(`, `)`, `{`, `}`, `[`, `]` determine if it is well-formed or not by applying the following rules:

* Each type of bracket needs to be first opened then closed.
  * Good: `()` or `[]` or `{}`
  * Bad: `(()` or `{}}`
* You can only close the last bracket that was opened.
  * Good: `({})`
  * Bad: `({)}`
* Inside parentheses `()` only braces `{}` are allowed.
  * Good: `({})`
  * Bad: `([])` or `(())`
* Inside braces `{}` only square brackets `[]` are allowed.
  * Good: `{[]}`
  * Bad: `{()}` or `{{}}`
*  Any bracket can appear (directly) inside square brackets `[]`
  * Good: `[()]` or `[{}]` or `[[]]` or `[[[]]]`
  * Bad: `[([])]`
* You can use a list of braces where a single one is allowed of that type
  * Good: `[()()]` or `{[][()()]}` or `()()`
* An empty string is not a valid expression

For a given string print out `True` if the string is well-formed or `False` if otherwise.

### Part I ###

Write a program that can read from stdin. Process all lines and print out the result to stdout.

### Part II ###

Enhance the program from the previous part so that it uses a multi-threaded approach. Dispatch the actual strings to test to as many threads as the machine has cores. Because the order is not necessarily the same as the input provide the output with the referring index of the n-th input like this (no spaces):

```
1:True
2:False
```

### Limitations ###

You can either use C++ or Java for your solution. Depending on the language, these are the limitations set for this problem:

* C++ Compiler: >= 4.1.2 (C++ 11 allowed for new compilers)
* C++ Library: STL and BOOST only
* Java Compiler: >= Java 7

The original problem description constrained solutions further with regard to lines of code and execution time. However, I do not care for micro-optimizations, so my personal goals in solving this puzzle was with regard to code quality and test coverage.

## Solution ##

Obviously, this sort of problem seems to be easily solvable if you use a stack and push the characters you read from stdin onto it, while checking for the various rules when popping them from the stack. While you can certainly follow this approach, it might get messy as soon as you have to implement rules like "inside parentheses only braces are allowed".

I've also seen a nice C++-based solution that uses a state machine and implements the set of rules as state transitions.

I went for a different approach and implemented my solution using Java. For the first part of the problem, I transformed the given set of rules into the following EBNF, which formally describes what "well-formed" means in the context of the given problem:

```
INPUT       := PARENTHESES
             | BRACES 
             | BRACKETS
PARENTHESES := () 
             | (BRACES) 
             | ()PARENTHESES
BRACES      := {} 
             | {BRACKETS} 
             | {}BRACES
BRACKETS    := [] 
             | [BRACKETS] 
             | [PARENTHESES] 
             | [BRACES] 
             | []BRACKETS
```

Using this grammar, I implemented a parser which encodes the various rules. The parser uses a lexer which breaks the input string down into the tokens that comprise the alphabet for this grammar. In my opinion, this approach has the benefit that it clearly separates various abstraction levels:
* The lexer breaks down the input string into tokens that are of interest for the actual parser. At this stage, we are able to dismiss any characters that are not part of the language we want to parse and fail-fast.
* The parser fetches tokens continuously from the lexer and parses them according to the grammar. At this level of abstraction, we are able to recognize errors with regard to the given set of rules.

Solving the second part of the problem was quite easy using Java's Fork/Join framework. The performance of it is decent I guess, but I did not look into it very thoroughly since I strived for code clarity and clear separation of various concerns.

I particularly enjoyed this coding puzzle, because the given problem can be solved using different interesting approaches and is small enough to do it well in a couple of hours. For reference: It took me 2 hours and 45 minutes to solve the problem with this approach.

I'm interested in hearing about your solutions to this problem as well, so feel free to drop me an E-mail.
