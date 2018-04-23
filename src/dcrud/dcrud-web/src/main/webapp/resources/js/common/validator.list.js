(function () {
    window.validator.rules = {
        required: {
            test: function (value) {
                return value != null && value.trim().length > 0;
            }, message: window.lang.validator.rule.required
        }
    }
})();