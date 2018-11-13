(function () {

    $(".data-table table").click("tr td a.table-delete-btn[ajax=true]", function (e) {
        var $this = $(e.target);
        var $tr = $this.parents(".data-table table tr");
        if ($tr.length === 0) {
            return false;
        }
        var confirm = $this.attr("confirm");
        var data = $tr.data();
        var href = $this.attr("ahref");
        var postFunction = function () {
            $.post(href, data, function () {
                messager.message(lang.option.success);
                window.location.reload();
            }, "JSON");
        };
        if (confirm == "true") {
            messager.confirm(window.lang.option.confirmTitle, window.lang.option.confirmOption.format($this.text()), postFunction)
        } else {
            postFunction();
        }
        return false;
    })

})();