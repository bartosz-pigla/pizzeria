package com.pizzashop.services;

/**
 * Created by bartek on 2/26/17.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Component
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AccountActivationEmailHeroku {
    @Autowired
    private JavaMailSender javaMailSender;

    private String eMailContent="<div>\n" +
            "    <h1>Aktywacja konta: </h1>\n" +
            "    <a id=\"activationLink\" href=\"\">Aktywuj konto</a>\n" +
            "</div>";


    private String getUrl(ActivationLink activationLink,String requestUri){
        StringBuilder stringBuilder = new StringBuilder(requestUri+"/");
        return stringBuilder
                .append(activationLink.getId())
                .append('/')
                .append(activationLink.getNumber())
                .toString();
    }

    public String appendUrl(String url){
        String linkElement="id=\"activationLink\" href=\"\"";
        int elementPosition = eMailContent.indexOf(linkElement);
        int pointcut=elementPosition+linkElement.length()-1;

        StringBuilder stringBuilder=new StringBuilder(eMailContent.substring(0,pointcut))
                .append(url)
                .append('"')
                .append(eMailContent.substring(pointcut+1));

        return stringBuilder.toString();
    }

    public void send(String eMailDeliveryAddess, ActivationLink activationLink, String requestUri) throws MessagingException {
        String url=getUrl(activationLink, requestUri);

        String eMailContent= appendUrl(url);

        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;

        helper = new MimeMessageHelper(mail, true);
        helper.setTo(eMailDeliveryAddess);
        helper.setSubject("Aktywacja konta");
        helper.setText(eMailContent, true);
        javaMailSender.send(mail);

    }
}
