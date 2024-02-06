drop table if exists sec_user cascade 
create table sec_user (password varchar(255), secret_question varchar(255), userid varchar(255) not null, username varchar(255), primary key (userid))
