# Getting Started

### Running
Application uses PostgreSQL for database management system. Image is available through docker-compose

```sh
$ docker-compose up
```

```sh
$ gradle bootRun
```

[Swagger UI is available under localhost:8080/swagger-ui/index.html](https://localhost:8080/swagger-ui/index.html)

### Adding admin

- Register as user
- Add admin role to user manually
```sql
insert into roles (id, name) values (userId, 'ROLE_ADMIN');
```

```sql
insert into user_roles (user_id, role_id) values (userId, roleId);
```