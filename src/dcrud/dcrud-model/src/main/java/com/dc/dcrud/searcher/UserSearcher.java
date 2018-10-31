package com.dc.dcrud.searcher;

import com.dc.dcrud.domain.UserEntity;
import com.dc.dcrud.web.view.support.viewpojo.inputview.TextInput;
import com.dc.dcrud.web.view.support.viewpojo.optionbutton.OptionButton;
import com.dc.dcrud.web.view.support.viewpojo.optionbutton.OptionButtons;
import com.dc.frame2.core.dao.conditions.CompareOperator;
import com.dc.frame2.core.dao.conditions.Condition;
import com.dc.frame2.core.dao.conditions.ConditionsGroup;
import com.dc.frame2.core.dao.conditions.PreContact;
import com.dc.frame2.core.dto.PageSearcher;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/4/14.
 */

@OptionButtons({
        @OptionButton(name = "crud.query.test.button1.text"),
        @OptionButton(name = "crud.query.test.button2.text"),
        @OptionButton(name = "crud.query.test.button3.text")
})
public class UserSearcher extends PageSearcher<UserEntity>{
    private Condition2 condition2;
    @TextInput
    @Condition(order = 1,operator = CompareOperator.DUP_LIKE)
    private String nickName;
    @Condition(order = 2,preContact = PreContact.OR,operator = CompareOperator.DUP_LIKE)
    @TextInput
    private String userName;
    
    
    public String getUserName() {
        return userName;
    }
    
    public UserSearcher setUserName(String userName) {
        this.userName = userName;
        return this;
    }
    
    public String getNickName() {
        return nickName;
    }
    
    public UserSearcher setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }
    
    public Condition2 getCondition2() {
        return condition2;
    }
    
    public UserSearcher setCondition2(Condition2 condition2) {
        this.condition2 = condition2;
        return this;
    }
    
    @Override
    public Class<UserEntity> getViewObjectClass() {
        return UserEntity.class;
    }
    
    public static class Condition2 implements ConditionsGroup{
        @Condition(operator = CompareOperator.DUP_LIKE)
        private String userName;
        @Condition(preContact = PreContact.OR,operator = CompareOperator.DUP_LIKE)
        private String userPassword;
        public String getUserPassword() {
            return userPassword;
        }
    
        public Condition2 setUserPassword(String userPassword) {
            this.userPassword = userPassword;
            return this;
        }
    
        public String getUserName() {
            return userName;
        }
    
        public Condition2 setUserName(String userName) {
            this.userName = userName;
            return this;
        }
    }
    
}
