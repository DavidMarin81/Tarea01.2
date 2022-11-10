# Tarea01.2
La tarea consiste en leer un documento elecciones.xml con DOM, filtrar los partidos políticos cuyo porcentaje de votos supera un umbral (3% en el ejemplo) y crear un fichero en formato binario con extensión .dat

Se facilita el proyecto disponible en https://github.com/adp-code-2223/Tarea01.2-Enunciado

Para ello, se facilitan una serie de clases e interfaces en el proyecto:

Clase Partido: Contiene los datos de interés de un partido político.
Interfaz IXMLService: Tiene una serie de constantes con los nombres de las etiquetas de interés de un elemento <partido>. Tiene además un único método:  ArrayList<Partido> leerPartidos(String ruta, float umbral) throws LecturaException;
Interfaz IPersistencia: Tiene 2 métodos: void escribir(ArrayList<Partido> partidos, String ruta) y ArrayList<Partido> leerTodo(String ruta);
Clase Main: Se encarga de crear objetos de tipo IXMLService para leer el documento XML y extraer un ArrayList<Partido>. A continuación, deberá crear un objeto que implemente IPersistencia y crear un archivo con ObjectOutputStream. Finalmente, deberá leer los datos escritos en el fichero de salida.
Clase LecturaException: Una excepción personalizada que guarda la ruta del fichero que ha causado un error de lectura.
Los documentos de lectura y generados se ubican/ubicarán en la carpeta src/docs del proyecto
Se pide:
Crear una clase DOMXMLService que implemente IXMLService que lea un documento XML con DOM. La ruta del fichero a leer viene por parámetro. Debe extraer la información de los partidos presentados y devolver un ArrayList<Partido> con aquellos partidos cuyo porcentaje de votos supera un umbral que viene por parámetro. Si se genera alguna excepción, de cualquier tipo, se creará una excepción de tipo LecturaException que incluirá el mensaje de la excepción original y la ruta del fichero.
Crear una clase PartidoObjectPersistencia que implemente IPersistencia con ObjectInputStream y ObjectOutputStream:
El método escribir, sobrescribirá el fichero desde el comienzo sea cual sea su contenido.
En el método leerTodo, controla EOFException para detectar el fin del fichero.
Completa el main de la clase Main para que cumpla con las funciones arriba descritas. Deberá controlarse al menos la excepción LecturaException.
