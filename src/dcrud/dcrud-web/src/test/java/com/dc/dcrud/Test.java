package com.dc.dcrud;

import com.dc.frame2.view.Frame2View;

import java.util.ServiceLoader;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/12/4.
 */
public class Test {
    public static void main(String[] args) {
        ServiceLoader<Frame2View> serviceLoader = ServiceLoader.load(Frame2View.class);
        serviceLoader.forEach(System.out::println);
    }
}
