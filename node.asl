// Agent node in project smarthome.mas2j

/* Initial beliefs and rules */
thisPlace(kitchen).

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("hello world.").

+entered(HUMAN,WHERE) : thisPlace(WHERE) <- -left(HUMAN,WHERE)[source(percept)];
											.print(HUMAN," entered ",WHERE);
											lightsOn(WHERE).
											
+entered(HUMAN,WHERE) : true<- .print("Not here").

+left(guest,WHERE) : thisPlace(WHERE) & entered(owner,WHERE) <- -entered(guest,WHERE)[source(percept)]; 
							  								    .print(guest," left ",WHERE).
							  
+left(guest,WHERE) : thisPlace(WHERE) & not entered(owner,WHERE) <- -entered(guest,WHERE)[source(percept)];
							  									   .print("Everyone left ",WHERE);
																   lightsOff(WHERE).
							  
+left(owner,WHERE) : thisPlace(WHERE) & entered(guest,WHERE) <- -entered(owner,WHERE)[source(percept)];
							  									.print(owner," left ",WHERE).
							  
+left(owner,WHERE) : thisPlace(WHERE) & not entered(guest,WHERE)  <- -entered(owner,WHERE)[source(percept)];
							  										 .print("Everyone left ",WHERE);
																	 lightsOff(WHERE).

