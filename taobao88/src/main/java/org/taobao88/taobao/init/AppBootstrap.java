package org.taobao88.taobao.init;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

public class AppBootstrap implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		String templatesPath = servletContext.getRealPath("/WEB-INF/pages/emails");
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_21);
		try {
			cfg.setDirectoryForTemplateLoading(new File(templatesPath));
			cfg.setDefaultEncoding("UTF-8");
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			servletContext.setAttribute("freemarker_cfg", cfg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
