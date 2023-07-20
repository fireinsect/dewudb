package com.youkeda.dewu.service;

import com.youkeda.dewu.model.Result;
import com.youkeda.dewu.model.User;
import javax.servlet.http.HttpServletRequest;

public interface UserService {

    /**
     * 注册用户
     *
     * @param userName
     * @param pwd
     * @return
     */
    public Result<User> register(String userName, String pwd);

    /**
     * 执行登录逻辑，登录成功返回 User 对象
     *
     * @param userName
     * @param pwd
     * @return
     */
    public Result<User> login(String userName, String pwd);
    
    /**
     * 判断是否登录
     *
     * @param request
     * @return
     */
    public Boolean checkLogin(HttpServletRequest request);

}
