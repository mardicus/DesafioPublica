Programa criado para uma questão prática requisitada pela Pública Técnologia.
  O programa recebe a pontuação de jogos de diferentes temporadas, insere-os em uma tabela e ordena-os em ordem crescente, o programa também calcula e exibe na tabela a maior e a menor pontuação da temporada e quantas vezes na temporada um recorde mínimo ou máximo foi quebrado. O programa aceita números inteiros inseridos pelo usuário e também aceita arquivos CSV (separados por vírgula), ele também pode salvar os dados da tabela em um arquivo CSV. O valor máximo permitido é 1000 por pontuação.

  Para utilizar o programa é necessária uma IDE que suporte Java, essa IDE deve ser configurada para utilizar JDK-15 e deve ter o JavaFX-15 e o JUnit5 já integrados. 
  Após configurar a IDE, basta abrir o projeto e executar a classe Main.java no pacote Application da pasta src como aplicação Java. Obs: Caso ocorra erro na execução pode ser necessário utilizar os seguintes VM arguments referentes ao caminho do java-fx na execução  do programa:
  --module-path 'caminho da pasta lib do JavaFX-SDK-15 --add-modules=javafx.fxml,javafx.controls
O programa possui testes unitários integrados que utilizam JUnit5 para verificar se as funções básicas do mesmo estão funcionando.
