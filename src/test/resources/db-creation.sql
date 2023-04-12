CREATE SEQUENCE IF NOT EXISTS public.activity_details_pk_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.activity_details_pk_seq OWNER TO postgres;
CREATE SEQUENCE IF NOT EXISTS public.activity_pk_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1
    OWNED BY activity.activity_id;

ALTER SEQUENCE public.activity_pk_seq OWNER TO postgres;
CREATE SEQUENCE IF NOT EXISTS public.activity_type_pk_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1
    OWNED BY activity_type.activity_type_id;

ALTER SEQUENCE public.activity_type_pk_seq OWNER TO postgres;

CREATE SEQUENCE IF NOT EXISTS public.body_measurement_area_pk_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1
    OWNED BY body_measurement_area.body_measurement_area_id;

ALTER SEQUENCE public.body_measurement_area_pk_seq OWNER TO postgres;
CREATE SEQUENCE IF NOT EXISTS public.body_measurement_pk_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.body_measurement_pk_seq OWNER TO postgres;
CREATE SEQUENCE IF NOT EXISTS public.food_tracking_pk_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.food_tracking_pk_seq OWNER TO postgres;
CREATE SEQUENCE IF NOT EXISTS public.food_type_pk_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.food_type_pk_seq OWNER TO postgres;
CREATE SEQUENCE IF NOT EXISTS public.meal_ingredients_pk_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.meal_ingredients_pk_seq OWNER TO postgres;
CREATE SEQUENCE IF NOT EXISTS public.meal_tracking_pk_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.meal_tracking_pk_seq OWNER TO postgres;
CREATE SEQUENCE IF NOT EXISTS public.meal_type_pk_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.meal_type_pk_seq OWNER TO postgres;
CREATE SEQUENCE IF NOT EXISTS public.sleep_tracking_pk_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.sleep_tracking_pk_seq OWNER TO postgres;
CREATE SEQUENCE IF NOT EXISTS public.stored_meal_ingredients_pk_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.stored_meal_ingredients_pk_seq OWNER TO postgres;
CREATE SEQUENCE IF NOT EXISTS public.stored_meal_pk_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.stored_meal_pk_seq OWNER TO postgres;
CREATE SEQUENCE IF NOT EXISTS public.training_plan_pk_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.training_plan_pk_seq OWNER TO postgres;

CREATE SEQUENCE IF NOT EXISTS public.user_group_pk_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.user_group_pk_seq OWNER TO postgres;
CREATE SEQUENCE IF NOT EXISTS public.users_pk_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.users_pk_seq OWNER TO postgres;
CREATE SEQUENCE IF NOT EXISTS public.water_tracking_pk_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.water_tracking_pk_seq OWNER TO postgres;

CREATE SEQUENCE IF NOT EXISTS public.weight_tracking_pk_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
CREATE TABLE IF NOT EXISTS public.user_group
(
    user_group_id          smallint                                           NOT NULL DEFAULT nextval('user_group_pk_seq'::regclass),
    user_group_description character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT user_role_pkey PRIMARY KEY (user_group_id)
)
    TABLESPACE pg_default;


ALTER SEQUENCE public.weight_tracking_pk_seq OWNER TO postgres;
ALTER TABLE IF EXISTS public.user_group
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.users
(
    user_id            integer                                             NOT NULL DEFAULT nextval('users_pk_seq'::regclass),
    username           character varying(100) COLLATE pg_catalog."default" NOT NULL,
    forename           character varying(100) COLLATE pg_catalog."default" NOT NULL,
    surname            character varying(100) COLLATE pg_catalog."default" NOT NULL,
    password           character varying(300) COLLATE pg_catalog."default" NOT NULL,
    email              character varying(300) COLLATE pg_catalog."default" NOT NULL,
    last_updated       timestamp without time zone,
    created_date       timestamp without time zone                         NOT NULL,
    last_accessed_date timestamp without time zone                         NOT NULL,
    height             smallint                                            NOT NULL,
    user_group_id      smallint                                            NOT NULL,
    date_of_birth      date                                                NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (user_id),
    CONSTRAINT users_email_unique UNIQUE (email),
    CONSTRAINT users_username_unique UNIQUE (username),
    CONSTRAINT users_user_group_fk FOREIGN KEY (user_group_id)
        REFERENCES public.user_group (user_group_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;
CREATE TABLE IF NOT EXISTS public.activity_type
(
    activity_type_id          smallint                                            NOT NULL DEFAULT nextval('activity_type_pk_seq'::regclass),
    activity_type_description character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT activity_type_pkey PRIMARY KEY (activity_type_id),
    CONSTRAINT activity_type_description_unique UNIQUE (activity_type_description)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.activity_type
    OWNER to postgres;
CREATE TABLE IF NOT EXISTS public.activity
(
    activity_id      integer                NOT NULL DEFAULT nextval('activity_pk_seq'::regclass),
    activity_type_id smallint               NOT NULL,
    user_id          integer                NOT NULL,
    activity_date    date                   NOT NULL,
    distance         numeric(5, 2),
    time_taken       time without time zone NOT NULL,
    calorie_count    integer,
    notes            character varying(500) COLLATE pg_catalog."default",
    start_time       time without time zone NOT NULL,
    CONSTRAINT activity_pkey PRIMARY KEY (activity_id),
    CONSTRAINT activity_activity_type_id_fkey FOREIGN KEY (activity_type_id)
        REFERENCES public.activity_type (activity_type_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT activity_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.activity
    OWNER to postgres;
CREATE TABLE IF NOT EXISTS public.activity_details
(
    activity_details_id integer  NOT NULL DEFAULT nextval('activity_details_pk_seq'::regclass),
    activity_id         integer  NOT NULL,
    repetitions         smallint NOT NULL,
    weight              numeric(5, 2),
    number_of_sets      smallint NOT NULL,
    CONSTRAINT activity_details_pkey PRIMARY KEY (activity_details_id),
    CONSTRAINT activity_details_activity__id_fkey FOREIGN KEY (activity_id)
        REFERENCES public.activity (activity_id) MATCH FULL
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.activity_details
    OWNER to postgres;
CREATE TABLE IF NOT EXISTS public.body_measurement_area
(
    body_measurement_area_id          smallint                                            NOT NULL DEFAULT nextval('body_measurement_area_pk_seq'::regclass),
    body_measurement_area_description character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT body_measurement_area_pkey PRIMARY KEY (body_measurement_area_id),
    CONSTRAINT body_measurement_area_description_unique UNIQUE (body_measurement_area_description)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.body_measurement_area
    OWNER to postgres;
CREATE TABLE IF NOT EXISTS public.body_measurement
(
    body_measurement_id      integer  NOT NULL DEFAULT nextval('body_measurement_pk_seq'::regclass),
    body_measurement_area_id smallint NOT NULL,
    user_id                  integer  NOT NULL,
    measurement_date         date     NOT NULL,
    measurement_size         numeric(5, 2),
    CONSTRAINT body_measurement_pkey PRIMARY KEY (body_measurement_id),
    CONSTRAINT body_measurement_body_measurement_id_fk FOREIGN KEY (body_measurement_area_id)
        REFERENCES public.body_measurement_area (body_measurement_area_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT body_measurement_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.body_measurement
    OWNER to postgres;
CREATE TABLE IF NOT EXISTS public.food_type
(
    food_type_id       integer                                             NOT NULL DEFAULT nextval('food_type_pk_seq'::regclass),
    food_name          character varying(200) COLLATE pg_catalog."default" NOT NULL,
    serving_size       numeric(9, 4)                                       NOT NULL,
    calories           numeric(9, 4)                                       NOT NULL,
    total_carbohydrate numeric(9, 4),
    total_fat          numeric(9, 4),
    protein            numeric(9, 4),
    saturated_fat      numeric(9, 4),
    trans_fat          numeric(9, 4),
    cholesterol        numeric(9, 4),
    sodium             numeric(9, 4),
    potassium          numeric(9, 4),
    dietary_fibre      numeric(9, 4),
    sugars             numeric(9, 4),
    vitamin_a          numeric(9, 4),
    vitamin_c          numeric(9, 4),
    calcium            numeric(9, 4),
    iron               numeric(9, 4),
    CONSTRAINT food_type_pkey PRIMARY KEY (food_type_id),
    CONSTRAINT food_type_unique UNIQUE (food_name, serving_size)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.food_type
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.food_tracking
(
    food_tracking_id   integer NOT NULL DEFAULT nextval('food_tracking_pk_seq'::regclass),
    food_tracking_date date    NOT NULL,
    user_id            integer NOT NULL,
    CONSTRAINT food_tracking_pkey PRIMARY KEY (food_tracking_id),
    CONSTRAINT food_tracking_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.food_tracking
    OWNER to postgres;
CREATE TABLE IF NOT EXISTS public.meal_type
(
    meal_type_id          smallint                                            NOT NULL DEFAULT nextval('meal_type_pk_seq'::regclass),
    meal_type_description character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT meal_type_pkey PRIMARY KEY (meal_type_id),
    CONSTRAINT meal_type_description_unique UNIQUE (meal_type_description)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.meal_type
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.meal_tracking
(
    meal_tracking_id integer  NOT NULL DEFAULT nextval('meal_tracking_pk_seq'::regclass),
    food_tracking_id integer  NOT NULL,
    meal_type_id     smallint NOT NULL,
    CONSTRAINT meal_tracking_pkey PRIMARY KEY (meal_tracking_id),
    CONSTRAINT meal_tracking_food_tracking_fkey FOREIGN KEY (food_tracking_id)
        REFERENCES public.food_tracking (food_tracking_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT meal_tracking_meal_type_fkey FOREIGN KEY (meal_type_id)
        REFERENCES public.meal_type (meal_type_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.meal_tracking
    OWNER to postgres;
CREATE TABLE IF NOT EXISTS public.meal_ingredients
(
    meal_ingredients_id integer       NOT NULL DEFAULT nextval('meal_ingredients_pk_seq'::regclass),
    meal_tracking_id    integer       NOT NULL,
    food_type_id        integer       NOT NULL,
    servings            numeric(7, 2) NOT NULL,
    CONSTRAINT meal_ingredients_pkey PRIMARY KEY (meal_ingredients_id),
    CONSTRAINT meal_ingredients_meal_tracking_fkey FOREIGN KEY (meal_tracking_id)
        REFERENCES public.meal_tracking (meal_tracking_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT meal_ingredients_type_fkey FOREIGN KEY (food_type_id)
        REFERENCES public.food_type (food_type_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.meal_ingredients
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.stored_meal
(
    stored_meal_id   integer                                             NOT NULL DEFAULT nextval('stored_meal_pk_seq'::regclass),
    stored_meal_name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT stored_meal_pkey PRIMARY KEY (stored_meal_id),
    CONSTRAINT stored_meal_unique UNIQUE (stored_meal_name)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.stored_meal
    OWNER to postgres;
CREATE TABLE IF NOT EXISTS public.stored_meal_ingredients
(
    stored_meal_ingredients_id integer       NOT NULL DEFAULT nextval('stored_meal_ingredients_pk_seq'::regclass),
    stored_meal_id             integer       NOT NULL,
    food_type_id               integer       NOT NULL,
    servings                   numeric(7, 2) NOT NULL,
    CONSTRAINT stored_meal_ingredients_pkey PRIMARY KEY (stored_meal_ingredients_id),
    CONSTRAINT stored_meal_ingredients_unique UNIQUE (stored_meal_id, food_type_id),
    CONSTRAINT stored_meal_ingredients_stored_mea_fkey FOREIGN KEY (stored_meal_id)
        REFERENCES public.stored_meal (stored_meal_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT stored_meal_ingredients_type_fkey FOREIGN KEY (food_type_id)
        REFERENCES public.food_type (food_type_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.stored_meal_ingredients
    OWNER to postgres;
CREATE TABLE IF NOT EXISTS public.training_plan
(
    training_plan_id integer  NOT NULL DEFAULT nextval('training_plan_pk_seq'::regclass),
    activity_type_id smallint NOT NULL,
    user_id          integer  NOT NULL,
    activity_date    date     NOT NULL,
    distance         numeric(5, 2),
    notes            character varying(500) COLLATE pg_catalog."default",
    CONSTRAINT training_plan_pkey PRIMARY KEY (training_plan_id),
    CONSTRAINT training_plan_activity_type_id_fkey FOREIGN KEY (activity_type_id)
        REFERENCES public.activity_type (activity_type_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT training_plan_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.training_plan
    OWNER to postgres;
CREATE TABLE IF NOT EXISTS public.water_tracking
(
    water_tracking_id integer NOT NULL DEFAULT nextval('water_tracking_pk_seq'::regclass),
    user_id           integer NOT NULL,
    water             integer NOT NULL,
    recorded_date     date    NOT NULL,
    CONSTRAINT water_tracking_pkey PRIMARY KEY (water_tracking_id),
    CONSTRAINT water_tracking_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.water_tracking
    OWNER to postgres;
CREATE TABLE IF NOT EXISTS public.weight_tracking
(
    weight_tracking_id integer       NOT NULL DEFAULT nextval('weight_tracking_pk_seq'::regclass),
    user_id            integer       NOT NULL,
    weight             numeric(5, 2) NOT NULL,
    skeletal_muscle    numeric(5, 2),
    fat_mass           numeric(5, 2),
    body_fat           numeric(5, 2),
    body_water         numeric(5, 2),
    recorded_date      date          NOT NULL,
    CONSTRAINT weight_tracking_pkey PRIMARY KEY (weight_tracking_id),
    CONSTRAINT weight_tracking_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.weight_tracking
    OWNER to postgres;