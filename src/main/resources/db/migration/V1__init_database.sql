CREATE
EXTENSION IF NOT EXISTS pgcrypto;

CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";


insert into "role" (id, role_name, role_code, status, is_deleted, create_date, create_by, last_modified_date, last_modified_by)
values
('623a4cb3-6789-43b2-b91d-df7d8bfad79d', 'SUPPER ADMIN', 'supper_admin', 'ACTIVE', false, NOW(),'ADMIN', NOW(), 'ADMIN'),
('9fcb42b1-e4d7-44c3-af8c-8e0974e34d8c', 'ADMIN', 'admin', 'ACTIVE', false, NOW(),'ADMIN', NOW(), 'ADMIN'),
('afc46184-6a06-41a8-b72b-9d1cc29e5f97', 'USER', 'user', 'ACTIVE', false, NOW(),'ADMIN', NOW(), 'ADMIN');

insert into "permission" (id, permisison_code , description ,create_date, create_by, last_modified_date, last_modified_by)
values
('8b29bf8b-b803-4ce9-b365-9964e4e1feb1', 'supper_admin', 'No description', NOW(),'ADMIN', NOW(), 'ADMIN'),

('14a3dd57-a97b-4763-a80f-d7d40eac4413', 'admin_read', 'No description', NOW(),'ADMIN', NOW(), 'ADMIN'),
('aabe4b73-d76f-4770-b1b2-8f707d4a0121', 'admin_write', 'No description', NOW(),'ADMIN', NOW(), 'ADMIN'),
('c9f1c0f1-d763-4053-9303-a23e229f3067', 'admin_update', 'No description', NOW(),'ADMIN', NOW(), 'ADMIN'),
('a336b668-811a-4dcb-a445-8c26aa31efd1', 'admin_delete', 'No description', NOW(),'ADMIN', NOW(), 'ADMIN'),

('e36268ab-bd0f-443c-a2bb-bdc44360af2a', 'user_read', 'No description', NOW(),'ADMIN', NOW(), 'ADMIN'),
('08a92e17-4789-422c-abd4-979f19be8eac', 'user_write', 'No description', NOW(),'ADMIN', NOW(), 'ADMIN'),
('03097ddf-e563-4e2f-b0f6-cea58887d101', 'user_update', 'No description', NOW(),'ADMIN', NOW(), 'ADMIN'),
('c9211703-15a1-47f4-a114-da0fff75b9b8', 'user_delete', 'No description', NOW(),'ADMIN', NOW(), 'ADMIN');


insert into "permisison_role" (id, role_id, permisison_id, permisison_code)
values
	('b7fbcbc3-26f5-4534-8173-f3b360cace26', '623a4cb3-6789-43b2-b91d-df7d8bfad79d', '8b29bf8b-b803-4ce9-b365-9964e4e1feb1', 'supper_admin'),

	('38da11d6-46b0-4965-bce7-bae314897d46', '9fcb42b1-e4d7-44c3-af8c-8e0974e34d8c', '14a3dd57-a97b-4763-a80f-d7d40eac4413', 'admin_read'),
	('b9bc97cc-744a-4af2-a728-6f50ebb2836f', '9fcb42b1-e4d7-44c3-af8c-8e0974e34d8c', 'aabe4b73-d76f-4770-b1b2-8f707d4a0121', 'admin_write'),
	('27225cdc-1f13-4e54-8ea3-73fe514a855c', '9fcb42b1-e4d7-44c3-af8c-8e0974e34d8c', 'c9f1c0f1-d763-4053-9303-a23e229f3067', 'admin_update'),
	('80d02ad7-59fa-4b2d-913d-b6a3f145bfd7', '9fcb42b1-e4d7-44c3-af8c-8e0974e34d8c', 'a336b668-811a-4dcb-a445-8c26aa31efd1', 'admin_delete'),

	('977ff842-2385-45cf-ab90-8085665dd792', 'afc46184-6a06-41a8-b72b-9d1cc29e5f97', 'e36268ab-bd0f-443c-a2bb-bdc44360af2a', 'user_read'),
	('ab717059-d66a-4ce8-85e0-294bd292816e', 'afc46184-6a06-41a8-b72b-9d1cc29e5f97', '08a92e17-4789-422c-abd4-979f19be8eac', 'user_write'),
	('f6003c6d-8b54-4452-ba92-7bd22ceff193', 'afc46184-6a06-41a8-b72b-9d1cc29e5f97', '03097ddf-e563-4e2f-b0f6-cea58887d101', 'user_update'),
	('17efcc65-f5f5-493f-920e-5c3255febf9c', 'afc46184-6a06-41a8-b72b-9d1cc29e5f97', 'c9211703-15a1-47f4-a114-da0fff75b9b8', 'user_delete');

insert into "user" (id, email, date_of_birth, avatar, "password", status, is_deleted, create_date, create_by, last_modified_date, last_modified_by)
values
    ('c2a68b61-fc46-4d71-85f1-28e654f6bafe', 'adminsupper@gmail.com', to_date('20/09/1996', 'DD/MM/YYYY'), 'No avatar', crypt('123@bBbb', gen_salt('bf', 8)), 'ACTIVE', false, NOW(), 'ADMIN', NOW(), 'ADMIN'),
    ('1a01336a-3a78-4c9e-b6fa-fe9e8ed98fa4', 'admin@gmail.com', to_date('02/10/1999', 'DD/MM/YYYY'), 'No avatar', crypt('123@bBbb', gen_salt('bf', 8)), 'ACTIVE', false, NOW(), 'ADMIN', NOW(), 'ADMIN');


insert into user_role (id, role_id, user_id, role_name)
values
	('291d5b04-9973-4edc-8f6e-782535018bf4', '623a4cb3-6789-43b2-b91d-df7d8bfad79d', 'c2a68b61-fc46-4d71-85f1-28e654f6bafe', 'SUPPER_ADMIN'),
	('f8ba9333-e3f9-42a8-8240-11ee20da706e', '9fcb42b1-e4d7-44c3-af8c-8e0974e34d8c', '1a01336a-3a78-4c9e-b6fa-fe9e8ed98fa4', 'ADMIN');




