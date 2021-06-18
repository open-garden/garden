package com.zipc.garden.job.bp;

/**
 * Exception class thrown when job generation is canceled.
 */
public class GenerationCancellationException extends Exception {

    /** A unique identifier for the Serializable class */
    private static final long serialVersionUID = -3393856294943967943L;

    /**
     * Constructs a new exception with the specified detail message.
     * @param message the detail message. The detail message is saved for later retrieval by the {@link #getMessage()} method.
     */
    public GenerationCancellationException(String message) {
        super(message);
    }
}
