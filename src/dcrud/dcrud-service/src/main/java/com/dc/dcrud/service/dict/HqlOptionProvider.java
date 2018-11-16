package com.dc.dcrud.service.dict;

import com.dc.dcrud.web.view.support.viewpojo.inputview.OptionProvider;
import com.dc.frame2.view.view.freemarker.form.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/11/15.
 */
@Service("HqlOptionProvider")
public class HqlOptionProvider implements OptionProvider {
    
    @Autowired
    private EntityManager entityManager;
    
    
    /**
     * usage option key must be a jpql and the select tow field as text and value
     *
     * @param optionKey select xxx as text,xxx as value from xxxxx [where xxxx]
     * @param locale
     * @return
     */
    @Override
    public List<Option> listOptions(String optionKey, Locale locale) {
        try {
            Query query = entityManager.createQuery(optionKey);
            List res = query.getResultList();
            List<Option> resOptions = new ArrayList<>(res.size());
            for (Object obj : res) {
                Object[] objs = (Object[]) obj;
                resOptions.add(new Option().setText(objs[0].toString()).setValue(objs[1].toString()));
            }
            return resOptions;
        } catch (Exception e) {
            throw new IllegalStateException("Illegal Hql statement :" + optionKey, e);
        }
    }
}
