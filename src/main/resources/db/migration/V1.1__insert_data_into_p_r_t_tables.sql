Insert into Providers (name) values
('Amazon'),
('Sephora'),
('Walmart');
commit;

Insert into Rebates (name,link,rebate_type,value,provider_id) values
('Topcashback','www.topcashback.com','Cashback','0.01','1'),
('Rakuten','www.rakuten.com','Cashback','0.08','2'),
('United Mileage','https://shopping.mileageplus.com/','Credit card points','3','3');
commit;

Insert into Orders (purchase_time,order_number,amount,rebate_id) values
('2020-05-01 12:30:00','SF000001','100','1'),
('2020-06-01 18:30:40','SG00004','256','3'),
('2020-09-14 09:30:00','46575474','23','2');
commit;

