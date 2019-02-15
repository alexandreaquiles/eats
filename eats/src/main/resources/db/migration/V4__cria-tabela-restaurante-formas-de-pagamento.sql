CREATE TABLE restaurante_formas_de_pagamento (
  restaurante_id bigint(20) NOT NULL,
  formas_de_pagamento_id bigint(20) NOT NULL,
  KEY FK_formas_de_pagamento_restaurante_formas_de_pagamento (formas_de_pagamento_id),
  KEY FK_restaurante_restaurante_formas_de_pagamento (restaurante_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
