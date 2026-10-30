# BookUsers · POO 2026B · Grupo D1 · UNIPAZ

Proyecto base del ejercicio BookUsers: registro de usuarios con Java Swing.
Programación Orientada a Objetos · Ingeniería Informática · Escuela de Ciencias.

## Contenido

Proyecto Java vacío, tal como lo crea IntelliJ IDEA: carpeta `src/` sin clases, configuración del módulo y `.gitignore`.
Todo el código (formularios, clases y paquetes) lo crea cada estudiante en su propia rama.

## Requisitos

- IntelliJ IDEA con el plugin **Swing UI Designer** (Settings → Plugins → Marketplace).
- JDK 21. Al abrir el proyecto, si IntelliJ indica *Project SDK is not defined*, se selecciona el JDK instalado.

## Procedimiento de entrega

1. **Fork** de este repositorio: botón *Fork* → **quitar la marca de «Copy the main branch only»** → *Create fork*. Así el fork trae todas las ramas del grupo, incluida la propia.
2. **Clonar el fork** desde Git Bash. El clon deja configurado `origin` apuntando al fork; no hay que configurar nada más.
   ```bash
   git clone https://github.com/USUARIO/BookUsers-D1.git
   cd BookUsers-D1
   ```
3. **Cambiar a la rama propia ANTES de escribir código.** La rama ya existe en el fork con el formato `ApellidoNombre` (primer apellido + primer nombre, sin tildes, sin ñ y sin espacios). Se busca en la lista y se activa:
   ```bash
   git branch -a                 # remotes/origin/ApellidoNombre
   git switch ApellidoNombre     # set up to track 'origin/ApellidoNombre'
   git status                    # On branch ApellidoNombre
   ```
   - **Todo el desarrollo y todos los commits van en esa rama.** `main` no recibe ningún commit.
   - Si `git switch` responde *invalid reference*, el fork se creó solo con `main`: se borra el fork y se repite el paso 1.
4. **Abrir en IntelliJ:** File → Open → carpeta `BookUsers-D1`. Si aparece *Project SDK is not defined*, se selecciona el JDK instalado.
5. **Commits con mensajes que describen el cambio** (mínimo uno por versión: v1, v2, v3, v4) y push a la rama propia:
   ```bash
   git status                    # debe decir: On branch ApellidoNombre
   git add .
   git commit -m "Se crea el formulario de bienvenida"
   git push                      # ApellidoNombre -> ApellidoNombre
   ```
   El primer `git push` abre el navegador para iniciar sesión en GitHub; no se usa la contraseña.
6. **Pull Request** desde el fork (*Contribute* → *Open pull request*) con:
   - base repository: este repositorio · **base: la rama con el nombre del estudiante** (no `main`)
   - head repository: el fork · compare: la rama del estudiante
   - Título: nombre completo y grupo. El Pull Request no se cierra ni se borra.

## Reglas

- El título de cada ventana incluye el nombre completo del estudiante.
- Lo que se revisa es el **historial de commits** del Pull Request: fechas, tamaño y orden de los cambios.
- Un solo commit con todo el proyecto no demuestra el proceso de trabajo.
- Los forks de un repositorio público son públicos: el código y el historial de cada estudiante son visibles.
