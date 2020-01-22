CREATE TYPE role AS ENUM
    ('ADMIN', 'USER');

ALTER TYPE public.role
    OWNER TO postgres;

CREATE SEQUENCE users_id_seq;

CREATE TABLE users
(
    id       integer                                        NOT NULL DEFAULT nextval('users_id_seq'::regclass),
    email    character varying COLLATE pg_catalog."default" NOT NULL,
    login    character varying COLLATE pg_catalog."default" NOT NULL,
    password character varying COLLATE pg_catalog."default" NOT NULL,
    name     character varying COLLATE pg_catalog."default",
    surname  character varying COLLATE pg_catalog."default",
    role      role                                          NOT NULL DEFAULT 'USER'::role,
    enabled  boolean                                        NOT NULL DEFAULT TRUE,
    CONSTRAINT users_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE users
    OWNER to postgres;

ALTER TABLE users
    ADD CONSTRAINT email UNIQUE (email)
        INCLUDE (login);