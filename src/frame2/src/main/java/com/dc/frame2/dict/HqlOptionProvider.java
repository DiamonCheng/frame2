package com.dc.frame2.dict;

import com.dc.frame2.view.view.freemarker.form.Option;
import org.hibernate.query.internal.QueryImpl;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/11/15.
 */
public class HqlOptionProvider implements OptionProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(HqlOptionProvider.class);
    
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
            //hibernate 专用
            query.unwrap(QueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            List res = query.getResultList();
            List<Option> resOptions = new ArrayList<>(res.size());
            for (Object obj : res) {
                Map<String, Object> map = (Map<String, Object>) obj;
                String value;
                if (map.get("value") == null) {
                    value = "";
                    LOGGER.warn("For HQL {} alias \"value\" not present.", optionKey);
                } else {
                    value = map.get("value").toString();
                }
                String text = "";
                if (map.get("text_" + locale.getLanguage()) != null) {
                    text = map.get("text_" + locale.getLanguage()).toString();
                } else if (map.get("text") != null) {
                    text = map.get("text").toString();
                } else {
                    LOGGER.warn("For HQL {} alias \"text\" and \"text_{}\" not present.", optionKey, locale.getLanguage());
                }
                resOptions.add(new Option().setText(text).setValue(value));
            }
            return resOptions;
        } catch (Exception e) {
            throw new IllegalStateException("Illegal Hql statement :" + optionKey, e);
        }
    }
}
