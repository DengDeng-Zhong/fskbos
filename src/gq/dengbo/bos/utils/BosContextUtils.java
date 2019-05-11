package gq.dengbo.bos.utils;

import gq.dengbo.bos.model.User;

import static org.apache.struts2.ServletActionContext.getRequest;

public class BosContextUtils {
    public static User loginUser(){
        return (User) getRequest().getSession().getAttribute("loginUser");
    }
}
