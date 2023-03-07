INSERT INTO T_AIRPORT ( id, name,AIRPORT_CODE )
VALUES
    ('4b8cb472-1927-4948-86ec-6c386991d54b', 'Dubai International Airport', 'DXB'),
    ('8daf6be1-18d2-4cff-90bc-3ff605e4c3ad', 'Helsinki Vantaa Airport', 'HEL'),
    ('67d3f5dd-a32c-4fe0-98e3-cb1fbad0c9d2', 'Guarulhos - Governador Andr̩ Franco Montoro International Airport', 'GRU'),
    ('56f6bc3e-b100-4fa4-bfae-86f9f72c34c5', 'Heihe Airport', 'HEK'),
    ('a1599137-9e96-4089-8cc8-edc8c1c9c64a', 'Indianapolis International Airport', 'IND'),
    ('20c1be11-e91a-4015-bd1b-231baa451560', 'Cochin International Airport', 'COK'),
    ('996e7db7-5741-4c77-8e2f-df8ea2a1e491', 'Gujrat Airport', 'GRT'),
    ('6106226e-2fce-4cd8-b6df-4963ea35528f', 'Injune Airport', 'INJ'),
    ('06774e52-fe94-48be-bf40-54b4ba25e396', 'Abu Dhabi International Airport', 'AUH'),
    ('f0891aae-355a-4101-9239-1cd896eb4eab', 'Jalalabad Airport', 'JAA'),
    ('86432a02-b4e0-4fb1-a92a-16e35c83c95f', 'Jackson Hole Airport', 'JAC'),
    ('b54f2fcf-2ee2-4020-a071-d2069ecafc7b', 'Jurien Bay Airport', 'IZA'),
    ('2d8aee5d-080e-4208-8ef7-f8e5905f8c8f', 'Kon Airport', 'KCI');

INSERT INTO T_AIRLINE ( id, name,AIRLINE_CODE )
VALUES
    ('3c4e312a-44f3-4787-9d20-c0dd7574d0d0', 'AMERICAN AIRLINES INC.', 'AAL'),
    ('71a0c6dc-8c3c-40a0-a641-7837b11f3073', 'AIR CANADA', 'ACA'),
    ('c71d3bb1-707f-438d-bdb0-c843daf9adc2', 'FEDEX', 'FDX'),
    ('589f1cd0-952d-44e8-b44c-c0e8e145cfe7', 'TURKISH AIRLINES INC.', 'THY'),
    ('78b0f9c5-d3b9-43ac-b83e-0a768e124e8e', 'Indianapolis International Airport', 'IND');

INSERT INTO T_AIRPLANE ( id,AIRLINE_CODE,AIRPLANE_CODE )
VALUES
    ('3071fbac-a07f-4cfc-af27-9768e70c5e6e',  'AAL','GEMINI200'),
    ('be41322f-2684-41d2-ac39-4f429d497e9d',  'ACA','GEMINI'),
    ('c9820bc8-4e6d-4068-ae1b-7f292a3668c9',  'FDX','INFLIGHT200'),
    ('8599046c-22be-42cd-8018-75f5aa7623b6',  'THY','BLACKOUT'),
    ('bc7cc99c-9325-4fd1-83b5-d8f30df236d5',  'IND','SKYMARKS');

INSERT INTO T_FLIGHT ( id,AIRPLANE_CODE,ARRIVAL_AIRPORT_CODE,DEPARTURE_TIME,DEPARTURE_AIRPORT_CODE,ARRIVAL_TIME,DURATION )
VALUES
('1c585c40-8507-4109-8001-a85706492faf','GEMINI','HEL', {ts '2022-09-01 18:30:00'},'GRU',{ts '2022-09-01 21:45:00'},195),
('a3db8c43-b4b7-4b65-a05b-27af3769388c','GEMINI200','DXB', {ts '2022-09-17 09:45:00'},'HEK',{ts '2022-09-17 11:00:00'},75),
('78716395-f55f-44b9-9714-7fbd1984d579','INFLIGHT200','IND', {ts '2022-11-14 11:00:00'},'COK',{ts '2022-11-14 13:00:00'},120),
('504528f8-6f9a-4b41-aa24-054339b9ae6c','BLACKOUT','GRT', {ts '2022-12-25 00:30:00'},'INJ',{ts '2022-12-25 06:30:00'},360),
('0c0c8ca2-62c0-460c-8a8f-9e296b754151','SKYMARKS','JAA', {ts '2023-01-04 12:15:00'},'JAC',{ts '2023-01-04 15:00:00'},165);

