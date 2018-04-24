package com.dc.frame2.view.engine.freemarker;

import com.dc.frame2.view.Frame2View;
import com.dc.frame2.view.Frame2ViewRender;
import com.dc.frame2.view.support.Frame2ServletContextResolver;
import com.dc.frame2.view.support.Frame2ViewConfiguration;
import com.dc.frame2.view.view.freemarker.FreemarkerView;
import freemarker.ext.beans.StringModel;
import freemarker.template.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

/**
 * <p>Descriptions...
 *
 * @author Defferson.Cheng
 * @date 2018/4/19.
 */
public class FreemarkerViewRender implements Frame2ViewRender {
    private Frame2ServletContextResolver frame2ServletContextResolver;
    private Frame2ViewConfiguration frame2ViewConfiguration;
    private Configuration configuration;
    private static final String RENDER_NAME = "RENDER";
    
    public static class FreemarkerViewRenderContext implements TemplateMethodModelEx {
        private SimpleHash moduleContext;
        private Configuration configuration;
        private Writer writer;
        
        public void render(Frame2View frame2View) throws IOException, TemplateException {
            moduleContext.putAll(frame2View.getParam());
            Template template = configuration.getTemplate(frame2View.getTemplateName());
            template.process(moduleContext, writer);
        }
        
        public SimpleHash getModuleContext() {
            return moduleContext;
        }
        
        public FreemarkerViewRenderContext setModuleContext(SimpleHash moduleContext) {
            this.moduleContext = moduleContext;
            return this;
        }
        
        public Configuration getConfiguration() {
            return configuration;
        }
        
        public FreemarkerViewRenderContext setConfiguration(Configuration configuration) {
            this.configuration = configuration;
            return this;
        }
        
        public Writer getWriter() {
            return writer;
        }
        
        public FreemarkerViewRenderContext setWriter(Writer writer) {
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
    
    @Override
    public void render(Frame2View frame2View, Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SimpleHash fmModel = frame2ServletContextResolver.buildContextModule(model, request, response);
        response.setCharacterEncoding(frame2ViewConfiguration.getCharset());
        FreemarkerViewRenderContext context = new FreemarkerViewRenderContext()
                                                      .setConfiguration(configuration)
                                                      .setModuleContext(fmModel)
                                                      .setWriter(response.getWriter());
        fmModel.put(RENDER_NAME, context);
        context.render(frame2View);
    }
    
    @Override
    public Class<? extends Frame2View> supportType() {
        return FreemarkerView.class;
    }
    
    public FreemarkerViewRender setConfiguration(Configuration configuration) {
        this.configuration = configuration;
        return this;
    }
    
    public FreemarkerViewRender setFrame2ServletContextResolver(Frame2ServletContextResolver frame2ServletContextResolver) {
        this.frame2ServletContextResolver = frame2ServletContextResolver;
        return this;
    }
    
    public FreemarkerViewRender setFrame2ViewConfiguration(Frame2ViewConfiguration frame2ViewConfiguration) {
        this.frame2ViewConfiguration = frame2ViewConfiguration;
        return this;
    }
}
