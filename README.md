# CSIS3275001

## Frontend
We are using React in frontend 
### Run frontend
```
cd frontend
npm install 
npm start
```

## Backend
We are using the following stack for backend
1. [Spring boot](https://spring.io/projects/spring-boot) as main backend framework
2. [Postgres](https://www.postgresql.org/) as main database
3. [Seaweedfs](https://github.com/chrislusf/seaweedfs) as an image storage
4. [Vultr](https://www.vultr.com/) as a hosting
5. [JWT](https://jwt.io/) as Auth framework
6. [Flyway](https://flywaydb.org/) as a database migration tool

### Run backend
You need to add three files into `srs/main/resources` folder
1. **database.properties** with the following content
    * spring.datasource.platform=postgres
    * spring.datasource.url=url to postgres
    * spring.datasource.username=postgres username
    * spring.datasource.password=postgres password
2. **jwt.properties** with the following content
    * jwt.secret=qwerty1234
    * jwt.expiration=1800
3. **weedfs.properties** with the following content
    * avatar.host=host with seaweedfs instance
    * avatar.port=seaweedfs master port

Run command
```
mvn spring-boot:run
```
