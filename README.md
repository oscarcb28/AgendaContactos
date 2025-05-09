==============================
INSTRUCCIONES DE USO - AGENDA TELEFÓNICA
==============================

Este cliente permite interactuar con un servidor para gestionar una agenda de contactos con múltiples teléfonos.

Cada operación se hace escribiendo una línea de texto en la caja inferior y presionando Enter.

Ejecutar "Servidor/Servidor" y seguidamente "Cleinte/ClienteGUI"

----------------------------------------
1. AÑADIR UN CONTACTO
----------------------------------------
Formato:
nombre:teléfono

Reglas:
- El nombre puede contener letras y espacios.
- El teléfono debe contener SOLO dígitos (sin espacios ni guiones).

Ejemplos:
oscar:612345678
daniel:699887766
oscar:611111111   ← Añade otro número al contacto oscar

Respuestas posibles:
✓ Contacto actualizado correctamente.
ERR01 - El número ya está registrado para este contacto.
ERR03 - Error de sintaxis en la petición.

----------------------------------------
2. BUSCAR TELÉFONOS DE UN CONTACTO
----------------------------------------
Formato:
buscar:nombre

Ejemplo:
buscar:oscar

Respuestas posibles:
✓ Teléfonos de oscar:
• 612345678
• 611111111

ERR02 - El contacto 'oscar' no existe.

----------------------------------------
3. ELIMINAR UN CONTACTO
----------------------------------------
Formato:
eliminar:nombre

Ejemplo:
eliminar:daniel

Respuestas posibles:
✓ Contacto 'daniel' eliminado con éxito.
ERR02 - El contacto 'daniel' no existe.

----------------------------------------
4. MOSTRAR TODOS LOS CONTACTOS
----------------------------------------
Formato:
contactos

Respuesta:
✓ Lista de contactos:
• oscar
• daniel
• lucia

Si no hay contactos:
✓ La agenda está vacía.

----------------------------------------
5. ERRORES DE SINTAXIS
----------------------------------------
Si escribes mal un comando o tiene caracteres inválidos, recibirás:
ERR03 - Error de sintaxis en la petición.
<petición original>
   ^ ← Señala el lugar del error

Ejemplo:
Entrada: oscar-612345678

Respuesta:
ERR03 - Error de sintaxis en la petición.
oscar-612345678
     ^

==============================
NOTAS ADICIONALES
==============================
- Cada petición se envía una por una (una línea = una acción).
- Puedes ejecutar múltiples clientes a la vez para probar concurrencia.
- El servidor debe estar corriendo en el puerto 5000 antes de abrir el cliente.

