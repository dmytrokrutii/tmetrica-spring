CREATE TYPE public.status AS ENUM
    ('SUSPENDED', 'CLOSED', 'ACTIVE');

ALTER TYPE public.status
    OWNER TO postgres;

CREATE SEQUENCE activitys_id_seq;

CREATE TABLE activitys
(
    id         integer                                        NOT NULL DEFAULT nextval('activitys_id_seq'::regclass),
    name       character varying COLLATE pg_catalog."default" NOT NULL,
    start_time timestamp without time zone                         NOT NULL,
    end_time   timestamp without time zone,
    status     status                                         NOT NULL,
    CONSTRAINT activitys_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE activitys
    OWNER to postgres;