// Agent node in project smarthome.mas2j



/* Initial beliefs and rules */


thisPlace(kitchen).

/* Initial goals */



!start.



/* Plans */



+!start : true <- .print("hello world.").


+entered(HUMAN,WHERE): thisPlace(WHERE) <- -left(HUMAN,WHERE);
											.print(HUMAN," entered ",WHERE);
											lightsOn(WHERE).

+left(HUMAN,WHERE): thisPlace(WHERE) <- -entered(HUMAN,WHERE);
										  lightsOff(WHERE).


