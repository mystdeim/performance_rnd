# Performance rnd

## Requirments

```sh
sudo apt update
sudo apt install wrk
sudo apt install postgresql postgresql-contrib
```

### DB
```sh
sudo -u postgres psql postgres # enter to console
\password # set password to 'password'
```

```sql
create database blog;
\c blog
create table blog(id serial primary key, title VARCHAR(255) not null, body VARCHAR(255));
insert into blog (title, body) values ('title', 'body');
\q
```