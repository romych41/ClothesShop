CREATE TABLE public.product_names
(
    id SERIAL,
    name character varying COLLATE pg_catalog."default",
    color character varying COLLATE pg_catalog."default",
    CONSTRAINT products_pkey1 PRIMARY KEY (id),
    CONSTRAINT product_names_name_color_key UNIQUE (name, color)

)

TABLESPACE pg_default;

ALTER TABLE public.product_names
    OWNER to postgres;