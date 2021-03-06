package com.diakogiannis.uel.moviebook.controller;

import com.diakogiannis.uel.moviebook.enums.UrlBindingsEnum;
import com.diakogiannis.uel.moviebook.exceptions.UserExistsException;
import com.diakogiannis.uel.moviebook.model.dto.UsersDTO;
import com.diakogiannis.uel.moviebook.model.entity.users.Users;
import com.diakogiannis.uel.moviebook.model.mappers.UsersMapper;
import com.diakogiannis.uel.moviebook.model.misc.ModalInfo;
import com.diakogiannis.uel.moviebook.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UsersMapper usersMapper;
    @Autowired
    UserService userService;
    @Autowired
    HttpSession session;

    @GetMapping(path = "/principal")
    public Principal retrievePrincipal(Principal principal) {
        return principal;
    }

    @PostMapping(path = "/register")
    public String registerUser(Model model, @Valid UsersDTO usersDTO, BindingResult result) {
        if (result.hasErrors()) {
            return UrlBindingsEnum.USER_REGISTER_TEMPLATE.getValue();
        }
        try {
            Users user = usersMapper.toUsers(usersDTO);
            userService.registerUser(user);
            model.addAttribute("created", true);
        } catch (UserExistsException e) {
            model.addAttribute("modalInfo", new ModalInfo("Error!", "User Already Exists!"));
        }

        return UrlBindingsEnum.USER_REGISTER_TEMPLATE.getValue();
    }

    @GetMapping(path = "/register")
    public String registerUserPage(Model model) {
        model.addAttribute("usersDTO", new UsersDTO());
        return UrlBindingsEnum.USER_REGISTER_TEMPLATE.getValue();
    }

    @Secured("ROLE_USER")
    @GetMapping(path = "/process-login")
    public String processLogin(Principal principal) {
        session.setAttribute("userDetailsDTO", userService.getUserDetails(principal.getName()));
        return "redirect:/" + UrlBindingsEnum.MOVIES_HOME_URI.getValue();
    }

}
