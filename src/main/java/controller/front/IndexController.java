package controller.front;

import com.javaclimb.houserent.common.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController {
    /*前端首页*/
    @RequestMapping("/")
    public String index(Model model){
        return "front/index";

    }
}
