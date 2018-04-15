package com.dc.frame2.core.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Base class for i18n translate exception
 * <p> CODE DEFINITION:
 *           [ERROR].[SYSTEM|BUSINESS].{SYSTEM NAME}.{FUNCTION NAME}.[SCENE NAME]
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
    
    public static Builder builder(){return new Builder();}
    
    public static class Builder{
        private Throwable cause;
        private List<Throwable> suppressed;
        private String message;
        private Map<String,Object> context;
        private String type="SYSTEM";
        private String system="MAIN";
        private String functionName="- -";
        private String sceneName="- -";
        
        public Builder builder(){
            return new Builder();
        }
    
        public Builder context(String key,Object value){
            if (context==null){
                context=new HashMap<>(3);
            }
            context.put(key,value);
            return this;
        }
    
        public Builder message(String message) {
            this.message = message;
            return this;
        }
    
        public Builder cause(Throwable cause) {
            this.cause = cause;
            return this;
        }
    
        public Builder functionName(String functionName) {
            this.functionName = functionName;
            return this;
        }
    
        public Builder sceneName(String sceneName) {
            this.sceneName = sceneName;
            return this;
        }
    
        public Builder addSuppressed(Throwable suppressed) {
            if (this.suppressed ==null){
                this.suppressed=new ArrayList<>();
            }
            this.suppressed.add(suppressed);
            return this;
        }
    
        public Builder system(String system) {
            this.system = system;
            return this;
        }
    
        public Builder type(String type) {
            this.type = type;
            return this;
        }
    
        public TranslatableException build(){
            final String code="ERROR."+type+"."+system+"."+functionName+"."+sceneName;
            return new TranslatableException("CODE:"+code+", CONTEXT:"+context+", MESSAGE:"+message,cause) {
                {{if (suppressed!=null){ suppressed.forEach(this::addSuppressed); }}}
                @Override
                public String getCode() {
                    return code;
                }
            };
        }
    }
    
}
