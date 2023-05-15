CREATE TABLE `t_users` (
    `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `username` varchar(255)  NOT NULL COMMENT '账号',
    `password` varchar(255)  NOT NULL COMMENT '密码',
    `role` int DEFAULT NULL COMMENT '角色 1 管理员 2 普通用户',
    PRIMARY KEY (`id`)
)  COMMENT='登录用户表'

INSERT INTO t_users (id, username, password, `role`) VALUES(1, 'admin', 'admin', 1);
INSERT INTO t_users (id, username, password, `role`) VALUES(2, 'user', 'user', 2);

CREATE TABLE `t_exercise_records` (
      `id` int NOT NULL AUTO_INCREMENT COMMENT '主键，唯一标识每条记录',
      `user_id` int NOT NULL COMMENT '关联用户表的外键，表示记录所属用户',
      `exercise_type` varchar(50)  NOT NULL COMMENT '记录运动类型，例如跑步、游泳等',
      `exercise_intensity` varchar(50)  NOT NULL COMMENT '记录运动的强度级别，例如低强度、中等强度、高强度',
      `calories_burned` float NOT NULL COMMENT '记录运动消耗的卡路里值',
      `start_time` datetime NOT NULL COMMENT '记录运动开始的日期和时间',
      `end_time` datetime NOT NULL COMMENT '记录运动结束的日期和时间',
      PRIMARY KEY (`id`),
      KEY `user_id` (`user_id`),
      CONSTRAINT `t_exercise_records_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_users` (`id`)
) COMMENT='运动消耗记录表'

INSERT INTO t_exercise_records (id, user_id, exercise_type, exercise_intensity, calories_burned, start_time, end_time) VALUES(1, 1, 'Running', 'High', 300.0, '2023-05-15 09:00:00', '2023-05-15 10:00:00');
INSERT INTO t_exercise_records (id, user_id, exercise_type, exercise_intensity, calories_burned, start_time, end_time) VALUES(2, 1, 'Cycling', 'Medium', 200.0, '2023-05-15 14:00:00', '2023-05-15 15:30:00');
INSERT INTO t_exercise_records (id, user_id, exercise_type, exercise_intensity, calories_burned, start_time, end_time) VALUES(3, 1, 'Swimming', 'Low', 150.0, '2023-05-15 18:00:00', '2023-05-15 19:00:00');
INSERT INTO t_exercise_records (id, user_id, exercise_type, exercise_intensity, calories_burned, start_time, end_time) VALUES(4, 1, 'Walking', 'Low', 100.0, '2023-05-15 11:00:00', '2023-05-15 11:30:00');


CREATE TABLE `t_food_value` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '食物排行榜信息的唯一标识符，使用自增长的整数类型',
  `name` varchar(50)  NOT NULL COMMENT '食物名称，使用字符串类型，长度为50个字符',
  `nutrition_value` varchar(100)  NOT NULL COMMENT '食物的营养价值，使用字符串类型，长度为100个字符，包含食物富含的维生素、蛋白质、矿物质等信息',
  `calorie` int NOT NULL COMMENT '食物的热量含量，使用整数类型，表示每100克食物所含的卡路里数。',
  PRIMARY KEY (`id`)
)  COMMENT='食物价值表'


INSERT INTO t_food_value(id, name, nutrition_value, calorie) VALUES(1, '苹果', '富含维生素C、膳食纤维、矿物质等', 52);
INSERT INTO t_food_value(id, name, nutrition_value, calorie) VALUES(2, '鸡胸肉', '富含蛋白质、低脂肪、低热量', 165);
INSERT INTO t_food_value(id, name, nutrition_value, calorie) VALUES(3, '三文鱼', '富含Omega-3脂肪酸、蛋白质、维生素D等', 206);
INSERT INTO t_food_value(id, name, nutrition_value, calorie) VALUES(4, '花生', '富含蛋白质、单不饱和脂肪酸、矿物质等', 567);
INSERT INTO t_food_value(id, name, nutrition_value, calorie) VALUES(5, '鸡蛋', '富含蛋白质、维生素D、矿物质等', 155);
INSERT INTO t_food_value(id, name, nutrition_value, calorie) VALUES(6, '杏仁', '富含维生素E、蛋白质、纤维等', 579);
INSERT INTO t_food_value(id, name, nutrition_value, calorie) VALUES(7, '黑巧克力', '富含抗氧化剂、镁、铁等', 546);
INSERT INTO t_food_value(id, name, nutrition_value, calorie) VALUES(8, '菠菜', '富含维生素K、维生素A、膳食纤维等', 23);
INSERT INTO t_food_value(id, name, nutrition_value, calorie) VALUES(9, '南瓜', '富含维生素A、膳食纤维、矿物质等', 26);
INSERT INTO t_food_value(id, name, nutrition_value, calorie) VALUES(10, '西兰花', '富含维生素C、维生素K、膳食纤维等', 34);



CREATE TABLE `t_recipes` (
    `id` int NOT NULL AUTO_INCREMENT COMMENT '每个食谱的唯一标识符（主键）',
    `recipe_name` varchar(255)  NOT NULL COMMENT '食谱的名称或标题',
    `recipe_description` text  COMMENT '对食谱的简要描述或介绍',
    `author` varchar(255)  DEFAULT NULL COMMENT '食谱的作者或创建者',
    `prep_time` int DEFAULT NULL COMMENT '准备食谱所需的时间',
    `cooking_time` int DEFAULT NULL COMMENT '烹饪食谱所需的时间',
    `serving_size` int DEFAULT NULL COMMENT '该食谱适合的人数',
    `difficulty` enum('初级','中级','高级')  DEFAULT NULL COMMENT '食谱的烹饪难度等级，如初级、中级、高级等',
    `ingredients` text  COMMENT '列出用于制作该食谱的原料及其相应的数量',
    `instructions` text  COMMENT '按照顺序列出制作食谱的步骤说明',
    PRIMARY KEY (`id`)
) COMMENT='食谱表'


INSERT INTO t_recipes(id, recipe_name, recipe_description, author, prep_time, cooking_time, serving_size, difficulty, ingredients, instructions) VALUES(1, '意大利面', '一道经典的意式面食', 'Maria', 15, 30, 4, '中级', '意大利面条, 西红柿酱, 橄榄油, 大蒜, 盐, 胡椒粉, 干香料, 帕尔玛干酪', '1. 煮意大利面条至熟透；2. 加热橄榄油，加入大蒜炒香；3. 加入西红柿酱和调味料，煮沸；4. 拌入煮熟的意大利面；5. 撒上帕尔玛干酪。');
INSERT INTO t_recipes(id, recipe_name, recipe_description, author, prep_time, cooking_time, serving_size, difficulty, ingredients, instructions) VALUES(2, '烤鸡', '美味的烤鸡配菜', 'John', 20, 60, 6, '中级', '全鸡, 盐, 胡椒粉, 干香料, 橄榄油, 鸡肉汁', '1. 鸡洗净，涂抹橄榄油和香料；2. 烤箱预热至180°C；3. 把鸡放入烤盘中；4. 烤1小时，定期翻面；5. 内部温度达到75°C；6. 鸡取出，静置10分钟；7. 切块并撒上鸡肉汁。');
INSERT INTO t_recipes(id, recipe_name, recipe_description, author, prep_time, cooking_time, serving_size, difficulty, ingredients, instructions) VALUES(3, '牛肉炖土豆', '慢炖牛肉与土豆的美味组合', 'Sarah', 30, 120, 8, '初级', '牛肉块, 土豆, 洋葱, 胡萝卜, 鸡肉汤, 番茄酱, 干香料, 盐, 胡椒粉', '1. 牛肉块煎至棕色；2. 加入洋葱、胡萝卜和土豆；3. 加入鸡肉汤和番茄酱；4. 加入盐、胡椒粉和香料；5. 慢炖2小时，直到肉变嫩；6. 调整味道并上桌。');


CREATE TABLE `t_weight_advice` (
   `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
   `min_weight` decimal(5,2) NOT NULL COMMENT '体重范围',
   `max_weight` decimal(5,2) NOT NULL COMMENT '体重范围',
   `advice` varchar(500)  NOT NULL COMMENT '针对该体重范围的建议',
   PRIMARY KEY (`id`)
) COMMENT='针对不同范围内的体重的人的建议表'

INSERT INTO t_weight_advice(id, min_weight, max_weight, advice) VALUES(1, 50.00, 60.00, '您的体重正常，请继续保持健康的生活方式。');
INSERT INTO t_weight_advice(id, min_weight, max_weight, advice) VALUES(2, 60.01, 70.00, '您的体重有些过重，建议适当控制饮食并增加运动量。');
INSERT INTO t_weight_advice(id, min_weight, max_weight, advice) VALUES(3, 70.01, 80.00, '您的体重已经超重，需要立即采取措施进行减肥。建议咨询专业医生或营养师。');



CREATE TABLE `t_weight_records` (
    `id` int NOT NULL AUTO_INCREMENT COMMENT '记录的唯一标识符',
    `record_time` datetime DEFAULT NULL COMMENT '记录体重的日期和时间',
    `user_id` int DEFAULT NULL COMMENT '关联用户表中的唯一标识符，用于指示哪个用户的体重记录。',
    `weight` decimal(5,2) DEFAULT NULL COMMENT '用户在给定时间点的体重',
    `note` text  COMMENT '可选字段，用于记录任何额外信息，例如用户的心情、饮食等',
    PRIMARY KEY (`id`),
    KEY `user_id` (`user_id`),
    CONSTRAINT `t_weight_records_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户体重记录表'



INSERT INTO t_weight_records(id, record_time, user_id, weight, note) VALUES(21, '2023-05-01 09:00:00', 1, 70.20, '正常');
INSERT INTO t_weight_records(id, record_time, user_id, weight, note) VALUES(22, '2023-05-02 08:30:00', 1, 69.80, '正常');
INSERT INTO t_weight_records(id, record_time, user_id, weight, note) VALUES(23, '2023-05-03 07:45:00', 1, 69.50, '正常');
INSERT INTO t_weight_records(id, record_time, user_id, weight, note) VALUES(24, '2023-05-04 09:15:00', 1, 69.30, '正常');
INSERT INTO t_weight_records(id, record_time, user_id, weight, note) VALUES(25, '2023-05-05 10:00:00', 1, 69.00, '正常');
INSERT INTO t_weight_records(id, record_time, user_id, weight, note) VALUES(26, '2023-05-06 09:30:00', 1, 68.70, '正常');
INSERT INTO t_weight_records(id, record_time, user_id, weight, note) VALUES(27, '2023-05-07 08:45:00', 1, 68.50, '正常');
INSERT INTO t_weight_records(id, record_time, user_id, weight, note) VALUES(28, '2023-05-08 07:30:00', 1, 68.20, '正常');
INSERT INTO t_weight_records(id, record_time, user_id, weight, note) VALUES(29, '2023-05-09 09:00:00', 1, 68.00, '正常');
INSERT INTO t_weight_records(id, record_time, user_id, weight, note) VALUES(30, '2023-05-10 08:15:00', 1, 67.80, '正常');
INSERT INTO t_weight_records(id, record_time, user_id, weight, note) VALUES(31, '2023-05-01 08:00:00', 1, 65.00, '正常');
INSERT INTO t_weight_records(id, record_time, user_id, weight, note) VALUES(32, '2023-05-02 07:30:00', 1, 64.50, '正常');
INSERT INTO t_weight_records(id, record_time, user_id, weight, note) VALUES(33, '2023-05-03 09:15:00', 1, 64.20, '正常');
INSERT INTO t_weight_records(id, record_time, user_id, weight, note) VALUES(34, '2023-05-04 08:45:00', 1, 64.00, '正常');
INSERT INTO t_weight_records(id, record_time, user_id, weight, note) VALUES(35, '2023-05-05 07:30:00', 1, 63.80, '正常');
INSERT INTO t_weight_records(id, record_time, user_id, weight, note) VALUES(36, '2023-05-06 09:00:00', 1, 63.50, '正常');
INSERT INTO t_weight_records(id, record_time, user_id, weight, note) VALUES(37, '2023-05-07 08:15:00', 1, 63.20, '正常');
INSERT INTO t_weight_records(id, record_time, user_id, weight, note) VALUES(38, '2023-05-08 07:45:00', 1, 63.00, '正常');
INSERT INTO t_weight_records(id, record_time, user_id, weight, note) VALUES(39, '2023-05-09 09:30:00', 1, 62.80, '正常');
INSERT INTO t_weight_records(id, record_time, user_id, weight, note) VALUES(40, '2023-05-10 08:00:00', 1, 62.50, '正常');