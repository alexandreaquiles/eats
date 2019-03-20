CREATE TABLE entrega (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  nome_do_cliente varchar(100) DEFAULT NULL,
  cep varchar(8) NOT NULL,
  endereco varchar(255) DEFAULT NULL,
  complemento varchar(255) DEFAULT NULL,
  pedido_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (pedido_id) REFERENCES pedido(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
