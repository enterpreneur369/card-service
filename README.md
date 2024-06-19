# Servicio Card de Bank Inc

## Antes de Iniciar

Empezaremos por explicar los diferentes componentes del proyectos y partiremos de los componentes externos, continuando con los componentes core de negocio (dominio) y por último el inicio y configuración de la aplicación.

Lee el artículo [Clean Architecture — Aislando los detalles](https://medium.com/bancolombia-tech/clean-architecture-aislando-los-detalles-4f9530f35d7a)

# Arquitectura

![Clean Architecture](https://miro.medium.com/max/1400/1*ZdlHz8B0-qu9Y-QO3AXR_w.png)

## Domain

Es el módulo más interno de la arquitectura, pertenece a la capa del dominio y encapsula la lógica y reglas del negocio mediante modelos y entidades del dominio.

## Usecases

Este módulo gradle perteneciente a la capa del dominio, implementa los casos de uso del sistema, define lógica de aplicación y reacciona a las invocaciones desde el módulo de entry points, orquestando los flujos hacia el módulo de entities.

## Infrastructure

### Helpers

En el apartado de helpers tendremos utilidades generales para los Driven Adapters y Entry Points.

Estas utilidades no están arraigadas a objetos concretos, se realiza el uso de generics para modelar comportamientos
genéricos de los diferentes objetos de persistencia que puedan existir, este tipo de implementaciones se realizan
basadas en el patrón de diseño [Unit of Work y Repository](https://medium.com/@krzychukosobudzki/repository-design-pattern-bc490b256006)

Estas clases no puede existir solas y debe heredarse su compartimiento en los **Driven Adapters**

### Driven Adapters

Los driven adapter representan implementaciones externas a nuestro sistema, como lo son conexiones a servicios rest,
soap, bases de datos, lectura de archivos planos, y en concreto cualquier origen y fuente de datos con la que debamos
interactuar.

#### JPACardRepository
Repositorio para obtener la data de la tabla cards

#### JPAClientRepository
Repositorio para obtener la data de la tabla clients

#### JPATransactionRepository
Repositorio para obtener la data de la tabla transactions

### Entry Points

Los entry points representan los puntos de entrada de la aplicación o el inicio de los flujos de negocio.

#### ApiRest
Es el entry point para las distintas operaciones con las tarjetas, en esta podemos realizar las siguientes 
 funcionalidades:

* Generar número de tarjeta
* Activar tarjeta
* Bloquear tarjeta
* Recarcar saldo
* Consulta de saldo
* Transacción de compra
* Consultar transacción

## Application

Este módulo es el más externo de la arquitectura, es el encargado de ensamblar los distintos módulos, resolver las dependencias y crear los beans de los casos de use (UseCases) de forma automática, inyectando en éstos instancias concretas de las dependencias declaradas. Además inicia la aplicación (es el único módulo del proyecto donde encontraremos la función “public static void main(String[] args)”.

**Los beans de los casos de uso se disponibilizan automaticamente gracias a un '@ComponentScan' ubicado en esta capa.**


## ¿Cómo probar la aplicación en local?

1. Para poder probar la aplicación en local, es necesario
tener una base de datos postgresql y es necesario
configurar el ide con estas variables de entorno

* DATABASE_USER = La que corresponde a su base de datos local
* DATABASE_PASSWORD = La que corresponde a su base de datos local
* DATABASE_NAME = La que corresponde a su base de datos local

2. Se genera la data de prueba inicial con clientes y tarjetas sin activar.
Para ello es necesario reemplazar el main por este código cuando ya haya data poblada para las pruebas 
se puede dejar como estaba antes.

~~~
import com.bankinc.model.card.Card;
import com.bankinc.model.card.CardState;
import com.bankinc.model.card.gateways.CardRepository;
import com.bankinc.model.client.Client;
import com.bankinc.model.client.gateways.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class MainApplication implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CardRepository cardRepository;

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Crear clientes
        Client client1 = new Client(null, "John", "Doe");
        Client client2 = new Client(null, "Jane", "Smith");
        Client client3 = new Client(null, "Bob", "Brown");

        // Guardar los clientes y obtener las instancias persistidas
        Client savedClient1 = clientRepository.saveClient(client1);
        Client savedClient2 = clientRepository.saveClient(client2);
        Client savedClient3 = clientRepository.saveClient(client3);

        // Crear tarjetas usando las instancias de clientes guardadas
        Card card1 = new Card(null, null, savedClient1, LocalDateTime.now().plusYears(3), CardState.INACTIVE, 0L, "USD", null);
        Card card2 = new Card(null, null, savedClient2, LocalDateTime.now().plusYears(3), CardState.INACTIVE, 0L, "USD", null);
        Card card3 = new Card(null, null, savedClient3, LocalDateTime.now().plusYears(3), CardState.INACTIVE, 0L, "USD", null);

        // Guardar las tarjetas
        cardRepository.saveCard(card1);
        cardRepository.saveCard(card2);
        cardRepository.saveCard(card3);
    }
}
~~~

3. Se procede a iniciar el proyecto