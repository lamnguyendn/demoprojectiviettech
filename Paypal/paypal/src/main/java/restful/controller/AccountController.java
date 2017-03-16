package restful.controller;

import com.sun.org.apache.xerces.internal.util.URI;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import restful.entity.AccountEntity;
import restful.repository.AccountRepository;

import javax.xml.ws.Response;
import java.util.List;

/**
 * Created by TungNguyen on 8/12/16.
 */

@Controller
@RequestMapping(value = "/")
public class AccountController {
    @Autowired
    AccountRepository accountRepository;

    @RequestMapping (method = RequestMethod.GET)
    public String test (){
        return "/index";

    }

    @RequestMapping(value = "/paypal/success", method = RequestMethod.GET)
    public String success (@RequestParam (name = "amt") String amt, Model model){
            model.addAttribute("amt",amt);
            return "/success";

    }





}
