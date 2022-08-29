CREATE TABLE public.todo (
	id UUID,
	"text" varchar NULL,
	created_at time NULL,
	deleted_at time NULL,
	done_at time NULL,
	CONSTRAINT todo_pk PRIMARY KEY (id)
);
