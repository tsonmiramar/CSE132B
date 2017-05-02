package Configuration;
import java.beans.PropertyVetoException;
import java.util.List;
import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;


@Configuration
@EnableWebMvc
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan({"Controller","Repository","Service","Model"})
public class WebConfig extends WebMvcConfigurerAdapter implements TransactionManagementConfigurer{
	
	@Bean
	public ViewResolver internalResourceViewResolver() {
	    InternalResourceViewResolver bean = new InternalResourceViewResolver();
	    bean.setPrefix("/WEB-INF/view/");
	    bean.setSuffix(".jsp");
	    return bean;
	}
	
	//Setup Database DataSource
	@Bean(destroyMethod="close")
	public ComboPooledDataSource poolDataSource(){
		ComboPooledDataSource bean = new ComboPooledDataSource();
		try {
			bean.setDriverClass("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		
		bean.setJdbcUrl("jdbc:sqlserver://localhost;databaseName=TritonLink");
		bean.setUser("sa");
		bean.setPassword("$onquynh2002");
		
		bean.setMinPoolSize(3);
		bean.setMaxPoolSize(20);
		bean.setMaxIdleTime(30000);
		
		return bean;
	}
	
	
	//Setup Hibernate SessionFactory
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
		bean.setDataSource(poolDataSource());
		bean.setPackagesToScan("Model");
		
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
		properties.setProperty("hibernate.show_sql", "true");
		bean.setHibernateProperties(properties);
		
		return bean;
	}
	
	
	//Setup Hibernate Transaction Manager
	@Bean
	public HibernateTransactionManager myTransactionManager(){
		HibernateTransactionManager bean = new HibernateTransactionManager();
		bean.setSessionFactory(sessionFactory().getObject());
		return bean;
	}
	
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return myTransactionManager();
	}
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
		converters.add(new MappingJackson2HttpMessageConverter());
		super.configureMessageConverters(converters);
	}
}
