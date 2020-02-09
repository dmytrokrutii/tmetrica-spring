CREATE TABLE users_activities
(
    user_id       integer NOT NULL,
    activities_id integer NOT NULL,
    CONSTRAINT users_activities_pkey PRIMARY KEY (user_id, activities_id),
    CONSTRAINT fk_activities FOREIGN KEY (activities_id)
        REFERENCES public.activities (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_user FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.users_activities
    OWNER to postgres;

CREATE INDEX fki_fk_activities
    ON public.users_activities USING btree
        (activities_id)
    TABLESPACE pg_default;

CREATE INDEX fki_fk_user
    ON public.users_activities USING btree
        (user_id)
    TABLESPACE pg_default;