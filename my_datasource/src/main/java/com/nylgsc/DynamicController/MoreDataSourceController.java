package com.nylgsc.DynamicController;

import com.nylgsc.domain.User;
import com.nylgsc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/dynamic")
public class MoreDataSourceController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/save/{name}")
    public void save(@PathVariable(value = "name") String name) {
        userService.save(name);
    }

    @GetMapping(value = "find")
    public List<User> find() {
        return userService.find();
    }
}
