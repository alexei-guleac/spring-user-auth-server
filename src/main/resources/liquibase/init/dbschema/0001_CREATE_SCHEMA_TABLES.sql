----liquibase formatted sql
----changeset CREATE_SCHEMA:1

create EXTENSION IF NOT EXISTS "uuid-ossp";

create TABLE user_details
(
    id                uuid                        DEFAULT uuid_generate_v4(),
    email             character varying(255)      NOT NULL,
    username          character varying(255),
    password          character varying(255)      NOT NULL,
    first_name        character varying(255)      NOT NULL,
    last_name         character varying(255)      NOT NULL,
    middle_name       character varying(255),
    birthday          date,
    create_datetime   timestamp                   without time zone NOT NULL,
    create_user_name  character varying(255)      NOT NULL,
    update_datetime   timestamp                   without time zone,
    update_user_name  character varying(255),
    deleted           boolean                     NOT NULL,
    active            boolean                     NOT NULL,
    CONSTRAINT        user_details_pkey PRIMARY KEY (id)
);


create TABLE user_role
(
    user_id   uuid    NOT NULL,
    role_id   bigint  NOT NULL
);

create TABLE roles
(
    id          bigint                  NOT NULL,
    role_name   character varying(255)  NOT NULL,
    CONSTRAINT  roles_pkey PRIMARY KEY (id)
);


create sequence roles_seq
    start with 1
    increment by 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

alter table ONLY roles
    alter COLUMN id SET DEFAULT nextval('roles_seq'::regclass);
