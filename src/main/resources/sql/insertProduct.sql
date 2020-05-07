insert into public.products
(add_date, amount, category, code, color, cost, amount_left, markup, name, price, s_cost, s_price, size, storage)
values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
ON CONFLICT(code) DO UPDATE SET
add_date = ?,
amount = ?,
category = ?,
color = ?,
cost = ?,
amount_left = ?,
markup = ?,
name = ?,
price = ?,
s_cost = ?,
s_price = ?,
size = ?,
storage = ?