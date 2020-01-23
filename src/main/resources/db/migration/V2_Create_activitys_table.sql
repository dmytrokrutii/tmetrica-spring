CREATE TYPE public.status AS ENUM
    ('SUSPENDED', 'CLOSED', 'ACTIVE');

ALTER TYPE public.status
    OWNER TO postgres;

CREATE SEQUENCE if not exists activities_id_seq;

CREATE TABLE activities
(
    id         integer                                        NOT NULL DEFAULT nextval('activities_id_seq'::regclass),
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

ALTER TABLE activities
    OWNER to postgres;