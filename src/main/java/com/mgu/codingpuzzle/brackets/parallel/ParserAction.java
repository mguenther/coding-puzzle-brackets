package com.mgu.codingpuzzle.brackets.parallel;

import com.mgu.codingpuzzle.brackets.core.Parser;
import com.mgu.codingpuzzle.brackets.core.ParserException;

import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * <code>RecursiveAction</code> implementation for the parallel execution
 * of <code>ParserTask</code> instances. <code>ForkJoinWorkerThread</code>s
 * operate directly on the <code>ParserTask</code> instances and write
 * the parser result back to that data structure.
 *
 * @author Markus Günther
 */
public class ParserAction extends RecursiveAction {

    private final int MAX_JOB_SIZE = 1;

    private final List<ParserTask> parserTasks;

    public ParserAction(final List<ParserTask> parserTasks) {
        this.parserTasks = parserTasks;
    }

    @Override
    protected void compute() {
        if (workloadTooLarge()) {
            parallelize();
        } else {
            computeSequentially();
        }
    }

    private boolean workloadTooLarge() {
        return this.parserTasks.size() > MAX_JOB_SIZE;
    }

    private void parallelize() {
        final int size = this.parserTasks.size();
        final int midIndex = size / 2;

        final List<ParserTask> parserTasksOne = this.parserTasks.subList(0, midIndex);
        final List<ParserTask> parserTaskTwo = this.parserTasks.subList(midIndex, size);

        final ParserAction parserActionOne = new ParserAction(parserTasksOne);
        final ParserAction parserActionTwo = new ParserAction(parserTaskTwo);

        invokeAll(parserActionOne, parserActionTwo);
    }

    private void computeSequentially() {
        for (ParserTask parserTask : this.parserTasks) {
            execute(parserTask);
        }
    }

    private void execute(final ParserTask parserTask) {
        final Parser parser = new Parser(parserTask.getInputString());
        try {
            parser.parse();
            parserTask.setWellFormed(true);
        } catch (ParserException e) {
            parserTask.setWellFormed(false);
        }
    }
}