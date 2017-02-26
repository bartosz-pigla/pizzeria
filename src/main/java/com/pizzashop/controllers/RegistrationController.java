package com.pizzashop.controllers;

import com.pizzashop.models.Manager;
import com.pizzashop.repositories.ManagerRepository;
import com.pizzashop.services.AccountActivationEmailHeroku;
import com.pizzashop.services.AccountActivationEmailService;
import com.pizzashop.services.ActivationLink;
import com.pizzashop.services.ActivationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by bartek on 2/26/17.
 */
@RestController
public class RegistrationController {
    @Autowired
    ActivationService activationService;

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    @Qualifier(value = "APP_URL")
    String appUrl;

//    @Autowired
//    AccountActivationEmailService accountActivationEmailService;

    @Autowired
    AccountActivationEmailHeroku accountActivationEmailService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Manager user(@RequestBody @Validated({Manager.RegistrationValidation.class}) Manager manager) throws MessagingException {
        Manager notActivatedManager=managerRepository.save(manager);
        ActivationLink activationLink = activationService.createLink(notActivatedManager.getManagerId());

        accountActivationEmailService.send(manager.geteMail(), activationLink, appUrl+"activate");

        return manager;
    }
//
//    @RequestMapping(value = "/register/{id}/{number}", method = RequestMethod.POST)
//    public Manager activate(@RequestBody @Validated({Manager.RegistrationValidation.class}) Manager manager, HttpServletRequest request) throws MessagingException {
//
//        Manager notActivatedManager=managerRepository.save(manager);
//        ActivationLink activationLink = activationService.createLink(notActivatedManager.getManagerId());
//
//        accountActivationEmailService.send(manager.geteMail(), activationLink, "http://localhost:8080/#/register");
//
//        return manager;
//    }

    @RequestMapping(value = "/activate", method = RequestMethod.POST)
    public boolean activate(@RequestBody ActivationLink activationLink){
        Manager manager = managerRepository.findOne(activationLink.getId());
        if(activationService.canBeActivated(activationLink) && !manager.getActivated()){
            if(manager!=null){
                manager.setActivated(true);
                manager.setActivationDate(new Date());
                managerRepository.save(manager);
                return true;
            }
            else
                return false;
        }
        else if(manager.getActivated())
            return true;
        else
            return false;

    }
}
