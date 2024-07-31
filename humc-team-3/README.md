# HUMC Start Repository

Bij deze de start-repository voor de HUMC opdracht.

In deze repository start een lege Spring-boot applicatie. Na opstarten kun je alvast kijken op

* http://localhost:8080/actuator voor allerhande debug info (heel soms handig)
* http://localhost:8080/h2-console voor je database (het is niet Postgres, maar ik denk dat je de weg wel vindt)

#### Om gebruik te maken van de Postgres database dient er van tevoren handmatig een database toegevoegd te worden aan 'postgres@localhost' genaamd 'monoliet'.

## Gemaakte functionaliteiten

## Koen
### Onderzoek
* researcher toevoegen
* status wijzigen
* onderzoek gegevens ophalen
* onderzoek aanmaken

## Ruben
### Roostering
* create rooster
* get rooster
* create medewerker
* update medewerkers email
* get medewerker
* create roosterpunt met medewerker (organisator)
* add medewerker to roosterpunt - met complexe 'isBeschikbaar(Class, begintijd, eindtijd)' functie
* add apparaat to roosterpunt - met complexe 'isBeschikbaar(Class, begintijd, eindtijd)' functie
* add locatie to roosterpunt - met complexe 'isBeschikbaar(Class, begintijd, eindtijd)' functie

## Corné
### laboratorium/ruimte beheer
* add laboratorium
* replace beheerder
* add middel
* add apparaat
* get ruimte
* delete apparaat
* delete middel
* (Niet-triviale usecase) zodra een middel over de datum is wordt het aangegeven bij de get ruimte

### NOSQL (Neo4j & Mongo)
ik heb het domeinmodel aangepast voor neo4j. Het "oude" domeinmodel voor postgresql staat nog in de code om inzicht te geven in de verandering en om mogelijk weer
gebruik te maken van postgreSQL. In het 'Roostering' project is gebruik gemaakt van de nosql methode van mongo. Deze code is verwerkt in de
domeinlaag.



### messaging
* Check Benodigdheden beschikbaar
- koen stuurt message met benodigdheden voor een onderzoek naar de queue ("requestacces"). zie (RequestBeschikbareItemsMessage.java) in ruimtebeheer voor d emessage
- Corne haalt de message op en checkt of de middelen beschikbaar zijn en geeft het aantal beschikbare middelen en een beschikbare ruimte terug naar de queue("accesresponse") zie (BeschikbareItemsMessage.java) voor de message
- Koen stuurt een message met een onderzoek ("startonderzoek") naar Ruben om een roosterpunt aan te maken voor dit onderzoek.
- Ruben stuurt een message naar Koen of het is gelukt of niet is gelukt om een onderzoek in te roosteren ("startonderzoekresponse").
- Ruben stuurt een message om een verzoek te doen om een middel te verplaatsen naar een nieuwe ruimte ("verplaatsmiddel")
- Corne haalt de message op en voegt het verzoek toe aan de te verplaatsen middelen in een inventaris
- Corne stuurt een message naar de queue (middelkwijt) zodra een middel zich niet meer in de ruimte bevindt en niet verplaatst is naar een andere ruimte
- Ruben ontvangt de message en verwijdert het uit een roosterpunt
