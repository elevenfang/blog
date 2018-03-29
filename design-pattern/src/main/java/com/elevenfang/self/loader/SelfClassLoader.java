package com.elevenfang.self.loader;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class SelfClassLoader extends ClassLoader {

	@Override
	public Class<?> findClass(String name) throws ClassNotFoundException {
		try {
			String fileName = name.replaceAll("\\.", "/");
			fileName = LoaderApp.BASE_FOLDER + fileName + ".class";
			System.out.println("path:" + fileName + "," + new File(fileName).exists());
			byte[] data = FileUtils.readFileToByteArray(new File(fileName));
			return defineClass(name, data, 0, data.length);
		} catch (IOException e) {
			throw new ClassNotFoundException();
		}
	}

}
