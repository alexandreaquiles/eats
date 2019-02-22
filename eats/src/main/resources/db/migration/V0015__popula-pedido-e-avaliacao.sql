INSERT INTO pedido (id, data_hora, status) values (1, NOW(), 'ENTREGUE');

INSERT INTO item_pedido (id, quantidade, item_cardapio_id, pedido_id) values (1, 1, 4, 1);
INSERT INTO item_pedido (id, quantidade, item_cardapio_id, pedido_id) values (2, 1, 8, 1);

INSERT INTO avaliacao (id, nota, pedido_id, restaurante_id) values (1, 4, 1, 1);
