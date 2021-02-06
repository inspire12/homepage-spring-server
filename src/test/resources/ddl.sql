create table if not exists AppUser
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
    constraint UK_atqgqm46rh7b0lrgl80ryd5tp
        unique (username)
);

create table if not exists Article
(
    id        bigint auto_increment primary key,
    boardId   int          null,
    content   varchar(255) null,
    createdAt datetime     null,
    depth     int          null,
    grpNo        int          null,
    grpOrd    int          null,
    hit       int          null,
    isDeleted bit          null,
    subject   varchar(255) null,
    tags      varchar(255) null,
    updatedAt datetime     null,
    userId    bigint       null,
    username  varchar(255) null
);

create table if not exists ArticleLike
(
    postId    bigint       not null,
    username  varchar(255) not null,
    createdAt datetime     null,
    updatedAt datetime     null,
    version   bigint       null,
    primary key (postId, username)
);

create table if not exists Board
(
    id        bigint       not null primary key,
    name      varchar(255) null,
    type      varchar(255) null,
    createdAt datetime     null,
    updatedAt datetime     null,
    version   bigint       null
);

create table if not exists Comment
(
    id        bigint auto_increment primary key,
    articleId bigint       null,
    content   varchar(255) null,
    createdAt datetime     null,
    depth     int          null,
    grpNo     int          null,
    grpOrd    int          null,
    likeCount int          null,
    updatedAt datetime     null,
    username  varchar(255) null,
    version   bigint       null
);

create table if not exists FileMeta
(
    id        bigint auto_increment primary key,
    articleId bigint       null,
    createdAt datetime     null,
    fileType  varchar(255) null,
    fileUrl   varchar(255) null,
    filename  varchar(255) null,
    updatedAt datetime     null,
    username  varchar(255) null,
    version   bigint       null
);

create table if not exists Recommend
(
    id        int          not null primary key,
    targetId  bigint       null,
    username  varchar(255) null
);

