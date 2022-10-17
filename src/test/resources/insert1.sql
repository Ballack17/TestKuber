delete from yandex_master.essence;
INSERT INTO yandex_master.essence (id,some_date,some_time,title,email)
VALUES ('00000001-0000-0000-0000-000000000001',
        '2021-06-01','08:00:00+03','test_title','email@gmail.com'),
('00000001-0000-0000-0000-000000000002',
 '2021-06-01','08:00:00+03','test_title1','email1@gmail.com'),
('00000001-0000-0000-0000-000000000003',
    '2021-06-01','08:00:00+03','test_title2', null);
