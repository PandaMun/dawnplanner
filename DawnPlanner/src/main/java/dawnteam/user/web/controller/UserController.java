package dawnteam.user.web.controller;

import dawnteam.user.service.UserService;
import dawnteam.user.service.dvo.UserInfoDto;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.ModelMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public String signup(UserInfoDto infoDto){ //회원 추가
        userService.save(infoDto);
        return "redirect:/login";
    }

    @GetMapping(value = "/error")
    public String error(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
        // modelmap에 error code 설정
        modelmap.addAttribute("Data", "error code : " + res.getStatus());
        // view의 파일명
        return "redirect:/error";
    }
    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {// 로그아웃 페이지
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }
}