CREATE TABLE public.customers
(
    id serial,
    full_name character varying COLLATE pg_catalog."default",
    phone character varying COLLATE pg_catalog."default",
    user_id integer
)

TABLESPACE pg_default;

ALTER TABLE public.customers
    OWNER to postgres;