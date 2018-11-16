/***
 *
 */
$(function () {
    $("#editForm").submit(function () {
        var $form = $(this);
        /*$form.find('.multiple-added').remove();
        $form.find("[multiple=multiple]").each(function(){
            var $this=$(this);
            var name=$this.attr("name");
            var index;
            if ((index=name.indexOf("."))!=-1){
                var name1=name.substring(0,index);
                var name2=name.substring(index+1);
                var value=$this.val();
                if (value!=null && value.trim()!=""){
                    var values=value.split(",");
                    for (var i in values){
                        var $input=$("<input class='multiple-added' type='hidden' name='"+name1+"["+i+"]."+name2+"'/>").val(value);
                        $form.append($input);
                    }
                }

            }
        });*/
        if ($form.data("validStatus") == true) {
            var url = $form.attr("action");
            $("#editForm input[type=submit]").attr("disabled", "disabled");

            $.ajax({
                method: "POST",
                url: url,
                data: $form.serializeArray(),
                success: function (res) {
                    messager.message(window.lang.option.success, function () {
                        $("#editForm input[type=reset]").click();
                    });
                },
                error: function (xhr, status, e) {
                    $("#editForm input[type=submit]").removeAttr("disabled");
                    $.commonAjaxErrorProcess(xhr, status, e);
                }
            });
        }
        return false;
    });
});