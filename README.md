# Warehouse management system

#### This is a spring-boot application creates a new DB on H2 with a name 'ikea_warehouse'.
    Note: if you need to change DB setting, edit the resources/application.properties file.
    Note: I used here H2 just for demo purpose not for production code, since it has not been desinged to be scalable.

---

Steps to run it
## 1. Build the project using maven.

	mvn clean package
	
## 2. Run the project.  
  
    java -jar target/warehouse-0.0.1-SNAPSHOT.jar

#### 3. Check the APIs on swagger: 
http://localhost:8090/swagger-ui.html

#### 4. Check H2 DB on: 
http://localhost:8090/h2/

--- 
### As a TODO work:
* I'd add Flyway to make the migration process of DB mor easier.
* dockerize the application itself and the db (after migrating to MySQL/PostgreSQL).
* add more Tests to cover as much as possible to make sure everything works as expected.
* add price property to the product entity.
* add multiple supplier/s for each article.
* add location/s for each product.  
* add more APIs to control every piece of data in the DB.
* add logs with different levels.
