# pokedex2
Pokedex V0.05


Este es un Pokedex que realizamos para la Clase de Taller de Programacion 3.

En esta aplicacion utilizamos los siguientes complementos:

Volley: se utiliza para realizar llamadas REST y poder consumir APIs, con Volley llamamos a PokeAPI para poder traer los datos de los 
Pokemons.
estos Vienen en JSON en consecuencia tenemos que tomar los datos que necesitemos del JSON para poder mostrarlos en pantalla. 
En este LINK se encuentra la documentacion de Volley: https://developer.android.com/training/volley


Picasso: se utiliza para poder mostrar imagenes desde Links, tiene un funcionamiento muy simple y potente. 
En el siguiente LINK se encuentra la docuemntacion de Picasso: https://square.github.io/picasso/


El funcionamiento es muy simple, se ingresa un nombre o  numero de POkemon - cuando se aprieta el boton de Busqueda se lanza el evento
que invoca volley, este envia un request a la POKEAPI y una vez que tengo la respuesta trabajo sobre el JSON que me devuelve. 
