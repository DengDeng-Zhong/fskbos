package gq.dengbo.bos.web.action;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String test1(){
        System.out.println(name);
        return SUCCESS;
    }
}
