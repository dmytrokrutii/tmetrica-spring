CREATE
    TYPE public.order_status AS ENUM
    ('ACCEPTED', 'REJECTED', 'PENDING');

ALTER
    TYPE public.order_status
    OWNER TO postgres;

CREATE
    TYPE public.action AS ENUM
    ('CREATE', 'JOIN', 'DELETE');

ALTER
    TYPE public.order_status
    OWNER TO postgres;
CREATE
    SEQUENCE if not exists orders_id_seq;

CREATE TABLE public.orders
(
    id          integer      NOT NULL DEFAULT nextval('orders_id_seq'::regclass),
    action      action       NOT NULL,
    activity_id integer      NOT NULL,
    user_id     integer      NOT NULL,
    status      order_status NOT NULL,
    CONSTRAINT orders_pkey PRIMARY KEY (id),
    CONSTRAINT fk_activity FOREIGN KEY (activity_id)
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

ALTER TABLE public.orders
    OWNER to postgres;

CREATE INDEX fki_fk_activity
    ON public.orders USING btree
        (activity_id)
    TABLESPACE pg_default;