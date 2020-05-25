CREATE TABLE public.ordered_products
(
    id serial,
    order_id integer,
    product_id integer,
    amount integer,
    CONSTRAINT ordered_products_pkey PRIMARY KEY (id),
    CONSTRAINT ordered_products_order_id_fkey FOREIGN KEY (order_id)
        REFERENCES public.orders (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT ordered_products_product_id_fkey FOREIGN KEY (product_id)
        REFERENCES public.products (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.ordered_products
    OWNER to postgres;