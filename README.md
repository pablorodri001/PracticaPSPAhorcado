# PracticaPSPAhorcado

Este proyecto es una práctica de Programación de Servicios y Procesos (PSP) que implementa el clásico juego del ahorcado utilizando una arquitectura cliente-servidor con TCP. El servidor proporciona palabras, el cliente envía letras, y el servidor decide si el jugador gana o pierde.

## Descripción del Proyecto

En **PracticaPSPAhorcado**, el servidor selecciona una palabra aleatoria de una lista predefinida y el cliente intenta adivinarla enviando una letra en cada turno. El servidor responde indicando si la letra está en la palabra y actualizando el estado del juego hasta que el jugador adivine la palabra completa o se quede sin intentos.

## Arquitectura

- **Servidor**: Se encarga de seleccionar la palabra, recibir las letras enviadas por el cliente, y determinar si el jugador ha ganado o perdido.
- **Cliente**: Envía letras al servidor e interpreta las respuestas para informar al jugador del estado del juego.

## Características

- Comunicación bidireccional mediante sockets TCP.
- Selección aleatoria de palabras por el servidor.
- Control de intentos restantes y visualización del progreso de la palabra adivinada.
- Gestión de finalización del juego con victoria o derrota.

## Requisitos

- Java
- Conexión de red local o internet para la comunicación cliente-servidor

## Cómo Empezar

### Clonar el Repositorio

```bash
git clone https://github.com/pablorodri001/PracticaPSPAhorcado.git
cd PracticaPSPAhorcado
