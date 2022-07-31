create table capacity(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    capacity int NOT NULL
);
create table rtype(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    rtype VARCHAR NOT NULL
);
create table client(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    fullname VARCHAR(20) NOT NULL,
    mobile VARCHAR NOT NULL
);

create table room(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    cid BIGINT REFERENCES capacity(id),
    tid BIGINT REFERENCES rtype(id),
    price INT,
    isavailablle BOOL
);

create table reservation(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    clid BIGINT REFERENCES client(id),
    rid BIGINT REFERENCES room(id),
    sdate DATE,
    edate DATE,
    UNIQUE(rid)

);

insert into room (cid, rid, price) values (4, 2, 1513);
insert into room (cid, rid, price) values (4, 1, 1250);
insert into room (cid, rid, price) values (2, 1, 1723);
insert into room (cid, rid, price) values (2, 3, 1718);
insert into room (cid, rid, price) values (4, 3, 1213);
insert into room (cid, rid, price) values (4, 1, 871);
insert into room (cid, rid, price) values (1, 1, 1066);
insert into room (cid, rid, price) values (1, 3, 1296);
insert into room (cid, rid, price) values (3, 2, 1462);
insert into room (cid, rid, price) values (3, 3, 1553);
