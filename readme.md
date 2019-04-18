# Frame2 CRUD Framework
>> 只有一颗心表示很悲伤~
>> 2019年4月18日16:19:31 修改 新增2.0-SNAPSHOT说明

# BUG还有很多，欢迎指正

## Guide
* 代码位置 src/frame2 src/dcrud
* 数据库初试数据位置 doc\init_sql\init_data.sql
* 登录用户 DC/101010
* 创建用户之后初始密码 233333

## 定义
针对管理端的，非单页面的，支持分布式的，支持组件化插拔的，前台/后台编码框架。

## 基于
* spring-boot
* spring-data-jpa
* hibernate
* thymeleaf
* sitemesh3
* shiro
* spring-cloud-open-feign
* consul
* ....

## 代码结构

* dcrud: 业务层代码模板,应当自带一套rbac的功能模板。
    * dcrud-api  <---- 对外接口和模型，不含实现
    * dcrud-web <---- model/dao/service/controller...
    * dcrud-web-support <---- frame2-views -- 对frame2-views增强，并适配前端视图框架 layui，框架性代码
* frame2: 核心代码，包含 1. 对spring-data-jpa增强组件 2. 自定义模块化的视图渲染逻辑
    * frame2 -- 一共没多少代码，全部仍一起，注意没有多少外置依赖，使用时注意ClassNotFound

# 特性
1. 前后台代码都有
2. 自带一套简单RBAC
3. 定义了一套（虽然挺复杂但是貌似还挺好用）视图组件
4. 可以编写子系统UI并通过依赖引入实现模块化 
5. 这个模块化可以是本地单实例运行也可以通过RPC调用接口实行

# 详细特性
## 1. 这是一套自带增删改查，前端UI，RBAC系统，可以进行soa拓展的后台管理框架 --划掉

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
```java
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
```java
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
```html
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
```java
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

```java
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

## 模块组件化说明
### 不组件化
下载源码，直接在源码里面增加 maven 模块编写业务。

### 单例集成
比如 要做一个模块化的 交易系统 payment

* dcrud: 
    * dcrud-api  
    * dcrud-web 
     - 依赖 dcurd-api
     - 依赖 dcrud-web-support
     - 依赖 payment
    * dcrud-web-support 
* payment 
  - 依赖 dcurd-api 用于对接用户/菜单等数据接口
  - 依赖 dcrud-web-support 视图层支持
  * model
  * dao 数据库共用的一个，如果不使用jpa，需要自行配置~还挺复杂
  * service
  * controller

  视图可以参考 dcrud-web 中的代码使用视图框架生成。  
  也可以直接使用thymeleaf模板，需要注意的是 视图html/ftl位置在 classpath:/templates/... **放在类路径下~**，静态资源放 resource js/css/img/... 下。 公共的js资源 比如jquery自带不用引入

注： 要关掉feign/consul

### 分布式集成
比如 要做一个模块化的 交易系统 payment  
注册中心使用consul，需要配置一个consul，如果不使用consul自行替换依赖和配置  
RPC 使用 Feign  
* dcrud: 
    * dcrud-api  
    * (运行实例1)dcrud-web 
      - 依赖 dcurd-api
      - 依赖 dcrud-web-support
      - 依赖 payment-api 
    * dcrud-web-support 
* payment 
  * payment-api
    - 依赖 dcrud-web-support 视图层支持
    - 依赖 dcurd-api 用于对接用户/菜单等数据接口
    * ro model ro->RPC Object，是provider里面参数和返回类型模型。直接作为数据库实体好像也米有什么问题，但是不建议把内部实体直接暴露在对外接口里。 直接可以当做controller VO使用（上面打视图定义注解），一定要区分出VO也咩有问题
    * provider RPC**接口**，使用feign格式定义，注意@GetMapping @PostMapping这种注解的坑
    * controller 控制层直接会在 dcrud-web实例中运行， 在里面注入 provider，使用RO，返回视图。 可以在 provider/ro 和 controller 之间 加一层 consumer/vo，如果不嫌烦的化。
    * views classpath:/templates/... **放在类路径下~**，静态资源放 resource js/css/img/... 下。 公共的js资源 比如jquery自带不用引入
  * (运行实例2)payment-impl 只要使用feign接口对的上不关心内部使用的什么数据库以及什么持久层框架
    - 依赖 payment-api
    * domain
    * dao
    * service
    * provider-impl 实现 payment-api中的provider接口，使用feign暴露

有个案例 BUG颇多的考试[https://gitee.com/DiamonCheng/syspapertest](https://gitee.com/DiamonCheng/syspapertest)