package com.dc.frame2.data;

import java.util.Objects;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/5/17.
 */
public class DataDictionaryEntry {
    private String text;
    private String value;
    
    public String getText() {
        return text;
    }
    
    public DataDictionaryEntry setText(String text) {
        this.text = text;
        return this;
    }
    
    public String getValue() {
        return value;
    }
    
    public DataDictionaryEntry setValue(String value) {
        this.value = value;
        return this;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DataDictionaryEntry that = (DataDictionaryEntry) o;
        return Objects.equals(text, that.text);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}
