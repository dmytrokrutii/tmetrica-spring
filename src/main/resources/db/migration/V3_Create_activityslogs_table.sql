CREATE SEQUENCE if not exists activitylogs_id_seq;

CREATE TABLE activitylogs
(
    id           integer                NOT NULL DEFAULT nextval('activitylogs_id_seq'::regclass),
    activity_id  integer                NOT NULL,
    user_id      integer                NOT NULL,
    opening_time time without time zone NOT NULL,
    closing_time time without time zone,
    CONSTRAINT activitylogs_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE activitylogs
    OWNER to postgres;