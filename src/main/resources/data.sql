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

INSERT INTO T_FLIGHT ( id,AIRPLANE_CODE,ARRIVAL_AIRPORT_CODE,ARRIVAL_TIME,DEPARTURE_AIRPORT_CODE,DEPARTURE_TIME )
VALUES
    (1,'GEMINI','HEL', to_timestamp( '03/03/2022 02:35', 'DD/MM/YYYY HH:MM'),'GRU',to_timestamp( '03/03/2022 06:35', 'DD/MM/YYYY HH:MM')),
    (2,'GEMINI200','DXB', to_timestamp( '24/11/2022 11:50', 'DD/MM/YYYY HH:MM'),'HEK',to_timestamp( '03/03/2022 15:00', 'DD/MM/YYYY HH:MM')),
    (3,'INFLIGHT200','IND', to_timestamp( '11/07/2022 19:00', 'DD/MM/YYYY HH:MM'),'COK',to_timestamp( '03/03/2022 23:30', 'DD/MM/YYYY HH:MM')),
    (4,'BLACKOUT','GRT', to_timestamp( '22/09/2022 21:30', 'DD/MM/YYYY HH:MM'),'INJ',to_timestamp( '03/03/2022 22:50', 'DD/MM/YYYY HH:MM')),
    (5,'SKYMARKS','JAA', to_timestamp( '07/01/2022 10:20', 'DD/MM/YYYY HH:MM'),'JAC',to_timestamp( '03/03/2022 14:00', 'DD/MM/YYYY HH:MM'));
