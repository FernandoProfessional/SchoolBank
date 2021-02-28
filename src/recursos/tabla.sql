create database cajerosbd;
use cajerosbd;
create table cuentas
(numeroCuenta integer(6)not null primary key,
saldo double(10,2)not null,
tipocuenta varchar(45)not null
);
create table movimientos
(idMovimiento integer(8)not null primary key auto_increment,
idCuenta integer(6)not null,
fecha datetime,
cantidad double(10,2)not null,
operacion varchar(45)not null,
foreign key(idCuenta) references cuentas(numeroCuenta)
);
<!--alter table movimientos add foreign key (idCuenta) references cuentas(numeroCuenta);
insert into cuentas values('1','1000','alguna');
insert into cuentas values('2','2000','algo');
insert into cuentas values('3','4000','nada');
insert into cuentas values('4','8000','palabra');
insert into cuentas values('5','20000','ordenador');

commit;