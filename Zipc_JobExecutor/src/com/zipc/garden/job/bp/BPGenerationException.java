package com.zipc.garden.job.bp;

/**
 * Exception class thrown when an error occurs during job generation.
 */
public class BPGenerationException extends IllegalStateException {

    /** A unique identifier for the Serializable class */
    private static final long serialVersionUID = 883001703263764225L;

    /**
     * <pre>
     * Constructs an IllegalStateException with the specified detail message.
     * A detail message is a String that describes this particular exception.
     * </pre>
     * 
     * @param message the String that contains a detailed message
     */
    public BPGenerationException(String message) {
        super(message);
    }
}
