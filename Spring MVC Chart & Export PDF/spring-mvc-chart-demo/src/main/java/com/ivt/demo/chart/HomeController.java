package com.ivt.demo.chart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by vhphat on 11/23/2016.
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {
    //http://www.highcharts.com/demo/line-basic
    @RequestMapping(method = GET)
    public String showBooks(Model model) {
        String tokyo = "7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6";
        String newYork = "-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5";
        String berlin = "-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0";
        String london = "3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8";
        model.addAttribute("tokyo", tokyo);
        model.addAttribute("newYork", newYork);
        model.addAttribute("berlin", berlin);
        model.addAttribute("london", london);

        return "home";
    }
}
