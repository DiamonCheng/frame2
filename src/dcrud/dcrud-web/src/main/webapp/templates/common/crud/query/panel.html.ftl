<form>
    <section class="layui-collapse layui-card query-panel">
        <div class="layui-colla-item">
            <div class="layui-colla-title query-panel-head">查询页 demo</div>
            <div class="layui-colla-content query-panel-conditions layui-anim-fadein layui-anim layui-row layui-col-space10 layui-show">
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
                            <textarea name="" placeholder="请输入" class="layui-textarea"></textarea>
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
    <#--http://www.layui.com/demo/form.html-->
    </section>

    <section class="layui-collapse layui-card ">
        <div class="layui-card-body option-buttons">
            <a href="javascript:" class="layui-btn">按钮一</a>
            <a href="javascript:" class="layui-btn layui-btn-primary">按钮三</a>
            <a href="javascript:" class="layui-btn layui-btn-danger">按钮二</a>
        </div>
    </section>

    <section class="layui-collapse layui-card ">
        <div class="layui-card-body data-table" lay-size="sm">

            <table class="layui-table">
                <thead>
                <tr>
                    <th>昵称
                        <span class="layui-table-sort layui-inline" lay-sort="desc">
                            <i class="layui-edge layui-table-sort-asc"></i>
                            <i class="layui-edge layui-table-sort-desc"></i>
                        </span>
                    </th>
                    <th>积分</th>
                    <th>签名</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>贤心1</td>
                    <td>66</td>
                    <td>人生就像是一场修行a</td>
                    <td>
                        <a href="javascript:" class="layui-btn layui-btn-xs">修改</a>
                        <a href="javascript:" class="layui-btn layui-btn-primary layui-btn-xs">详情</a>
                        <a href="javascript:" class="layui-btn layui-btn-danger layui-btn-xs">删除</a>
                    </td>
                </tr>
                <tr>
                    <td>贤心2</td>
                    <td>88</td>
                    <td>人生就像是一场修行b</td>
                    <td>
                        <a href="javascript:" class="layui-btn layui-btn-xs">修改</a>
                        <a href="javascript:" class="layui-btn layui-btn-primary layui-btn-xs">详情</a>
                        <a href="javascript:" class="layui-btn layui-btn-danger layui-btn-xs">删除</a>
                    </td>
                </tr>
                <tr>
                    <td>贤心3</td>
                    <td>33</td>
                    <td>人生就像是一场修行c</td>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </div>
    </section>
    <section class="layui-collapse layui-card ">
        <div class="layui-card-body page-bar" id="page-bar" totalCount="101" pageNo="2" pageSize="20">
        </div>
    </section>

</form>