# Web Crawler para Padrão TISS

### Dev: Julielison

Este projeto consiste em um Web Crawler desenvolvido em Groovy para extrair informações do site da Agência Nacional de Saúde Suplementar (ANS) sobre o Padrão TISS, que padroniza a troca de informações entre operadoras de planos de saúde.

## Tecnologias Utilizadas

- **Groovy**
- **JSoup** (para manipulação de HTML)
- **OpenCSV** (para manipulação de arquivos CSV)
- **HttpBuilder** (para download de arquivos)

## Configuração do Ambiente

Este projeto utiliza o **Gradle** como gerenciador de dependências. O arquivo `build.gradle` está configurado para incluir as bibliotecas necessárias.

### Dependências Principais

```groovy
plugins {
    id 'groovy'
    id 'application'
}

group = 'org.julielison'
version = '1.0-SNAPSHOT'

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.codehaus.groovy:groovy-all:3.0.9'
    implementation 'io.github.http-builder-ng:http-builder-ng-core:1.0.4'
    implementation 'org.jsoup:jsoup:1.14.3'
    implementation 'com.opencsv:opencsv:5.5.2'
    implementation 'org.slf4j:slf4j-simple:1.7.36'
}

test {
    useJUnitPlatform()
}

application {
    mainClass = 'org.julielison.Main'
}
```

## Instalação

1. Clone este repositório:
   ```bash
   git clone https://github.com/Julielison/web-crawler.git
   ```
2. Acesse o diretório do projeto:
   ```bash
   cd web-crawler
   ```
3. Certifique-se de ter **Java e Gradle** instalados.
4. Compile o projeto:
   ```bash
   ./gradlew build
   ```

## Uso

1. Execute o programa com o seguinte comando:
   ```bash
   ./gradlew run
   ```
2. Os arquivos baixados e os relatórios gerados estarão no diretório `./Downloads/Aquivos_padrao_TISS/`

## Estrutura do Projeto

```
web-crawler/
├── gradle/wrapper/         
├── src/main/groovy/org/julielison/
│   ├── Main.groovy
│   ├── WebCrawler.groovy  
├── Downloads/
├── .gitignore 
├── build.gradle
├── gradlew  
├── gradlew.bat
├── settings.gradle  
```

## Contribuição

1. Fork o repositório.
2. Crie uma branch para sua funcionalidade (`git checkout -b feature/nova-feature`).
3. Commit suas alterações (`git commit -m 'Adiciona nova funcionalidade'`).
4. Faça push para a branch (`git push origin feature/nova-feature`).
5. Abra um Pull Request.

## Licença

Este projeto está sob a licença MIT. Para mais detalhes, consulte o arquivo `LICENSE`.

