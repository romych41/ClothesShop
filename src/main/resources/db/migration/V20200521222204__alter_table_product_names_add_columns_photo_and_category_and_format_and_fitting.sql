ALTER TABLE public.product_names
    ADD COLUMN photo character varying;

ALTER TABLE public.product_names
    ADD COLUMN category character varying;

ALTER TABLE public.product_names
    ADD COLUMN ph_format character varying;

ALTER TABLE public.product_names
    ADD COLUMN fitting boolean DEFAULT false;