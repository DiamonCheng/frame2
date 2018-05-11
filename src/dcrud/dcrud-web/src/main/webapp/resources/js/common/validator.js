/*
* DC 表单校验工具
*
* 使用方法： input，select，textarea 添加 validator="required" 之类的属性，
* 统一校验器定义在 validator.list.js 中,
* 可以在单独页面中只用 window.validator.rules.自定义规则={test:function(value){验证代码}}添加自定义的校验工具
*
* 对于特殊的操作可以参考源代码手动调用包中的方法。
*/

(function ($) {
    window.validator = {
        valid: function ($input) {
            var validRules = $input.attr("validator");
            if (validRules == null || validRules.length === 0) {
                return true;
            }
            validRules = validRules.split(",");
            var res = true;
            for (var i in validRules) {
                var ruleName = validRules[i];
                var rule = validator.rules[ruleName];
                if (rule == null) {
                    console.error($input, "input validate rule '" + ruleName + "' not found, cannot pass this validate. Check rule name in 'validator.list.js' file");
                    return false;
                }
                if (rule.test($input.val())) {
                    validator.removeValidateTips($input);
                } else {
                    validator.addValidTips($input, rule.message);
                    res = false;
                    break;
                }
            }
            validator.onValidate($input, res);
            return res;
        },
        removeValidateTips: function ($input) {
            $input.parent().removeClass("danger").find("div.danger-tip").remove();

        },
        addValidTips: function ($input, message) {
            var $parent = $input.parent();
            if ($parent.hasClass("danger")) {
                $parent.find("div.danger-tip>span.danger-text").text(message);
            } else {
                $parent.addClass("danger").append(
                    $("<div>").addClass("danger-tip").append(
                        $("<span class=\"layui-badge\">!</span>")
                    ).append(
                        $("<span>").addClass("danger-text").text(message)
                    )
                );
            }
            /*`<div class="danger-tip">
                        <span class="layui-badge">!</span> <span class="danger-text"></span>
                    </div>`*/
        },
        onValidate: function ($input, res) {

        },
        register: function ($form) {
            if (!$form) {
                $form = $("form");
            }
            $form.on("change", ":input[validator]", function () {
                validator.valid($(this));
            });

            $form.submit(function () {
                var res = true;
                $form.find(":input[validator]").each(function () {
                    var $input = $(this);
                    if (!validator.valid($input)) {
                        res = false;
                        console.log($input, "valid failed, cannot submit form.");
                    }
                });
                return res;
            });

        },
        rules: {}
    }
})($);