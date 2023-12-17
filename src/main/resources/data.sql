insert into role (name,description) values ('User','Uzytkownik');
insert into role (name,description) values ('Moderator','Moderator');
insert into role (name,description) values ('Admin','Admin');

insert into  address (city,zip_code,street,house_number) values ('Lublin','23-250','Poludniowa','4');
insert into address (city,zip_code,street,house_number) values ('Lublin','23-250','Poludniowa','4');
insert into address (city,zip_code,street,house_number) values ('Lublin','23-250','Poludniowa','4');

--lukasz
insert into person ( first_name,last_name, email ,password) values ('Lukasz','Bak','lukasz.bak@interiowy.pl'
                                                                   ,'{bcrypt}$2a$10$S9SF2iYav0bUpVL1iTjlrO5xGFHpO8crWTbr7CDhs6O5qhIvRn2nq');

--lukasz
insert into person ( first_name,last_name, email ,password) values ('Lukasz','Bak','bbzzyyczczeek@interia.pl'
                                                                   ,'{bcrypt}$2a$10$kcseLLQa.jhaQboKpUKjSeWiRmO3xJNmi3m34iTia/fqKZl4CgHw.');

--lukasz
insert into person ( first_name,last_name, email ,password) values ('Lukasz','Bak','bbzzyyczczeekk@interia.pl'
                                                                   ,'{bcrypt}$2a$10$kcseLLQa.jhaQboKpUKjSeWiRmO3xJNmi3m34iTia/fqKZl4CgHw.');


insert into user_role (user_id,role_id) values (1,1);

insert into user_role (user_id,role_id) values (2,1);
insert into user_role (user_id,role_id) values (2,2);

insert into user_role (user_id,role_id) values (3,1);
insert into user_role (user_id,role_id) values (3,2);
insert into user_role (user_id,role_id) values (3,3);

insert into category (name) values('silnik');
insert into category (name) values('opony');
insert into category (name) values('hamulce');

insert into car (mark, model, serial_number, parts_brand, price, quantity, photo, category_name) values ('Volkswagen', 'Jetta III', '2FMDK3GC4AB016773', 'French Pastry - Mini Chocolate', 95, 1, null, 'silnik');
insert into car (mark, model, serial_number, parts_brand, price, quantity, photo, category_name) values ('Land Rover', 'Range Rover', 'WAUEF48H17K898335', 'Wine - George Duboeuf Rose', 90, 2, null, 'silnik');
insert into car (mark, model, serial_number, parts_brand, price, quantity, photo, category_name) values ('Bentley', 'Continental Flying Spur', '2D4RN4DE9AR409291', 'Juice - Clam, 46 Oz', 91, 3, null, 'silnik');
insert into car (mark, model, serial_number, parts_brand, price, quantity, photo, category_name) values ('Saab', '9000', '2HKRM3H36DH553983', 'Beets - Pickled', 50, 4, null, 'opony');
insert into car (mark, model, serial_number, parts_brand, price, quantity, photo, category_name) values ('Mazda', 'RX-7', 'WBAPM73599A247752', 'Beef - Tender Tips', 90, 5, null, 'opony');
insert into car (mark, model, serial_number, parts_brand, price, quantity, photo, category_name) values ('Nissan', 'Sentra', 'WBAWC33598P719953', 'Bouq All Italian - Primerba', 92, 6, null, 'opony');
insert into car (mark, model, serial_number, parts_brand, price, quantity, photo, category_name) values ('Pontiac', 'Aztek', '3VW1K7AJ7DM912509', 'Pepper - Julienne, Frozen', 52, 7, null, 'opony');
insert into car (mark, model, serial_number, parts_brand, price, quantity, photo, category_name) values ('Lexus', 'GX', '1G4HK5ES8BU829538', 'Tortillas - Flour, 10', 48, 8, null, 'hamulce');
insert into car (mark, model, serial_number, parts_brand, price, quantity, photo, category_name) values ('Chevrolet', 'Avalanche', '1G6DV1EP0E0339088', 'Arctic Char - Fresh, Whole', 65, 9, null, 'hamulce');
insert into car (mark, model, serial_number, parts_brand, price, quantity, photo, category_name) values ('Land Rover', 'Defender Ice Edition', 'WBA3B5G59EN743592', 'Chinese Foods - Plain Fried Rice', 89, 10, null, 'hamulce');




insert into moto_parts (mark, model, serial_number, parts_brand, price, quantity, photo, category_name) values ('Suzuki', 'GS 500', '2FMDK3GC4AB016773', 'French Pastry - Mini Chocolate', 95, 1, null, 'silnik');
insert into moto_parts (mark, model, serial_number, parts_brand, price, quantity, photo, category_name) values ('Suzuki', 'GS 500', 'WAUEF48H17K898335', 'Wine - George Duboeuf Rose', 90, 2, null, 'silnik');
insert into moto_parts (mark, model, serial_number, parts_brand, price, quantity, photo, category_name) values ('Suzuki', 'GSX 650F', '2D4RN4DE9AR409291', 'Juice - Clam, 46 Oz', 91, 3, null, 'silnik');
insert into moto_parts (mark, model, serial_number, parts_brand, price, quantity, photo, category_name) values ('Suzuki', 'GSX 650F', '2HKRM3H36DH553983', 'Beets - Pickled', 50, 4, null, 'opony');
insert into moto_parts (mark, model, serial_number, parts_brand, price, quantity, photo, category_name) values ('Suzuki', 'GSX 650F', 'WBAPM73599A247752', 'Beef - Tender Tips', 90, 5, null, 'opony');
insert into moto_parts (mark, model, serial_number, parts_brand, price, quantity, photo, category_name) values ('Piaggio', 'beverly 200', 'WBAWC33598P719953', 'Bouq All Italian - Primerba', 92, 6, null, 'opony');
insert into moto_parts (mark, model, serial_number, parts_brand, price, quantity, photo, category_name) values ('Piaggio', 'beverly 200', '3VW1K7AJ7DM912509', 'Pepper - Julienne, Frozen', 52, 7, null, 'opony');
insert into moto_parts (mark, model, serial_number, parts_brand, price, quantity, photo, category_name) values ('Piaggio', 'beverly 200', '1G4HK5ES8BU829538', 'Tortillas - Flour, 10', 48, 8, null, 'hamulce');
insert into moto_parts (mark, model, serial_number, parts_brand, price, quantity, photo, category_name) values ('Piaggio', 'beverly 200', '1G6DV1EP0E0339088', 'Arctic Char - Fresh, Whole', 65, 9, null, 'hamulce');
insert into moto_parts (mark, model, serial_number, parts_brand, price, quantity, photo, category_name) values ('Piaggio', 'beverly 200', 'WBA3B5G59EN743592', 'Chinese Foods - Plain Fried Rice', 89, 10, null, 'hamulce');


