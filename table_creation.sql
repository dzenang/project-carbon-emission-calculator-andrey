create table users 
(
	user_id int primary key,
	username varchar(50),
	email varchar(50),
	password_hash varchar(50)
)

create table activities 
(
	activity_id int primary key,
	name varchar(50),
	description text
)

create table emission_factors
	(
	factor_id int primary key,
	activity_id int,
	factor decimal,
	unit varchar(50),
	constraint fk_activity_id
      foreign key(activity_id) 
        references activities(activity_id)
	)

create table user_emissions
	(
	emission_id int primary key,
	user_id int,
	activity_id int,
	quantity decimal,
	emission decimal,
	date date,
	constraint fk_user_id
      foreign key(user_id) 
        references users(user_id),
	constraint fk_activity_id
      foreign key(activity_id) 
        references activities(activity_id)
	)

create table emission_goals
	(
	goal_id int primary key,
	user_id int,
	target_emission decimal,
	start_date date,
	end_date date,
	status varchar(50),
	constraint fk_user_id
      foreign key(user_id) 
        references users(user_id)
	)

select * from emission_goals;
select * from user_emissions;
select * from activities;
select * from emission_goals;
select * from users;

--total emissions for a user
select sum(ue.emission) as "sum", u.username as "username"
	from user_emissions ue 
	join users u on u.user_id = ue.user_id
	group by u.username 
	having u.username = 'TestUser';

--compare emissions between different activities
SELECT a.name, avg(ue.emission) FROM user_emissions ue
JOIN activities a ON ue.activity_id = a.activity_id
GROUP BY a.name ORDER BY avg(ue.emission);

--list of emissions above threshold
SELECT ue.emission as "emission", a.name as "name" FROM user_emissions ue JOIN activities a ON ue.activity_id = a.activity_id WHERE ue.emission > 2;

--monthly emission for user
SELECT to_char(date, 'MM') AS MONTH, SUM(emission) AS "emission per month" FROM user_emissions GROUP BY to_char(date, 'MM') ORDER BY SUM(emission);
