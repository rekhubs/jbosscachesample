package com.thund.jbosscachetest;

import java.io.File;

import org.jboss.cache.Fqn;

public class Main {

	public static void main(String[] args) {
		FileCache<String> fileCache = new FileCache<String>(new File("d:\\test\\tmp"));
		
		Fqn<String> jimFqn = Fqn.fromString("/com/manager/jim");
		Fqn<String> tomFqn = Fqn.fromString("/com/developer/tom");
		
		fileCache.addNode(jimFqn);
		fileCache.addNode(tomFqn);
		
		fileCache.addNodeInfo(jimFqn, "en-name", "Jim Zhang");
		fileCache.addNodeInfo(jimFqn, "zh-name", "Zhang Ji");
		fileCache.addNodeInfo(tomFqn, "en-name", "Tom Wan");
		fileCache.addNodeInfo(tomFqn, "zh-name", "Wan Feng");
		
		String enName = fileCache.getNodeInfo(tomFqn, "en-name");
		System.out.println(enName);

	}

}
