package com.songz.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRenewedEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaRegistryAvailableEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaServerStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
@Configuration
@EnableScheduling
public class EurekaStateChangeListener implements ApplicationListener {

	private Logger logger = LoggerFactory.getLogger(EurekaStateChangeListener.class);

	@Override
	public void onApplicationEvent(ApplicationEvent applicationEvent) {

		// Eureka Server启动事件
		if (applicationEvent instanceof EurekaServerStartedEvent) {

			EurekaServerStartedEvent event = (EurekaServerStartedEvent) applicationEvent;
			if (logger.isInfoEnabled()) {
				logger.info("Eureka Server启动...", event.getTimestamp());
			}
		}

		// Eureka注册中心启动事件
		if (applicationEvent instanceof EurekaRegistryAvailableEvent) {

			EurekaRegistryAvailableEvent event = (EurekaRegistryAvailableEvent) applicationEvent;
			if (logger.isInfoEnabled()) {
				logger.info("Eureka注册中心启动...", event.getTimestamp());
			}
		}

		// 服务续约事件
		if (applicationEvent instanceof EurekaInstanceRenewedEvent) {

			EurekaInstanceRenewedEvent event = (EurekaInstanceRenewedEvent) applicationEvent;
			if (logger.isInfoEnabled()) {
				logger.info("AppName:" + event.getAppName() + " Id:" + event.getServerId() + "续约...");
			}
		}

		// 服务注册事件
		if (applicationEvent instanceof EurekaInstanceRegisteredEvent) {

			EurekaInstanceRegisteredEvent event = (EurekaInstanceRegisteredEvent) applicationEvent;
			if (logger.isInfoEnabled()) {
				logger.info("AppName:" + event.getInstanceInfo().getAppName() + "注册...");
			}
		}

		// 服务下线事件
		if (applicationEvent instanceof EurekaInstanceCanceledEvent) {

			EurekaInstanceCanceledEvent event = (EurekaInstanceCanceledEvent) applicationEvent;
			if (logger.isInfoEnabled()) {
				logger.info("AppName:" + event.getAppName() + " Id:" + event.getServerId() + "下线...");
			}
		}

	}

}
