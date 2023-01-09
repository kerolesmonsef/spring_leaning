package com.example.qgame.services;

import com.example.qgame.Models.PasswordReset;
import com.example.qgame.helpers.Helper;
import com.example.qgame.helpers.dto.EmailDetails;
import com.example.qgame.jobs.SendEmailJob;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ForgetPasswordService {

    private final ResetPasswordService passwordResetService;
    private final ApplicationContext applicationContext;
    private final TaskExecutor taskExecutor;

    //    @Transactional
    public void forgetPassword(String email) {
        PasswordReset passwordReset = passwordResetService.create(email);

        String link = Helper.base_url() + "/password/reset?token=" + passwordReset.getToken() + "&email=" + email;

        String restPasswordMessage = "this mail for resetting your password \n" +
                "click on the link bellow to reset your password \n" +
                link;

        EmailDetails emailDetails = new EmailDetails(email, "Reset Password", restPasswordMessage, null);
        SendEmailJob sendEmailJob = applicationContext.getBean(SendEmailJob.class, emailDetails);

        taskExecutor.execute(sendEmailJob);

    }


}
