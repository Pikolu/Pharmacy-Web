package com.pharmacy.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by apopow on 27.12.2015.
 */
@Controller
public class RegistrationController {

    private static final Logger LOG = LoggerFactory.getLogger(RegistrationController.class);

    private static final String REGISTRATION = "registration";
    @Autowired
    private UserValidator validator;
    @Autowired
    private UserService userService;
    private ModelAndView modelAndView;
//    @Autowired
//    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registration(@ModelAttribute("command") EndUser user, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("Enter registration: user={}, result={}", user, result);
        try {
            validator.validate(user, result, false);
            if (result.hasErrors()) {
                if (modelAndView == null) {
                    modelAndView = new ModelAndView("redirect:registration.html", "command", new EndUser());
                }
                modelAndView.getModel().putAll(result.getModel());
            } else {
                modelAndView = new ModelAndView("redirect:welcome.html", "command", user);
                userService.save(user);
                authenticateUserAndSetSession(user.getAccount(), request);
            }
        } catch (ServiceException ex) {
            ex.writeLog(LOG);
        }
        LOG.trace("Exit registration: modelAndView={}", modelAndView);
        return modelAndView;
    }

    private void authenticateUserAndSetSession(Account account, HttpServletRequest request) {
        LOG.trace("Enter authenticateUserAndSetSession: account={}", account);
        String username = account.getEmail();
        String password = account.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        // generate session if one doesn't exist
        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));
//        Authentication authenticatedUser = authenticationManager.authenticate(token);

//        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
        LOG.trace("Exit authenticateUserAndSetSession");
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView initRegistrition() {
        LOG.trace("Enter showContacts");
        modelAndView = new ModelAndView(REGISTRATION, "command", new EndUser());
        LOG.trace("Exit initRegistrition: modelAndView={}", modelAndView);
        return modelAndView;
    }
}
