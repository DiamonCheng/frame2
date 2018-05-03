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

$(function menu_option(){
    $(document).on("click","dd.menu-item>a",function(){
       console.log(this);
       var $dd=$(this).parent();
       if ($dd.hasClass("open")){
           $dd.removeClass("open");
       }else{
           $dd.addClass("open");
       }
    });
});