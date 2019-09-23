package com.niule.znxj.core.common;

import org.springframework.context.annotation.PropertySource;

import java.util.ResourceBundle;

/**
 * 配置文件处理类
 * 
 * @author guods
 *
 */
@PropertySource(value = {"classpath:/application.properties"})
public final class Resources {
	public static final ResourceBundle ApplicationResources = ResourceBundle.getBundle("application");
}
