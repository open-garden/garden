package com.zipc.garden.job.bp;

/**
 * Receive generated resources and process anything.
 */
public interface IGenerationHandler<T> {

    /**
     * Call before generation started.
     */
    void start();

    /**
     * Set the total number of patterns to be registered.
     * @param amount Total number of patterns
     */
    void setAmount(double amount);

    /**
     * Enter the process to be executed based on the current pattern number.
     * @param current the number of the current pattern
     */
    void setCurrent(double current);

    /**
     * Registers the specified element.
     * @param newElement specified element
     * @throws GenerationCancellationException
     */
    void generated(T newElement) throws GenerationCancellationException;

    /**
     * Call after generation finished.
     */
    void finished();
}
