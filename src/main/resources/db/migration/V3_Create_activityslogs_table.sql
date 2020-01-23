CREATE SEQUENCE if not exists activities_logs_id_seq;

CREATE TABLE activities_logs
(
    id           integer                NOT NULL DEFAULT nextval('activities_logs_id_seq'::regclass),
    activity_id  integer                NOT NULL,
    user_id      integer                NOT NULL,
    opening_time time without time zone NOT NULL,
    closing_time time without time zone,
    CONSTRAINT activities_logs_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE activities_logs
    OWNER to postgres;