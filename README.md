<h1>Client-Server App for movie rental company</h1>
<P>Dla poprawnej pracy aplikacji na serverze ma byc zainstalowana i odpowiednio skonfigurowana baza danych MySQL </P>
<hr>
<p>Polecenia MySQL:
1) Baza danych użytkownikow	
CREATE TABLE
    `users` (
        `id` INT(11) NOT NULL AUTO_INCREMENT,
        `user` CHAR(30) NOT NULL,
        `pass` CHAR(30) NOT NULL,
        PRIMARY KEY(`id`)
    )
2)CREATE TABLE
    `movies` (
        `id` INT(11) NOT NULL AUTO_INCREMENT,
        `title` CHAR(30) NOT NULL,
        `name` CHAR(30) NOT NULL,
        `surname` CHAR(30) NOT NULL,
        `date` CHAR(30) NOT NULL,
        `status` CHAR(30) NOT NULL,
        PRIMARY KEY(`id`)
    )
3)INSERT INTO users (user,pass) VALUES('root','start001');
4)INSERT INTO movies (title,name,surname,date,status) VALUES('Avatar','Mateusz','Woch','19.07.2019','Wypozyczony');</p>

