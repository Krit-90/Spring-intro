package com.gleb.springintrodiction.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import java.util.Locale;

@Component
public final class MessageSourceUtil {

    public static ResourceBundleMessageSource messageSource;

    @Autowired
    private MessageSourceUtil() {
            this.messageSource = new ResourceBundleMessageSource();
            messageSource.setBasename("messages");
            messageSource.setDefaultEncoding("UTF-8");
    }

    public static String getMessage(String message, Locale locale, Object...arg){
        return messageSource.getMessage(message, arg, locale);
    }
}
