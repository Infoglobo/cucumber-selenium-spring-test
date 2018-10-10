# language: pt
Funcionalidade: Repositório de organização no GitHub

  Esquema do Cenário: Procurar membro participante da organização
    Dado que o usuário esteja na página da Infoglobo
    Quando clicar na categoria Pessoas
    E procurar por <membro>
    Então deve retornar um único membro

  Exemplos:
  |       membro      |
  | "Willian Antunes" |
