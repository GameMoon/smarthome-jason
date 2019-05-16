// Agent node in project smarthome.mas2j

/* Initial beliefs and rules */
thisPlace(kitchen).

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("hello world.").

+entered(HUMAN,WHERE) : thisPlace(WHERE) <- -left(HUMAN, WHERE);
											.print(HUMAN," entered ",WHERE);
											lightsOn(WHERE).

+left(guest,WHERE) : thisPlace(WHERE) & entered(owner,WHERE) <- -entered(guest,WHERE); 
							  								    .print(guest," left ",WHERE).
							  
+left(guest,WHERE) : thisPlace(WHERE) & not entered(owner,WHERE) <- -entered(guest,WHERE);
							  									   .print(guest," left ",WHERE);
																   lightsOff(WHERE).
							  
+left(owner,WHERE) : thisPlace(WHERE) & entered(guest,WHERE) <- -entered(owner,WHERE);
							  									.print(owner," left").
							  
+left(owner,WHERE) : thisPlace(WHERE) & not entered(guest,WHERE)  <- -entered(owner,WHERE);
							  										 .print(owner," left");
																	 lightsOff(WHERE).

