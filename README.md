<h1>Client-Server App for movie rental company</h1>
<P>Dla poprawnej pracy aplikacji na serverze ma byc zainstalowana i odpowiednio skonfigurowana baza danych MySQL </P>
<hr>
<p>Polecenia MySQL:<br>
<pre><code>
1) Baza danych użytkownikow<br>
CREATE TABLE<br>
    `users` (
        `id` INT(11) NOT NULL AUTO_INCREMENT,
        `user` CHAR(30) NOT NULL,
        `pass` CHAR(30) NOT NULL,
        PRIMARY KEY(`id`)
    )<br>
2)CREATE TABLE<br>
    `movies` (
        `id` INT(11) NOT NULL AUTO_INCREMENT,
        `title` CHAR(30) NOT NULL,
        `name` CHAR(30) NOT NULL,
        `surname` CHAR(30) NOT NULL,
        `date` CHAR(30) NOT NULL,
        `status` CHAR(30) NOT NULL,
        PRIMARY KEY(`id`)
    )<br>
3)INSERT INTO users (user,pass) VALUES('root','start001');<br>
4)INSERT INTO movies (title,name,surname,date,status) VALUES('','','','','');</b><br>
5)<b>UPDATE movies SET name='', surname='',date='', status='' WHERE title ='';</b><br>
6)DELETE FROM movies where title='';</p><br>
</code></pre>
