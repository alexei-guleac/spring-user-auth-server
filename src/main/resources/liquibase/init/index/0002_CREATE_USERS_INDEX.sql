----liquibase formatted sql
----changeset CREATE_INDEX:2

drop index if exists user_details_mail_index;
drop index if exists user_details_fname_index;
drop index if exists user_details_lname_index;
drop index if exists user_details_mail_fname_index;
drop index if exists user_details_mail_lname_index;
drop index if exists user_details_mail_fname_lname_index;



-- user_details table
CREATE INDEX user_details_mail_index ON user_details (email);
CREATE INDEX user_details_fname_index ON user_details (first_name);
CREATE INDEX user_details_lname_index ON user_details (last_name);

CREATE INDEX user_details_mail_fname_index ON user_details (email, first_name);
CREATE INDEX user_details_mail_lname_index ON user_details (email, last_name);

CREATE INDEX user_details_mail_fname_lname_index ON user_details (email, first_name, last_name);

