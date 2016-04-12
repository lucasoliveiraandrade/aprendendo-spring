insert into role values('ROLE_ADMIN');

insert into usuario values('admin@casadocodigo.com.br', 'Administrador', '$2a$04$Ba9Gy3dRodjHNGoLdylkYOroPuBb5jCXTTBhrDt1XKll1lHaxedz2');  -- 123
														   
insert into usuario_role(usuario_email, roles_nome) values ('admin@casadocodigo.com.br', 'ROLE_ADMIN');