CREATE TABLE consulta(
id bigint not null auto_increment,
id_medico bigint not null,
id_paciente bigint not null,
data datetime not null,
primary key(id),
constraint fk_consulta_id_medico foreign key (id_medico) references medico (id),
constraint fk_consulta_id_paciente foreign key (id_paciente) references paciente (id));
