package com.dc.dcrud.web.view.support;

import com.dc.dcrud.web.view.option.DefaultOptionButtonView;
import com.dc.dcrud.web.view.option.OperationPermissionCheck;
import com.dc.dcrud.web.view.option.OptionButton;
import com.dc.dcrud.web.view.support.viewpojo.optionbutton.OptionButtons;
import com.dc.frame2.core.dto.PageSearcher;
import com.dc.frame2.util.SpringContextUtils;
import com.dc.frame2.view.Frame2View;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/10/30.
 */
public class QueryPageViewOptionButtonFactory implements QueryViewFactory {
    
    private List<com.dc.dcrud.web.view.support.viewpojo.optionbutton.OptionButton> buttonConfigs = null;
    
    public QueryPageViewOptionButtonFactory configure(PageSearcher searcher) {
        Assert.notNull(searcher, "Searcher cannot be null.");
        Class<?> configPojoClass = searcher.getClass();
        OptionButtons optionButtons = configPojoClass.getAnnotation(com.dc.dcrud.web.view.support.viewpojo.optionbutton.OptionButtons.class);
        if (optionButtons == null) {
            buttonConfigs = Collections.emptyList();
        } else {
            buttonConfigs = Arrays.asList(optionButtons.value());
        }
        return this;
    }
    
    @Override
    public Frame2View createPageQueryView(PageSearcher searcher) {
        if (buttonConfigs == null) {
            configure(searcher);
        }
        DefaultOptionButtonView defaultOptionButtonView = new DefaultOptionButtonView();
        buttonConfigs.forEach(buttonConfig -> {
            OptionButton optionButton = new OptionButton()
                                                .setName(buttonConfig.name())
                                                .setTitle(StringUtils.isEmpty(buttonConfig.title()) ? buttonConfig.name() : buttonConfig.title())
                                                .setId(StringUtils.isEmpty(buttonConfig.id()) ? null : buttonConfig.id())
                                                .setType(buttonConfig.type());
            Arrays.stream(buttonConfig.classes()).forEach(optionButton::addCls);
            optionButton.addAttr("href", buttonConfig.href());
            if (!OperationPermissionCheck.class.equals(buttonConfig.permissionCheckClass())) {
                optionButton.setPermissionCheck(SpringContextUtils.tryGetInstanceByClass(buttonConfig.permissionCheckClass()));
            }
            defaultOptionButtonView.addButton(optionButton);
        });
        return defaultOptionButtonView;
    }
}
