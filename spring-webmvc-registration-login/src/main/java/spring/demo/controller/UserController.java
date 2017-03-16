package spring.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.demo.entity.UserEntity;
import spring.demo.repository.UserRepository;
import spring.demo.service.UserService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by vhphat on 10/8/2016.
 */

@Controller
@RequestMapping(value = "/")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = GET)
    public String home() {
        return "home";
    }

    @RequestMapping(value = "sign-up", method = GET)
    public String showSignUpPage(Model model) {
        model.addAttribute("user", new UserEntity());
        return "sign-up";
    }

    @RequestMapping(value = "sign-up", method = POST)
    public String doRegistration(UserEntity user, Model model) {
        boolean isEmailExisting = userService.isEmailExisting(user.getEmail());
        if (isEmailExisting) {
            model.addAttribute("errorSignUp", "Email <" + user.getEmail() + "> already existing");
            user.setEmail(""); // reset email
            model.addAttribute("user", user); // keep entered data
            return "sign-up";
        }

        userService.doRegistration(user);
        model.addAttribute("message", "Thanks for your registration. Please go to your mailbox to activate your account.");
        model.addAttribute("cssBootstrap", "alert-success");
        return "home";
    }

    @RequestMapping(value = "sign-in", method = POST)
    public String doLogin(@RequestParam("form-username") String email,
                          @RequestParam("form-password") String password,
                          Model model) {
        int result = userService.doLogin(email, password);

        model.addAttribute("user", new UserEntity());
        if (result == 0) {
            model.addAttribute("errorSignIn", "Login failed. Incorrect user name or password");
            return "sign-up";
        } else {
            if (result == 1) {
                model.addAttribute("errorSignIn", "Login failed. You account not activated yet");
                return "sign-up";
            } else if (result == 2) {
                model.addAttribute("message", "Welcome " + email);
                model.addAttribute("cssBootstrap", "alert-success");
            }
        }

        return "home";
    }

    @RequestMapping(value = "activateAccount", method = GET)
    public String activateAccount(@RequestParam(name = "email") String email,
                                  @RequestParam(name = "code") String code,
                                  Model model) {
        int result = userRepository.activateUser(email, code);
        if (result == 1) {
            model.addAttribute("message", "Your account has ben activated. Now, you can login. Thank you.");
            model.addAttribute("cssBootstrap", "alert-success");
        } else {
            model.addAttribute("message", "Your activation code is not correct.");
            model.addAttribute("cssBootstrap", "alert-danger");
        }
        return "home";
    }
}
