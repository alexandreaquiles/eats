CREATE TABLE restaurante (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  aprovado bit(1) DEFAULT NULL,
  cnpj varchar(255) DEFAULT NULL,
  descricao varchar(1000) DEFAULT NULL,
  cep varchar(8) NOT NULL,
  endereco varchar(300) NOT NULL,
  nome varchar(255) DEFAULT NULL,
  taxa_de_entrega_em_reais decimal(19,2) DEFAULT NULL,
  tempo_de_entrega_maximo_em_minutos int(11) DEFAULT NULL,
  tempo_de_entrega_minimo_em_minutos int(11) DEFAULT NULL,
  tipo_de_cozinha_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (tipo_de_cozinha_id) REFERENCES tipo_de_cozinha(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
