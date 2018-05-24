<section class="layui-collapse layui-card ">
    <div class="layui-card-body option-buttons">
        <#list buttons as button>
            ${RENDER(button)}
        </#list>
    </div>
</section>