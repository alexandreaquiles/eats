CREATE TABLE item_pedido (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  observacao varchar(255) DEFAULT NULL,
  quantidade int(11) NOT NULL,
  item_cardapio_id bigint(20) NOT NULL,
  pedido_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (item_cardapio_id) REFERENCES item_do_cardapio(id),
  FOREIGN KEY (pedido_id) REFERENCES pedido(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
