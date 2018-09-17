package com.ideas2it.hospitalmanagement.exception;

/**
 * ApplicationException is a custom Exception class defined by user
 * specifically for the application designed. It is used to handle
 * multiple exceptions at the same time and avoid program being terminated.
 *
 * @author Santhosh Kumar
 */
public class ApplicationException extends Throwable {
    
    private static int code;

    public ApplicationException(int code) {
        super();
        this.code = code;
    }

    public ApplicationException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
 
    public ApplicationException(Throwable cause) {
        super(cause);
    }

    public ApplicationException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }
	
    public int getCode() {
        return this.code;
    }
}
