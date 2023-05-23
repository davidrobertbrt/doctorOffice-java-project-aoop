--
-- PostgreSQL database dump
--

-- Dumped from database version 14.8
-- Dumped by pg_dump version 14.8

-- Started on 2023-05-23 16:29:33

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 836 (class 1247 OID 16428)
-- Name: specialization; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.specialization AS ENUM (
    'CARDIOLOGIE',
    'DERMATOLOGIE',
    'ORTOPEDIE',
    'GINECOLOGIE',
    'NEUROLOGIE',
    'PEDIATRIE',
    'PSIHIATRIE'
);


ALTER TYPE public.specialization OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 214 (class 1259 OID 16457)
-- Name: appointments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.appointments (
    id uuid NOT NULL,
    appointmentdate timestamp without time zone NOT NULL,
    pacient_id uuid NOT NULL,
    medic_id uuid NOT NULL
);


ALTER TABLE public.appointments OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 16412)
-- Name: audit; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.audit (
    id integer NOT NULL,
    name_action character varying(1024) NOT NULL,
    datetime_action timestamp without time zone NOT NULL
);


ALTER TABLE public.audit OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 16411)
-- Name: audit_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.audit_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.audit_id_seq OWNER TO postgres;

--
-- TOC entry 3362 (class 0 OID 0)
-- Dependencies: 209
-- Name: audit_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.audit_id_seq OWNED BY public.audit.id;


--
-- TOC entry 212 (class 1259 OID 16443)
-- Name: medics; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.medics (
    id uuid NOT NULL,
    firstname character varying(1024) NOT NULL,
    lastname character varying(1024) NOT NULL,
    specialization public.specialization NOT NULL,
    phonenumber character varying(16) NOT NULL
);


ALTER TABLE public.medics OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16420)
-- Name: pacients; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pacients (
    id uuid NOT NULL,
    firstname character varying(1024) NOT NULL,
    lastname character varying(1024) NOT NULL,
    phonenumber character varying(16) NOT NULL,
    address character varying(1024) NOT NULL,
    dateofbirth date NOT NULL
);


ALTER TABLE public.pacients OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16468)
-- Name: pills; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pills (
    id integer NOT NULL,
    prescription_id uuid NOT NULL,
    pill_name character varying(2048) NOT NULL,
    quantity integer DEFAULT 1 NOT NULL
);


ALTER TABLE public.pills OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16467)
-- Name: pills_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pills_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pills_id_seq OWNER TO postgres;

--
-- TOC entry 3363 (class 0 OID 0)
-- Dependencies: 216
-- Name: pills_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pills_id_seq OWNED BY public.pills.id;


--
-- TOC entry 215 (class 1259 OID 16462)
-- Name: prescriptions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.prescriptions (
    id uuid NOT NULL,
    pacient_id uuid NOT NULL,
    dateofuse date NOT NULL
);


ALTER TABLE public.prescriptions OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 16450)
-- Name: reports; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reports (
    id uuid NOT NULL,
    pacient_id uuid NOT NULL,
    medic_id uuid NOT NULL,
    description character varying(5028) NOT NULL,
    dateofwrite date NOT NULL
);


ALTER TABLE public.reports OWNER TO postgres;

--
-- TOC entry 3192 (class 2604 OID 16415)
-- Name: audit id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.audit ALTER COLUMN id SET DEFAULT nextval('public.audit_id_seq'::regclass);


--
-- TOC entry 3193 (class 2604 OID 16471)
-- Name: pills id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pills ALTER COLUMN id SET DEFAULT nextval('public.pills_id_seq'::regclass);


--
-- TOC entry 3353 (class 0 OID 16457)
-- Dependencies: 214
-- Data for Name: appointments; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.appointments (id, appointmentdate, pacient_id, medic_id) FROM stdin;
d00e1c41-849c-496d-b1d0-66e8cc6d9259	2023-05-21 20:26:38.608	5f18d265-83ca-4b6d-a4f6-07714b4849d3	7e7b881a-b860-43f8-abbc-dc6bc0cb8e67
\.


--
-- TOC entry 3349 (class 0 OID 16412)
-- Dependencies: 210
-- Data for Name: audit; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.audit (id, name_action, datetime_action) FROM stdin;
1	Test1	2023-05-10 12:45:00
2	Test2	2023-05-11 12:45:00
3	Test3	2023-05-20 22:15:36.27
4	load_pacient	2023-05-23 15:37:39.065
5	load_medic	2023-05-23 15:37:39.085
6	load_prescription	2023-05-23 15:37:39.092
7	load_report	2023-05-23 15:37:39.095
8	load_appointment	2023-05-23 15:37:39.095
\.


--
-- TOC entry 3351 (class 0 OID 16443)
-- Dependencies: 212
-- Data for Name: medics; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.medics (id, firstname, lastname, specialization, phonenumber) FROM stdin;
7e7b881a-b860-43f8-abbc-dc6bc0cb8e67	John	Doe	CARDIOLOGIE	0712345678
\.


--
-- TOC entry 3350 (class 0 OID 16420)
-- Dependencies: 211
-- Data for Name: pacients; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pacients (id, firstname, lastname, phonenumber, address, dateofbirth) FROM stdin;
5f18d265-83ca-4b6d-a4f6-07714b4849d3	Jane	Doe	0712345678	str. Alexandreiescu Mihail nr.5	2002-06-03
168c6682-407a-4960-99c4-878b6dcde8dc	Mihail	Andries	07111111111	str. Exemplu nr. 3	2000-03-03
929cecaa-970b-4928-91bf-7a1dc0ed5809	Ion	Popescu	0711111111	str.Exemplu	2000-05-05
\.


--
-- TOC entry 3356 (class 0 OID 16468)
-- Dependencies: 217
-- Data for Name: pills; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pills (id, prescription_id, pill_name, quantity) FROM stdin;
1	617a5f84-fc7d-4a9d-b1e3-0921fafc9d13	Strepsils	1
2	617a5f84-fc7d-4a9d-b1e3-0921fafc9d13	Paracetamol	2
3	617a5f84-fc7d-4a9d-b1e3-0921fafc9d13	Aerius	2
\.


--
-- TOC entry 3354 (class 0 OID 16462)
-- Dependencies: 215
-- Data for Name: prescriptions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.prescriptions (id, pacient_id, dateofuse) FROM stdin;
617a5f84-fc7d-4a9d-b1e3-0921fafc9d13	929cecaa-970b-4928-91bf-7a1dc0ed5809	2023-05-05
\.


--
-- TOC entry 3352 (class 0 OID 16450)
-- Dependencies: 213
-- Data for Name: reports; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.reports (id, pacient_id, medic_id, description, dateofwrite) FROM stdin;
9d8a5fe9-4ded-4c64-bb75-895b5698fe45	5f18d265-83ca-4b6d-a4f6-07714b4849d3	7e7b881a-b860-43f8-abbc-dc6bc0cb8e67	Raceala sezoniera	2023-05-23
\.


--
-- TOC entry 3364 (class 0 OID 0)
-- Dependencies: 209
-- Name: audit_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.audit_id_seq', 8, true);


--
-- TOC entry 3365 (class 0 OID 0)
-- Dependencies: 216
-- Name: pills_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pills_id_seq', 3, true);


--
-- TOC entry 3204 (class 2606 OID 16461)
-- Name: appointments appointments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT appointments_pkey PRIMARY KEY (id);


--
-- TOC entry 3196 (class 2606 OID 16419)
-- Name: audit audit_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.audit
    ADD CONSTRAINT audit_pkey PRIMARY KEY (id);


--
-- TOC entry 3200 (class 2606 OID 16449)
-- Name: medics medic_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medics
    ADD CONSTRAINT medic_pkey PRIMARY KEY (id);


--
-- TOC entry 3198 (class 2606 OID 16426)
-- Name: pacients pacients_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pacients
    ADD CONSTRAINT pacients_pkey PRIMARY KEY (id);


--
-- TOC entry 3208 (class 2606 OID 16476)
-- Name: pills pills_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pills
    ADD CONSTRAINT pills_pkey PRIMARY KEY (id);


--
-- TOC entry 3206 (class 2606 OID 16466)
-- Name: prescriptions prescriptions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prescriptions
    ADD CONSTRAINT prescriptions_pkey PRIMARY KEY (id);


--
-- TOC entry 3202 (class 2606 OID 16456)
-- Name: reports reports_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reports
    ADD CONSTRAINT reports_pkey PRIMARY KEY (id);


-- Completed on 2023-05-23 16:29:33

--
-- PostgreSQL database dump complete
--
