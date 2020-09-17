Insert into Providers (name) values
('Amazon'),
('Sephora'),
('Walmart');
commit;

Insert into Rebates (name,link,rebate_type,value,end_time,provider_id) values
('Topcashback','www.topcashback.com','Cashback','0.01','08-10-2021 13:10:02.047','1'),
('Rakuten','www.rakuten.com','Cashback','0.08','08-15-2021 13:10:02.047','2'),
('United Mileage','https://shopping.mileageplus.com/','Credit card points','3','09-10-2020 13:10:02.047','3');
commit;

Insert into Transactions (purchase_time,order_id,amount,rebate_id) values
('6/20/2020 4:00:00','SF000001','100','1'),
('7/14/2020 15:00:00','SG00004','256','3'),
('8/24/2020 10:04:00','46575474','23','2');
commit;