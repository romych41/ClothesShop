CREATE TABLE public.orders
(
    id serial,
    "number" integer,
    customer_id integer,
    status character varying COLLATE pg_catalog."default",
    delivery_id integer,
    address character varying COLLATE pg_catalog."default",
    order_date timestamp without time zone,
    free_shipping boolean,
    CONSTRAINT orders_pkey PRIMARY KEY (id),
    CONSTRAINT orders_customer_id_fkey FOREIGN KEY (customer_id)
        REFERENCES public.customers (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT orders_delivery_id_fkey FOREIGN KEY (delivery_id)
        REFERENCES public.delivery_options (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.orders
    OWNER to postgres;