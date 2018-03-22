package com.example.reretrofit2.retrofit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by XJ on 2018/3/17 0017.
 */

@Retention(RetentionPolicy.RUNTIME)

@Target(ElementType.PARAMETER)

public @interface Path {

    String value();

}
