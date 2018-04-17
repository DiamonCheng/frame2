package com.dc.dcrud.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>Descriptions...
 *
 * @author Defferson.Cheng
 * @date 2018/4/17.
 */
@RequestMapping("/interface")
public interface InterfaceController {
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    @ResponseBody
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
}
