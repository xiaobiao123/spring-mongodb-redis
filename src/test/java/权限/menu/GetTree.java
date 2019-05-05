package 权限.menu;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetTree {
 
    // 菜单节点s
    private  static List<Element> list = new ArrayList<>();
 
    static {
        list.add( new Element("1",null,"基础信息管理","url1") );
        list.add( new Element("2","1","物料管理","url2") );
        list.add( new Element("5","1","报价管理","url3") );
        list.add( new Element("7",null,"一级菜单","url7") );
    }
 
    public List<Element> getTree() {
 
        List<Element> baseLists = new ArrayList<>();
        // 总菜单，出一级菜单，一级菜单没有父id
        for (Element e: list) {
            if( e.getPid()==null ){
                baseLists.add( e );
            }
        }
        // 遍历一级菜单
        for (Element e: baseLists) {
            // 将子元素 set进一级菜单里面
            e.setChilds( getChild(e.getId(),list) );
        }
 
        return baseLists;
    }
 
    /**
     * 获取子节点
     * @param pid
     * @param elements
     * @return
     */
    private List<Element> getChild(String pid , List<Element> elements){
        List<Element> childs = new ArrayList<>();
        for (Element e: elements) {
            if(e.getPid()!=null){
                if(e.getPid().equals( pid )){
                    // 子菜单的下级菜单
                    childs.add( e );
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (Element e: childs) {
            // 继续添加子元素
            e.setChilds( getChild( e.getId() , elements ) );
        }
 
        //停下来的条件，如果 没有子元素了，则停下来
        if( childs.size()==0 ){
            return null;
        }
        return childs;
    }

    public static void main(String[] args) {
        GetTree getTree=new GetTree();
        System.out.println(JSONObject.toJSONString(getTree.getTree()));
    }
 
}
