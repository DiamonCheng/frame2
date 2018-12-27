# frame2 crud framework
## Guide
* 代码位置 src/frame2 src/dcrud
* 数据库初试数据位置 doc\init_sql\init_data.sql
* 登录用户 DC/101010
* 创建用户之后初始密码 233333
## 定义
JavaEE 页面编码框架。

## 基于
* spring-boot
* spring-data-jpa
* hibernate
* thymeleaf
* sitemesh3
* ....

## 代码编写目标
简化页面CRUD编码，并基于原frame框架新增分布式的支持。


##代码结构

* dcrud: 业务层代码模板,应当自带一套rbac的功能模板。
    * dcrud-model  <---- frame2-model -- 业务代码
    * dcrud-dao   <---- frame2-data-jpa ,  dcrud-model -- 业务代码
    * dcrud-service <---- dcrud-dao --业务代码
    * dcrud-web <---- dcrud-service,dcrud-web-support --业务代码
    * dcrud-web-support <---- frame2-views -- 对frame2-views增强，并适配前端视图框架 layui，框架性代码
* frame2: 核心代码，包含 1. 对spring-data-jpa增强组件 2. 自定义模块化的视图渲染逻辑
    * frame2-common -- 工具类
    * frame2-model
    * frame2-data-jpa  <--- frame2-model
    * frame2-views -- 核心 提供Freemarker嵌套视图解析，核心类为Frame2View，FreemarkerView


#特性
## 1. 这是一套自带增删改查，前端UI，RBAC系统，可以进行soa拓展的后台管理框架

## 2. Spring Data JPA 支持
对于原生 Spring Data JPA 进行完整支持并进行拓展，可以支持基于类注解解析查询条件，可以支持到括号嵌套, 可以支持自带排序的分页查询。

* 相关模块 
	1. frame2-model: com.dc.frame2.core.dao.condition.* 
	2. frame2-data-jpa: com.dc.frame2.core.dao.*

## 3. 对于Thymeleaf和Freemarker原生支持，可以支持传统SpringMVC式网页开发
src/main/java/.../controller
src/main/webapp/templates/.../view

## 4. 封装了一套Frame2视图层，基于Freemarker开发， 特点为模块化嵌套化渲染。
可以这样返回一个视图
```
@Controller
@RequestMapping("/query")
public class QueryTestController {
    @RequestMapping("/")
    public Object page(UserSearcher searcher) {
        return new FormView()
                       .setId("pageForm")
                       .addContent(
                               new QueryPanelView().addCondition(
                                       new TextInput()
                                               .setId("inputView")
                                               .setLabel("crud.query.test.inputView.label")
                                               .setName("inputView")
                                               .setPlaceHolder("crud.query.test.inputView.label")
                               ).addCondition(
                                       new DatePickerInput()
                                               .addOption("max", "2018-12-12")
                                               .addOption("min", "2018-01-01")
                                               .setId("dataPickerView")
                                               .setLabel("crud.query.test.dataPickerView.label")
                                               .setName("dataPickerView")
                               ).addCondition(...)
                       );
    }	
}	
```
以下是一个视图的编写方式
```
public class QueryPanelView implements FreemarkerView {
    private static final String TEMPLATE_NAME = "/common/crud/query/conditions.html.ftl";
    
    private List<ConditionView> conditions = new ArrayList<>(3);
    private String title = "";
    
    public List<ConditionView> getConditions() {
        return conditions;
    }
    
    public QueryPanelView addCondition(ConditionView condition) {
        conditions.add(condition);
        return this;
    }
    
    public QueryPanelView setTitle(String title) {
        this.title = title;
        return this;
    }
    
    public String getTitle() {
        return title;
    }
    
    @Override
    public String getTemplateName() {
        return TEMPLATE_NAME;
    }
    
    @Override
    public Map<String, Object> getParam() {
        return MapBuilder.dataMap()
                       .put("title", title)
                       .put("conditions", conditions)
                       .build();
    }
}
```
/common/crud/query/conditions.html.ftl 
```
<section class="layui-collapse layui-card query-panel">
    <div class="layui-colla-item">
        <div class="layui-colla-title query-panel-head"><@spring.message title/></div>
        <div class="layui-colla-content query-panel-conditions layui-anim-fadein layui-anim layui-show">
            <div class=" layui-row layui-col-space10">
                <#list conditions as condition>
                    ${RENDER(condition)}
                </#list>
                <div class="layui-col-md12 layui-col-xs12 layui-col-lg12">
                    <div class="layui-form-item layui-re query-panel-bottom">
                        <input type="submit" class="layui-btn query-btn"
                               value="<@spring.message 'crud.query.condition.button.query'/>"/>
                        <input type="reset" class="layui-btn layui-btn-primary reset-btn"
                               onclick="window.location.replace('?')"
                               value="<@spring.message 'crud.query.condition.button.reset'/>"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
```

视图层经过了模块化，并和Java类绑定，这样就能很方便的对视图层进行组件化和封装。

经过了很久的努力，现在可以通过注解来配置一个带条件、分页、排序的查询页面以及编辑页面。

例： 
```
@Controller
@RequestMapping("/dcrud/user")
public class UserController {
    
    private QueryPageViewFactory queryPageViewFactory = new QueryPageViewFactory();
    
    private EditViewFactory editViewFactory = new EditViewFactory();
    
    @Autowired
    private UserService userService;
    
    @RequestMapping("/")
    public Object searchPage(UserSearcher searcher) {
        userService.searchPage(searcher);
        PageView pageView = (PageView) queryPageViewFactory.createPageQueryView(searcher);
        FormView formView = (FormView) pageView.getComponents().get(0);
        return pageView;
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Object add() {
        return editViewFactory.generateAddView(new UserEntity());
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public Object edit(Long id) {
        UserEntity userEntity = userService.get(id);
        
        return editViewFactory.generateEditView(userEntity);
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(UserEntity userEntity) {
        //throw new IllegalStateException(" exception alert test");
        return new AjaxResult();
    }
    
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(long id) {
        return new AjaxResult();
    }

}
```
```
@OptionButtons({
        @OptionButton(name = "crud.query.option.add", href = "add"),
})
@DataTableConfig(
        columns = {
                @TableColumnConfig(
                        path = "username", headName = "com.dc.dcrud.domain.UserEntity.username"
                ),
                @TableColumnConfig(
                        path = "nickName", headName = "com.dc.dcrud.domain.UserEntity.nickName"
                ),
                @TableColumnConfig(
                        path = "roles", headName = "com.dc.dcrud.domain.UserEntity.roles"
                ),
                @TableColumnConfig(
                        path = "createDateTime", headName = "com.dc.dcrud.domain.createDateTime"
                ),
                @TableColumnConfig(
                        path = "updateDateTime", headName = "com.dc.dcrud.domain.updateDateTime"
                ),
        }, buttons = {
        @TableOptionButtonConfig(name = "crud.query.table.option.update", href = "edit"),
        @TableOptionButtonConfig(name = "crud.query.table.option.delete", href = "delete",
                ajax = true,
                ajaxConfirm = true)
})
@ConditionViewTitle("crud.userEntity.query.condition.title")
public class UserSearcher extends PageSearcher<UserEntity> {
    @TextInput
    @Condition(operator = CompareOperator.DUP_LIKE)
    private String nickName;
    @Condition(operator = CompareOperator.DUP_LIKE)
    @TextInput
    private String username;
    
    
    public String getUsername() {
        return username;
    }
    
    public UserSearcher setUsername(String username) {
        this.username = username;
        return this;
    }
    
    public String getNickName() {
        return nickName;
    }
    
    public UserSearcher setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }
}
```

相关资源： com.dc.dcrud.web.controller.user.UserController
