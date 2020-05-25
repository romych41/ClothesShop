CREATE TABLE public.delivery_options
(
    id serial,
    description character varying COLLATE pg_catalog."default",
    CONSTRAINT delivery_options_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.delivery_options
    OWNER to postgres;