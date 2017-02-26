package com.pizzashop.controllers;

import com.pizzashop.models.Manager;
import com.pizzashop.repositories.ManagerRepository;
import com.pizzashop.services.ActivationLink;
import com.pizzashop.services.ActivationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Manager user(@RequestBody @Validated({Manager.RegistrationValidation.class}) Manager manager){
        Manager notActivatedManager=managerRepository.save(manager);
        activationService.createLink(notActivatedManager.getManagerId());
        return manager;
    }

    @RequestMapping(value = "/activate", method = RequestMethod.POST)
    public boolean activate(@RequestBody ActivationLink activationLink){
        if(activationService.canBeActivated(activationLink)){
            Manager manager = managerRepository.findOne(activationLink.getId());
            if(manager!=null){
                manager.setActivated(true);
                manager.setActivationDate(new Date());
                managerRepository.save(manager);
                return true;
            }
            else
                return false;
        }
        else
            return false;

    }
}
