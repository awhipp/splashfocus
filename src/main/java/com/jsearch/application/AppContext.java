package com.jsearch.application;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.jsearch.database.MySQLConnector;
import com.jsearch.util.EmailUtil;
import com.jsearch.util.GeoUtil;

@Component
public class AppContext implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		MySQLConnector.init();
		GeoUtil.init();
		EmailUtil.init();
	}
}