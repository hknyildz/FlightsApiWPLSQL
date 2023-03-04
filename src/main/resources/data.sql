INSERT INTO T_AIRPORT ( id, name,AIRPORT_CODE )
VALUES
    (1, 'Dubai International Airport', 'DXB'),
    (2, 'Helsinki Vantaa Airport', 'HEL'),
    (3, 'Guarulhos - Governador Andr̩ Franco Montoro International Airport', 'GRU'),
    (4, 'Heihe Airport', 'HEK'),
    (5, 'Indianapolis International Airport', 'IND'),
    (6, 'Cochin International Airport', 'COK'),
    (7, 'Gujrat Airport', 'GRT'),
    (8, 'Injune Airport', 'INJ'),
    (9, 'Abu Dhabi International Airport', 'AUH'),
    (10, 'Jalalabad Airport', 'JAA'),
    (11, 'Jackson Hole Airport', 'JAC'),
    (12, 'Jurien Bay Airport', 'IZA'),
    (13, 'Kon Airport', 'KCI');

INSERT INTO T_AIRLINE ( id, name,AIRLINE_CODE )
VALUES
    (1, 'AMERICAN AIRLINES INC.', 'AAL'),
    (2, 'AIR CANADA', 'ACA'),
    (3, 'FEDEX', 'FDX'),
    (4, 'TURKISH AIRLINES INC.', 'THY'),
    (5, 'Indianapolis International Airport', 'IND');

INSERT INTO T_AIRPLANE ( id,AIRLINE_CODE,AIRPLANE_CODE )
VALUES
    (1,  'AAL','GEMINI200'),
    (2,  'ACA','GEMINI'),
    (3,  'FDX','INFLIGHT200'),
    (4,  'THY','BLACKOUT'),
    (5,  'IND','SKYMARKS');

INSERT INTO T_FLIGHT ( id,AIRPLANE_CODE,ARRIVAL_AIRPORT_CODE,DEPARTURE_TIME,DEPARTURE_AIRPORT_CODE,ARRIVAL_TIME )
VALUES
(1,'GEMINI','HEL', {ts '2022-09-01 18:30:00'},'GRU',{ts '2022-09-01 21:45:00'}),
(2,'GEMINI200','DXB', {ts '2022-09-17 09:45:00'},'HEK',{ts '2022-09-17 11:00:00'}),
(3,'INFLIGHT200','IND', {ts '2022-11-14 11:00:00'},'COK',{ts '2022-11-14 11:00:00'}),
(4,'BLACKOUT','GRT', {ts '2022-12-25 00:30:00'},'INJ',{ts '2022-12-25 13:30:00'}),
(5,'SKYMARKS','JAA', {ts '2023-01-04 12:15:00'},'JAC',{ts '2023-01-04 15:00:00'});

