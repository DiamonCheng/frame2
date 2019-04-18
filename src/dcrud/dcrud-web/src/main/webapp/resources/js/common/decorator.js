/****
 *
 *
 *
 *
 *  1. 添加菜单伸缩
 *      添加css类，使用这个类时缩进
 *      当宽度小于 768 隐藏
 *      resize 判断 小于768 默认添加该类
 *      添加该类时 hover 显示菜单
 *
 *      伸缩动画
 *
 *   2. 添加宽度适配选项 使用 relative
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

$(function menu_option() {
    $(".side-menu").addClass("no-transition");
    $(".side-menu a[href]").each(function () {
        var $this = $(this);
        if (window.location.pathname === $this.attr("href")) {
            $this.parent("dd.menu-item")
                .addClass("active")
        }
        if (window.location.pathname.indexOf($this.attr("href")) !== -1) {
            $this.parent("dd.menu-item")
                .parents("dd.menu-item")
                .removeClass("close")
                .addClass("open");
        }
    });
    setTimeout(function(){
        $(".side-menu").removeClass("no-transition");
    },200);

    /*menu click event*/
    $(document).on("click", ".side-menu dd.menu-item>a", function () {
        // console.log(this);
        var $dd = $(this).parent();
        if ($(this).next("dl.menu").length === 0) {
            $('.side-menu dd.menu-item>a').removeClass("active");
            $dd.addClass("active");
        } else {
            if ($dd.hasClass("open")) {
                $dd.removeClass("open").addClass("close");
            } else {
                $dd.addClass("open").removeClass("close");
            }
        }
    });

    /*resize change event*/
    $('#frame2_app_flexible').click(function () {
        var $app = $("#frame2_app");
        if ($app.hasClass("shrink")) {
            $app.removeClass("shrink");
        } else {
            $app.addClass("shrink");
        }
    });

    $(window).on("resize", function () {
        // console.log("resize - ", window.innerWidth);
        var $app = $("#frame2_app");
        if (window.innerWidth > 768) {
            if ($app.hasClass("shrink")) {
                $app.removeClass("shrink");
            }
        } else if (!$app.hasClass("shrink")) {
            $app.addClass("shrink");

            //hide conditions
            $(".query-panel-conditions").removeClass("layui-show");
            $(".query-panel-head>i").html("&#xe602");
        }


    }).trigger("resize");
    $("#frame2_app #shade").click(function () {
        $("#frame2_app").addClass("shrink");
    });
});