package com.thund.jbosscachetest;

import java.io.File;
import java.util.Map;

import org.jboss.cache.Cache;
import org.jboss.cache.DefaultCacheFactory;
import org.jboss.cache.Fqn;
import org.jboss.cache.Node;
import org.jboss.cache.config.CacheLoaderConfig;
import org.jboss.cache.config.Configuration;
import org.jboss.cache.loader.FileCacheLoader;
import org.jboss.cache.loader.FileCacheLoaderConfig;


public class FileCache<T> {
	
	/**
	 * The JBoss Cache, used to cache frequently accessed Java objects. 
	 */
	private Cache<String, T> cache;
	
	public FileCache(File fsCacheLoaderLocation){
		cache = initCache(fsCacheLoaderLocation);
	}

	/**
	 * Create a Cache and whose cache loader type is File Cache Loader
	 * @param fsCacheLoaderLocation
	 * @return
	 */
	private Cache<String, T> initCache(File fsCacheLoaderLocation) {
		
		FileCacheLoader fsCacheLoader = new FileCacheLoader();
		
		FileCacheLoaderConfig fsCacheLoaderConfig = new FileCacheLoaderConfig();
		fsCacheLoaderConfig.setLocation(fsCacheLoaderLocation.toString());
		fsCacheLoaderConfig.setCacheLoader(fsCacheLoader);
		
		fsCacheLoader.setConfig(fsCacheLoaderConfig);
		
		Configuration config = new Configuration();
		config.setCacheLoaderConfig(new CacheLoaderConfig());
		config.getCacheLoaderConfig().addIndividualCacheLoaderConfig(fsCacheLoaderConfig);
		
		return new DefaultCacheFactory<String, T>().createCache(config);
	}
	
	public Node<String, T> addNode(Fqn<String> fqn){
		return cache.getRoot().addChild(fqn);				
	}
	
	public void removeNode(Fqn<String> fqn){
		cache.removeNode(fqn);
	}
	
	public void addNodeInfo(Fqn<String> fqn, String key, T value){
		cache.put(fqn, key, value);
	}
	
	public void addNodeInfos(Fqn<String> fqn, Map<String, T> infos){
		cache.put(fqn, infos);
	}
	
	public T getNodeInfo(Fqn<String> fqn, String key){
		return cache.get(fqn, key);
	}
	
	public void removeNodeInfo(Fqn<String> fqn, String key){
		cache.remove(fqn, key);		
	}

}
