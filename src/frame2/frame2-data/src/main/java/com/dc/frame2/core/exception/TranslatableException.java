package com.dc.frame2.core.exception;

/**
 * <p>Base class for i18n translate exception
 *
 * @author DC
 * @date 2018/4/14.
 */
public abstract class TranslatableException extends RuntimeException {
    public TranslatableException() {
    }
    
    public TranslatableException(String message) {
        super(message);
    }
    
    public TranslatableException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public TranslatableException(Throwable cause) {
        super(cause);
    }
    public abstract String getCode();
}
