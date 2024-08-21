## Cadastro de um novo autor
### necessidades

* É necessário cadastrar um novo autor no sistema. 
* Todo autor tem um nome, email e uma descrição. 
* Também queremos saber o instante exato que ele foi registrado.

### restrições
* O instante não pode ser nulo
* O email é obrigatório
* O email tem que ter formato válido
* O nome é obrigatório
* A descrição é obrigatória e não pode passar de 400 caracteres

### resultado esperado
* Um novo autor criado e status 200 retornado

### Tritutar
 * Entidade Autor (@Entity)
   * atributos
     * Long id (auto-increment e chave-primaria)
     * String nome -> @NotNull @NotEmpty
     * String email -> @NotNull @NotEmpty @Email
     * String descricao -> @NotNull e limite < 400
     * Datetime createdAt -> @NotNull
 * AutorRepository
   * extender JPA
 * AutorController
     * método salvaAutor
       * POST
       * retorno ResponseBody
       * parâmetro autorDTO
         * usar @Valid
         * user @RequestBody


## Email do autor é único
### necessidades
 - O email do autor precisa ser único no sistema
### resultado esperado
 - Erro de validação no caso de email duplicado

### Tritutar
 * AutorRepository
   * método findByEmail
 * AutorController
   * método salvaAutor
     * usar findByEmail
     * lançar exceção caso email já exista