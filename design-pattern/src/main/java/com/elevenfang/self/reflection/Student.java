package com.elevenfang.self.reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Student {

	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	static @interface Lable {
		String value() default "";
	}

	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	static @interface Format {
		String pattern() default "yyyy-MM-dd HH:mm:ss";

		String timezone() default "GMT+8";
	}

	@Lable(value = "name")
	String name;

	@Lable(value = "birthday")
	@Format(pattern = "yyyy/MM/dd")
	Date birthday;

	public static String format(Object object) throws Exception {

		Class<?> clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();
		StringBuilder stringBuilder = new StringBuilder();
		for (Field field : fields) {
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}
			Lable lable = field.getAnnotation(Lable.class);
			String name = lable != null ? lable.value() : field.getName();
			Object value = field.get(object);
			if (value != null && field.getType() == Date.class) {
				value = format(field, value);
			}
			stringBuilder.append(name + ":" + value + System.lineSeparator());
		}

		return stringBuilder.toString();
	}

	private static Object format(Field field, Object value) {
		Format formater = field.getAnnotation(Format.class);
		if (formater != null) {
			String pattern = formater.pattern();
			String timezone = formater.timezone();
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			sdf.setTimeZone(TimeZone.getTimeZone(timezone));
			return sdf.format(value);
		}
		return value;
	}

	public Student(String name, Date birthday) {
		this.name = name;
		this.birthday = birthday;
	}

	public static void main(String[] args) throws Exception {
		Student student = new Student("nihao", new Date());
		System.out.println(Student.format(student));
	}

}
