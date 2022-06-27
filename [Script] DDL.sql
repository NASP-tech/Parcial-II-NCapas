-- Table: Category

-- public.category definition

-- Drop table

-- DROP TABLE public.category;

CREATE TABLE public.category (
	"name" varchar NOT NULL,
	code varchar NOT NULL,
	CONSTRAINT category_pk PRIMARY KEY (code)
);

-- Table: Movie

-- public.movie definition

-- Drop table

-- DROP TABLE public.movie;

CREATE TABLE public.movie (
	code varchar NOT NULL,
	title varchar NOT NULL,
	length int4 NOT NULL,
	id_category varchar NULL,
	CONSTRAINT movie_pk PRIMARY KEY (code),
	CONSTRAINT movie_fk FOREIGN KEY (id_category) REFERENCES public.category(code) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Table: Schedule

-- public.schedule definition

-- Drop table

-- DROP TABLE public.schedule;

CREATE TABLE public.schedule (
	id serial4 NOT NULL,
	"timestamp" timestamp NOT NULL,
	price numeric NOT NULL,
	id_movie varchar NOT NULL,
	capacity int4 NOT NULL,
	CONSTRAINT schedule_pk PRIMARY KEY (id),
	CONSTRAINT schedule_fk FOREIGN KEY (id_movie) REFERENCES public.movie(code) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Table: Booking

-- public.booking definition

-- Drop table

-- DROP TABLE public.booking;

CREATE TABLE public.booking (
	id serial4 NOT NULL,
	"timestamp" timestamp NOT NULL,
	quantity int4 NOT NULL,
	id_user int4 NOT NULL,
	id_schedule int4 NOT NULL,
	CONSTRAINT booking_pk PRIMARY KEY (id),
	CONSTRAINT booking_fk_schedule FOREIGN KEY (id_schedule) REFERENCES public.schedule(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT booking_fk_user FOREIGN KEY (id_user) REFERENCES public."user"(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Table: User

-- public."user" definition

-- Drop table

-- DROP TABLE public."user";

CREATE TABLE public."user" (
	id serial4 NOT NULL,
	username varchar NOT NULL,
	email varchar NOT NULL,
	"password" varchar NOT NULL,
	CONSTRAINT user_pk PRIMARY KEY (id),
	CONSTRAINT user_unique_email UNIQUE (email),
	CONSTRAINT user_unique_username UNIQUE (username)
);