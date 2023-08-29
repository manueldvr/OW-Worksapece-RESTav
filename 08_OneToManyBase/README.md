# Asociaciones many-to-one

## Modelo de datos
● No es lo mismo crear un controlador para una entidad sin
asociaciones, que para una entidad asociada con otras entidades.<br>
● Si la entidad en cuestión tiene asociaciones nos tenemos que
preguntar qué hacer:<br>
+ + Al listar todos
+ +  Al listar uno
+ +  Al crear/editar

## Asociación Many To One
● Suele ser una de las asociaciones más frecuente.<br>
● Diversas variantes (agregación, composición, …)<br>
● Asocia una instancia de una entidad con una varias instancias de
otra.<br>

## Asociación Many To One - ejemplos:

○ **Producto → Categoría:**<br>
un producto se asocia a una categoría,
pero una categoría puede estar asociada a muchos productos<br>
○ **Pedido → Cliente:** <br>
un pedido se asocia a un único cliente, pero un
cliente puede realizar muchos pedidos.<br>
○ **Población → Provincia:** <br>
una población pertenece a una provincia,
pero una provincia tiene muchas poblaciones.
