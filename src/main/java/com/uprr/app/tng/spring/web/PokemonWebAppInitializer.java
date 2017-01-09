package com.uprr.app.tng.spring.web;

import com.uprr.app.tng.spring.config.MainConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by david on 1/8/17.
 */
public class PokemonWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MainConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/secure/jas/*"};
    }
}
