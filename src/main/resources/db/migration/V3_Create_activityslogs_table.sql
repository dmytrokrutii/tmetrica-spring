CREATE SEQUENCE if not exists activities_logs_id_seq;

CREATE TABLE activities_logs
(
    id          integer                     NOT NULL DEFAULT nextval('activities_logs_id_seq'::regclass),
    activity_id integer                     NOT NULL,
    user_id     integer                     NOT NULL,
    start_time  timestamp without time zone NOT NULL,
    end_time    timestamp without time zone,
    CONSTRAINT activities_logs_pkey PRIMARY KEY (id),
    CONSTRAINT fk_activities_l FOREIGN KEY (activity_id)
        REFERENCES public.activities (id) MATCH SIMPLE
        ON UPDATE  CASCADE
        ON DELETE  CASCADE,
    CONSTRAINT fk_user_l FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE activities_logs
    OWNER to postgres;

CREATE INDEX indx_activity
    ON public.activities_logs USING btree
        (activity_id)
    INCLUDE (user_id)
    TABLESPACE pg_default;

ALTER TABLE public.activities_logs
    CLUSTER ON indx_activity;