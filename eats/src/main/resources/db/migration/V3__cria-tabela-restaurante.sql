CREATE TABLE restaurante (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  aprovado bit(1) DEFAULT NULL,
  cnpj varchar(255) DEFAULT NULL,
  descricao varchar(1000) DEFAULT NULL,
  nome varchar(255) DEFAULT NULL,
  taxa_de_entrega_em_reais decimal(19,2) NOT NULL,
  tempo_de_entrega_maximo_em_minutos int(11) DEFAULT NULL,
  tempo_de_entrega_minimo_em_minutos int(11) DEFAULT NULL,
  tipo_de_cozinha_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  KEY FK_tipo_de_cozinha_restaurante (tipo_de_cozinha_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
