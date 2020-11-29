create table users(
id int primary key auto_increment ,
name varchar(50) not null,
email varchar(255) not null unique,
password varchar(255) not null,
created_at datetime
);

create table contacts(
id int primary key auto_increment ,
firstname varchar(55) not null,
lastname varchar(65),
email varchar(255) not null unique,
phone varchar(100) not null unique,
address varchar(55),
city varchar(65),
avatar varchar(55),
pincode varchar(65),
state varchar(65),
country varchar(55),
created_at datetime,
user_id int,
constraint fk_users foreign key (user_id) references users (id)

);