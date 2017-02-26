package com.pizzashop;

import com.pizzashop.controllers.PizzaController;
import com.pizzashop.models.Manager;
import com.pizzashop.productFilters.DrinkFilter;
import com.pizzashop.productFilters.PizzaFilter;
import com.pizzashop.productFilters.SauceFilter;
import com.pizzashop.repositories.customRepositories.ProductRepositoryImpl;
import com.pizzashop.initializers.DbInitializer;
import com.pizzashop.initializers.ProductFilterInitializer;
import com.pizzashop.aop.FilterChangedListener;
import com.pizzashop.services.CustomUserDetails;
import com.pizzashop.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
@Configuration
@ComponentScan(basePackageClasses={PizzaController.class, DbInitializer.class, FilterChangedListener.class, ProductRepositoryImpl.class, CustomUserDetailsService.class})
@EnableTransactionManagement
@EnableCaching
@RestController
public class PizzaShopApplication extends WebMvcConfigurerAdapter{
	public static void main(String[] args) {
		SpringApplication.run(PizzaShopApplication.class, args);
	}

	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}

//	@RequestMapping("/foo")
//	public void foo() {
//		System.out.println("FOO");
//
//	}

//	@RequestMapping(value = "/register", method = RequestMethod.POST)
//	public Manager user(@RequestBody @Validated({Manager.LoginValidation.class, Manager.RegistrationValidation.class}) Manager manager){
//		System.out.println(manager);
//		return manager;
//	}

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:63342");
			}
		};
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		super.configurePathMatch(configurer);
		configurer.setUseSuffixPatternMatch(false);
	}

	@Bean(name = "dbInitializer")
	public DbInitializer getDbInitializer(){
		return new DbInitializer();
	}

	@Bean
	@DependsOn("dbInitializer")
	public ProductFilterInitializer productFilterInitializer(){
		return new ProductFilterInitializer();
	}

	@Bean
	@DependsOn("dbInitializer")
	public PizzaFilter pizzaFilter(ProductFilterInitializer productFilterInitializer){
		return productFilterInitializer.createPizzaFilter();
	}

	@Bean
	@DependsOn("dbInitializer")
	public SauceFilter sauceFilter(ProductFilterInitializer productFilterInitializer){
		return productFilterInitializer.createSauceFilter();
	}

	@Bean
	@DependsOn("dbInitializer")
	public DrinkFilter drinkFilter(ProductFilterInitializer productFilterInitializer){
		return productFilterInitializer.createDrinkFilter();
	}

	@Bean(name = "requestForHeroku")
	public RestTemplate restTemplateForHerokuServerSleepingPrevention(){
		return new RestTemplate();
	}

	@Bean(name="DRIVER_NAME")
	public String driverName(){
		return System.getenv("DRIVER_NAME");
	}

	@Bean(name="APP_URL")
	public String appUrl(){
		return System.getenv("APP_URL");
	}

	@Bean
	public Timer herokuServerSleepingPrevention(
			@Qualifier(value = "DRIVER_NAME") String driverName,
			@Qualifier(value = "APP_URL") String appUrl,
			@Qualifier(value = "requestForHeroku") RestTemplate restTemplate
			){
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				//System.out.println("log for the sake of Heroku");
				if(driverName.equals("org.postgresql.Driver")){
					restTemplate.getForObject(appUrl+"/product/count", Integer.class);
					System.out.println("Heroku database driver name: "+driverName);
				}
			}
		},0,1000*60);

		return timer;
	}

	//SECURITY


//
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//				.inMemoryAuthentication()
//				.withUser("u2").password("p2").roles("USER");
//	}

	@Configuration
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
//			// @formatter:off
//			http
//				.httpBasic().and()
//				.authorizeRequests()
//					.antMatchers("/index.html", "/home.html", "/login.html", "/", "/info.html").permitAll()
//					.antMatchers("/**").permitAll()
//					.anyRequest().authenticated()
//					.and()
//				.csrf()
//					.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//			// @formatter:on

			// @formatter:off
			http
					.httpBasic().and()
					.authorizeRequests()
					.antMatchers("/**").permitAll()
					.antMatchers(HttpMethod.GET,"/foo").permitAll()
					.antMatchers(HttpMethod.POST,"/register").permitAll()
					.antMatchers(HttpMethod.POST,"/activate").permitAll()
					.antMatchers(HttpMethod.GET,"/**").authenticated()
					.and()
					.csrf()
					.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
			// @formatter:on
		}

		@Autowired
		private CustomUserDetailsService userDetailsService;

		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService);
		}
	}
}
