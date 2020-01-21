CREATE TYPE role AS ENUM
    ('ADMIN', 'USER');

ALTER TYPE public.role
    OWNER TO postgres;

CREATE SEQUENCE users_id_seq;

CREATE TABLE users
(
    id       integer                                        NOT NULL DEFAULT nextval('users_id_seq'::regclass),
    email    character varying COLLATE pg_catalog."default" NOT NULL,
    password character varying COLLATE pg_catalog."default" NOT NULL,
    name     character varying COLLATE pg_catalog."default" NOT NULL,
    surname  character varying COLLATE pg_catalog."default" NOT NULL,
    role     role                                           NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE users
    OWNER to postgres;