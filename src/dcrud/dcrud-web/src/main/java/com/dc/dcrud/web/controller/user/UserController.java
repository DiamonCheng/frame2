package com.dc.dcrud.web.controller.user;

import com.dc.dcrud.domain.RoleEntity;
import com.dc.dcrud.domain.UserEntity;
import com.dc.dcrud.searcher.UserSearcher;
import com.dc.dcrud.service.rbac.UserService;
import com.dc.dcrud.web.view.data.DefaultDataTableView;
import com.dc.dcrud.web.view.data.DefaultTableHeadView;
import com.dc.dcrud.web.view.data.OptionTableHeadView;
import com.dc.dcrud.web.view.data.TableOptionButton;
import com.dc.dcrud.web.view.option.DefaultOptionButtonView;
import com.dc.dcrud.web.view.option.OptionButton;
import com.dc.dcrud.web.view.support.QueryPageViewFactory;
import com.dc.frame2.view.view.freemarker.form.FormView;
import com.dc.frame2.view.view.freemarker.page.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/9/17.
 */
@Controller
@RequestMapping("/dcrud/user")
public class UserController {
    
    private QueryPageViewFactory queryPageViewFactory = new QueryPageViewFactory();
    
    @Autowired
    private UserService userService;
    
    @RequestMapping("/")
    public Object searchPage(UserSearcher searcher) {
        userService.searchPage(searcher);
        PageView pageView = (PageView) queryPageViewFactory.createPageQueryView(searcher);
        FormView formView = (FormView) pageView.getComponents().get(0);
        formView.addContent(
                new DefaultOptionButtonView()
                        .addButton(
                                new OptionButton()
                                        .setName("crud.query.test.button1.text")
                                        .setTitle("crud.query.test.button1.text")
                                        .setType(OptionButton.Type.DEFAULT)
                        )
                        .addButton(
                                new OptionButton()
                                        .setName("crud.query.test.button2.text")
                                        .setTitle("crud.query.test.button2.text")
                                        .setType(OptionButton.Type.PRIMARY)
                        )
                        .addButton(
                                new OptionButton()
                                        .setName("crud.query.test.button3.text")
                                        .setTitle("crud.query.test.button3.text")
                                        .setType(OptionButton.Type.DANGER)
                        )
        
        ).addContent(
                new DefaultDataTableView()
                        .setPageParameters(3, 10, 233)
                        .setData(Arrays.asList(
                                new UserEntity()
                                        .setNickName("nickName1")
                                        .setUsername("username1")
                                        .setCreateDateTime(new Date())
                                        .setUpdateDateTime(new Date())
                                        .setId(123465L),
                                new UserEntity()
                                        .setNickName("nickName2")
                                        .setUsername("username2")
                                        .setCreateDateTime(new Date())
                                        .setUpdateDateTime(new Date())
                                        .setId(223465L),
                                new UserEntity()
                                        .setNickName("nickName3")
                                        .setUsername("username3")
                                        .setCreateDateTime(new Date())
                                        .setUpdateDateTime(new Date())
                                        .setId(323465L),
                                new UserEntity()
                                        .setNickName("nickName4")
                                        .setUsername("username4")
                                        .setRoles(Collections.singleton(new RoleEntity().setName("admin").setCode("admin")))
                                        .setCreateDateTime(new Date())
                                        .setUpdateDateTime(new Date())
                                        .setId(423465L)
                        )).addTableHeadView(
                        new DefaultTableHeadView()
                                .setFieldName("username")
                                .setName("crud.query.test.dataTable.head.username")
                                .setSortable(true)
                                .setTextAlign(DefaultTableHeadView.TEXT_ALIGN_RIGHT)
                                .setSortFieldName("username")
                                .setSortOrder(1)
                ).addTableHeadView(
                        new DefaultTableHeadView()
                                .setFieldName("nickName")
                                .setName("crud.query.test.dataTable.head.nickName")
                                .setSortable(true)
                                .setSortFieldName("nickName")
                                .setSort("DESC")
                                .setSortOrder(5)
                ).addTableHeadView(
                        new DefaultTableHeadView()
                                .setFieldName("createDateTime")
                                .setName("crud.query.test.dataTable.head.createTime")
                ).addTableHeadView(
                        new DefaultTableHeadView()
                                .setFieldName("roles")
                                .setName("crud.query.test.dataTable.head.roles")
                ).addTableHeadView(
                        new DefaultTableHeadView()
                                .setFieldName("updateDateTime")
                                .setName("crud.query.test.dataTable.head.updateTime")
                                .setSortable(true)
                                .setSort("DESC")
                                .setSortOrder(4)
                ).addTableHeadView(
                        new OptionTableHeadView().setName("crud.query.table.option.head")
                                .addButton((TableOptionButton)
                                                   new TableOptionButton()
                                                           .setTableOptionButtonFilter((dataList, data, index) -> {
                                                               UserEntity userEntity = (UserEntity) data;
                                                               return userEntity.getRoles() != null && userEntity.getRoles().stream().anyMatch(role -> "admin".equals(role.getCode()));
                                                           }).setName("crud.query.table.option.update")
                                )
                                .addButton((TableOptionButton)
                                                   new TableOptionButton()
                                                           .setName("crud.query.table.option.delete")
                                )
                )
        );
        return pageView;
    }


}
