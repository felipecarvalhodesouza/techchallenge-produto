Funcionalidade: API - Produtos

  Cenário: Registrar um novo produto
	Dado que seja um usuário administrador
    Quando registrar um novo produto
    Então a mensagem é registrada com sucesso
    
  Cenário: Registrar um novo produto
	Dado que seja um usuário comum
    Quando registrar um novo produto
    Então receberá uma mensagem de falta de acesso
