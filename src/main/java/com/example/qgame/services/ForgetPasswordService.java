package com.example.qgame.services;

import com.example.qgame.Models.PasswordReset;
import com.example.qgame.helpers.Helper;
import com.example.qgame.helpers.dto.EmailDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ForgetPasswordService {

    private final EmailService emailService;
    private final ResetPasswordService passwordResetService;

    //    @Transactional
    public void forgetPassword(String email) {
        PasswordReset passwordReset = passwordResetService.create(email);

        String link = Helper.base_url() + "/password/reset?token=" + passwordReset.getToken() + "&email=" + email;

        String restPasswordMessage = "this mail for resetting your password \n" +
                "click on the link bellow to reset your password \n" +
                link;

        EmailDetails emailDetails = new EmailDetails(email, "Reset Password", restPasswordMessage, null);
        EmailService.MailSenderStatus status = emailService.sendSimpleMail(emailDetails);

    }


}
