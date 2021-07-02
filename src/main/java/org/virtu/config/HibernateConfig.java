package org.virtu.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

import javax.sql.DataSource;

@Configuration
public class HibernateConfig {

    @Bean(name = "entityManagerFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
        sessionBuilder.scanPackages("org.virtu.domain");
        sessionBuilder.scanPackages("org.virtu.domain");
        sessionBuilder.setProperty("hibernate.show_sql", "false");

//      Нужно для решения пробелмы с ленивой загрузкой в @Async Возможно это решение не очень правильное. Проблемы с производительностью
        /*
        sessionBuilder.setProperty(org.hibernate.cfg.Environment.ENABLE_LAZY_LOAD_NO_TRANS, "true");
        sessionBuilder.setProperty(org.hibernate.cfg.Environment.USE_SECOND_LEVEL_CACHE, "true");
        sessionBuilder.setProperty(org.hibernate.cfg.Environment.CACHE_REGION_FACTORY, "org.hibernate.cache.jcache.JCacheRegionFactory");
        sessionBuilder.setProperty(org.hibernate.cfg.Environment.USE_QUERY_CACHE, "true");
        sessionBuilder.setProperty("hibernate.javax.cache.provider", "org.ehcache.jsr107.EhcacheCachingProvider");
        sessionBuilder.setProperty("hibernate.javax.cache.missing_cache_strategy", "create");

*/
        return sessionBuilder.buildSessionFactory();
    }

}
