<div class="layui-col-md12 layui-col-xs12 layui-col-lg12">
    <div class="layui-form-item">
        <label class="layui-form-label"><@spring.message label/></label>
        <div class="layui-input-block">
            <#list inputs as input>
                ${RENDER(input)}
            </#list>
        </div>
    </div>
</div>
