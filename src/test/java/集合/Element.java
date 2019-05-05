package 集合;

import lombok.Data;

import java.util.List;
@Data
public class Element {
    private String id;
    private String pid;
    private String name;
 
    private List<Element> childs;


}