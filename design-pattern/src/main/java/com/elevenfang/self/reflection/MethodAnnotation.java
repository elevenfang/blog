package com.elevenfang.self.reflection;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

public class MethodAnnotation {
	@Inherited
	@Target(ElementType.PARAMETER)
	@Retention(RetentionPolicy.RUNTIME)
	static @interface QueryParam {
		String value();
	}

	@Target(ElementType.PARAMETER)
	@Retention(RetentionPolicy.RUNTIME)
	static @interface DefaultValue {
		String value() default "";
	}

	public void hello(@QueryParam("action") String action, @QueryParam("sort") @DefaultValue("asc") String sort) {

	}

	public static void main(String[] args) throws Exception {
		Class<?> clazz = MethodAnnotation.class;
		Method method = clazz.getMethod("hello", new Class[] { String.class, String.class });
		Annotation[][] annotations = method.getParameterAnnotations();
		for (int i = 0; i < annotations.length; i++) {
			System.out.println("annotations for parameter " + (i + 1));
			Annotation[] annotations2 = annotations[i];
			for (Annotation annotation : annotations2) {
				if (annotation instanceof QueryParam) {
					QueryParam queryParam = (QueryParam) annotation;
					System.out.println(queryParam.annotationType().getSimpleName() + ":" + queryParam.value());
				} else if (annotation instanceof DefaultValue) {
					DefaultValue defaultValue = (DefaultValue) annotation;
					System.out.println(defaultValue.annotationType().getSimpleName() + ":" + defaultValue.value());
				}
			}
		}
	}
}
