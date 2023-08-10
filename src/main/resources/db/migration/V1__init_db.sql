create table ingredient(
    id int auto_increment primary key,
    name char(50),
    isUsing boolean
);

create table dish(
    id int auto_increment primary key,
    name char(50),
    cost int,
    cookingTime int
);

create table ingredients_dishes(
    ingredient_dish_id int primary key auto_increment,
    ingredient_id int,
    dish_id int,
    foreign key(ingredient_id) references ingredient(id),
    foreign key(dish_id) references dish(id)
);

create table bill(
    id int auto_increment primary key,
    tableNumber int,
    total int
);

create table dish_bill(
    dish_bill_id int auto_increment primary key,
    dish_id int,
    bill_id int,
    foreign key(dish_id) references dish(id),
    foreign key(bill_id) references bill(id)
);