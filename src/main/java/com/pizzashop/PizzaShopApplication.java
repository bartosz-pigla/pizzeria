package com.pizzashop;

import com.pizzashop.controllers.PizzaController;
import com.pizzashop.productFilters.DrinkFilter;
import com.pizzashop.productFilters.PizzaFilter;
import com.pizzashop.productFilters.SauceFilter;
import com.pizzashop.repositories.customRepositories.ProductRepositoryImpl;
import com.pizzashop.initializers.DbInitializer;
import com.pizzashop.initializers.ProductFilterInitializer;
import com.pizzashop.aop.FilterChangedListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;

@SpringBootApplication
@Configuration
@ComponentScan(basePackageClasses={PizzaController.class, DbInitializer.class, FilterChangedListener.class, ProductRepositoryImpl.class})
@EnableTransactionManagement
@EnableCaching
public class PizzaShopApplication extends WebMvcConfigurerAdapter{
	public static void main(String[] args) {
		SpringApplication.run(PizzaShopApplication.class, args);
	}

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
}
