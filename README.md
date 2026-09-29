# SecureBank - Fraud Detection API 🛡️💳

O **SecureBank** é um sistema inteligente de detecção de fraudes bancárias desenvolvido em **Java** e **Spring Boot**. A aplicação utiliza técnicas de Inteligência Artificial e Machine Learning, especificamente a biblioteca **Weka**, para analisar e classificar transações financeiras como legítimas ou fraudulentas.

Através do algoritmo de Árvore de Decisão (**J48**), o sistema é treinado com um conjunto de dados históricos e é capaz de prever anomalias com base em padrões, como o valor da transação e a sua origem (nacional ou internacional).

## 🚀 Tecnologias Utilizadas

*   **Java 17+**
*   **Spring Boot** (Framework para a criação da aplicação e API REST)
*   **Weka API** (Biblioteca de Machine Learning para mineração de dados e algoritmos de predição)
*   **Maven** (Gerenciamento de dependências)

## ⚙️ Como Executar o Projeto

1. Certifique-se de ter o **Java 17** (ou superior) e o **Maven** instalados na sua máquina.
2. Clone este repositório:
   ```bash
   git clone https://github.com/seu-usuario/securebank-fraud-detection.git
   ```
3. Acesse a pasta do projeto:
   ```bash
   cd securebank-fraud-detection
   ```
4. Baixe as dependências e inicie a aplicação Spring Boot:
   ```bash
   mvn spring-boot:run
   ```

## 🧠 Como o Modelo Funciona

O núcleo da aplicação (`DeteccaoDeFraudeBancaria.java`) cria um conjunto de dados na memória (*Instances*) contendo atributos como:
*   `valor` (Numérico): O montante da transação.
*   `origem` (Nominal): Se a transação foi "nacional" ou "internacional".
*   `fraude` (Nominal): A classe alvo, indicando se é "sim" ou "não".

O modelo é treinado usando a classe `J48` da biblioteca Weka. Ao receber uma nova transação, o sistema a submete à árvore de decisão gerada, retornando a probabilidade daquela transação ser uma fraude.

## 🔮 Futuras Features (Possibilidades de Melhorias)

Este projeto tem um grande potencial de escalabilidade. Abaixo estão algumas melhorias planejadas para as próximas versões:

*   [ ] **Exposição via API REST:** Criar *Controllers* no Spring Boot para receber transações via requisições HTTP (ex: `POST /api/v1/transactions/analyze`) e retornar a classificação em formato JSON.
*   [ ] **Integração com Banco de Dados:** Substituir os dados de treinamento em memória (Hardcoded) por dados reais extraídos de um banco de dados relacional (PostgreSQL/MySQL) usando Spring Data JPA.
*   [ ] **Novos Atributos de Análise:** Incluir mais variáveis para aumentar a precisão do modelo, como:
    *   Horário da transação (madrugada x horário comercial).
    *   Dispositivo utilizado (novo dispositivo x dispositivo de confiança).
    *   Histórico de gastos do usuário.
    *   Geolocalização (IP).
*   [ ] **Persistência do Modelo:** Implementar a funcionalidade de salvar o modelo treinado em um arquivo `.model` e carregá-lo ao iniciar o Spring Boot. Isso evita a necessidade de retreinar o algoritmo a cada reinicialização da API.
*   [ ] **Testes de Múltiplos Algoritmos:** Avaliar o desempenho de outros algoritmos além do J48 (como *Random Forest*, *Naive Bayes* ou *Multilayer Perceptron*) e utilizar o que apresentar maior acurácia.
*   [ ] **Dashboard de Monitoramento:** Desenvolver uma interface web simples para visualizar estatísticas em tempo real das transações barradas e aprovadas.
*   [ ] **Retreinamento Contínuo:** Criar um *Job* (usando `@Scheduled` do Spring) para retreinar o modelo periodicamente com os dados das fraudes mais recentes identificadas pela equipe de segurança.

## 🤝 Contribuição

Sinta-se à vontade para fazer um *fork* do projeto, abrir *issues* ou enviar *pull requests* com sugestões e melhorias. Toda ajuda é bem-vinda!
