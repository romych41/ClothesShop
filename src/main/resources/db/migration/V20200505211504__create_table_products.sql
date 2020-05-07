CREATE TABLE public.products
(
    id serial,
    code bigint,
    name character varying,
    cost numeric,
    markup numeric,
    price numeric,
    amount integer,
    add_date timestamp without time zone,
    storage integer,
    amount_left integer,
    s_cost numeric,
    s_price numeric,
    color character varying,
    size character varying ,
    color_hex character varying,
    category character varying,
    photo character varying,
    PRIMARY KEY (id),
    UNIQUE (code)
);

ALTER TABLE public.products
    OWNER to postgres;