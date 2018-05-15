<section class="layui-collapse layui-card query-panel">
    <div class="layui-colla-item">
        <div class="layui-colla-title query-panel-head">查询页 demo</div>
        <div class="layui-colla-content query-panel-conditions layui-anim-fadein layui-anim layui-show">
            <div class=" layui-row layui-col-space10">
                <div class="layui-col-md6 layui-col-xs12 layui-col-lg6">
                    <div class="layui-form-item">
                        <label class="layui-form-label">输入框</label>
                        <div class="layui-input-block">
                            <input validator="required" type="text" name="title" placeholder="请输入标题" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-col-md6 layui-col-xs12 layui-col-lg6">
                    <div class="layui-form-item">
                        <label class="layui-form-label">日期选择</label>
                        <div class="layui-input-block">
                            <input validator="required" type="text" name="title2" placeholder="请输入标题" autocomplete="off"
                                   class="layui-input date-picker"
                                   data-picker-option="{min:'2018-01-01',max:'2018-12-12'}">
                        </div>
                    </div>
                </div>
                <div class="layui-col-md6 layui-col-xs12 layui-col-lg6">
                    <div class="layui-form-item">
                        <label class="layui-form-label">日期范围选择</label>
                        <div class="layui-input-block">
                            <input validator="required" type="text" name="date" placeholder="请输入标题" autocomplete="off"
                                   class="layui-input date-range-picker"
                                   data-range-option="{start:'dateStart',end:'dateEnd'}"
                                   data-picker-option="{min:'2018-01-01',max:'2018-12-12'}">
                            <input name="dateStart" value="2018-3-11">
                            <input name="dateEnd" value="2018-3-13">
                        </div>
                    </div>
                </div>
                <div class="layui-col-md6 layui-col-xs12 layui-col-lg6">
                    <div class="layui-form-item">
                        <label class="layui-form-label">普通下拉</label>
                        <div class="layui-input-block" validator="required">
                            <select name="city">
                                <option value=""></option>
                                <option value="0">北京</option>
                                <option value="1">上海</option>
                                <option value="2">广州</option>
                                <option value="3">深圳</option>
                                <option value="4">杭州</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="layui-col-md6 layui-col-xs12 layui-col-lg6">
                    <div class="layui-form-item">
                        <label class="layui-form-label">自动查询下拉</label>
                        <div class="layui-input-block">
                            <select name="city" validator="required">
                                <option value=""></option>
                                <option value="0">北京</option>
                                <option value="1">上海</option>
                                <option value="2">广州</option>
                                <option value="3">深圳</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="layui-col-md6 layui-col-xs12 layui-col-lg6">
                    <div class="layui-form-item">
                        <label class="layui-form-label">自动查询下拉</label>
                        <div class="layui-input-block">
                            <select name="city" validator="required">
                                <option value=""></option>
                                <option value="0">北京</option>
                                <option value="1">上海</option>
                                <option value="2">广州</option>
                                <option value="3">深圳</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                                <option value="4">杭州</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="layui-col-md6 layui-col-xs12 layui-col-lg6">
                    <div class="layui-form-item">
                        <label class="layui-form-label">多对多关系</label>
                        <div class="layui-input-block">
                            <input type="checkbox" name="" title="写作" checked>
                            <input type="checkbox" name="" title="发呆">
                            <input type="checkbox" name="" title="禁用" disabled>
                        </div>
                    </div>
                </div>
                <div class="layui-col-md6 layui-col-xs12 layui-col-lg6">
                    <div class="layui-form-item">
                        <label class="layui-form-label">开关</label>
                        <div class="layui-input-block">
                            <input type="checkbox" name="yyy" lay-skin="switch" lay-text="ON|OFF" checked>
                            <input type="checkbox" name="zzz" lay-skin="switch" lay-text="开启|关闭">
                        </div>
                    </div>
                </div>
                <div class="layui-col-md12 layui-col-xs12 layui-col-lg12">
                    <div class="layui-form-item">
                        <label class="layui-form-label">文本域</label>
                        <div class="layui-input-block">
                            <textarea name="" placeholder="请输入" class="layui-textarea">233
3
3</textarea>
                        </div>
                    </div>
                </div>
                <div class="layui-col-md12 layui-col-xs12 layui-col-lg12">
                    <div class="layui-form-item layui-re query-panel-bottom">
                        <input type="submit" class="layui-btn query-btn" value="查询"/>
                        <input type="reset" class="layui-btn layui-btn-primary reset-btn" value="重置"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
<#--http://www.layui.com/demo/form.html-->
</section>

