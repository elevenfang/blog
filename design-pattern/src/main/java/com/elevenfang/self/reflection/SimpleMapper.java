package com.elevenfang.self.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class SimpleMapper {
	public static String toString(Object object) throws Exception {
		Class<?> clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();
		StringBuilder sb = new StringBuilder();
		sb.append(clazz.getName() + System.lineSeparator());
		for (Field field : fields) {
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}
			String value = field.get(object).toString();
			String name = field.getName();
			sb.append(name + "=" + value + System.lineSeparator());
		}

		return sb.toString();
	}

	public static Object fromString(String str) throws Exception {
		String[] lines = StringUtils.split(str, System.lineSeparator());
		if (ArrayUtils.isEmpty(lines)) {
			throw new IllegalArgumentException(str);
		}
		Class<?> clazz = Class.forName(lines[0]);
		Object object = clazz.newInstance();
		if (lines.length > 1) {
			for (String item : lines) {
				String[] attr = StringUtils.split(item, "=");
				if (attr.length < 2) {
					throw new IllegalArgumentException(item);
				}
				Field field = clazz.getDeclaredField(attr[0]);
				if (!field.isAccessible()) {
					field.setAccessible(true);
				}
				Class<?> type = field.getType();
				if (type == int.class) {
					field.setInt(object, Integer.parseInt(attr[1]));
				} else if (type == byte.class) {
					field.setByte(object, Byte.parseByte(attr[1]));
				} else if (type == double.class) {
					field.setDouble(object, Double.parseDouble(attr[1]));
				} else if (type == float.class) {
					field.setFloat(object, Float.parseFloat(attr[1]));
				} else if (type == boolean.class) {
					field.setBoolean(object, Boolean.parseBoolean(attr[1]));
				} else if (type == long.class) {
					field.setLong(object, Long.parseLong(attr[1]));
				} else if (type == String.class) {
					field.set(object, attr[1]);
				} else {
					Constructor<?> constructor = type.getConstructor(new Class[] {String.class});
					field.set(object, constructor.newInstance(attr[1]));
				}
			}
		}
		return object;
	}
}
