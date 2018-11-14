window.lang = $.extend(window.lang, {
    lang: 'en',
    validator: {
        rule: {
            required: "This field cannot be null.",
            regex: "Regex pattern {0} valid not passed  ",
            length: "Value length need be {0} to {1}"
        }
    },
    pageBar: {
        next: "Next Page",
        prev: "Last Page",
        first: "First Page",
        last: "Last Page",
        total1: "Total ",
        total2: " item(s)",
        itemPage: "/page",
        to: "To page",
        pageString: " ",
        submit: "GO"
    },
    option: {
        success: "Option Success.",
        failed: "Option Failed！",
        sessionTimeout: "Session Timeout. Please Login again",
        unknownError: "Error Occurred. Press F12 to see.",
        confirmTitle: 'Confirm',
        confirmOption: 'Are you sure to execute option {0} ?',
    }
});