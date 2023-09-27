insert into topic (title, header)
values ('Top books', 'Discussions about pupular books'),
       ('New books', 'Comments about top rated films'),
       ('Populiar books', 'Discussions about latest movies'),
       ('Most expensive books', 'Discussions about most expensive Books');

-- ('Most popular films', 'Discussions about pupular films'),
-- ('Top rated films', 'Discussions about top rated films'),
-- ('Latest movies', 'Discussions about latest movies'),
-- ('Most expensive movies', 'Discussions about most expensive movies');

insert into comment (text, topic_id)
values ('It think something something about Most popular books', 1),
       ('But I think something otherwise about Most popular books', 1),
       ('Nononono about Most popular books', 1),

       ('It think something something about top rated books', 2),
       ('But I think something otherwise about top rated books', 2),

       ('It think something something about latest books', 3),
       ('It think something something about latest books', 3);