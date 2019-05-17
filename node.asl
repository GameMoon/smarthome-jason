// Agent node in project smarthome.mas2j



/* Initial beliefs and rules */


/* Initial goals */



!start.



/* Plans */



+!start : true <- .print("hello ",WHERE).

//lights
+entered(HUMAN,WHERE): thisPlace(WHERE) <- -left(HUMAN,WHERE);
											.print(HUMAN," entered ",WHERE);
											lightsOn(WHERE).

+left(owner,WHERE): thisPlace(WHERE) & not entered(guest,WHERE) <- -entered(owner,WHERE);
										  .print(owner," left ",WHERE);
										  .print("Everyone left ",WHERE);
										  lightsOff(WHERE).

+left(owner,WHERE): thisPlace(WHERE) & not left(guest,WHERE) <- -entered(owner,WHERE);
														.print(owner," left ",WHERE).
														
+left(guest,WHERE): thisPlace(WHERE) & not entered(owner,WHERE) <- -entered(guest,WHERE);
										  .print(guest," left ",WHERE);
										  .print("Everyone left ",WHERE);
										  lightsOff(WHERE).

+left(guest,WHERE): thisPlace(WHERE) & not left(owner,WHERE) <- -entered(guest,WHERE);
														.print(guest," left ",WHERE).

//temperature														
+highTemp(WHERE): thisPlace(WHERE) & highSmoke(WHERE) <- -lowTemp(WHERE);
														.print("Temp and smoke are high in ",WHERE,". Fire detected");
														.send(server,tell,highTemp);
														extinguish(WHERE).
														
+highTemp(WHERE): thisPlace(WHERE) & not highSmoke(WHERE) <- -lowTemp(WHERE);
														.print("Temp is high in ",WHERE,".");
														.send(server,tell,highTemp).
														
+lowTemp(WHERE): thisPlace(WHERE)<- -highTemp(WHERE);
									.print("Temp is low in ",WHERE,".");
									.send(server,tell,lowTemp);
									extinguishOff(WHERE).

//smoke
+highSmoke(WHERE): thisPlace(WHERE) & highTemp(WHERE) <- -lowSmoke(WHERE);
														.print("Temp and smoke are high in ",WHERE,". Fire detected");
														.send(server,tell,highSmoke);
														.send(server,tell,fireAt(WHERE));
														extinguish(WHERE).
														
+highSmoke(WHERE): thisPlace(WHERE) & not highTemp(WHERE) <- -lowSmoke(WHERE);
														.print("Smoke is high in ",WHERE,".");
														.send(server,tell,highSmoke).

+lowSmoke(WHERE): thisPlace(WHERE) <- -highSmoke(WHERE);
										.print("Smoke is low in ",WHERE,".");
										.send(server,tell,lowSmoke);
										extinguishOff(WHERE).
										
+extinguish[source(server)] <- ?thisPlace(WHERE);
							   -extinguishOff[source(server)];
							   -entered(guest,WHERE);
							   -entered(owner,WHERE);
							   -left(guest,WHERE);
							   -left(owner,WHERE);
							   lightsOff(WHERE);
							   extinguish(WHERE).
							   
+extinguishOff[source(server)] <- ?thisPlace(WHERE);
							   -extinguish[source(server)]
							   extinguishOff(WHERE).
