package es.ubu.lsi.edat.pract08;

import java.util.Collection;
import java.util.Map;
//import java.util.Set;

/**
 * Este interfaz define una colección similar al interfaz Map de Java.
 * Se pretende adaptar el interfaz Map al uso de dos claves en la medida de lo posible.
 * Se puede consultar: https://docs.oracle.com/javase/8/docs/api/java/util/Map.html
 * para obtener más detalles.  
 * 
 * Esta colección asociará un par de claves ordenadas, llamadas fila y columna con un único
 * valor. Esta tabla puede ser dispersa, teniendo solo una fracción de elementos definidos 
 * por su fila y columna ocupados por un valor.
 * 
 * Las asociaciones correspondientes a una determinada fila, se pueden ver como un mapa cuyas
 * claves son los objetos que deteminan la columna, incluyendo pares: clave de columna / valor. 
 * El inverso también es posible, asociando el obtener los elementos de una misma columna como
 * pares de: claves de fila / valores.
 * 
 * En muchas implementaciones el acceso a los datos por columnas puede tener una eficiencia mucho
 * menor que el acceso por filas.
 * 
 * Los metodos que devuelven colecciones o mapas devolverán vistas sobre la propia Tabla, de manera
 * que los cambios en sus elementos se producirán también en los elementos almacenados en la misma. 
 * 
 * 
 * @author bbaruque
 *
 * @param <R> Tipo de datos que se emplean como clave en las filas de la tabla
 * @param <C> Tipo de datos que se emplean como clave en las columnas de la tabla
 * @param <V> Tipo de datos que se emplean como valor en la tabla
 */

public interface Table <R,C,V> {

	// Métodos de modificación y consulta del contenido //

	/**
	 * Asocia el valor especificado con su correspondiente par de claves.
	 * Si la tabla ya contiene una asociacion con esas mismas claves, el valor anterior se
	 * reemplaza por el valor introducido como parámetro
	 * 
	 * @param row Clave de fila con la que se asocia el valor
	 * @param column Clave de columna con la que se asocia el valor
	 * @param value Valor a asociar a las dos claves determinadas
	 * @return Valor previamente asociado a ambas claves o nulo si no existía esa asociación
	 */
	public V put (R row, C column, V value);

	/**
	 * Elimina la asociación entre las dos claves y su valor, si existía anteriormente.
	 * Se eliminarán los 3 elementos de la tabla, no solo el valor.
	 * 
	 * @param row Clave del fila de la asociación a ser eliminada
	 * @param column Clave del columna de la asociación a ser eliminada
	 * @return Valor previamente asociado a ambas claves o nulo si no existía esa asociación
	 */
	public V remove (R row, C column);

	/**
	 * Devuelve el valor correspondiente asociado a una determinada combinación de claves de fila
	 * y de columna o null si no existe esa asociación.
	 * 
	 * @param row Clave del fila de la asociación correspondiente al valor a recuperar
	 * @param column Clave de columna de la asociación correspondiente al valor a recuperar
	 * @return Valor asociado a las dos claves facilitadas o null si no existe esa asociación
	 */
	public V get (Object row, Object column);

	/**
	 * Devuelve verdadero si la tabla contienen una asociación que incluya las claves de fila
	 * y de columna que se especifican
	 * 
	 * @param row Clave del fila de la asociación que se pretende consultar
	 * @param column Clave de columna de la asociación que se pretende consultar
	 * @return true si existe la asociacion y false en otro caso
	 */
	public boolean containsKeys (Object row, Object column);

	/**
	 * Devuelve verdadero si la tabla contienen un valor igual al que se especifica como
	 * parámetro, sin importar las claves a las que esté asociado.
	 * 
	 * @param value valora a buscar
	 * @return true si la tabla contiene ese valor y falso en caso contrario
	 */
	public boolean containsValue (V value);

	// Métodos de consulta como Map//

	/**
	 * Devuelve una vista de todas las asociaciones que coniciden en emplear la misma clave 
	 * de fila. Por cada asociación de clave de fila / clave de columna / valor en la tabla
	 * original, el mapa devuelto contendrá la correspondiente asociación de clave de columna / valor.
	 * 
	 * Si no existe ninguna asociación que contenga esa clave de fila, se devolverá un mapa vacío.
	 * 
	 * @param rowKey clave de fila que se debe recuperar de la tabla
	 * @return mapa correspondiente asociando las claves de columna a su valor
	 */
	public Map<C,V> row(R rowKey);

	/**
	 * Devuelve una vista de todas las asociaciones que coniciden en emplear la misma clave 
	 * de columna. Por cada asociación de clave de fila / clave de columna / valor en la tabla
	 * original, el mapa devuelto contendrá la correspondiente asociación de clave de fila / valor.
	 * 
	 * Si no existe ninguna asociación que contenga esa clave de columna, se devolverá un mapa vacío.
	 * 
	 * @param columnKey clave de columna que se debe recuperar de la tabla
	 * @return mapa correspondiente asociando las claves de columna a su valor
	 */
	public Map<R,V> column(C columnKey);

	/**
	 * Devuelve el contenido completo de la tabla en forma de una conjunto de tripletas de 
	 * clave de fila / clave de columna / valor. Se tratará de una vista, por lo que los cambios sobre
	 * los datos de la colección se realizarán también sobre los datos contenidos en la tabla.
	 * 
	 * Cada una de las tripletas de datos se almacenará en una clase que implemente el interfaz
	 * Table.Cell (incluido en este fichero). 
	 * 
	 * La colección de celdas no deberá soportar los metodos de add o addAll.
	 * 
	 * @return contenido 
	 */
	Collection<Table.Cell<R,C,V>> cellSet();
	//** Para cumplir con la especificación de mapa, debería ser Set<Table.Cell<R,C,V>> cellSet();
	//Puesto que no se han estudiado las colecciones de tipo Set aún, se empleará la colección más genérica 

	/**
	 * Devuelve el numero de sociaciones de clave de columna / clave de fila / valor que
	 * se encuentran almacenadas en el mapa.
	 * 
	 * @return numero de sociaciones almacenadas en el mapa.
	 */
	public int size();

	/**
	 * Devuelve true si la tabla no contiene ninguna asociacion de datos y false en caso contrario.
	 * 
	 * @return true si la tabla no contiene ninguna asociacion de datos y false en caso contrario.
	 */
	public boolean isEmpty();


	/**
	 * Elimina todas las asociaciones de datos incluidas en el mapa.
	 */
	void clear();

	/**
	 * 
	 * Interfaz que permite encapsular en un solo objeto todos los datos correspondientes
	 * a las asociaciones que se incluyen en las clases que implementen el interfaz Table.
	 * 
	 * Los datos se deberán incluir en el método constructor y esta clase solo permitirá
	 * consultar las claves de fila o columna y consultar y actualizar el correspondiente valor.
	 * 
	 * @author bbaruque
	 *
	 * @param <R> Tipo de datos que se emplean como clave en las filas de la tabla
	 * @param <C> Tipo de datos que se emplean como clave en las columnas de la tabla
	 * @param <V> Tipo de datos que se emplean como valor en la tabla
	 */
	public interface Cell <R,C,V>{
		R getRowKey();
		C getColumnKey();
		V getValue();
		V setValue(V value );
	}

}