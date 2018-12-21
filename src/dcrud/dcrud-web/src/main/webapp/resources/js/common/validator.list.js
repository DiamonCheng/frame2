(function () {
    window.validator.rules = {
        required: {
            test: function (value) {
                return value != null && value.trim().length > 0;
            }, message: window.lang.validator.rule.required
        },
        regex: {
            test: function (value, args) {
                if (args == null || args.length === 0) {
                    throw new Error("regex pattern is not found");
                }
                var regex = args[0];
                return new RegExp(regex).test(value);
            }, message: window.lang.validator.rule.regex
        },
        length: {
            test: function (value, args) {
                if (args == null || args.length < 2) {
                    throw new Error("length parameter is invalid");
                }
                return value != null && ("" + value).length >= args[0] && ("" + value).length <= args[1]
            }, message: window.lang.validator.rule.length
        }
    }
})();