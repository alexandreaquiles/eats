CREATE TABLE endereco_de_entrega (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  cep varchar(8) NOT NULL,
  endereco varchar(255) NOT NULL,
  complemento varchar(255) DEFAULT NULL,
  pedido_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (pedido_id) REFERENCES pedido(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
