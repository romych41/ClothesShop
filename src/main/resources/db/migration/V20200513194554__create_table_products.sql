CREATE TABLE public.products
(
    id SERIAL,
    product_name_id integer,
    size character varying COLLATE pg_catalog."default",
    price numeric,
    CONSTRAINT product_sizes_pkey PRIMARY KEY (id),
    CONSTRAINT product_sizes_product_id_size_key UNIQUE (product_name_id, size)
,
    CONSTRAINT product_sizes_product_id_fkey FOREIGN KEY (product_name_id)
        REFERENCES public.product_names (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.products
    OWNER to postgres;