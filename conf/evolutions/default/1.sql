# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table video_full (
  id                            bigint auto_increment not null,
  title                         varchar(255),
  year                          varchar(255),
  imdb_id                       varchar(255),
  y_url                         varchar(255),
  tp                            varchar(255),
  poster                        varchar(255),
  rated                         varchar(255),
  released                      varchar(255),
  runtime                       varchar(255),
  genre                         varchar(255),
  director                      varchar(255),
  writer                        varchar(255),
  actors                        varchar(255),
  plot                          varchar(255),
  imdb_rating                   varchar(255),
  imdb_votes                    varchar(255),
  tomato_meter                  varchar(255),
  tomato_image                  varchar(255),
  tomato_rating                 varchar(255),
  tomato_reviews                varchar(255),
  tomato_fresh                  varchar(255),
  tomato_rotten                 varchar(255),
  tomato_consensus              varchar(255),
  tomato_user_meter             varchar(255),
  tomato_user_rating            varchar(255),
  tomato_user_reviews           varchar(255),
  tomato_url                    varchar(255),
  tomato_dvd                    varchar(255),
  tomato_box_office             varchar(255),
  tomato_production             varchar(255),
  tomato_website                varchar(255),
  languages                     varchar(255),
  countries                     varchar(255),
  awards                        varchar(255),
  metascore                     integer not null,
  season                        integer,
  episode                       integer,
  constraint pk_video_full primary key (id)
);


# --- !Downs

drop table if exists video_full;

