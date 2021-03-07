drop table if exists AppUser;
create table AppUser
(
    id           bigint(20)   not null primary key,
    email        varchar(255) null,
    name         varchar(255) null,
    nickname     varchar(255) null,
    password     varchar(255) null,
    profile      varchar(255) null,
    role         varchar(255) null,
    studentId    int          null,
    username     varchar(255) null,
    createdAt    datetime     null,
    lastAccessAt datetime     null,
    version      bigint       null,
    constraint idx_AppUser_username
        unique (username)
);

drop table if exists Article;
create table Article
(
    id           bigint auto_increment primary key,
    boardType    varchar(20)  null,
    content      varchar(255) null,
    depth        int          null,
    grpNo        int          null,
    grpOrder     int          null,
    hits         int          null,
    likes        int          null,
    commentCount int          null,
    deleted      bit          null,
    title        varchar(255) null,
    tags         varchar(255) null,
    authorId     bigint       null,
    version      bigint       not null,
    modifiedAt   datetime     null,
    createdAt    datetime     null
);

create index idx_Article_boardType on Article(boardType);

drop table if exists ArticleLike;
create table ArticleLike
(
    articleId bigint       not null,
    userId    varchar(255) not null,
    createdAt datetime     null,
    updatedAt datetime     null,
    version   bigint       null,
    primary key (articleId, userId)
);

drop table if exists Board;
create table Board
(
    id        bigint       not null primary key,
    name      varchar(255) null,
    type      varchar(255) null,
    createdAt datetime     null,
    updatedAt datetime     null,
    version   bigint       null
);

drop table if exists Comment;
create table Comment
(
    id        bigint auto_increment primary key,
    articleId bigint       null,
    content   varchar(255) null,
    depth     int          null,
    grpNo     int          null,
    grpOrd    int          null,
    likeCount int          null,
    userId    bigint(20)   null,
    version   bigint(20)   null,
    createdAt datetime     null,
    updatedAt datetime     null
);
drop table if exists FileMeta;
create table FileMeta
(
    id        bigint auto_increment primary key,
    articleId bigint       null,
    createdAt datetime     null,
    fileType  varchar(255) null,
    fileUrl   varchar(255) null,
    filename  varchar(255) null,
    updatedAt datetime     null,
    userId    bigint(20)   null
        version bigint null
);

drop table if exists Recommend;
create table Recommend
(
    id         int        not null primary key,
    targetId   bigint     null,
    targetType varchar(20),
    userId     bigint(20) null
);

