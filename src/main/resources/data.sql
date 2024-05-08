--DELETE FROM product where 1 = 1;
--INSERT INTO `product` (`product_no`, `product_img`, `product_name`, `product_price`) VALUES (1, 'img/product/product_5.png', 'Colorful Stylish Shirt', 150);
--INSERT INTO `product` (`product_no`, `product_img`, `product_name`, `product_price`) VALUES (2, 'img/product/product_1.png', 'Quartz Belt Watch', 100);
--INSERT INTO `product` (`product_no`, `product_img`, `product_name`, `product_price`) VALUES (3, 'img/product/product_2.png', 'Full Skirt Dress', 160);
--INSERT INTO `product` (`product_no`, `product_img`, `product_name`, `product_price`) VALUES (4, 'img/product/product_3.png', 'Beautiful Skirt', 250);
--INSERT INTO `product` (`product_no`, `product_img`, `product_name`, `product_price`) VALUES (5, 'img/product/product_4.png', 'Trendy Blouse', 180);
--INSERT INTO `product` (`product_no`, `product_img`, `product_name`, `product_price`) VALUES (6, 'img/product/product_6.png', 'BodyCon Dress', 130);

DELETE FROM review where 1 = 1;
INSERT INTO review (review_no, file_name, review_title, review_content) VALUES (1, 'upload-dir/product_1.png', '생각했던 원단재질이 아니라 조금실망스러워요', '55사이즈인 분들에게는 조금넉넉한핏입니다');
INSERT INTO review (review_no, file_name, review_title, review_content) VALUES (2, 'upload-dir/product_2.png', '베스트 코디아이템이 될것같습니다', '주줌옷은 좀많이큰편이여서 많이 망설이다가 픽!!! 입어보니 넘만족합니다 적당한여유핏과 가벼운착용감 스판소재');
INSERT INTO review (review_no, file_name, review_title, review_content) VALUES (3, 'upload-dir/product_3.png', '작년에 브라운 구입 후 올해는 네이비 구입했어요..', '네이비도 넘 하늘하늘 이뻐요.. 저는 아이보리 조끼랑 같이 입고 출근했어요..@^^@');
INSERT INTO review (review_no, file_name, review_title, review_content) VALUES (4, 'upload-dir/product_4.png', '잘입겠슴니다.', '제가 좋아하는 스타일이라 맘설임없이 주문했는데. 원단 고급지고 스타일도 예쁘네요. 약간 오버핏이네요.');
INSERT INTO review (review_no, file_name, review_title, review_content) VALUES (5, 'upload-dir/product_5.png', '마음에 듭니다', '옷감이 부드럽고 색상도 환하니 얼굴이 더 밝아 보여서 마음에 듭니다.');
