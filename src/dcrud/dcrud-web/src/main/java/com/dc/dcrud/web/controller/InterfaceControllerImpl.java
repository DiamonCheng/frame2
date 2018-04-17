package com.dc.dcrud.web.controller;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Descriptions...
 *
 * @author Defferson.Cheng
 * @date 2018/4/17.
 */
@RestController
public class InterfaceControllerImpl implements InterfaceController {
    @Override
    public String sayHiFromClientOne(String name) {
        return "My name is |"+name+"|";
    }
}
