CREATE TABLE public.user_roles
(
    user_id integer NOT NULL,
    role role NOT NULL,
    CONSTRAINT fk_users_id_role FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.user_roles
    OWNER to postgres;

-- Index: fki_fk_users_id_role

-- DROP INDEX public.fki_fk_users_id_role;

CREATE INDEX fki_fk_users_id_role
    ON public.user_roles USING btree
    (user_id)
    TABLESPACE pg_default;