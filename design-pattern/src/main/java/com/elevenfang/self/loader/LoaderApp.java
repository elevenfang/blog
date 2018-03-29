package com.elevenfang.self.loader;

import java.io.File;

public class LoaderApp {
	public static void main(String[] args) throws Exception {
		monitor();
		client();
	}

	private static volatile LoaderService service;
	public static final String CLASS_NAME = "com.elevenfang.self.loader.LoaderServiceImpl";
	public static final String BASE_FOLDER = "~/design-pattern/target/classes/";// change
																				// self
																				// directory
	public static final String FILE_NAME = BASE_FOLDER + CLASS_NAME.replaceAll("\\.", "/") + ".class";;

	public static LoaderService getService() throws Exception {
		if (service != null) {
			return service;
		}
		synchronized (LoaderApp.class) {
			if (service == null) {
				service = createServiceInstance();
			}
			return service;
		}
	}

	static LoaderService createServiceInstance() throws Exception {
		SelfClassLoader loader = new SelfClassLoader();
		Class<?> clazz = loader.loadClass(CLASS_NAME);
		System.out.println("find class....");
		if (null != clazz) {
			System.out.println("creating");
			return (LoaderService) clazz.newInstance();
		}
		return null;
	}

	static void client() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					while (true) {
						LoaderService service = getService();
						service.sayHello();
						Thread.sleep(2000L);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		thread.start();

	}

	static void monitor() {
		Thread thread = new Thread(new Runnable() {
			long lastModified = new File(FILE_NAME).lastModified();

			@Override
			public void run() {
				try {
					while (true) {
						Thread.sleep(2000L);
						long now = new File(FILE_NAME).lastModified();
						System.out.println(now == lastModified);
						if (now != lastModified) {
							System.out.println("different");
							lastModified = now;
							reloadService();
						}
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}
			}

			private void reloadService() {
				try {
					service = createServiceInstance();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		thread.start();
	}
}
