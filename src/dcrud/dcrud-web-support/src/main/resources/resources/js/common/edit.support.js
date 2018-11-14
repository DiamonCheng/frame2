/***
 *
 */
$(function () {
    $("#editForm").submit(function () {
        var $form = $(this);
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
                    if (xhr.status == 500) {
                        var e = JSON.parse(xhr.responseText);
                        console.error(e);
                        messager.error(lang.option.failed + " " + e.message);
                    } else {
                        console.error("ajax请求出现异常:", xhr);
                        console.error("status:", status);
                        console.error(e);
                        messager.error(lang.option.unknownError);
                    }
                }
            });
        }
        return false;
    });
});