package com.dc.dcrud.api.provider;

import com.dc.dcrud.api.model.UserRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2019/4/16.
 */
@FeignClient("dcrud")
public interface UserServiceProvider {
    @RequestMapping("/service/dcrud/user/")
    UserRO getUserById(@RequestParam("id") long id);
    @RequestMapping("/service/dcrud/user/list")
    List<UserRO> listUser();
}
