<section class="layui-collapse layui-card query-panel">
    <div class="layui-colla-item">
        <div class="layui-colla-title query-panel-head"><@spring.message title/></div>
        <div class="layui-colla-content query-panel-conditions layui-anim-fadein layui-anim layui-show">
            <div class=" layui-row layui-col-space10">
                <#list conditions as condition>
                    ${RENDER(condition)}
                </#list>
                <div class="layui-col-md12 layui-col-xs12 layui-col-lg12">
                    <div class="layui-form-item layui-re query-panel-bottom">
                        <input type="submit" class="layui-btn query-btn"
                               value="<@spring.message 'crud.query.condition.button.query'/>"/>
                        <input type="reset" class="layui-btn layui-btn-primary reset-btn"
                               onclick="window.location.replace('./')"
                               value="<@spring.message 'crud.query.condition.button.reset'/>"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
<#--http://www.layui.com/demo/form.html-->
</section>

