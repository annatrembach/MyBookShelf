-- Table: public.Book

--DROP TABLE IF EXISTS public."Users";
--DROP TABLE IF EXISTS public."Book";
--DROP TABLE IF EXISTS public."Shelf";
--DROP TABLE IF EXISTS public."Book_response";
--DROP TABLE IF EXISTS public."Book_Shelf";
--DROP TABLE IF EXISTS public."Users_Connection";


--TABLES
CREATE TABLE IF NOT EXISTS public."Book"
(
    Id_book int NOT NULL,
    name_book text NOT NULL,
    cover_book bytea,
    short_description_book text,
    Id_book_author int NOT NULL,
    Id_book_publisher int NOT NULL,
    Id_book_response int,
    CONSTRAINT "Book_pkey" PRIMARY KEY (Id_book)
);

CREATE TABLE IF NOT EXISTS public."Users"
(
    Id_user int NOT NULL,
	name_user text NOT NULL,
	email_user text NOT NULL UNIQUE,
	password_user text NOT NULL UNIQUE,
	CONSTRAINT "Users_pkey" PRIMARY KEY (Id_user)
);

CREATE TABLE IF NOT EXISTS public."Shelf"
(
    Id_shelf int NOT NULL,
	name_shelf text NOT NULL,
	Id_user int NOT NULL,
	CONSTRAINT "Shelf_pkey" PRIMARY KEY (Id_shelf)
);

CREATE TABLE IF NOT EXISTS public."Shelf_Book"
(
    Id_shelf int NOT NULL,
	Id_book int NOT NULL
);

CREATE TABLE IF NOT EXISTS public."Book_publisher"
(
    Id_book_publisher int NOT NULL,
	name_book_publisher text NOT NULL,
	CONSTRAINT "Book_publisher_pkey" PRIMARY KEY (Id_book_publisher)
);
	
CREATE TABLE IF NOT EXISTS public."Users_connection"
(
    Id_first_user int NOT NULL,
	Id_second_user int NOT NULL
);
	
CREATE TABLE IF NOT EXISTS public."Book_author"
(
    Id_book_author int NOT NULL,
	name_book_author text NOT NULL,
	CONSTRAINT "Book_author_pkey" PRIMARY KEY (Id_book_author)
);
	
CREATE TABLE IF NOT EXISTS public."Book_response"
(
    Id_book_response INT NOT NULL,
	rating_response INT NOT NULL CHECK (rating_response BETWEEN 0 AND 5),
    content_response INT NOT NULL CHECK (content_response BETWEEN 0 AND 5),
    fun_response INT NOT NULL CHECK (fun_response BETWEEN 0 AND 5),
    emotional_response INT NOT NULL CHECK (emotional_response BETWEEN 0 AND 5),
    wow_response INT NOT NULL CHECK (wow_response BETWEEN 0 AND 5),
	Id_user INT NOT NULL,
	CONSTRAINT "Book_response_pkey" PRIMARY KEY (Id_book_response)
);


--FOREIGN KEY 

--USERS_CONNECTION

ALTER TABLE public."Users_connection"
ADD CONSTRAINT fk_Id_first_user
FOREIGN KEY (Id_first_user) REFERENCES public."Users" (Id_user);

ALTER TABLE public."Users_connection"
ADD CONSTRAINT fk_Id_second_user
FOREIGN KEY (Id_second_user) REFERENCES public."Users" (Id_user);

--SHELF_BOOK

ALTER TABLE public."Shelf_Book"
ADD CONSTRAINT fk_Id_shelf
FOREIGN KEY (Id_shelf) REFERENCES public."Shelf" (Id_shelf);

ALTER TABLE public."Shelf_Book"
ADD CONSTRAINT fk_Id_book
FOREIGN KEY (Id_book) REFERENCES public."Book" (Id_book);

--SHELF

ALTER TABLE public."Shelf"
ADD CONSTRAINT fk_Id_user
FOREIGN KEY (Id_user) REFERENCES public."Users" (Id_user);

--BOOK

ALTER TABLE public."Book"
ADD CONSTRAINT fk_Id_book_publisher
FOREIGN KEY (Id_book_publisher) REFERENCES public."Book_publisher" (Id_book_publisher);

ALTER TABLE public."Book"
ADD CONSTRAINT fk_Id_book_response
FOREIGN KEY (Id_book_response) REFERENCES public."Book_response" (Id_book_response);

ALTER TABLE public."Book"
ADD CONSTRAINT fk_Id_book_author
FOREIGN KEY (Id_book_author) REFERENCES public."Book_author" (Id_book_author);

--BOOK_RESPONSE

ALTER TABLE public."Book_response"
ADD CONSTRAINT fk_Id_user
FOREIGN KEY (Id_user) REFERENCES public."Users" (Id_user);





