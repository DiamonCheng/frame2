package com.dc.dcrud;

import com.dc.frame2.core.dao.SearcherJpaRepositoryImpl;
import com.dc.frame2.view.support.Frame2ViewSpringConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/4/10.
 */
@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = SearcherJpaRepositoryImpl.class)
@Import({Frame2ViewSpringConfiguration.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
