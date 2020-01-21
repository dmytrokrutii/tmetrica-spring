CREATE TABLE users_activitys
(
    user_id      integer NOT NULL,
    activitys_id integer NOT NULL,
    CONSTRAINT users_activitys_pkey PRIMARY KEY (user_id, activitys_id),
    CONSTRAINT fk_activitys FOREIGN KEY (activitys_id)
        REFERENCES public.activitys (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_user FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.users_activitys
    OWNER to postgres;

CREATE INDEX fki_fk_activitys
    ON public.users_activitys USING btree
        (activitys_id)
    TABLESPACE pg_default;

CREATE INDEX fki_fk_user
    ON public.users_activitys USING btree
        (user_id)
    TABLESPACE pg_default;