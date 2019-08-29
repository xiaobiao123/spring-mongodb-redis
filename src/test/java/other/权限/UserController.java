package other.权限;

import cn.springmvc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping("addUser")
   // @RightDefintion(menu=["menu_token1","menu_token2"] , right=["tight_token1","tight_token2"])
    @ResponseBody
    public String addUser(User user) {
     return null;
    }



}
