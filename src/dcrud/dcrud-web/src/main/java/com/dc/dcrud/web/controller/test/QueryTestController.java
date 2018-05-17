package com.dc.dcrud.web.controller.test;

import com.dc.dcrud.web.view.DefaultDataTableView;
import com.dc.dcrud.web.view.DefaultOptionButtonView;
import com.dc.dcrud.web.view.query.*;
import com.dc.frame2.view.view.freemarker.form.FormView;
import com.dc.frame2.view.view.freemarker.form.Option;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/5/11.
 */
@Controller
@RequestMapping("/query")
public class QueryTestController {
    
    @RequestMapping("/")
    public Object page() {
        return new FormView()
                       .addContent(
                               new QueryPanelView().addCondition(
                                       new InputCondition()
                                               .setId("inputView")
                                               .setLabel("crud.query.test.inputView.label")
                                               .setName("inputView")
                                               .setPlaceHolder("crud.query.test.inputView.label")
                               ).addCondition(
                                       new DatePickerCondition()
                                               .addOption("max", "2018-12-12")
                                               .addOption("min", "2018-01-01")
                                               .setId("dataPickerView")
                                               .setLabel("crud.query.test.dataPickerView.label")
                                               .setName("dataPickerView")
                               ).addCondition(
                                       new DateRangePickerCondition()
                                               .addOption("max", "2018-12-12")
                                               .addOption("min", "2018-01-01")
                                               .setStartName("xxxxStart")
                                               .setEndName("xxxxEnd")
                                               .setStartValue("2018-03-04")
                                               .setEndValue("2018-03-16")
                                               .setLabel("crud.query.test.dataRangeView.label")
                               ).addCondition(
                                       new SelectCondition()
                                               .setLabel("crud.query.test.selectView.label")
                                               .setName("selectView")
                                               .addOption(new Option().setText("crud.query.test.selectView.option.text.1").setValue("1"))
                                               .addOption(new Option().setText("crud.query.test.selectView.option.text.2").setValue("2"))
                                               .addOption(new Option().setText("crud.query.test.selectView.option.text.3").setValue("3"))
                                               .addOption(new Option().setText("crud.query.test.selectView.option.text.3").setValue("3"))
                                               .addOption(new Option().setText("crud.query.test.selectView.option.text.3").setValue("3"))
                                               .addOption(new Option().setText("crud.query.test.selectView.option.text.3").setValue("3"))
                                               .addOption(new Option().setText("crud.query.test.selectView.option.text.3").setValue("3"))
                                               .addOption(new Option().setText("crud.query.test.selectView.option.text.3").setValue("3"))
                                               .addOption(new Option().setText("crud.query.test.selectView.option.text.3").setValue("3"))
                                               .addOption(new Option().setText("crud.query.test.selectView.option.text.3").setValue("3"))
                                               .addOption(new Option().setText("crud.query.test.selectView.option.text.3").setValue("3"))
                                               .addOption(new Option().setText("crud.query.test.selectView.option.text.3").setValue("3"))
                                               .addOption(new Option().setText("crud.query.test.selectView.option.text.3").setValue("3"))
                                               .addOption(new Option().setText("crud.query.test.selectView.option.text.3").setValue("3"))
                                               .addOption(new Option().setText("crud.query.test.selectView.option.text.4").setValue("4"))
                               ).addCondition(
                                       new CheckboxCondition()
                                               .setValues(Arrays.asList("1", "2"))
                                               .addOption(new Option().setText("crud.query.test.checkboxView.option.text.1").setValue("1"))
                                               .addOption(new Option().setText("crud.query.test.checkboxView.option.text.2").setValue("2"))
                                               .addOption(new Option().setText("crud.query.test.checkboxView.option.text.3").setValue("3"))
                                               .setLabel("crud.query.test.checkboxView.label")
                               ).setTitle("crud.query.test.title")
                       ).addContent(
                        new DefaultOptionButtonView()
                ).addContent(
                        new DefaultDataTableView()
                );
    }
}
