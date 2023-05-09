# 🔥 Micro-services Tutorial using Spring Boot in One Video

: April 24, 2023
Github Repo: https://github.com/LearnCodeWithDurgesh/Microservices-Tutorial-Series
Last edited time: May 9, 2023 12:47 PM
Status: In progress
YoutubeUrl: https://www.youtube.com/watch?v=ubHa5I3yP70

# Micro-services

## What are microservices ?

Microservices are a software architecture pattern that involves breaking down a large application into smaller, independent services that communicate with each other through APIs. Each microservice is responsible for a specific task and can be developed, deployed, and scaled independently of other microservices.

In the context of Spring Boot, microservices are used by building individual Spring Boot applications that are designed to be lightweight and modular. These microservices can be deployed independently of each other and can communicate with each other using RESTful APIs. Spring Boot provides a range of tools and frameworks for building microservices, including Spring Cloud and Netflix OSS, which offer features such as service discovery, load balancing, and fault tolerance.

In summary, microservices are a way to design and build software applications that are more flexible, scalable, and resilient, and Spring Boot provides the tools and frameworks to build and manage these microservices in a simple and efficient manner.

## Microservices Project

## Creating Service Registry

- Create a Spring Starter Project and add the dependency : ************************************************************`Eureka Server, Cloud Bootstrap`************************************************************
    - `pom.xml`
        
        ```xml
        <?xml version="1.0" encoding="UTF-8"?>
        <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
        	<modelVersion>4.0.0</modelVersion>
        	<parent>
        		<groupId>org.springframework.boot</groupId>
        		<artifactId>spring-boot-starter-parent</artifactId>
        		<version>2.7.11</version>
        		<relativePath/> <!-- lookup parent from repository -->
        	</parent>
        	<groupId>com.spring.service.registery</groupId>
        	<artifactId>springMicroservice</artifactId>
        	<version>0.0.1-SNAPSHOT</version>
        	<name>ServiceRegistery</name>
        	<description>user project for Spring Boot</description>
        	<properties>
        		<java.version>11</java.version>
        		<spring-cloud.version>2021.0.6</spring-cloud.version>
        	</properties>
        	<dependencies>
        		<dependency>
        			<groupId>org.springframework.boot</groupId>
        			<artifactId>spring-boot-starter-web</artifactId>
        			<version>3.0.5</version>
        		</dependency>
        		<dependency>
        			<groupId>org.springframework.cloud</groupId>
        			<artifactId>spring-cloud-starter</artifactId>
        		</dependency>
        		<dependency>
        			<groupId>org.springframework.cloud</groupId>
        			<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        		</dependency>
        
        		<dependency>
        			<groupId>org.springframework.boot</groupId>
        			<artifactId>spring-boot-starter-test</artifactId>
        			<scope>test</scope>
        		</dependency>
        	</dependencies>
        	<dependencyManagement>
        		<dependencies>
        			<dependency>
        				<groupId>org.springframework.cloud</groupId>
        				<artifactId>spring-cloud-dependencies</artifactId>
        				<version>${spring-cloud.version}</version>
        				<type>pom</type>
        				<scope>import</scope>
        			</dependency>
        		</dependencies>
        	</dependencyManagement>
        
        	<build>
        		<plugins>
        			<plugin>
        				<groupId>org.springframework.boot</groupId>
        				<artifactId>spring-boot-maven-plugin</artifactId>
        			</plugin>
        		</plugins>
        	</build>
        
        </project>
        ```
        

- Then add below in `[application.properties](http://application.properties)` file or you can create the `aplication.yml` file and it as per its structure
    
    ```yaml
    server.port=8761
    eureka.instance.hostname=localhost
    eureka.client.register-with-eureka=false
    eureka.client.fetch-registry=false
    ```
    
    - **`server.port=8761`**: Sets the port for the Eureka server to listen on.
    - **`eureka.instance.hostname=localhost`**: Sets the host-name that the Eureka server will use to register itself with other Eureka servers in a cluster.
    - **`eureka.client.register-with-eureka=false`**: Disables self-registration of the Eureka server with itself.
    - **`eureka.client.fetch-registry=false`**: Disables fetching the registry of other Eureka servers, as this server is intended to be a standalone server and not part of a cluster.

- Now add `@EnableEurekaServer` to runner class
    
    ```java
    package com.spring.service.registery;
    
    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
    
    @SpringBootApplication
    @EnableEurekaServer
    public class ServiceRegisteryApplication {
    
    	public static void main(String[] args) {
    		SpringApplication.run(ServiceRegisteryApplication.class, args);
    	}
    
    }
    ```
    

## Creating User Service

- Create a Spring Starter Project and add the dependency : ************************************************************`Eureka Dicovery Client, Cloud Bootstrap`************************************************************
    - `pom.xml`
        
        ```xml
        <?xml version="1.0" encoding="UTF-8"?>
        <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
        	<modelVersion>4.0.0</modelVersion>
        	<parent>
        		<groupId>org.springframework.boot</groupId>
        		<artifactId>spring-boot-starter-parent</artifactId>
        		<version>2.7.11</version>
        		<relativePath/> <!-- lookup parent from repository -->
        	</parent>
        	<groupId>com.spring.micro.service</groupId>
        	<artifactId>springsecurityjwt</artifactId>
        	<version>0.0.1-SNAPSHOT</version>
        	<name>userServiceProject</name>
        	<description>user project for Spring Boot</description>
        	<properties>
        		<java.version>11</java.version>
        		<spring-cloud.version>2021.0.6</spring-cloud.version>
        	</properties>
        	<dependencies>
        		<dependency>
        			<groupId>org.springframework.boot</groupId>
        			<artifactId>spring-boot-starter-data-jpa</artifactId>
        		</dependency>
        		<dependency>
        			<groupId>org.springframework.boot</groupId>
        			<artifactId>spring-boot-starter-thymeleaf</artifactId>
        		</dependency>
        		<dependency>
        			<groupId>org.springframework.boot</groupId>
        			<artifactId>spring-boot-starter-web</artifactId>
        		</dependency>
        
        		<dependency>
        			<groupId>org.springframework.boot</groupId>
        			<artifactId>spring-boot-devtools</artifactId>
        			<scope>runtime</scope>
        			<optional>true</optional>
        		</dependency>
        		<dependency>
        			<groupId>com.mysql</groupId>
        			<artifactId>mysql-connector-j</artifactId>
        			<scope>runtime</scope>
        		</dependency>
        		<dependency>
        			<groupId>org.projectlombok</groupId>
        			<artifactId>lombok</artifactId>
        			<optional>true</optional>
        		</dependency>
        		<dependency>
        			<groupId>org.springframework.boot</groupId>
        			<artifactId>spring-boot-starter-test</artifactId>
        			<scope>test</scope>
        		</dependency>
        		<dependency>
        			<groupId>org.springframework.cloud</groupId>
        			<artifactId>spring-cloud-starter</artifactId>
        		</dependency>
        		<dependency>
        			<groupId>org.springframework.cloud</groupId>
        			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        		</dependency>
        		<dependency>
        	      <groupId>org.springframework.cloud</groupId>
        	      <artifactId>spring-cloud-starter-openfeign</artifactId>
        	    </dependency>
        	    <dependency>
        	      <groupId>org.springframework.cloud</groupId>
        	      <artifactId>spring-cloud-starter-config</artifactId>
        	    </dependency>
        	    <dependency>
        			<groupId>org.springframework.boot</groupId>
        			<artifactId>spring-boot-starter-actuator</artifactId>
        		</dependency>
        		<dependency>
        			<groupId>org.springframework.boot</groupId>
        			<artifactId>spring-boot-starter-aop</artifactId>
        		</dependency>
        		<!-- https://mvnrepository.com/artifact/io.github.resilience4j/resilience4j-spring-boot2 -->
        		<dependency>
        		    <groupId>io.github.resilience4j</groupId>
        		    <artifactId>resilience4j-spring-boot2</artifactId>
        		</dependency>
        		<dependency>
        	      <groupId>org.springframework.boot</groupId>
        	      <artifactId>spring-boot-starter-oauth2-client</artifactId>
        	    </dependency>
        	    <dependency>
        	      <groupId>org.springframework.boot</groupId>
        	      <artifactId>spring-boot-starter-security</artifactId>
        	    </dependency>
        	    <dependency>
        	      <groupId>com.okta.spring</groupId>
        	      <artifactId>okta-spring-boot-starter</artifactId>
        	      <version>2.1.6</version>
        	    </dependency>
        	</dependencies>
        	
        	<dependencyManagement>
        		<dependencies>
        			<dependency>
        				<groupId>org.springframework.cloud</groupId>
        				<artifactId>spring-cloud-dependencies</artifactId>
        				<version>${spring-cloud.version}</version>
        				<type>pom</type>
        				<scope>import</scope>
        			</dependency>
        		</dependencies>
        	</dependencyManagement>
        	
        
        	<build>
        		<plugins>
        			<plugin>
        				<groupId>org.springframework.boot</groupId>
        				<artifactId>spring-boot-maven-plugin</artifactId>
        				<configuration>
        					<excludes>
        						<exclude>
        							<groupId>org.projectlombok</groupId>
        							<artifactId>lombok</artifactId>
        						</exclude>
        					</excludes>
        				</configuration>
        			</plugin>
        		</plugins>
        	</build>
        
        </project>
        ```
        

- Then add below in `[application.properties](http://application.properties)` file or you can create the `aplication.yml` file and it as per its structure
    
    ```yaml
    server.port=8081
    spring.datasource.name=user_microservices
    spring.datasource.url=jdbc:mysql://localhost:3306/user_microservices
    spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=Mysql@123
    spring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect
    spring.jpa.show-sql=true
    spring.jpa.hibernate.ddl-auto=update
    
    spring.application.name=USER-SERVICE
    
    eureka.instance.prefer-ip-address=true
    eureka.client.fetch-registry=true
    eureka.client.register-with-eureka=true
    eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
    ```
    
    - **`server.port=8081`**: Specifies the port number that the microservice will run on.
    - **`spring.datasource.name=user_microservices`**: Sets the name of the data source for the microservice.
    - **`spring.datasource.url=jdbc:mysql://localhost:3306/user_microservices`**: Specifies the URL of the database that the microservice will connect to.
    - **`spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver`**: Specifies the name of the JDBC driver class that the microservice will use to connect to the database.
    - **`spring.datasource.username=root`**: Specifies the username that the microservice will use to authenticate with the database.
    - **`spring.datasource.password=Mysql@123`**: Specifies the password that the microservice will use to authenticate with the database.
    - **`spring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect`**: Specifies the dialect of the database that the microservice is using.
    - **`spring.jpa.show-sql=true`**: Sets the flag to enable SQL logging, which will print the SQL statements that the microservice generates to the console.
    - **`spring.jpa.hibernate.ddl-auto=update`**: Tells Hibernate to automatically update the database schema based on the entity classes in the application.
    - **`spring.application.name=USER-SERVICE`**: Sets the name of the microservice.
    - **`eureka.instance.prefer-ip-address=true`**: Tells Eureka to prefer using IP addresses over hostnames when registering the microservice instance.
    - **`eureka.client.fetch-registry=true`**: Tells the microservice to fetch the registry of services from Eureka.
    - **`eureka.client.register-with-eureka=true`**: Tells the microservice to register itself with Eureka.
    - **`eureka.client.service-url.defaultZone=http://localhost:8761/eureka/`**: Sets the URL for the Eureka server that the microservice will use to register and discover other services.

- Now add `@EnableEurekaClient` to runner class
    
    ```java
    package com.spring.service.registery;
    
    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
    
    @SpringBootApplication
    @EnableEurekaClient
    public class ServiceRegisteryApplication {
    
    	public static void main(String[] args) {
    		SpringApplication.run(ServiceRegisteryApplication.class, args);
    	}
    
    }
    ```
    
- Create a `User, Rating and Hotel` entities in package `com.spring.micro.service.entity`
    - `User`
        
        ```java
        package com.spring.micro.service.entity;
        
        import java.util.ArrayList;
        import java.util.List;
        
        import javax.persistence.Column;
        import javax.persistence.Entity;
        import javax.persistence.GeneratedValue;
        import javax.persistence.GenerationType;
        import javax.persistence.Id;
        import javax.persistence.Table;
        import javax.persistence.Transient;
        
        import lombok.AllArgsConstructor;
        import lombok.Builder;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;
        
        @Entity
        @Table(name = "micro_users")
        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        @Builder
        public class User {
        	
        	@Id
        	@Column(name = "user_id")
        	@GeneratedValue(strategy = GenerationType.IDENTITY)
        	private Long userId;
        	
        	@Column(name = "user_name", length = 20)
        	private String userName;
        	
        	@Column(name = "user_email")
        	private String userEmail;
        	
        	@Column(name = "user_about")
        	private String userAbout;
        	
        	@Transient
        	private List<Rating> ratings = new ArrayList<>();
        	
        }
        ```
        
    - `Rating`
        
        ```java
        package com.spring.micro.service.entity;
        
        import lombok.AllArgsConstructor;
        import lombok.Builder;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;
        
        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        @Builder
        public class Rating {
        
        	private Long ratingId;
        	private Long userId;
        	private Long hotelId;
        	private int rating;
        	private String feedback;
        	
        	private Hotel hotel;
        }
        ```
        
    - `Hotel`
        
        ```java
        package com.spring.micro.service.entity;
        
        import javax.persistence.Column;
        
        import lombok.AllArgsConstructor;
        import lombok.Builder;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;
        
        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        public class Hotel {
        	
        	private Long hotelId;
        	private String hotelName;
        	private String hotelAbout;
        	private String hotelLocation;
        }
        ```
        
    
- Create `service, serviceImpl, controller, repository` more over also create `GlobalExceptionHandler` class which handles all the exception and also `ApiResponse`  class to return the message along with status code and success is `true or false`
    - `UserService` in `com.spring.micro.service.services`
        
        ```java
        package com.spring.micro.service.services;
        
        import java.util.List;
        
        import org.springframework.stereotype.Service;
        
        import com.spring.micro.service.entity.User;
        import com.spring.micro.service.exception.ExceptionClass.ResourceNotFoundException;
        
        public interface UserService {
        
        	User saveUser(User user);
        	
        	List<User> getAllUsers();
        	
        	User getUserById(Long userId)throws ResourceNotFoundException ;
        	
        	void deleteUserById(Long userId);
        	
        	void updateUserById(Long userId);
        }
        ```
        
    
    - `UserServiceImpl` in `com.spring.micro.service.serviceImpl`
        
        ```java
        package com.spring.micro.service.serviceImpl;
        
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;
        import java.util.UUID;
        import java.util.stream.Collectors;
        
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.ResponseEntity;
        import org.springframework.stereotype.Service;
        import org.springframework.web.client.RestTemplate;
        
        import com.spring.micro.service.entity.Hotel;
        import com.spring.micro.service.entity.Rating;
        import com.spring.micro.service.entity.User;
        import com.spring.micro.service.exception.ExceptionClass;
        import com.spring.micro.service.exception.ExceptionClass.ResourceNotFoundException;
        import com.spring.micro.service.external.feignservices.HotelService;
        import com.spring.micro.service.repository.UserRepository;
        import com.spring.micro.service.services.UserService;
        
        @Service
        public class UserServiceImpl implements UserService{
        
        	@Autowired
        	private RestTemplate restTemplate;
        	
        	@Autowired
        	UserRepository userRepository;
        	
        	@Autowired
        	private HotelService hotelService;
        	
        	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
        	
        	@Override
        	public User saveUser(User user) {
        		return userRepository.save(user);
        	}
        
        	@Override
        	public List<User> getAllUsers() {
        		//implement the REST-SERVICE using RestTemplate
        		//List<User> userList= userRepository.findAll();
        		return userRepository.findAll();
        	}
        
        	
        	@Override
        	public User getUserById(Long userId) throws ResourceNotFoundException {
        		// TODO Auto-generated method stub
        		User user = userRepository.findById(userId).orElseThrow(() -> new ExceptionClass().new ResourceNotFoundException("No User found with this credentials"));
        		return user;
        	}
        
        	@Override
        	public void deleteUserById(Long userId) {
        		// TODO Auto-generated method stub
        		
        	}
        
        	@Override
        	public void updateUserById(Long userId) {
        		// TODO Auto-generated method stub
        		
        	}
        
        }
        ```
        
    
    - `UserController` in `com.spring.micro.service.controller`
        
        ```java
        package com.spring.micro.service.controller;
        
        import java.util.List;
        
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;
        
        import com.spring.micro.service.entity.User;
        import com.spring.micro.service.exception.ExceptionClass.ResourceNotFoundException;
        import com.spring.micro.service.services.UserService;
        
        import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
        import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
        import io.github.resilience4j.retry.annotation.Retry;
        
        @RestController
        @RequestMapping("/users")
        public class UserController {
        
        	@Autowired
        	private UserService userService;
        	
        	private Logger logger = LoggerFactory.getLogger(UserController.class);
        	
        	@GetMapping
        	public String usersa() {
        		System.out.println("ok");
        		return "ok";
        	}
        	
        	@PostMapping("/create-user")
        	public ResponseEntity<User> createUser(@RequestBody User user) {
        		User savedUser = userService.saveUser(user);
        		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        	}
        		
        	@GetMapping("/get-users/{userId}")
        	public ResponseEntity<User> getSingleUser(@PathVariable Long userId) throws ResourceNotFoundException {
        		User user = userService.getUserById(userId);
        		return ResponseEntity.status(HttpStatus.OK).body(user);
        	}
        	
        	@GetMapping("/get-all-users")
        	public ResponseEntity<List<User>> getAllUsers(){
        		List<User> users = userService.getAllUsers();
        		return ResponseEntity.ok(users);
        	}
        	
        }
        ```
        
    
    - `EcxeptionClass` in `com.spring.micro.service.exception`
        
        ```java
        package com.spring.micro.service.exception;
        
        public class ExceptionClass extends Exception{
        
        	public class ResourceNotFoundException extends Exception{
        		public ResourceNotFoundException(String message) {
        			super(message);
        			// TODO Auto-generated constructor stub
        		}
        	}
        }
        ```
        
    
    - `ApiResponse` in `com.spring.micro.service.payload`
        
        ```java
        package com.spring.micro.service.payload;
        
        import org.springframework.http.HttpStatus;
        
        import lombok.AllArgsConstructor;
        import lombok.Builder;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;
        
        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        @Builder
        public class ApiResponse {
        	
        	private  String message;
        	private boolean success;
        	private HttpStatus httpStatus;
        }
        ```
        
    
    - `GlobalExceptionHandler` in `com.spring.micro.service.exception`
        
        ```java
        package com.spring.micro.service.exception;
        
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.ExceptionHandler;
        import org.springframework.web.bind.annotation.RestControllerAdvice;
        
        import com.spring.micro.service.exception.ExceptionClass.ResourceNotFoundException;
        import com.spring.micro.service.payload.ApiResponse;
        
        @RestControllerAdvice
        public class GlobalExceptionHandler {
        
        	@ExceptionHandler(ExceptionClass.ResourceNotFoundException.class)
        	public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException exp) {
        		String message = exp.getMessage();
        		ApiResponse response = ApiResponse.builder().message(message).success(true).httpStatus(HttpStatus.NOT_FOUND).build();
        		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        		
        	}
        }
        ```
        
        This is a Spring Boot class named "GlobalExceptionHandler" that handles exceptions thrown by REST endpoints. It is annotated with @RestControllerAdvice.
        
        <aside>
        💡 @RestControllerAdvice, which tells Spring to use this class to handle exceptions across all controllers.
        
        </aside>
        
        The class has a single method, "handlerResourceNotFoundException," which handles the ResourceNotFoundException. It is  annotated with @ExceptionHandler, which specifies that it should handle ResourceNotFoundExceptionsThis method takes in a ResourceNotFoundException instance and returns a ResponseEntity object containing an ApiResponse object with the exception message, the success status, and the HTTP status code.
        
        Overall, this class provides a centralized way to handle exceptions thrown by REST endpoints in a Spring Boot application.
        

## Creating Rating Service

- Create a Spring Starter Project and add the dependency : ************************************************************`Eureka Dicovery Client, Cloud Bootstrap`************************************************************
    - `pom.xml`
        
        ```xml
        <?xml version="1.0" encoding="UTF-8"?>
        <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
        	<modelVersion>4.0.0</modelVersion>
        	<parent>
        		<groupId>org.springframework.boot</groupId>
        		<artifactId>spring-boot-starter-parent</artifactId>
        		<version>2.7.11</version>
        		<relativePath/> <!-- lookup parent from repository -->
        	</parent>
        	<groupId>com.spring.micro.service</groupId>
        	<artifactId>springsecurityjwt</artifactId>
        	<version>0.0.1-SNAPSHOT</version>
        	<name>ratingServiceProject</name>
        	<description>user project for Spring Boot</description>
        	<properties>
        		<java.version>11</java.version>
        		<spring-cloud.version>2021.0.6</spring-cloud.version>
        	</properties>
        	<dependencies>
        		<dependency>
        			<groupId>org.springframework.boot</groupId>
        			<artifactId>spring-boot-starter-data-jpa</artifactId>
        		</dependency>
        		<dependency>
        			<groupId>org.springframework.boot</groupId>
        			<artifactId>spring-boot-starter-thymeleaf</artifactId>
        		</dependency>
        		<dependency>
        			<groupId>org.springframework.boot</groupId>
        			<artifactId>spring-boot-starter-web</artifactId>
        		</dependency>
        
        		<dependency>
        			<groupId>org.springframework.boot</groupId>
        			<artifactId>spring-boot-devtools</artifactId>
        			<scope>runtime</scope>
        			<optional>true</optional>
        		</dependency>
        		<dependency>
        			<groupId>com.mysql</groupId>
        			<artifactId>mysql-connector-j</artifactId>
        			<scope>runtime</scope>
        		</dependency>
        		<dependency>
        			<groupId>org.projectlombok</groupId>
        			<artifactId>lombok</artifactId>
        			<optional>true</optional>
        		</dependency>
        		<dependency>
        			<groupId>org.springframework.boot</groupId>
        			<artifactId>spring-boot-starter-test</artifactId>
        			<scope>test</scope>
        		</dependency>
        		<dependency>
        			<groupId>org.springframework.cloud</groupId>
        			<artifactId>spring-cloud-starter</artifactId>
        		</dependency>
        		<dependency>
        			<groupId>org.springframework.cloud</groupId>
        			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        		</dependency>
        		 <dependency>
        	      <groupId>org.springframework.boot</groupId>
        	      <artifactId>spring-boot-starter-security</artifactId>
        	    </dependency>
        	    <dependency>
        	      <groupId>com.okta.spring</groupId>
        	      <artifactId>okta-spring-boot-starter</artifactId>
        	      <version>2.1.6</version>
        	    </dependency>
        	</dependencies>
        
        	
        	<dependencyManagement>
        		<dependencies>
        			<dependency>
        				<groupId>org.springframework.cloud</groupId>
        				<artifactId>spring-cloud-dependencies</artifactId>
        				<version>${spring-cloud.version}</version>
        				<type>pom</type>
        				<scope>import</scope>
        			</dependency>
        		</dependencies>
        	</dependencyManagement>
        
        	<build>
        		<plugins>
        			<plugin>
        				<groupId>org.springframework.boot</groupId>
        				<artifactId>spring-boot-maven-plugin</artifactId>
        				<configuration>
        					<excludes>
        						<exclude>
        							<groupId>org.projectlombok</groupId>
        							<artifactId>lombok</artifactId>
        						</exclude>
        					</excludes>
        				</configuration>
        			</plugin>
        		</plugins>
        	</build>
        
        </project>
        ```
        

- Then add below in `[application.properties](http://application.properties)` file or you can create the `aplication.yml` file and it as per its structure
    
    ```yaml
    server.port=8083
    spring.datasource.name=user_microservices
    spring.datasource.url=jdbc:mysql://localhost:3306/user_microservices
    spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=Mysql@123
    spring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect
    spring.jpa.show-sql=true
    spring.jpa.hibernate.ddl-auto=update
    
    spring.application.name=RATING-SERVICE
    
    eureka.instance.prefer-ip-address=true
    eureka.client.fetch-registry=true
    eureka.client.register-with-eureka=true
    eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
    ```
    
    - **`server.port=8083`**: Specifies the port number that the microservice will run on.
    - **`spring.datasource.name=user_microservices`**: Sets the name of the data source for the microservice.
    - **`spring.datasource.url=jdbc:mysql://localhost:3306/user_microservices`**: Specifies the URL of the database that the microservice will connect to.
    - **`spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver`**: Specifies the name of the JDBC driver class that the microservice will use to connect to the database.
    - **`spring.datasource.username=root`**: Specifies the username that the microservice will use to authenticate with the database.
    - **`spring.datasource.password=Mysql@123`**: Specifies the password that the microservice will use to authenticate with the database.
    - **`spring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect`**: Specifies the dialect of the database that the microservice is using.
    - **`spring.jpa.show-sql=true`**: Sets the flag to enable SQL logging, which will print the SQL statements that the microservice generates to the console.
    - **`spring.jpa.hibernate.ddl-auto=update`**: Tells Hibernate to automatically update the database schema based on the entity classes in the application.
    - **`spring.application.name=RATING-SERVICE`**: Sets the name of the microservice.
    - **`eureka.instance.prefer-ip-address=true`**: Tells Eureka to prefer using IP addresses over hostnames when registering the microservice instance.
    - **`eureka.client.fetch-registry=true`**: Tells the microservice to fetch the registry of services from Eureka.
    - **`eureka.client.register-with-eureka=true`**: Tells the microservice to register itself with Eureka.
    - **`eureka.client.service-url.defaultZone=http://localhost:8761/eureka/`**: Sets the URL for the Eureka server that the microservice will use to register and discover other services.

- Now add `@EnableEurekaClient` to runner class
    
    ```java
    package com.spring.micro.service;
    
    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
    
    @SpringBootApplication
    @EnableEurekaClient
    public class RatingServiceProjectApplication {
    
    	public static void main(String[] args) {
    		SpringApplication.run(RatingServiceProjectApplication.class, args);
    	}
    
    }
    ```
    
- Create a `Rating`  entities in package `com.spring.micro.service.entity`
    - `Rating`
        
        ```java
        package com.spring.micro.service.entity;
        
        import javax.persistence.Entity;
        import javax.persistence.GeneratedValue;
        import javax.persistence.GenerationType;
        import javax.persistence.Id;
        import javax.persistence.Table;
        
        import lombok.AllArgsConstructor;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;
        
        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        @Entity
        @Table(name = "rating_serivce")
        public class Rating {
        
        	@Id
        	@GeneratedValue(strategy = GenerationType.IDENTITY)
        	private Long ratingId;
        	private Long userId;
        	private Long hotelId;
        	private int rating;
        	private String feedback;
        }
        ```
        
    
- Create `service, serviceImpl, controller, repository` more over also create `GlobalExceptionHandler` class which handles all the exception and also `ApiResponse`  class to return the message along with status code and success is `true or false`
    - `RatingService` in `com.spring.micro.service.services`
        
        ```java
        package com.spring.micro.service.services;
        
        import java.util.List;
        
        import com.spring.micro.service.entity.Rating;
        
        public interface RatingService {
        
        	Rating createRatings(Rating rating);
        	
        	Rating updateRatingsByRatingId(Long ratingId,Rating rating);
        	
        	List<Rating> getAllRatings();
        	
        	List<Rating> getRatingById(Long ratingId);
        	
        	List<Rating> getRatingByHotelId(Long hotelId);
        	
        	List<Rating> getRatingByUserId(Long userId);
        }
        ```
        
    
    - `RatingServiceImpl` in `com.spring.micro.service.serviceImpl`
        
        ```java
        package com.spring.micro.service.serviceImpl;
        
        import java.util.List;
        
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;
        
        import com.spring.micro.service.entity.Rating;
        import com.spring.micro.service.repository.RatingRepository;
        import com.spring.micro.service.services.RatingService;
        
        @Service
        public class RatingServiceImpl implements RatingService {
        
        	@Autowired
        	RatingRepository ratingRepository;
        	
        	@Override
        	public Rating createRatings(Rating rating) {
        		// TODO Auto-generated method stub
        		return ratingRepository.save(rating);
        	}
        
        	@Override
        	public List<Rating> getAllRatings() {
        		// TODO Auto-generated method stub
        		return ratingRepository.findAll();
        	}
        
        	@Override
        	public List<Rating> getRatingById(Long ratingId) {
        		// TODO Auto-generated method stub
        		return ratingRepository.findByRatingId(ratingId);
        	}
        
        	@Override
        	public List<Rating> getRatingByHotelId(Long hotelId) {
        		// TODO Auto-generated method stub
        		return ratingRepository.findByHotelId(hotelId);
        	}
        
        	@Override
        	public List<Rating> getRatingByUserId(Long userId) {
        		// TODO Auto-generated method stub
        		return ratingRepository.findByUserId(userId);
        	}
        
        	@Override
        	public Rating updateRatingsByRatingId(Long ratingId,Rating currentRating) {
        		Rating exstingRating = ratingRepository.findRatingByRatingId(ratingId);	
        		exstingRating.setHotelId(currentRating.getHotelId());
        		exstingRating.setFeedback(currentRating.getFeedback());
        		exstingRating.setRating(currentRating.getRating());
        		exstingRating.setUserId(currentRating.getUserId());
        		
        		ratingRepository.save(exstingRating);
        		return exstingRating;
        	}
        
        }
        ```
        
    
    - `RatingController` in `com.spring.micro.service.controller`
        
        ```java
        package com.spring.micro.service.controller;
        
        import java.util.List;
        
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.security.access.prepost.PreAuthorize;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.PutMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;
        
        import com.spring.micro.service.entity.Rating;
        import com.spring.micro.service.services.RatingService;
        
        @RestController
        @RequestMapping("/ratings")
        public class RatingController {
        
        	@Autowired
        	private RatingService ratingService;
        	
        	@GetMapping("/")
        	public String ratings() {
        		return "ratings";
        	}
        	
        	@PostMapping("/create-rating")
        	public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        		return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.createRatings(rating));
        	}
        	
        	//Update ratings
        	public ResponseEntity<Rating> updateRatingByRatingId(@PathVariable Long ratingId,@RequestBody Rating rating) {
        		return ResponseEntity.status(HttpStatus.OK).body(ratingService.updateRatingsByRatingId(ratingId, rating));
        	}
        	
        	@GetMapping("/get-all-ratings")
        	public ResponseEntity<List<Rating>> getAllRatings(){
        		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getAllRatings());
        	}
        	
        	public ResponseEntity<List<Rating>> getRatingsByRatingId(@PathVariable Long ratingId ){
        		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingById(ratingId));
        	}
        	
        	@GetMapping("/hotels/{hotelId}")
        	public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable Long hotelId ){
        		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingByHotelId(hotelId));
        	}
        	
        	@GetMapping("/users/{userId}")
        	public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable Long userId ){
        		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingByUserId(userId));
        	}
        }
        ```
        
    
    - `EcxeptionClass` in `com.spring.micro.service.exception`
        
        ```java
        package com.spring.micro.service.exception;
        
        public class ExceptionClass extends Exception{
        
        	public class ResourceNotFoundException extends Exception{
        		public ResourceNotFoundException(String message) {
        			super(message);
        			// TODO Auto-generated constructor stub
        		}
        	}
        }
        ```
        
    
    - `ApiResponse` in `com.spring.micro.service.payload`
        
        ```java
        package com.spring.micro.service.payload;
        
        import org.springframework.http.HttpStatus;
        
        import lombok.AllArgsConstructor;
        import lombok.Builder;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;
        
        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        @Builder
        public class ApiResponse {
        	
        	private  String message;
        	private boolean success;
        	private HttpStatus httpStatus;
        }
        ```
        
    
    - `GlobalExceptionHandler` in `com.spring.micro.service.exception`
        
        ```java
        package com.spring.micro.service.exception;
        
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.ExceptionHandler;
        import org.springframework.web.bind.annotation.RestControllerAdvice;
        
        import com.spring.micro.service.exception.ExceptionClass.ResourceNotFoundException;
        import com.spring.micro.service.payload.ApiResponse;
        
        @RestControllerAdvice
        public class GlobalExceptionHandler {
        
        	@ExceptionHandler(ExceptionClass.ResourceNotFoundException.class)
        	public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException exp) {
        		String message = exp.getMessage();
        		ApiResponse response = ApiResponse.builder().message(message).success(true).httpStatus(HttpStatus.NOT_FOUND).build();
        		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        		
        	}
        }
        ```
        
        This is a Spring Boot class named "GlobalExceptionHandler" that handles exceptions thrown by REST endpoints. It is annotated with @RestControllerAdvice.
        
        <aside>
        💡 @RestControllerAdvice, which tells Spring to use this class to handle exceptions across all controllers.
        
        </aside>
        
        The class has a single method, "handlerResourceNotFoundException," which handles the ResourceNotFoundException. It is  annotated with @ExceptionHandler, which specifies that it should handle ResourceNotFoundExceptionsThis method takes in a ResourceNotFoundException instance and returns a ResponseEntity object containing an ApiResponse object with the exception message, the success status, and the HTTP status code.
        
        Overall, this class provides a centralized way to handle exceptions thrown by REST endpoints in a Spring Boot application.
        

## Creating Hotel Service

- Create a Spring Starter Project and add the dependency : ************************************************************`Eureka Dicovery Client, Cloud Bootstrap`************************************************************
    - `pom.xml`
        
        ```xml
        <?xml version="1.0" encoding="UTF-8"?>
        <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
        	<modelVersion>4.0.0</modelVersion>
        	<parent>
        		<groupId>org.springframework.boot</groupId>
        		<artifactId>spring-boot-starter-parent</artifactId>
        		<version>2.7.11</version>
        		<relativePath/> <!-- lookup parent from repository -->
        	</parent>
        	<groupId>com.spring.micro.service</groupId>
        	<artifactId>springsecurityjwt</artifactId>
        	<version>0.0.1-SNAPSHOT</version>
        	<name>hotelServiceProject</name>
        	<description>user project for Spring Boot</description>
        	<properties>
        		<java.version>11</java.version>
        		<spring-cloud.version>2021.0.6</spring-cloud.version>
        	</properties>
        	<dependencies>
        		<dependency>
        			<groupId>org.springframework.boot</groupId>
        			<artifactId>spring-boot-starter-data-jpa</artifactId>
        		</dependency>
        		<dependency>
        			<groupId>org.springframework.boot</groupId>
        			<artifactId>spring-boot-starter-thymeleaf</artifactId>
        		</dependency>
        		<dependency>
        			<groupId>org.springframework.boot</groupId>
        			<artifactId>spring-boot-starter-web</artifactId>
        		</dependency>
        
        		<dependency>
        			<groupId>org.springframework.boot</groupId>
        			<artifactId>spring-boot-devtools</artifactId>
        			<scope>runtime</scope>
        			<optional>true</optional>
        		</dependency>
        		<dependency>
        			<groupId>com.mysql</groupId>
        			<artifactId>mysql-connector-j</artifactId>
        			<scope>runtime</scope>
        		</dependency>
        		<dependency>
        			<groupId>org.projectlombok</groupId>
        			<artifactId>lombok</artifactId>
        			<optional>true</optional>
        		</dependency>
        		<dependency>
        			<groupId>org.springframework.boot</groupId>
        			<artifactId>spring-boot-starter-test</artifactId>
        			<scope>test</scope>
        		</dependency>
        		<dependency>
        			<groupId>org.springframework.cloud</groupId>
        			<artifactId>spring-cloud-starter</artifactId>
        		</dependency>
        		<dependency>
        			<groupId>org.springframework.cloud</groupId>
        			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        		</dependency>
        		<dependency>
        	      <groupId>org.springframework.boot</groupId>
        	      <artifactId>spring-boot-starter-security</artifactId>
        	    </dependency>
        	    <dependency>
        	      <groupId>com.okta.spring</groupId>
        	      <artifactId>okta-spring-boot-starter</artifactId>
        	      <version>2.1.6</version>
        	    </dependency>
        	</dependencies>
        
        	<dependencyManagement>
        		<dependencies>
        			<dependency>
        				<groupId>org.springframework.cloud</groupId>
        				<artifactId>spring-cloud-dependencies</artifactId>
        				<version>${spring-cloud.version}</version>
        				<type>pom</type>
        				<scope>import</scope>
        			</dependency>
        		</dependencies>
        	</dependencyManagement>
        
        	<build>
        		<plugins>
        			<plugin>
        				<groupId>org.springframework.boot</groupId>
        				<artifactId>spring-boot-maven-plugin</artifactId>
        				<configuration>
        					<excludes>
        						<exclude>
        							<groupId>org.projectlombok</groupId>
        							<artifactId>lombok</artifactId>
        						</exclude>
        					</excludes>
        				</configuration>
        			</plugin>
        		</plugins>
        	</build>
        
        </project>
        ```
        

- Then add below in `[application.properties](http://application.properties)` file or you can create the `aplication.yml` file and it as per its structure
    
    ```yaml
    server.port=8082
    spring.datasource.name=user_microservices
    spring.datasource.url=jdbc:mysql://localhost:3306/user_microservices
    spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=Mysql@123
    spring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect
    spring.jpa.show-sql=true
    spring.jpa.hibernate.ddl-auto=update
    
    spring.application.name=RATING-SERVICE
    
    eureka.instance.prefer-ip-address=true
    eureka.client.fetch-registry=true
    eureka.client.register-with-eureka=true
    eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
    ```
    
    - **`server.port=8082`**: Specifies the port number that the microservice will run on.
    - **`spring.datasource.name=user_microservices`**: Sets the name of the data source for the microservice.
    - **`spring.datasource.url=jdbc:mysql://localhost:3306/user_microservices`**: Specifies the URL of the database that the microservice will connect to.
    - **`spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver`**: Specifies the name of the JDBC driver class that the microservice will use to connect to the database.
    - **`spring.datasource.username=root`**: Specifies the username that the microservice will use to authenticate with the database.
    - **`spring.datasource.password=Mysql@123`**: Specifies the password that the microservice will use to authenticate with the database.
    - **`spring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect`**: Specifies the dialect of the database that the microservice is using.
    - **`spring.jpa.show-sql=true`**: Sets the flag to enable SQL logging, which will print the SQL statements that the microservice generates to the console.
    - **`spring.jpa.hibernate.ddl-auto=update`**: Tells Hibernate to automatically update the database schema based on the entity classes in the application.
    - **`spring.application.name=HOTEL-SERVICE`**: Sets the name of the microservice.
    - **`eureka.instance.prefer-ip-address=true`**: Tells Eureka to prefer using IP addresses over hostnames when registering the microservice instance.
    - **`eureka.client.fetch-registry=true`**: Tells the microservice to fetch the registry of services from Eureka.
    - **`eureka.client.register-with-eureka=true`**: Tells the microservice to register itself with Eureka.
    - **`eureka.client.service-url.defaultZone=http://localhost:8761/eureka/`**: Sets the URL for the Eureka server that the microservice will use to register and discover other services.

- Now add `@EnableEurekaClient` to runner class
    
    ```java
    package com.spring.micro.service;
    
    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
    
    @SpringBootApplication
    @EnableEurekaClient
    public class HotelServiceProjectApplication {
    
    	public static void main(String[] args) {
    		SpringApplication.run(HotelServiceProjectApplication.class, args);
    	}
    
    }
    ```
    
- Create a `Hotel`  entities in package `com.spring.micro.service.entity`
    - `Hotel`
        
        ```java
        package com.spring.micro.service.entity;
        
        import javax.persistence.Column;
        import javax.persistence.Entity;
        import javax.persistence.GeneratedValue;
        import javax.persistence.GenerationType;
        import javax.persistence.Id;
        import javax.persistence.Table;
        
        import lombok.AllArgsConstructor;
        import lombok.Builder;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;
        
        @Entity
        @Table(name = "hotel_service")
        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        @Builder
        public class Hotel {
        	
        	@Id
        	@Column(name = "hotel_id")
        	@GeneratedValue(strategy = GenerationType.IDENTITY)
        	private Long hotelId;
        	
        	@Column(name = "hotel_name")
        	private String hotelName;
        	
        	@Column(name = "hotel_about")
        	private String hotelAbout;
        	
        	@Column(name = "hotel_location")
        	private String hotelLocation;
        	
        }
        ```
        
    
- Create `service, serviceImpl, controller, repository` more over also create `GlobalExceptionHandler` class which handles all the exception and also `ApiResponse`  class to return the message along with status code and success is `true or false`
    - `HotelService` in `com.spring.micro.service.services`
        
        ```java
        package com.spring.micro.service.services;
        
        import java.util.List;
        
        import com.spring.micro.service.entity.Hotel;
        
        public interface HotelService {
        	
        	Hotel createHotel(Hotel hotel);
        	
        	List<Hotel> getAllHotels();
        	
        	Hotel getHotelById(Long hotelId);
        }
        ```
        
    
    - `HotelServiceImpl` in `com.spring.micro.service.serviceImpl`
        
        ```java
        package com.spring.micro.service.serviceImpl;
        
        import java.util.List;
        
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;
        
        import com.spring.micro.service.entity.Hotel;
        import com.spring.micro.service.exception.ExceptionClass;
        import com.spring.micro.service.exception.ExceptionClass.ResourceNotFoundException;
        import com.spring.micro.service.repository.HotelRepository;
        import com.spring.micro.service.services.HotelService;
        
        @Service
        public class HotelServiceImpl implements HotelService{
        
        	@Autowired
        	HotelRepository hotelRepository;
        	
        	@Override
        	public Hotel createHotel(Hotel hotel) {
        		// TODO Auto-generated method stub
        		return hotelRepository.save(hotel);
        	}
        
        	@Override
        	public List<Hotel> getAllHotels() {
        		// TODO Auto-generated method stub
        		return hotelRepository.findAll();
        	}
        
        	@Override
        	public Hotel getHotelById(Long hotelId) {
        		// TODO Auto-generated method stub
        		return hotelRepository.findById(hotelId).orElseThrow(() -> new ExceptionClass().new ResourceNotFoundException("No User found with this credentials"));
        	}
        
        }
        ```
        
    
    - `HotelController` in `com.spring.micro.service.controller`
        
        ```java
        package com.spring.micro.service.controller;
        
        import java.util.List;
        
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.security.access.prepost.PreAuthorize;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;
        
        import com.spring.micro.service.entity.Hotel;
        import com.spring.micro.service.services.HotelService;
        
        @RestController
        @RequestMapping("/hotels")
        public class HotelController {
        
        	@Autowired
        	private HotelService hotelService;
        	
        	@GetMapping("/")
        	public String hotel() {
        		return "hotel";
        	}
        	
        	@PostMapping("/create-hotel")
        	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        		return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(hotel));
        	}
        	
        	@GetMapping("/hotel-by-id/{hotelId}")
        	public ResponseEntity<Hotel> getHotelById(@PathVariable Long hotelId) {
        		return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotelById(hotelId));
        	}
        	
        	@GetMapping("/get-all-hotels")
        	public ResponseEntity<List<Hotel>> getAllHotels() {
        		return ResponseEntity.ok(hotelService.getAllHotels());
        	}
        }
        ```
        
    
    - `EcxeptionClass` in `com.spring.micro.service.exception`
        
        ```java
        package com.spring.micro.service.exception;
        
        public class ExceptionClass extends Exception{
        
        	public class ResourceNotFoundException extends Exception{
        		public ResourceNotFoundException(String message) {
        			super(message);
        			// TODO Auto-generated constructor stub
        		}
        	}
        }
        ```
        
    
    - `ApiResponse` in `com.spring.micro.service.payload`
        
        ```java
        package com.spring.micro.service.payload;
        
        import org.springframework.http.HttpStatus;
        
        import lombok.AllArgsConstructor;
        import lombok.Builder;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;
        
        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        @Builder
        public class ApiResponse {
        	
        	private  String message;
        	private boolean success;
        	private HttpStatus httpStatus;
        }
        ```
        
    
    - `GlobalExceptionHandler` in `com.spring.micro.service.exception`
        
        ```java
        package com.spring.micro.service.exception;
        
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.ExceptionHandler;
        import org.springframework.web.bind.annotation.RestControllerAdvice;
        
        import com.spring.micro.service.exception.ExceptionClass.ResourceNotFoundException;
        import com.spring.micro.service.payload.ApiResponse;
        
        @RestControllerAdvice
        public class GlobalExceptionHandler {
        
        	@ExceptionHandler(ExceptionClass.ResourceNotFoundException.class)
        	public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException exp) {
        		String message = exp.getMessage();
        		ApiResponse response = ApiResponse.builder().message(message).success(true).httpStatus(HttpStatus.NOT_FOUND).build();
        		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        		
        	}
        }
        ```
        
        This is a Spring Boot class named "GlobalExceptionHandler" that handles exceptions thrown by REST endpoints. It is annotated with @RestControllerAdvice.
        
        <aside>
        💡 @RestControllerAdvice, which tells Spring to use this class to handle exceptions across all controllers.
        
        </aside>
        
        The class has a single method, "handlerResourceNotFoundException," which handles the ResourceNotFoundException. It is  annotated with @ExceptionHandler, which specifies that it should handle ResourceNotFoundExceptionsThis method takes in a ResourceNotFoundException instance and returns a ResponseEntity object containing an ApiResponse object with the exception message, the success status, and the HTTP status code.
        
        Overall, this class provides a centralized way to handle exceptions thrown by REST endpoints in a Spring Boot application.
        

## Using Rest Template to call the Rating API in User Service

```java
@Override
	public User getUserById(Long userId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId).orElseThrow(() -> new ExceptionClass().new ResourceNotFoundException("No User found with this credentials"));
		
		// now fetch rating for the user from RATING-SERVICE
		//http://localhost:8083/ratings/users/1
		// in order to call the rating service in user we will use here
		// the Resttemplate @Autowired private RestTemplate restTemplate;
		
		// we cannot directly convert this into ArrayList so here we get it into an array then convert the array to arraylist
		Rating[] userRatings =  restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
		
		//Array to ArrayList
		List<Rating> userRatingsList = Arrays.stream(userRatings).toList();
		logger.info("{}", userRatings);
		
		//now get the hotels and set it to the related ratings
		List<Rating> ratingList = userRatingsList.stream().map(rating -> {
			//api call to hotel service to get the hotel for url : localhost:8082/hotels/hotel-by-id/2 this is hard coded
			//ResponseEntity<Hotel> responseEntity =  restTemplate.getForEntity("http://localhost:8082/hotels/hotel-by-id/"+rating.getHotelId(), Hotel.class);
			//below is dynamic i.e it will not use the hostname or the ip address instead it will take the service name like [HOTEL-SERVICE]
			//and if we use the service name we have to mention a annotation
			// @LoadBalanced in the config class for the bean we have declared for RestTemplate
			ResponseEntity<Hotel> responseEntity =  restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/hotel-by-id/"+rating.getHotelId(), Hotel.class);
			Hotel hotel = responseEntity.getBody();
			
			//set the hotel to Rating
			rating.setHotel(hotel);
			
			//return the rating
			return rating;
			
		}).collect(Collectors.toList());
		
		user.setRatings(ratingList);
		return user;
	}
```

This code is an implementation of a method called **`getUserById`** which fetches a user object from the database using the given user id. After getting the user object, the code sends a GET request to the RATING-SERVICE to fetch ratings for the user. The ratings are received in the form of an array and then converted to an ArrayList. The code then sends a GET request to the HOTEL-SERVICE to fetch hotels related to each rating, and adds them to the corresponding rating object. Finally, the code sets the list of ratings to the user object and returns it.

To achieve this, the code uses a RestTemplate to make the REST API calls to the RATING-SERVICE and HOTEL-SERVICE.

The response from the service is an array of **`Rating`** objects, which is then converted to an **`ArrayList`** using the **`Arrays.stream()`** method and **`toList()`** method.

Next, it uses the **`stream`** method of the **`ArrayList`** to iterate through each **`Rating`** object in the list and fetches the corresponding hotel details by calling the **`getForEntity`** method of **`RestTemplate`** with the URL of the hotel service and the hotel ID. The response from the hotel service is deserialized into a **`Hotel`** object, which is then set to the **`Hotel`** field of the **`Rating`** object. Finally, the updated **`Rating`** object is returned.

Finally, the method sets the **`ArrayList`** of ratings to the **`ratings`** field of the **`User`** object and returns the **`User`** object. Overall, this code fetches the user ratings and the corresponding hotel details using a **`RestTemplate`** object to call remote services. It also uses the **`@LoadBalanced`** annotation for the **`RestTemplate`** bean in the configuration class to make it discoverable by the service registry.

## Using Feign Client to call the Rating API in User Service

Feign is a declarative web service client that allows developers to make HTTP requests to RESTful services more easily. It simplifies the process of making HTTP requests by abstracting away much of the low-level boilerplate code that is required when using lower-level libraries like RestTemplate. One of the key features of Feign is its ability to generate client-side code for invoking RESTful services.

Feign provides a simple way to create REST clients, and the client code can be written as an interface. This interface can be annotated with Feign annotations that specify the target URL, HTTP method, and request/response format. Feign uses these annotations to generate the necessary boilerplate code that is required to make HTTP requests.

Using Feign, developers can define an interface that describes the HTTP API of a service and then use that interface to create a client that can interact with the service. This allows developers to write client code that is clean, concise, and easy to understand.

### Steps to use the feign client :[This is called a declarative approach]

- Add the feign client dependency in **`pom.xml`**
    
    ```xml
    <dependency>
    	   <groupId>org.springframework.cloud</groupId>
    	   <artifactId>spring-cloud-starter-openfeign</artifactId>
    </dependency>
    ```
    
- Then add the annotation `@EnableFeignClients` to the runner class
- Then a  `HotelService` interface in package `com.spring.micro.service.external.feignsevices`  and add the following method
    
    ```java
    @GetMapping("/hotels/{hoteld}")
    	Hotel getHotelById(@PathVariable("hotelId") String hotelId);
    ```
    
- Then in-order to use it in the UserServiceImpl we Autowire it
    
    ```java
    @Autowired
    	HotelService hotelService;
    ```
    
- And now use this `Hotel hotel = hotelService.getHotelById(rating.getHotelId());` instead of this `ResponseEntity<Hotel> responseEntity = restTemplate.getForEntity("[http://HOTEL-SERVICE/hotels/hotel-by-id/](http://hotel-service/hotels/hotel-by-id/)"+rating.getHotelId(), Hotel.class);`  Do keep in mind the uri path we are giving in the `@GetMapping`   it should same as we have used in original hotel controller or other we are using like rating
    
    ```java
    @Override
    	public User getUserById(Long userId) throws ResourceNotFoundException {
    		// TODO Auto-generated method stub
    		User user = userRepository.findById(userId).orElseThrow(() -> new ExceptionClass().new ResourceNotFoundException("No User found with this credentials"));
    	
    		// we cannot directly convert this into ArrayList so here we get it into an array then convert the array to arraylist
    		Rating[] userRatings =  restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
    		
    		//Array to ArrayList
    		List<Rating> userRatingsList = Arrays.stream(userRatings).toList();
    		logger.info("{}", userRatings);
    		
    		//now get the hotels and set it to the related ratings
    		List<Rating> ratingList = userRatingsList.stream().map(rating -> {
    			
    			//ResponseEntity<Hotel> responseEntity =  restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/hotel-by-id/"+rating.getHotelId(), Hotel.class);
    			Hotel hotel = hotelService.getHotelById(rating.getHotelId());
    			
    			//set the hotel to Rating
    			rating.setHotel(hotel);
    			
    			//return the rating
    			return rating;
    			
    		}).collect(Collectors.toList());
    		
    		user.setRatings(ratingList);
    		return user;
    	}
    ```
    

### Creating GET, POST, PUT & DELETE Http call using Feign client

In`com.spring.micro.service.external.feignsevices`  create `RatingService` interdace and add the following:

```java
package com.spring.micro.service.external.feignservices;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.spring.micro.service.entity.Rating;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

	//GET
	@GetMapping("/get-all-ratings")
	public ResponseEntity<List<Rating>> getAllRatings();
	
	//POST
	@PostMapping("/ratings/create-rating")
	public ResponseEntity<Rating> createRating(@RequestBody Rating ratings);
	
	//PUT
	@PutMapping("/update-rating-by-ratingId/{ratingId}")
	public Rating updateRatingsByRatingId(@PathVariable Long ratingId,@RequestBody Rating rating);
	
	//Delete
	@DeleteMapping("/delete-rating-by-ratingId/{ratingId}")
	public boolean deleteRatingsBYRatingId(@PathVariable Long ratingId);
	
	
}
```

## API Gateway

<aside>
⭐ Refer this documentation [https://cloud.spring.io/spring-cloud-gateway/reference/html/](https://cloud.spring.io/spring-cloud-gateway/reference/html/)

</aside>

An API gateway is a server that acts as an entry point for all the microservices APIs in a system. It provides a centralized point of entry for clients to access the APIs of multiple microservices. Here are some reasons why we use an API gateway in microservices architecture with Spring Boot:

1. Centralized Access Control: An API gateway provides a single point of access to all the microservices, which makes it easier to implement access control and security policies. It can perform authentication, authorization, and access control at a centralized level.
2. Load Balancing: An API gateway can distribute the incoming requests to different instances of the same microservice based on the load on each instance. This can help to optimize the use of available resources.
3. Routing and Service Discovery: An API gateway can route incoming requests to the appropriate microservice based on the URL, headers, or other parameters in the request. It can also help to discover the available microservices in the system.
4. Protocol Translation: An API gateway can translate incoming requests from one protocol to another. For example, it can translate RESTful requests to SOAP requests or vice versa.
5. Caching: An API gateway can cache the responses from microservices to improve the performance of the system. This can reduce the load on the microservices and improve the response time for clients.

Overall, an API gateway provides a unified and simplified interface for clients to access the APIs of multiple microservices in a system. It can help to improve the scalability, reliability, and security of the system.

### Create API Gateway

now first add the dependencies `Gateway, Spring Reactive Web, Cloud Bootstrap, Eureka discovery client` 

Refer below `pom.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.7.11</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.spring.api.gateway</groupId>
	<artifactId>springMicroserviceApiGateway</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>ApiGateway</name>
	<description>user project for Spring Boot</description>
	<properties>
		<java.version>11</java.version>
		<spring-cloud.version>2021.0.6</spring-cloud.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webflux</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-gateway</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>io.projectreactor</groupId>
			<artifactId>reactor-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>
		</plugins>
	</build>

</project>
```

Then enable the Eureka discovery client by adding the annotation `@EnableEurekaClient` 

```java
package com.spring.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
```

Then add this in `application.properties`

```yaml
server.port=8084

spring.application.name=API-GATEWAY

eureka.instance.prefer-ip-address=true
eureka.client.fetch-registry=true
eureka.client.register-with-eureka=true
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/

#Configure API Gateway routes
spring.cloud.gateway.routes[0].id=USER-SERVICE
spring.cloud.gateway.routes[0].uri=lb://USER-SERVICE
spring.cloud.gateway.routes[0].predicates[0]=Path=/users/**

spring.cloud.gateway.routes[1].id=HOTEL-SERVICE
spring.cloud.gateway.routes[1].uri=lb://HOTEL-SERVICE
spring.cloud.gateway.routes[1].predicates[0]=Path=/hotels/**

spring.cloud.gateway.routes[2].id=RATING-SERVICE
spring.cloud.gateway.routes[2].uri=lb://RATING-SERVICE
spring.cloud.gateway.routes[2].predicates[0]=Path=/ratings/**
```

- **`spring.cloud.gateway.routes[1].id=HOTEL-SERVICE`**: This sets the ID of the route to HOTEL-SERVICE.
- **`spring.cloud.gateway.routes[1].uri=lb://HOTEL-SERVICE`**: This sets the URI of the route to lb://HOTEL-SERVICE. The **`lb`** prefix indicates that this is a load-balanced route. In other words, requests to this route will be load-balanced across instances of the HOTEL-SERVICE service. The **`//HOTEL-SERVICE`** part specifies the name of the service to route to.
- **`spring.cloud.gateway.routes[1].predicates[0]=Path=/hotels/**`**: This sets up a predicate for the route. Predicates are used to match incoming requests to a route. In this case, the predicate is **`Path=/hotels/**`**, which means that any request to a URL that starts with **`/hotels`** will match this route.

<aside>
💡 Now we are able to access the USER-SERVICE, HOTEL-SERVICE and RATING-SERVICE through API-GATEWAY like we can even create user by `[http://127.0.0.1:8084/users/create-user](http://127.0.0.1:8084/users/create-user)` where 127.0.0.1 is the IP address [we can also mention hostname i.e here [localhost](http://localhost)]  8084 is the port no. of the API-GATEWAY and the users is the path

</aside>

********************************************************But now there is one Issue :********************************************************

If we have a `StaffController` in Hotel Service in the controller package the we are able to access the staff controller methods by using the url path but that is not possible when we do it with the API-GATEWAY 

To resolve this we need to add the path `/staffs/**` in predicate for that specific service in which that controller is present

```yaml
server.port=8084

spring.application.name=API-GATEWAY

eureka.instance.prefer-ip-address=true
eureka.client.fetch-registry=true
eureka.client.register-with-eureka=true
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/

#Configure API Gateway routes
spring.cloud.gateway.routes[0].id=USER-SERVICE
spring.cloud.gateway.routes[0].uri=lb://USER-SERVICE
spring.cloud.gateway.routes[0].predicates[0]=Path=/users/**

spring.cloud.gateway.routes[1].id=HOTEL-SERVICE
spring.cloud.gateway.routes[1].uri=lb://HOTEL-SERVICE
spring.cloud.gateway.routes[1].predicates[0]=Path=/hotels/**,/staffs/**

spring.cloud.gateway.routes[2].id=RATING-SERVICE
spring.cloud.gateway.routes[2].uri=lb://RATING-SERVICE
spring.cloud.gateway.routes[2].predicates[0]=Path=/ratings/**
```

## Config Server

### What is config server?

In a microservices architecture, the Config Server in Spring Boot is a centralized configuration service that manages external configurations for distributed applications across all environments. It is a dedicated server that stores configurations in one or more Git repositories, and microservices applications can access it to obtain their specific configurations. The Config Server is designed to simplify the management of configurations for multiple instances of microservices in a distributed environment, and it allows applications to dynamically fetch new configurations as they become available, without the need to redeploy or restart the application.

We don’t have to repeat the configurations in every service in our local machine

************************************************************To use config server we need to create a new Spring boot starter project :************************************************************

Create a new starter project and the  `Config Server`& `Eureka Discovery Client` dependencies in the `pom.xml` or add them while creating the project

 ************************************************************************************************************************

```xml
		<dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-config-server</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
```

`pom.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.7.11</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.spring.config.server</groupId>
	<artifactId>springMicroservicesConfigServer</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>ConfigServer</name>
	<description>user project for Spring Boot</description>
	<properties>
		<java.version>11</java.version>
		<spring-cloud.version>2021.0.6</spring-cloud.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-config-server</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>
```

Then add `@EnableConfigServer`   in runner class

```java
package com.spring.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}

}
```

Then create a repository on GitHub and add a file `application.yml`  or `[application.properties](http://application.properties)`  and then in-order to use whatever configs in `applicarion`   file on GitHub  to local machine Config Server we have to add `spring.cloud.config.server.git.uri=https://github.com/Rushibhawar/Microservices-Tutorial-configuration`  

 `spring.cloud.config.server.git.clone-on-start=true` in `application.properties` of the ConfigServer microservice :

```yaml
server.port=8085
spring.application.name=CONFIG-SERVER

spring.cloud.config.server.git.uri=https://github.com/Rushibhawar/Microservices-Tutorial-configuration
spring.cloud.config.server.git.clone-on-start=true
```

1. **`spring.cloud.config.server.git.uri`**: This property specifies the URI of the Git repository that contains the configuration files. In this case, it is set to **`https://github.com/Rushibhawar/Microservices-Tutorial-configuration`**.
2. **`spring.cloud.config.server.git.clone-on-start`**: This property controls whether the Git repository should be cloned on startup of the Config Server. If set to **`true`**, the repository will be cloned; if set to **`false`**, it will not be cloned.

********************************************************************************************************Now to use this config server in other services we need to add `spring.config.import=configserver:http://localhost:8085/` in `[application.properties](http://application.properties)` in order to use it.**

```yaml
spring.config.import=configserver:http://localhost:8085/
```

`[application.properties](http://application.properties)` of User service

```yaml
server.port=8081
spring.datasource.name=user_microservices
spring.datasource.url=jdbc:mysql://localhost:3306/user_microservices
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=Mysql@123
spring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update

spring.application.name=USER-SERVICE

#eureka.instance.prefer-ip-address=true
#eureka.client.fetch-registry=true
#eureka.client.register-with-eureka=true
#eureka.client.service-url.defaultZone=http://localhost:8761/eureka/

#configurint the config server client
spring.config.import=configserver:http://localhost:8085/
```

## Fault Tolerance & Circuit Breaker

<aside>
💡 Refer the below documentation of resilience4j-circuitbreaker [https://resilience4j.readme.io/docs/circuitbreaker](https://resilience4j.readme.io/docs/circuitbreaker)

</aside>

In microservices architecture, fault tolerance is the ability of a system to continue functioning even when one or more services fail or are experiencing high latency. When a service becomes unresponsive or slow, it can cause cascading failures across the entire system, making it unusable.

Circuit breaker pattern is one of the mechanisms used to achieve fault tolerance in microservices architecture. The circuit breaker pattern works by monitoring the calls to a service and when the service fails or experiences high latency, it opens the circuit and redirects the requests to a fallback mechanism, instead of allowing the requests to continue to the failing service. This reduces the load on the failing service and prevents cascading failures.

When the circuit breaker is in the open state, it periodically checks if the failing service is available again. If it detects that the service is functioning properly, it switches the circuit to the closed state, allowing the requests to flow through again. If the service continues to fail, the circuit remains open, and the requests continue to be redirected to the fallback mechanism.

The circuit breaker pattern is used in conjunction with other patterns and mechanisms such as load balancing and retries to achieve high availability and fault tolerance in microservices architecture.

![39cdd54-state_machine.jpg](%F0%9F%94%A5%20Micro-services%20Tutorial%20using%20Spring%20Boot%20in%20One%208fd3b97c33c24f918fb3c041329f2426/39cdd54-state_machine.jpg)

The CircuitBreaker is implemented via a finite state machine with three normal states: CLOSED, OPEN and HALF_OPEN and two special states  DISABLED and FORCED_OPEN.

The CircuitBreaker uses a sliding window to store and aggregate the outcome of calls. You can choose between a count-based sliding window and a time-based sliding window. The count-based sliding window aggregates the outcome of the last N calls. The time-based sliding window aggregates the outcome of the calls of the last N seconds.

### Implementing fault tolerance in USER-SERVICE

- We need to add dependencies for Actuator for health check, Aop for passing any matrix and resilience4j for spring boot 2 in `pom.xml` of whichever service we want to implement in [here we will implement in USER-SERVICE]
    
    ```xml
    		<dependency>
    			<groupId>org.springframework.boot</groupId>
    			<artifactId>spring-boot-starter-actuator</artifactId>
    		</dependency>
    		<dependency>
    			<groupId>org.springframework.boot</groupId>
    			<artifactId>spring-boot-starter-aop</artifactId>
    		</dependency>
    		<!-- https://mvnrepository.com/artifact/io.github.resilience4j/resilience4j-spring-boot2 -->
    		<dependency>
    		    <groupId>io.github.resilience4j</groupId>
    		    <artifactId>resilience4j-spring-boot2</artifactId>
    		    <version>2.0.2</version>
    		</dependency>
    ```
    

- Then we need to add the circuit breaker to the API where the service is dependent on another service
    
    ```java
    	@GetMapping("/get-users/{userId}")
    	@CircuitBreaker(name = "RATING-HOTEL-BREAKER", fallbackMethod = "ratingFallback")
    	public ResponseEntity<User> getSingleUser(@PathVariable Long userId) throws ResourceNotFoundException {
    		User user = userService.getUserById(userId);
    		return ResponseEntity.status(HttpStatus.OK).body(user);
    	}
    ```
    
    The **`@CircuitBreaker`**annotation in Spring Boot is used to create a circuit breaker on a method. In this case, the **`name`** attribute is used to specify the name of the circuit breaker, which is "RATING-HOTEL-BREAKER".
    
    The **`fallbackMethod`** attribute in the **`@CircuitBreaker`** annotation specifies the name of the method that will be executed if the circuit breaker is tripped due to errors or timeouts. In this example, the fallback method is **`ratingFallback`**.
    
    So, if the service call protected by this circuit breaker fails, the fallback method **`ratingFallback`** will be executed, which should ideally provide some default response or handle the error gracefully.
    
    Fallback method :
    
    ```java
    //Creating rating fallback method
    	public ResponseEntity<User> ratingFallback(@PathVariable Long userId, Exception exp) {
    		logger.info("Fallback method is executed due to the service has failed : ",exp.getMessage());
    		User user = User.builder()
    						.userName("Dummy Name")
    						.userEmail("dummy@gmail.com")
    						.userAbout("Thid is a dummy value as the service is down")
    						.build();
    		return ResponseEntity.status(HttpStatus.OK).body(user);
    	}
    ```
    
    `UserController.java`
    
    ```java
    @GetMapping("/get-users/{userId}")
    	@CircuitBreaker(name = "RATING-HOTEL-BREAKER", fallbackMethod = "ratingFallback")
    	public ResponseEntity<User> getSingleUser(@PathVariable Long userId) throws ResourceNotFoundException {
    		User user = userService.getUserById(userId);
    		return ResponseEntity.status(HttpStatus.OK).body(user);
    	}
    	
    	//Creating rating fallback method
    	public ResponseEntity<User> ratingFallback(@PathVariable Long userId, Exception exp) {
    		logger.info("Fallback method is executed due to the service has failed : ",exp.getMessage());
    		User user = User.builder()
    						.userName("Dummy Name")
    						.userEmail("dummy@gmail.com")
    						.userAbout("Thid is a dummy value as the service is down")
    						.build();
    		return ResponseEntity.status(HttpStatus.OK).body(user);
    	}
    ```
    
- Now in order to add the threshold and all we need to add some important configurations in the `[application.properties](http://application.properties)`
    
    
    ```yaml
    management.health.circuitbreakers.enabled=true
    management.endpoints.web.exposure.include=health
    management.endpoint.health.show-details=always
    ```
    
    - **`management.health.circuitbreakers.enabled=true`**: This property enables the circuit breaker health indicator. If a circuit breaker is open or closed, it will report as "DOWN" in the health endpoint.
    - **`management.endpoints.web.exposure.include=health`**: This property exposes the **`/health`** endpoint in the application's web endpoints. This allows you to monitor the health of the application using a web browser or monitoring tool.
    - **`management.endpoint.health.show-details=always`**: This property shows the details of the health endpoint in the response. If set to "always", it will always show the full details of the health check, including any additional details provided by custom health indicators.
    
    ```yaml
    #resilience4j 
    resilience4j.circuitbreaker.instances.RATING-HOTEL-BREAKER.register-health-indicator=true
    resilience4j.circuitbreaker.instances.RATING-HOTEL-BREAKER.event-consumer-buffer-size=10
    resilience4j.circuitbreaker.instances.RATING-HOTEL-BREAKER.failure-rate-threshold=50
    resilience4j.circuitbreaker.instances.RATING-HOTEL-BREAKER.minimum-number-of-calls=5
    resilience4j.circuitbreaker.instances.RATING-HOTEL-BREAKER.automatic-transition-from-open-to-half-open-enabled=true
    resilience4j.circuitbreaker.instances.RATING-HOTEL-BREAKER.wait-duration-in-open-state=6
    resilience4j.circuitbreaker.instances.RATING-HOTEL-BREAKER.permitted-number-of-calls-in-half-open-state=3
    resilience4j.circuitbreaker.instances.RATING-HOTEL-BREAKER.sliding-window-size=10
    resilience4j.circuitbreaker.instances.RATING-HOTEL-BREAKER.sliding-window-type=COUNT_BASED
    ```
    
    These configurations are related to the behavior of the Resilience4j Circuit Breaker instance named "RATING-HOTEL-BREAKER". Here's a brief explanation of what each configuration does:
    
    - **`resilience4j.circuitbreaker.instances.RATING-HOTEL-BREAKER.register-health-indicator=true`**: This configuration registers the Circuit Breaker instance as a health indicator in the Spring Boot application.
    - **`resilience4j.circuitbreaker.instances.RATING-HOTEL-BREAKER.event-consumer-buffer-size=10`**: This configuration sets the buffer size of the event consumer. The event consumer is responsible for recording all events related to the Circuit Breaker, such as successful or failed calls.
    - **`resilience4j.circuitbreaker.instances.RATING-HOTEL-BREAKER.failure-rate-threshold=50`**: This configuration sets the failure rate threshold, which is the percentage of failed calls that triggers the Circuit Breaker to open.
    - **`resilience4j.circuitbreaker.instances.RATING-HOTEL-BREAKER.minimum-number-of-calls=5`**: This configuration sets the minimum number of calls that must be made before the Circuit Breaker can open.
    - **`resilience4j.circuitbreaker.instances.RATING-HOTEL-BREAKER.automatic-transition-from-open-to-half-open-enabled=true`**: This configuration allows the Circuit Breaker to automatically transition from the open state to the half-open state after the specified wait duration.
    - **`resilience4j.circuitbreaker.instances.RATING-HOTEL-BREAKER.wait-duration-in-open-state=6`**: This configuration sets the wait duration in the open state, which is the amount of time the Circuit Breaker will stay in the open state before attempting to transition to the half-open state.
    - **`resilience4j.circuitbreaker.instances.RATING-HOTEL-BREAKER.permitted-number-of-calls-in-half-open-state=3`**: This configuration sets the number of permitted calls in the half-open state, which is the maximum number of calls that can be made before the Circuit Breaker transitions back to the open state if any of the calls fail.
    - **`resilience4j.circuitbreaker.instances.RATING-HOTEL-BREAKER.sliding-window-size=10`**: This configuration sets the sliding window size, which is the number of calls that are recorded in the sliding window.
    - **`resilience4j.circuitbreaker.instances.RATING-HOTEL-BREAKER.sliding-window-type=COUNT_BASED`**: This configuration sets the sliding window type, which can be either COUNT_BASED or TIME_BASED. COUNT_BASED means that the sliding window is based on the number of calls, while TIME_BASED means that the sliding window is based on time. In this case, the sliding window is based on the number of calls.
    
    Now to check weather the service is down or up and send dummy value set by fallback function using circuit breaker we need to do following :
    
    - Go to the service registry and click on the service which is dependent on other service here i.e USER-SERVICE
    - Can click its status link and type `/health` after  `/actuator/health`
    - This will show you the health check as per the configurations we have earlier in `[application.properties](http://application.properties)` just like below
        
        ![Screenshot from 2023-05-02 15-10-08.png](%F0%9F%94%A5%20Micro-services%20Tutorial%20using%20Spring%20Boot%20in%20One%208fd3b97c33c24f918fb3c041329f2426/Screenshot_from_2023-05-02_15-10-08.png)
        
    - Then perform an HTTP call to get the users here where in the USER-SERVICE is dependent on RATING-SERVICE
    - Then whenever the RATING-SERVICE is down it will give the dummy value we have set in fallback function above for that particular request just as below returned JSON shows
        - 
        
        ![Screenshot from 2023-05-02 15-13-53.png](%F0%9F%94%A5%20Micro-services%20Tutorial%20using%20Spring%20Boot%20in%20One%208fd3b97c33c24f918fb3c041329f2426/Screenshot_from_2023-05-02_15-13-53.png)
        
    
    ### Retry
    
    "Retry" is a resilience pattern used in software engineering to handle failures in distributed systems. It involves automatically retrying failed operations with the hope of succeeding in subsequent attempts. In this pattern, when an operation fails, it is retried a specified number of times with a delay between each attempt. The delay is intended to give the system time to recover before retrying the operation. The retries stop when the operation is successful, or when the maximum number of attempts has been reached. The Retry pattern can be used to improve the overall availability and reliability of a system.
    
    `@Retry` is an annotation provided by the Resilience4j library in Java, which allows developers to apply retry logic to a method or function that may fail due to various reasons such as network issues, temporary unavailability of services, or other transient errors. The @Retry annotation can be used to specify the number of times a method should be retried and the delay between each retry attempt. This annotation can be combined with other Resilience4j annotations such as `@CircuitBreaker` and `@RateLimiter` to provide a more robust and resilient system.
    
    To do so we have to add configs:
    
    ```yaml
    resilience4j.retry.instances.RATING-HOTEL-BREAKER.max-attempts=3
    resilience4j.retry.instances.RATING-HOTEL-BREAKER.wait-duration=5s
    ```
    
    The configuration **`resilience4j.retry.instances.RATING-HOTEL-BREAKER.max-attempts=3`** sets the maximum number of attempts to retry the failed operation for the circuit breaker instance named **`RATING-HOTEL-BREAKER`**. In this case, it is set to 3, which means that if the operation fails, it will be retried up to 3 times.
    
    The configuration **`resilience4j.retry.instances.RATING-HOTEL-BREAKER.wait-duration=5s`** sets the wait duration between retries for the circuit breaker instance named **`RATING-HOTEL-BREAKER`**. In this case, it is set to 5 seconds. This means that after a failed attempt, the retry operation will wait for 5 seconds before attempting again.
    
    And then we have to `@Retry` to the controller method
    
    ```java
    @GetMapping("/get-users/{userId}")
    	@Retry(name = "RATING-HOTEL-BREAKER", fallbackMethod = "ratingFallback")
    //	@CircuitBreaker(name = "RATING-HOTEL-BREAKER", fallbackMethod = "ratingFallback")
    	public ResponseEntity<User> getSingleUser(@PathVariable Long userId) throws ResourceNotFoundException {
    		logger.info("retrycount : {}", retryCount);
    		retryCount++;
    		User user = userService.getUserById(userId);
    		return ResponseEntity.status(HttpStatus.OK).body(user);
    	}
    ```
    
    We can call a different fallback method for retry and a different for circuit breaker
    

### Rate Limiter

A rate limiter is a mechanism used in microservices to limit the number of requests made to a service or an API in a given time period. This helps to prevent overwhelming a service with too many requests and ensures that the service remains responsive and available for all users. In Spring Boot, the Rate Limiter feature is provided by the Resilience4j library. The library provides an annotation-based approach to configure rate limiting for individual endpoints in a microservice application. It allows us to configure limits on the number of requests per second, per minute, or per hour, and also provides a fallback mechanism in case the rate limit is exceeded.

- We need to add the configs in `applicaion.properties`
    
    ```yaml
    resilience4j.ratelimiter.instances.userRateLimiter.limit-refresh-period=4s
    resilience4j.ratelimiter.instances.userRateLimiter.limit-for-period=2
    resilience4j.ratelimiter.instances.userRateLimiter.timeout-duration=0s
    ```
    
    - **`limit-refresh-period`**: the period at which the limit for the rate limiter should be refreshed. In this case, the limit will be refreshed every 4 seconds.
    - **`limit-for-period`**: the maximum number of calls that can be made during the refresh period. In this case, the limit is set to 2 calls per refresh period.
    - **`timeout-duration`**: the amount of time a call will wait for a permit from the rate limiter before timing out. In this case, the timeout is set to 0 seconds, meaning that calls will not wait for a permit and will either be immediately rejected or allowed through, depending on whether the limit has been reached.

- Then add `@RateLimiter`  annotation.
    
    ```java
    @GetMapping("/get-users/{userId}")
    	//@Retry(name = "RATING-HOTEL-RETRY-SERVICE", fallbackMethod = "ratingFallback")
    	//@CircuitBreaker(name = "RATING-HOTEL-BREAKER", fallbackMethod = "ratingFallback")
    	@RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingFallback")
    	public ResponseEntity<User> getSingleUser(@PathVariable Long userId) throws ResourceNotFoundException {
    		logger.info("retrycount : {}", retryCount);
    		retryCount++;
    		User user = userService.getUserById(userId);
    		return ResponseEntity.status(HttpStatus.OK).body(user);
    	}
    ```
    

- Then to test it we need to download Jmeter [refer this → [https://linuxhint.com/install_apache_jmeter_ubuntu/](https://linuxhint.com/install_apache_jmeter_ubuntu/)]
    - First go google and download the zip file refer: [https://jmeter.apache.org/download_jmeter.cgi](https://jmeter.apache.org/download_jmeter.cgi)
    - Then open extract it and open the bin in terminal and type command  `./jmeter` to run start ti Jmeter
    

## Spring Security with OKTA Auth

First of all to use OKTA Auth we need to create account on OKTA Auth website [https://dev-47078417-admin.okta.com/admin/dashboard](https://dev-47078417-admin.okta.com/admin/dashboard) 

- Create an application and then add the details and save it .
- Then we need to add people and groups
- Then we need to go to API and add the scope internal to access other internal services like USER-SERVICE, RATING-SERVICE.
- Then add a claim for the access token.
- Then as we need to implement the OKTA Auth at API gateway we need to configure it

### Implementing OKTA Auth at API Gateway

First we need to add the `OKTA` ,`Spring security` dependencies in `pom.xml`

```xml
			<dependency>
	      <groupId>org.springframework.boot</groupId>
	      <artifactId>spring-boot-starter-security</artifactId>
	    </dependency>
	    <dependency>
	      <groupId>com.okta.spring</groupId>
	      <artifactId>okta-spring-boot-starter</artifactId>
	      <version>2.1.6</version>
	    </dependency>
```

<aside>
💡 Always keep in mind that we have used `spring-cloud-api-gateway` and we know it is only applicable if we have webflux and netty dependencies refer the documentation we have mentioned above while implementing API gateway

</aside>

<aside>
❗ Spring Cloud Gateway requires the Netty runtime provided by Spring Boot and Spring Webflux. It does not work in a traditional Servlet Container or when built as a WAR.

</aside>

 But as we have already added the webflux dependency before we will not get the error

- Then we need to add the okta configs in `[application.properties](http://application.properties)`
    
    
    ```yaml
    okta.oauth2.issuer=https://dev-47078417.okta.com/oauth2/default
    okta.oauth2.audience=api://default
    okta.oauth2.client-id=0oa9di26pvs0eT1NU5d7
    okta.oauth2.client-secret=nEkMdMhdka7I50O55cup62wt7qaPxqC8p62xYd4y
    okta.oauth2.scopes=openid, profile, email, offline_access
    ```
    
    - **`okta.oauth2.issuer`** specifies the URL of the Okta Authorization Server.
    - **`okta.oauth2.audience`** specifies the unique identifier for the resource server.
    - **`okta.oauth2.client-id`** and **`okta.oauth2.client-secret`** provide the client ID and client secret of the application, which are used to authenticate the application with Okta.
    - **`okta.oauth2.scopes`** defines the set of OAuth2 scopes that the application is authorized to access. In this case, the scopes include **`openid`**, **`profile`**, **`email`**, and **`offline_access`**.
    - Refer this [[https://dev-47078417-admin.okta.com/admin/oauth2/as/aus9czk5u7VwU4QnP5d7#scopes](https://dev-47078417-admin.okta.com/admin/oauth2/as/aus9czk5u7VwU4QnP5d7#scopes)] for scopes.
    
- After that we need to create a class to add code in security filter chain so we’ll create a class `SecurityConfig` in package `com.api.gateaway.config`
    - 
        
        ```java
        package com.spring.api.gateway.config;
        
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
        import org.springframework.security.config.web.server.ServerHttpSecurity;
        import org.springframework.security.web.server.SecurityWebFilterChain;
        import org.springframework.web.reactive.config.EnableWebFlux;
        
        @Configuration
        @EnableWebFluxSecurity
        public class SecurityConfig {
        
        	@Bean
        	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        		httpSecurity
        					.authorizeExchange()
        					.anyExchange()
        					.authenticated()
        					.and()
        					.oauth2Client()
        					.and()
        					.oauth2ResourceServer()
        					.jwt();
        		
        		
        		return httpSecurity.build();
        	}
        }
        ```
        
        This is a method in a Spring Boot application that defines a SecurityWebFilterChain for handling security-related tasks. The method takes an instance of ServerHttpSecurity as a parameter, which is used to configure the security for the application.
        
        In this particular implementation, the method authorizes all exchanges with any endpoint in the application to be authenticated. The method also configures the application to use OAuth2 for both the client and resource server. Additionally, it sets up the application to use JWT for token verification and authentication. Finally, the method returns the SecurityWebFilterChain that has been built with the provided configuration.
        
        <aside>
        💡 The **`oauth2ResourceServer()`**
         method is a part of Spring Security's OAuth2 Resource Server configuration. When called, it configures the application to act as an OAuth2 Resource Server, which means that it can receive and verify access tokens from an OAuth2 Authorization Server.
        
        When a client application needs to access a protected resource on a Resource Server, it sends an access token to the Resource Server. The Resource Server then validates the token to ensure that it is a legitimate token issued by a trusted Authorization Server, and that the token grants the necessary authorization to access the requested resource.
        
        In the context of Spring Security, the **`oauth2ResourceServer()`** method is used to configure the Resource Server to validate and process OAuth2 access tokens. This method allows you to configure the Resource Server to accept access tokens in various formats (such as JWT or opaque tokens), and to specify the details of how the tokens should be validated.
        
        By configuring the Resource Server using the **`oauth2ResourceServer()`** method, you can ensure that your application's protected resources are only accessible to clients that have valid and authorized access tokens.
        
        </aside>
        
        <aside>
        💡 The **`oauth2Client()`** method is a part of Spring Security's OAuth2 Client configuration. When called, it configures the application to act as an OAuth2 Client, which means that it can initiate the OAuth2 authorization flow and obtain access tokens from an OAuth2 Authorization Server.
        
        In the context of this specific example, the **`oauth2Client()`** method might be used to configure the application to:
        
        - Define the client registration details, such as client ID and client secret, for communicating with the Authorization Server.
        - Define the redirect URIs where the Authorization Server should redirect the user after successful authentication and authorization.
        - Define the scopes that the application is requesting access to.
        - Configure the OAuth2 login process, such as specifying the login page or customizing the login form.
        
        Once the **`oauth2Client()`** method is called, you can chain other methods to further configure the OAuth2 client behavior, such as **`authorizationCodeGrant()`** or **`clientCredentialsGrant()`**, depending on the type of OAuth2 flow that you need to implement.
        
        </aside>
        
        <aside>
        💡 Since the APIGateway is responsible for handling the client-side OAuth2 authentication flow, you need to add the **`oauth2Client()`**
         configuration in the **`SecurityWebFilterChain`**
         of the APIGateway to ensure that the OAuth2 client is properly configured to interact with the OAuth2 server.
        
        </aside>
        
- Now we need to create a login controller in order to add login functionality
    - `AuthController`
        
        ```java
        package com.spring.api.gateway.controller;
        
        import java.util.List;
        import java.util.stream.Collectors;
        
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.security.core.annotation.AuthenticationPrincipal;
        import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
        import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
        import org.springframework.security.oauth2.core.oidc.user.OidcUser;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;
        
        import com.spring.api.gateway.models.AuthResponse;
        
        @RestController
        @RequestMapping("/auth")
        public class AuthController {
        
        	Logger logger = LoggerFactory.getLogger(AuthController.class);
        	
        	@GetMapping("/login")
        	public ResponseEntity<AuthResponse> login(
        			@RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient auth2AuthorizedClient,
        			@AuthenticationPrincipal OidcUser oidcUser,
        			Model model
        			){
        		logger.info("user email : {}",oidcUser.getEmail());
        		
        		List<String> authorities = oidcUser.getAuthorities().stream().map(grantAuthoritie -> {
        			return grantAuthoritie.getAuthority();
        		}).collect(Collectors.toList());
        		
        		//creating AuthResponse object and setting userId and accessToken
        		AuthResponse authResponse = AuthResponse
        												.builder()
        												.userId(oidcUser.getEmail())
        												.accessToken(auth2AuthorizedClient.getAccessToken().getTokenValue())
        												.refreshToken(auth2AuthorizedClient.getRefreshToken().getTokenValue())
        												.expireAt(auth2AuthorizedClient.getAccessToken().getExpiresAt().getEpochSecond())
        												.authorities(authorities)
        												.build();
        		
        		return ResponseEntity.status(HttpStatus.OK).body(authResponse);
        		
        	}
        	
        }
        ```
        
        This is a Java class named **`AuthController`** that contains a REST endpoint for user authentication. The endpoint is mapped to **`/auth/login`** and accepts GET requests. The method **`login`** has three parameters: **`OAuth2AuthorizedClient`**, **`OidcUser`**, and **`Model`**. The **`@RegisteredOAuth2AuthorizedClient`** annotation is used to retrieve the authorized client with the name "okta" and the **`@AuthenticationPrincipal`** annotation is used to retrieve the OIDC user object. The **`Model`** parameter is used to add attributes to the view.
        
        The **`login`** method retrieves the user's email and authorities from the OIDC user object. It then creates an **`AuthResponse`** object and sets the **`userId`**, **`accessToken`**, **`refreshToken`**, **`expireAt`**, and **`authorities`** fields using the values retrieved from the OIDC user and authorized client objects. Finally, the method returns a **`ResponseEntity`** with an HTTP status of **`OK`** and the **`AuthResponse`** object in the response body.
        
        `AuthResponse`
        
        ```java
        package com.spring.api.gateway.models;
        
        import java.util.Collection;
        
        import lombok.AllArgsConstructor;
        import lombok.Builder;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;
        
        @Builder
        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public class AuthResponse {
        
        	private String userId;
        	private String accessToken;
        	private String refreshToken;
        	private long expireAt;
        	private Collection<String> authorities;
        }
        ```
        
- Now test he login page first set the  the Sign-in redirect URLs  in OKTA auth website
    - i.e http://localhost:portnumber/login/oauth2/code/okta
- Then after setting paste the URL in chrome and test
- Login with the credentials you have in okta auth people and we will get an response that we have set and return from the `AuthController`
    - Then go to [http://localhost:8084/auth/login](http://localhost:8084/auth/login) login and then you should redirect to okta auth home page then again hit this URL you will get following
    
    ![Screenshot from 2023-05-03 16-21-58.png](%F0%9F%94%A5%20Micro-services%20Tutorial%20using%20Spring%20Boot%20in%20One%208fd3b97c33c24f918fb3c041329f2426/Screenshot_from_2023-05-03_16-21-58.png)
    

### Implementing OKTA auth security at each services

- ********************************************************************Implementing OKTA auth security at USER-SERVICE********************************************************************
    - First we need to add the `OKTA` ,`Spring security` and `OAuth2 Client`dependencies in `pom.xml`
        
        ```xml
        			<dependency>
        	      <groupId>org.springframework.boot</groupId>
        	      <artifactId>spring-boot-starter-oauth2-client</artifactId>
        	    </dependency>
        	    <dependency>
        	      <groupId>org.springframework.boot</groupId>
        	      <artifactId>spring-boot-starter-security</artifactId>
        	    </dependency>
        	    <dependency>
        	      <groupId>com.okta.spring</groupId>
        	      <artifactId>okta-spring-boot-starter</artifactId>
        	      <version>2.1.6</version>
        	    </dependency>
        ```
        
    
    - Then we need to add the Okta configs in `[application.properties](http://application.properties)`
        
        ```yaml
        okta.oauth2.issuer=https://dev-47078417.okta.com/oauth2/default
        okta.oauth2.audience=api://default
        okta.oauth2.client-id=0oa9di26pvs0eT1NU5d7
        okta.oauth2.client-secret=nEkMdMhdka7I50O55cup62wt7qaPxqC8p62xYd4y
        okta.oauth2.scopes=openid, profile, email, offline_access
        ```
        
        - **`okta.oauth2.issuer`** specifies the URL of the Okta Authorization Server.
        - **`okta.oauth2.audience`** specifies the unique identifier for the resource server.
        - **`okta.oauth2.client-id`** and **`okta.oauth2.client-secret`** provide the client ID and client secret of the application, which are used to authenticate the application with Okta.
        - **`okta.oauth2.scopes`** defines the set of OAuth2 scopes that the application is authorized to access. In this case, the scopes include **`openid`**, **`profile`**, **`email`**, and **`offline_access`**(That keeps us logged in).
        
    - Then we need to add the oauth2 client configs too
        
        
        ```yaml
        #security
        spring.security.oauth2.resourceserver.jwt.issuer-uri=https://dev-47078417.okta.com/oauth2/default
        spring.security.oauth2.client.registration.my-internal-client.provider=okta
        spring.security.oauth2.client.registration.my-internal-client.authorization-grant-type=client_credentials
        spring.security.oauth2.client.registration.my-internal-client.scope=internal
        spring.security.oauth2.client.registration.my-internal-client.client-id=0oa9di26pvs0eT1NU5d7
        spring.security.oauth2.client.registration.my-internal-client.client-secret=nEkMdMhdka7I50O55cup62wt7qaPxqC8p62xYd4y
        
        spring.security.oauth2.client.provider.okta.issuer-uri=https://dev-47078417.okta.com/oauth2/default
        ```
        
        The **`spring.security.oauth2.resourceserver.jwt.issuer-uri`** property specifies the issuer URI for JWT tokens issued by the Okta authorization server.
        
        The **`spring.security.oauth2.client.registration.my-internal-client`** properties configure a client registration for the resource server to use when authenticating with the authorization server. **`client-id`** and **`client-secret`** specify the credentials to use for the client, while **`scope`** specifies the requested scopes. **`authorization-grant-type`** specifies the OAuth2 grant type to use when acquiring tokens, which in this case is **`client_credentials`**.
        
        The **`spring.security.oauth2.client.provider.okta.issuer-uri`** property specifies the issuer URI for the Okta authorization server.
        
    - We need to create a configuration file in `config` package
        - `WebSecurityConfig`
            
            ```java
            package com.spring.micro.service.config;
            
            import org.springframework.context.annotation.Bean;
            import org.springframework.context.annotation.Configuration;
            import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
            import org.springframework.security.config.annotation.web.builders.HttpSecurity;
            import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
            import org.springframework.security.web.SecurityFilterChain;
            
            @Configuration
            @EnableWebSecurity
            @EnableGlobalMethodSecurity(prePostEnabled = true)
            public class WebSecurityConfig {
            
            	@Bean
            	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
            		
            		httpSecurity
            					.authorizeHttpRequests()
            					.anyRequest()
            					.authenticated()
            					.and()
            					.oauth2ResourceServer()
            					.jwt();
            		return httpSecurity.build();
            	}
            }
            ```
            
            Here we have not used `@EnableWebFluxSecurity`  instead we are using `@EnableWebSecurity`  as we haven’t used and webflux in here `USER-SERVICE` 
            
            This is a Java class annotated with **`@Configuration`**, **`@EnableWebSecurity`**, and **`@EnableGlobalMethodSecurity(prePostEnabled = true)`**. These annotations are used to configure Spring Security for a web application.
            
            **`@Configuration`** is a Spring annotation that marks the class as a configuration class, which means it provides configuration information to the Spring container.
            
            **`@EnableWebSecurity`** is a Spring Security annotation that enables Spring Security's web security features.
            
            **`@EnableGlobalMethodSecurity(prePostEnabled = true)`** is a Spring Security annotation that enables method-level security. This means that you can annotate your controller methods with **`@PreAuthorize`** and **`@PostAuthorize`** annotations to secure them.
            
            The **`WebSecurityConfig`** class has a method annotated with **`@Bean`** that returns a **`SecurityFilterChain`** object. This object defines the security configuration for the application.
            
            The **`filterChain()`** method configures Spring Security to require authentication for any request (**`anyRequest().authenticated()`**) and to use JWT tokens for authentication (**`oauth2ResourceServer().jwt()`**).**`oauth2ResourceServer()`** method is being used to configure the application to use JWT (JSON Web Token) as the format for the access tokens.
            
            <aside>
            💡 When you add the OAuth2 client configuration in the **`application.properties`** file of the USER service, Spring Boot automatically creates a **`OAuth2AuthorizedClientManager`** bean for you. This bean is then used by Spring Security's **`OAuth2LoginAuthenticationFilter`** and **`OAuth2ClientAuthenticationProcessingFilter`** to obtain the **`OAuth2AuthorizedClient`** for the logged-in user.
            
            Therefore, you do not need to manually create an **`OAuth2AuthorizedClientManager`** bean or add the **`oauth2Client()`** method in your **`SecurityConfig`** file as Spring Boot's auto-configuration takes care of it for you. The **`SecurityFilterChain`** bean that you have defined in your **`SecurityConfig`** file will use the **`OAuth2AuthorizedClientManager`** bean to obtain the **`OAuth2AuthorizedClient`** object for the authenticated user.
            
            </aside>
            
- ****************************Implementing OKTA auth security at RATING-SERVICE****************************
    - First we need to add the `OKTA` ,`Spring security` dependencies in `pom.xml`
        
        ```xml
        		<dependency>
        	      <groupId>org.springframework.boot</groupId>
        	      <artifactId>spring-boot-starter-security</artifactId>
        	    </dependency>
        	    <dependency>
        	      <groupId>com.okta.spring</groupId>
        	      <artifactId>okta-spring-boot-starter</artifactId>
        	      <version>2.1.6</version>
        	    </dependency>
        ```
        
    
    - Then we need to add the configs in `application.properties`
        
        ```yaml
        #okta configs for api gateway
        okta.oauth2.issuer=https://dev-47078417.okta.com/oauth2/default
        okta.oauth2.audience=api://default
        ```
        
    
    - Then we need to create `SecurityConfig` class in package `com.spring.micro.service.config`
        
        ```java
        package com.spring.micro.service.config;
        
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
        import org.springframework.security.config.annotation.web.builders.HttpSecurity;
        import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
        import org.springframework.security.web.SecurityFilterChain;
        
        @Configuration
        @EnableWebSecurity
        @EnableGlobalMethodSecurity(prePostEnabled = true)
        public class SecurityConfig {
        
        	@Bean
        	 public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        		 httpSecurity
        		 			.authorizeHttpRequests()
        		 			.anyRequest()
        		 			.authenticated()
        		 			.and()
        		 			.oauth2ResourceServer()
        		 			.jwt();
        		 
        		 return httpSecurity.build();
        	 }
        }
        ```
        
        This code defines a configuration class for Spring Security and sets up a security filter chain.
        
        - **`@Configuration`** indicates that this class is a configuration class.
        - **`@EnableWebSecurity`** enables Spring Security's web security support.
        - **`@EnableGlobalMethodSecurity(prePostEnabled = true)`** enables method-level security based on annotations. In this case, the **`@PreAuthorize`** annotation can be used to restrict access to methods based on the user's authority.
        - The **`filterChain()`** method creates a security filter chain. **`HttpSecurity`** is used to configure the security settings for HTTP requests. This code configures the security filter chain to require authentication for any request and to use JSON Web Tokens (JWTs) for authentication via the **`oauth2ResourceServer()`** method. **`jwt()`** indicates that JWTs will be used for authentication. The **`build()`** method returns the constructed **`SecurityFilterChain`**.
    
    - Then we can use `@PreAuthorize` and `@PostAuthorize`  annotations
        - `RatingController`
            
            ```java
            package com.spring.micro.service.controller;
            
            import java.util.List;
            
            import org.springframework.beans.factory.annotation.Autowired;
            import org.springframework.http.HttpStatus;
            import org.springframework.http.ResponseEntity;
            import org.springframework.security.access.prepost.PreAuthorize;
            import org.springframework.web.bind.annotation.GetMapping;
            import org.springframework.web.bind.annotation.PathVariable;
            import org.springframework.web.bind.annotation.PostMapping;
            import org.springframework.web.bind.annotation.PutMapping;
            import org.springframework.web.bind.annotation.RequestBody;
            import org.springframework.web.bind.annotation.RequestMapping;
            import org.springframework.web.bind.annotation.RestController;
            
            import com.spring.micro.service.entity.Rating;
            import com.spring.micro.service.services.RatingService;
            
            @RestController
            @RequestMapping("/ratings")
            public class RatingController {
            
            	@Autowired
            	private RatingService ratingService;
            	
            	@GetMapping("/")
            	public String ratings() {
            		return "ratings";
            	}
            	
            	@PreAuthorize("hasAuthority('Admin')")
            	@PostMapping("/create-rating")
            	public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
            		return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.createRatings(rating));
            	}
            	
            	//Update ratings
            	@PutMapping("/update-rating-by-ratingId/{ratingId}")
            	public ResponseEntity<Rating> updateRatingByRatingId(@PathVariable Long ratingId,@RequestBody Rating rating) {
            		return ResponseEntity.status(HttpStatus.OK).body(ratingService.updateRatingsByRatingId(ratingId, rating));
            	}
            	
            	@GetMapping("/get-all-ratings")
            	public ResponseEntity<List<Rating>> getAllRatings(){
            		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getAllRatings());
            	}
            	
            	@GetMapping("/get-ratings-by-ratingId/{ratingId}")
            	public ResponseEntity<List<Rating>> getRatingsByRatingId(@PathVariable Long ratingId ){
            		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingById(ratingId));
            	}
            	
            	@GetMapping("/hotels/{hotelId}")
            	public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable Long hotelId ){
            		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingByHotelId(hotelId));
            	}
            	
            	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
            	@GetMapping("/users/{userId}")
            	public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable Long userId ){
            		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingByUserId(userId));
            	}
            }
            ```
            
            **`@PreAuthorize`** is an annotation provided by Spring Security that is used to authorize access to a method or endpoint based on certain conditions. In this case, it is used to restrict access to the **`createRating`**and **`getRatingsByUserId`**methods to users who have the **`Admin`** authority or the authority, respectively. If a user does not have the required authority, they will not be authorized to access these methods and will receive a 403 (Forbidden) HTTP response.
            
            The **********`Admin`** role defining means adding people to the different groups i.e here `Admin` group
            
- ****************************Implementing OKTA auth security at HOTEL-SERVICE****************************
    - First we need to add the `OKTA` ,`Spring security` dependencies in `pom.xml`
        
        ```xml
        		<dependency>
        	      <groupId>org.springframework.boot</groupId>
        	      <artifactId>spring-boot-starter-security</artifactId>
        	    </dependency>
        	    <dependency>
        	      <groupId>com.okta.spring</groupId>
        	      <artifactId>okta-spring-boot-starter</artifactId>
        	      <version>2.1.6</version>
        	    </dependency>
        ```
        
    
    - Then we need to add the configs in `application.properties`
        
        ```yaml
        #okta configs for api gateway
        okta.oauth2.issuer=https://dev-47078417.okta.com/oauth2/default
        okta.oauth2.audience=api://default
        ```
        
    
    - Then we need to create `SecurityConfig` class in package `com.spring.micro.service.config`
        
        ```java
        package com.spring.micro.service.config;
        
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
        import org.springframework.security.config.annotation.web.builders.HttpSecurity;
        import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
        import org.springframework.security.web.SecurityFilterChain;
        
        @Configuration
        @EnableWebSecurity
        @EnableGlobalMethodSecurity(prePostEnabled = true)
        public class SecurityConfig {
        
        	@Bean
        	 public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        		 httpSecurity
        		 			.authorizeHttpRequests()
        		 			.anyRequest()
        		 			.authenticated()
        		 			.and()
        		 			.oauth2ResourceServer()
        		 			.jwt();
        		 
        		 return httpSecurity.build();
        	 }
        }
        ```
        
        This code defines a configuration class for Spring Security and sets up a security filter chain.
        
        - **`@Configuration`** indicates that this class is a configuration class.
        - **`@EnableWebSecurity`** enables Spring Security's web security support.
        - **`@EnableGlobalMethodSecurity(prePostEnabled = true)`** enables method-level security based on annotations. In this case, the **`@PreAuthorize`** annotation can be used to restrict access to methods based on the user's authority.
        - The **`filterChain()`** method creates a security filter chain. **`HttpSecurity`** is used to configure the security settings for HTTP requests. This code configures the security filter chain to require authentication for any request and to use JSON Web Tokens (JWTs) for authentication via the **`oauth2ResourceServer()`** method. **`jwt()`** indicates that JWTs will be used for authentication. The **`build()`** method returns the constructed **`SecurityFilterChain`**.
    
    - Then we can use `@PreAuthorize` and `@PostAuthorize`  annotations
        - `HotelService`
        
        ```java
        package com.spring.micro.service.controller;
        
        import java.util.List;
        
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.security.access.prepost.PreAuthorize;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;
        
        import com.spring.micro.service.entity.Hotel;
        import com.spring.micro.service.services.HotelService;
        
        @RestController
        @RequestMapping("/hotels")
        public class HotelController {
        
        	@Autowired
        	private HotelService hotelService;
        	
        	@GetMapping("/")
        	public String hotel() {
        		return "hotel";
        	}
        	
        	@PreAuthorize("hasAuthority('Admin')")
        	@PostMapping("/create-hotel")
        	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        		return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(hotel));
        	}
        	
        	@PreAuthorize("hasAuthority('SCOPE_internal')")
        	@GetMapping("/hotel-by-id/{hotelId}")
        	public ResponseEntity<Hotel> getHotelById(@PathVariable Long hotelId) {
        		return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotelById(hotelId));
        	}
        	
        	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
        	@GetMapping("/get-all-hotels")
        	public ResponseEntity<List<Hotel>> getAllHotels() {
        		return ResponseEntity.ok(hotelService.getAllHotels());
        	}
        }
        ```
        

### **Feign Client Interceptor**

- First we need to create a class `FeignClientInterceptor` in package `com.spring.micro.service.config.interceptor`
    
    
    ```java
    package com.spring.micro.service.config.interceptor;
    
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
    import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
    import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
    
    import feign.RequestInterceptor;
    import feign.RequestTemplate;
    
    public class FeignClientInterceptor implements RequestInterceptor{
    
    	@Autowired
    	private OAuth2AuthorizedClientManager auth2AuthorizedClientManager;
    	
    	@Override
    	public void apply(RequestTemplate template) {
    		String token = auth2AuthorizedClientManager
    												.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("my-internal-client").principal("internal").build())
    												.getAccessToken()
    												.getTokenValue();
    		
    		template.header("Authorization", "Bearer "+token);
    		
    	}
    
    }
    ```
    
    The **`FeignClientInterceptor`** class is a Spring component that implements the **`RequestInterceptor`** interface. This class intercepts outgoing HTTP requests made by a Feign client and applies an OAuth2 access token to the request header.
    
    <aside>
    💡  The **`RequestInterceptor`**interface is part of the Feign library and allows you to intercept requests before they are executed by the client.
    
    </aside>
    
    When an outgoing HTTP request is intercepted, the **`apply()`** method of the **`RequestInterceptor`** interface is called. In this implementation, the **`apply()`** method retrieves an access token using the **`OAuth2AuthorizedClientManager`**, which is a Spring Security component that manages authorized OAuth2 clients.
    
    The **`authorize()`** method of the **`OAuth2AuthorizedClientManager`** is called with an **`OAuth2AuthorizeRequest`** object that specifies the client registration ID and the principal (or user) associated with the access token. In this example, the client registration ID is "my-internal-client" and the principal is set to "internal".
    
    <aside>
    💡 In an OAuth2 client configuration, a client registration ID is used to identify a specific client application that has been registered with an authorization server. The client registration ID is typically defined in the application's configuration file or properties. As mentioned in [Click Here](https://www.notion.so/Micro-services-Tutorial-using-Spring-Boot-in-One-Video-8fd3b97c33c24f918fb3c041329f2426)
    
    </aside>
    
    <aside>
    💡 The principal in this context refers to the user or entity on whose behalf the client application is requesting access to protected resources. In this case, the principal is set to "internal", which means that the client is requesting access to internal resources.
    
    </aside>
    
    The **`getAccessToken()`** method is then called on the authorized client to retrieve the access token value. This value is appended to the request header with the key "Authorization" using the "Bearer" token type.
    
    Overall, this **`FeignClientInterceptor`** class provides a way to automatically apply OAuth2 access tokens to outgoing requests made by a Feign client, which helps to secure the communication between the client and the protected resource.
    
- Now in order to get the `OAuth2AuthorizedClientManager` object in `FeignCLientInceptor`  and `@Autowired`  it we need to create and return its object from configuration file in `ConfigClass` in package `com.spring.micro.service.config`
    - 
    
    ```java
    @Bean
    	public OAuth2AuthorizedClientManager manager(
    		ClientRegistrationRepository clientRegistrationRepository,
    		OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository
    			) {
    		//provider
    		OAuth2AuthorizedClientProvider auth2AuthorizedClientProvider = OAuth2AuthorizedClientProviderBuilder.builder().clientCredentials().build();
    		
    		DefaultOAuth2AuthorizedClientManager defaultAuth2AuthorizedClientManager = new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository, auth2AuthorizedClientRepository);
    		defaultAuth2AuthorizedClientManager.setAuthorizedClientProvider(auth2AuthorizedClientProvider);
    		return defaultAuth2AuthorizedClientManager;
    	}
    ```
    
    This is a Java Spring Bean configuration that creates and returns an instance of the OAuth2AuthorizedClientManager class. The manager is used to manage the authorized client credentials for an OAuth2 client.
    
    The manager takes two parameters: a client registration repository and an authorized client repository. The client registration repository stores the registered clients that the manager can use to obtain access tokens, while the authorized client repository stores the authorized client credentials.
    
    The code creates an instance of the OAuth2AuthorizedClientProviderBuilder class and calls the clientCredentials() method to create an instance of the OAuth2AuthorizedClientProvider class that uses the client credentials grant type.
    
    Finally, it creates an instance of the DefaultOAuth2AuthorizedClientManager class, sets the authorized client provider to the one created above, and returns the manager instance as a Spring Bean.
    

### Rest Template Interceptor

- First we need to create a class `RestTemplateInterceptor` in package `com.spring.micro.service.config.interceptor`
    
    ```java
    package com.spring.micro.service.config.interceptor;
    
    import java.io.IOException;
    
    import org.springframework.http.HttpRequest;
    import org.springframework.http.client.ClientHttpRequestExecution;
    import org.springframework.http.client.ClientHttpRequestInterceptor;
    import org.springframework.http.client.ClientHttpResponse;
    import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
    import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
    
    public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {
    	/*
    	 * The RestTemplate is a class provided by Spring Framework that is used to make
    	 * RESTful API calls. When making HTTP requests, it sends an HTTP request
    	 * message to a server and receives an HTTP response message.
    	 * 
    	 * The ClientHttpRequestInterceptor interface allows you to intercept and modify
    	 * HTTP requests and responses that are processed by the RestTemplate.
    	 * 
    	 * However, the RestTemplate does not intercept requests directly after
    	 * implementing ClientHttpRequestInterceptor. You need to create an instance of
    	 * the RestTemplateInterceptor class and add it to the list of interceptors for
    	 * the RestTemplate using the setInterceptors() method.
    	 * 
    	 * In the given code, a RestTemplate bean is created, and an instance of
    	 * RestTemplateInterceptor class is added to the list of interceptors for this
    	 * RestTemplate bean. This interceptor intercepts the HTTP requests and modifies
    	 * them before sending them to the server. The RestTemplate then sends these
    	 * modified HTTP requests to the server.
    	 */
    	
    	private OAuth2AuthorizedClientManager auth2AuthorizedClientManager;
    	
    	
    	public RestTemplateInterceptor(OAuth2AuthorizedClientManager auth2AuthorizedClientManager) {
    		this.auth2AuthorizedClientManager = auth2AuthorizedClientManager;
    	}
    
    	@Override
    	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
    			throws IOException {
    		String token =  auth2AuthorizedClientManager
    												.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("my-internal-client").principal("internal").build())
    												.getAccessToken()
    												.getTokenValue();
    		
    		request.getHeaders().add("Authorization", "Bearer "+token);
    		
    		
    		return execution.execute(request, body);
    	}
    
    }
    ```
    
- Now in `ConfigClass` as we have already created method for `RestTemlplate` now we’ll make changes
    - `restTemplate()` method
        
        ```java
        @Autowired
        	ClientRegistrationRepository clientRegistrationRepository;
        	
        	@Autowired
        	OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository;
        	
        	//this is the configuration for the RestTemplate with okta auth authorization
        	@Bean
        	@LoadBalanced //we use this annotation to use the service name instead of the host name or the ip-address
        	public RestTemplate restTemplate() {
        		
        		RestTemplate restTemplate = new RestTemplate();
        		
        		List<ClientHttpRequestInterceptor> inceptors =  new ArrayList<>();
        		inceptors.add(new RestTemplateInterceptor(manager(clientRegistrationRepository, auth2AuthorizedClientRepository)));
        		restTemplate.setInterceptors(inceptors);
        		
        		return restTemplate;
        	}
        ```
        
        This is a Java configuration code block that defines a Spring Bean of type RestTemplate. The RestTemplate is a class provided by Spring that makes it easy to interact with RESTful services over HTTP.
        
        This configuration block sets up a RestTemplate with OAuth2 authorization using the Okta authentication provider. It uses the @LoadBalanced annotation to allow the service name to be used instead of the hostname or IP address.
        
        The configuration block also defines a RestTemplateInterceptor that adds an OAuth2 authorization header to each request. The OAuth2AuthorizedClientManager is created by a manager() method defined elsewhere, which is passed the ClientRegistrationRepository and OAuth2AuthorizedClientRepository as arguments. These repositories provide information about the OAuth2 client registration and authorized clients, respectively.
        
        Finally, the RestTemplate is returned as a Spring Bean so it can be used elsewhere in the application by autowiring it.
        
        <aside>
        💡 The **`RestTemplate`** is a class provided by Spring Framework that is used to make RESTful API calls. When making HTTP requests, it sends an HTTP request message to a server and receives an HTTP response message.
        
        The **`ClientHttpRequestInterceptor`** interface allows you to intercept and modify HTTP requests and responses that are processed by the **`RestTemplate`**.
        
        However, the **`RestTemplate`** does not intercept requests directly after implementing **`ClientHttpRequestInterceptor`**. You need to create an instance of the **`RestTemplateInterceptor`** class and add it to the list of interceptors for the **`RestTemplate`** using the **`setInterceptors()`** method.
        
        In the given code, a **`RestTemplate`** bean is created, and an instance of **`RestTemplateInterceptor`** class is added to the list of interceptors for this **`RestTemplate`** bean. This interceptor intercepts the HTTP requests and modifies them before sending them to the server. The **`RestTemplate`** then sends these modified HTTP requests to the server.
        
        </aside>
        
    
    - `ConfigClass`
        
        ```java
        package com.spring.micro.service.config;
        
        import java.util.ArrayList;
        import java.util.List;
        
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.cloud.client.loadbalancer.LoadBalanced;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.http.client.ClientHttpRequestInterceptor;
        import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
        import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
        import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
        import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
        import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
        import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
        import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
        import org.springframework.web.client.RestTemplate;
        
        import com.spring.micro.service.config.interceptor.RestTemplateInterceptor;
        
        @Configuration
        public class ConfigClass {
        
        	//this is the configuration for the RestTemplate without okta auth authorization
        /*	@Bean
        	@LoadBalanced //we use this annotation to use the service name instead of the host name or the ip-address
        	public RestTemplate restTemplate() {
        		return new RestTemplate();
        	}
        */
        	@Autowired
        	ClientRegistrationRepository clientRegistrationRepository;
        	
        	@Autowired
        	OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository;
        	
        	//this is the configuration for the RestTemplate with okta auth authorization
        	@Bean
        	@LoadBalanced //we use this annotation to use the service name instead of the host name or the ip-address
        	public RestTemplate restTemplate() {
        		
        		RestTemplate restTemplate = new RestTemplate();
        		
        		List<ClientHttpRequestInterceptor> inceptors =  new ArrayList<>();
        		inceptors.add(new RestTemplateInterceptor(manager(clientRegistrationRepository, auth2AuthorizedClientRepository)));
        		restTemplate.setInterceptors(inceptors);
        		
        		return restTemplate;
        	}
        	
        	/*
        	 * This is a Java Spring Bean configuration that creates and returns an instance
        	 * of the OAuth2AuthorizedClientManager class. The manager is used to manage the
        	 * authorized client credentials for an OAuth2 client.
        	 * 
        	 * The manager takes two parameters: a client registration repository and an
        	 * authorized client repository. The client registration repository stores the
        	 * registered clients that the manager can use to obtain access tokens, while
        	 * the authorized client repository stores the authorized client credentials.
        	 * 
        	 * The code creates an instance of the OAuth2AuthorizedClientProviderBuilder
        	 * class and calls the clientCredentials() method to create an instance of the
        	 * OAuth2AuthorizedClientProvider class that uses the client credentials grant
        	 * type.
        	 * 
        	 * Finally, it creates an instance of the DefaultOAuth2AuthorizedClientManager
        	 * class, sets the authorized client provider to the one created above, and
        	 * returns the manager instance as a Spring Bean.
        	 */
        	@Bean
        	public OAuth2AuthorizedClientManager manager(
        		ClientRegistrationRepository clientRegistrationRepository,
        		OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository
        			) {
        		//provider
        		OAuth2AuthorizedClientProvider auth2AuthorizedClientProvider = OAuth2AuthorizedClientProviderBuilder.builder().clientCredentials().build();
        		
        		DefaultOAuth2AuthorizedClientManager defaultAuth2AuthorizedClientManager = new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository, auth2AuthorizedClientRepository);
        		defaultAuth2AuthorizedClientManager.setAuthorizedClientProvider(auth2AuthorizedClientProvider);
        		return defaultAuth2AuthorizedClientManager;
        	}
        }
        ```
        

<aside>
💡 If you are unable to access the services and the fallback method is beign called then first `printStackTrace()` the exception and if you are having the unauthorized access error the ⇒ go to OKTA website then →Applications → Click on your application → Got to General Setting by scrolling down → Check ✅ client credentials checkbox

</aside>

<aside>
💡 If we directly access the services like HOTEL-SERVICE through API Gateway we will be unable to access it as we haven’t defined the ‘internal’ scope for API Gateway but we are able to access the HOTEL-SERVICE or RATING-SERVICE as we have defined ‘internal’ scope for USER_SERVICE.

</aside>
