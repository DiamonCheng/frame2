package com.dc.dcrud.web.view.option;

/**
 * <p>Descriptions...
 * Used to switch button is shown.
 *
 * @author Diamon.Cheng
 * @date 2018/8/30.
 */
public interface OperationPermissionCheck {
    /**
     * true if show this button
     *
     * @return true if show this button
     */
    boolean test(OptionButton optionButton);
    
    /**
     * use to add a external permission check condition
     *
     * @param check @return true if show this button
     * @return OperationPermissionCheck
     */
    default OperationPermissionCheck and(OperationPermissionCheck check) {
        return (b) -> test(b) && check.test(b);
    }
}
