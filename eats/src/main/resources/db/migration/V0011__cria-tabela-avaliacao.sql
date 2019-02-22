CREATE TABLE avaliacao (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  comentario varchar(255) DEFAULT NULL,
  nota int(11) NOT NULL,
  pedido_id bigint(20) NOT NULL,
  restaurante_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (pedido_id) REFERENCES pedido(id),
  FOREIGN KEY (restaurante_id) REFERENCES restaurante(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
