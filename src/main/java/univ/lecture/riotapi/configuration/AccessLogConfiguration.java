package univ.lecture.riotapi.configuration;

import ch.qos.logback.access.tomcat.LogbackValve;
import org.apache.catalina.Context;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by rchung on 2016. 12. 1..
 */

@Configuration
public class AccessLogConfiguration {
    @ConditionalOnProperty(name = "application.logback.access.config.path")
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(final @Value("${application.logback.access.config.path}") String fileName) {
        return container -> {
            if (container instanceof TomcatEmbeddedServletContainerFactory) {
                ((TomcatEmbeddedServletContainerFactory) container)
                        .addContextCustomizers(new TomcatContextCustomizer() {

                            @Override
                            public void customize(Context context) {
                                LogbackValve logbackValve = new LogbackValve();
                                logbackValve.setFilename(fileName);
                                context.getPipeline().addValve(logbackValve);
                            }
                        });
            }
        };
    }
}
