package com.dc.frame2.view.engine.freemarker;

import com.dc.frame2.view.Frame2View;
import com.dc.frame2.view.Frame2ViewRender;
import freemarker.ext.beans.StringModel;
import freemarker.template.*;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * <p>Descriptions...
 *
 * @author Defferson.Cheng
 * @date 2018/4/19.
 */
public class FreemarkerViewRender implements Frame2ViewRender, TemplateMethodModelEx {
    private Configuration configuration;
    private SimpleHash moduleContext;
    private Writer writer;
    
    @Override
    public void render(Frame2View frame2View) throws IOException, TemplateException {
        moduleContext.putAll(frame2View.getParam());
        Template template = configuration.getTemplate(frame2View.getTemplateName());
        template.process(moduleContext, writer);
    }
    
    public FreemarkerViewRender setModuleContext(SimpleHash moduleContext) {
        this.moduleContext = moduleContext;
        return this;
    }
    
    public FreemarkerViewRender setConfiguration(Configuration configuration) {
        this.configuration = configuration;
        return this;
    }
    
    public FreemarkerViewRender setWriter(Writer writer) {
        this.writer = writer;
        return this;
    }
    
    @Override
    public Object exec(List arguments) throws TemplateModelException {
        if (arguments.size() == 0) {
            throw new TemplateModelException("Arguments[0] not found");
        }
        Object argument = arguments.get(0);
        if (argument instanceof StringModel) {
            argument = ((StringModel) argument).getWrappedObject();
        }
        if (!(argument instanceof Frame2View)) {
            throw new TemplateModelException("Arguments[0] not a Frame2View Object");
        }
        try {
            render((Frame2View) argument);
        } catch (Exception e) {
            throw new TemplateModelException(e);
        }
        
        return "";
    }
}
