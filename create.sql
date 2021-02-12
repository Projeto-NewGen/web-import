
    create table tb_campanha (
       id  bigserial not null,
        nome varchar(255),
        qtd_telefone int4,
        primary key (id)
    );

    create table tb_campanha_campos (
       campo_id int8 not null,
        campanha_id int8 not null
    );

    create table tb_campanha_telefone (
       telefone_id int8 not null,
        campanha_id int4 not null
    );

    create table tb_campo (
       id  bigserial not null,
        nome varchar(255),
        valor varchar(255),
        primary key (id)
    );

    create table tb_telefone (
       id  serial not null,
        primary key (id)
    );

    create table telefone_phones (
       telefone_id int4 not null,
        phones varchar(255)
    );

    alter table tb_campanha_campos 
       add constraint FKnlx622vp014bcpilnbjx4e5kd 
       foreign key (campanha_id) 
       references tb_campo;

    alter table tb_campanha_campos 
       add constraint FK8h1rv5ie98pjdoe3adhguhhed 
       foreign key (campo_id) 
       references tb_campanha;

    alter table tb_campanha_telefone 
       add constraint FKhxl286syof7hwm7pent4aoa25 
       foreign key (campanha_id) 
       references tb_telefone;

    alter table tb_campanha_telefone 
       add constraint FKcnkfal6vv7wtybf30h4wear3p 
       foreign key (telefone_id) 
       references tb_campanha;

    alter table telefone_phones 
       add constraint FKfut9vkjvy6uolonuon1ysdfn3 
       foreign key (telefone_id) 
       references tb_telefone;
