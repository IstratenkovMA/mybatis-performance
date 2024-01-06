-- public.customer definition

-- Drop table

-- DROP TABLE public.customer;

CREATE TABLE public.customer (
                                 customer_id int8 NOT NULL,
                                 customer_balance numeric(38, 2) NULL,
                                 customer_created_at timestamptz(6) NULL,
                                 customer_type varchar(255) NULL,
                                 customer_is_active bool NULL,
                                 customer_name varchar(255) NULL,
                                 customer_phone varchar(255) NULL,
                                 customer_phone_type int2 NULL,
                                 customer_updated_at timestamptz(6) NULL,
                                 customer_version int4 NULL,
                                 CONSTRAINT customer_pkey PRIMARY KEY (customer_id)
);

-- public.customer_seq definition

-- DROP SEQUENCE public.customer_seq;

CREATE SEQUENCE public.customer_seq;