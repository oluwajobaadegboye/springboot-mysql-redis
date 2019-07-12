create table customer (
       id bigint not null auto_increment,
        address varchar(255),
        date_of_birth date,
        email varchar(255),
        first_name varchar(255),
        last_name varchar(255),
        phone varchar(255),
        primary key (id)
    ) engine=MyISAM


<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-redis</artifactId>
  <exclusions>
    <exclusion>
	 <groupId>io.lettuce</groupId>
	 <artifactId>lettuce-core</artifactId>
    </exclusion>
  </exclusions>
</dependency>
<dependency>
  <groupId>redis.clients</groupId>
  <artifactId>jedis</artifactId>
</dependency>