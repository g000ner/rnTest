create table compute_results_logs(
	id uuid primary key,
	run_id uuid,
	compute_start_time time,
	compute_end_time time,
	first_point_descriptor varchar(30),
	second_point_descriptor varchar(30),
	compute_type varchar(20),
	distance numeric
);