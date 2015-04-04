package com.mgu.codingpuzzle.brackets;

import com.mgu.codingpuzzle.brackets.parallel.ParserAction;
import com.mgu.codingpuzzle.brackets.parallel.ParserTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 * Runs checks on a list of input strings in parallel using
 * the Fork-Join framework.
 *
 * @author Markus Günther <markus.guenther@gmail.com>
 */
public class ParallelRunner {
    public static void execute(final String[] args) {
        System.out.println("Running parallel on " + Runtime.getRuntime().availableProcessors() + " cores.");
        final List<String> inputStrings = Arrays.asList(args);
        final List<ParserTask> parserTasks = toParserTasks(inputStrings);

        executeParallel(parserTasks);

        printResults(parserTasks);
    }

    private static List<ParserTask> toParserTasks(final List<String> inputStrings) {
        final List<ParserTask> parserTasks = new ArrayList<>();
        for (int i = 0; i < inputStrings.size(); i++) {
            final ParserTask parserTask = new ParserTask(i, inputStrings.get(i));
            parserTasks.add(parserTask);
        }
        return Collections.unmodifiableList(parserTasks);
    }

    private static void executeParallel(final List<ParserTask> parserTasks) {
        final ParserAction parserAction = new ParserAction(parserTasks);
        final int cores = Runtime.getRuntime().availableProcessors();
        final ForkJoinPool forkJoinPool = new ForkJoinPool(cores);
        forkJoinPool.invoke(parserAction);
    }

    private static void printResults(final List<ParserTask> parserTasks) {
        for (ParserTask parserTask : parserTasks) {
            System.out.println(parserTask);
        }
    }
}