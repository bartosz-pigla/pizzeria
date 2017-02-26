package com.pizzashop.services;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by bartek on 2/26/17.
 */

@Component
@Scope(value="singleton",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ActivationService {
    private Set<ActivationLink> activationLinks=new CopyOnWriteArraySet<>();

    private Random random=new Random();

    private int maxActivationLinkNumber=1000;

    private Set<Integer> numbersNotToRandom=new CopyOnWriteArraySet<>();

    private Timer deleteActivationLinks = new Timer();

    private int expiryTime=1000*60*60;//1 hour

    public ActivationService() {
        deleteActivationLinks.schedule(new TimerTask() {
            @Override
            public void run() {
                Date date=new Date();
                Set<ActivationLink> activationLinksToDelete=new CopyOnWriteArraySet<>();

                for (ActivationLink activationLink:activationLinks
                        ) {
                    long ms=(date.getTime() - activationLink.getCreationDate().getTime());
                    if(ms>expiryTime)
                        activationLinksToDelete.add(activationLink);
                }

                for(ActivationLink activationLink: activationLinksToDelete){
                    activationLinks.remove(activationLink);
                }
            }
        },0,expiryTime);
    }

    public void createLink(int id){
        activationLinks.add(new ActivationLink(id,randomNumber()));
    }

    public boolean canBeActivated(ActivationLink activationLinkToFind){
        return activationLinks.contains(activationLinkToFind);
    }

    private int randomNumber(){
        int number=0;

        if(numbersNotToRandom.size()>=maxActivationLinkNumber)
            maxActivationLinkNumber+=maxActivationLinkNumber;
        else{
            while(numbersNotToRandom.contains(number)){
                number = random.nextInt(maxActivationLinkNumber)+1;
            }
        }

        return number;
    }


}
