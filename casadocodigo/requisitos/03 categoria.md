## Cadastro de uma categoria
### necessidades
 - Toda categoria precisa de um nome
### restrições
 - O nome é obrigatório
 - O nome não pode ser duplicado
### resultado esperado
 - Uma nova categoria cadastrada no sistema e status 200 retorno
 - Caso alguma restrição não seja atendida, retorne 400 e um json informando os problemas de validação

### Triturar
    * Entidade Categoria (@Entity)
    * atributos
        * Long id (auto-increment e chave-primaria)
        * String nome -> @NotNull @NotEmpty
    * CategoriaRepository
    * extender JPA
    * CategoriaController
        * método salvaCategoria
        * POST
        * retorno ResponseBody
        * parâmetro categoriaDTO
            * usar @Valid
            * usar @RequestBody