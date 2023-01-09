package com.example.qgame.controllers.user;

import com.example.qgame.Models.User;
import com.example.qgame.controllers.IController;
import com.example.qgame.repositories.UserRepository;
import com.example.qgame.services.ForgetPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ForgetPasswordController extends IController {

    private final UserRepository userRepository;
    private final ForgetPasswordService forgetPasswordService;

    @GetMapping("/password/forget")
    public String openForgetPasswordForm() throws Exception {
        return "/password/forgetPassword";
    }

    @GetMapping("/password/emailSentSuccessfullyThankYou")
    public String thankYou(Model model) {

        return "password/emailSentSuccessfullyThankYou";
    }

    @PostMapping("/password/forget")
    public ModelAndView forgetPassword(@RequestParam("email") String email, RedirectAttributes attributes) {
        if (email == null) {
            attributes.addFlashAttribute("alertError", "some errors occurs");
            return back();
        }

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            attributes.addFlashAttribute("alertError", "email not found");
            return back();
        }

        forgetPasswordService.forgetPassword(email);

        attributes.addFlashAttribute("alertSuccess", "Check Your Mail");

//        return back();

        return new ModelAndView("redirect:/password/emailSentSuccessfullyThankYou");
    }
}
