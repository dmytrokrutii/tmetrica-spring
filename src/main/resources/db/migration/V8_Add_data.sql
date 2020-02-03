insert into users(email, password, name)
VALUES ('solosuicide133@gmail.com', '$2a$12$Q.9c5XxwNTWXDaQvGhxs4OR5PlJiF2vzm5My4ywAgHxJDawHCZv6W', 'Dmytro Krutii');
insert into users(email, password, name)
VALUES ('mykytafrolow@gmail.com', '$2a$12$pegFHPkjlEVbvc9hKx8xdupYjbyQ09xlkzlRh0UIjbOKus1ehshzG', 'Mykyta Frolow');
insert into user_roles(user_id, role)
VALUES (1, 'ADMIN');
insert into user_roles(user_id, role)
VALUES (1, 'USER');
insert into user_roles(user_id, role)
VALUES (2, 'USER');
insert into activities(name, opening_time, closing_time, status)
values ('Epam final project', '2020-01-20 19:30:00', null, 'ACTIVE');
insert into activities(name, opening_time, closing_time, status)
values ('News project', '2019-12-01 10:00:00', '2019-12-31 22:00:00', 'CLOSED');
insert into activities(name, opening_time, closing_time, status)
values ('Firmwares project', '2019-10-01 10:00:00', null, 'SUSPENDED');
insert into users_activities(user_id, activities_id)
VALUES (2, 1);
insert into users_activities(user_id, activities_id)
VALUES (2, 3);
insert into users_activities(user_id, activities_id)
VALUES (1, 1);
insert into users_activities(user_id, activities_id)
VALUES (1, 2);
insert into users_activities(user_id, activities_id)
VALUES (1, 3);
insert into activities_logs(activity_id, user_id, start_time, end_time)
VALUES (1, 1, '2020-01-20 19:30:00', '2020-02-20 19:30:00');
insert into activities_logs(activity_id, user_id, start_time, end_time)
VALUES (1, 2, '2010-04-10 15:30:00', '2010-05-20 20:30:00');
insert into activities_logs(activity_id, user_id, start_time, end_time)
VALUES (1, 2, '2010-04-10 15:30:00', '2010-05-20 20:30:00');
insert into activities_logs(activity_id, user_id, start_time, end_time)
VALUES (1, 1, '2020-01-20 19:30:00', '2020-02-20 19:30:00');
insert into activities_logs(activity_id, user_id, start_time, end_time)
VALUES (1, 1, '2010-04-10 15:30:00', '2010-05-20 20:30:00');
insert into activities_logs(activity_id, user_id, start_time, end_time)
VALUES (2, 1, '2020-01-20 18:20:00', '2020-02-20 19:30:00');
insert into activities_logs(activity_id, user_id, start_time, end_time)
VALUES (3, 2, '2019-06-17 13:20:00', '2010-05-20 20:30:00');