package com.pizzashop.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Bartosz Pigla on 1/23/17.
 */
public class ImageUrlValidator implements ConstraintValidator<ImageUrl,String> {
    ImageUrl imageUrl;
    static List<String> acceptedImageExtensions= Arrays.asList("jpg","jpeg","png");


    @Override
    public void initialize(ImageUrl imageUrl) {
        this.imageUrl=imageUrl;
    }

    @Override
    public boolean isValid(String url, ConstraintValidatorContext constraintValidatorContext) {
        int fileTypeIdx=url.lastIndexOf('.');
        if(fileTypeIdx==-1)
            return false;
        else{
            String fileType=url.substring(fileTypeIdx+1,url.length());
            return acceptedImageExtensions.contains(fileType);
        }
    }
}
