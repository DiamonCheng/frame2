$(function () {
    console.log("tree-select");
    $('.tree-select').off()
    // .attr('unselectable', 'on')
    // .css('user-select', 'none')
        .on('selectstart', false);
    $('.tree-select dd.tree-select-node>span.tree-select-expand:not(:nth-last-child(2))').off()
        .on('click', function () {
            // console.log("onclick",this);
            var $this = $(this);
            var $parent = $this.parent();
            if ($parent.hasClass("open")) {
                $parent.removeClass("open").addClass("close");
            } else {
                $parent.addClass("open").removeClass("close");
            }
        });
    $('.tree-select :checkbox').on('change', function () {
        var $this = $(this);
        var checked = $this.prop("checked");
        // console.log("onchange",this,$this.prop("checked"));
        $this.parent().next().find(":checkbox").prop("checked", checked);
        if (checked) {
            $this.parent().parent().parents("dd.tree-select-node").children("label.tree-select-node-label").children(":checkbox").prop("checked", checked);
        }
    });
});