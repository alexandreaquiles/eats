CREATE TABLE cardapio (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  restaurante_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_restaurante_cardapio (restaurante_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
