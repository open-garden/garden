package com.zipc.garden.webplatform.client.view;

/**
 * Class that manages the handler that can be used for post-processing
 */
public class ProcessHandler {
    /**
     * A handler that can be used for post processing. no arguments.
     */
    public interface PostProcessHandler {
        public void execute();
    }

    /**
     * A handler that can be used for post processing. With arguments
     * @param <T>
     */
    public interface PostProcessHandler2<T> {
        public void execute(T obj);
    }
}
