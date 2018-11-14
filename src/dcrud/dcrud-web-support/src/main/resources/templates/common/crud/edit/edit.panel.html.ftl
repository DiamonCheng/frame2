<section class=" layui-card edit-panel">
    <div class="layui-card-header edit-panel-head"><@spring.message title/><i class="layui-icon layui-colla-icon"></i>
    </div>
    <div class="layui-card-body edit-panel-inputs">
        <div class=" layui-row layui-col-space10">
            <#list conditions as condition>
                ${RENDER(condition)}
            </#list>
            <div class="layui-col-md12 layui-col-xs12 layui-col-lg12">
                <div class="layui-form-item layui-re query-panel-bottom">
                    <input type="submit" class="layui-btn query-btn"
                           value="<@spring.message 'crud.edit.submit'/>"/>
                    <input type="reset" class="layui-btn layui-btn-primary reset-btn"
                           onclick="window.location.href=('${backHref}')"
                           value="<@spring.message 'crud.edit.back'/>"/>
                </div>
            </div>
        </div>
    </div>
<#--http://www.layui.com/demo/form.html-->
</section>

