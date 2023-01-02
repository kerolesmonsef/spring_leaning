package com.example.qgame.controllers.user;

import com.example.qgame.controllers.IController;
import com.example.qgame.helpers.ResetPasswordStatus;
import com.example.qgame.repositories.PasswordResetRepository;
import com.example.qgame.requests.ResetPasswordRequest;
import com.example.qgame.services.ResetPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class ResetPasswordController extends IController {

    private final PasswordResetRepository passwordResetRepository;
    private final ResetPasswordService resetPasswordService;

    @GetMapping("/password/reset")
    public String openResetPasswordForm(HttpServletRequest request, Model model) {
        model.addAttribute("email", request.getParameter("email"));
        model.addAttribute("token", request.getParameter("token"));

        return "/password/resetPassword";
    }

    @GetMapping("password/passwordUpdatedSuccessfullyThankYou")
    public String passwordUpdatedSuccessfullyThankYou() {
        return "/password/passwordUpdatedSuccessfullyThankYou";
    }


    @PostMapping("/password/reset")
    public ModelAndView resetPassword(@Valid ResetPasswordRequest request, BindingResult bindingResult, RedirectAttributes attributes) {

        ResetPasswordStatus passwordStatus = resetPasswordService.resetPassword(request, bindingResult);

        if (passwordStatus.isFail()) {
            attributes.addFlashAttribute("alertError", passwordStatus.getMessage());
            return back();
        }

        attributes.addFlashAttribute("alertSuccess", passwordStatus.getMessage());

        return new ModelAndView("redirect:/password/passwordUpdatedSuccessfullyThankYou");
    }
}
