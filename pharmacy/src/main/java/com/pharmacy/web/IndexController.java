package com.pharmacy.web;

import com.pharmacy.service.api.ImportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;

/**
 * Created by Alexander on 09.11.2015.
 */
@Controller
public class IndexController {

    @Inject
    private ImportService importService;

    @RequestMapping("/")
    public String index(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }


    @RequestMapping("/importtest")
    public void importTest(Model model) {
        Assert.notNull (model);
        importService.importCSVFile();
    }

}
