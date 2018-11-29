package org.lxc.mall.sys.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used on @Controller Class's methods to demonstrate
 * roles and permissions that are needed to invoking according method
 * @author lxc
 *
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {
	
	String value() default "";
	
	String[] roles() default {};
}
