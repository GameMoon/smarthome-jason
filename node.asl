// Agent node in project smarthome.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("hello world.").
+entered(HUMAN) : true <- +inside(HUMAN); .print(HUMAN," entered"); lightsOn.

+left(guest) : isInside(owner) <- -isInside(guest); .print(guest," left").
+left(guest) : true <- -isInside(guest); .print(guest," left"); lightsOff.

+left(owner) : isInside(guest) <- -isInside(owner); .print(owner," left").
+left(owner) : true <- -isInside(owner); .print(owner," left"); lightsOff.
