Пет проект небольшого маркета 

Проект появился после прочтения книги Ричардсона "Паттерны микросервисной архитектуры", чтобы опробовать идеи из книги на практике.
А так-же попытка спроектировать небольшую архитектуру микросервисов через UML и C4.

Здесь есть ряд небольших микросервисов в монорепозитории, маленький фронт на react
Все микросервисы написаны в ассинхронном стиле с корутинами и webflux, протокол общения между МКС - gRPC.

Применен паттерн SAGA - Оркестрация. Использовалась Camunda 8, в последствии Temporal.
Опробован шаблон Event Driven. Проектирование с использованием Event Storming и основных DDD подходов (самых базовых)
развернут кластер в minikube. ссылка на хелмы - https://github.com/basmingo/market-test-helm
кубер разворачивается через Argo CD 

База данных H2, для простоты развертывания
в файле Basmarket.io - схемы UML и архитектура. 
