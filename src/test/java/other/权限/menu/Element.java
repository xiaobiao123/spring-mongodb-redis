package other.权限.menu;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
public class Element {
    private String id;
    private String pid;
    private String name;
    private String url;
    private String type ="menu";// 菜单，按钮
 
    private List<Element> childs;

    public Element(String id,String pid,String name,String url){
        this.id=id;
        this.pid=pid;
        this.name=name;
        this.url=url;
    }

    @RequestMapping
    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Element> getChilds() {
        return childs;
    }

    public void setChilds(List<Element> childs) {
        this.childs = childs;
    }
}