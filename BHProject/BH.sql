DROP DATABASE IF EXISTS BH2015;

CREATE DATABASE BH2015;

USE BH2015;



create table BH_USER
(
   user_id serial unique,
   username character varying(255),
   password character varying(255),
   email character varying(255),
   name character varying(255),
   telephone character varying(255),
   street character varying(255),
   zip character varying(255),
   city character varying(255),
   state character varying(255),
   country character varying(255)
);

INSERT INTO BH_USER (username, password, email, name, telephone, street, zip, city, state, country)
VALUES ('dummy','123', 'dummy@gmail.com','Dummy User','3124786012','1400 W Belmont Ave', '60657', 'Chicago', 'IL', 'USA');

INSERT INTO BH_USER (username, password, email, name, telephone, street, zip, city, state, country)
VALUES ('lwitgen','123', 'jdhscripts@gmail.com','Ludwig Witgenstien','3124786012','1400 W Belmont Ave', '60657', 'Chicago', 'IL', 'USA');

INSERT INTO BH_USER (username, password, email, name, telephone, street, zip, city, state, country)
VALUES ('brussel','456','jdhscripts@gmail.com','Bertrand Russel','3124786012','601 W Belmont Ave', '60657', 'Chicago', 'IL', 'USA');

INSERT INTO BH_USER (username, password, email, name, telephone, street, zip, city, state, country)
VALUES ('jdoe','789','st250@yahoo.com','Jane Doe','3124786012','801 W Belmont Ave', '60657', 'Chicago', 'IL', 'USA');


CREATE TABLE BH_CATEGORY 
(
  category_id serial unique,
  category_name character varying(255),
  category_level integer,
  image_name character varying(255),
  comment1 character varying(1024),
  comment2 character varying(1024),
  parent_category_id numeric(10,0),
  leaf_category boolean
);


INSERT INTO BH_CATEGORY (category_name, category_level, image_name, comment1, comment2, parent_category_id, leaf_category)
VALUES ('Community Chest',0,'cchest.png','Welcome to the chest! Where you can share resources in your community', '', 1, false );

INSERT INTO BH_CATEGORY (category_name, category_level, image_name, comment1, comment2, parent_category_id, leaf_category)
VALUES ('Transportation',1,'dccars.jpg','Luxury and standard cars, trucks and SUVs', '', 1, false );

INSERT INTO BH_CATEGORY (category_name, category_level, image_name, comment1, comment2, parent_category_id, leaf_category)
VALUES ('Appliances',1,'bathchair.jpg','Household kitchen appliances', '', 1, false );

INSERT INTO BH_CATEGORY (category_name, category_level, image_name, comment1, comment2, parent_category_id, leaf_category)
VALUES ('Medical',1,'motorwheel.jpg','Medical equipment and services', '', 1, false );


INSERT INTO BH_CATEGORY (category_name, category_level, image_name, comment1, comment2, parent_category_id, leaf_category)
VALUES ('Standard Cars',2,'stcars.jpg','Standard cars, trucks and SUVs', '', 2, true );

INSERT INTO BH_CATEGORY (category_name, category_level, image_name, comment1, comment2, parent_category_id, leaf_category)
VALUES ('Driverless Cars',2,'dccars.jpg','Standard cars, trucks and SUVs', '', 2, true );

INSERT INTO BH_CATEGORY (category_name, category_level, image_name, comment1, comment2, parent_category_id, leaf_category)
VALUES ('8 foot Truck',2,'8ftruck.jpg','Standard cars, trucks and SUVs', '', 2, true );

INSERT INTO BH_CATEGORY (category_name, category_level, image_name, comment1, comment2, parent_category_id, leaf_category)
VALUES ('Manual Wheelchairs',2,'manwheel.jpg','Manual wheelchairs of all sizes', '', 4, true );

INSERT INTO BH_CATEGORY (category_name, category_level, image_name, comment1, comment2, parent_category_id, leaf_category)
VALUES ('Motorized Wheelchairs',2,'motorwheel.jpg','All weather motorized wheelchairs', '', 4, true );

INSERT INTO BH_CATEGORY (category_name, category_level, image_name, comment1, comment2, parent_category_id, leaf_category)
VALUES ('Lift Chairs',2,'liftchairs.jpg','Support robots for everyday easy living', '', 4, true );



CREATE TABLE BH_ITEM
(
  item_id serial unique,
  category_id bigint unsigned,
  item_code character varying(255),
  description character varying(255),
  long_description character varying(1024),
  image_name character varying(255),
  checkoutuser_id bigint unsigned,
  requestuser_id1 bigint unsigned,
  requestuser_id2 bigint unsigned,
  due_date timestamp,
  updateuser_id bigint unsigned,
  donor_id bigint unsigned,
  item_status int unsigned not null DEFAULT 0,

foreign key (checkoutuser_id) references BH_USER(user_id),
foreign key (donor_id) references BH_USER(user_id),
foreign key (category_id) references BH_CATEGORY(category_id),
foreign key (requestuser_id1) references BH_USER(user_id),
foreign key (requestuser_id2) references BH_USER(user_id)
);

INSERT INTO BH_ITEM (item_id, category_id, item_code, description, long_description, 
image_name, checkoutuser_id, requestuser_id1, requestuser_id2, due_date, updateuser_id, donor_id)
VALUES (1,8,'NOVA12','Nova Standard Steel Wheelchair with Fixed Arms & Swing Away Footrests 20 Inch Steel', '20 in. seat width<br> Foot rests are easy to adjust <br> High Strength Aluminum Footplates',
'nova12.jpg', 1, 2, 3, '7/3/2015', 3,3 );

INSERT INTO BH_ITEM (item_id, category_id, item_code, description, long_description, 
image_name, checkoutuser_id, requestuser_id1, requestuser_id2, due_date, updateuser_id, donor_id)
VALUES (2,8,'MEDLINEK4','Medline K4 Basic Lightweight Wheelchair', 'Medline K4 Basic Wheelchair. Seat 18W x 16D; Black, Nylon Upholstery, Swing Back Desk-Length Arms And Swing Away, Detachable Foot Rests, Adjustable Back Height; 300 Lb Weight Capacity. Hemi Height Adjustable',
'medline14.jpg', 2, 1, 1, '7/3/2015', 3,3 );

INSERT INTO BH_ITEM (item_id, category_id, item_code, description, long_description, 
image_name, checkoutuser_id, requestuser_id1, requestuser_id2, due_date, updateuser_id, donor_id)
VALUES (3,8,'KARMENT','Karman Lightweight Deluxe 18 inch Steel Wheelchair with Removable Armrests, 36 lbs', 'Karman Healthcare Lightweight Deluxe Wheelchair with Removable Armrests Features:
A Silver Vein finish
Available in 18 inch seat width
Available with or without elevating footrests
Removable padded armrests
Swing away footrests
24 inch polyurethane rear tires
8 inch front casters
Double padded black nylon upholstery
Adjustable seat height',
'karment.jpg', 3, 1, 1, '7/3/2015', 2,2 );


INSERT INTO BH_ITEM (item_id, category_id, item_code, description, long_description, 
image_name, checkoutuser_id, requestuser_id1, requestuser_id2, due_date, updateuser_id, donor_id)
VALUES (4,9,'KDSMART','Standard KD Smart Chair Electric Wheelchair', 'Weighs 50 pounds and supports passengers weighing up to 265 pounds	
Folds in seconds for easy storage in your car, SUV or closet	
Lithium Ion battery can travel up to 15 miles	
FDA cleared medical device	
More compact in size than traditional wheelchairs	
Removable seat and seat cover for easy cleaning	
Adjustable arm rests that can be raised for easy side access	
Joystick can be mounted on left or right side arm rest	
Our powered wheelchair costs less than any of its competitors',
'kdsmart.jpg', 1, 1, 1, '7/3/2015', 0,3 );

INSERT INTO BH_ITEM (item_id, category_id, item_code, description, long_description, 
image_name, checkoutuser_id, requestuser_id1, requestuser_id2, due_date, updateuser_id, donor_id)
VALUES (5,9,'ELITE-14','Pride Jazzy Elite 14 Powerchair', 'The Jazzy Elite 14 features the ideal combination of power and maeuverability.  Using a twin motor system, it can travel almost 10 miles on a single charge and reach a speed or 4 mph.  It also comes with a comfortable seat and a weight capacity of up to 300 pounds.  Front wheel anti-tip castors and solid rear wheels offer additional stability and safety.',
'elite-14.jpg', 1, 1, 1, '7/3/2015', 0,3 );

INSERT INTO BH_ITEM (item_id, category_id, item_code, description, long_description, 
image_name, checkoutuser_id, requestuser_id1, requestuser_id2, due_date, updateuser_id, donor_id)
VALUES (6,9,'MEDALIST-14','Drive Medalist Bariatric Captain Seat Heavy Duty Power Wheelchair', 'The Medalist Heavy Duty Power Wheelchair with its extra strong steel frame offers a durable and reliable performance rear wheel power chair in one package. The captain seat with deep contoured cushions and backs provides superior comfort while maintaining a long life. Almost everything on this chair is adjustable; from a reclining backrest with adjustable headrest, to the height of the chair.',
'medalist-14.jpg', 1, 1, 1, '7/3/2015', 0,3 );



INSERT INTO BH_ITEM (item_id, category_id, item_code, description, long_description, 
image_name, checkoutuser_id, requestuser_id1, requestuser_id2, due_date, updateuser_id, donor_id)
VALUES (7,10,'Pride LC-110','2-Position Partial Recline', 'Button-back Design. Medium Size. Pride Essential Collection delivers superb value and excellent comfort with features like an innovative arm design and overstuffed fiber-filled backs. In addition, Essential Collection Lift Chairs feature patented, quiet and smooth operation and stylish designs to add to any home décor.',
'pride-110.jpg', 1, 1, 1, '7/3/2015', 0,3 );

INSERT INTO BH_ITEM (item_id, category_id, item_code, description, long_description, 
image_name, checkoutuser_id, requestuser_id1, requestuser_id2, due_date, updateuser_id, donor_id)
VALUES (8,10,'Pride LC-250','3-Position Full Recline', 'A 3-position lift chair that delivers superb value and excellent comfort. Prides LC-105 delivers superb value, excellent comfort and exceptional quality. The LC-105 features quiet and smooth operation and stylish designs to add to any home décor',
'plc-250.jpg', 1, 1, 1, '7/3/2015', 0,3 );

INSERT INTO BH_ITEM (item_id, category_id, item_code, description, long_description, 
image_name, checkoutuser_id, requestuser_id1, requestuser_id2, due_date, updateuser_id, donor_id)
VALUES (9,10,'Golden Oxford','Oxford club-style chair ', 'The new Oxford club-style chair is the ideal lift chair for your home! Perfect for the den, office or bedroom, our pocketed coil spring seat has soft, supportive comfort. The classic club chair design is available in our luxurious padded suede in two colors, Silt and Mahogany. We’ve included brushed bronze nail heads and luggage stitching for the classic finishing touches.',
'golden-ox.jpg', 1, 1, 1, '7/3/2015', 0,3 );






